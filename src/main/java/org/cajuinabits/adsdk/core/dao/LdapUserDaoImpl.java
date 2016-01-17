package org.cajuinabits.adsdk.core.dao;

import org.cajuinabits.adsdk.core.contextmapper.UserContextMapper;
import org.cajuinabits.adsdk.core.beans.LdapUser;
import org.cajuinabits.adsdk.core.beans.UserDetails;
import org.cajuinabits.adsdk.core.beans.LdapUserImpl;
import org.cajuinabits.adsdk.core.beans.Entrance;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnit;
import org.cajuinabits.adsdk.core.exception.LdapException;
import org.cajuinabits.adsdk.core.exception.LdapSaveException;
import org.cajuinabits.adsdk.security.ActiveDirectoryUtils;
import static org.cajuinabits.adsdk.core.util.LdapUtil.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.ldap.core.*;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;

/**
 * Respons&aacute;vel por criar e configurar uma conex&atilde;o com o Servidor
 * LDAP Active Directory.
 *
 * @author Levi de Sousa Soares <br> email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 * @see LdapUserDao
 * @see DaoImpl.
 */
public class LdapUserDaoImpl extends LdapDaoImpl implements LdapUserDao {

    private static final String USER_ACCOUNT_CONTROL_ATTR_NAME = "userAccountControl";
    private static final String ACCOUNT_EXPIRES_ATTR_NAME = "accountExpires";
    private static final String PWD_LAST_SET_ATTR_NAME = "pwdLastSet";
    private static final String PASSWORD_ATTR_NAME = "unicodepwd";
    private static final String DISTINGUISHED_NAME_ATTR_NAME = "distinguishedname";
    private static final String MEMBER_ATTR_NAME = "member";
    // usercontrol params
    private static final int FLAG_TO_DISABLE_USER = 0x2;
    private static final int ADS_UF_DONT_EXPIRE_PASSWD = 0x10000;
    private static final int USER_CONTROL_NORMAL_USER = 512;
    private static final String CONFIGURATION_DIR_NAME = "conf";
    private static final String PARAMS_FILE_NAME = "springldap.xml";
    private static String classesPath = null;
    private static BeanFactory factory = null;
    private static Resource resource = null;
    private LdapTemplate ldapTemplate;
    ActiveDirectoryUtils activeDirectoryUtils = new ActiveDirectoryUtils();

    /**
     * Construtor que inicializa inst&acirc;ncia com configura&ccedil;&otilde;es
     * armazenadas no arquivo xmlFileConfig
     * @return 
     * @throws UnsupportedEncodingException 
     */
    public static LdapUserDaoImpl getInstance() {
        if (factory == null) {
            classesPath = LdapUserDaoImpl.class.getResource("/").getPath();
            factory = new FileSystemXmlApplicationContext(classesPath + CONFIGURATION_DIR_NAME + "/" + PARAMS_FILE_NAME);
        }
        return (LdapUserDaoImpl) factory.getBean("ldapUser");
    }

    /**
     * Obt&eacute;m uma inst&acirc;ncia do DaoImpl com as
     * cofigura&ccedil;&otilde;es armazenadas no arquivo xmlFileConfig.
     *
     * @param xmlFileConfig Arquivo que cont&eacute;m as configurações do
     * Servidor Active Directory (Endere&ccedil;o, modo de conex&atilde;o, etc).
     * @throws UnsupportedEncodingException  
     */
    public static LdapUserDaoImpl getInstance(String xmlFileConfig) {
        if (factory == null) {
            classesPath = LdapUserDaoImpl.class.getResource("/").getPath();
            factory = new FileSystemXmlApplicationContext(classesPath
                    + CONFIGURATION_DIR_NAME + "/" + xmlFileConfig, xmlFileConfig);
        }
        return (LdapUserDaoImpl) factory.getBean("ldapUser");
    }

    public List<LdapUser> getAllUsers() throws LdapException {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        return getLdapOperations().search("", "(objectclass=person)", controls, new UserContextMapper());
    }

    @Override
    public boolean userExists(String loginString) throws LdapException {
        boolean resulB = false;
        List<LdapUser> users = getAllUsers();
        for (Iterator<LdapUser> it = users.iterator(); it.hasNext();) {
            LdapUser user = it.next();
            if (user.getUserNameString().equals(loginString)) {
                resulB = true;
                break;
            }
        }
        return resulB;
    }


