package org.cajuinabits.adsdk.core.exception;

/**
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 * Exce&ccedil;&atilde;o lan&ccedil;ada quando o password que se tenta atribuir
 * ao usu&aacute;rio tem tamanho inadequaldo.
 */
public class PasswordStrengthException extends LdapException {
    public PasswordStrengthException(String message,Throwable throwable) {
        super(message,throwable);
    }
}
