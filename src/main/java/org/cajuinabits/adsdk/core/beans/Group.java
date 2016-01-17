package org.cajuinabits.adsdk.core.beans;

import java.util.List;
import javax.naming.Name;
import org.cajuinabits.adsdk.core.exception.DuplicateObjetcException;
import org.cajuinabits.adsdk.core.exception.LdapException;

/**
 * Interface de manipula&ccedil;&atilde;o de atributos de Grupos do 
 * Servidor LDAP Active Directory.
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 05/06/2013
 * @see EntryImpl
 */
public interface Group {
    
    /**
     * Adiciona um membro ao grupo. Se o membro j&aacute; existe, uma exce&ccedil;&atilde;o 
     * &eacute; lan&ccedil;ada.
     * @param member
     * @throws IllegalArgumentException
     */        
    public void addMember(Entrance member) throws DuplicateObjetcException;

    public void removeMember(Entrance member) throws LdapException;

    /**
     * Retorna o(s) membro(s) do grupo.
     * @return 
     */            
    public List<Name> getMembers();
    
    @Override
    public boolean equals(Object obj);
    
}
