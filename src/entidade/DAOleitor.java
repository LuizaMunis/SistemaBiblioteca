package entidade;

import controle.Leitor;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class DAOleitor extends DAO{
    
  public void inserir(Leitor l) throws Exception {
    try {
    abrirBanco();
    String query = "INSERT INTO leitor(id,nome,telefone,email) "
            + "values(null,?,?,?)";
    
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, l.getNome());
    pst.setString(2,l.getTelefone());
    pst.setString(3,l.getEmail());
    pst.execute();
    JOptionPane.showMessageDialog(null, "Concluido");
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }  
}
  
      public void editar(Leitor l) throws Exception {
        abrirBanco();
        //JOptionPane.showMessageDialog(null, l.getNome()+ l.getTelefone() + l.getEmail());
        String query = "UPDATE leitor set nome =?,telefone =?, email= ? where id=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, l.getNome());
        pst.setString(2, l.getTelefone());
        pst.setString(3, l.getEmail());
        pst.setInt(4, l.getId());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Concluido");
        fecharBanco();
    }
  
    public void deletar(Leitor l) throws Exception{
         abrirBanco();
         String query = "delete from leitor where id=?";
         pst=(PreparedStatement) con.prepareStatement(query);
         pst.setInt(1, l.getId());
         pst.execute();
        JOptionPane.showMessageDialog(null, "Leitor deletado!");
	fecharBanco();
     }
  
    public ArrayList<Leitor> listar () throws Exception {
       ArrayList<Leitor>leitores = new ArrayList<Leitor>();
         try{
         abrirBanco();  
         String query = "select id, nome, telefone, email FROM leitor";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Leitor l ;
         while (tr.next()){               
           l = new Leitor();
           l.setId(tr.getInt("Id"));
           l.setNome(tr.getString("nome"));
           l.setTelefone(tr.getString("telefone"));
           l.setEmail(tr.getString("email"));
           leitores.add(l);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
        } 
        return leitores;
    }
            
    public void pesquisar(Leitor l) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM leitor where id=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, l.getId());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                l.setId(tr.getInt("id"));
                l.setNome(tr.getString("nome"));
                l.setTelefone(tr.getString("telefone"));
                l.setEmail(tr.getString("email"));
            } else {
               JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
            

            
}
