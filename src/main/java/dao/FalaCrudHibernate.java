/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.conexao.HibernateUtil;
import dao.entity.Fala;
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
public class FalaCrudHibernate {

    @SuppressWarnings("unchecked")
    public void salvar(Fala fala) {
        Session sessao = null;
        Transaction transacao = null;
        SessionFactory fabrica = null;
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
            sessao.save(fala);
//confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível inserir uma Fala. Erro:" + e.getMessage());
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
    public List<Fala> listar() {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Fala> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();
            
            consulta = sessao.createQuery("from Fala");
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
            
//retona a lista de usuarios
            return resultado;
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar as Fala. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
    /*
     * Busca um usuário pelo código

     */

//    public List<Fala> listarPorPersonagem(int personagem) {
//        Session sessao = null;
//        Transaction transacao = null;
//        Query consulta = null;
//        List<Fala> resultado = null;
//        try {
////Abre uma sessao no banco de dados
//
//            sessao = HibernateUtil.getSessionFactory().openSession();
////Inicia uma transacao
////Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
//            transacao = sessao.beginTransaction();
//            
//            consulta = sessao.createQuery("from Fala where codigo_personagem=:parametro");
//            consulta.setInteger("parametro", personagem);
//            //Retorna lista de contatos
//
//            resultado = consulta.list();
//            //Confirma a transacao
//            transacao.commit();
////retona a lista de usuarios
//            return resultado;
//        } catch (HibernateException e) {
//            throw new ExceptionInInitializerError("Não foi possível selecionar as Falas. Erro:" + e.getMessage());
//        }
//        finally{
//           Connection con = sessao.close(); 
//        }
//    }

    public List<Fala> listarPorAventura(int codigoAventura) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Fala> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();
            
            consulta = sessao.createQuery("from Fala where codigo_aventura=:parametro");
            consulta.setInteger("parametro", codigoAventura);
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar as Falas. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public Fala buscarFala(int valor) {
        Fala fala = null;
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
            consulta = sessao.createQuery("from Fala where codigo= :parametro");
//atribui o valor do código do usuário como parâmetro da consulta HQL.
            consulta.setInteger("parametro", valor);
//Obtém um único objeto da consulta
            fala = (Fala) consulta.uniqueResult();
//confirma transação
            transacao.commit();
            return fala;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível buscar a fala. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public void excluir(Fala fala) {
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
            sessao.delete(fala);
            //confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível excluir a Fala. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }

    }

    public void atualizar(Fala fala) {
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
            sessao.update(fala);
            //confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível excluir um Fala. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }

    }
}
