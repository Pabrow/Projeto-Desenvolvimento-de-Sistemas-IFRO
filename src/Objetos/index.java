package Objetos;
import Conexao.ConexaoSQL;
import DAO.CompraDAO;
import DAO.FuncionarioDAO;
import Formularios.FLogin;
import javax.swing.JOptionPane;
public class index {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Compra p = new Compra();
        p.setData("2020-12-12");
        p.setFormaPag("asd");
        p.setId_fornecedor_fk(2);
        p.setId_produto_fk(5);
        p.setQuantItens(1);
        p.setValor(12);
        CompraDAO pDAO = new CompraDAO();
        pDAO.cadastrarCompra(p);
        //FLogin fl = new FLogin();
        //fl.setVisible(true);
    }    
}
