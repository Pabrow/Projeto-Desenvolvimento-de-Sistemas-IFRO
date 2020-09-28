package DAO;
import Conexao.ConexaoSQL;
import Objetos.Caixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class CaixaDAO {
    private Connection con = null;
    
    
    public void cadastrarCaixa(Caixa p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO caixa (data_caixa, saldoIn_caixa, totalRec_caixa, totalPag_caixa, saldoFi_caixa, id_funcionario_fk) values (?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getData_caixa());
          stm.setDouble(2, p.getSaldoIn_caixa());
          stm.setDouble(3, p.getTotalRec_caixa());
          stm.setDouble(4, p.getTotalPag_caixa());
          stm.setDouble(5, p.getTotalFin_caixa());
          stm.setInt(6, p.getId_funcionario_fk());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Caixa p){
        con = ConexaoSQL.conectar();
        String sql = "update caixa set data_caixa =?, saldoIn_caixa =?, totalRec_caixa =?, totalPag_caixa =?, saldoFi_caixa =?, id_funcionario_fk =? where id_caixa=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getData_caixa());
            stm.setDouble(2, p.getSaldoIn_caixa());
            stm.setDouble(3, p.getTotalRec_caixa());
            stm.setDouble(4, p.getTotalPag_caixa());
            stm.setDouble(5, p.getTotalFin_caixa());
            stm.setInt(6, p.getId_funcionario_fk());
            stm.setInt(7, p.getId_caixa());
            stm.execute();
            System.out.println("EDITADO COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from caixa where id_caixa=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O CAIXA NO ID: "+id, "EXCLUIR", JOptionPane.YES_NO_OPTION);
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
    
    public List<Caixa> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Caixa> Lista = new ArrayList<>();
        String sql = "Select * from Caixa";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Caixa p = new Caixa();
                p.setId_caixa(Resultado.getInt("id_caixa"));
                p.setData_caixa(Resultado.getString("data_caixa"));
                p.setId_funcionario_fk(Resultado.getInt("id_funcionario_fk"));
                p.setSaldoIn_caixa(Resultado.getDouble("saldoIn_caixa"));
                p.setTotalFin_caixa(Resultado.getDouble("totalFin_caixa"));
                p.setTotalPag_caixa(Resultado.getDouble("totalPag_caixa"));
                p.setTotalRec_caixa(Resultado.getDouble("totalRec_caixa"));
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
