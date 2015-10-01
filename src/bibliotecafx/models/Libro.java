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
public class Libro {
    private Integer codigoLibro;
    private String titulo;
    private String editorial;
    private Integer Isbn;
    private Integer paginas;

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    
    public Integer getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(Integer codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getISBN() {
        return Isbn;
    }

    public void setISBN(Integer ISBN) {
        this.Isbn = ISBN;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Libro() {
    }

    public Libro(Integer codigoLibro,String editorial, String titulo, Integer ssbn, Integer paginas) {
        this.codigoLibro = codigoLibro;
        this.titulo = titulo;
        this.Isbn = Isbn;
        this.paginas = paginas;
        this.editorial = editorial;
    }
    
    public static ObservableList<Libro> getLibrosList(){
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT * FROM Libro";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Libro libro = new Libro();
                
                libro.setCodigoLibro(rs.getInt("codigoLibro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setISBN(rs.getInt("Isbn"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setPaginas(rs.getInt("paginas"));
              
                libros.add(libro);
            }
        }catch(Exception e){
          
        }
        
        return libros;
    }
    public static boolean agregarLibro(Libro nuevoLibro){
        
        String insertSQL =  "INSERT INTO Libros (Titulo,ISBN,Editorial,Paginas) "
                + "VALUES (?, ?, ?,?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
           
            insertStatement.setString(2, nuevoLibro.getTitulo());
            insertStatement.setInt(3, nuevoLibro.getISBN());
            insertStatement.setString(4, nuevoLibro.getEditorial());
            insertStatement.setInt(5, nuevoLibro.getPaginas());
            
            insertStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al insertar un Libro", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
    public static boolean editarLibro(Libro nuevoLibro){
        String updateSQL = "UPDATE Libros"
                + " SET Titulo = ?,ISBN = ?, Editorial = ?, Paginas =? "
                + " WHERE Titulo = ?"; 
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(2, nuevoLibro.getTitulo());
            updateStatement.setInt(3, nuevoLibro.getISBN());
            updateStatement.setString(4, nuevoLibro.getEditorial());
            updateStatement.setInt(5, nuevoLibro.getPaginas());
           
            
            updateStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al actualizar un Libro", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
    public static boolean eliminarLibros(Libro Libro){
        String deleteSQL = "DELETE FROM Libros "
                + "WHERE Titulo = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setString(1, Libro.getTitulo());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al eliminar un Libro", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
}
