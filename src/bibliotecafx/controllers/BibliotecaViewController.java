/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author informatica
 */
public class BibliotecaViewController implements Initializable {
    private BibliotecaFX bibliotecaFX;

    public void setBibliotecaFX(BibliotecaFX bibliotecaFX) {
        this.bibliotecaFX = bibliotecaFX;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
            
     @FXML
    private void salirDeAplicacion(){
        System.exit(0);
    }
    
    @FXML
    private void mostrarAutores(){
        this.bibliotecaFX.mostrarAutores();
    }
    
    @FXML
    private void mostrarEjemplares(){
        this.bibliotecaFX.mostrarEjemplares();
    }
    
    @FXML
    private void mostrarUsuarios(){
         this.bibliotecaFX.mostrarUsuarios();
    }
    
    @FXML
    private void mostrarLibros(){
         this.bibliotecaFX.mostrarLibros();
    }
}
