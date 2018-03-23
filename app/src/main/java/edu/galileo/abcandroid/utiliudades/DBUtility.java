package edu.galileo.abcandroid.utiliudades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import edu.galileo.abcandroid.data.Persona;

/**
 * Created by jalfaro on 3/22/18.
 */

public class DBUtility {
    public static final String DBNAME = "ejemplo";
    public static final int DBVER = 1;

    private DBHelper conn;
    private Context context;

    public DBUtility(Context context) {
        this.context = context;
        this.conn = new DBHelper(context);
    }

    public void insertPersona (Persona dato) {
        String query = "INSERT INTO nombre (nombre, apellido) VALUES ('" + dato.getNombre() + "','" + dato.getApellido() +"')";
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void updatePersona(Persona dato) {
        String query = "UPDATE nombre SET nombre = '" + dato.getNombre() + "', apellido = '" + dato.getApellido() +"' WHERE id = " + dato.getId();
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void deletePersona(Persona dato) {
        String query = "DELETE FROM  nombre  WHERE id = " + dato.getId();
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public List<Persona> getAllPersona() {
        List<Persona> datos;
        String query = "SELECT id, nombre, apellido FROM  nombre ";
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor c= null;
        c = db.rawQuery(query, null);
        datos = Persona.getListFromCursor(c);
        c.close();
        db.close();
        return datos;
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE nombre (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
