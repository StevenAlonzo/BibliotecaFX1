/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx.controllers;

import bibliotecafx.BibliotecaFX;
import bibliotecafx.helpers.Dialogs;
import bibliotecafx.models.Ejemplar;
import bibliotecafx.models.Libro;
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
public class DialogEditarLibroController {
    @FXML
    private TextField txtISBN;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtPaginas;
    
     private Stage dialogStage;
    private Libro libro;
    private boolean presionadoOk;
    private BibliotecaFX.CRUDOperation operacion;
    
     public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setOperacion(BibliotecaFX.CRUDOperation operacion) {
        this.operacion = operacion;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
        
        txtTitulo.setText(libro.getTitulo());
        if (operacion.equals(BibliotecaFX.CRUDOperation.Update)){
            txtTitulo.setDisable(true);
        }
        txtISBN.setText (Integer.toString(libro.getISBN()));
        txtTitulo.setText(libro.getTitulo());
       txtEditorial.setText(libro.getEditorial());
       txtPaginas.setText(Integer.toString(libro.getPaginas()));
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
        if(esLibroValido()){
            
            libro.setTitulo(txtTitulo.getText());
           
            if (operacion.equals(BibliotecaFX.CRUDOperation.Create)){
                presionadoOk = Libro.agregarLibro(libro);
            }
            if (operacion.equals(BibliotecaFX.CRUDOperation.Update)){
                presionadoOk = Libro.editarLibro(libro);
            }
            dialogStage.close();
        }
    }
    @FXML
    private void cambiosCancelados(){
        dialogStage.close();
    }
    
    private boolean esLibroValido(){
        if(txtTitulo.getText() == null || txtTitulo.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Carne no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtTitulo.requestFocus();
            return false;
        }
        if(txtISBN.getText() == null || txtISBN.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Nombre no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtISBN.requestFocus();
            return false;
        }
        if(txtEditorial.getText() == null || txtEditorial.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Nombre no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtEditorial.requestFocus();
            return false;
        }
        if(txtPaginas.getText() == null || txtPaginas.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Nombre no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtPaginas.requestFocus();
            return false;
        }
        return true;
    }
   
}
