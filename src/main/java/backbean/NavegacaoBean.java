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
 * @author Tiago
 */

@ManagedBean(name = "navegacaoBean")
@ViewScoped
public class NavegacaoBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;
    
    private Usuario usuario;
    
    
    public String inicio(){
        return "inicio";
    }
    
    public String jogar(){
        return "listarAventuraJogador";
    }
    
    public String mestrar(){
        return "listarAventuraMestre";
    }
    public String minhasFichas(){
        return "listarFichas";
    }
    public String meusDados(){
        return "consultarUsuario";
    }
    
    public String desconectar(){
        SessaoUtil.removeElementSession("usuarioLogado");
        return "login";
    }
}
