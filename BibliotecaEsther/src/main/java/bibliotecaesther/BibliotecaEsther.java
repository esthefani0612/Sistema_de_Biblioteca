
package bibliotecaesther;

import entity.Livro;
import entity.Pessoa;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.BibliotService;
import view.Visao;

public class BibliotecaEsther {
    
    public static void main(String[] args) {
       
    }
    private BibliotService bibliotecaService;
    public BibliotecaEsther() {
        bibliotecaService = new BibliotService(); // Inicialização do serviço da biblioteca
    } 
    public static void maind(String[] args){
    
    Visao v = new Visao();
    v.setVisible(true);}
    
     public void cadastrarLivros() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
         
        String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
        String autor = JOptionPane.showInputDialog("Digite o nome do autor:");
        String publicacao = JOptionPane.showInputDialog("Digite o ano de publicação:");


        Livro livro = new Livro(titulo, autor, publicacao);
        bibliotecaService.cadastrarLivro(livro);
        
        //persistindo os livros cadastrados, no banco de dados
        session.save(livro);
        tx.commit();
        JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
    }

public void cadastrarUsuario() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        
        String nome = JOptionPane.showInputDialog("Digite o nome:");
        String email = JOptionPane.showInputDialog("Digite o email:");
        String celular = JOptionPane.showInputDialog("Digite o número de celular:");

        Pessoa pessoa = new Pessoa(nome, email, celular);
        bibliotecaService.cadastrarUsuario(pessoa);
        session.save(pessoa);
        tx.commit();
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
    }
    
//Este método faz a conexão com o bando de dados para listar todos os usuários cadastrados...
    public List<Pessoa> listarPessoa() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        
        CriteriaQuery<Pessoa> query = builder.createQuery(Pessoa.class);
        Root<Pessoa> root = query.from(Pessoa.class);
        query.select(root);

        List<Pessoa> pessoas = session.createQuery(query).getResultList();
        session.close();

    return pessoas;
}
/*...E este outro método chama o método acima e exibe o resultado na tela 
por meio do JOptionPane.
*/
    public void exibirListaDePessoas() {
    
    List<Pessoa> pessoas = listarPessoa();

    if (pessoas.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado.", "Lista de Pessoas", JOptionPane.INFORMATION_MESSAGE);
    } else {
        StringBuilder sb = new StringBuilder();
        sb.append("Pessoas cadastradas:\n\n");

        for (Pessoa pessoa : pessoas) {
            sb.append("Nome: ").append(pessoa.getNome()).append("\n");
            sb.append("E-mail: ").append(pessoa.getEmail()).append("\n");
            sb.append("-----------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Pessoas", JOptionPane.PLAIN_MESSAGE);
    }
}
    
//Este método faz a conexão com o bando de dados para listar todos os livros cadastrados...
   public List<Livro> listarLivro() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        
        CriteriaQuery<Livro> query = builder.createQuery(Livro.class);
        Root<Livro> root = query.from(Livro.class);
        query.select(root);

        List<Livro> livro = session.createQuery(query).getResultList();
        session.close();

    return livro;
}
   
public void exibirListaDeLivros() {
    
    List<Livro> livros = listarLivro();

    if (livros.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nenhum livro cadastrado.", "Lista de Livros", JOptionPane.INFORMATION_MESSAGE);
    } else {
        StringBuilder sb = new StringBuilder();
        sb.append("Livros cadastrados:\n\n");

        for (Livro pessoa : livros) {
            sb.append("Título: ").append(pessoa.getNome()).append("\n");
            sb.append("Autor: ").append(pessoa.getAutor()).append("\n");
            sb.append("-----------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Livros", JOptionPane.PLAIN_MESSAGE);
    }
}



   public void buscarLivro() {
    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do livro: "));

    Session session = HibernateUtil.getSessionFactory().openSession();
    Livro livro = session.get(Livro.class, id);
    session.close();

    if (livro != null) {
        StringBuilder sb = new StringBuilder();
        sb.append("Livro encontrado:\n\n");
        sb.append("ID: ").append(livro.getIdLivro()).append("\n");
        sb.append("Título: ").append(livro.getNome()).append("\n");
        sb.append("Autor: ").append(livro.getAutor()).append("\n");

        JOptionPane.showMessageDialog(null, sb.toString(), "Detalhes do Livro", JOptionPane.PLAIN_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Livro não encontrado!", 
                "Erro", JOptionPane.ERROR_MESSAGE);}
        
}
   
   
    public void buscarUsuario() {
    int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário: "));

    Session session = HibernateUtil.getSessionFactory().openSession();
    Pessoa pessoa = session.get(Pessoa.class, id);
    session.close();

    if (pessoa != null) {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuário encontrado:\n\n");
        sb.append("ID: ").append(pessoa.getId()).append("\n");
        sb.append("Nome: ").append(pessoa.getNome()).append("\n");
        sb.append("E-mail: ").append(pessoa.getEmail()).append("\n");
        sb.append("Telefone: ").append(pessoa.getTelefone()).append("\n");

        JOptionPane.showMessageDialog(null, sb.toString(), "Detalhes do Usuário", JOptionPane.PLAIN_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Usuário não encontrado!", 
                "Erro", JOptionPane.ERROR_MESSAGE);}}



    public void realizarEmprestimo() {
    int idLivro = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do livro a ser emprestado: "));
    int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário que está realizando o empréstimo: "));

    try {
        bibliotecaService.realizarEmprestimo(idLivro, idUsuario);
        JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
    } catch (BibliotService.EmprestimoException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
    
    
    
   public void realizarDevolucao() {
    int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário que está realizando a devolução: "));
    int idLivro = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do livro a ser devolvido: "));

    try {
        bibliotecaService.realizarDevolucao(idUsuario, idLivro);
        JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso!");
    } catch (BibliotService.DevolucaoException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}  
}
    

