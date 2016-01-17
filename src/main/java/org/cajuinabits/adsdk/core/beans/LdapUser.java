package org.cajuinabits.adsdk.core.beans;

import javax.naming.directory.Attribute;

/**
 * Interface de manipula&ccedil;&atilde;o de atributos dos usu&aacute;rios do Servidor LDAP Active Directory. 
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 05/09/2012
 * @see UserImpl
 */
public interface LdapUser {
    
    /**
     * Altera o login (Campo: sAMAccountName) do usu&aacute;rio.
     * @param userNameString
     * Nome utilizado para efetuar o logon no servidor.
     */            
    public void setUserName(String userNameString);
    
    /**
     * Retorna o atributo sAMAccountName em forma de String.
     * @return sAMAccountName
     * login do usu&aacute;rio.
     */    
    public String getUserNameString ();

    /**
     * Retorna o atributo sAMAccountName.
     * @return sAMAccountName
     * login do usu&aacute;rio.
     */        
    public Attribute getUserName();
    
    /**
     * Atribui/Altera o primeiro nome (givenName) do usu&aacute;rio.
     * @param firstNameString
     * primeiro nome (givenName) do usu&aacute;rio.
     */    
    public void setFirstName(String firstNameString);
    
    /**
     * Retorna o primeiro nome (givenName) do usu&aacute;rio em forma de String.
     * @return givenName
     * primeiro nome (givenName) do usu&aacute;rio.
     */
    public String getFirstNameString();

    /**
     * Retorna o primeiro nome (givenName) do usu&aacute;rio.
     * @return firstName 
     * primeiro nome (givenName) do usu&aacute;rio.
     */    
    public Attribute getFirstName();
    
    /**
     * Altera o &Uacute;ltimo nome (sn) do usu&aacute;rio.
     * @param lastNameString 
     * &uacute;ltimo nome (sn) do usu&aacute;rio.
     */
    public void setLastName(String lastNameString);
    
    /**
     * Retorna o &Uacute;ltimo nome (sn) do usu&aacute;rio em forma de String.
     * @return 
     */
    public String getLastNameString();
    
    /**
     * Retorna o &Uacute;ltimo nome (sn) do usu&aacute;rio.
     * @return sn
     * &uacute;ltimo nome (sn) do usu&aacute;rio.
     */
    public Attribute getLastName();
    
    /**
     * Altera o nome de exibi&ccedil;ao (displayName) do usu&aacute;rio.
     * @param displayName
     * nome de exibi&ccedil;&atilde;o (displayName) do usu&aacute;rio.
     */      
    public void setDisplayName(String displayName);

    /**
     * Retorna o Nome de exibi&ccedil;ao (DisplayName) do usu&aacute;rio em forma de String.
     * @return displayNameString
     * nome de exibi&ccedil;&atilde;o do usu&aacute;rio.
     */    
    public String getDisplayNameString();
    
    /**
     * Retorna o Nome de exibi&ccedil;ao (DisplayName) do usu&aacute;rio.
     * @return displayNameString
     * nome de exibi&ccedil;&atilde;o do usu&aacute;rio.
     */    
    public Attribute getDisplayName();
    

    /**
     * Altera o password (unicodePwd) do usu&aacute;rio.
     * @param passwordString String: password do usu&aacute;rio.
     */      
    public void setPassword(String passwordString);
    
    /**
     * Retorna o password (unicodePwd) do usu&aacute;rio em forma de String.
     */
    public String getPasswordString();
    
    /**
     * Retorna o password (unicodePwd) do usu&aacute;rio.
     */        
    public Attribute getPassword();
    
    /**
     * Altera o email (userPrincipalName) do usu&aacute;rio.
     * @param emailAddressString
     * Email do usuario.
     */      
    public void setEmailAddress(String emailAddressString);

    /**
     * Retorna o email (userPrincipalName) do usu&aacute;rio em forma de String.
     */            
    public String getEmailAddressString();
    
    /**
     * Retorna o email (userPrincipalName) do usu&aacute;rio em forma de String.
     */            
    public Attribute getEmailAddress();
    /**
     * Altera a UF (st: Estado, Província, Distrito) do usu&aacute;rio.
     * @param st
     * UF do usu&aacute;rio.
     */          
    public void setState(String st);

    /**
     * Retorna a UF (st: Estado, Prov&iacute;ncia, Distrito) do usu&aacute;rio na forma de String.
     * @return st
     * UF do usu&aacute;rio.
     */                
    public String getStateString();

    /**
     * Retorna a UF (st: Estado, Prov&iacute;ncia, Distrito) do usu&aacute;rio.
     * @return st
     * UF do usu&aacute;rio.
     */                    
    public Attribute getState();

    /**
     * Altera a Rua (street: Rua, endere&ccedil;o) do usu&aacute;rio.
     * @param street
     * Rua do usu&aacute;rio.
     */              
    public void setStreet(String street);

    /**
     * Retorna a Rua (street: Rua, endere&ccedil;o) do usu&aacute;rio em forma de String.
     * @return street
     * Rua do usu&aacute;rio.
     */                  
    public String getStreetString();

    /**
     * Retorna a Rua (street: Rua, endere&ccedil;o) do usu&aacute;rio.
     * @return street
     * Rua do usu&aacute;rio.
     */                  
    public Attribute getStreet();

    /**
     * Verifica se a conta do usu&aacute;rio est&aacute; habilitada. <br>
     * @return <code>true</code> se o usu&aacute;rio estiver habilitado, 
     * <code>false</code> caso usu&aacute;rio.
     */
    public boolean isEnabled();

    /**
     * Habilita um usu&aacute;rio a autenticar-se no Servidor.
     * <b>OBS.:</b> O usu&aacute;rio ficar&aacute; desabilitado ap&oacute;s ser adicionado ao Servidor Active Directory.
     */    
    public void enable();
    
    /**
     * Desabilita um usu&aacute;rio de autenticar-se no Servidor.
     * <b>OBS.:</b> O usu&aacute;rio ficar&aacute; desabilitado após ser adicionado ao Servidor Active
     * Directory.
     */    
    public void disable();
  
    @Override
    public boolean equals(Object obj);

}
