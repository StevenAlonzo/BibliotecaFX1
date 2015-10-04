/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.models.Autor;
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
public class AutorController implements Initializable  {
    
    @FXML
    private TableView<Autor> tbvAutores;
    @FXML
    private TableColumn<Autor, Integer> tbcCodigo;
    @FXML
    private TableColumn<Autor, String> tbcNombre;
    
    private BibliotecaFX bibliotecaFX;

    public void setBibliotecaFX(BibliotecaFX bibliotecaFX) {
        this.bibliotecaFX = bibliotecaFX;
        tbvAutores.setItems(bibliotecaFX.getAutoresList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tbcCodigo.setCellValueFactory(new PropertyValueFactory<Autor, Integer>("codigoAutor"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombre"));
        
        tbvAutores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
        tbvAutores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Autor>() {        
            @Override
            public void changed(ObservableValue <? extends Autor> observable,
                Autor oldValue, Autor newValue) {
            }
        });
    }

}
