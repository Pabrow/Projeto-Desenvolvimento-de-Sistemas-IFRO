package DAO;
import Conexao.ConexaoSQL;
import Objetos.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ProdutoDAO {
    private Connection con = null;
    private ResultSet rsDados;  
    
    
    public void cadastrarProduto(Produto p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO produto (desc_prod, marca_prod, tamanho_prod, qtd_prod, valor_prod, tipo_prod) values (?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getDesc());
          stm.setString(2, p.getMarca());
          stm.setString(3, p.getTamanho());
          stm.setInt(4, p.getQuantidade());
          stm.setDouble(5, p.getValor());
          stm.setString(6, p.getTipo());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Produto p){
        con = ConexaoSQL.conectar();
        String sql = "update Produto set desc_prod =?, marca_prod =?, tamanho_prod =?, qtd_prod =?, valor_prod =?, tipo_prod =? where id_produto=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){ 
            stm.setString(1, p.getDesc());
            stm.setString(2, p.getMarca());
            stm.setString(3, p.getTamanho());
            stm.setInt(4, p.getQuantidade());
            stm.setDouble(5, p.getValor());
            stm.setString(6, p.getTipo());
            stm.setInt(7, p.getId_produto());
            System.out.println(sql);
            stm.execute();
            stm.close();
            System.out.println("EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from produto where id_produto=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O PRODUTO NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
    
    public List<Produto> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Produto> Lista = new ArrayList<>();
        String sql = "Select * from Produto";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Produto p = new Produto();
                p.setId_produto(Resultado.getInt("id_produto"));
                p.setDesc(Resultado.getString("desc_prod"));
                p.setMarca(Resultado.getString("marca_prod"));
                p.setTamanho(Resultado.getString("tamanho_prod"));
                p.setQuantidade(Resultado.getInt("qtd_prod"));
                p.setValor(Resultado.getDouble("valor_prod"));
                p.setTipo(Resultado.getString("tipo_prod"));
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
