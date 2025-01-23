/*
 * Sofis Solutions
 */
package uy.com.sofis.entities;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import uy.com.sofis.constantes.Constantes;
/**
 *
 * @author sofis3
 */
@Entity
@Table(name = "lb_menu", schema = Constantes.LB_SCHEMA)
@XmlRootElement
public class Menu {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lb_menu_id")
    private Integer menuId;
    
    @Basic(optional = false)
    @Column(name = "lb_menu_item")
    private String menuItem;    
    
    @Basic(optional = false)
    @Column(name = "lb_ope_cod")
    private String opeCod;      
    
    @Basic(optional = false)
    @Column(name = "lb_menu_habilitado")
    private Boolean menuHabilitado;
    
    @Basic(optional = false)
    @Column(name = "lb_comando")
    private String menuComando; 

    public Menu() {
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getOpeCod() {
        return opeCod;
    }

    public void setOpeCod(String opeCod) {
        this.opeCod = opeCod;
    }

    public Boolean getMenuHabilitado() {
        return menuHabilitado;
    }

    public void setMenuHabilitado(Boolean menuHabilitado) {
        this.menuHabilitado = menuHabilitado;
    }

    public String getMenuComando() {
        return menuComando;
    }

    public void setMenuComando(String menuComando) {
        this.menuComando = menuComando;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.menuId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (!Objects.equals(this.menuId, other.menuId)) {
            return false;
        }
        return true;
    }
    
    
}
