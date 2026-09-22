package com.ferp.system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginDesarrolladorController {

    // Botón para volver al Login
    public void volverLogin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/ferp/system/view/LoginView.fxml")
        );

        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene()
                .getWindow();

        // Cambiar a LoginView
        stage.setScene(new Scene(root));

        stage.setTitle("Login");

        stage.show();
    }


    // Botón "INICIAR SECCIÓN"
    public void irAOpciones(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/ferp/system/view/opciones.fxml")
        );

        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene()
                .getWindow();

        // Cambiar a opciones.fxml
        stage.setScene(new Scene(root));

        stage.setTitle("Opciones de Administrador");

        stage.show();
    }
}
