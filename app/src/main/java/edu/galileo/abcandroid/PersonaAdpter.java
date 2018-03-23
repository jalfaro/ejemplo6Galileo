package edu.galileo.abcandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.galileo.abcandroid.data.Persona;

/**
 * Created by jalfaro on 3/22/18.
 */

public class PersonaAdpter extends ArrayAdapter<Persona> {
    private Context context;
    private int layout;
    private List<Persona> list;
    public PersonaAdpter(@NonNull Context context, int resource, @NonNull List<Persona> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(layout, null);
        }
        if (list.get(position) != null) {
            TextView nombre, apellido;
            nombre = v.findViewById(R.id.nombre_persona);
            apellido = v.findViewById(R.id.apellido_persona);
            nombre.setText(list.get(position).getNombre());
            apellido.setText(list.get(position).getApellido());
        }
        return v;
    }
}
