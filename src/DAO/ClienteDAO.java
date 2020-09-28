package DAO;
import Conexao.ConexaoSQL;
import Objetos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ClienteDAO {
    private Connection con = null;
    
    
    public void cadastrarCliente(Cliente p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Cliente (nome_cli, cpf_cli, sexo_cli, rg_cli, tel_cli, email_cli, end_cli, dataNasc_cli) values (?, ?, ?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getNome());
          stm.setString(2, p.getCpf());
          stm.setString(3, p.getSexo());
          stm.setString(4, p.getRg());
          stm.setString(5, p.getTelefone());
          stm.setString(6, p.getEmail());
          stm.setString(7, p.getEndereco());
          stm.setString(8, p.getDataNasc());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Cliente p){
        con = ConexaoSQL.conectar();
        String sql = "update cliente set nome_cli =?, cpf_cli =?, sexo_cli =?, rg_cli =?, tel_cli =?, email_cli = ?, end_cli = ?, dataNasc_cli =? where id_cliente=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCpf());
            stm.setString(3, p.getSexo());
            stm.setString(4, p.getRg());
            stm.setString(5, p.getTelefone());
            stm.setString(6, p.getEmail());
            stm.setString(7, p.getEndereco());
            stm.setString(8, p.getDataNasc());
            stm.setInt(9, p.getId_cliente());
            System.out.println(sql);
            stm.execute();
            System.out.println("EDITADO COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from cliente where id_cliente=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O CLIENTE NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
        if(opcao==0){
            try( PreparedStatement stm =con.prepareStatement(sql)){
                stm.setInt(1,id);
                stm.execute(); 
                stm.close();
                System.out.println("DELETADO COM SUCESSO");
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }   
        }else{
            System.out.println("Operação cancelada");
        }
    }
    
    public List<Cliente> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Cliente> Lista = new ArrayList<>();
        String sql = "Select * from Cliente";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Cliente p = new Cliente();
                p.setId_cliente(Resultado.getInt("id_cliente"));
                p.setNome(Resultado.getString("nome_cli"));
                p.setCpf(Resultado.getString("cpf_cli"));
                p.setSexo(Resultado.getString("sexo_cli"));
                p.setRg(Resultado.getString("rg_cli"));
                p.setTelefone(Resultado.getString("tel_cli"));
                p.setEmail(Resultado.getString("email_cli"));
                p.setEndereco(Resultado.getString("end_cli"));
                p.setDataNasc(Resultado.getString("dataNasc_cli"));
                Lista.add(p);
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }
}
