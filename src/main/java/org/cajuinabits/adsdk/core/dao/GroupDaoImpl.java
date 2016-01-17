package org.cajuinabits.adsdk.core.dao;

import org.cajuinabits.adsdk.core.contextmapper.GroupContextMapper;
import org.cajuinabits.adsdk.core.beans.Group;
import org.cajuinabits.adsdk.core.beans.GroupImpl;
import org.cajuinabits.adsdk.core.exception.LdapException;
import org.cajuinabits.adsdk.core.exception.LdapSaveException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import org.cajuinabits.adsdk.core.beans.Entrance;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;


/**
 *
 * @author levi
 */
public class GroupDaoImpl extends LdapDaoImpl implements GroupDao {
	SearchControls controls = new SearchControls();
    
    @Override
    public void save(Group group) throws LdapSaveException {
        this.save(group);
    }

    @Override
    public void update(Group group) throws LdapSaveException {
        this.update(group);
    }

    @Override
    public void delete(Group group) throws LdapException {
        this.delete(group);
    }

    @Override
    public void modify(Group group, String attribute, Object value) throws LdapSaveException {
        this.modify(group, attribute, value);
    }

    /**
     * Retorna uma lista com todos os grupos no servidor
     * active directory.
     * @return List<Group>
     */    
    
    @Override
    public List<Group> findAllGroups() {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        return getLdapOperations().search("", "(objectclass=group)", controls, new GroupContextMapper());
    }

    /**
     * Retorna um grupo espec&iacutefico do servidor Active Directory.
     * @param group nome do grupo.
     * @return GroupImpl
     * @throws LdapException
     */    
    @Override
    public GroupImpl getGroup(String group) throws LdapException {
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "group")).and(new EqualsFilter("cn", group));
        List<GroupImpl> groups = getLdapOperations().search("", filter.toString(), controls, new GroupContextMapper());
        if (groups.isEmpty()) {
            return null;
        }
        GroupImpl answer = groups.get(0);
        return answer;
    }

    /**
     * Adiciona um objeto (usuario, grupo ou unidade organizacional ) em um Grupo.
     * @param group Grupo que receber&aacute; o membro.
     * @param member Objeto a ser adicionado ao grupo.
     */
    @Override
    public void addMember(Group group, Entrance member) throws LdapSaveException {
        GroupImpl grupo = (GroupImpl) group;
        try {
            extrairBase(grupo);
        } catch (InvalidNameException ex) {
            Logger.getLogger(GroupDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("member", member.getMemberOf()));
        getLdapOperations().modifyAttributes(grupo.getId(), new ModificationItem[]{item});
    }

    /**
     * Verifica se um Grupo existe no servidor Active
     * Directory.
     *
     * @param searchGroup Grupo a ser verificado
     * @return <code>true</code> se o Grupo existir no 
     * Active Directory, <code>false</code> caso contr&aacute;rio.
     * @throws LdapException
     */        
    public boolean groupExists(Group searchGroup) throws LdapException {
        boolean answer = false;
        List<Group> groups = this.findAllGroups();
        for (Iterator<Group> it = groups.iterator(); it.hasNext();) {
            Group group = it.next();
            if (searchGroup.equals(group)) {
                answer = true;
                break;
            }
        }
        return answer;

    }

    public boolean groupExists(String nameGroup) throws LdapException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
