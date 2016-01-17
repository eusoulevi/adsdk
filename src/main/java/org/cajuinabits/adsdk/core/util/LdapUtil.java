package org.cajuinabits.adsdk.core.util;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ldap.core.LdapOperations;

/**
 * 
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 * Esta classe cont&eacute;m m&eacute;todos para auxiliar na manipulação de 
 * objetos de servidores de diret&oacute;rio Active Directory.
 * 
 */
public class LdapUtil {
    /**
     * Converte uma String em um array de bytes (Ideal para codificar senhas).
     * @param password String a ser codificada em array de bytes.
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static byte[] encodePassword(String password) throws UnsupportedEncodingException {
        String newQuotedPassword = "\"" + password + "\"";
        return newQuotedPassword.getBytes("UTF-16LE");
    }
    
    /**
     * Decodifica um array de bytes para uma String.
     * @param passwordBytes array de bytes a ser decodificada para String.
     * @return 
     */
    public static String decodePassword (byte[] passwordBytes) {
        String answer = null;
        try {
            answer = new String(passwordBytes, "UTF-16LE");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LdapUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer.replaceAll("\"", "");
    }
    
}