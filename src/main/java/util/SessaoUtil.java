/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.entity.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static util.JsfUtil.addErrorMessage;

/**
 *
 * @author Tiago
 */
public class SessaoUtil {

    public static void validarUsuario(Usuario usuario, String redirecionamento) {

        if (usuario == null) {
            System.out.println("Nenhum usuario logado");
            SessaoUtil.logout(redirecionamento);
            //SessaoUtil.redirectTo(redirecionamento);
        }
    }

    public static void logout(String redirecionamento) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.getSession().invalidate();
            //facesContext.getExternalContext().redirect(redirecionamento);
            SessaoUtil.redirectTo(redirecionamento);
        } catch (Exception ex) {
            
        }
    }

    public static Object getElementSession(String key) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
        HttpSession session = (HttpSession) request.getSession();
         Object obj = session.getAttribute(key);
         
         return obj;
    }

    public static void setElementSession(String key, Object obj) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute(key, obj);
    }

    public static void removeElementSession(String key) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static void redirectTo(String url) {
        try {

            url = "/MesaRPG/faces/" + url;
            System.out.println("redirectTo: " + url);
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);

            try {
                addErrorMessage("Pagina nao Encontrada");
                FacesContext.getCurrentInstance().getExternalContext().redirect("/MesaRPG/faces/404.xhtml");

            } catch (IOException ex1) {
                Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }
    
    

}
