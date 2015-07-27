/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.FichaCrudHibernate;
import dao.PersonagemCrudHibernate;
import dao.entity.Ficha;
import dao.entity.Personagem;
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
@ManagedBean(name = "fichaJogadorBean")
@ViewScoped
public class FichaJogadorBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private Ficha ficha;
    private Personagem personagem;
    private boolean editavel;
    
    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");

        this.personagem = (Personagem) SessaoUtil.getElementSession("personagem");
        this.ficha = personagem.getFicha();
        
        this.editavel = false;
        
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    
    public boolean isEditavel() {
        return editavel;
    }

    public void setEditavel(boolean editavel) {
        this.editavel = editavel;
    }
    
    
  
    
    public String voltarPaginaJogo(){
        return "jogarAventura";
    }
    
    public String editarFicha(){
        return "editarFichaJogador";
    }
    
    public String savarFicha(){
        
       FichaCrudHibernate crud = new FichaCrudHibernate();
       
       crud.atualizar(ficha);
       
       personagem.setFicha(ficha);
       
       SessaoUtil.setElementSession("personagem", personagem);
       
        return "consultarFichaJogador";
    }
    
}
