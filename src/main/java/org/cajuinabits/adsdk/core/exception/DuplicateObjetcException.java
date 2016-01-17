package org.cajuinabits.adsdk.core.exception;

/**
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 * Exce&ccedil;&atilde;o lan&ccedil;ada quando se tenta adicionar um usu&aacute;rio j&aacute; cadastrado
 * no Servidor Active Directory.
 */
public class DuplicateObjetcException extends LdapException {

    public DuplicateObjetcException(String message,Throwable throwable) {
        super(message,throwable);
    }
}
