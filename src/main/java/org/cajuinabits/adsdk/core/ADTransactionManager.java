
package org.cajuinabits.adsdk.core;

import java.io.File;
import javax.naming.NamingException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;

/**
 *
 * @author levi
 */
public class ADTransactionManager {
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
    private static final String CONFIGURATION_DIR_NAME = "src" + File.separatorChar + "conf";
    private static final String PARAMS_FILE_NAME = "springldap.xml";
    private static String classesPath = null;
    private static String filePath = null;
    private static BeanFactory factory = null;
    private static Resource resource = null;
    private static ADTransactionManager instance = null;
    private LdapTemplate ldapTemplate;
    private static DistinguishedName base = null;

	public static ADTransactionManager getInstance(String xmlFileConfig) throws NamingException {
		if (!(xmlFileConfig.isEmpty())) {
            File fileconf = new File(xmlFileConfig);
            factory = new FileSystemXmlApplicationContext(fileconf.getAbsolutePath());			
		}
		
        if (resource == null) {
            instance = (ADTransactionManager) factory.getBean("myDataAccessObject");
            base = new DistinguishedName(instance.ldapTemplate.getContextSource().getReadWriteContext().getNameInNamespace());
        }
        return instance;
	}
	
	public static ADTransactionManager getInstance() throws NamingException {
		String xmlFileConfig = CONFIGURATION_DIR_NAME + File.separatorChar + "springldap.xml";
		return ADTransactionManager.getInstance(xmlFileConfig);
	}
		
	    
        
}
