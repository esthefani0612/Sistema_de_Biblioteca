package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name= "Usuario")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name="celular")
    private String telefone;
    @Column(name ="QtdLivroEmprest")
    private int qtdLivrosEmprestados;
    @OneToMany(mappedBy = "pessoa")
    private List<Livro> livrosEmprestados = new ArrayList<>();

    public Pessoa(String nome, String email, String celu) {
        this.nome = nome;
        this.email = email;
        this.qtdLivrosEmprestados = 0;
        this.telefone = celu;
    }
    
    public Pessoa(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdLivrosEmprestados() {
        return qtdLivrosEmprestados;
    }

    public void setQtdLivrosEmprestados(int qtdLivrosEmprestados) {
        this.qtdLivrosEmprestados = qtdLivrosEmprestados;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    
    public boolean getPossuiVaga() {
       return qtdLivrosEmprestados < 3;}
    
    

   public void removerLivro(Livro livro) {
        livrosEmprestados.remove(livro);
        qtdLivrosEmprestados--;
}
   
   public void adicionarLivro(Livro livro) {
        livrosEmprestados.add(livro);
        qtdLivrosEmprestados++;
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
   
}