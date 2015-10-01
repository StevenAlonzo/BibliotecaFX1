/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx;

import bibliotecafx.controllers.AutorController;
import bibliotecafx.controllers.BibliotecaViewController;
import bibliotecafx.controllers.DialogEditarAutorController;
import bibliotecafx.controllers.DialogEditarEjemplarController;
import bibliotecafx.controllers.DialogEditarLibroController;
import bibliotecafx.controllers.EjemplarController;
import bibliotecafx.controllers.LibroController;
import bibliotecafx.controllers.UsuarioController;
import bibliotecafx.helpers.Dialogs;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import bibliotecafx.models.Autor;
import bibliotecafx.models.Ejemplar;
import bibliotecafx.models.Libro;
import bibliotecafx.models.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
/**
 *
 * @author Edson
 */
public class BibliotecaFX extends Application {
    
     private Stage primaryStage;
    private BorderPane view;
   
    private ObservableList<Autor> autoresList = FXCollections.observableArrayList();
    private ObservableList<Ejemplar> ejemplaresList = FXCollections.observableArrayList();
    private ObservableList<Libro> librosList = FXCollections.observableArrayList();
    private ObservableList<Usuario> usuariosList = FXCollections.observableArrayList();
    public enum CRUDOperation {None, Create, Read, Update, Delete};
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BibliotecaFX");
       try {
            FXMLLoader loads = new FXMLLoader();
            loads.setLocation(BibliotecaFX.class.getResource("views/BibliotecaView.fxml"));
            view = (BorderPane) loads.load();
            BibliotecaViewController controller = loads.getController();
            controller.setBibliotecaFX(this);
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.show();
            } catch (IOException e) {
            e.printStackTrace();
        }
         this.ejemplaresList = Ejemplar.getEjemplaresList();
         this.autoresList = Autor.getAutoresList();
         this.usuariosList = Usuario.getUsuariosList();
         this.librosList = Libro.getLibrosList();
    }
    
    
public void mostrarAutores(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BibliotecaFX.class.getResource("views/Autor.fxml"));
            AnchorPane autores = (AnchorPane) loader.load();
            AutorController controlador = loader.getController();
            controlador.setBibliotecaFX(this);
            view.setCenter(autores);

        } catch (Exception e) {
            e.printStackTrace();
        }
         
       }

public void mostrarLibros(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BibliotecaFX.class.getResource("views/Libro.fxml"));
            AnchorPane libros = (AnchorPane) loader.load();
            LibroController controlador = loader.getController();
            controlador.setBibliotecaFX(this);
            view.setCenter(libros);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

public void mostrarEjemplares(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BibliotecaFX.class.getResource("views/Ejemplar.fxml"));
            AnchorPane ejemplares = (AnchorPane) loader.load();
            EjemplarController controlador = loader.getController();
            controlador.setBibliotecaFX(this);
            view.setCenter(ejemplares);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

public void mostrarUsuarios(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BibliotecaFX.class.getResource("views/Usuario.fxml"));
            AnchorPane usuarios = (AnchorPane) loader.load();
            UsuarioController controlador = loader.getController();
            controlador.setBibliotecaFX(this);
            view.setCenter(usuarios);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

 public Stage getprimaryStage() {
        return primaryStage;
    }

    public ObservableList<Autor> getAutoresList() {
        return autoresList;
    }
    public boolean mostrarEditarAutor(Autor autor, CRUDOperation operacion){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BibliotecaFX.class.getResource("views/DialogEditarAutor.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Autor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene escena = new Scene(page);
            dialogStage.setScene(escena);
            dialogStage.setResizable(false);
            DialogEditarAutorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOperacion(operacion);
            controller.setAutor(autor);
            
            dialogStage.showAndWait();
            
            return controller.fuePresionadoOk();
            
        }
        catch(Exception e){
            e.printStackTrace();
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Error al cargar el archivo FXML", e);
            error.showAndWait();
            return false;
        }
    }
    public ObservableList<Ejemplar> getEjemplaresList() {
        return ejemplaresList;
    }
    public boolean mostrarEditarEjemplar(Ejemplar ejemplar, CRUDOperation operacion){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BibliotecaFX.class.getResource("views/DialogEditarEjemplar.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Ejemplar");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene escena = new Scene(page);
            dialogStage.setScene(escena);
            dialogStage.setResizable(false);
            DialogEditarEjemplarController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOperacion(operacion);
            controller.setEjemplar(ejemplar);
            
            dialogStage.showAndWait();
            
            return controller.fuePresionadoOk();
            
        }
        catch(Exception e){
            e.printStackTrace();
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Error al cargar el archivo FXML", e);
            error.showAndWait();
            return false;
        }
    }
    public ObservableList<Libro> getLibrosList() {
        return librosList;
    }
     public boolean mostrarEditarLibro(Libro libro, CRUDOperation operacion){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BibliotecaFX.class.getResource("views/DialogEditarLibros.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Libro");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene escena = new Scene(page);
            dialogStage.setScene(escena);
            dialogStage.setResizable(false);
            DialogEditarLibroController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOperacion(operacion);
            controller.setLibro(libro);
            
            dialogStage.showAndWait();
            
            return controller.fuePresionadoOk();
            
        }
        catch(Exception e){
            e.printStackTrace();
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "CET Kinal", null, "Error al cargar el archivo FXML", e);
            error.showAndWait();
            return false;
        }
    }
    public ObservableList<Usuario> getUsuariosList() {
        return usuariosList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
