/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.models;

import bibliotecafx.helps.DBHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    
}
