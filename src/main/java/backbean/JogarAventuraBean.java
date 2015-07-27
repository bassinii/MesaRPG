/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.FalaCrudHibernate;
import dao.PersonagemCrudHibernate;
import dao.entity.Aventura;
import dao.entity.Fala;
import dao.entity.Ficha;
import dao.entity.Personagem;
import dao.entity.Usuario;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.PostLoad;
import util.MessageUtil;
import util.SessaoUtil;

/**
 *
 * @author Bassini
 */
@ManagedBean(name = "jogarAventuraBean")
@ViewScoped
public class JogarAventuraBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private List<Personagem> personagens;
    private Personagem personagem;
    private List<Fala> falas;
    private List<Fala> falasExibidas;
    private Aventura aventura;
    private Ficha ficha;
    private Fala fala;
    private int quantidadeFalasExibidas;

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    
    
    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");

        this.fala = new Fala();

        inicializarAventura();
        inicializarPersonagens();
        inicializarFalas();
        inicializarQuantidadeFalasExibidas();
        inicializarFalasExibidas();
    }

    private void inicializarAventura() {
        this.personagem = (Personagem) SessaoUtil.getElementSession("personagem");
        if (personagem == null) {
            SessaoUtil.redirectTo("aventuraJogadorListar.xhtml");
        }
        
        this.aventura = personagem.getAventura();
        this.ficha = personagem.getFicha();
        
        if(this.ficha==null){
            ficha = new Ficha();
        }
    }

    private void inicializarPersonagens() {
        PersonagemCrudHibernate crud = new PersonagemCrudHibernate();
        this.personagens = crud.listarPorAventuraComFicha(this.aventura.getCodigo());
    }

    private void inicializarFalas() {
        FalaCrudHibernate crud = new FalaCrudHibernate();
        this.falas = crud.listarPorAventura(this.aventura.getCodigo());
    }

    @PreDestroy
    public void finalizar() {
        SessaoUtil.removeElementSession("quantidedeFalasExibidas");
    }

    private void inicializarFalasExibidas() {

        if (this.quantidadeFalasExibidas < this.falas.size()) {
            this.falasExibidas = new ArrayList<Fala>();

            int indice = this.falas.size() - this.quantidadeFalasExibidas;
            for (int i = indice; i < falas.size(); i++) {
                //int indice = this.fala.length() - (this.quantidadeFalasExibidas-i);
                this.falasExibidas.add(this.falas.get(i));
            }
        } else {
            this.falasExibidas = this.falas;
        }
    }

    private void inicializarQuantidadeFalasExibidas() {
        Integer quantidade = (Integer) SessaoUtil.getElementSession("quantidedeFalasExibidas");
        if (quantidade == null) {
            this.quantidadeFalasExibidas = 5;
        } else {
            this.quantidadeFalasExibidas = quantidade.intValue();
            SessaoUtil.removeElementSession("quantidedeFalasExibidas");
        }
    }
    
    public String rolarDado() {

        Random rand = new Random();
        int resultado = rand.nextInt(6) + 1;

        String dado = "d6-" + resultado;
        
        String fala = this.fala.getFala();
        fala = "Rolando dado... Resultado: "+resultado;
        this.fala.setFala(fala);
        this.fala.setDado(dado);

        return salvarFala();
    }
    
    public String salvarFala() {

        try {

            if (this.fala.getFala() != "") {
                //Fala fala = new Fala();

                String autor;
                
                if(personagem.getFicha()==null){
                    autor = this.usuario.getNome();
                }
                else{
                    autor = personagem.getFicha().getNome();
                }
                    
                fala.setAutor(autor);
                fala.setAventura(this.aventura);
                fala.setDataHora(new Date(System.currentTimeMillis()));

                FalaCrudHibernate crud = new FalaCrudHibernate();
                crud.salvar(fala);
                this.falas.add(fala);

                this.inicializarFalasExibidas();

                this.fala = new Fala();
            } else {
                MessageUtil.ErrorMessage(null, "Não é possivel enviar uma mensagem em branco.", "Não é possivel enviar uma mensagem em branco.");
            }

        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Nao foi possivel enviar a Fala!", "Nao foi possivel enviar a Fala!");
        }

        return null;
    }

    public List<Personagem> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<Personagem> personagens) {
        this.personagens = personagens;
    }

    public List<Fala> getFalas() {
        return falas;
    }

    public void setFalas(List<Fala> falas) {
        this.falas = falas;
    }

    public Aventura getAventura() {
        return aventura;
    }

    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
    }

    public Fala getFala() {
        return fala;
    }

    public void setFala(Fala fala) {
        this.fala = fala;
    }

    public String mostrarMaisFalas() {

        this.quantidadeFalasExibidas += 5;
        SessaoUtil.setElementSession("quantidedeFalasExibidas", new Integer(quantidadeFalasExibidas));
        this.inicializarFalasExibidas();
        return null;

    }

    public List<Fala> getFalasExibidas() {
        return falasExibidas;
    }

    public void setFalasExibidas(List<Fala> falasExibidas) {
        this.falasExibidas = falasExibidas;
    }

    public String verFicha(Personagem personagem) {
        if (personagem.getFicha() != null) {
            SessaoUtil.setElementSession("ficha", personagem.getFicha());
            SessaoUtil.setElementSession("paginaRetorno", new String("jogarAventura"));
            return "consultarFichaSimples";
        }
        return null;
    }
    
    
    
    public String minhaFicha(){
        
        if(this.personagem.getFicha() == null){
            return "selecionarFicha";
        }
        else{
            return "consultarFichaJogador";
        }
    }
    
    public String nomeBotaoMinhaFicha(){
        
        if(this.personagem.getFicha()==null){
            return "Selecionar Ficha";
        }
        else{
            return "Detalhes";
        }
        
    }
    
    public boolean exibirFicha(){
        if(this.personagem.getFicha()==null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public String imagemDado(String dado) {
        return "/imagens/" + dado + ".png";
    }

    public boolean exibirImagemDado(String dado) {

        return !dado.equals("");
    }

}
