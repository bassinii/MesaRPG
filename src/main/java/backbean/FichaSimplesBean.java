/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.FichaCrudHibernate;
import dao.entity.Ficha;
import dao.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import util.MessageUtil;
import util.SessaoUtil;

/**
 *
 * @author Bassini
 */
@ManagedBean(name = "fichaSimplesBean")
@ViewScoped
public class FichaSimplesBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private Ficha ficha;
    private String paginaRetorno;
    
    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");
        
        this.paginaRetorno = (String)SessaoUtil.getElementSession("paginaRetorno");
        this.ficha = (Ficha) SessaoUtil.getElementSession("ficha");
    }

    
    
    public String voltarPaginaJogo(){
        return paginaRetorno;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
}
