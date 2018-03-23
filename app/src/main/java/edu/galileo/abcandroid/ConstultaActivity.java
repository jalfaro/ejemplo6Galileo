package edu.galileo.abcandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import edu.galileo.abcandroid.data.Persona;
import edu.galileo.abcandroid.utiliudades.DBUtility;

/**
 * Created by jalfaro on 3/22/18.
 */

public class ConstultaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lista;
    private List<Persona> listPersona;
    private DBUtility conn;
    private AlertDialog dialog;
    private Persona selectedPersona;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_layout);
        lista = (ListView) findViewById(R.id.list);
        conn = new DBUtility(this);
        listPersona = conn.getAllPersona();
        lista.setAdapter(new PersonaAdpter(this, R.layout.row_persona_layout, listPersona));
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedPersona = ((Persona)lista.getItemAtPosition(position));
        dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage("Que accion quiere realizar");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "BORRAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                conn.deletePersona(selectedPersona);
                Toast.makeText(ConstultaActivity.this, "El elemento ha sido borrado", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "EDITAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(ConstultaActivity.this, MainActivity.class);
                i.putExtra("Persona", selectedPersona);
                startActivity(i);

            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listPersona = conn.getAllPersona();
        lista.setAdapter(new PersonaAdpter(this, R.layout.row_persona_layout, listPersona));
    }
}
