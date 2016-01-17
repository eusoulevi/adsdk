
package org.cajuinabits.adsdk.core.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import org.cajuinabits.adsdk.core.exception.LdapException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ldap.core.LdapAttribute;
import org.springframework.ldap.core.LdapAttributes;
import org.springframework.ldap.support.LdapNameBuilder;

/**
 * Classe abstrata de manipula&ccedil;&atilde;o de atributos de objetos.
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 2.0
 * @since: 18/03/2015
 * @see LdapUserImpl
 * @see OrganizationalUnitImpl
 * @see GroupImpl
 */
public class EntranceImplIT {
    EntranceImpl instance;
    String nome = "ouTest";

    public EntranceImplIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InvalidNameException {
        instance = new GroupImpl(nome);
    }
    
    @After
    public void tearDown() {
        instance = null;
    }
    
    /**
     * Test of getId method, of class EntranceImpl.
     */
    @Test
    public void testGetId() throws InvalidNameException {
        System.out.println("getId");
        Name expResult = new LdapName("CN=" + nome);
        Name result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class EntranceImpl.
     */
    @Test
    public void testSetId_Name() throws InvalidNameException {
        System.out.println("setId");
        Name expResult = new LdapName("OU=testeSetDN" );
        instance.setId(expResult);
        Name result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class EntranceImpl.
     */
    @Test
    public void testSetId_String() throws InvalidNameException {
        System.out.println("setId");
        String expResult = "OU=testeSetDN";
        instance.setId(expResult);
        String result = instance.getId().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class EntranceImpl.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Attribute expResult = new LdapAttribute("description","Teste de Unidade");
        instance.setDescription("Teste de Unidade");
        Attribute result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescriptionString method, of class EntranceImpl.
     */
    @Test
    public void testGetDescriptionString() throws Exception {
        System.out.println("getDescriptionString");
        EntranceImpl instance1 = new OrganizationalUnitImpl(nome);
        String expResult = "Teste GetDescription";
        instance1.setDescription(expResult);
        String result = instance1.getDescriptionString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class EntranceImpl.
     */
    @Test
    public void testSetDescription() throws LdapException, NamingException {
        System.out.println("setDescription");
        String expResult = "TestDescricao";
        instance.setDescription(expResult);
        String result = instance.getDescriptionString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMemberOf method, of class EntranceImpl.
     */
    @Test
    public void testGetMemberOf() throws InvalidNameException {
        System.out.println("getMemberOf");
        String dnMemberOfString = "CN=GrupoTest,CN=Builtin,DC=tjpithe,DC=local";
        Name expResult = new LdapName(dnMemberOfString);
        instance.addMemberOf(expResult);
        LdapName result = null;
        try {
            result = (LdapName) instance.getMemberOf().get();
        } catch (NamingException ex) {
            Logger.getLogger(EntranceImplIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of getMemberOfString method, of class EntranceImpl.
     */
    @Test
    public void testGetMemberOfString() throws InvalidNameException {
        System.out.println("Test getMemberOfString");
        String [] expResult = {"CN=Administradores,OU=Grupos,DC=cajuinabits,DC=org",
            "CN=Suporte,OU=Grupos,DC=cajuinabits,DC=org"};
        instance.addMemberOf(new LdapName(expResult[0]));
        instance.addMemberOf(new LdapName(expResult[1]));
        String result[] = instance.getMemberOfString();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setAttributes method, of class EntranceImpl.
     */
    @Test
    public void testSetAttributes() throws InvalidNameException {
        System.out.println("setAttributes");
        Attributes expResult = new LdapAttributes();
        expResult.put("cn", "NameTest");
        expResult.put("member", new LdapName("CN=Nametest"));
        EntranceImpl instance2 = new OrganizationalUnitImpl(nome);
        instance2.setAttributes(expResult);
        Attributes result = instance2.getAttributes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAttributes method, of class EntranceImpl.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes: Teste executado no metodo testSetAttributes()");        
    }
    
    /**
     * Test of attributeExists method, of class EntranceImpl.
     */
    @Test
    public void testAttributeExists() {
        System.out.println("attributeExists");
        instance.addAttribute("name", new Object());
        assertTrue(instance.attributeExists("name"));
    }

    /**
     * Test of setAttributeValue method, of class EntranceImpl.
     */
    @Test
    public void testSetAttributeValue() {
        System.out.println("setAttributeValue");
        instance.addAttribute("canonicalName", "Nome Canonico Inicial");
        instance.setAttributeValue("canonicalName", "Atributo Alterado");
        Attribute expResult = new LdapAttribute("canonicalName", "Atributo Alterado");
        Attribute result = instance.getAttribute("canonicalName");
        assertEquals(expResult, result);
    }

    /**
     * Test of addAttribute method, of class EntranceImpl.
     */
    @Test
    public void testAddAttribute_Attribute() throws InvalidNameException {
        System.out.println("addAttribute");
        EntranceImpl entry = new OrganizationalUnitImpl(nome);
        String id = "comment";
        String value = "Teste Comentario";
        Attribute expResult = new LdapAttribute(id, value);
        entry.addAttribute(expResult);
        Attribute result = entry.getAttribute(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of addAttribute method, of class EntranceImpl.
     */
    @Test
    public void testAddAttribute_String_Object() {
        System.out.println("addAttribute");
        final String id = "comment";
        final String value = "Add test attribute";
        instance.addAttribute(id, value);
        Attribute expResult = new LdapAttribute(id, value);
        Attribute result = instance.getAttribute(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAttribute method, of class EntranceImpl.
     */
    @Test
    public void testGetAttribute() {
        System.out.println("getAttribute");
        final String id = "co";
        Attribute expResult = new LdapAttribute(id, "BRAZIL");
        instance.addAttribute(expResult);
        Attribute result = instance.getAttribute(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAttribute method, of class EntranceImpl.
     */
    @Test
    public void testRemoveAttribute() {
        System.out.println("removeAttribute");
        final String ID = "canonicalName";
        Attribute attr = new LdapAttribute(ID, "Unidade Organizacional de Teste");
        instance.addAttribute(attr);
        assertTrue(instance.attributeExists(ID));
        Attribute result;
        result = (Attribute) instance.removeAttribute(ID);
        assertFalse(instance.attributeExists(ID));        
    }

    /**
     * Test of toString method, of class EntranceImpl.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        instance.setDescription("Descricao Teste");
        String expResult = "org.cajuinabits.adsdk.core.beans.GroupImpl{dn: cn=ouTest\ndescription: "
                + "Descricao Teste\nobjectclass: top\nobjectclass: group\n"
//                + "member: null\n"
                + "}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getObjectclass method, of class EntranceImpl.
     */
    @Test
    public void testGetObjectclass() {
        System.out.println("getObjectclass");
        Attribute expResult = new LdapAttribute("objectclass", "group");
        expResult.add("top");
        Attribute result = instance.getObjectclass();
        assertEquals(expResult, result);
    }

    /**
     * Test of addMemberOf method, of class EntranceImpl.
     */
    @Test
    public void testAddMemberOf() {
        System.out.println("addMemberOf");
        System.out.println("Mesmo teste testGetMemberOf() throws InvalidNameException {");
    }

    /**
     * Test of getStringAttribute method, of class EntranceImpl.
     */
    @Test
    public void testGetStringAttribute() {
        System.out.println("getStringAttribute");
        String id = "display";
        String expResult = "Test GetStringAttribute()";
        instance.addAttribute(id, expResult);
        String result = instance.getStringAttribute(id);
        assertEquals(expResult, result);        
    }

    /**
     * Test of getObjectAttribute method, of class EntranceImpl.
     */
    @Test
    public void testGetObjectAttribute() {
        System.out.println("getObjectAttribute");
        String id = "description";
        Object expResult = new Object();
        instance.addAttribute(id, expResult);
        Object result = instance.getObjectAttribute(id);
        String id2 = "uf";
        String expResult2 = "PI";
        instance.addAttribute(id2, expResult2);
        Object result2 = instance.getObjectAttribute(id2);
        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of addAttributeValue method, of class EntranceImpl.
     */
    @Test
    public void testAddAttributeValue() {
        System.out.println("addAttributeValue");
        instance.addAttributeValue("comment", "Teste ComentÃƒÂ¡rio");
        Attribute expResult = new LdapAttribute("comment", "Teste ComentÃƒÂ¡rio");
        Attribute result = instance.getAttribute("comment");
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAttributeValue method, of class EntranceImpl.
     */
    @Test
    public void testRemoveAttributeValue() {
        System.out.println("removeAttributeValue");
        final String ID = "emails";
        String email1 = "email-1@cajuinabits.org";
        String email2 = "email-1@cajuinabits.org";
        
        instance.addAttribute(ID, email1);
        instance.addAttributeValue(ID, email2);

        Attribute expResult = new LdapAttribute(ID);
        expResult.add(email1);
        expResult.add(email2);
        
        instance.removeAttributeValue(ID, email1);
        expResult.remove(email1);
        
        Attribute result = instance.getAttribute(ID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getObjectAttributes method, of class EntranceImpl.
     */
    @Test
    public void testGetObjectAttributes() throws InvalidNameException {
        System.out.println("getObjectAttributes");
        EntranceImpl entry = new OrganizationalUnitImpl(nome);
        Object[] expResult = {
            "CN=user1,OU=Users,DC=cajuinabits,DC=org",
            "CN=user2,OU=Users,DC=cajuinabits,DC=org",
            "CN=groupTest,OU=Users,DC=cajuinabits,DC=org"};
        String id = "member";
        for (int i = 0; i < expResult.length; i++) {
            entry.addAttributeValue(id, expResult[i]);
        }
        Object[] result = entry.getObjectAttributes(id);
        assertArrayEquals(expResult, result);
    }


    /**
     * Test of setAttributeValues method, of class EntranceImpl.
     * @throws javax.naming.InvalidNameException
     */
    @Test
    public void testSetAttributeValues() throws InvalidNameException, NamingException {
        System.out.println("setAttributeValues");
        String id = "memberOf";
        LdapName [] memberOfs = new LdapName[3];
        memberOfs[0] = new LdapName("CN=g.cat.ESTAGIARIO,OU=GROUPS,OU=INTRANET,DC=tjpithe,DC=local");
        memberOfs[1] = new LdapName("CN=g.cat.MAGISTRADO,OU=GROUPS,OU=INTRANET,DC=tjpithe,DC=local");
        memberOfs[2] = new LdapName("CN=g.cat.SERVIDOR_CARREIRA,OU=GROUPS,OU=INTRANET,DC=tjpithe,DC=local");
        EntranceImpl entry = new OrganizationalUnitImpl(nome);
        entry.setAttributeValues(id, memberOfs);

        Attribute expResult = new LdapAttribute(id);
        for (int i = 0; i < memberOfs.length; i++) {
            expResult.add(memberOfs[i]);
        }
        
        Attribute result = entry.getAttribute(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of moveTo method, of class EntranceImpl.
     */
    @Test
    public void testMoveTo() throws InvalidNameException {
        System.out.println("moveTo");
        OrganizationalUnitImpl pai = new OrganizationalUnitImpl("Pai");
        EntranceImpl filho = new GroupImpl("Filho");
        filho.setId(new LdapName("CN=Filho"));
        Name expResult = LdapNameBuilder.newInstance().add("CN", "Filho").add("OU","Pai").build();
        filho.moveTo(pai);
        Name result = filho.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class EntranceImpl.
     */
    @Test
    public void testHashCode() throws InvalidNameException {
        System.out.println("hashCode");
        EntranceImpl entry = new GroupImpl(nome);
        int expResult = 3 * 5 + (entry.getAttributes() != null ? entry.getAttributes().hashCode() : 0);
        int result = entry.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class EntranceImpl.
     */
    @Test
    public void testEquals() throws InvalidNameException {
        System.out.println("equals");
        Object obj = null;
        EntranceImpl instance = new GroupImpl(nome);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}
