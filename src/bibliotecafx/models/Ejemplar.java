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
public class Ejemplar {
    private  Integer codigoEjemplar ;
    private String localizacion;
    private int codigoLibro;
    private int codigoUsuario;
    private String titulo;
    private String nombre;

    public int getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(int codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(Integer codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Ejemplar() {
    }

    public Ejemplar(Integer codigoEjemplar, String localizacion, int codigoLibro, int codigoUsuario, String titulo, String nombre) {
        this.codigoEjemplar = codigoEjemplar;
        this.localizacion = localizacion;
        this.codigoLibro = codigoLibro;
        this.codigoUsuario = codigoUsuario;
        this.titulo = titulo;
        this.nombre = nombre;
    }

    public static ObservableList<Ejemplar> getEjemplaresList(){
         ObservableList<Ejemplar> ejemplares = FXCollections.observableArrayList();
         
          try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT e.CodigoEjemplar, e.Localizacion, u.Nombre, l.Titulo\n" +
"FROM Ejemplar as e INNER JOIN Usuario as u on e.CodigoUsuario = u.CodigoUsuario INNER JOIN Libro as l on e.CodigoLibro = l.CodigoLibro ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Ejemplar ejemplar = new Ejemplar();
                
                ejemplar.setCodigoEjemplar(rs.getInt("CodigoEjemplar"));
                ejemplar.setLocalizacion(rs.getString("Localizacion"));
                ejemplar.setTitulo(rs.getString("titulo"));
                ejemplar.setNombre(rs.getString("nombre"));
              
                ejemplares.add(ejemplar);
            }
        }catch(Exception e){
           
        }
        
        return ejemplares;
    
    }
    
     public static boolean insertarEjemplar(Ejemplar nuevoEjemplar){
        
        String insertSQL =  "INSERT INTO Ejemplar(Localizacion,Titulo) "
                + "VALUES (?, ?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
           
            insertStatement.setString(3, nuevoEjemplar.getTitulo());
            insertStatement.setString(2, nuevoEjemplar.getTitulo());
            
            
            insertStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al insertar un Ejemplar", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
    public static boolean editarEjemplar(Ejemplar nuevoEjemplar){
        String updateSQL = "UPDATE Ejemplar"
                + " SET Localizacion = ?,Titulo = ? "
                + " WHERE Localizacion = ?"; 
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(3, nuevoEjemplar.getTitulo());
            updateStatement.setString(2, nuevoEjemplar.getLocalizacion());
            
           
            
            updateStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al actualizar un Ejemplar", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
    public static boolean eliminarEjemplar(Ejemplar Ejemplar){
        String deleteSQL = "DELETE FROM Ejemplar "
                + "WHERE Localizacion = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setString(2, Ejemplar.getLocalizacion());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al eliminar un Ejemplar", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
}
