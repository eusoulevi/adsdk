package org.cajuinabits.adsdk.core.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cajuinabits.adsdk.core.exception.LdapException;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import org.springframework.ldap.core.LdapAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.support.LdapNameBuilder;

/**
 * Classe que representa um Grupo no
 * Servidor LDAP Active Directory.
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 06/06/2013
 * @see Entry, EntryImpl
 */
public class GroupImpl extends EntranceImpl implements Group {

    public GroupImpl (String nameString) throws InvalidNameException {
        super.setId(LdapNameBuilder.newInstance().add("CN",nameString).build());
        this.addAttribute("objectclass","top");
        this.addAttributeValue("objectclass","group");
//        this.addAttribute("member", null);
    }


    @Override
    public void addMember(Entrance member) {
        this.addMember(member.getId());
    }
    
    public void addMember(Name member) {
        Name cloneDNmember = (Name) member.clone();        
        if (!(this.attributeExists("member"))) {
            this.addAttribute(new LdapAttribute("member",cloneDNmember));
        } else {
            this.addAttributeValue("member", member);
        }                
    }
    
    @Override
    public List<Name> getMembers() {
        Attribute members = this.getAttribute("member");
        List <Name> resposta = null;
        if (members != null) {
            resposta = new ArrayList<Name>();
            for (int i = 0; i < members.size(); i++) {
                try {
                    Name member = (Name) members.get(i);
                    resposta.add(member);
                } catch (NamingException ex) {
                    Logger.getLogger(GroupImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resposta;
    }
    
    public void removeMember(Entrance member) throws LdapException {
        removeMember(member.getId());
    }

    public void removeMember(Name member) throws LdapException {
        this.removeAttributeValue("member", member);
    }
    
}