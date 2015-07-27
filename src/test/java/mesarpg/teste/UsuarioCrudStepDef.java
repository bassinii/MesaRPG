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
import dao.UsuarioCrudHibernate;
import dao.entity.Usuario;
import org.mockito.Mockito;

/**
 *
 * @author Tiago
 */
public class UsuarioCrudStepDef {

    private int codigo;
    private Usuario usuario;
    private UsuarioCrudHibernate crud;

    @Dado("^que eu possuo um usuario$")
    public void queEuPossuoUmUsuario() throws Throwable {
        this.usuario = new Usuario();

    }

    @Quando("^eu recebo as informacoes do usuario: (\\d+), (.*), (.*), (.*), (.*)$")
    public void euReceboAsInformacoesDoUsuario(int codigo, String nome, String login, String senha, String email) throws Throwable {
        usuario.setCodigo(codigo);
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setEmail(email);
    }

    @Entao("^eu salvo o usuario$")
    public void euSalvoOUsuario() throws Throwable {
        crud = Mockito.mock(UsuarioCrudHibernate.class);
        crud.salvar(usuario);

        Mockito.verify(crud).salvar(usuario);
    }

    @Entao("^eu excluo o usuario$")
    public void euExcluoOUsuario() throws Throwable {
        crud = Mockito.mock(UsuarioCrudHibernate.class);
        crud.excluir(usuario);

        Mockito.verify(crud).excluir(usuario);
    }

    @Entao("^eu atualizo o usuario$")
    public void euAtualizoOUsuario() throws Throwable {
        crud = Mockito.mock(UsuarioCrudHibernate.class);
        crud.atualizar(usuario);

        Mockito.verify(crud).atualizar(usuario);
    }

    @Entao("^eu busco o usuario$")
    public void euBuscoOUsuario() throws Throwable {
        crud = Mockito.mock(UsuarioCrudHibernate.class);
        crud.buscaUsuario(this.usuario.getCodigo());

        Mockito.verify(crud).buscaUsuario(this.usuario.getCodigo());
    }

    @Entao("^eu busco o usuario por login$")
    public void euBuscoOUsuarioPorLogin() throws Throwable {
        crud = Mockito.mock(UsuarioCrudHibernate.class);
        crud.buscaUsuarioPorLogin(this.usuario.getLogin());

        Mockito.verify(crud).buscaUsuarioPorLogin(this.usuario.getLogin());
    }

    @Entao("^eu busco o usuario por login e senha$")
    public void euBuscoOUsuarioPorLoginESenha() throws Throwable {
        crud = Mockito.mock(UsuarioCrudHibernate.class);
        crud.buscaUsuario(this.usuario.getLogin(), usuario.getSenha());

        Mockito.verify(crud).buscaUsuario(this.usuario.getLogin(), usuario.getSenha());
    }

    @Dado("^que eu quero listar os usuarios$")
    public void queEuQueroListarOsUsuarios() throws Throwable {
        crud = Mockito.mock(UsuarioCrudHibernate.class);
        
    }

    @Entao("^eu listo os usuario cadastrados$")
    public void euListoOsUsuarioCadastrados() throws Throwable {
       crud.listar();
       
       Mockito.verify(crud).listar();
    }
}
