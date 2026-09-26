package com.ferp.system.controller;

import com.ferp.system.controller.EditarTratamientos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ferp.system.model.Tratamiento;
import com.ferp.system.model.TratamientoData;
import com.ferp.system.repository.TratamientoEliminado;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MostrarInfoController implements Initializable {

    @FXML
    private TableView<Tratamiento> tablaServicios;
    @FXML
    private TableColumn<Tratamiento, String> colServicio;
    @FXML
    private TableColumn<Tratamiento, String> colDescripcion;
    @FXML
    private TableColumn<Tratamiento, Double> colCosto;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colServicio.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );
        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion")
        );
        colCosto.setCellValueFactory(
                new PropertyValueFactory<>("costo")
        );

        ObservableList<Tratamiento> tratamientos
                = TratamientoData.getTratamientos();

        tablaServicios.setItems(tratamientos);
    }

    @FXML
    private void crearTratamiento(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/ferp/system/view/CrearTratamiento.fxml")
            );

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Crear Tratamiento");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editarTratamiento(ActionEvent event) {

        Tratamiento seleccionado
                = tablaServicios.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            System.out.println("Debe seleccionar un tratamiento.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/ferp/system/view/Editar.fxml"
                    )
            );

            Parent root = loader.load();
            EditarTratamientos controller
                    = loader.getController();

            controller.setTratamiento(seleccionado);
            Stage stage = new Stage();
            stage.setTitle("Editar Tratamiento");
            stage.setScene(new Scene(root));

            stage.showAndWait();

            tablaServicios.setItems(TratamientoData.getTratamientos());
            tablaServicios.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
private void eliminarTratamiento(ActionEvent event) {

    Tratamiento seleccionado =
            tablaServicios.getSelectionModel().getSelectedItem();

    if (seleccionado == null) {

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Eliminar tratamiento");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "Debe seleccionar un tratamiento para eliminar."
        );
        alerta.showAndWait();

        return;
    }

    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
    confirmacion.setTitle("Eliminar tratamiento");
    confirmacion.setHeaderText("¿Está seguro de eliminar este tratamiento?");
    confirmacion.setContentText(
            "Tratamiento: " + seleccionado.getNombre()
    );

    ButtonType botonBorrar = new ButtonType("BORRAR");
    ButtonType botonCancelar = new ButtonType(
            "CANCELAR",
            ButtonBar.ButtonData.CANCEL_CLOSE
    );

    confirmacion.getButtonTypes().setAll(
            botonBorrar,
            botonCancelar
    );

    Optional<ButtonType> resultado =
            confirmacion.showAndWait();

    if (resultado.isPresent()
            && resultado.get() == botonBorrar) {
        boolean eliminado =
                TratamientoEliminado.eliminarTratamiento(
                        seleccionado.getIdServicio()
                );
        if (eliminado) {
            tablaServicios.getItems().remove(seleccionado);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Tratamiento eliminado");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "El tratamiento se eliminó correctamente."
            );
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se pudo eliminar el tratamiento.");
            alerta.setContentText(
                    "Ocurrió un error al eliminar el tratamiento de la base de datos."
            );
            alerta.showAndWait();
        }
    }
}
    
}
