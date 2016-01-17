
package org.cajuinabits.adsdk.core.beans;

import javax.naming.InvalidNameException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapName;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ldap.core.LdapAttribute;

/**
 *
 * @author levi
 */
public class OrganizationalUnitImplIT {
    String nameOu = "outest";
    
    public OrganizationalUnitImplIT() {
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
     * Test of getOu method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testGetOu() throws InvalidNameException {
        System.out.println("getOu");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        Attribute expResult = new LdapAttribute("ou", nameOu);
        Attribute result = (Attribute) instance.getOu();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method, of class OrganizationalUnitImpl.
     * @throws javax.naming.InvalidNameException
     */
    @Test
    public void testGetCity() throws InvalidNameException {
        System.out.println("getCity");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        instance.setCity("Teresina");
        Attribute expResult = new LdapAttribute("l","Teresina");
        Attribute result = (Attribute) instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostalCode method, of class OrganizationalUnitImpl.
     * @throws javax.naming.InvalidNameException
     */
    @Test
    public void testGetPostalCode() throws InvalidNameException {
        System.out.println("getPostalCode");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        Attribute expResult = new LdapAttribute("postalCode","64003-715");
        instance.setPostalCode("64003-715");
        Attribute result = (Attribute) instance.getPostalCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostalCodeString method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testGetPostalCodeString() throws InvalidNameException {
        System.out.println("getPostalCodeString");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        instance.setPostalCode("64000-000");
        String expResult = "64000-000";
        String result = instance.getPostalCodeString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCityString method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testGetCityString() throws InvalidNameException {
        System.out.println("getCityString");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        instance.setCity("Teresina");
        String expResult = "Teresina";
        String result = instance.getCityString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        System.out.println("Mesmo teste getCity");
    }

    /**
     * Test of setPostalCode method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testSetPostalCode() throws InvalidNameException {
        System.out.println("setPostalCode");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        instance.setPostalCode("64000-000");
        String expResult = "64000-000";
        String result = instance.getPostalCodeString();
        assertEquals(expResult, result);    }

    /**
     * Test of toString method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testToString() throws InvalidNameException {
        System.out.println("toString");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        LdapName dn = new LdapName("cn=GrupoTest,cn=Builtin,dc=tjpithe,dc=local");
        instance.addMemberOf((dn));
        instance.setCity("Teresina");
        instance.setPostalCode("6400000");
        instance.setDescription("OU para Testes");
        String expResult = "org.cajuinabits.adsdk.core.beans.OrganizationalUnitImpl{dn: ou=outest\n" +
                "l: Teresina\n" +
                "ou: outest\n" +
                "description: OU para Testes\n" +
                "objectclass: top\n" +
                "objectclass: organizationalUnit\n" +
                "postalCode: 6400000\n" +
                "memberOf: cn=GrupoTest,cn=Builtin,dc=tjpithe,dc=local\n" +
                "}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testEquals() throws InvalidNameException {
        System.out.println("equals");
        OrganizationalUnitImpl obj = new OrganizationalUnitImpl("outest");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        boolean result = instance.equals(obj);
        assertTrue(result);
    }

    /**
     * Test of hashCode method, of class OrganizationalUnitImpl.
     */
    @Test
    public void testHashCode() throws InvalidNameException {
        System.out.println("hashCode");
        OrganizationalUnitImpl instance = new OrganizationalUnitImpl(nameOu);
        int result = instance.hashCode();
        LdapAttribute objectclass = new LdapAttribute("objectclass","organizationalUnit");
        objectclass.add("top");
        int hashcodeObject = objectclass.hashCode();
        int expResult = 3 + new LdapAttribute("ou", nameOu).hashCode() + hashcodeObject;
        assertEquals(expResult, result);
    }
    
}
