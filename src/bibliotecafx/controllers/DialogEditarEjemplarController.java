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
public class DialogEditarEjemplarController {
    
    @FXML
    private TextField txtLocalizacion;
    @FXML
    private TextField txtTitulo;
    
     private Stage dialogStage;
    private Ejemplar ejemplar;
    private boolean presionadoOk;
    private BibliotecaFX.CRUDOperation operacion;
    
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setOperacion(BibliotecaFX.CRUDOperation operacion) {
        this.operacion = operacion;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
        
        txtLocalizacion.setText(ejemplar.getLocalizacion());
        if (operacion.equals(CRUDOperation.Update)){
            txtLocalizacion.setDisable(true);
        }
        txtLocalizacion.setText(ejemplar.getNombre());
        txtTitulo.setText(ejemplar.getTitulo());
       
    }
    
    public boolean fuePresionadoOk(){
        return this.presionadoOk;
    }
    
    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
     @FXML
    private void cambiosAceptados(){
        if(esEjemplarValido()){
            
            ejemplar.setLocalizacion(txtLocalizacion.getText());
           
            if (operacion.equals(BibliotecaFX.CRUDOperation.Create)){
                presionadoOk = Ejemplar.insertarEjemplar(ejemplar);
            }
            if (operacion.equals(BibliotecaFX.CRUDOperation.Update)){
                presionadoOk = Ejemplar.editarEjemplar(ejemplar);
            }
            dialogStage.close();
        }
    }
    @FXML
    private void cambiosCancelados(){
        dialogStage.close();
    }
    
    private boolean esEjemplarValido(){
        if(txtLocalizacion.getText() == null || txtLocalizacion.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Carne no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtLocalizacion.requestFocus();
            return false;
        }
        if(txtTitulo.getText() == null || txtTitulo.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Nombre no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtTitulo.requestFocus();
            return false;
        }
        
        return true;
    }
   
    
}
