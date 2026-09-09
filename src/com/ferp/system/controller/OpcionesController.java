package com.ferp.system.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class OpcionesController {

    @FXML
    private StackPane contenido;

    public void mostrarInicio() throws IOException {

        Parent vista = FXMLLoader.load(
            getClass().getResource("/com/ferp/system/view/inicio.fxml")
        );

        contenido.getChildren().setAll(vista);
    }

    public void mostrarServicios() throws IOException {

        Parent vista = FXMLLoader.load(
            getClass().getResource("/com/ferp/system/view/descripcion.fxml")
        );

        contenido.getChildren().setAll(vista);
    }

    public void mostrarConfiguracion() throws IOException {

        Parent vista = FXMLLoader.load(
            getClass().getResource("/com/ferp/system/view/configuracion.fxml")
        );

        contenido.getChildren().setAll(vista);
    }
}
