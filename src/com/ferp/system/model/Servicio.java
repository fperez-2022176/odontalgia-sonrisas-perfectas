
package com.ferp.system.model;


public class Servicio {

    private String nameService;

    private String descripcion;

    private double precio;
 
    public Servicio(String nameService, String descripcion, double precio) {

        this.nameService = nameService;

        this.descripcion = descripcion;

        this.precio = precio;

    }
 
    public String getNameService() { return nameService; }

    public void setNameService(String nameService) { this.nameService = nameService; }
 
    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
 
    public double getPrecio() { return precio; }

    public void setPrecio(double precio) { this.precio = precio; }

}
 