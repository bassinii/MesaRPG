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
import dao.FichaCrudHibernate;
import dao.entity.Ficha;
import dao.entity.Usuario;
import org.mockito.Mockito;

/**
 *
 * @author Tiago
 */
public class FichaCrudStepDef {

    private FichaCrudHibernate crud;
    private Ficha ficha;
    private int codigo;

    @Dado("^que eu tenho uma ficha$")
    public void queEuTenhoUmaFicha() throws Throwable {
        this.ficha = new Ficha();
    }
    
    


    @Quando("^eu recebo as informacoes da ficha: (.*)$")
    public void euReceboAsInformacoesDaFicha(String nome) throws Throwable {
        this.ficha.setNome(nome);
    }

    @Entao("^eu devo salvar a ficha$")
    public void euDevoSalvarAFicha() throws Throwable {
        this.crud = Mockito.mock(FichaCrudHibernate.class);
        crud.salvar(ficha);

        Mockito.verify(crud).salvar(ficha);
    }

    @Dado("^que eu quero listar as fichas$")
    public void queEuQueroListarAsFichas() throws Throwable {
        this.crud = Mockito.mock(FichaCrudHibernate.class);
    }

    @Entao("^eu recebo uma lista de fichas$")
    public void euReceboUmaListaDeFichas() throws Throwable {
        this.crud.listar();

        Mockito.verify(this.crud).listar();
    }

    @Dado("^que eu tenho um usuario de codigo (\\d+)$")
    public void queEuTenhoUmUsuarioDeCodigo(int arg1) throws Throwable {
        this.codigo = arg1;
    }

    @Quando("^eu quero listar as fichas por usuario$")
    public void queEuQueroListarAsFichasPorUsuario() throws Throwable {
        this.crud = Mockito.mock(FichaCrudHibernate.class);
    }

    @Entao("^eu recebo uma lista de fichas por usuario$")
    public void euReceboUmaListaDeFichasPorUsuario() throws Throwable {
        crud.listarPorUsuario(codigo);
        Mockito.verify(crud).listarPorUsuario(codigo);
    }

    @Dado("^que eu tenho uma ficha de codigo (\\d+)$")
    public void queEuTenhoUmaFichaDeCodigo(int arg1) throws Throwable {
        this.codigo = arg1;
    }

    @Quando("^eu busco a ficha pelo codigo$")
    public void queEuBuscoAFichaPeloCodigo() throws Throwable {
        this.crud = Mockito.mock(FichaCrudHibernate.class);
        this.ficha = crud.buscaFicha(codigo);
    }

    @Entao("^eu recebo uma ficha$")
    public void euReceboUmaFicha() throws Throwable {
        Mockito.verify(crud).buscaFicha(codigo);
    }


    @Quando("^eu excluo a ficha$")
    public void euExcluoAFicha() throws Throwable {
        this.crud = Mockito.mock(FichaCrudHibernate.class);

        this.ficha = new Ficha();
        this.ficha.setCodigo(codigo);
        this.crud.excluir(ficha);
    }

    @Entao("^a ficha e apagada$")
    public void aFichaEApagada() throws Throwable {
        Mockito.verify(crud).excluir(ficha);
    }
    
    
    @Quando("^eu recebo as novas informacoes da ficha: (\\d+), (.*)$")
    public void euReceboAsNovasInformacoesDaFicha(int codigo, String nome) throws Throwable {
        this.ficha.setCodigo(codigo);
        this.ficha.setNome(nome);
    }
    
    @Entao("^eu atualizo a ficha$")
    public void euAtualizoAFicha() throws Throwable {
        this.crud = Mockito.mock(FichaCrudHibernate.class);
        crud.atualizar(ficha);
        Mockito.verify(crud).atualizar(ficha);
    }
}
