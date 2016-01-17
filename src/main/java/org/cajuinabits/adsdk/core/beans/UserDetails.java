package org.cajuinabits.adsdk.core.beans;

/**
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 29/01/2013
 * @see UserImpl
 */
public interface UserDetails {
    /**
     * Indica se o usu&aacute;rio est&aacute; ativado ou desativado.
     * Um usu&aacute;rio desabilitado n&atilde;o pode ser autenticado.
     * @return <code>true</code> se o usu&aacute;rio est&aacute; habilitado, <code>false</code> 
     * caso contr&aacute;rio
     */
    boolean isEnabled();

    /**
     * Indica se a conta do usu&aacute;rio  pode expirar.
     * @return <code>true</code> se a conta do usu&aacute;rio nunca expira,
     *         <code>false</code> se pode expirar.
     */
    boolean accountNeverExpire();

    /**
     * 
     * Indica se a conta do usu&aacute;rio expirou.
     * Um usu&aacute;rio com conta expirada n&atilde;o pode se autenticar no servidor. 
     * 
     * @return <code>true</code> se a conta do usu&aacute;rio &eacute; v&aacute;lida
     * (ou seja, que n&atilde;o esteja expirada), <code>false</code> 
     * se n&atilde;o &eacute; mais v&aacute;lido (est&aacute; expirada)
     */
    boolean isAccountNonExpired();

//    /**
//     * Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
//     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
//     */
//    boolean isAccountNonLocked();

    /**
     * Indica se as credenciais do usu&aacute;rio (senha) devem ser alteradas no primeiro logon.
     * @return <code>true</code> se as credenciais do usu&aacute;rio deve ser alterada, e 
     * <code>false</code> se n&atilde;o precisa de ser alterada.
     */
    boolean credentialsHasToBeChangedAtFirst();


    /**
     * Indica se as credenciais do usu&aacute;rio (senha) pode expirar.
     * @return <code>true</code> se nunca as credenciais do usu&aacute;rio nunca podem expirar,
     *         <code>false</code> se pode expirar.
     */
    boolean credentialsNeverExpire();

    /**
     * Indica se as credenciais do usu&aacute;rio (senha) expiraram.<br>
     * Credencial expirada impedi a autentica&ccedil;&atilde;o.
     * @return <code>true</code> se as credenciais do usu&aacute;rio s&atilde;o 
     * v&aacute;lidos (ou seja, que n&atilde;o esteja expirada)
     *         <code>false</code> se n&atilde;o for mais v&aacute;lido (expirou).
     */
    boolean isCredentialsNonExpired();
}
