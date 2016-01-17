package org.cajuinabits.adsdk.core.beans;

import javax.naming.directory.Attribute;

/**
 * Interface de manipula&ccedil;&atilde;o de atributos das unidades organizacionais do Servidor LDAP Active Directory. 
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 10/09/2012
 * @see OrganizationalUnitImpl
 */
public interface OrganizationalUnit extends Entrance{

    /**
     * Retorna o atributo ou (nome da unidade organizacional)
     * @return ou
     */    
    public Attribute getOu();

    /**
     * Retorna o atributo l (city: cidade).
     * @return l (city)
     */                
    public Attribute getCity();

    /**
     * Retorna o C&oacute;digo Postal.
     * @return postalCode
     */    
    public Attribute getPostalCode();
       
}