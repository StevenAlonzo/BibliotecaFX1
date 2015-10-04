/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.models.Ejemplar;
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
public class EjemplarController  implements Initializable{
    
    @FXML
    private TableView<Ejemplar> tbvEjemplares;
    
    @FXML
    private TableColumn<Ejemplar, Integer> tbcCodigo;
    
    @FXML
    private TableColumn<Ejemplar, String> tbcLocalizacion;
    
    @FXML
    private TableColumn<Ejemplar, String> tbcTitulo;
    
    @FXML
    private TableColumn<Ejemplar, String> tbcUsuario;
     
    private BibliotecaFX bibliotecaFX;

    public void setBibliotecaFX(BibliotecaFX bibliotecaFX) {
        this.bibliotecaFX = bibliotecaFX;
        tbvEjemplares.setItems(bibliotecaFX.getEjemplaresList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       tbcCodigo.setCellValueFactory(new PropertyValueFactory<Ejemplar, Integer>("CodigoEjemplar"));
       tbcLocalizacion.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("Localizacion"));
       tbcTitulo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
       tbcUsuario.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("nombre"));
        
        tbvEjemplares.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
        tbvEjemplares.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ejemplar>() {
            
            @Override
            public void changed(ObservableValue <? extends Ejemplar> observable,
                Ejemplar oldValue, Ejemplar newValue) {
            }
        });
       
    }
    
    
}
