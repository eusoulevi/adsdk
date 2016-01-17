
package org.cajuinabits.adsdk.core.dao;

import org.cajuinabits.adsdk.core.beans.Group;
import org.cajuinabits.adsdk.core.exception.LdapException;
import org.cajuinabits.adsdk.core.exception.LdapSaveException;
import java.util.List;
import org.cajuinabits.adsdk.core.beans.Entrance;
import org.cajuinabits.adsdk.core.beans.GroupImpl;

/**
 *
 * @author levi
 */
public interface GroupDao {
    
    public void save(Group group) throws LdapSaveException;
    
    public void update(Group group) throws LdapSaveException;

    public void delete (Group group) throws LdapException;
    
    public void modify (Group group, String attribute, Object value) throws LdapSaveException;
    
    public List<Group> findAllGroups() throws LdapException;

    /**
     * Verifica ser um determinado grupo existe no servidor Ldap.
     * @param nameGroup
     * Nome do grupo.
     * @return <code>true</code> caso o grupo exista, e 
     * <code>false</code> caso contr&aacute;rio.
     * @throws LdapException 
     */
    public boolean groupExists(String nameGroup) throws LdapException;
    
    public GroupImpl getGroup(String group) throws LdapException;
    
    public void addMember(Group group, Entrance member) throws LdapSaveException;
         
}
