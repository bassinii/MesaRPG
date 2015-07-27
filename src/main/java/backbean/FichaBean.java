/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.FichaCrudHibernate;
import dao.entity.Ficha;
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
@ManagedBean(name = "fichaBean")
@ViewScoped
public class FichaBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;
    private List<Ficha> lista;
    private Ficha ficha;

    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");

        inicializaFicha();
        inicializaListaFichas();
    }

    private void inicializaFicha() {

        this.ficha = (Ficha) SessaoUtil.getElementSession("ficha");
        
        if (ficha == null ){
            this.ficha = new Ficha();
            this.ficha.setUsuario(this.usuario);
        }
        else{
            SessaoUtil.removeElementSession("ficha");
        }
        
    }
    
    private void inicializaListaFichas(){
        FichaCrudHibernate crud = new FichaCrudHibernate();
        this.lista = crud.listarPorUsuario(this.usuario.getCodigo());
    }
        
    
    

    public String novaFicha() {
        return "cadastrarFicha";
    }

    public String consultarFicha() {
        SessaoUtil.setElementSession("ficha", this.ficha);
        return "consultarFicha";
    }
    
    public String editarFicha() {
        SessaoUtil.setElementSession("ficha", this.ficha);
        return "editarFicha";
    }

    public String voltarPaginaIndex() {
        return "inicio";
    }
    public String voltarPaginaListarFichas() {
        return "listarFichas";
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
    
    public String salvar(){
        FichaCrudHibernate crud = new FichaCrudHibernate();
        
        crud.salvar(ficha);
        return this.consultarFicha();
    }
    public String editar(){
        
        try {
            FichaCrudHibernate crud = new FichaCrudHibernate();
            crud.atualizar(this.ficha);
            
            return "consultarFicha";
        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Não foi possivel salvar as alterações.", "Não foi possivel salvar as alterações.");
        } catch (Throwable t) {
            MessageUtil.ErrorMessage(null, "Não foi possivel salvar as alterações.", "Não foi possivel salvar as alterações.");
        }
        return null;
        
    }
    
    
    public String excluir(){
        
        try{
            FichaCrudHibernate crud = new FichaCrudHibernate();
            crud.excluir(ficha);
            this.ficha = null;
            
            this.lista = crud.listar();
        }
        catch(Exception e){
            
        }
        return this.voltarPaginaListarFichas();
    }

}
