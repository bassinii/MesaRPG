/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.UsuarioCrudHibernate;
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

@ManagedBean(name = "novoUsuarioBean")
@ViewScoped
public class NovoUsuarioBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    
    private Usuario novoUsuario;
    
    
    @PostConstruct
    public void iniciar() {
        
        
        this.novoUsuario = new Usuario();
        

    }
    
    public String salvar() {

        try {
            UsuarioCrudHibernate crud = new UsuarioCrudHibernate();
            crud.salvar(novoUsuario);
            this.novoUsuario = null;
            this.novoUsuario = new Usuario();
            
            return "login";
            
        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Não foi possivel cadastrar o novo usuario.", "Não foi possivel cadastrar o novo usuario.");
        } catch (Throwable t) {
            MessageUtil.ErrorMessage(null, "Não foi possivel cadastrar o novo usuario.", "Não foi possivel cadastrar o novo usuario.");
        }
        
        return null;
    }

    public String voltarPaginaLogin() {
        return "login";
    }
    
    

    public String novoUsuario() {
        return "cadastrarUsuario";
    }

    public Usuario getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(Usuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }
    


}
