
package org.cajuinabits.adsdk.core.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.naming.InvalidNameException;
import javax.naming.Name;
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
public class GroupImplIT {
    String nameGroup = "GrupoTeste";
    
    public GroupImplIT() {
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
     * Test of addMember method, of class GroupImpl.
     */
    @Test
    public void testAddMember_Entrance() throws InvalidNameException {
        System.out.println("addMember");
        Entrance member1 = new GroupImpl("Administrators");
        Entrance member2 = new GroupImpl("Users");
        List<Name> expResult = new ArrayList<Name>();
        expResult.add(member1.getId());
        expResult.add(member2.getId());
        GroupImpl group = new GroupImpl(nameGroup);
        group.addMember(member1);
        group.addMember(member2);
        List<Name> members = group.getMembers();
        List<Name> result = new ArrayList<Name>();
        for (Iterator<Name> iterator = members.iterator(); iterator.hasNext();) {
            result.add(iterator.next());            
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of addMember method, of class GroupImpl.
     */
    @Test
    public void testAddMember_Name() throws InvalidNameException {
        System.out.println("addMember");
        GroupImpl group = new GroupImpl(nameGroup);
        Name expResult = new LdapName("CN=levi,OU=PJPI,ou=INTRANET,DC=tjpithe,DC=local");
        group.addMember(expResult);
        List<Name> member = group.getMembers();
        Name result = member.get(0);
        System.out.println(group.getMembers());
        assertEquals(expResult, result);        
    }

    /**
     * Test of getMembers method, of class GroupImpl.
     */
    @Test
    public void testGetMembers() throws InvalidNameException {
        System.out.println("getMembers");
        Name member1 = new LdapName("CN=member1,OU=TI,DC=cajuinabits,DC=org");
        Name member2 = new LdapName("CN=member2,OU=Finances,DC=cajuinabits,DC=org");
        GroupImpl group = new GroupImpl(nameGroup);
        group.addMember(member1);
        group.addMember(member2);
        List expResult = new ArrayList();
        expResult.add(member1);
        expResult.add(member2);
        List<Name> result = group.getMembers();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class GroupImpl.
     */
    @Test
    public void testEquals() throws InvalidNameException {
        System.out.println("equals");
        GroupImpl obj1 = new GroupImpl(nameGroup);
        GroupImpl obj2 = new GroupImpl(nameGroup);
        System.out.println(obj1.equals(obj2));
        assertTrue(obj1.equals(obj2));
    }

    /**
     * Test of removeMember method, of class GroupImpl.
     */
    @Test
    public void testRemoveMember_Entrance() throws InvalidNameException {
        System.out.println("removeMember");
        GroupImpl group = new GroupImpl(nameGroup);
        GroupImpl member1 = new GroupImpl("Operators");
        GroupImpl member2 = new GroupImpl("Convidados");
        group.addMember(member1);
        group.addMember(member2);
        group.removeMember(member2);
        List<Name> members = group.getMembers();
        
    }

    /**
     * Test of removeMember method, of class GroupImpl.
     */
    @Test
    public void testRemoveMember_Name() throws InvalidNameException {
        System.out.println("removeMember");
        Name member1 = new LdapName("CN=member1,OU=TI,DC=cajuinabits,DC=org");
        Name member2 = new LdapName("CN=member2,OU=Finances,DC=cajuinabits,DC=org");
        GroupImpl group = new GroupImpl(nameGroup);
        group.addMember(member1);
        group.addMember(member2);
        List expResult = new ArrayList();
        expResult.add(member1);
        expResult.add(member2);
        List<Name> result = group.getMembers();
        assertEquals(expResult, result);
        
        group.removeMember(member1);
        List<Name> result2 = group.getMembers();
        expResult.remove(member1);
        assertEquals(expResult, result2);
    }
    
}
