package edu.galileo.abcandroid.data;

import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jalfaro on 3/22/18.
 */

public class Persona implements Serializable{
    private int id;
    private String nombre;
    private String apellido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public static List<Persona> getListFromCursor (Cursor c) {
        List<Persona> lista = null;
        Persona temp;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            lista = new ArrayList<Persona>();
            while (!c.isAfterLast()) {
                temp = new Persona();
                temp.setNombre(c.getString(c.getColumnIndex("nombre")));
                temp.setApellido(c.getString(c.getColumnIndex("apellido")));
                temp.setId(c.getInt(c.getColumnIndex("id")));
                lista.add(temp);
                c.moveToNext();
            }
        }
        return lista;
    }
}
