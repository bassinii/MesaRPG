package dao;

import dao.entity.Usuario;
import java.util.List;
import org.hibernate.*;
import dao.conexao.HibernateUtil;
import java.sql.Connection;
/*
 * Classe responsável por realizar as operações CRUD no objeto Usuario
 * */

public class UsuarioCrudHibernate {
    /*
     * Método que salva um usuário no banco de dados
     */

    public void salvar(Usuario usuario) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        try {
//abre um sessão com o banco de dados.
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//inicia um transacao
            transacao = sessao.beginTransaction();
//salva o usuário
            sessao.save(usuario);
//confirma a transacao
            transacao.commit();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível inserir um usuario. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    @SuppressWarnings("unchecked")
    /**
     *
     * @return List<Usuario>
     *
     * Método retorna uma lista de usuarios
     */
    public List<Usuario> listar() {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Usuario> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();

            transacao = sessao.beginTransaction();
            
            consulta = sessao.createQuery("from Usuario");
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            
            return resultado;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar os usuarios. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao); 
        }
    }
    /*
     * Busca um usuário pelo código

     */

    public Usuario buscaUsuario(int valor) {
        Usuario usuario = null;
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        try {
//inicia a sessao
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//inicia transacao
            transacao = sessao.beginTransaction();
//consulta o Usuario
            consulta = sessao.createQuery("from Usuario where codigo= :parametro");
//atribui o valor do código do usuário como parâmetro da consulta HQL.
            consulta.setInteger("parametro", valor);
//Obtém um único objeto da consulta
            usuario = (Usuario) consulta.uniqueResult();
//confirma transação
            transacao.commit();
            return usuario;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível buscar o contato. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
    public Usuario buscaUsuarioPorLogin(String login) {
        Usuario usuario = null;
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        try {
//inicia a sessao
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//inicia transacao
            transacao = sessao.beginTransaction();
//consulta o Usuario
            consulta = sessao.createQuery("from Usuario where login= :parametro");
//atribui o valor do código do usuário como parâmetro da consulta HQL.
            consulta.setString("parametro", login);
//Obtém um único objeto da consulta
            usuario = (Usuario) consulta.uniqueResult();
//confirma transação
            transacao.commit();
            
            return usuario;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível buscar o contato. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public void excluir(Usuario usuario) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        try {
            //abre um sessão com o banco de dados.
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
            //inicia um transacao
            transacao = sessao.beginTransaction();
            //exclui o usuário
            sessao.delete(usuario);
            //confirma a transacao
            transacao.commit();
            
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível excluir um usuario. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }

    }

    public void atualizar(Usuario usuario) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        try {
            //abre um sessão com o banco de dados.
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
            //inicia um transacao
            transacao = sessao.beginTransaction();
            //exclui o usuário
            sessao.update(usuario);
            //confirma a transacao
            transacao.commit();
            
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível excluir um usuario. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }

    }

    public Usuario buscaUsuario(String login, String senha) {
        Usuario user = null;
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        try {
//inicia a sessao
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//inicia transacao
            transacao = sessao.beginTransaction();
//consulta o Usuario
            consulta = sessao.createQuery("from Usuario where login=:login and senha=:senha");
//atribui o valor do código do usuário como parâmetro da consulta HQL.
            consulta.setString("login", login);
            consulta.setString("senha", senha);
//Obtém um único objeto da consulta
            user = (Usuario) consulta.uniqueResult();
//confirma transação
            transacao.commit();
            
            return user;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível buscar o contato. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
}
