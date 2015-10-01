/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.helpers;

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
    private static final String DB_SERVER = "localhost:1433";
    private static final String DB_INSTANCE = "USUARIO-PC\\SQLEXPRESS";
    private static final String DB_NAME = "BibliotecaInFX";
    
    public static void setBibliotecafx(BibliotecaFX bibliotecafx) {
        DBHelper.bibliotecafx = bibliotecafx;
    }
    

    public DBHelper() throws ClassNotFoundException, SQLException{
         
     Class.forName(DRIVER);
     connection = DriverManager.getConnection("jdbc:sqlserver://"+ DB_SERVER + ":instanceName"+DB_INSTANCE+":"+"databaseName"+DB_NAME+"user=sa"+ ";"+"password=Kinal2015");
    
    
      
    }
    
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if (instance == null){
            instance = new DBHelper();
        }
        return instance.connection;
    }
}
    
