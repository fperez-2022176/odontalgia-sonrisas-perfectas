package com.ferp.system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginDesarrolladorController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pwdClave;

    @FXML
    private void volverLogin(ActionEvent event) {
        // TODO: lógica para regresar a la pantalla de login principal
        System.out.println("CANCELAR presionado");
    }

    @FXML
    private void irAOpciones(ActionEvent event) {
        // TODO: lógica para validar usuario/clave e ir a opciones
        String usuario = txtUsuario.getText();
        String clave = pwdClave.getText();
        System.out.println("INICIAR SESION presionado. Usuario=" + usuario);
    }
}
