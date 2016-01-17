
package org.cajuinabits.adsdk.core.dao;

import java.util.List;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import org.cajuinabits.adsdk.core.beans.Entrance;
import org.cajuinabits.adsdk.core.beans.GroupImpl;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnitImpl;
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
public class LdapDaoImplIT {
    public static String xmlFileConfig = "/src/main/java/conf/springldap.xml";
    public static LdapDaoImpl dao;
    
    public LdapDaoImplIT() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException {
        dao = LdapDaoImpl.getInstance(xmlFileConfig);
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
     * Test of getInstance method, of class LdapDaoImpl.
     */
    @Test
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        LdapDaoImpl result = LdapDaoImpl.getInstance(xmlFileConfig);
        assertNotNull(result);
    }

    /**
     * Test of save method, of class LdapDaoImpl.
     */
    @Test
    public void testSave() throws InvalidNameException {
        System.out.println("save");
        OrganizationalUnitImpl expResult = new OrganizationalUnitImpl("OUTeste");
        dao.save(expResult);
        GroupImpl expResult2 = new GroupImpl("GrupoTeste");
        System.out.println(expResult2);
        dao.save(expResult2);        
        OrganizationalUnitImpl result = (OrganizationalUnitImpl) dao.findByDn(expResult.getId());
        OrganizationalUnitImpl result2 = (OrganizationalUnitImpl) dao.findByDn(expResult2.getId());
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of update method, of class LdapDaoImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Entrance entry = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        instance.update(entry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modify method, of class LdapDaoImpl.
     */
    @Test
    public void testModify_Entrance_Attribute() {
        System.out.println("modify");
        Entrance entry = null;
        Attribute attribute = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        instance.modify(entry, attribute);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modify method, of class LdapDaoImpl.
     */
    @Test
    public void testModify_3args() {
        System.out.println("modify");
        Entrance entry = null;
        String attribute = "";
        Object value = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        instance.modify(entry, attribute, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class LdapDaoImpl.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Entrance entrada = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        LdapName expResult = null;
        LdapName result = instance.getId(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLdapOperations method, of class LdapDaoImpl.
     */
    @Test
    public void testGetLdapOperations() {
        System.out.println("getLdapOperations");
        LdapDaoImpl instance = new LdapDaoImpl();
        LdapTemplate expResult = null;
        LdapTemplate result = instance.getLdapOperations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLdapOperations method, of class LdapDaoImpl.
     */
    @Test
    public void testSetLdapOperations() {
        System.out.println("setLdapOperations");
        LdapContextSource ldapContextSource = new LdapContextSource();
        LdapOperations expResult = new LdapTemplate(ldapContextSource);
        LdapDaoImpl instance = new LdapDaoImpl();
        instance.setLdapOperations(expResult);
        LdapTemplate result = instance.getLdapOperations();
        assertEquals(expResult, result);
    }

    /**
     * Test of extrairBase method, of class LdapDaoImpl.
     */
    @Test
    public void testExtrairBase() throws Exception {
        System.out.println("extrairBase");
        Entrance entry = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        instance.extrairBase(entry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBase method, of class LdapDaoImpl.
     */
    @Test
    public void testSetBase() {
        System.out.println("setBase");
        Name aBase = null;
        LdapDaoImpl.setBase(aBase);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class LdapDaoImpl.
     */
    @Test
    public void testFind_String_Class() {
        System.out.println("find");
        String name = "";
        Class clazz = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        List expResult = null;
        List result = instance.find(name, clazz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class LdapDaoImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        Class clazz = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        List expResult = null;
        List result = instance.findAll(clazz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDn method, of class LdapDaoImpl.
     */
    @Test
    public void testFindByDn() throws InvalidNameException {
        System.out.println("findByDn");
        GroupImpl expResult = new GroupImpl("GrupoTeste");
        System.out.println(expResult);
        dao.save(expResult);
        GroupImpl result = (GroupImpl) dao.findByDn(expResult.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class LdapDaoImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Entrance entry = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        instance.delete(entry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class LdapDaoImpl.
     */
    @Test
    public void testFind_Attributes_Class() {
        System.out.println("find");
        Attributes attrs = null;
        Class clazz = null;
        LdapDaoImpl instance = new LdapDaoImpl();
        List expResult = null;
        List result = instance.find(attrs, clazz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