    public UserDetails getUserDetails(String userName) {
        DirContextAdapter dirContext = (DirContextAdapter) ldapTemplate.lookup(getDnFrom(userName));
        String dnUserFull = dirContext.getStringAttribute(DISTINGUISHED_NAME_ATTR_NAME);
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String userAccountControlStr = dirContext.getStringAttribute(USER_ACCOUNT_CONTROL_ATTR_NAME);
        String pwdLastSet = dirContext.getStringAttribute(PWD_LAST_SET_ATTR_NAME);
        String accountExpires = dirContext.getStringAttribute(ACCOUNT_EXPIRES_ATTR_NAME);
        String maxPwdAge = "-36288000000000";
        UserDetails userDetails = activeDirectoryUtils.getUserDetailsFrom(userName, dnUserFull, userAccountControlStr, pwdLastSet, accountExpires, maxPwdAge);
        return userDetails;
    }

    public LdapUserImpl findUser(String firstName) throws InvalidNameException {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        LdapQueryBuilder query = LdapQueryBuilder.query();
        query.base("base").attributes("objectclass", "cn").where("objectclass").is("person").and("cn").is(firstName);
        LdapUserImpl user = null;
        List<LdapUserImpl> find = getLdapOperations().find(query, LdapUserImpl.class);
        return user;
    }

    @Override
    public boolean login(String userName, String password) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("cn", userName));
        boolean authenticate = ldapTemplate.authenticate(DistinguishedName.EMPTY_PATH, filter.toString(), password);
        return authenticate;
    }

    @Override
    public void changePassword(String userName, String password) {
        try {
            ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(PASSWORD_ATTR_NAME, encodePassword(password)));
            ldapTemplate.modifyAttributes(getDnFrom(userName), new ModificationItem[]{item});
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LdapUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void enableUser(String userName) {
        DirContextOperations userContextOperations = ldapTemplate.lookupContext(getDnFrom(userName));
        String userAccountControlStr = userContextOperations.getStringAttribute(USER_ACCOUNT_CONTROL_ATTR_NAME);
        int newUserAccountControl = Integer.parseInt(userAccountControlStr) & ~FLAG_TO_DISABLE_USER;
        userContextOperations.setAttributeValue(USER_ACCOUNT_CONTROL_ATTR_NAME, "" + newUserAccountControl);
        ldapTemplate.modifyAttributes(userContextOperations);
    }

    @Override
    public void disableUser(String userName) {
        DirContextOperations userContextOperations = ldapTemplate.lookupContext(getDnFrom(userName));
        String userAccountControlStr = userContextOperations.getStringAttribute(USER_ACCOUNT_CONTROL_ATTR_NAME);
        int newUserAccountControl = Integer.parseInt(userAccountControlStr) | FLAG_TO_DISABLE_USER;
        userContextOperations.setAttributeValue(USER_ACCOUNT_CONTROL_ATTR_NAME, "" + newUserAccountControl);
        ldapTemplate.modifyAttributes(userContextOperations);
    }


//    private int getUserAccountControl(User user) {
//        int userAccounControl = USER_CONTROL_NORMAL_USER;
//        if (!user.isExpirePasswd()) {
//            userAccounControl |= ADS_UF_DONT_EXPIRE_PASSWD;
//        }
//        return userAccounControl;
//    }
    @Override
    public void addUserToGroup(String userName, String group) {
        try {
            DirContextAdapter dirContext = (DirContextAdapter) ldapTemplate.lookup(getDnFrom(userName));
            String dnUserFull = dirContext.getStringAttribute(DISTINGUISHED_NAME_ATTR_NAME);
            DirContextOperations groupContextOperations = ldapTemplate.lookupContext(getDnFrom(group));
            String[] currentMembers = groupContextOperations.getStringAttributes(MEMBER_ATTR_NAME);
            List<String> dnUserFullList = new ArrayList<String>();
            if (currentMembers != null && currentMembers.length > 0) {
                dnUserFullList.addAll(Arrays.asList(currentMembers));
            }
            dnUserFullList.add(dnUserFull);
            groupContextOperations.setAttributeValues(MEMBER_ATTR_NAME, dnUserFullList.toArray(new String[dnUserFullList.size()]));
            ldapTemplate.modifyAttributes(groupContextOperations);
        } catch (Throwable e) {
            throw new LdapException("Problema na  adição do usuário:[" + userName + "] para o Grupo:[" + group + "]", e);
        }
    }

    private DistinguishedName getDnFrom(String userName) {
        return new DistinguishedName("CN=" + userName);
    }

    @Override
    public void save(LdapUser user) {
        this.save(user);
    }

    public void update(LdapUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(LdapUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modify(LdapUser user, String attribute, Object value) throws LdapSaveException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<LdapUser> getAllUsers(OrganizationalUnit ou) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LdapUserImpl getUser(String userName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}