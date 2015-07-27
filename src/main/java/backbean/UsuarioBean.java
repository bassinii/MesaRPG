/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backbean;

import dao.UsuarioCrudHibernate;
import dao.entity.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import util.MessageUtil;
import util.SessaoUtil;

/**
 *
 * @author Tiago
 */
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private Usuario usuario;

    private String novaSenha;
    private String confirmaSenha;

    @PostConstruct
    public void iniciar() {
        this.usuario = (Usuario) SessaoUtil.getElementSession("usuarioLogado");
        SessaoUtil.validarUsuario(usuario, "usuarioLogin.xhtml");

    }

    public String voltarPaginaLogin() {
        return "login";
    }

    public String voltarPaginaIndex() {
        return "inicio";
    }

    public String editarUsuario() {
        return "editarUsuario";
    }

    public String consultarUsuario() {
        return "consultarUsuario";
    }

    public String excluir() {
        System.out.println("Teste entrada <-----");
        try{
            UsuarioCrudHibernate crud = new UsuarioCrudHibernate();
            crud.excluir(usuario);
            
            SessaoUtil.logout("usuarioLogin.xhtml");
            
            return "login";
        }
        catch(Exception e){
            MessageUtil.ErrorMessage(null, "Falha ao Excluir Conta", "Falha ao Excluir Conta");
        }
        catch(Throwable t){
            MessageUtil.ErrorMessage(null, "Falha ao Excluir Conta", "Falha ao Excluir Conta");
        }
        System.out.println("teste");
        return null;
    }

    public String editar() {

        if (this.novaSenha != "") {
            if (this.novaSenha.equals(this.confirmaSenha)) {
                usuario.setSenha(novaSenha);
            } else {
                MessageUtil.ErrorMessage(null, "As Senhas São Diferentes!", "As Senhas São Diferentes!");
                return null;
            }
        }

        try {
            UsuarioCrudHibernate crud = new UsuarioCrudHibernate();
            crud.atualizar(usuario);
            
            SessaoUtil.setElementSession("usuarioLogado", usuario);
            
            return "consultarUsuario";
        } catch (Exception e) {
            MessageUtil.ErrorMessage(null, "Não foi possivel salvar as alterações.", "Não foi possivel salvar as alterações.");
        } catch (Throwable t) {
            MessageUtil.ErrorMessage(null, "Não foi possivel salvar as alterações.", "Não foi possivel salvar as alterações.");
        }
        return null;

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

}
