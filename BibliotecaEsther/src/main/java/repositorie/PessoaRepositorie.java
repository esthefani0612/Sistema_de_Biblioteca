package repositorie;

import entity.Pessoa;

import java.util.ArrayList;
import java.util.List;


public class PessoaRepositorie{
    
    
    private List<Pessoa> usuario = new ArrayList<>();
    private int proximoId = 1;
    
    
    public void cadastrarU(Pessoa usuarios) {
        usuarios.setId(proximoId++);
        usuario.add(usuarios);
    }
   
      public List<Pessoa> buscarTodos() {
        return usuario;
        
    }

     public Pessoa buscarPorId(int id) {
        for (Pessoa usuarios : usuario) {
            if (usuarios.getId() == id) {
                return usuarios;
            }
        }
        return null;
    }

    
}
