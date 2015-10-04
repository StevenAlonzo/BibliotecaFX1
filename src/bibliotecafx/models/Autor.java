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
    
    
}
