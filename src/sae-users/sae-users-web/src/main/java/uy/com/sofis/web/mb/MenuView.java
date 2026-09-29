package uy.com.sofis.web.mb;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import uy.com.sofis.constantes.ConstantesPaginas;
import uy.com.sofis.web.presentacion.mensajes.Etiquetas;
import uy.com.sofis.web.seguridad.SessionMBean;

/**
 *
 * @author Usuario
 */
@Named
@SessionScoped
public class MenuView implements Serializable {

	private Locale locale;
	private MenuModel model;

	@Inject
	private SessionMBean sessionBean;

	@Inject
	private InicioManagedBean inicioBean;

	public MenuView() {
	}

	@PostConstruct
	public void init() {
		try {

			locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			model = new DefaultMenuModel();

			// Inicio
			DefaultMenuItem item0 = new DefaultMenuItem(Etiquetas.getValue("menuInicio", locale), null,
					ConstantesPaginas.INICIO);

			DefaultMenuItem item1 = new DefaultMenuItem(Etiquetas.getValue("gestionusuario", locale), null,
					ConstantesPaginas.USUARIO);

			model.addElement(item0);

			if (sessionBean.tieneRol("RA_AE_ADMINISTRADOR")) {
				model.addElement(item1);
			}

			// Manual
//            DefaultMenuItem item4 = new DefaultMenuItem(Etiquetas.getValue("menuManual", locale));
//            model.addElement(item4);
			model.generateUniqueIds();

		} catch (Exception ex) {

		}
	}

	public MenuModel getModel() {
		return model;
	}

}
