/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bassini
 */
@Entity
@Table(name = "ficha")
public class Ficha implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;
    
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private Integer codigo;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo", nullable = false)
    private Usuario usuario;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    
    @Column(name = "pontos", nullable = false)
    private int pontos;
    
    @Column(name = "forca", nullable = false)
    private int forca;
    
    @Column(name = "habilidade", nullable = false)
    private int habilidade;
    
    @Column(name = "resistencia", nullable = false)
    private int resistencia;
    
    @Column(name = "armadura", nullable = false)
    private int armadura;
    
    @Column(name = "poder_de_fogo", nullable = false)
    private int poderDeFogo;
    
    @Column(name = "pontos_de_vida", nullable = false)
    private int pontosDeVida;
    
    @Column(name = "pontos_de_magia", nullable = false)
    private int pontosDeMagia;
    
    @Column(name = "pontos_de_experiencia", nullable = true)
    private int pontosDeExperiencia;
    
    @Column(name = "vantagens", length = 500 ,nullable = true)
    private String vantagens;
    
    @Column(name = "desvantagens", length = 500 ,nullable = true)
    private String desvantagens;
    
    @Column(name = "tipos_de_dano", length = 200 ,nullable = true)
    private String tiposDeDano;

    @Column(name = "itens", length = 500 ,nullable = true)
    private String itens;

    
    @Column(name = "historia", length = 500 ,nullable = true)
    private String historia;
    
    @Column(name = "magias_conhecidas", length = 500 ,nullable = true)
    private String magiasConhecidas;
        
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getPoderDeFogo() {
        return poderDeFogo;
    }

    public void setPoderDeFogo(int poderDeFogo) {
        this.poderDeFogo = poderDeFogo;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public int getPontosDeMagia() {
        return pontosDeMagia;
    }

    public void setPontosDeMagia(int pontosDeMagia) {
        this.pontosDeMagia = pontosDeMagia;
    }

    public String getVantagens() {
        return vantagens;
    }

    public void setVantagens(String vantagens) {
        this.vantagens = vantagens;
    }

    public String getDesvantagens() {
        return desvantagens;
    }

    public void setDesvantagens(String desvantagens) {
        this.desvantagens = desvantagens;
    }

    public String getItens() {
        return itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getMagiasConhecidas() {
        return magiasConhecidas;
    }

    public void setMagiasConhecidas(String magiasConhecidas) {
        this.magiasConhecidas = magiasConhecidas;
    }

    public int getPontosDeExperiencia() {
        return pontosDeExperiencia;
    }

    public void setPontosDeExperiencia(int pontosDeExperiencia) {
        this.pontosDeExperiencia = pontosDeExperiencia;
    }

    public String getTiposDeDano() {
        return tiposDeDano;
    }

    public void setTiposDeDano(String tiposDeDano) {
        this.tiposDeDano = tiposDeDano;
    }
    
    public Ficha(){
        this.forca =0;
        this.habilidade =0;
        this.resistencia =0;
        this.armadura =0;
        this.poderDeFogo =0;
        this.pontos =0;
        this.pontosDeVida =0;
        this.pontosDeMagia =0;
        
        this.vantagens = "";
        this.desvantagens = "";
        this.itens = "";
        this.tiposDeDano = "";
        this.historia = "";
        this.magiasConhecidas = "";
    }
    
    
}
