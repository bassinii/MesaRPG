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
import util.SenhaUtil;
import util.SessaoUtil;

/**
 *
 * @author Bassini
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;

    private String senha, login;

    @PostConstruct
    public void iniciar() {
        senha = "";
        login = "";
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String logar() {

        UsuarioCrudHibernate crud = new UsuarioCrudHibernate();

        try {
            
            senha = SenhaUtil.convertStringToMd5(senha);
            
            Usuario usuarioLogado = crud.buscaUsuario(login, senha);
            if (usuarioLogado != null) {
                SessaoUtil.setElementSession("usuarioLogado", usuarioLogado);
                return "inicio";
            } else {
                MessageUtil.ErrorMessage(null, "Login ou Senha incorreto.", "Login ou Senha incorreto.");
                return null;
            }
        } catch (Exception e) {

        } catch (Throwable t) {

        }
        MessageUtil.ErrorMessage(null, "Não foi possivel realizar o login no sistema.", "Não foi possivel realizar o login no sistema.");
        return null;

    }

    public String cadastrar() {
        return "cadastrarUsuario";
    }

    public String recuperarSenha() {
        return null;
    }

}
