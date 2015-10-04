/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.helps;

import bibliotecafx.BibliotecaFX;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 
 */
public class DBHelper {
    
    private static BibliotecaFX bibliotecafx;
    private Connection connection;
    private static DBHelper instance;
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    public static void setBibliotecafx(BibliotecaFX bibliotecafx) {
        DBHelper.bibliotecafx = bibliotecafx;
    }
    

    public DBHelper() throws ClassNotFoundException, SQLException{
            try {
                Class.forName(DRIVER);
      String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Biblioteca;integratedSecurity=true";
      connection = DriverManager.getConnection(connectionUrl);
      System.out.println("Conectado.");
    } 
    catch (SQLException ex) 
    {
      System.out.println("Error.");
    }
    }
    
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if (instance == null){
            instance = new DBHelper();
        }
        return instance.connection;
    }
}
    
