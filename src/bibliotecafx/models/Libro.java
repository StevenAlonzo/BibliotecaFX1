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
public class Libro {
    private Integer codigoLibro;
    private String titulo;
    private String editorial;
    private Integer ssbn;
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

    public Integer getSsbn() {
        return ssbn;
    }

    public void setSsbn(Integer ssbn) {
        this.ssbn = ssbn;
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
        this.ssbn = ssbn;
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
                libro.setSsbn(rs.getInt("ssbn"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setPaginas(rs.getInt("paginas"));
              
                libros.add(libro);
            }
        }catch(Exception e){
          
        }
        
        return libros;
    }
    
}
