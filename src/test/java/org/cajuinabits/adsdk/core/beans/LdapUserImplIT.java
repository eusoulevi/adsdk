
package org.cajuinabits.adsdk.core.beans;

import java.io.UnsupportedEncodingException;
import javax.naming.InvalidNameException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.ldap.LdapName;
import static org.cajuinabits.adsdk.core.util.LdapUtil.encodePassword;
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
public class LdapUserImplIT {
     LdapUserImpl user;
     String nome = "userTest";
    
    public LdapUserImplIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InvalidNameException {
        user = new LdapUserImpl(nome);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setUserName method, of class LdapUserImpl.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        System.out.println("Testado no teste testGetUserName()");
    }

    /**
     * Test of getUserNameString method, of class LdapUserImpl.
     */
    @Test
    public void testGetUserNameString() {
        System.out.println("getUserNameString");
        String expResult = "User";
        user.setUserName(expResult);
        String result = user.getUserNameString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserName method, of class LdapUserImpl.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        String userName = "User";
        Attribute expResult = new BasicAttribute("sAMAccountName", userName);
        user.setUserName(userName);
        Attribute result = user.getUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class LdapUserImpl.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
    }

    /**
     * Test of getFirstNameString method, of class LdapUserImpl.
     */
    @Test
    public void testGetFirstNameString() {
        System.out.println("getFirstNameString");
        String expResult = "User";
        user.setFirstName(expResult);
        String result = user.getFirstNameString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class LdapUserImpl.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String firstName = "User";
        Attribute expResult = new LdapAttribute("givenName","User");
        user.setFirstName("User");
        Attribute result = user.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class LdapUserImpl.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        System.out.println("Teste executado no mÃ©todo testSetPassword()");
    }

