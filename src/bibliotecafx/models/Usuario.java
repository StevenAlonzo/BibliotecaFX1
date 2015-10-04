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
public class Usuario {
    private Integer codigoUsuario;
    private String nombre;
    private String direccion;
    private int telefono;

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuario() {
    }

    public Usuario(Integer codigoUsuario, String nombre, String direccion, int telefono) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public static ObservableList<Usuario> getUsuariosList(){
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT * FROM Usuario";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setCodigoUsuario(rs.getInt("codigoUsuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setDireccion(rs.getString("Direccion"));
                usuario.setTelefono(rs.getInt("Telefono"));
              
                usuarios.add(usuario);
            }
        }catch(Exception e){
          
        }
        
        return usuarios;
    }
     public static boolean insertarUsuario(Usuario nuevoUsuario){
        
        String insertSQL =  "INSERT INTO Usuario (nombre,telefono,direccion) "
                + "VALUES (?, ?, ?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
           
            insertStatement.setString(2, nuevoUsuario.getNombre());
            insertStatement.setInt(5, nuevoUsuario.getTelefono());
            insertStatement.setString(4, nuevoUsuario.getDireccion());
            
            
            insertStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al insertar un usuario", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
    public static boolean editarAlumno(Usuario nuevoUsuario){
        String updateSQL = "UPDATE Usuario"
                + " SET nombre = ?,telefono = ?, direccion = ? "
                + " WHERE Nombre = ?";
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(1, nuevoUsuario.getNombre());
            updateStatement.setInt(4, nuevoUsuario.getTelefono());
            updateStatement.setString(3, nuevoUsuario.getDireccion());
           
            
            updateStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al actualizar un usuario", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
    public static boolean eliminarAlumno(Usuario Usuario){
        String deleteSQL = "DELETE FROM usuario "
                + "WHERE Nombre = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setString(1, Usuario.getNombre());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca", null, "Error al eliminar un Usuario", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
}
