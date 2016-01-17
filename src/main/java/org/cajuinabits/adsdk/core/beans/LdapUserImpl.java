package org.cajuinabits.adsdk.core.beans;

import org.cajuinabits.adsdk.core.dao.LdapUserDao;
import static org.cajuinabits.adsdk.core.util.LdapUtil.*;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import org.springframework.ldap.core.LdapAttribute;
import org.springframework.ldap.support.LdapNameBuilder;

/**
 * @author Levi de Sousa Soares
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 08/04/2013
 * @see LdapUserDao
 * Classe que representa um usuario no AD.
 */

public class LdapUserImpl extends EntranceImpl implements LdapUser{
    private static final String ACCOUNT_DISABLE = "2";
    private static final String USER_CONTROL_NORMAL_USER = "512";
    
    public LdapUserImpl(String userNameString) throws InvalidNameException {
        this.addAttributeValue("objectclass","top");
        this.addAttributeValue("objectclass","person");
        this.addAttributeValue("objectclass","organizationalPerson");
        this.addAttributeValue("objectclass", "user");
        this.addAttribute(new LdapAttribute("sAMAccountName", userNameString));
        this.setId(LdapNameBuilder.newInstance().add("CN",userNameString).build());
    }

    public LdapUserImpl(String userNameString, String password) throws InvalidNameException {
        this(userNameString);
        this.setPassword(password);
    }

    public LdapUserImpl(String userNameString, OrganizationalUnit ou) throws InvalidNameException {
        this(userNameString);
        this.moveTo(ou);
    }
    
    @Override
    public void setUserName(String userNameString) {
        this.setAttributeValue("sAMAccountName", userNameString);
    }
    
    @Override
    public String getUserNameString () {
        String answer = null;
        try {
            answer = (String) this.getUserName().get();        
        } catch (NamingException ex) {
            Logger.getLogger(LdapUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public Attribute getUserName() {
        return this.getAttribute("sAMAccountName");
    }

    @Override
    public void setFirstName(String firstNameString) {
        this.setAttributeValue("givenName", firstNameString);
    }
    
    @Override
    public String getFirstNameString() {
        String answer = null;
        try {
            answer = (String) getFirstName().get();
        } catch (NamingException ex) {
            Logger.getLogger(LdapUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }

    @Override
    public Attribute getFirstName() {
        Attribute answer = null;
        if (this.attributeExists("givenName")) {
            answer = this.getAttribute("givenName");
        }
        return answer;
    }

    @Override
    public void setLastName(String lastNameString) {
        if (!(this.attributeExists("sn"))) {
            this.addAttribute(new LdapAttribute("sn"));
        }
        this.getLastName().clear();
        this.getLastName().add(lastNameString);
    }

    @Override
    public String getLastNameString() {
        String answer = null;
        try {
            answer = (String) getLastName().get();
        } catch (NamingException ex) {
            Logger.getLogger(LdapUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public Attribute getLastName() {
        Attribute answer = null;
        if (this.attributeExists("sn")) {
            answer = this.getAttribute("sn");
        }
        return answer;
    }

    @Override
    public void setDisplayName(String displayNameString) {
        this.setAttributeValue("displayName", displayNameString);
    }

    @Override
    public String getDisplayNameString() {
        String answer = null;
        try {
            answer = (String) getDisplayName().get();
        } catch (NamingException ex) {
            Logger.getLogger(LdapUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public Attribute getDisplayName() {
        Attribute answer = null;
        if (this.attributeExists("displayName")) {
            answer = this.getAttribute("displayName");
        }
        return answer;
    }

    @Override
    public void setPassword(String passwordString) {
        if (!(this.attributeExists("unicodepwd"))) {
            this.addAttribute(new LdapAttribute("unicodepwd"));
        }

        this.getPassword().clear();
        try {
            this.getPassword().add(encodePassword(passwordString));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LdapUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getPasswordString() {
        byte[] passwordBytes=null;
        String answer = null;
        try {
            answer = decodePassword((byte[]) this.getPassword().get());
        } catch (NamingException ex) {
            Logger.getLogger(LdapUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public Attribute getPassword() {
        Attribute answer = null;
        if (this.attributeExists("unicodepwd")) {
            answer = this.getAttribute("unicodepwd");
        }
        return answer;
    }

    @Override
    public void setEmailAddress(String emailAddressString) {
        this.setAttributeValue("userPrincipalName", emailAddressString);
    }    

    @Override
    public String getEmailAddressString() {
        String answer = null;
        try {
            answer = (String) getEmailAddress().get();
        } catch (NamingException ex) {
            Logger.getLogger(LdapUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public Attribute getEmailAddress() {
        Attribute answer = null;
        if (this.attributeExists("userPrincipalName")) {
            answer = this.getAttribute("userPrincipalName");
        }
        return answer;
    }
            
    @Override
    public void setState(String stateString) {
        this.setAttributeValue("st", stateString);
    }

    @Override
    public String getStateString() {
        String answer = null;
        try {
            answer = (String) this.getState().get();
        } catch (NamingException ex) {
            Logger.getLogger(LdapUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }

    @Override
    public Attribute getState() {
        return this.getAttribute("st");
    }

    @Override
    public void setStreet(String streetString) {
        this.setAttributeValue("street", streetString);
    }

    @Override
    public String getStreetString() {
        String answer = null;
        try {
            answer = (String) this.getStreet().get();
        } catch (NamingException ex) {
            Logger.getLogger(LdapUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }

    @Override
    public Attribute getStreet() {
        return this.getAttribute("street");
    }

    /**
     * Retorna o atributo userAccountControl.
     * @return userAccountControl
     * Atributo do Banco de Dados do Active Directory respons&aacute;vel por definir
     * v&aacute;rias propriedades do usu&aacute;rio como enable/disable, etc.
     */
    public Attribute getUserAccountControl() {
        Attribute answer = null;
        if (!(this.attributeExists("userAccountControl"))) {
            this.addAttribute(new LdapAttribute("userAccountControl",USER_CONTROL_NORMAL_USER));
        }
        try {
            answer = this.getAttribute("userAccountControl");
        } catch (Exception ex) {
            Logger.getLogger(LdapUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }

    @Override
    public boolean isEnabled() {
        boolean answer = false;
        try {
            answer = (this.getAttribute("userAccountControl").get().equals(USER_CONTROL_NORMAL_USER));
        } catch (NamingException ex) {
            Logger.getLogger(LdapUserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    
    @Override
    public void enable() {
        this.setAttributeValue("userAccountControl", USER_CONTROL_NORMAL_USER);
    }
    
    @Override
    public void disable() {
        this.setAttributeValue("userAccountControl", ACCOUNT_DISABLE);
    }
            
    @Override
    public String toString() {
        String answer;
        answer = this.getClass().getName() + "{" + this.getAttributes() + "}";
        return answer;
    }

    public void setUserAccountControl(String value) {
        this.getUserAccountControl().clear();
        this.getUserAccountControl().add(value);
    }

//    public void setPhoto(File photo) throws FileNotFoundException, IOException {
//        InputStream inputStream = new FileInputStream(photo);
//        java.io.ByteArrayInputStream
//        inputStream.close();
//        this.setAttribute("photo", photo);
//    }
//    
//    public File getPhoto() throws NamingException {
//        java.io.OutputStream outputStream = (java.io.OutputStream) this.getAttribute("photo").get();
//        outputStream.close();
//        return null;
//    }
    
}