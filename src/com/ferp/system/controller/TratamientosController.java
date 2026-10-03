package com.ferp.system.controller;

import com.ferp.system.model.User;

import javafx.fxml.FXML;

public class TratamientosController {

    private User user;

    public void setUser(User user) {

        this.user = user;

        System.out.println(
                "Usuario recibido: "
                + user.getId_cliente()
        );
    }

    @FXML
    public void initialize() {

        System.out.println(
                "ViewTratamientos cargada"
        );
    }
}

