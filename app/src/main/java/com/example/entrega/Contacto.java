package com.example.entrega;

import java.io.Serializable;
import java.util.ArrayList;

public class Contacto implements Serializable {

    private String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private ArrayList<String> gustos;
    private ArrayList<String> preferencias;

    public static final String[] OPCIONES_GUSTOS = {
            "Música", "Cine", "Deportes", "Videojuegos", "Lectura",
            "Cocina", "Viajes", "Arte", "Fotografía", "Tecnología"
    };

    public static final String[] OPCIONES_PREFERENCIAS = {
            "Pizza", "Sushi", "Hamburguesa", "Pasta", "Tacos",
            "Ensalada", "Pollo", "Mariscos", "Vegetariano", "Postres"
    };

    public Contacto(String id, String nombre, String apellido, String telefono,
                    String direccion, ArrayList<String> gustos, ArrayList<String> preferencias) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.gustos = gustos;
        this.preferencias = preferencias;
    }

    public String getId()                        { return id; }
    public String getNombre()                    { return nombre; }
    public String getApellido()                  { return apellido; }
    public String getTelefono()                  { return telefono; }
    public String getDireccion()                 { return direccion; }
    public ArrayList<String> getGustos()         { return gustos; }
    public ArrayList<String> getPreferencias()   { return preferencias; }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + nombre + " " + apellido;
    }
}