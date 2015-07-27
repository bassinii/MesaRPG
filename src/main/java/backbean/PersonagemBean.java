/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.PersonagemCrudHibernate;
import dao.UsuarioCrudHibernate;
import dao.entity.Aventura;
import dao.entity.Fala;
import dao.entity.Personagem;
import dao.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import util.GravatarUtil;
import util.MessageUtil;
import util.SessaoUtil;

/**
 *
 * @author Bassini
 */
@ManagedBean(name = "personagemBean")
@ViewScoped
public class PersonagemBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private List<Personagem> personagens;
    private Aventura aventura;
    private String nome;
    private Personagem personagem;

    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");

        personagem = new Personagem();

        inicializarAventura();
        inicializarPersonagens();
    }

    private void inicializarAventura() {
        this.aventura = (Aventura) SessaoUtil.getElementSession("aventura");
        if (aventura == null) {
            SessaoUtil.redirectTo("aventuraMestreListar.xhtml");
        }
    }

    private void inicializarPersonagens() {
        PersonagemCrudHibernate crud = new PersonagemCrudHibernate();
        this.personagens = crud.listarPorAventura(this.aventura.getCodigo());
    }

    public String adicionarPersonagem() {

        try {
            UsuarioCrudHibernate usuarioCrud = new UsuarioCrudHibernate();
            Usuario jogador = usuarioCrud.buscaUsuarioPorLogin(nome);

            if (jogador == null) {
                MessageUtil.ErrorMessage(null, "Nome do Jogador invalido!", "Nome do Jogador invalido!");
            } else {
                Personagem personagem = new Personagem();
                personagem.setAventura(aventura);
                personagem.setUsuario(jogador);

                PersonagemCrudHibernate crudPersonagem = new PersonagemCrudHibernate();
                crudPersonagem.salvar(personagem);
                this.personagens.add(personagem);
            }
        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Falha ao cadastrar Jogador!", "Falha ao cadastrar Jogador!");
        }
        this.nome = "";
        return null;
    }

    public String removerPersonagem() {
        try {
            PersonagemCrudHibernate crudPersonagem = new PersonagemCrudHibernate();
            crudPersonagem.excluir(personagem);
            this.personagens = crudPersonagem.listarPorAventura(this.aventura.getCodigo());
            this.personagem = new Personagem();
        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Falha ao cadastar Jogador!", "Falha ao cadastar Jogador!");
        }
        return null;
    }

    public List<Personagem> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<Personagem> personagens) {
        this.personagens = personagens;
    }

    public Aventura getAventura() {
        return aventura;
    }

    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public String voltarPaginaAventura() {
        return "mestrarAventura";
    }
    

}
