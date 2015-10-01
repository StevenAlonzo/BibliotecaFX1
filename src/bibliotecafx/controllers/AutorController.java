/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.BibliotecaFX.CRUDOperation;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Autor;
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
    @FXML
    private void eliminarAutor() {
        int selectedIndex = tbvAutores.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Autor autorAEliminar = tbvAutores.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(Alert.AlertType.CONFIRMATION, "Biblioteca", null, "Deseas eliminar este autor");
            Optional<ButtonType> result = pregunta.showAndWait();
            if (result.get() == ButtonType.OK){
                if(Autor.eliminarAutor(autorAEliminar)){
                    tbvAutores.getItems().remove(selectedIndex);
                }
            }
        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca", null, "No ha seleccionado ningun alumno");
            error.showAndWait();
        }
    }
     @FXML
    private void agregarAutor(){
        Autor autorTemporal = new Autor();
        boolean okPresionado = bibliotecaFX.mostrarEditarAutor(autorTemporal, CRUDOperation.Create);
        if (okPresionado){
            bibliotecaFX.getAutoresList().add(autorTemporal);
        }
    }
     @FXML
    private void editarAutor() {
        Autor autorSeleccionado = tbvAutores.getSelectionModel().getSelectedItem();
        if (autorSeleccionado != null) {
            boolean okClicked = bibliotecaFX.mostrarEditarAutor(autorSeleccionado, CRUDOperation.Update);
            if (okClicked) {
                refrescarDatosAutor();
                
            }

        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "No ha seleccionado ningun alumno");
            error.showAndWait();
        }
    }
    private void refrescarDatosAutor(){
        int selectedIndex = tbvAutores.getSelectionModel().getSelectedIndex();
        tbvAutores.setItems(null);
        tbvAutores.layout();
        tbvAutores.setItems(bibliotecaFX.getAutoresList());
        tbvAutores.getSelectionModel().select(selectedIndex);
    }
}
