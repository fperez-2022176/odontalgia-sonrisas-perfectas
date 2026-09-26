package com.ferp.system.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OpcionesController {

    @FXML
    private StackPane contenido;

    public void mostrarInicio() throws IOException {

        Parent vista = FXMLLoader.load(
                getClass().getResource("/com/ferp/system/view/inicio.fxml")
        );

        contenido.getChildren().setAll(vista);
    }

    
    public void mostrarConfiguracion() throws IOException {

        Parent vista = FXMLLoader.load(
                getClass().getResource("/com/ferp/system/view/configuracion.fxml")
        );

        contenido.getChildren().setAll(vista);
    }

  
    public void cerrarSesion(javafx.event.ActionEvent event) throws IOException {

        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Cerrar sesión");
        alerta.setHeaderText(null);
        alerta.setContentText("Se cerró la sesión.");

        
        alerta.showAndWait();

      
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/ferp/system/view/LoginView.fxml")
        );

        Parent root = loader.load();

       
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
}
