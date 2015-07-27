/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "fala")
public class Fala implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;
    
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private Integer codigo;
    
    @Column(name = "autor", length = 50, nullable = true)
    private String autor;
    
    @Column(name = "fala", length = 500, nullable = true)
    private String fala;
    
    @Column(name = "data_hora", nullable = true)
    private Date dataHora;
    
    @Column(name = "dado", length = 45, nullable = true)
    private String dado;
    

    @ManyToOne
    @JoinColumn(name = "codigo_aventura", referencedColumnName = "codigo", nullable = false)
    private Aventura aventura;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFala() {
        return fala;
    }

    public void setFala(String fala) {
        this.fala = fala;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Aventura getAventura() {
        return aventura;
    }

    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    

}
