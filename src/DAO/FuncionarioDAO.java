package DAO;
import Conexao.ConexaoSQL;
import Objetos.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class FuncionarioDAO {
    private Connection con = null;
    private ResultSet rsDados;  
    
    
    public void cadastrarFuncionario(Funcionario p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Funcionario (nome_func, cpf_func, senha_func, rg_func, tel_func, email_func, sexo_func, salario_func, funcao_func) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getNome());
          stm.setString(2, p.getCpf());
          stm.setString(3, p.getSenha());
          stm.setString(4, p.getRg());
          stm.setString(5, p.getTelefone());
          stm.setString(6, p.getEmail());
          stm.setString(7, p.getSexo());
          stm.setDouble(8, p.getSalario());
          stm.setString(9, p.getFuncao());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Funcionario p){
        con = ConexaoSQL.conectar();
        String sql = "update funcionario set nome_func =?, cpf_func =?, senha_func =?,  rg_func =?, tel_func =?, email_func =?, sexo_func = ?, salario_func = ?, funcao_func =? where id_funcionario=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCpf());
            stm.setString(3, p.getSenha());
            stm.setString(4, p.getRg());
            stm.setString(5, p.getTelefone());
            stm.setString(6, p.getEmail());
            stm.setString(7, p.getSexo());
            stm.setDouble(8, p.getSalario());
            stm.setString(9, p.getFuncao());
            stm.setInt(10, p.getId_funcionario());
            stm.execute();
            System.out.println("EDITADO COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from funcionario where id_funcionario=?";
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
    public List<Funcionario> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Funcionario> Lista = new ArrayList<>();
        String sql = "Select * from Funcionario";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Funcionario p = new Funcionario();
                p.setId_funcionario(Resultado.getInt("id_funcionario"));
                p.setNome(Resultado.getString("nome_func"));
                p.setSenha(Resultado.getString("senha_func"));
                p.setCpf(Resultado.getString("cpf_func"));
                p.setSexo(Resultado.getString("sexo_func"));
                p.setRg(Resultado.getString("rg_func"));
                p.setTelefone(Resultado.getString("tel_func"));
                p.setEmail(Resultado.getString("email_func"));
                p.setSalario(Resultado.getDouble("salario_func"));
                p.setFuncao(Resultado.getString("funcao_func"));
                Lista.add(p);
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }
    
    public Funcionario entrarFuncionario(String senha, String cpf){
        con = ConexaoSQL.conectar();
        Funcionario p = new Funcionario();
        String sql = "Select * from funcionario where cpf_func = ? and senha_func = ?";
        try (PreparedStatement stm =con.prepareStatement(sql)){  
            stm.setString(1, cpf);
            stm.setString(2, senha);
            ResultSet Resultado  = stm.executeQuery();
            if(Resultado.next()){
                p.setId_funcionario(Resultado.getInt("id_funcionario"));
                p.setNome(Resultado.getString("nome_func"));
                p.setSenha(Resultado.getString("senha_func"));
                p.setCpf(Resultado.getString("cpf_func"));
                p.setSexo(Resultado.getString("sexo_func"));
                p.setRg(Resultado.getString("rg_func"));
                p.setEmail(Resultado.getString("email_func"));
                p.setSalario(Resultado.getDouble("salario_func"));
                p.setFuncao(Resultado.getString("funcao_func"));
            }else{
                System.out.println("Não foi possível encontrar o usuario.");
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Erro:");
            throw new RuntimeException(e);
        }
        return p;
    }
}
