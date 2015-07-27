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
import org.hibernate.annotations.Cascade;
import util.GravatarUtil;

/**
 *
 * @author Bassini
 */
@Entity
@Table(name = "personagem")
public class Personagem implements Serializable {
    
    private static final long serialVersionUID = 3081176758827811827L;
    
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private Integer codigo;
    
    @ManyToOne
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "codigo_ficha", referencedColumnName = "codigo", nullable = true)
    private Ficha ficha;
    
    @ManyToOne
    @JoinColumn(name = "codigo_aventura", referencedColumnName = "codigo", nullable = false)
    private Aventura aventura;

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

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Aventura getAventura() {
        return aventura;
    }

    public void setAventura(Aventura aventura) {
        this.aventura = aventura;
    }

    
    public String gerarURLGravatar(){
        String url  = "https://s.gravatar.com/avatar/";
               url += GravatarUtil.md5Hex( this.usuario.getEmail());
               //url += "?s600";     
        return url;
    }
     
}
