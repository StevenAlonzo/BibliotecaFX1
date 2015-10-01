/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.BibliotecaFX.CRUDOperation;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Ejemplar;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
    @FXML
    private void eliminarEjemplar() {
        int selectedIndex = tbvEjemplares.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Ejemplar ejemplarAEliminar = tbvEjemplares.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(Alert.AlertType.CONFIRMATION, "Biblioteca", null, "Deseas eliminar este autor");
            Optional<ButtonType> result = pregunta.showAndWait();
            if (result.get() == ButtonType.OK){
                if(Ejemplar.eliminarEjemplar(ejemplarAEliminar)){
                    tbvEjemplares.getItems().remove(selectedIndex);
                }
            }
        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca", null, "No ha seleccionado ningun alumno");
            error.showAndWait();
        }
    }
     @FXML
    private void agregarEjemplar(){
        Ejemplar EjemplarTemporal = new Ejemplar();
        boolean okPresionado = bibliotecaFX.mostrarEditarEjemplar(EjemplarTemporal, BibliotecaFX.CRUDOperation.Create);
        if (okPresionado){
            bibliotecaFX.getEjemplaresList().add(EjemplarTemporal);
        }
    }
    
     @FXML
    private void editarEjemplar() {
        Ejemplar ejemplarSeleccionado = tbvEjemplares.getSelectionModel().getSelectedItem();
        if (ejemplarSeleccionado != null) {
            boolean okClicked = bibliotecaFX.mostrarEditarEjemplar(ejemplarSeleccionado, CRUDOperation.Update);
            if (okClicked) {
                refrescarDatosEjemplar();
                
            }

        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "No ha seleccionado ningun alumno");
            error.showAndWait();
        }
    }
    private void refrescarDatosEjemplar(){
        int selectedIndex = tbvEjemplares.getSelectionModel().getSelectedIndex();
        tbvEjemplares.setItems(null);
        tbvEjemplares.layout();
        tbvEjemplares.setItems(bibliotecaFX.getEjemplaresList());
        tbvEjemplares.getSelectionModel().select(selectedIndex);
    }
}
