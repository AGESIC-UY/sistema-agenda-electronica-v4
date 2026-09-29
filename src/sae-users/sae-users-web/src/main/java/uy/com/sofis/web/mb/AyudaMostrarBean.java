/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.web.mb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import uy.com.sofis.business.services.AyudaBusinessBean;
import uy.com.sofis.entities.Ayuda;
import uy.com.sofis.filtros.FiltroCodiguera;

@Named
@ViewScoped
public class AyudaMostrarBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(AyudaMostrarBean.class.getName());

    @Inject
    private AyudaBusinessBean lmb;

    private Boolean rendered = Boolean.FALSE;

    public AyudaMostrarBean() {
    }
    
        public Ayuda getAyuda(String codigo) {
        try {
            FiltroCodiguera filtro = new FiltroCodiguera();
            filtro.setCodigoExacto(codigo);
            filtro.setHabilitado(Boolean.TRUE);
            List<Ayuda> ayudas = lmb.obtenerPorFiltroCache(filtro);
            if (!ayudas.isEmpty()) {
                return ayudas.get(0);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public void showSidebar() {
        this.rendered = Boolean.TRUE;
    }

    public Boolean getRendered() {
        return rendered;
    }

}
