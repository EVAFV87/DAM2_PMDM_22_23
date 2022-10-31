package es.alejandra.android.ejemplorecyclerview.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import es.alejandra.android.ejemplorecyclerview.R;
import es.alejandra.android.ejemplorecyclerview.adapters.AdaptadorEquipos;
import es.alejandra.android.ejemplorecyclerview.modelo.Equipo;

public class MainActivity extends AppCompatActivity {
    // DATOS
    static List<Equipo> listaEquipos;

    // VIEWS
    private RecyclerView rvListaEquipos;

    // ADAPTADORES
    private AdaptadorEquipos adaptadorEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();
        // obtiene las referencias a las vistas XML
        initReferences();
        configurarRecyclerView();

    }


    /**
     * Método que carga los datos en el arrayList de equipos
     */
    private void cargarDatos() {
        // Extraer datos del XML
        String[] nombres = getResources().getStringArray(R.array.nombre_equipo);
        int[] puntos = getResources().getIntArray(R.array.puntos_equipo);
        TypedArray array = getResources().obtainTypedArray(R.array.escudo_equipo);
        Drawable[] imagenesEscudos = new Drawable[array.length()];
        for (int i = 0; i < imagenesEscudos.length; i++) {
            imagenesEscudos[i] = array.getDrawable(i);
        }
        // rellenar ArrayList Equipos
        listaEquipos = new ArrayList<>();
        for (int i = 0; i < imagenesEscudos.length; i++) {
            listaEquipos.add(new Equipo(nombres[i], imagenesEscudos[i], puntos[i], 22));
        }
    }

    /**
     * Método que obtiene las referencias a las vistas del XML
     */
    private void initReferences() {
        rvListaEquipos = findViewById(R.id.rvListaEquipos);
    }

    /**
     * Método que configura la RecyclerView
     */
    private void configurarRecyclerView() {
        adaptadorEquipos = new AdaptadorEquipos((listaEquipos));
        adaptadorEquipos.setOnItemClickListener(new AdaptadorEquipos.OnItemClickListener() {
            @Override
            public void onItemClick(int posicion) {
                // Este código se ejecutará cuando se pulse click en un elemento de la lista
                // Debemos programar que lance la activity VerEquipo sabiendo la posición que fue pulsada.
                lanzarActivityVerEquipo(posicion);
            }
        });
        //asigno el adaptador
        rvListaEquipos.setAdapter(adaptadorEquipos);
        // asigno la distribución en forma de lista (no de grid)
        /* también se podría hacer por XML (aunque tienen menos posibilidades de configuración)
           y sería con esta propiedad:

           app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"

         */
        rvListaEquipos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    /** Método que lanza la activity VerEquipo que muestra los datos del equipo que se encuentra
     *  en la posición indicada del array de datos
     * @param posicion que ocupa el equipo en la lista de datos
     */
    private void lanzarActivityVerEquipo(int posicion){
        Intent i=new Intent(this, VerEquipoActivity.class);
        i.putExtra(VerEquipoActivity.EXTRA_POSICION_ARRAY,posicion);
        startActivity(i);
    }
}