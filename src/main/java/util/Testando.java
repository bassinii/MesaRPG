package util;

import dao.AventuraCrudHibernate;
import dao.entity.Aventura;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tiago
 */
public class Testando {

    public static void main(String[] args) {

        try {
            AventuraCrudHibernate crud = new AventuraCrudHibernate();

            Aventura aventura = crud.buscaAventura(1);

            if (aventura != null) {
                System.out.println(
                        "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------"
                        + "ok... funcionou.... usuario: "+ aventura.getNome()
                        + "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------");
            } else {
                System.out.println(
                        "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------"
                        + "Vish...... NÃO funcionou...."
                        + "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------"
                        + "---------------------------------------------------------------------");
            }

            System.out.println("fim");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
