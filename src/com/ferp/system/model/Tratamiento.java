package com.ferp.system.model;

/**
 * Modelo de un tratamiento del catálogo.
 */
public class Tratamiento {

    // ID real de la base de datos
    private int idServicio;

    // Código que actualmente utiliza la interfaz
    private String codigo;

    private String nombre;
    private String descripcion;
    private double costo;

    /*
     * Constructor que ya utiliza tu controlador actual.
     * Lo dejamos para no romper el código existente.
     */
    public Tratamiento(
            String codigo,
            String nombre,
            String descripcion,
            double costo) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    /*
     * Constructor para cuando el tratamiento venga de MySQL.
     */
    public Tratamiento(
            int idServicio,
            String codigo,
            String nombre,
            String descripcion,
            double costo) {

        this.idServicio = idServicio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
