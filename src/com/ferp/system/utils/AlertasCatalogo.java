package com.ferp.system.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public final class AlertasCatalogo {

    private AlertasCatalogo() {
    }

  
    public static void mostrarExito(String titulo, String mensaje) {
        mostrar(AlertType.INFORMATION, titulo, "Operación exitosa", mensaje);
    }

   
    public static void mostrarAdvertencia(String titulo, String mensaje) {
        mostrar(AlertType.WARNING, titulo, null, mensaje);
    }

   
    public static void mostrarError(String titulo, String mensaje) {
        mostrar(AlertType.ERROR, titulo, null, mensaje);
    }

    private static void mostrar(AlertType tipo, String titulo, String encabezado, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
