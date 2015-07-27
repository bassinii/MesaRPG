/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.conexao.HibernateUtil;
import dao.entity.Aventura;
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
public class AventuraCrudHibernate {

    public void salvar(Aventura aventura) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        try {
//abre um sessão com o banco de dados.
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
            if (sessao == null) {
                System.out.println("sessao nula!!!!");
            }
//inicia um transacao
            transacao = sessao.beginTransaction();
//salva o usuário
            sessao.save(aventura);
//confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível inserir uma Aventura. Erro:" + e.getMessage());
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
    public List<Aventura> listar() {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Aventura> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();
            
            consulta = sessao.createQuery("from Aventura");
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar as Aventuras. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
    /*
     * Busca um usuário pelo código

     */

    public List<Aventura> listarPorUsuario(int codigoUsuario) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Aventura> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();
            
            consulta = sessao.createQuery("from Aventura where codigo_usuario=:parametro");
            consulta.setInteger("parametro", codigoUsuario);
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar as aventuras. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public Aventura buscaAventura(int valor) {
        Aventura aventura = null;
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
            consulta = sessao.createQuery("from Aventura where codigo= :parametro");
//atribui o valor do código do usuário como parâmetro da consulta HQL.
            consulta.setInteger("parametro", valor);
//Obtém um único objeto da consulta
            aventura = (Aventura) consulta.uniqueResult();
//confirma transação
            transacao.commit();
            return aventura;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível buscar a Aventura. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public void excluir(Aventura aventura) {
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
            sessao.delete(aventura);
            //confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível excluir a Aventura. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }

    }

    public void atualizar(Aventura aventura) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        try {
            //abre um sessão com o banco de dados.
            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
            //inicia um transacao
            transacao = sessao.beginTransaction();
            //atualizar aventura
            sessao.update(aventura);
            //confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível excluir um Aventura. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }

    }
}
