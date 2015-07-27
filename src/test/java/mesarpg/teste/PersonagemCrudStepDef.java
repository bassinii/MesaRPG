/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesarpg.teste;

import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import dao.PersonagemCrudHibernate;
import dao.entity.Aventura;
import dao.entity.Ficha;
import dao.entity.Personagem;
import dao.entity.Usuario;
import org.mockito.Mockito;

/**
 *
 * @author Tiago
 */
public class PersonagemCrudStepDef {

    int codigo;
    private Personagem personagem;
    private PersonagemCrudHibernate crud;

    @Dado("^que eu possuo um Persoangem$")
    public void queEuPossuoUmPersoangem() throws Throwable {
        personagem = new Personagem();
    }

    @Quando("^eu recebo as informacoes do personagem: (\\d+), (\\d+), (\\d+), (\\d+)$")
    public void euReceboAsInformacoesDoPersonagem(int arg1, int arg2, int arg3, int arg4) throws Throwable {

        Usuario user = new Usuario();
        user.setCodigo(arg2);

        Ficha ficha = new Ficha();
        ficha.setCodigo(arg3);

        Aventura aventura = new Aventura();
        aventura.setCodigo(arg4);

        personagem.setCodigo(arg1);
        personagem.setUsuario(user);
        personagem.setAventura(aventura);
        personagem.setFicha(ficha);
    }

    @Entao("^eu salvo o personagem$")
    public void euSalvoOPersonagem() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);

        crud.salvar(personagem);

        Mockito.verify(crud).salvar(personagem);
    }

    @Entao("^eu excluo o personagem$")
    public void euExcluoOPersonagem() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);

        crud.excluir(personagem);

        Mockito.verify(crud).excluir(personagem);
    }

    @Entao("^eu atualizo o personagem$")
    public void euAtualizoOPersonagem() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);

        crud.atualizar(personagem);

        Mockito.verify(crud).atualizar(personagem);
    }

    @Entao("^eu busco o personagem$")
    public void euBuscoOPersonagem() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);

        crud.buscaPersonagem(personagem.getCodigo());

        Mockito.verify(crud).buscaPersonagem(personagem.getCodigo());
    }

    @Dado("^quero listar os personagens$")
    public void queroListarOsPersonagens() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);
    }

    @Entao("^eu listo todos os personagens cadastrados$")
    public void euListoTodosOsPersonagensCadastrados() throws Throwable {
        crud.listar();
        Mockito.verify(crud).listar();
    }

    @Dado("^o usuario de codigo (\\d+)$")
    public void oUsuarioDeCodigo(int arg1) throws Throwable {
        codigo = arg1;
    }

    @Entao("^eu listo todos os personagens do usuario$")
    public void euListoTodosOsPersonagensDoUsuario() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);

        crud.listarPorUsuario(codigo);

        Mockito.verify(crud).listarPorUsuario(codigo);
    }

    @Dado("^a aventura de codigo (\\d+)$")
    public void aAventuraDeCodigo(int arg1) throws Throwable {
        codigo = arg1;
    }

    @Entao("^eu listo todos os personagens da aventura$")
    public void euListoTodosOsPersonagensDaAventura() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);
        crud.listarPorAventura(codigo);

        Mockito.verify(crud).listarPorAventura(codigo);
    }
    
    @Entao("^eu listo todos os personagens da aventura com ficha$")
    public void euListoTodosOsPersonagensDaAventuraComFicha() throws Throwable {
        crud = Mockito.mock(PersonagemCrudHibernate.class);
        crud.listarPorAventuraComFicha(codigo);

        Mockito.verify(crud).listarPorAventuraComFicha(codigo);
    }

}
