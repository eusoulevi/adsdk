package org.cajuinabits.adsdk.core.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapName;
import org.springframework.ldap.core.LdapAttribute;


/**
 * @author Levi de Sousa Soares
 * email: eusoulevi@gmail.com
 * @version 1.0
 * Entidade que representa uma Unidade Organizacional.
 */
public final class OrganizationalUnitImpl extends EntranceImpl implements OrganizationalUnit{

    public OrganizationalUnitImpl (String nameString) throws InvalidNameException {
        // equivale ao atributo ou (Ex.: ou=sead)
        super.addAttribute("ou", nameString); 
//        super.addAttribute("name", nameString); 
        super.setId(new LdapName("ou=" + nameString));
        super.addAttribute("objectclass","top");
        super.addAttributeValue("objectclass","organizationalUnit");
    }
    
    @Override
    public Attribute getOu() {
        return this.getAttribute("ou");        
    }
    
    @Override
    public Attribute getCity() {
        return this.getAttribute("l");
    }

    @Override
    public Attribute getPostalCode() {
        return this.getAttribute("postalCode");
    }

    public String getPostalCodeString() {
        String answer = null;
        try {
            answer = (String) this.getAttribute("postalCode").get();
        } catch (NamingException ex) {
            Logger.getLogger(OrganizationalUnitImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
        
    public String getCityString() {
        String answer = null;
        try {
            answer = (String) this.getAttribute("l").get();
        } catch (NamingException ex) {
            Logger.getLogger(OrganizationalUnitImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }

    /**
     * Altera o atributo l (city: cidade).
     * @param cityString
     */    
    public void setCity(String cityString) {
        if (!(this.attributeExists(("l")))) {
            this.addAttributeValue("l", cityString);
        }
        this.getAttribute("l").clear();
        this.getAttribute("l").add(cityString);
    }

    /**
     * Altera o atributo postalCode (c&oacute;digo postal).
     * @param postalCodeString
     */        
    public void setPostalCode(String postalCodeString) {
        if (!(this.attributeExists(("postalCode")))) {
            this.addAttributeValue("postalCode",postalCodeString);
        }                        
        this.getAttribute("postalCode").clear();
        this.getAttribute("postalCode").add(postalCodeString);
    }
    
    @Override
    public String toString() {
        String answer;
        answer = this.getClass().getName() + "{" + this.getAttributes() + "}";
        return answer;
    }

    /**
     * @param obj: UserImpl
     * @return: boolean
     */
    @Override
    public boolean equals(Object obj) {
        boolean answer = false;
        OrganizationalUnitImpl ouCompared;
        if(obj instanceof OrganizationalUnitImpl) {
            ouCompared = (OrganizationalUnitImpl) obj;
            if (this.hashCode()==ouCompared.hashCode()) {
                answer = true;
            }
        }
        return answer;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash + this.getAttribute("ou").hashCode()
                + this.getAttribute("objectclass").hashCode();
        return hash;
    }
    
}