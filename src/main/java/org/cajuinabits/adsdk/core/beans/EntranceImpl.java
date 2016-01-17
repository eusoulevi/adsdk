package org.cajuinabits.adsdk.core.beans;

import org.cajuinabits.adsdk.core.exception.LdapException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import org.springframework.ldap.NoSuchAttributeException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapAttribute;
import org.springframework.ldap.core.LdapAttributes;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;

/**
 * Classe abstrata de manipula&ccedil;&atilde;o de atributos de objetos do 
 * Servidor LDAP Active Directory.
 * @author Levi de Sousa Soares <br>
 * email: eusoulevi@gmail.com
 * @version 1.0
 * @since: 05/09/2012
 * @see UserImpl
 * @see OrganizationalUnit
 * @see GroupImpl
 */
public class EntranceImpl implements Entrance, Removable {
    private DirContextAdapter dirContextAdapter;
    private LdapAttributes attributes;

    public EntranceImpl() {
        this.attributes = new LdapAttributes();
    }

    @Override
    public LdapName getId() {
        return this.attributes.getName();
    }

    @Override
    public void setId(Name name) {
        this.attributes.setName(name);
    }

    @Override
    public void setId(String nameString) {
        LdapName id = LdapNameBuilder.newInstance().add(nameString).build();
        setId(id);
    }

    public void setName(String name) {
        this.setAttributeValue("name", name);
    }

    public Attribute getName() {
        return this.getAttribute("name");
    }
    
    public String getNameString() throws NamingException {
        String answer = (String) this.getAttribute("name").get();
        return answer;
    }
    
    @Override
    public Attribute getDescription() {
        return this.getAttribute("description");
    }
    
    public String getDescriptionString() throws LdapException, NamingException {
        return (String) getDescription().get();
    }

    @Override
    public void setDescription(String desc) {
            this.setAttributeValue("description", desc);
    }
    
    @Override
    public Attribute getMemberOf() {
            return this.getAttribute("memberOf");
    }

/**
 * M&eacute;todo que lista as Unidades Organizacionais (OUs) (Campo: MemberOf) em que o 
 * usu&aacute;rio est&atilde; inserido no Banco de Dados do AD.
 * @return 
 */
    
    public String[] getMemberOfString() {        
        Object [] objectAttributes = this.getObjectAttributes("memberOf");
        String [] answer = new String[objectAttributes.length];
        for (int i = 0; i <= objectAttributes.length - 1; i++) {
            Name member = (Name) objectAttributes[i];
            answer[i] = member.toString();
        }
        return answer;
    }

    @Override
    public void setAttributes(Attributes attr) {
        attributes = (LdapAttributes) attr;
    }

    public boolean attributeExists(String idAtribute) {
        return this.attributes.get(idAtribute) != null;
    }
    
    public void setAttributeValue(String idAtribute, Object value) throws IllegalArgumentException {
        if (!attributeExists(idAtribute)) {
            addAttribute(idAtribute, value);
        } else {
            this.attributes.get(idAtribute).clear();
            this.attributes.put(idAtribute, value);
        }
    }
    
    @Override
    public void addAttribute(Attribute additionalAttribute) throws IllegalArgumentException {
        if ((this.attributeExists(additionalAttribute.getID()))) {
            throw new IllegalArgumentException("Atributo já existe");
        } else {
            this.attributes.put(additionalAttribute);
        }
    }
    
    @Override
    public void addAttribute(String id, Object value) throws IllegalArgumentException {
        if (!(this.attributeExists(id))) {
            this.attributes.put(id, value);
        } else {
            throw new IllegalArgumentException("Atributo já existe");
        }
    }
    
