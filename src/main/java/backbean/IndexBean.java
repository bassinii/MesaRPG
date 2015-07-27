/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.entity.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import util.SessaoUtil;

/**
 *
 * @author Bassini
 */


@ManagedBean(name = "indexBean")
@ViewScoped
public class IndexBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;

    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");

    }
        
    public String gerenciarFichas(){
        return "listarFichas";
    } 
    
    public String editarMeusDados(){
        return "consultarUsuario";
    }
    
    public String jogarAventura(){
        return "listarAventuraJogador";
    }
    public String mestrarAventura(){
        return "listarAventuraMestre";
    }

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    
}
