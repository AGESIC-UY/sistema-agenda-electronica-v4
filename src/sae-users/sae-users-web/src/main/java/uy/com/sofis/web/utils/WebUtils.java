package uy.com.sofis.web.utils;

import javax.faces.context.FacesContext;

public class WebUtils {

    public static void keepMessages() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }
}
