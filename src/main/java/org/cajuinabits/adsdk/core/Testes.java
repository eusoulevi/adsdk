package org.cajuinabits.adsdk.core;

import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import org.cajuinabits.adsdk.core.beans.GroupImpl;
import org.cajuinabits.adsdk.core.beans.LdapUserImpl;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnitImpl;
import org.cajuinabits.adsdk.core.dao.LdapDaoImpl;
import org.springframework.security.ldap.server.ApacheDSContainer;

/**
 *
 * @author levi
 */
public class Testes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidNameException, NamingException {
        String xmlFileConfig = "src/main/java/conf/springldap.xml";
        LdapDaoImpl dao = LdapDaoImpl.getInstance(xmlFileConfig);
        GroupImpl expResult2 = new GroupImpl("GrupoTeste");
        dao.save(expResult2);
    }
    
}
