package DAO;
import Conexao.ConexaoSQL;
import Objetos.Despesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class DespesaDAO {
    private Connection con = null;
    private ResultSet rsDados;  
    
    
    public void cadastrarDespesa(Despesa p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Despesa (desc_desp, valor_desp, data_desp, formaPag_desp) values (?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getDesc());
          stm.setDouble(2, p.getValor());
          stm.setString(3, p.getData());
          stm.setString(4, p.getFormaPag());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Despesa p){
        con = ConexaoSQL.conectar();
        String sql = "update despesa set desc_desp =?, valor_desp =?, data_desp =?, formaPag_desp =? where id_despesa=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getDesc());
            stm.setDouble(2, p.getValor());
            stm.setString(3, p.getData());
            stm.setString(4, p.getFormaPag());
            stm.setInt(5, p.getId_despesa());
            stm.execute();
            stm.close();
            System.out.println("EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from despesa where id_despesa=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR A DESPESA NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
    
    public List<Despesa> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Despesa> Lista = new ArrayList<>();
        String sql = "Select * from Despesa";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Despesa p = new Despesa();
                p.setId_despesa(Resultado.getInt("id_despesa"));
                p.setDesc(Resultado.getString("desc_desp"));
                p.setValor(Resultado.getDouble("valor_desp"));
                p.setData(Resultado.getString("data_desp"));
                p.setFormaPag(Resultado.getString("formaPag_desp"));
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
