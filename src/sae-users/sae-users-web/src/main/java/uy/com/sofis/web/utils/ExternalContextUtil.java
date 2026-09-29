/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.sofis.web.utils;

import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofis
 */
public abstract class ExternalContextUtil implements Serializable {

    public static HttpSession getHttpSession() {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext
                .getRequest();
        return request.getSession();
    }

    public static HttpServletRequest getHttpServletRequest() {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext
                .getRequest();
        return request;
    }

}
