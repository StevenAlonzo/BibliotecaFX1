/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.models.Usuario;
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
public class UsuarioController implements Initializable {
    
    @FXML
    private TableView<Usuario> tbvUsuarios;
    @FXML
    private TableColumn<Usuario, Integer> tbcCodigo;
    @FXML
    private TableColumn<Usuario, String> tbcNombre;
    @FXML
    private TableColumn<Usuario, Integer> tbcTelefono;
    @FXML
    private TableColumn<Usuario, String> tbcDireccion;
    
    private BibliotecaFX bibliotecaFX;

    public void setBibliotecaFX(BibliotecaFX bibliotecaFX) {
        this.bibliotecaFX = bibliotecaFX;
        tbvUsuarios.setItems(bibliotecaFX.getUsuariosList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcCodigo.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("codigoUsuario"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("telefono"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<Usuario, String>("direccion"));
        
        tbvUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
        tbvUsuarios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {        
            @Override
            public void changed(ObservableValue <? extends Usuario> observable,
                Usuario oldValue, Usuario newValue) {
            }
        });
    }
    
}
