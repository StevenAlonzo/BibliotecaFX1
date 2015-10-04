/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.models.Autor;
import bibliotecafx.models.Libro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author informatica
 */
public class LibroController implements Initializable {
    
    @FXML
    private TableView<Libro> tbvLibros;
    @FXML
    private TableColumn<Libro, Integer> tbcCodigo;
    @FXML
    private TableColumn<Libro, String> tbcTitulo;
    @FXML
    private TableColumn<Libro, Integer> tbcIsbn;
    @FXML
    private TableColumn<Libro, String> tbcEditorial;
    @FXML
    private TableColumn<Libro, Integer> tbcPaginas;
    
     private BibliotecaFX bibliotecaFX;

    public void setBibliotecaFX(BibliotecaFX bibliotecaFX) {
        this.bibliotecaFX = bibliotecaFX;
        tbvLibros.setItems(bibliotecaFX.getLibrosList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcCodigo.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("codigoLibro"));
        tbcTitulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
        tbcIsbn.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("ssbn"));
        tbcEditorial.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        tbcPaginas.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("paginas"));
        
        tbvLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
        tbvLibros.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Libro>() {        
            @Override
            public void changed(ObservableValue <? extends Libro> observable,
                Libro oldValue, Libro newValue) {
            }
        });
    }
    
}
