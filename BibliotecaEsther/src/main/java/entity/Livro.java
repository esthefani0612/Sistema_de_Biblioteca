
package entity;

import javax.persistence.*;

@Entity
@Table(name="livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Declarando os atributos e suas visibilidades.
    @Column(name="id")
    private int idLivro;
    @Column(name="nome")
    private String nome;
    private boolean disponivel;
    @Column(name="anoPublicacao")
    private String anoPublicacao;
    @Column(name="Autor")
    private String autor;
    
    @ManyToOne
    @JoinColumn(name="pessoa_FK")
    private Pessoa pessoa;
    
    //Construtor de Livro.
    public Livro(String nome,String autor, String anoPublicacao) {
        this.nome = nome;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }
    public  Livro(){
    }

//Métodos acessores e modificadores.
public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void add(Livro livro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
    
}

