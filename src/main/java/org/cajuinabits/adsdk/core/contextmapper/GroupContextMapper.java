package org.cajuinabits.adsdk.core.contextmapper;

import org.cajuinabits.adsdk.core.beans.GroupImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.ldap.LdapName;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;

/**
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 07/06/2013
 * @see AbstractContextMapper
 * @link: http://static.springsource.org/spring-ldap/docs/1.3.x/apidocs/
 */
public class GroupContextMapper extends AbstractContextMapper {

    @Override
    protected Object doMapFromContext(DirContextOperations context) {
        GroupImpl group = null;
        try {
            group = new GroupImpl(context.getStringAttribute("cn"));
        } catch (InvalidNameException ex) {
            Logger.getLogger(GroupContextMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(context.getStringAttribute("distinguishedName"));
            group.setId(new LdapName(context.getStringAttribute("distinguishedName")));
        } catch (InvalidNameException ex) {
            Logger.getLogger(GroupContextMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!(context.getStringAttribute("description")==null)) {
            group.setDescription(context.getStringAttribute("description"));
        }
        
        if (context.attributeExists("member")) {
            Object[] objectAttributes = context.getObjectAttributes("member");
                for (int i = 0; i < objectAttributes.length; i++) {
                    if (objectAttributes[i] instanceof LdapName ) {
                        group.addMember((Name) objectAttributes[i]);
                    } else {
                        try {
                            group.addMember(new LdapName((String) objectAttributes[i]));
                        } catch (InvalidNameException ex) {
                            Logger.getLogger(GroupContextMapper.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        }
        
        if (context.attributeExists("memberOf")) {
            Object[] objectAttributes = context.getObjectAttributes("memberOf");
                for (int i = 0; i < objectAttributes.length; i++) {
                    if (objectAttributes[i] instanceof LdapName ) {
                        group.addMemberOf((Name) objectAttributes[i]);
                    } else {
                        try {
                            group.addMemberOf(new LdapName((String) objectAttributes[i]));
                        } catch (InvalidNameException ex) {
                            Logger.getLogger(GroupContextMapper.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        }
        
        if (context.attributeExists("memberOf")) {
            if ( !(context.getObjectAttributes("memberOf").length > 1) ) {
                String [] valoresMemberOf;
                valoresMemberOf = context.getStringAttributes("memberOf");
                for (int i=0; i < valoresMemberOf.length; i++) {
                    String dnMemberOf = valoresMemberOf[i];
                    try {
                        group.addMemberOf(new LdapName(dnMemberOf));
                    } catch (InvalidNameException ex) {
                        Logger.getLogger(GroupContextMapper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    group.addMemberOf(new LdapName(context.getStringAttribute("memberOf")));
                } catch (InvalidNameException ex) {
                    Logger.getLogger(GroupContextMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
        
        return group;
    }
    
}
