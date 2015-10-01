/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Autor;
import bibliotecafx.models.Ejemplar;
import bibliotecafx.models.Libro;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    @FXML
    private void eliminarLibro() {
        int selectedIndex = tbvLibros.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Libro LibroAEliminar = tbvLibros.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(Alert.AlertType.CONFIRMATION, "Biblioteca", null, "Deseas eliminar este autor");
            Optional<ButtonType> result = pregunta.showAndWait();
            if (result.get() == ButtonType.OK){
                if(Libro.eliminarLibros(LibroAEliminar)){
                    tbvLibros.getItems().remove(selectedIndex);
                }
            }
        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca", null, "No ha seleccionado ningun alumno");
            error.showAndWait();
        }
    }
     @FXML
    private void agregarLibro(){
        Libro LibroTemporal = new Libro();
        boolean okPresionado = bibliotecaFX.mostrarEditarLibro(LibroTemporal, BibliotecaFX.CRUDOperation.Create);
        if (okPresionado){
            bibliotecaFX.getLibrosList().add(LibroTemporal);
        }
    }
    
     @FXML
    private void editarLibro() {
        Libro libroSeleccionado = tbvLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado != null) {
            boolean okClicked = bibliotecaFX.mostrarEditarLibro(libroSeleccionado, BibliotecaFX.CRUDOperation.Update);
            if (okClicked) {
                refrescarDatosLibro();
                
            }

        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "No ha seleccionado ningun alumno");
            error.showAndWait();
        }
    }
    private void refrescarDatosLibro(){
        int selectedIndex = tbvLibros.getSelectionModel().getSelectedIndex();
        tbvLibros.setItems(null);
        tbvLibros.layout();
        tbvLibros.setItems(bibliotecaFX.getLibrosList());
        tbvLibros.getSelectionModel().select(selectedIndex);
    }
}
