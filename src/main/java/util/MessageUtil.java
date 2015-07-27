/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bassini
 */
public class MessageUtil {

    public static void ErrorMessage(String campo, String titulo ,String detalhes) {
        
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, detalhes);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(campo, facesMessage);
    }


}
