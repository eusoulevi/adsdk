package org.cajuinabits.adsdk.core.beans;

/**
 *
 * @author levi
 */
public interface Removable {
     /**
     * Move o objeto para dentro de uma unidade organizacional.
     * @param destiny Unidade Organizacional de destino.
     */ 
    public void moveTo(OrganizationalUnit destiny);
}
