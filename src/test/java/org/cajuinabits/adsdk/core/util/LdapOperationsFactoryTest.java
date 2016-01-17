package org.cajuinabits.adsdk.core.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.cajuinabits.adsdk.core.dao.LdapDaoImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 *
 * @author levi
 */
public class LdapOperationsFactoryTest {
    
    public LdapOperationsFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstanceLdapOperations method, of class LdapOperationsFactory.
     */
    @Test
    public void testGetInstanceLdapOperations() throws Exception {
        System.out.println("getInstanceLdapOperations");
        String xmlFileConfig = "src\\main\\java" + File.separatorChar + "conf\\springldap.xml";
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://127.0.0.1:10389");
        contextSource.setBase("dc=cajuinabits,dc=org");
        contextSource.setAnonymousReadOnly(true);
        Map map = new HashMap();
        map.put("java.naming.security.authentication", "simple");
        contextSource.setBaseEnvironmentProperties(map);
        contextSource.setReferral("follow");
        LdapOperations expResult = new LdapTemplate(contextSource);
        LdapOperations result = LdapOperationsFactory.getInstanceLdapOperations(xmlFileConfig);
        System.out.println(expResult);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstanceLdapOperations method, of class LdapOperationsFactory.
     */
    @Test
    public void testGetInstanceLdapOperations_String() throws Exception {
        System.out.println("getInstanceLdapOperations");
        String xmlFileConfig = "";
        LdapOperations expResult = null;
        LdapOperations result = LdapOperationsFactory.getInstanceLdapOperations(xmlFileConfig);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstanceLdapOperations method, of class LdapOperationsFactory.
     */
    @Test
    public void testGetInstanceLdapOperations_String_LdapOperations() throws Exception {
        System.out.println("getInstanceLdapOperations");
        String xmlFileConfig = "";
        LdapOperations ldapOperations = null;
        LdapDaoImpl expResult = null;
        LdapDaoImpl result = LdapOperationsFactory.getInstanceLdapOperations(xmlFileConfig, ldapOperations);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}