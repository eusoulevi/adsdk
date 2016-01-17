
package org.cajuinabits.adsdk.core.contextmapper;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import org.cajuinabits.adsdk.core.beans.GroupImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapAttribute;
import org.springframework.ldap.core.LdapAttributes;

/**
 *
 * @author levi
 */
public class GroupContextMapperIT {
    DirContextOperations context = null;
    GroupContextMapper instance;
    Name base;
    Name dnGroup;
    Attribute cn;
    Attribute description;
    Attributes attributes;
    Attribute member;
    
    public GroupContextMapperIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InvalidNameException {
        base = new LdapName("OU=groups,DC=cajuinabits,DC=org");
        
        cn = new LdapAttribute("CN", "groupTest");
        description = new LdapAttribute("description", "Grupo de Teste");
        member = new LdapAttribute("member", new LdapName("CN=raniel.nunes,OU=SEAJU,OU=TJ-PI,OU=PJPI,OU=INTRANET,DC=tjpithe,DC=local"));
        member.add(new LdapName("OU=STIC,OU=TJ-PI,OU=PJPI,OU=INTRANET,DC=tjpithe,DC=local"));
        
        attributes = new LdapAttributes();
        attributes.put(cn);
        attributes.put(description);
        attributes.put(member);
        context = new DirContextAdapter(attributes, dnGroup, base);        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of doMapFromContext method, of class GroupContextMapper.
     */
    @Test
    public void testDoMapFromContext() throws InvalidNameException {
        System.out.println("doMapFromContext");
        dnGroup = new LdapName("CN=groupTest,OU=groups,DC=cajuinabits,DC=org");
        instance = new GroupContextMapper();
        GroupImpl expResult = new GroupImpl("groupTest");
        expResult.setId(dnGroup);
        expResult.addAttribute(description);
        expResult.addAttribute(member);
        GroupImpl result;
        result = (GroupImpl) instance.doMapFromContext(context);
        assertEquals(expResult, result);
    }
    
}
