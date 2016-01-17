package org.cajuinabits.adsdk.core.beans;

import org.cajuinabits.adsdk.core.exception.LdapException;
import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;

/**
 * Interface de manipula&ccedil;&atilde;o de atributos de entradas do 
 * Servidor LDAP Active Directory.
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 04/09/2012
 * @see UserImpl, OrganizationalUnit
 */
public interface Entrance {
    
    public Attribute getObjectclass();
    
    public LdapName getId();
                
    /**
     * Altera o nome (name) do objeto.
     * @param ldapName
     */
    public void setId(String ldapName);
    
    public void setId(Name ldapName);
    
    public void setName(String name);

    public Attribute getName();
        
    /**
     * Retorna a descri&ccedil;&atilde;o (description) do objeto na forma de Attribute..
     * @return description
     * Descri&ccedil;&atilde;o do objeto
     */
    public Attribute getDescription();

    /**
     * Atribui/altera o campo descri&ccedil;&atilde;o (description) do objeto.
     * @param description
     * Descri&ccedil;&atilde;o do objeto
     */
    public void setDescription(String description);
    
    /**
     * Retorna os Grupos do(a) qual(is) o objeto &eacute; membro.
     * @return memberOf
     * Grupo(s) ao(s) qual(is) o objeto pertence.
     * <br>OBS.: O objeto pode ser membro de uma ou mais grupos.
     */
    public Attribute getMemberOf();
        
    /**
     * Atribui/altera o campo MemberOf (description) do objeto.
	 * @param dnMemberOf
     * Grupo(s) ao(s) qual(is) o objeto pertence.
     * <br>OBS.: O objeto pode ser membro de uma mais grupos.
     */
    public void addMemberOf(Name dnMemberOf);
            
    /**
     * Atribui / Modifica um conjunto de atributos do objeto.
     * @param attributes
     * Cole&ccedil;&atilde;o de todos os atributos do objeto.
     */
    public void setAttributes(Attributes attributes);    
            
    /**
     * Adiciona um atributo ao objeto. Se o atributo j&aacute; existe, uma exce&ccedil;&atilde;o
     * &eacute; lan&ccedil;ada
     * @param additionalAttribute
     * @throws IllegalArgumentException
     */
    public void addAttribute(Attribute additionalAttribute) throws IllegalArgumentException;

    /**
     * Adiciona um atributo ao objeto. Se o atributo j&aacute; existe, uma exce&ccedil;&atilde;o &eacute; lan&ccedil;ada.
     * @param id
     * Identifica&ccedil;&atilde;o do atributo.
     * @param value
     * Valor do atributo.
     * @throws IllegalArgumentException
     */    
    public void addAttribute(String id, Object value) throws IllegalArgumentException;
    
    /**
     * Retorna um atributo. Caso o atributo n&atilde;o exista, 
     * uma exce&ccedil;&atilde;o &eacute; lan&ccedil;ada.
     * @param idAttribute
     * Identifica&ccedil;&atilde;o do atributo.
     * @return 
     * @throws IllegalArgumentException
     */
    public Attribute getAttribute(String idAttribute) throws IllegalArgumentException;
    
    /**
     * Remove do objeto o atributo especificado pelo id. Caso o atributo n&atilde;o exista, 
     * uma exce&ccedil;&atilde;o &eacute; lan&ccedil;ada.
     * @param id
     * Identifica&ccedil;&atilde;o do atributo.
	 * @return 
     * @throws LdapException
     */
    public Attribute removeAttribute(String id) throws LdapException;
    
    public Attributes getAttributes();
    
}