/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bassini
 */
@Entity
@Table(name = "aventura")
public class Aventura implements Serializable {
    
    

    private static final long serialVersionUID = 3081176758827811827L;
    
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private Integer codigo;
    
    
    @Column(name = "nome", length = 45, nullable = false)
    private String nome;
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "pontos_minimo",nullable = false)
    private int pontosMinimo;

    @Column(name = "pontos_maximo", nullable = false)
    private int pontosMaximo;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo", nullable = false)
    private Usuario mestre;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontosMinimo() {
        return pontosMinimo;
    }

    public void setPontosMinimo(int pontosMinimo) {
        this.pontosMinimo = pontosMinimo;
    }

    public int getPontosMaximo() {
        return pontosMaximo;
    }

    public void setPontosMaximo(int pontosMaximo) {
        this.pontosMaximo = pontosMaximo;
    }

    public Usuario getMestre() {
        return mestre;
    }

    public void setMestre(Usuario mestre) {
        this.mestre = mestre;
    }
    
    public String getPontuação(){
        return (this.pontosMinimo+" - "+this.pontosMaximo);
    }
    
    public Aventura() {
    }

    
    
}
