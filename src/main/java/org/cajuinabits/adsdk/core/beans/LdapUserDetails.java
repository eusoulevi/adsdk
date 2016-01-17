package org.cajuinabits.adsdk.core.beans;

import java.util.Date;

/**
 * Classe que cont&eacute;m informa&ccedil;&otilde;es avan&ccedil;adas de um 
 * usu&aacute;rio do Servidor LDAP Active Directory.
 * @author Levi de Sousa Soares <br> email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 26/04/2013
 * @see UserDao
 * @see DaoImpl.
 */

public class LdapUserDetails implements UserDetails {

    private boolean accountNonExpired;
    private boolean accountNeverExpire;
    private boolean credentialsHasToBeChangedAtFirst;
    private boolean credentialsNeverExpire;
    private boolean credentialsNonExpired;
    private boolean enabled = true;
    private Date accountExpiration;
    private Date credentialsExpiration;
    private int timeBeforeAccountExpiration = Integer.MAX_VALUE;
    private int timeBeforeCredentialsExpiration = Integer.MAX_VALUE;


    /**
     * Verifica se o usu&aacute;rio est&aacute; habilitado.
     */     
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Habilita/Desabilita a conta do usu&aacute;rio..
     * @param enabled quando o valor atribuido for igual a <code>true</code> o usu&aacute;rio ser&aacute; habilitado,
     * e caso seja <code>false</code> o usu&aacute;rio ser&aacute; desabilitado.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Verifica se a conta do usu&aacute;rio nunca expiera. <br>
     * @return <code>true</code> se a conta do usu&aacute;rio expira, 
     * <code>false</code> caso a conta do usu&aacute;rio n&atilde;o expira.
     */
    @Override
    public boolean accountNeverExpire() {
        return accountNeverExpire;
    }

    /**
     * Defini se a conta do usu&aacute;rio est&aacute; nunca expira. <br>
     * @param accountNeverExpire <code>true</code> para ativar (Nunca Expira), 
     * <code>false</code> caso usu&aacute;rio contr&aacute;rio.
     */    
    public void setAccountNeverExpire(boolean accountNeverExpire) {
        this.accountNeverExpire = accountNeverExpire;
    }

    @Override
    public boolean credentialsHasToBeChangedAtFirst() {
        return credentialsHasToBeChangedAtFirst;
    }

    public void setCredentialsHasToBeChangedAtFirst(boolean credentialsHasToBeChangedAtFirst) {
        this.credentialsHasToBeChangedAtFirst = credentialsHasToBeChangedAtFirst;
    }

    /**
     * Verifica se a conta do usu&aacute;rio nunca expira. <br>
     * @return <code>true</code> caso a conta du usu&aacute;rio estiver habilitado para nunca expirar, 
     * <code>false</code> caso usu&aacute;rio.
     */    
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setTimeBeforeAccountExpiration(int timeBeforeAccountExpiration) {
        this.timeBeforeAccountExpiration = timeBeforeAccountExpiration;
    }

    @Override
    public boolean credentialsNeverExpire() {
        return credentialsNeverExpire;
    }

    public void setCredentialsNeverExpire(boolean credentialsNeverExpire) {
        this.credentialsNeverExpire = credentialsNeverExpire;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setTimeBeforeCredentialsExpiration(int timeBeforeCredentialsExpiration) {
        this.timeBeforeCredentialsExpiration = timeBeforeCredentialsExpiration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ADUserDetails");
        sb.append(", enabled=").append(enabled);
        sb.append(", accountNeverExpire=").append(accountNeverExpire);
        sb.append(", accountNonExpired=").append(accountNonExpired);
        sb.append(", accountExpiration=").append(accountExpiration);
        sb.append(", timeBeforeAccountExpiration=").append(timeBeforeAccountExpiration);
        sb.append(", credentialsHasToBeChangedAtFirst=").append(credentialsHasToBeChangedAtFirst);
        sb.append(", credentialsNeverExpire=").append(credentialsNeverExpire);
        sb.append(", credentialsNonExpired=").append(credentialsNonExpired);
        sb.append(", credentialsExpiration=").append(credentialsExpiration);
        sb.append(", timeBeforeCredentialsExpiration=").append(timeBeforeCredentialsExpiration);
        sb.append('}');
        return sb.toString();
    }

    public void setAccountExpiration(Date accountExpiration) {
        this.accountExpiration = accountExpiration;
    }

    public void setCredentialsExpiration(Date credentialsExpiration) {
        this.credentialsExpiration = credentialsExpiration;
    }
}
