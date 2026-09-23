package com.ferp.system.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TratamientoData {

    private static final ObservableList<Tratamiento> tratamientos =
            FXCollections.observableArrayList();

    static {
        tratamientos.add(new Tratamiento(
                "TRT-001",
                "Limpieza profunda",
                "Eliminación de placa bacteriana y sarro en toda la dentadura",
                1200.00
        ));

        tratamientos.add(new Tratamiento(
                "TRT-002",
                "Extracción",
                "Procedimiento para remover una pieza dental dañada",
                500.00
        ));

        tratamientos.add(new Tratamiento(
                "TRT-003",
                "Ortodoncia",
                "Corrección de la posición dental mediante aparatología",
                18000.00
        ));
    }

    public static ObservableList<Tratamiento> getTratamientos() {
        return tratamientos;
    }
}
