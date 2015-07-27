/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.conexao.HibernateUtil;
import dao.entity.Personagem;
import dao.entity.Usuario;
import java.sql.Connection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Bassini
 */
public class PersonagemCrudHibernate {

    public void salvar(Personagem personagem) {
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
            sessao.save(personagem);
//confirma a transacao
            transacao.commit();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível inserir um Personagem. Erro:" + e.getMessage());
        } finally {
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
    public List<Personagem> listar() {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Personagem> resultado = null;
        try {
//Abre uma sessao no banco de dados

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            consulta = sessao.createQuery("from Personagem");
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar os Personagens. Erro:" + e.getMessage());
        } finally {
            HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
    /*
     * Busca um usuário pelo código

     */

    public List<Personagem> listarPorUsuario(int codigoUsuario) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Personagem> resultado = null;
        try {
//Abre uma sessao no banco de dados

            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            consulta = sessao.createQuery("from Personagem where codigo_usuario=:parametro");
            consulta.setInteger("parametro", codigoUsuario);
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar os Personagens. Erro:" + e.getMessage());
        } finally {
            HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public List<Personagem> listarPorAventura(int codigoAventura) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Personagem> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();

            consulta = sessao.createQuery("from Personagem where codigo_aventura=:parametro");
            consulta.setInteger("parametro", codigoAventura);
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar os Personagens. Erro:" + e.getMessage());
        } finally {
            HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public List<Personagem> listarPorAventuraComFicha(int codigoAventura) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Personagem> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();

            consulta = sessao.createQuery("from Personagem where codigo_aventura=:parametro and codigo_ficha!=null");
            consulta.setInteger("parametro", codigoAventura);
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar os Personagens. Erro:" + e.getMessage());
        } finally {
            HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public Personagem buscaPersonagem(int valor) {
        Personagem personagem = null;
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
            consulta = sessao.createQuery("from Personagem where codigo= :parametro");
//atribui o valor do código do usuário como parâmetro da consulta HQL.
            consulta.setInteger("parametro", valor);
//Obtém um único objeto da consulta
            personagem = (Personagem) consulta.uniqueResult();
//confirma transação
            transacao.commit();
            return personagem;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível buscar o Personagem. Erro:" + e.getMessage());
        } finally {
            HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public void excluir(Personagem personagem) {
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
            sessao.delete(personagem);
            //confirma a transacao
            transacao.commit();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível excluir a Personagem. Erro:" + e.getMessage());
        } finally {
            HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public void atualizar(Personagem personagem) {
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
            sessao.update(personagem);
            //confirma a transacao
            transacao.commit();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível excluir um Personagem. Erro:" + e.getMessage());
        } finally {
            HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
}
