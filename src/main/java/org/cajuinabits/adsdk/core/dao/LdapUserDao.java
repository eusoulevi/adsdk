package org.cajuinabits.adsdk.core.dao;

import org.cajuinabits.adsdk.core.beans.LdapUser;
import org.cajuinabits.adsdk.core.beans.OrganizationalUnit;
import org.cajuinabits.adsdk.core.exception.LdapException;
import java.util.List;
import org.cajuinabits.adsdk.core.beans.LdapUserImpl;
import org.cajuinabits.adsdk.core.exception.LdapSaveException;

/**
 * 
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/04/2013
 * Esta interface cont&eacute;m m&eacute;todos para conex&atilde;o, configura&ccedil;ao e manipula&ccedil;&atilde;o de 
 * objetos de servidores Active Directory.
 * @see UserDaoImpl
 * @see DaoImpl
 */
public interface LdapUserDao {
    
    public void save(LdapUser user);
    public void update(LdapUser user);
    public void delete(LdapUser user);
    public void modify (LdapUser user, String attribute, Object value) throws LdapSaveException;
    /**
     * Retorna um lista de todos os usu&aacute;rios do servidor active directory.
     * @throws Exception 
     */
    public List<LdapUser> getAllUsers(OrganizationalUnit ou) throws Exception;

    /**
     * Verifica ser um determinado usu&aacute;rio existe no servidor Active Directory.
     * @param loginString
     * Nome utilizado para efetuar logon do usu&aacute;rio.
     * @return <code>true</code> caso o usu&aacute;rio exista, e 
     * <code>false</code> caso contr&aacute;rio.
     * @throws LdapException 
     */
    public boolean userExists(String loginString) throws LdapException;

    /**
     * Autentica o usu&aacute;rio no Active Directory.<br>
     * OBS.:Opera&ccedil;&atilde;o poss&iacute;vel apenas se a senha e a conta do usu&aacute;rio estive habilitada
     * @return <code>true</code> se o login for efetuado com sucesso, 
     * <code>false</code> caso contr&aacute;rio
     * @throws LdapException  
     */        
    public boolean login(String userName, String password) throws LdapException;

    /**
     * Habilita o usu&aacute;rio para auteticar-se no Active Directory
     * @throws Exception 
     */
    public void enableUser(String userName) throws Exception;

    /**
     * Desabilita o usu&aacute;rio de auteticar-se no Active Directory
     */    
    public void disableUser(String userName);

    /**
     * Modifica a senha de autentica&ccedil;&atilde;o do usu&aacute;rio.
     */
    public void changePassword(String userName, String password) throws Exception;
        
    /**
     * Adiciona um usu&aacute;rio no grupo especificado
     */
    public void addUserToGroup(String userName, String group) throws Exception;
    
    /**
     * Retorna o usu&aacute;rio do SERVIDOR Active Directory.
     */
    public LdapUserImpl getUser(String userName) throws Exception;
    
}
