
package repositorie;

import entity.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositorie {

    private List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;
    
    public void cadastrar(Livro livro) {
        livro.setIdLivro(proximoId++);
        livros.add(livro);
    }
      public List<Livro> buscarTodos() {
        return livros;
        
    }

     public Livro buscarPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getIdLivro() == id) {
                return livro;
            }
        }
        return null;
    }

}