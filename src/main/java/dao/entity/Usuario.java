package dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 3081176758827811827L;
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private Integer codigo;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @Column(name = "login", unique = true ,length = 50, nullable = false)
    private String login;
    
    @Column(name = "senha", length = 50, nullable = false)
    private String senha;
    
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    
        
    //gerar gets e sets
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
   

}
