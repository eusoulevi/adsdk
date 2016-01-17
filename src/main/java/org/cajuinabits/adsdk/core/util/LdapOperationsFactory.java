
package org.cajuinabits.adsdk.core.util;

import java.io.File;
import javax.naming.NamingException;
import org.cajuinabits.adsdk.core.dao.LdapDaoImpl;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 *
 * @author levi
 */
public class LdapOperationsFactory {
    public static LdapDaoImpl dao;
    public static LdapOperations operations;

    /**
     * Construtor que inicializa inst&acirc;ncia com configura&ccedil;&otilde;es
     * armazenadas no arquivo de configura&ccedil;&atilde;o padr&atilde;o
     * (conf/springldap.xml).
     * @throws NamingException
     */
    public static LdapOperations getInstanceLdapOperations(String xmlFileConfig) throws NamingException {
        FileSystemXmlApplicationContext factory = null;
        LdapContextSource contextSource = null;
        if (operations == null) {
            if (!(xmlFileConfig.isEmpty())) {
                File fileconf = new File(xmlFileConfig);
                factory = new FileSystemXmlApplicationContext(fileconf.getAbsolutePath());
            }
            operations = (LdapTemplate) factory.getBean("ldapTemplate");
        }
        return operations;
    }

    /**
     *
     * @param xmlFileConfig
     * @return
     * @throws NamingException
     */
    public static LdapDaoImpl getInstanceLdapOperations(String xmlFileConfig ,LdapOperations ldapOperations)
            throws NamingException {
        FileSystemXmlApplicationContext factory = null;
        if (dao == null) {
            if (!(xmlFileConfig.isEmpty())) {
                File fileconf = new File(xmlFileConfig);
                factory = new FileSystemXmlApplicationContext(fileconf.getAbsolutePath());
            }
            dao = (LdapDaoImpl) factory.getBean("ldapDao");
        }
        return dao;
    }
    
}
