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
import bibliotecafx.models.Ejemplar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author steven
 */
public class DialogEditarAutorController {
   
    @FXML
    private TextField txtNombre;
 
  
    
    
    private Stage dialogStage;
    private Autor autor;
    private boolean presionadoOk;
    private BibliotecaFX.CRUDOperation operacion;
    

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setOperacion(BibliotecaFX.CRUDOperation operacion) {
        this.operacion = operacion;
    }
    
 public void setAutor(Autor autor) {
        this.autor = autor;
        
        txtNombre.setText(autor.getNombre());
        if (operacion.equals(CRUDOperation.Update)){
            txtNombre.setDisable(true);
        }
        txtNombre.setText(autor.getNombre());
        
       
    }
    
    public boolean fuePresionadoOk(){
        return this.presionadoOk;
    }
    
    @FXML
    private void cambiosAceptados(){
        if(esAutorValido()){
            
            autor.setNombre(txtNombre.getText());
           
            if (operacion.equals(CRUDOperation.Create)){
                presionadoOk = Autor.agregarAutor(autor);
            }
            if (operacion.equals(CRUDOperation.Update)){
                presionadoOk = Autor.editarAutor(autor);
            }
            dialogStage.close();
        }
    }
    
    @FXML
    private void cambiosCancelados(){
        dialogStage.close();
    }
    
    private boolean esAutorValido(){
        if(txtNombre.getText() == null || txtNombre.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Carne no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtNombre.requestFocus();
            return false;
        }
        
        return true;
    }
}
