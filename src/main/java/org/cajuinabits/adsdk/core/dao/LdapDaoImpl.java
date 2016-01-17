package org.cajuinabits.adsdk.core.dao;

import org.cajuinabits.adsdk.core.beans.Entrance;
import org.cajuinabits.adsdk.core.exception.LdapException;
import org.cajuinabits.adsdk.core.exception.LdapSaveException;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.LdapName;
import org.cajuinabits.adsdk.core.beans.EntranceImpl;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.ldap.core.*;
import org.springframework.ldap.core.support.DefaultIncrementalAttributesMapper;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;

/**
 * O LdapDaoImpl &eacute; respons&aacute;vel por criar e configurar uma
 * conex&atilde;o com o Servidor LDAP Active Directory.
 * @author Levi de Sousa Soares <br> email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 * @see LdapDao
 */
public class LdapDaoImpl implements LdapDao {

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
    private static final String CONFIGURATION_DIR_NAME = "src\\main\\java" + File.separatorChar + "conf";
    private static final String PARAMS_FILE_NAME = "springldap.xml";
    private static String classesPath = null;
    private static String filePath = null;
    private static FileSystemXmlApplicationContext factory = null;
    private static Resource resource = null;
    private static LdapDaoImpl instance = null;
    private LdapOperations ldapTemplate;
    private static LdapContextSource contextSource;
    private static Name base = null;
	SearchControls controls = new SearchControls();
    
    
    protected LdapDaoImpl() {}
    
    public static LdapDaoImpl getInstance(String xmlFileConfig) throws NamingException {
	    if (instance == null) {
			factory = new FileSystemXmlApplicationContext(xmlFileConfig);
		    instance = (LdapDaoImpl) factory.getBean("ldapDao");            
		    base = new LdapName(instance.getLdapOperations().getContextSource().getReadWriteContext().getNameInNamespace());
	    }
	    return instance;
    }

    @Override
    public void save(Entrance entry) throws LdapSaveException {
        try {
            extrairBase(entry);
        } catch (InvalidNameException ex) {
            Logger.getLogger(LdapDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        getLdapOperations().bind(entry.getId(), null, entry.getAttributes());
    }

	// TO-DO: Atualizar para a Nova VersÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Â ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢ÃƒÆ’Ã†â€™ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â£o do Spring LDAP 2.0 e refatorar com o novo mÃƒÆ’Ã†â€™Ãƒâ€ Ã¢â‚¬â„¢ÃƒÆ’Ã¢â‚¬Â ÃƒÂ¢Ã¢â€šÂ¬Ã¢â€žÂ¢ÃƒÆ’Ã†â€™ÃƒÂ¢Ã¢â€šÂ¬Ã…Â¡ÃƒÆ’Ã¢â‚¬Å¡Ãƒâ€šÃ‚Â©todo
	// void rebind(Object entry) da interface LdapOperations
    @Override
    public void update(Entrance entry) throws LdapSaveException {
        try {
            extrairBase(entry);
            Name dn = (Name) entry.getId().clone();
            DirContextOperations context = getLdapOperations().lookupContext(dn);        
            context.modifyAttributes(dn, DirContextOperations.REPLACE_ATTRIBUTE, entry.getAttributes());
//        getLdapOperations().rebind(entry.getId(), null, entry.getAttributes());
        } catch (InvalidNameException ex) {
            Logger.getLogger(LdapDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LdapDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modify(Entrance entry, Attribute attribute) throws LdapSaveException {
        try {
            modify(entry, attribute.getID(), attribute.get());
        } catch (NamingException ex) {
            Logger.getLogger(LdapDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void modify (Entrance entry, String attribute, Object value) throws LdapSaveException {
        try {
            extrairBase(entry);
        } catch (InvalidNameException ex) {
            Logger.getLogger(LdapDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                    new BasicAttribute(attribute, value));

        Name dnEntry = entry.getId();
        getLdapOperations().modifyAttributes(dnEntry, new ModificationItem[]{item});
    }

    @Override
    public LdapName getId(Entrance entrada) {
        return entrada.getId();
    }

    @Override
    public LdapTemplate getLdapOperations() {
        return (LdapTemplate) this.ldapTemplate;
    }

    @Override
    public void setLdapOperations(LdapOperations ldapOperations) {
        this.ldapTemplate = (LdapTemplate) ldapOperations;
    }

    protected void extrairBase(Entrance entry) throws InvalidNameException {
        LdapName dn = (LdapName) entry.getId();
        System.out.println("Antes de extrair: " + dn);
        if (dn.endsWith(base)) {
            dn.remove(0);
//            dn.removeFirst(base);
        }
    }
	
	public static void setBase(Name aBase) {
		base = aBase;
	}

    public <Entrance> List<Entrance> find(String name, Class clazz) {
        LdapQuery query = LdapQueryBuilder.query()
         .attributes("CN")
         .where("CN").is(name);
        return getLdapOperations().find(query, clazz);
    }

    public <Entrance> List<Entrance> findAll(Class clazz) {
        return getLdapOperations().findAll(clazz);
    }

    public Entrance findByDn(Name dn) {
        DirContextAdapter adapter = (DirContextAdapter) getLdapOperations().lookup(dn);
        EntranceImpl entranceImpl = new EntranceImpl();
        entranceImpl.setAttributes((LdapAttributes)adapter.getAttributes());
        return entranceImpl;
    }

    public void delete(Entrance entry) throws LdapException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <Entrance> List<Entrance> find(Attributes attrs, Class clazz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <Entrance> List<Entrance> search(LdapQuery query, AttributesMapper<Entrance> mapper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}