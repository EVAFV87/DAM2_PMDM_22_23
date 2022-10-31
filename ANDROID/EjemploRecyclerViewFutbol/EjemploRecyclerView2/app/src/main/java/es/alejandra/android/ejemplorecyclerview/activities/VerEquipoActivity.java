package es.alejandra.android.ejemplorecyclerview.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import es.alejandra.android.ejemplorecyclerview.R;
import es.alejandra.android.ejemplorecyclerview.modelo.Equipo;

public class VerEquipoActivity extends AppCompatActivity {
    // CONSTANTS
    public final static String EXTRA_POSICION_ARRAY="posicion_array";
    // VIEWS
    TextView tvNombre,tvPuntos,tvNumeroJugadores;
    ImageView ivEscudo;
    // VARIABLES
    private int pos=-1;
    // OBJETOS
    private Equipo equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipo);
        initReferences();
        // recojo la posición del equipo que debe ser mostrado
        if(getIntent().hasExtra(EXTRA_POSICION_ARRAY)){
           pos=getIntent().getIntExtra(EXTRA_POSICION_ARRAY,-1);
        }
        if(pos!=-1){
            equipo=MainActivity.listaEquipos.get(pos);
            mostrar(equipo);
        }
    }

    /** Método que obtiene las referencias a las vistas del XML
     *
     */
    private void initReferences(){
        ivEscudo=findViewById(R.id.ivEscudo);
        tvNombre=findViewById(R.id.tvNombreEquipo);
        tvPuntos=findViewById(R.id.tvPuntos);
        tvNumeroJugadores=findViewById(R.id.tvNumeroJugadores);
    }

    /** Muestra en la pantalla el equipo cuyos datos se pasan
     *
     * @param equipo objeto Equipo que se muestra en pantalla
     */
    private void mostrar(Equipo equipo){
        ivEscudo.setImageDrawable(equipo.getEscudo());
        tvNombre.setText(equipo.getNombre());
        tvPuntos.setText(String.valueOf(equipo.getPuntos()));
        tvNumeroJugadores.setText(String.valueOf(equipo.getNumeroJugadores()));
    }
}