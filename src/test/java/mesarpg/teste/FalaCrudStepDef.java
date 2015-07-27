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
import dao.FalaCrudHibernate;
import dao.entity.Aventura;
import dao.entity.Fala;
import java.util.List;
import org.junit.Assert;
import org.mockito.Mockito;

/**
 *
 * @author Tiago
 */
public class FalaCrudStepDef {

    private FalaCrudHibernate crud;
    private Fala fala;
    private List lista;
    private int codigo;

    @Dado("^que eu possuo uma Fala$")
    public void queEuUmaFala() throws Throwable {
        this.fala = new Fala();
    }

    @Quando("^eu recebo as informacoes da fala: (.*), (.*), (.*), (\\d+)$")
    public void euReceboAsInformacoesDaFalaAutorFalaD(String autor, String fala, String dado, int aventura) throws Throwable {

        this.fala.setAutor(autor);
        this.fala.setFala(fala);
        this.fala.setDado(dado);

        Aventura avent = new Aventura();
        avent.setCodigo(aventura);
        this.fala.setAventura(avent);

    }

    @Entao("^eu salvo a fala$")
    public void euSalvoAFala() throws Throwable {
        crud = Mockito.mock(FalaCrudHibernate.class);
        crud.salvar(fala);

        Assert.assertNotSame(0, this.fala.getCodigo());
        Mockito.verify(crud).salvar(fala);
    }

    //----------------------------------------------------------------------
    @Dado("^que eu quero listar as falas$")
    public void queEuQueroListarAsFalas() throws Throwable {
        this.crud = Mockito.mock(FalaCrudHibernate.class);
    }

    @Quando("^eu consulto as falas$")
    public void euConsultoAsFalas() throws Throwable {
        this.lista = crud.listar();
    }

    @Entao("^eu consigo uma lista de falas$")
    public void euConsigoUmaListaDeFalas() throws Throwable {
        Mockito.verify(this.crud).listar();
    }

    //---------------------------------------------------------------------------
    @Dado("^que eu quero listar as falas em uma Aventura de codigo (\\d+)$")
    public void queEuQueroListarAsFalasEmUmaAventuraDeCodigo(int arg1) throws Throwable {
        this.codigo = codigo;
    }

    @Quando("^eu consulto as falas por aventura$")
    public void euConsultoAsFalasPorAventura() throws Throwable {
        this.crud = Mockito.mock(FalaCrudHibernate.class);
        this.lista = crud.listarPorAventura(codigo);
    }

    @Entao("^eu consigo uma lista de falas por aventura$")
    public void euConsigoUmaListaDeFalasPorAventura() throws Throwable {

        Mockito.verify(this.crud).listarPorAventura(codigo);

    }

    //-------------------------------------------------------------------------
    @Dado("^que eu quero consultar uma fala de codigo (\\d+)$")
    public void queEuQueroConsultarUmaFalaDeCodigo(int arg1) throws Throwable {
        this.codigo = codigo;
    }

    @Quando("^eu consulta a fala$")
    public void euConsultaAFala() throws Throwable {
        this.crud = Mockito.mock(FalaCrudHibernate.class);
        this.fala = crud.buscarFala(codigo);
    }

    @Entao("^eu obtenho a fala$")
    public void euObtenhoAFala() throws Throwable {

        Mockito.verify(this.crud).buscarFala(codigo);

    }

    //----------------------------------------------------------------------
    @Dado("^que eu possuo uma fala de codigo (\\d+)$")
    public void queEuPossupUmaFalaDeCodigo(int arg1) throws Throwable {

        this.fala = new Fala();
        this.fala.setCodigo(arg1);
    }

    @Quando("^eu excluir a fala$")
    public void euExcluirAFala() throws Throwable {
        this.crud = Mockito.mock(FalaCrudHibernate.class);

        crud.excluir(this.fala);
    }

    @Entao("^a fala deve ser apagada$")
    public void aFalaDeveSerApagada() throws Throwable {
        Mockito.verify(this.crud).excluir(fala);
    }

    //--------------------------------------
    @Quando("^eu possuo as informacoes da fala: (\\d+), (.*), (.*), (.*), (\\d+)$")
    public void euPossuoAsInformacoesDaFalaAutorFalaD(int codigo, String atuor, String fala, String dado, int codigoAventura) throws Throwable {
        this.fala.setCodigo(codigo);
        this.fala.setAutor(atuor);
        this.fala.setFala(fala);
        this.fala.setDado(dado);

        Aventura avent = new Aventura();
        avent.setCodigo(codigoAventura);

        this.fala.setAventura(avent);
    }

    @Entao("^eu atualizado a fala$")
    public void euAtualizadoAFala() throws Throwable {

        this.crud = Mockito.mock(FalaCrudHibernate.class);
        crud.atualizar(this.fala);
        Mockito.verify(this.crud).atualizar(fala);

    }

}
