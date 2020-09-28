package DAO;
import Conexao.ConexaoSQL;
import Objetos.Recebimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class RecebimentoDAO {
    private Connection con = null;
    private ResultSet rsDados;  
    
    
    public void cadastrarRecebimento(Recebimento p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Recebimento (valor_receb, parcela_receb, forma_receb, dataVenc_receb, id_caixa_fk, id_venda_fk) values (?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setDouble(1, p.getValor());
          stm.setDouble(2, p.getParcela());
          stm.setString(3, p.getFormaPag());
          stm.setString(4, p.getData_venc());
          stm.setInt(5, p.getId_caixa_fk());
          stm.setInt(6, p.getId_venda_fk());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Recebimento p){
        con = ConexaoSQL.conectar();
        String sql = "update Fornecedor set valor_receb =?, parcela_receb =?, forma_receb =?, dataVenc_receb =?, id_caixa_fk =?, id_venda_fk =? where id_recebimento=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){  
            stm.setDouble(1, p.getValor());
            stm.setDouble(2, p.getParcela());
            stm.setString(3, p.getFormaPag());
            stm.setString(4, p.getData_venc());
            stm.setInt(5, p.getId_caixa_fk());
            stm.setInt(6, p.getId_venda_fk());
            stm.setInt(7, p.getId_recebimento());
            stm.execute();
            stm.close();
            System.out.println("EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from recebimento where id_recebimento=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O RECEBIMENTO NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
    
    public List<Recebimento> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Recebimento> Lista = new ArrayList<>();
        String sql = "Select * from Despesa";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Recebimento p = new Recebimento();
                p.setId_recebimento(Resultado.getInt("id_recebimento"));
                p.setValor(Resultado.getDouble("valor_receb"));
                p.setParcela(Resultado.getDouble("parcela_receb"));
                p.setFormaPag(Resultado.getString("forma_receb"));
                p.setData_venc(Resultado.getString("dataVenc_receb"));
                p.setId_caixa_fk(Resultado.getInt("id_caixa_fk"));
                p.setId_venda_fk(Resultado.getInt("id_venda_fk"));
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
