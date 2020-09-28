package DAO;
import Conexao.ConexaoSQL;
import Objetos.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class PagamentoDAO {
    private Connection con = null;
    private ResultSet rsDados;  
    
    
    public void cadastrarPagamento(Pagamento p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO pagamento (valor_pag, parcela_pag, data_pag, forma_pag, id_compra_fk, id_despesa_fk, id_caixa_fk) ) values (?, ?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setDouble(1, p.getValor());
          stm.setDouble(2, p.getParcela());
          stm.setString(3, p.getData());
          stm.setString(4, p.getFormaPag());
          stm.setInt(5, p.getId_compra_fk());
          stm.setInt(6, p.getId_despesa_fk());
          stm.setInt(7, p.getId_despesa_fk());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Pagamento p){
        con = ConexaoSQL.conectar();
        String sql = "update pagamento set valor_pag =?, parcela_pag =?, data_pag =?, forma_pag =?, id_compra_fk =?, id_despesa_fk =?, id_caixa_fk =? where id_pagamento=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setDouble(1, p.getValor());
            stm.setDouble(2, p.getParcela());
            stm.setString(3, p.getData());
            stm.setString(4, p.getFormaPag());
            stm.setInt(5, p.getId_compra_fk());
            stm.setInt(6, p.getId_despesa_fk());
            stm.setInt(7, p.getId_despesa_fk());
            stm.setInt(8, p.getId_pagamento());
            stm.execute();
            stm.close();
            System.out.println("EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from pagamento where id_pagamento=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O PAGAMENTO NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
    
    public List<Pagamento> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Pagamento> Lista = new ArrayList<>();
        String sql = "Select * from Despesa";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Pagamento p = new Pagamento();
                p.setId_pagamento(Resultado.getInt("id_pagamento"));
                p.setValor(Resultado.getDouble("valor_pag"));
                p.setParcela(Resultado.getDouble("parcela_pag"));
                p.setData(Resultado.getString("data_pag"));
                p.setFormaPag(Resultado.getString("forma_pag"));
                p.setId_caixa_fk(Resultado.getInt("id_caixa_fk"));
                p.setId_compra_fk(Resultado.getInt("id_compra_fk"));
                p.setId_despesa_fk(Resultado.getInt("id_despesa_fk"));
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
