
package service;

import entity.*;
import repositorie.*;
import java.util.List;

public class BibliotService {

    public class EmprestimoException extends Exception {
        public EmprestimoException(String mensagem) {
        super(mensagem);
}}
    
    public class DevolucaoException extends Exception {
        public DevolucaoException(String mensagem) {
        super(mensagem);
}}
    
    
    private PessoaRepositorie usuario = new PessoaRepositorie();
       
    public Pessoa buscarUsuario(int id) {
        return usuario.buscarPorId(id);
    }
    public List<Pessoa> listarUsuario() {
        return usuario.buscarTodos();
    }
    
    public void cadastrarUsuario(Pessoa usuarios) {
        usuario.cadastrarU(usuarios);
    }
    
     public void realizarDevolucao(int idUsuario, int idLivro) throws DevolucaoException {
          Pessoa usuarios = usuario.buscarPorId(idUsuario);
          Livro livro =  livroRepository.buscarPorId(idLivro);
          
          if (livro != null && usuario != null && !livro.isDisponivel() &&
            usuarios.getLivrosEmprestados().contains(livro)) {
            livro.setDisponivel(true);
            usuarios.removerLivro(livro);
            } else {
            throw new DevolucaoException("Não foi possível realizar a devolução.");
            }
}
      
     

    private LivroRepositorie livroRepository = new LivroRepositorie();

    public void cadastrarLivro(Livro livro) {
        livroRepository.cadastrar(livro);
    }

    public  List<Livro> listarLivros() {
        return livroRepository.buscarTodos();
    }

    public Livro buscarLivro(int id) {
        return livroRepository.buscarPorId(id);
    }
    
    
    /**  
     Bloco para realizar o empréstimo de livros. 
     Um livro pode ser emprestado a apenas um usuário,
     já 1 usuário pode pegar emprestado vários livros.
     
     Será exibido uma mensagem de erro caso o livro já tenha sido emprestado, 
     ou caso não exista livros cadastrados no sistema.
     A mesma mensagem será exibida no caso dos usuários.
     */
      public void realizarEmprestimo(int idLivro, int idUsuario) throws EmprestimoException{
        Livro livro = livroRepository.buscarPorId(idLivro);
        Pessoa usuarios = usuario.buscarPorId(idUsuario);
        
        if (livro != null && usuario != null && livro.isDisponivel() && usuarios.getPossuiVaga()) {
            livro.setDisponivel(false);
            usuarios.adicionarLivro(livro);
        } else {
            throw new EmprestimoException("Não foi possível realizar o empréstimo.\nEste livro não existe, ou já foi emprestado.\n");
}
}
    }
      
     
