/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.conexao.HibernateUtil;
import dao.entity.Ficha;
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
public class FichaCrudHibernate {

    public void salvar(Ficha ficha) {
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
            sessao.save(ficha);
            transacao.commit();
//confirma a transacao
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Não foi possível inserir uma Ficha. Erro:" + e.getMessage());
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
    public List<Ficha> listar() {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Ficha> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();
            /*Responsável por montar consultas no Hibernate usando o padrão HQL.
             O hibernate cria um nova linguagem HQL, a sintaxe similar ao SQL. Observe que, ao
             passarmos a instrucao SQL para o método, não usamos a tradicional instrucao select * from contato, mas
             from Contato. Isso porque agora estamos lidando com objetos, e não mais linhas de resultado de uma consulta.
             Tanto que Contato, de from Contato, se refere à nossa classe conforme mapeada no arquivo XML
             Mais informacoes: http://www.mkyong.com/hibernate/hibernate-query-examples-hql/
             */
            consulta = sessao.createQuery("from Ficha");
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar as fichas. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
    /*
     * Busca um usuário pelo código

     */

    public List<Ficha> listarPorUsuario(int codigoUsuario) {
        Session sessao = null;
        SessionFactory fabrica = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Ficha> resultado = null;
        try {
//Abre uma sessao no banco de dados

            fabrica = HibernateUtil.getSessionFactory();
            sessao = fabrica.openSession();
//Inicia uma transacao
//Mesmo na leitura é bom manter o controle da transação - leia mais:http://www.inf.puc-rio.br/~rogcosta/inf1341/bd3-TransacoesEmAplicacoes.pdf
            transacao = sessao.beginTransaction();
            
            consulta = sessao.createQuery("from Ficha where codigo_usuario=:parametro");
            consulta.setInteger("parametro", codigoUsuario);
            //Retorna lista de contatos

            resultado = consulta.list();
            //Confirma a transacao
            transacao.commit();
//retona a lista de usuarios
            return resultado;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível selecionar as fichas. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public Ficha buscaFicha(int valor) {
        Ficha ficha = null;
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
            consulta = sessao.createQuery("from Ficha where codigo= :parametro");
//atribui o valor do código do usuário como parâmetro da consulta HQL.
            consulta.setInteger("parametro", valor);
//Obtém um único objeto da consulta
            ficha = (Ficha) consulta.uniqueResult();
//confirma transação
            transacao.commit();
            return ficha;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível buscar a ficha. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }

    public void excluir(Ficha ficha) {
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
            sessao.delete(ficha);
            //confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível excluir a Ficha. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }

    }

    public void atualizar(Ficha ficha) {
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
            sessao.update(ficha);
            //confirma a transacao
            transacao.commit();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Não foi possível excluir um ficha. Erro:" + e.getMessage());
        }
        finally{
           HibernateUtil.CloseSessionFactory(fabrica, sessao);
        }
    }
}
