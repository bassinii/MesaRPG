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
import util.SessaoUtil;

/**
 *
 * @author Bassini
 */
@ManagedBean(name = "selecionarFichaBean")
@ViewScoped
public class SelecionarFichaBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private Personagem personagem;
    private List<Ficha> lista;
    private Ficha ficha;

    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");
        
        this.personagem = (Personagem) SessaoUtil.getElementSession("personagem");
        
        inicializaListaFichas();
    }

        
    private void inicializaListaFichas(){
        FichaCrudHibernate crud = new FichaCrudHibernate();
        this.lista = crud.listarPorUsuario(this.usuario.getCodigo());
    }
        
    
    

    public List<Ficha> getLista() {
        return lista;
    }

    public void setLista(List<Ficha> lista) {
        this.lista = lista;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    public String selecionar(){
        
        PersonagemCrudHibernate crud = new PersonagemCrudHibernate();
        personagem.setFicha(ficha);
        crud.atualizar(personagem);
        
        SessaoUtil.setElementSession("personagem",personagem);
        
        return this.voltarPaginaJogo();
    }

    
    public String voltarPaginaJogo(){
        return "jogarAventura";
    }
}
