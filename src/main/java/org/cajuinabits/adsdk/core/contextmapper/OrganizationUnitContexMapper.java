package org.cajuinabits.adsdk.core.contextmapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnit;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnitImpl;
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
public class OrganizationUnitContexMapper extends AbstractContextMapper {
    @Override
    protected OrganizationalUnit doMapFromContext(DirContextOperations context) {
        OrganizationalUnitImpl ou = null;
        try {
            ou = new OrganizationalUnitImpl(context.getStringAttribute("ou"));
        } catch (InvalidNameException ex) {
            Logger.getLogger(OrganizationUnitContexMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ou.setId(new LdapName(context.getStringAttribute("distinguishedName")));
        } catch (InvalidNameException ex) {
            Logger.getLogger(OrganizationUnitContexMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (context.attributeExists("memberOf")) {
            if ( !(context.getObjectAttributes("memberOf").length > 1) ) {
                String [] valoresMemberOf;
                valoresMemberOf = context.getStringAttributes("memberOf");
                for (int i=0; i < valoresMemberOf.length; i++) {
                    String dnMemberOf = valoresMemberOf[i];
                    try {
                        ou.addMemberOf(new LdapName(dnMemberOf));
                    } catch (InvalidNameException ex) {
                        Logger.getLogger(OrganizationUnitContexMapper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    ou.addMemberOf(new LdapName(context.getStringAttribute("memberOf")));
                } catch (InvalidNameException ex) {
                    Logger.getLogger(OrganizationUnitContexMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (!(context.getStringAttribute("l")==null)) {
            ou.setCity(context.getStringAttribute("l"));
        }
        
        if (!(context.getStringAttribute("description")==null)) {
            ou.setDescription(context.getStringAttribute("description"));
        }

        if (!(context.getStringAttribute("postalCode")==null)) {
            ou.setPostalCode(context.getStringAttribute("postalCode"));
        }
        
        return ou;
    }
}