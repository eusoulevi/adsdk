package org.cajuinabits.adsdk.core.exception;

/**
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 */
public class LdapException extends RuntimeException {
    public LdapException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
