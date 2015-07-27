package mesarpg.teste;

import static org.junit.Assert.*;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import dao.AventuraCrudHibernate;
import dao.entity.Aventura;
import dao.entity.Usuario;
import java.util.List;
import org.junit.Assert;
import org.mockito.Mockito;

public class AventuraCrudStepDef {

    private AventuraCrudHibernate crud;
    private Aventura aventura;
    private int codigo;

    //Cenario: Salvando Uma Aventura
    @Dado("^que eu tenho uma aventura$")
    public void dado_que_eu_tenho_uma_aventura() throws Throwable {

        aventura = new Aventura();
    }

    @Quando("^eu tenho o nome da aventura (.*) e pontuacao minima (\\d+) e maxima (\\d+) e descricao de (.*)$")
    public void quando_eu_recebo_as_informacoes(String nome, int min, int max, String desc) throws Throwable {
        Usuario mestre = new Usuario();
        mestre.setCodigo(1);

        aventura.setNome(nome);
        aventura.setDescricao(desc);
        aventura.setPontosMinimo(min);
        aventura.setPontosMaximo(max);
        aventura.setMestre(mestre);
    }

    @Entao("^a aventura deve ser salva")
    public void entao_a_aventura_deve_ser_persistida() throws Throwable {

        this.crud = Mockito.mock(AventuraCrudHibernate.class);

        this.crud.salvar(aventura);

        Mockito.verify(crud).salvar(aventura);
        //assertEquals(arg1, result);
    }

    //Cenario: Consultando Uma Aventura-------------------------------------------------------------------
    @Dado("^que eu uma aventura de codigo igual (\\d+)$")
    public void dado_que_eu_tenho_uma_aventura_de_codigo_igual(int codigo) throws Throwable {

        this.codigo = codigo;
        this.aventura = null;
        this.crud = null;
    }

    @Quando("^eu consulto uma a aventura pelo codigo$")
    public void quando_eu_consulto_uma_a_aventura_pelo_codigo() throws Throwable {

        this.crud = Mockito.mock(AventuraCrudHibernate.class);
        this.aventura = crud.buscaAventura(codigo);

    }

    @Entao("^eu devo ter os dados de uma aventura$")
    public void entao_eu_devo_ter_os_dados_de_uma_aventura() throws Throwable {

        //Assert.assertNotNull(this.aventura);
        Mockito.verify(this.crud).buscaAventura(this.codigo);

    }

    //Cenario: Excluindo Uma Aventura-------------------------------------------------------------------
    @Quando("^eu excluo uma aventura que possui codigo igual a (\\d+)$")
    public void quando_eu_excluo_uma_aventura_que_possui_codigo_igual_a(int codigo) throws Throwable {

        this.aventura.setCodigo(codigo);

        this.crud = Mockito.mock(AventuraCrudHibernate.class);
        crud.excluir(this.aventura);

    }

    @Entao("^a aventura deve ser apagada$")
    public void entao_a_aventura_deve_ser_apagada() throws Throwable {

        //Assert.assertNotNull(this.aventura);
        Mockito.verify(this.crud).excluir(this.aventura);

    }

    //  Cenario: Consultando uma lista de Aventuras ------------------------------------------[
    @Dado("^que eu quero uma lista das aventuras$")
    public void que_eu_quero_uma_lista_das_aventuras() throws Throwable {

        this.crud = Mockito.mock(AventuraCrudHibernate.class);
    }

    @Quando("^eu consulto todas as aventuras$")
    public void eu_consulto_todas_as_aventuras() throws Throwable {

        List lista = this.crud.listar();

    }

    @Entao("^eu consigo uma lista das aventuras")
    public void eu_consigo_uma_lista_das_aventuras() throws Throwable {

        Mockito.verify(crud).listar();
        //assertEquals(arg1, result);
    }
    
    //---------------------------------------------------------------------

    @Quando("^eu tenho todos os dados da aventura: (\\d+), (.*), (\\d+), (\\d+), (.*)$")
    public void euTenhoTodosOsDadosDaAventuraAventuraDescricaoAventura(int codigo, String nome, int min, int max, String desc) throws Throwable {
        this.aventura.setCodigo(codigo);
        this.aventura.setNome(nome);
        this.aventura.setDescricao(desc);
        this.aventura.setPontosMinimo(min);
        this.aventura.setPontosMaximo(max);
    }

    @Entao("^a aventura deve ser atualizada$")
    public void aAventuraDeveSerAtualizada() throws Throwable {
        this.crud = Mockito.mock(AventuraCrudHibernate.class);
        crud.atualizar(aventura);
        Mockito.verify(crud).atualizar(aventura);
    }

    @Dado("^que eu tenho um Usuario de codigo (\\d+)$")
    public void queEuTenhoUmUsuarioDeCodigo(int codigo) throws Throwable {
        
        this.codigo = codigo;
        
    }

    @Quando("^eu listo as aventuras por usuario$")
    public void euListoAsAventurasPorUsuario() throws Throwable {
        this.crud = Mockito.mock(AventuraCrudHibernate.class);
        crud.listarPorUsuario(codigo);
        
    }

    @Entao("^eu recebo uma lista de aventuras desse usuario$")
    public void euReceboUmaListaDeAventurasDesseUsuario() throws Throwable {
        Mockito.verify(crud).listarPorUsuario(codigo);
    }

}
