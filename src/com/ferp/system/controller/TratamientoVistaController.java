package com.ferp.system.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ferp.system.model.Tratamiento;
import com.ferp.system.utils.AlertasCatalogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TratamientoVistaController implements Initializable {

    @FXML private TableView<Tratamiento> tablaTratamientos;
    @FXML private TableColumn<Tratamiento, String> colCodigo;
    @FXML private TableColumn<Tratamiento, String> colNombre;
    @FXML private TableColumn<Tratamiento, String> colDescripcion;
    @FXML private TableColumn<Tratamiento, Double> colCosto;

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtCosto;

    @FXML private Label lblAvisoAdmin;

    private final ObservableList<Tratamiento> tratamientos = FXCollections.observableArrayList();

    private Tratamiento tratamientoSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        // Sin login ni aviso de rol: el label queda oculto.
        if (lblAvisoAdmin != null) {
            lblAvisoAdmin.setVisible(false);
            lblAvisoAdmin.setManaged(false);
        }

        cargarTratamientosIniciales();
        tablaTratamientos.setItems(tratamientos);

        tablaTratamientos.getSelectionModel().selectedItemProperty()
                .addListener((obs, anterior, seleccionado) -> cargarEnFormulario(seleccionado));
    }

    /** Los 3 tratamientos base del catálogo. */
    private void cargarTratamientosIniciales() {
        tratamientos.add(new Tratamiento("TRT-001", "Limpieza profunda",
                "Eliminación de placa bacteriana y sarro en toda la dentadura", 1200.00));
        tratamientos.add(new Tratamiento("TRT-002", "Extracción",
                "Procedimiento para remover una pieza dental dañada", 500.00));
        tratamientos.add(new Tratamiento("TRT-003", "Ortodoncia",
                "Corrección de la posición dental mediante aparatología", 18000.00));
    }

    @FXML
    private void onGuardar(MouseEvent event) {
        try {
            String codigo = txtCodigo.getText() == null ? "" : txtCodigo.getText().trim();
            String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText() == null ? "" : txtDescripcion.getText().trim();
            String costoTexto = txtCosto.getText() == null ? "" : txtCosto.getText().trim();

            if (codigo.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || costoTexto.isEmpty()) {
                AlertasCatalogo.mostrarAdvertencia("Campos incompletos", "Todos los campos son obligatorios.");
                return;
            }

            if (existeCodigo(codigo)) {
                AlertasCatalogo.mostrarAdvertencia("Código duplicado",
                        "Ya existe un tratamiento con el código '" + codigo + "'.");
                return;
            }

            double costo;
            try {
                costo = Double.parseDouble(costoTexto.replace(",", "."));
                if (costo <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                AlertasCatalogo.mostrarAdvertencia("Costo inválido",
                        "El costo debe ser un número mayor a cero. Ejemplo: 850.00");
                return;
            }

            Tratamiento nuevo = new Tratamiento(codigo, nombre, descripcion, costo);
            tratamientos.add(nuevo);
            tablaTratamientos.getSelectionModel().select(nuevo);
            tablaTratamientos.scrollTo(nuevo);

            // Mensaje mostrando exactamente lo que se agregó
            String detalle = String.format(
                    "Código: %s%nNombre: %s%nDescripción: %s%nCosto: Q%.2f",
                    nuevo.getCodigo(), nuevo.getNombre(), nuevo.getDescripcion(), nuevo.getCosto());
            AlertasCatalogo.mostrarExito("Tratamiento agregado", detalle);

            limpiarCampos();

        } catch (Exception e) {
            AlertasCatalogo.mostrarError("Error al guardar", "Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    @FXML
    private void onActualizar(MouseEvent event) {
        try {
            if (tratamientoSeleccionado == null) {
                AlertasCatalogo.mostrarAdvertencia("Sin selección",
                        "Selecciona un tratamiento de la tabla antes de actualizar.");
                return;
            }

            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String costoTexto = txtCosto.getText().trim();

            if (codigo.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || costoTexto.isEmpty()) {
                AlertasCatalogo.mostrarAdvertencia("Campos incompletos", "Todos los campos son obligatorios.");
                return;
            }

            double costo;
            try {
                costo = Double.parseDouble(costoTexto.replace(",", "."));
                if (costo <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                AlertasCatalogo.mostrarAdvertencia("Costo inválido",
                        "El costo debe ser un número mayor a cero. Ejemplo: 850.00");
                return;
            }

            tratamientoSeleccionado.setCodigo(codigo);
            tratamientoSeleccionado.setNombre(nombre);
            tratamientoSeleccionado.setDescripcion(descripcion);
            tratamientoSeleccionado.setCosto(costo);

            tablaTratamientos.refresh();
            AlertasCatalogo.mostrarExito("Tratamiento actualizado",
                    "Se actualizó correctamente '" + nombre + "'.");

            limpiarCampos();

        } catch (Exception e) {
            AlertasCatalogo.mostrarError("Error al actualizar", "Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    @FXML
    private void onLimpiar(MouseEvent event) {
        limpiarCampos();
    }

    @FXML
    private void onVolver(MouseEvent event) {
        // Sin pantalla de login: este proyecto solo muestra el catálogo,
        // así que "Volver" simplemente cierra la ventana.
        ((javafx.stage.Stage) tablaTratamientos.getScene().getWindow()).close();
    }

    private boolean existeCodigo(String codigo) {
        return tratamientos.stream().anyMatch(t -> t.getCodigo().equalsIgnoreCase(codigo));
    }

    private void cargarEnFormulario(Tratamiento t) {
        this.tratamientoSeleccionado = t;
        if (t == null) {
            return;
        }
        txtCodigo.setText(t.getCodigo());
        txtNombre.setText(t.getNombre());
        txtDescripcion.setText(t.getDescripcion());
        txtCosto.setText(String.valueOf(t.getCosto()));
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtCosto.clear();
        tratamientoSeleccionado = null;
        tablaTratamientos.getSelectionModel().clearSelection();
    }
}
