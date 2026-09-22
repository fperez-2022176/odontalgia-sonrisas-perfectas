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

    // Mostrar la vista de Inicio
    public void mostrarInicio() throws IOException {

        Parent vista = FXMLLoader.load(
            getClass().getResource("/com/ferp/system/view/inicio.fxml")
        );

        contenido.getChildren().setAll(vista);
    }

    // Mostrar la vista de Configuración
    public void mostrarConfiguracion() throws IOException {

        Parent vista = FXMLLoader.load(
            getClass().getResource("/com/ferp/system/view/configuracion.fxml")
        );

        contenido.getChildren().setAll(vista);
    }

    // Cerrar sesión y regresar al Login
    public void cerrarSesion(javafx.event.ActionEvent event) throws IOException {

        // Mostrar mensaje de confirmación
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Cerrar sesión");
        alerta.setHeaderText(null);
        alerta.setContentText("Se cerró la sesión.");

        // Esperar a que el usuario presione OK
        alerta.showAndWait();

        // Cargar LoginView.fxml
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/ferp/system/view/LoginView.fxml")
        );

        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        // Cambiar a LoginView
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
}
