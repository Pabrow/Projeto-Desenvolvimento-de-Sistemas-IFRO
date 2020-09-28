package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoSQL {
    private static Connection con;
    private static final String url="jdbc:mysql://localhost:3306/BD_mecanica?useTimezone=true&serverTimezone=UTC";//3306 é desnecessário se for localhost
    private static final String usuario="root";
    private static final String senha="root";
 
    public static Connection conectar() {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver"); 
          con =DriverManager.getConnection(url,usuario,senha);     
          System.out.println("Conectado com Sucesso!!");
        } catch (Exception e) {
            System.out.println("A conexão falhou!!!   "+e.getMessage());
        }
        return con;
    }
}