    /**
     * Test of getLastNameString method, of class LdapUserImpl.
     */
    @Test
    public void testGetLastNameString() {
        System.out.println("getLastNameString");
        String expResult = "Test";
        user.setLastName(expResult);
        String result = user.getLastNameString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class LdapUserImpl.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String lastName = "Test";
        user.setLastName(lastName);
        Attribute expResult = new BasicAttribute("sn", lastName);
        Attribute result = user.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDisplayName method, of class LdapUserImpl.
     */
    @Test
    public void testSetDisplayName() {
        System.out.println("setDisplayName");
        String displayNameString = "Display Test";
        user.setDisplayName(displayNameString);
        Attribute expResult = new BasicAttribute("displayName", displayNameString);
        Attribute result = user.getDisplayName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayName method, of class LdapUserImpl.
     */
    @Test
    public void testGetDisplayName() {
        System.out.println("getDisplayName");
        String display = "User Test";
        user.setDisplayName(display);
        Attribute expResult = new BasicAttribute("displayName", display);
        Attribute result = user.getDisplayName();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDisplayNameString method, of class LdapUserImpl.
     */
    @Test
    public void testGetDisplayNameString() {
        System.out.println("getDisplayNameString");
        String expResult = "User Test";
        user.setDisplayName(expResult);
        String result = user.getDisplayNameString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class LdapUserImpl.
     */
    @Test
    public void testSetPassword() throws UnsupportedEncodingException {
        System.out.println("setPassword");
        String password = "1q!Q2w@W";
        LdapAttribute expResult = new LdapAttribute("unicodepwd",encodePassword(password));
        user.setPassword(password);
        LdapAttribute result = (LdapAttribute) user.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPasswordString method, of class LdapUserImpl.
     */
    @Test
    public void testGetPasswordString() throws UnsupportedEncodingException {
        System.out.println("getPasswordString");
        String expResult = "1q!Q2w@W";
        user.setPassword(expResult);
        String result = user.getPasswordString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class LdapUserImpl.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        System.out.println("Teste executado no mÃ©todo testSetPassword()");
    }

    /**
     * Test of setEmailAddress method, of class LdapUserImpl.
     */
    @Test
    public void testSetEmailAddress() {
        System.out.println("setEmailAddress");
        System.out.println("Teste executado no mÃ©todo testGetEmailAddress()");
    }

    /**
     * Test of getEmailAddressString method, of class LdapUserImpl.
     */
    @Test
    public void testGetEmailAddressString() {
        System.out.println("getEmailAddressString");
        String expResult = "teste@teste.local";
        user.setEmailAddress("teste@teste.local");
        String result = user.getEmailAddressString();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getEmailAddress method, of class LdapUserImpl.
     */
    @Test
    public void testGetEmailAddress() {
        System.out.println("getEmailAddress");
        String email = "teste@teste.local";
        user.setEmailAddress(email);
        Attribute expResult = new BasicAttribute("userPrincipalName", email);
        Attribute result = user.getEmailAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class LdapUserImpl.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        System.out.println("Teste executado no mÃ©todo testGetState()");
    }

    /**
     * Test of getStateString method, of class LdapUserImpl.
     */
    @Test
    public void testGetStateString() {
        System.out.println("getStateString");
        String expResult = "PI";
        user.setState(expResult);
        String result = user.getStateString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class LdapUserImpl.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        String uf = "PI";
        user.setState(uf);
        Attribute expResult = new LdapAttribute("st", uf);
        Attribute result = user.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStreet method, of class LdapUserImpl.
     */
    @Test
    public void testSetStreet() {
        System.out.println("setStreet");
        System.out.println("Teste executado no mÃ©todo testGetStreetString()");
    }

    /**
     * Test of getStreetString method, of class LdapUserImpl.
     */
    @Test
    public void testGetStreetString() {
        System.out.println("getStreetString");
        String expResult = "Test";
        user.setLastName(expResult);
        String result = user.getLastNameString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStreet method, of class LdapUserImpl.
     */
    @Test
    public void testGetStreet() {
        System.out.println("getStreet");
        String street = "Street Test";
        user.setStreet(street);
        Attribute result = user.getStreet();
        Attribute expResult = new BasicAttribute("street", street);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserAccountControl method, of class LdapUserImpl.
     */
    @Test
    public void testGetUserAccountControl() throws InvalidNameException {
        System.out.println("getUserAccountControl");
        LdapUserImpl instance = new LdapUserImpl(nome);
        Attribute expResult = new BasicAttribute("userAccountControl", "512");
        Attribute result = instance.getUserAccountControl();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEnabled method, of class LdapUserImpl.
     */
    @Test
    public void testIsEnabled() {
        System.out.println("isEnabled");
        user.setAttributeValue("userAccountControl", "512");
        assertTrue(user.isEnabled());
        user.setAttributeValue("userAccountControl", "2");
        assertFalse(user.isEnabled());
    }

    /**
     * Test of enable method, of class LdapUserImpl.
     */
    @Test
    public void testEnable() throws InvalidNameException {
        System.out.println("enable");
        LdapUserImpl instance = new LdapUserImpl("UserTest");
        instance.disable();
        assertFalse(instance.isEnabled());
        instance.enable();
        assertTrue(instance.isEnabled());
    }

    /**
     * Test of disable method, of class LdapUserImpl.
     */
    @Test
    public void testDisable() {
        System.out.println("disable");
        System.out.println("Teste executado no metodo testEnable()");
    }

    /**
     * Test of toString method, of class LdapUserImpl.
     */
    @Test
    public void testToString() throws InvalidNameException {
        System.out.println("toString");
        user.setFirstName("User");
        user.setLastName("Test");
        user.setDisplayName(user.getFirstNameString() + " " + user.getLastNameString());
        user.setEmailAddress("teste@test.com");
        user.setStreet("Rua 1 de Maio");
        user.setState("PI");
        user.addMemberOf(new LdapName("cn=GrupoTest,cn=Builtin,dc=cajuinabits,dc=org"));
        String expResult = "org.cajuinabits.adsdk.core.beans.LdapUserImpl{dn: cn=userTest\n" +
                "street: Rua 1 de Maio\n" +
                "sn: Test\n" +
                "givenName: User\n" +
                "objectclass: top\n" +
                "objectclass: person\n" +
                "objectclass: organizationalPerson\n" +
                "objectclass: user\n" +
                "memberOf: cn=GrupoTest,cn=Builtin,dc=cajuinabits,dc=org\n" +
                "userPrincipalName: teste@test.com\n" +
                "displayName: User Test\n" +
                "sAMAccountName: userTest\n" +
                "st: PI\n}";
        String result = user.toString();
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
