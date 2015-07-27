/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.AventuraCrudHibernate;
import dao.FalaCrudHibernate;
import dao.PersonagemCrudHibernate;
import dao.entity.Aventura;
import dao.entity.Fala;
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
@ManagedBean(name = "mestreAventuraBean")
@ViewScoped
public class MestreAventuraBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private List<Aventura> lista;
    private Aventura aventura;

    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");

        inicializaAventura();
        inicializaListaAventuras();
    }

    private void inicializaAventura() {

        this.aventura = (Aventura) SessaoUtil.getElementSession("aventura");

        if (aventura == null) {
            this.aventura = new Aventura();
            this.aventura.setMestre(this.usuario);
        } else {
            SessaoUtil.removeElementSession("aventura");
        }

    }

    private void inicializaListaAventuras() {
        AventuraCrudHibernate crud = new AventuraCrudHibernate();
        this.lista = crud.listarPorUsuario(this.usuario.getCodigo());
    }

    public String novaAventura() {
        return "cadastrarAventuraMestre";
    }

    public String editarAventura() {
        SessaoUtil.setElementSession("aventura", this.aventura);
        return "editarAventuraMestre";
    }

    public String mestrarAventura() {
        SessaoUtil.setElementSession("aventura", this.aventura);
        return "mestrarAventura";
    }

    public String voltarPaginaIndex() {
        return "inicio";
    }

    public String voltarPaginaListarAventuras() {
        return "listarAventuraMestre";
    }

    public List<Aventura> getLista() {
        return lista;
    }

    public void setLista(List<Aventura> lista) {
        this.lista = lista;
    }

    public Aventura getAventura() {
        return this.aventura;
    }

    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
    }

    public String salvar() {
        AventuraCrudHibernate crud = new AventuraCrudHibernate();

        crud.salvar(this.aventura);
        lista.add(aventura);
        return this.voltarPaginaListarAventuras();
    }

    public String editar() {

        try {
            AventuraCrudHibernate crud = new AventuraCrudHibernate();
            crud.atualizar(this.aventura);
            
            this.lista = crud.listar();
            
            return this.voltarPaginaListarAventuras();
        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Não foi possivel salvar as alterações.", "Não foi possivel salvar as alterações.");
        } catch (Throwable t) {
            MessageUtil.ErrorMessage(null, "Não foi possivel salvar as alterações.", "Não foi possivel salvar as alterações.");
        }
        return null;

    }

    public String excluir() {
        try {
            
            PersonagemCrudHibernate personagemCrud = new PersonagemCrudHibernate();
            List<Personagem> personagens = personagemCrud.listarPorAventura(this.aventura.getCodigo());
            
            for(Personagem personagem : personagens){
                personagemCrud.excluir(personagem);
            }
            
            FalaCrudHibernate falaCrud = new FalaCrudHibernate();
            List<Fala> falas = falaCrud.listarPorAventura(this.aventura.getCodigo());
            for(Fala fala : falas ){
                falaCrud.excluir(fala);
            }
            
            AventuraCrudHibernate aventuraCrud = new AventuraCrudHibernate();
            aventuraCrud.excluir(this.aventura);
            this.aventura = null;

            this.lista = aventuraCrud.listar();

            return this.voltarPaginaListarAventuras();
        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Não foi possivel fazer as alterações.", "Não foi possivel fazer as alterações.");
        }
        return null;
    }

}
