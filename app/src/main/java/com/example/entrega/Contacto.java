package com.example.entrega;

public class Contacto {

    private String id;

    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    private String gusto;

    private String preferencia;

    public static final String[] GUSTOS = {
            "Música", "Cine", "Deportes", "Videojuegos", "Lectura",
            "Cocina", "Viajes", "Arte", "Fotografía", "Tecnología"
    };

    public static final String[] PREFERENCIAS = {
            "Pizza", "Sushi", "Hamburguesa", "Pasta", "Tacos",
            "Ensalada", "Pollo", "Mariscos", "Vegetariano", "Postres"
    };

    public Contacto(String id, String nombre, String apellido, String telefono,
                    String direccion, String gusto, String preferencia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.gusto = gusto;
        this.preferencia = preferencia;
    }

    public String getId()          { return id; }
    public String getNombre()      { return nombre; }
    public String getApellido()    { return apellido; }
    public String getTelefono()    { return telefono; }
    public String getDireccion()   { return direccion; }
    public String getGusto()       { return gusto; }
    public String getPreferencia() { return preferencia; }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + nombre + " " + apellido +
                "\nTel: " + telefono +
                " | Dir: " + direccion +
                "\nGusto: " + gusto +
                " | Pref: " + preferencia;
    }
}