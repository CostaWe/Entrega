package com.example.entrega;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestorDatos {

    private static final String ARCHIVO_USUARIOS = "usuarios.dat";

    public static void guardarUsuarios(Context context, ArrayList<Usuario> lista) {
        try {
            FileOutputStream fos = context.openFileOutput(ARCHIVO_USUARIOS, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Usuario> cargarUsuarios(Context context) {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(ARCHIVO_USUARIOS);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ArrayList<Usuario>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static void guardarContactos(Context context, ArrayList<Contacto> lista, String username) {
        try {
            FileOutputStream fos = context.openFileOutput("contactos_" + username + ".dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Contacto> cargarContactos(Context context, String username) {
        ArrayList<Contacto> lista = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput("contactos_" + username + ".dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ArrayList<Contacto>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}