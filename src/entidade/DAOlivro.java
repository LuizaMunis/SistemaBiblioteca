
package entidade;

import controle.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DAOlivro extends DAO {
    public void inserir(Livro l) throws Exception {
    try {
    abrirBanco();
    String query = "INSERT INTO livros(id,titulo,autor,genero,editora,quantidade,disponibilidade) "
            + "values(null,?,?,?,?,?,?)";
    
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, l.getTitulo());
    pst.setString(2, l.getAutor());
    pst.setString(3, l.getGenero());
    pst.setString(4, l.getEditora());
    pst.setInt(5, l.getQuantidade());   
    pst.setString(6, l.getDisponibilidade());
    pst.execute();
    JOptionPane.showMessageDialog(null, "Concluido");
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }  
}
  
      public void editar(Livro l) throws Exception {
        abrirBanco();
        //JOptionPane.showMessageDialog(null, l.getNome()+ l.getTelefone() + l.getEmail());
        String query = "UPDATE livros set titulo =?,autor =?, genero= ?,editora= ?,quantidade= ?,disponibilidade= ? where id=?";
        
        pst = (PreparedStatement) con.prepareStatement(query);
        
        pst.setString(1, l.getTitulo());
        pst.setString(2, l.getAutor());
        pst.setString(3, l.getGenero());
        pst.setString(4, l.getEditora());
        pst.setInt(5, l.getQuantidade());
        pst.setString(6, l.getDisponibilidade());
        pst.setInt(7, l.getId());
     
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Concluido");
        fecharBanco();
    }
  
    public void deletar(Livro l) throws Exception{
         abrirBanco();
         String query = "DELETE FROM livros WHERE id=?";
         pst=(PreparedStatement) con.prepareStatement(query);
         pst.setInt(1, l.getId());
         pst.execute();
        JOptionPane.showMessageDialog(null, "Livro deletado!");
	fecharBanco();
     }
  
   public ArrayList<Livro> listar() throws Exception {
    ArrayList<Livro> livros = new ArrayList<Livro>();
    try {
        abrirBanco();
        String query = "SELECT * FROM livros"; // Corrigir o nome da tabela para "livros"
        pst = (PreparedStatement) con.prepareStatement(query);
        ResultSet tr = pst.executeQuery();
        while (tr.next()) {
            Livro l = new Livro();
            l.setId(tr.getInt("Id"));
            l.setTitulo(tr.getString("titulo"));
            l.setAutor(tr.getString("autor"));
            l.setGenero(tr.getString("genero"));
            l.setEditora(tr.getString("editora"));
            l.setQuantidade(tr.getInt("quantidade"));
            l.setDisponibilidade(tr.getString("disponibilidade"));
            livros.add(l);
        }
        fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    return livros;
}
            
    public void pesquisar(Livro l) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM livros where id=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, l.getId());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                l.setId(tr.getInt("id"));
                l.setTitulo(tr.getString("titulo"));
                l.setAutor(tr.getString("autor"));
                l.setGenero(tr.getString("genero"));
                l.setEditora(tr.getString("editora"));
                l.setQuantidade(tr.getInt("quantidade"));
                l.setDisponibilidade(tr.getString("disponibilidade"));
                
            } else {
               JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
