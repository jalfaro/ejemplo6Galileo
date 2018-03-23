package edu.galileo.abcandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.galileo.abcandroid.data.Persona;
import edu.galileo.abcandroid.utiliudades.DBUtility;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText txtNombre, txtApellido;
    private Button btnGuardar, btnConsultar;
    private boolean isEditable;
    private Persona editablePersona;
    private DBUtility conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isEditable = false;
        setContentView(R.layout.activity_main);
        if (getIntent().hasExtra("Persona")) {
            editablePersona = (Persona)getIntent().getSerializableExtra("Persona");
            isEditable = true;
        }
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        btnConsultar = (Button) findViewById(R.id.btnConsulta);
        btnGuardar = (Button) findViewById(R.id.btnGrabar);
        if (isEditable ) {
            txtNombre.setText(editablePersona.getNombre());
            txtApellido.setText(editablePersona.getApellido());
            btnConsultar.setVisibility(View.GONE);
            btnGuardar.setText("EDITAR");
        }
        conn = new DBUtility(this);
        btnGuardar.setOnClickListener(this);
        btnConsultar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnConsulta) {
            Intent i = new Intent(this, ConstultaActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.btnGrabar) {
            Persona temp = new Persona();
            temp.setNombre(txtNombre.getText().toString());
            temp.setApellido(txtApellido.getText().toString());
            if (isEditable) {
                temp.setId(editablePersona.getId());
                conn.updatePersona(temp);
                finish();
                Toast.makeText(this, "Persona editada con exito", Toast.LENGTH_LONG).show();
            } else {
                conn.insertPersona(temp);
            }
            txtNombre.setText("");
            txtApellido.setText("");
        }
    }
}
