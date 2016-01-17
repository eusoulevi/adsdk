package org.cajuinabits.adsdk.core.dao;

import org.cajuinabits.adsdk.core.exception.LdapException;
import org.cajuinabits.adsdk.core.exception.LdapSaveException;
import java.util.List;
import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import org.cajuinabits.adsdk.core.beans.Entrance;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

/**
 * 
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @param <Entrance>
 * @since: 04/04/2013
 * Esta interface cont&eacute;m m&eacute;todos para conex&atilde;o, configura&ccedil;ao e manipula&ccedil;&atilde;o de 
 * objetos de servidores de diret&oacute;rio Active Directory.
 * 
 */
public interface LdapDao  {
    
    /**
     * Cria um objeto no servidor active directory.
     * @param entry Pode ser qualquer objeto que implementa a interface Entrance. <br>
     * Ex.: UserImpl, OrganizationalUnit, GroupImpl.
     * @throws LdapSaveException 
     */
    public void save(Entrance entry) throws LdapSaveException;

    /**
     * Apaga um objeto do servidor active directory
     * @param entry Pode ser qualquer objeto que implementa a interface Entrance. <br>
     * Ex.: UserImpl, OrganizationalUnit, GroupImpl.
     * @throws LdapException
     */
    public void delete (Entrance entry) throws LdapException;
    
    /**
     * Modifica um atributo do objeto (UserImpl, organizationalUnitImpl, etc);
     * @param entry
     * Objeto a ser modificada (UserImpl, OrganizationalUnit, etc).
     * @param attribute
     * Atributo a ser modificado.
     * @param value
     * Novo valor do atributo
     * @throws LdapSaveException  
     */
    public void modify (Entrance entry, String attribute, Object value) throws LdapSaveException;

    public void modify (Entrance entry, Attribute attribute) throws LdapSaveException;
  
    /**
     * Atualiza os atributos alterados de um objeto já salvo no Servidor.
     * @param entry
     * Objeto atualizado (UserImpl, OrganizationalUnit, etc).
     * @throws LdapSaveException
     */    
    public void update(Entrance entry) throws LdapSaveException;
    
    /**
     * @param entrance
     * pode ser qualquer objeto que implementa a interface Entrance. Exemplo: UserImpl, OrganizationalUnit, etc.
     * @return distinguishedName
     * Identificador do objeto. DN &eacute; usado para identificar um
     * objeto de forma n&atilde;o amb&iacute;gua em um servi&ccedil;o de diret&oacute;rio. 
     * Esse atributo &eacute; composto por uma sequ&ecirc;ncia de Relative Distinguished Name (RDN) e 
     * cada RDN corresponde a um ramo na &Aacute;rvore do diret&oacute;rio, desde a raiz at&eacute;
     * o objeto &agrave qual o DN faz refer&eacute;ncia.
     * Um DN &eacute; formado por uma s&eacute;rie de RDNs separados por v&iacute;rgulas. Por exemplo:
     * DN: name=levi,ou=stic,ou=dev,dc=tjpi,dc=jus,dc=br
     * @throws LdapException
     */
    public LdapName getId(Entrance entrance) throws LdapException;
    
    public <Entrance> List<Entrance> find(String name, Class clazz);

    public <Entrance> List<Entrance> find(Attributes attrs, Class clazz);

    public <Entrance> List<Entrance> findAll(Class clazz);
    
    public Entrance findByDn(Name dn);

    public <Entrance> List<Entrance> search(LdapQuery query, AttributesMapper<Entrance> mapper);

    /**
     * Retorna o objeto respons&aacute;vel pelas opera&ccedil;&otilde;es LDAP.
     * @return LdapOperations
     * OBS.: O objeto que implementa a interface LdapOperations cont&eacute;m m&eacute;todos que
     * realizam desde pesquisas at&aacute; manipula&ccedil;&otilde;es nos objetos do Active Directory.
     * Este m&eacute;todo permite extender opera&ccedil;&otilde;es da interface LdapDao.
     */
    public LdapTemplate getLdapOperations();
    
    /**
     * 
     * @param LdapOperations
     */
    public void setLdapOperations(LdapOperations LdapOperations);
            
}