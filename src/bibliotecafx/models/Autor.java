/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.models;

import bibliotecafx.helpers.DBHelper;
import bibliotecafx.helpers.Dialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author informatica
 */
public class Autor {
    private Integer codigoAutor;
    private String nombre;

    public int getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(Integer codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public Autor() {
    }

    public Autor(Integer codigoAutor, String nombre) {
        this.codigoAutor = codigoAutor;
        this.nombre = nombre;
    }
    
    public static ObservableList<Autor> getAutoresList(){
        ObservableList<Autor> autores = FXCollections.observableArrayList();
        
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT * FROM Autor";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Autor autor = new Autor();
                
                autor.setCodigoAutor(rs.getInt("codigoAutor"));
                autor.setNombre(rs.getString("Nombre"));
              
                autores.add(autor);
            }
        }catch(Exception e){
          
        }
        
        return autores;
    }
    
     public static boolean agregarAutor(Autor nuevoAutor){
        
        String insertSQL =  "INSERT INTO Autor (Nombre) "
                + "VALUES (?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
           
            insertStatement.setString(2, nuevoAutor.getNombre());
            
            
            insertStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al insertar un Autor", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
    public static boolean editarAutor(Autor nuevoAutor){
        String updateSQL = "UPDATE Autor"
                + " SET Nombre = ? "
                + " WHERE Nombre = ?"; 
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(2, nuevoAutor.getNombre());
            
           
            
            updateStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al actualizar un Autor", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
    public static boolean eliminarAutor(Autor Autor){
        String deleteSQL = "DELETE FROM Autor "
                + "WHERE Nombre = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setString(2, Autor.getNombre());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al eliminar un Autor", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
}
