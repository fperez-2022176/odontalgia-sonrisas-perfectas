package com.ferp.system.controller;

import com.ferp.system.model.Tratamiento;
import com.ferp.system.repository.TratamientoEditado;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarTratamientos {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    private Tratamiento tratamiento;

    public void setTratamiento(Tratamiento tratamiento) {

        this.tratamiento = tratamiento;

        txtNombre.setText(tratamiento.getNombre());
        txtDescripcion.setText(tratamiento.getDescripcion());
        txtPrecio.setText(String.valueOf(tratamiento.getCosto()));
    }

    @FXML
    private void guardarCambios() {

        if (tratamiento == null) {
            System.out.println("No hay tratamiento seleccionado.");
            return;
        }

        try {

            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            double precio = Double.parseDouble(
                    txtPrecio.getText().trim()
            );

            boolean actualizado
                    = TratamientoEditado.actualizarTratamiento(
                            tratamiento.getIdServicio(),
                            nombre,
                            descripcion,
                            precio
                    );

            if (actualizado) {

                tratamiento.setNombre(nombre);
                tratamiento.setDescripcion(descripcion);
                tratamiento.setCosto(precio);

                System.out.println(
                        "Tratamiento actualizado correctamente."
                );

                Stage stage
                        = (Stage) txtNombre.getScene().getWindow();

                stage.close();

            } else {

                System.out.println(
                        "No se pudo actualizar el tratamiento."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "El precio debe ser un número válido."
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar() {

        Stage stage
                = (Stage) txtNombre.getScene().getWindow();

        stage.close();
    }
}
