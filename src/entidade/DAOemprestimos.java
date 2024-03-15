
package entidade;

import controle.EmprestimoPesquisa;
import controle.Emprestimos;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class DAOemprestimos extends DAO {
    
  public void inserir(Emprestimos em) throws Exception {
    try {
    abrirBanco();
    String query = "INSERT INTO emprestimos(id,id_leitor, id_livro,data_emprestimo, data_devolucao,devolucao) "
            + "values(null,?,?,?,?,?)";
    
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setInt(1, em.getId_leitor());
    pst.setInt(2,em.getId_livro());
    pst.setString(3, em.getData_emprestimo());
    pst.setString(4, em.getData_devolucao());
    pst.setString(5,em.getDevolucao());
    pst.execute();
    JOptionPane.showMessageDialog(null, "Concluido");
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }  
}
  
public ArrayList<Emprestimos> listar() throws Exception {
    ArrayList<Emprestimos> emprestimos = new ArrayList<Emprestimos>();
    try {
        abrirBanco();
        String query = "SELECT * FROM emprestimos"; // Corrigir o nome da tabela para "livros"
        pst = (PreparedStatement) con.prepareStatement(query);
        ResultSet tr = pst.executeQuery();
        while (tr.next()) {
            Emprestimos em = new Emprestimos();
            em.setId(tr.getInt("Id"));
            em.setId_leitor(tr.getInt("id_leitor"));
            em.setId_livro(tr.getInt("id_livro"));
            em.setData_emprestimo(tr.getString("data_emprestimo"));
            em.setData_devolucao(tr.getString("data_devolucao"));
            em.setDevolucao(tr.getString("devolucao"));
            emprestimos.add(em);
        }
        fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    return emprestimos;
}

public ArrayList<EmprestimoPesquisa> listarJoin() throws Exception {
    ArrayList<EmprestimoPesquisa> emprestimos = new ArrayList<EmprestimoPesquisa>();
    try {
        abrirBanco();
        String query = "SELECT DISTINCT(l.nome) as Nome, COUNT(e.id) as QuantidadeLivrosEmprestado\n" +
"FROM emprestimos as e\n" +
"JOIN leitor as l ON l.id = e.id_leitor\n" +
"JOIN livros as li ON li.id = e.id_livro\n" +
"GROUP BY l.nome\n" +
"ORDER BY QuantidadeLivrosEmprestado DESC, Nome ASC\n" +
"LIMIT 10;"; // Corrigir o nome da tabela para "livros"
        pst = (PreparedStatement) con.prepareStatement(query);
        ResultSet tr = pst.executeQuery();
        while (tr.next()) {
            EmprestimoPesquisa ep = new EmprestimoPesquisa();
            ep.setNome(tr.getString("nome"));
            ep.setQuantidadeLivrosEmprestado(tr.getInt("quantidadeLivrosEmprestado"));
            emprestimos.add(ep);
        }
        fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }
    return emprestimos;
}
    
}
