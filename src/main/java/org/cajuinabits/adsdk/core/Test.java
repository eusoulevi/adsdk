package org.cajuinabits.adsdk.core;

import javax.naming.NamingException;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnitImpl;
import org.cajuinabits.adsdk.core.dao.LdapDaoImpl;
import org.cajuinabits.adsdk.core.util.LdapOperationsFactory;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.security.ldap.server.ApacheDSContainer;

/**
 *
 * @author levi
 */
public class Test {
    
    public static LdapDaoImpl dao;
    public static String xmlFileConfig = "src/main/java/conf/springldap.xml";

    public static void main(String[] args) throws NamingException, Exception {
        dao = LdapDaoImpl.getInstance(xmlFileConfig);
        ApacheDSContainer dSContainer = new ApacheDSContainer("admin", "src/main/java/conf/schema-b92c7de4-e232-4d8d-bdf7-27aeaa2a0faa.ldif");
        dSContainer.setPort(10389);
        dSContainer.start();
        if (dSContainer.isRunning()) {
            System.out.println("Servidor Funcionando");
        }
    }
    
}
