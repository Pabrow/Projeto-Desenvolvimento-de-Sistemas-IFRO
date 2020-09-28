package DAO;
import Conexao.ConexaoSQL;
import Objetos.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class VendaDAO {
    private Connection con = null;
    private ResultSet rsDados;  
    
    
    public void cadastrarVenda(Venda p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Venda (valor_vend, data_vend, hora_vend, formaPag_vend, id_cliente_fk, id_funcionario_fk) values (?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(2, p.getData());
          stm.setDouble(1, p.getValor());
          stm.setString(3, p.getHora());
          stm.setString(4, p.getFormaPag());
          stm.setInt(5, p.getId_cliente_fk());
          stm.setInt(6, p.getId_funcionario_fk());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Venda p){
        con = ConexaoSQL.conectar();
        String sql = "update Venda set valor_vend =?, data_vend =?, hora_vend =?, formaPag_vend = ?, id_funcionario_fk =?, id_cliente_fk =? where id_vend=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){     
            stm.setDouble(1, p.getValor());
            stm.setString(2, p.getData());
            stm.setString(3, p.getHora());
            stm.setString(4, p.getFormaPag());
            stm.setInt(5, p.getId_funcionario_fk());
            stm.setInt(6, p.getId_cliente_fk());
            stm.setInt(7, p.getId_venda());
            stm.execute();
            stm.close();
            System.out.println("EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from venda where id_vend=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR A VENDA NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
    
    public List<Venda> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Venda> Lista = new ArrayList<>();
        String sql = "Select * from Venda";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Venda p = new Venda();
                p.setId_venda(Resultado.getInt("id_vend"));
                p.setValor(Resultado.getDouble("valor_vend"));
                p.setData(Resultado.getString("data_vend"));
                p.setHora(Resultado.getString("hora_vend"));
                p.setFormaPag(Resultado.getString("formaPag_vend"));
                p.setId_funcionario_fk(Resultado.getInt("id_funcionario_fk"));
                p.setId_cliente_fk(Resultado.getInt("id_cliente_fk"));
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