    @Override
    public Attribute getAttribute(String idAttribute) throws IllegalArgumentException {
        Attribute answer = null;
        if (!(this.attributeExists(idAttribute))) {
            try {
                throw new IllegalArgumentException("Atributo não existe");
            } catch (Exception ex) {
                Logger.getLogger(EntranceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
                answer = this.attributes.get(idAttribute);
        }
        return answer;
    }
    
    @Override
    public Attribute removeAttribute(String id) throws LdapException {
        Attribute answer = null;
        if (!(this.attributeExists(id))) {
            try {
                throw new IllegalArgumentException("Atributo não existe");
            } catch (Exception ex) {
                Logger.getLogger(EntranceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.attributes.remove(id);
        }
        return answer;
    }

    @Override
    public void moveTo(OrganizationalUnit destiny) {
        try {
            normalizar(destiny.getId());
        } catch (InvalidNameException ex) {
            Logger.getLogger(EntranceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Name dnEntrace = this.getId();
        Name dnDestiny = destiny.getId();
        try {
            this.setId(dnEntrace.addAll(dnDestiny));
        } catch (InvalidNameException ex) {
            Logger.getLogger(EntranceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void normalizar(Name destiny) throws InvalidNameException {
        for (int i=0; i <= destiny.size()-1; i++) {
            LdapName dnLocal = (LdapName) this.getId();
            LdapName dnDestiny = (LdapName) destiny;
            if (dnLocal.getRdn(0).equals(dnDestiny.getRdn(i))) {
                this.getId().remove(i);
            }
        }
    }
    

    @Override
    public Attribute getObjectclass() {
      return this.getAttribute("objectclass");
    }    
  
    @Override
    public void addMemberOf(Name dnMemberOf) {
        this.addAttributeValue("memberOf", dnMemberOf);
    }

    public boolean isMemberOf(Name groupDN) throws NamingException {
        boolean answer = false;
        Attribute membersOf = this.getMemberOf();
		if (membersOf != null) {
            NamingEnumeration<?> valuesMembers = membersOf.getAll();
            while (valuesMembers.hasMoreElements()) {
                Name memberofDN = (Name) valuesMembers.nextElement();
                if (memberofDN.equals(groupDN)) {
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }
    
    public String getStringAttribute(String string) {
        return (String) getObjectAttribute(string);
    }

	public Object getObjectAttribute(String name) {
		Attribute oneAttr = attributes.get(name);
		if (oneAttr == null || oneAttr.size() == 0) { // LDAP-215
			return null;
		}
		try {
			return oneAttr.get();
		}
		catch (NamingException e) {
			throw LdapUtils.convertLdapException(e);
		}
    }


    public void addAttributeValue(String string, Object o) {
        Attribute get = this.attributes.get(string);
        if (get==null) {
            this.attributes.put(string, o);
        } else {
            get.add(o);
        }
    }

    public void removeAttributeValue(String id, Object o) {
        this.attributes.get(id).remove(o);
    }


    public Object[] getObjectAttributes(String name) {
		try {
            return collectAttributeValuesAsList(name).toArray(new Object[0]);
		}
		catch (NoSuchAttributeException e) {
			// O atributo não existe - retorna null.
			return null;
		}
    }
    
	private List collectAttributeValuesAsList(String name) {
		List list = new LinkedList();
		LdapUtils.collectAttributeValues(attributes, name, list);
		return list;
	}    

    @Override
    public Attributes getAttributes() {
        return this.attributes;
    }

    public void setAttributeValues(String name, Object[] values) {
        Attribute a = new LdapAttribute(name);
		for (int i = 0; values != null && i < values.length; i++) {
			a.add(values[i]);
		}
        this.attributes.put(a);
    }
        
    @Override
    public String toString() {
        String answer;
        answer = this.getClass().getName() + "{" + this.getAttributes() + "}";
        return answer;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 5 * hash + (this.attributes != null ? this.attributes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntranceImpl other = (EntranceImpl) obj;
        if (this.attributes != other.attributes && (this.attributes == null || !this.attributes.equals(other.attributes))) {
            return false;
        }
        return true;
    }
    
}
