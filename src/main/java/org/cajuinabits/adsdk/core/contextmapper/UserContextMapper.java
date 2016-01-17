package org.cajuinabits.adsdk.core.contextmapper;

import org.cajuinabits.adsdk.core.beans.LdapUserImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;

/**
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 * @see AbstractContextMapper
 * @link: http://static.springsource.org/spring-ldap/docs/1.3.x/apidocs/
 */
public class UserContextMapper extends AbstractContextMapper {
    
    @Override
    protected Object doMapFromContext(DirContextOperations context) {
        LdapUserImpl user = null;
        try {
            user = new LdapUserImpl(context.getStringAttribute("cn"));
        } catch (InvalidNameException ex) {
            Logger.getLogger(UserContextMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            user.setId(new LdapName(context.getStringAttribute("distinguishedName")));
        } catch (InvalidNameException ex) {
            Logger.getLogger(UserContextMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!(context.getStringAttribute("description")==null)) {
            user.setDescription(context.getStringAttribute("description"));
        }
        
        if (!(context.getStringAttribute("givenName")==null)) {
            user.setFirstName(context.getStringAttribute("givenName"));
        }
        
        if (!(context.getStringAttribute("sn")==null)) {
            user.setLastName(context.getStringAttribute("sn"));
        }
        
        if (!(context.getStringAttribute("userPrincipalName")==null)) {
            user.setEmailAddress(context.getStringAttribute("userPrincipalName"));
        }
        
        if (context.attributeExists("memberOf")) {
            if ( !(context.getObjectAttributes("memberOf").length > 1) ) {
                String [] valoresMemberOf;
                valoresMemberOf = context.getStringAttributes("memberOf");
                for (int i=0; i < valoresMemberOf.length; i++) {
                    String dnMemberOf = valoresMemberOf[i];
                    try {
                        user.addMemberOf(new LdapName(dnMemberOf));
                    } catch (InvalidNameException ex) {
                        Logger.getLogger(UserContextMapper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    user.addMemberOf(new LdapName(context.getStringAttribute("memberOf")));
                } catch (InvalidNameException ex) {
                    Logger.getLogger(UserContextMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (!(context.getStringAttribute("displayName")==null)) {
            user.setDisplayName(context.getStringAttribute("displayName"));
        }
        
        if (!(context.getStringAttribute("st")==null)) {
            user.setState(context.getStringAttribute("st"));
        }
        
        if (!(context.getStringAttribute("street")==null)) {
            user.setStreet(context.getStringAttribute("street"));
        }
                    
        user.setUserAccountControl(context.getStringAttribute("userAccountControl"));
          
        if (context.getStringAttribute("userAccountControl").equals("512")) {
            user.enable();
        } else {
            user.disable();
        }
          
//        user.setAccountExpires(context.getStringAttribute("accountExpires"));
        return user;
    }
    
}