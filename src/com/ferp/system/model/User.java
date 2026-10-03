package com.ferp.system.model;

public class User {

    private String name;
    private String lastname;
    private String password;
    private String id_cliente;

    public User() {

    }

    // Constructor para crear un cliente
    public User(String name, String lastname) {

        this.name = name;
        this.lastname = lastname;
    }

    // Constructor completo
    public User(
            String name,
            String lastname,
            String password,
            String id_cliente
    ) {

        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.id_cliente = id_cliente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
}
