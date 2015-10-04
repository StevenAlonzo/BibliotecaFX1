/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafx;

import bibliotecafx.controllers.AutorController;
import bibliotecafx.controllers.BibliotecaViewController;
import bibliotecafx.controllers.EjemplarController;
import bibliotecafx.controllers.LibroController;
import bibliotecafx.controllers.UsuarioController;
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
import javafx.scene.layout.AnchorPane;
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

    public ObservableList<Ejemplar> getEjemplaresList() {
        return ejemplaresList;
    }

    public ObservableList<Libro> getLibrosList() {
        return librosList;
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
