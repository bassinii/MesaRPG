/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.AventuraCrudHibernate;
import dao.PersonagemCrudHibernate;
import dao.entity.Aventura;
import dao.entity.Personagem;
import dao.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import util.SessaoUtil;

/**
 *
 * @author Bassini
 */
@ManagedBean(name = "jogadorAventuraBean")
@ViewScoped
public class JogadorAventuraBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private List<Personagem> personagens;
    private Personagem personagem;

    
    
    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");
        inicializaListaAventuras();
    }

    private void inicializaListaAventuras() {
        PersonagemCrudHibernate crudPersonagem = new PersonagemCrudHibernate();

        this.personagens = crudPersonagem.listarPorUsuario(usuario.getCodigo());

    }

    public String jogarAventura() {
        SessaoUtil.setElementSession("personagem", this.personagem);
        return "jogarAventura";
    }

    public String voltarPaginaIndex() {
        return "inicio";
    }

    public String abandonarAventura() {
        
        PersonagemCrudHibernate crud = new PersonagemCrudHibernate();
        crud.excluir(personagem);
        this.personagens.remove(personagem);
        
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Personagem> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<Personagem> personagens) {
        this.personagens = personagens;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

}
