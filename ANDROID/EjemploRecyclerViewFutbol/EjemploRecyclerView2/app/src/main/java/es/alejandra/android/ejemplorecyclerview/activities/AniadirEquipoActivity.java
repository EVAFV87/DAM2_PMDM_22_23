package es.alejandra.android.ejemplorecyclerview.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import es.alejandra.android.ejemplorecyclerview.R;
import es.alejandra.android.ejemplorecyclerview.modelo.Equipo;

public class AniadirEquipoActivity extends AppCompatActivity {
    // CONSTANTES
    final static String RESULTADO_INSERCION="resultado insercion";
    final static String POSICION_INSERCION="posición inserción";
    // VIEWS
    CheckBox cbSinFoto;
    Button btAniadirFoto, btAniadirEquipo;
    ImageView ivEscudo;
    // LAUNCHERS FOR ACTIVITIES RETURNS RESULTS, como la cámara de fotos
    private ActivityResultLauncher<Void> tomarFotoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_equipo);

        initReferences();
        setListenersToCheckBoxes();
        configurarLauncherTomarFoto();
        setListenersToButtons();

    }

    /**
     * Método que obtiene las referencias a las vistas XML
     */
    private void initReferences() {
        cbSinFoto = findViewById(R.id.cbSinFoto);
        btAniadirFoto = findViewById(R.id.btAniadirFoto);
        ivEscudo = findViewById(R.id.ivEscudoAniadir);
        btAniadirEquipo = findViewById(R.id.btAniadirEquipo);
    }

    /**
     * Método que asigna los listeners a los checkBoxes
     */
    private void setListenersToCheckBoxes() {
        cbSinFoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //activo el botón añadir foto cuando esté el checbox marcado o lo desactivo en caso contrario
                btAniadirFoto.setEnabled(!isChecked);
                if (isChecked) {
                    //cargo imagen genérica en el ImageView del escudo
                    ivEscudo.setImageResource(R.drawable.generica);
                    ivEscudo.setTag(R.drawable.generica); //lo necesitaré después saber qué imagen está
                } else {
                    // quito la foto del ImageView sólo si es la genérica, porque puede ser que
                    // hubiese una foto tomada con la cámara
                    if ((int) ivEscudo.getTag() == R.drawable.generica) {
                        ivEscudo.setImageDrawable(null);
                    }
                }
            }
        });
    }

    /**
     * Método que configurar el launcher que permite lanzar la activity de la cámara de fotos
     * y recoger luego la foto en formato thumbnail
     */
    private void configurarLauncherTomarFoto() {
        tomarFotoLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicturePreview(),
                new ActivityResultCallback<Bitmap>() {
                    @Override
                    public void onActivityResult(Bitmap result) {

                        ivEscudo.setImageBitmap(result);

                    }
                }
        );

    }

    /**
     * Método que añade los listeners a los botones
     */
    private void setListenersToButtons() {
        btAniadirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFoto();
            }
        });

        btAniadirEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aniadirEquipo();
            }
        });

    }

    /**
     * Método que lanza la cámara del dispositivo y recoge la foto en formato thumbnail
     */
    private void tomarFoto() {
        tomarFotoLauncher.launch(null);

    }


    /**
     * Método que añade un equipo a la lista de equipos
     */
    private void aniadirEquipo() {
        // creo el equipo comprobando que los datos han sido correctos
        Equipo equipo = crearEquipoFromDatosActivity();
        if(equipo!=null){
            // no hubo errores
            // Busco la posición donde debo insertar el nuevo equipo, pues van ordenados por puntos
            // en el ArrayList
            int pos=buscarPosEquipo(MainActivity.listaEquipos,equipo.getPuntos());
            //inserto el equipo en su posición
            MainActivity.listaEquipos.add(pos,equipo);
            //desactivo botón AÑADIR EQUIPO
            btAniadirEquipo.setEnabled(false);
            //configuro el Intent a devolver a la activity MainActivity
            Intent iResultadoInsercion=new Intent();
            iResultadoInsercion.putExtra(RESULTADO_INSERCION,"OK");
            iResultadoInsercion.putExtra(POSICION_INSERCION,pos);
            setResult(RESULT_OK,iResultadoInsercion);
            //cierro la activity
            finish();
        }

    }

    /**
     * Método que crea un objeto Equipo a partir de los datos de los datos introducidos
     * en el formulario de Añadir Equipo.
     * Se comprueba que el nombre no sea vacío, escudo no vacío y si los puntos están en blanco se
     * ponen a 0;  nº jugadores se ponen a 0 siempre.
     *
     * @return un objeto Equipo con los datos del nuevo equipo o null si hay errores
     */
    private Equipo crearEquipoFromDatosActivity() {
        Equipo equipo = null;
        boolean error=false;
        // VIEWS
        EditText etNombre, etPuntos;

        //obtengo referencias para las nuevas views
        etNombre = findViewById(R.id.etNombreEquipoAniadir);
        etPuntos = findViewById(R.id.etPuntosEquipoAniadir);

        // Comprobación errores
        if (TextUtils.isEmpty(etNombre.getText())) {
            etNombre.setError(getString(R.string.error_nombre_vacío));
            error = true;
        }
        String puntos = etPuntos.getText().toString()
                .isEmpty() ? "0" : etPuntos.getText().toString();
        if (ivEscudo == null || ivEscudo.getDrawable() == null) {
            Toast.makeText(this, getString(R.string.error_escudo), Toast.LENGTH_SHORT).show();
            error = true;
        }
        if (!error) {
            equipo = new Equipo(etNombre.getText().toString(),
                    ivEscudo.getDrawable(), Integer.parseInt(puntos), 0);
        }
        return equipo;
    }


    /** Método que busca la pos adecuada para insertar un equipo nuevo en un ArrayList de Equipo
     *  ordenado por los puntos de los equipos.
     *
     * @param lista el ArrayList con los equipos donde se busca la posición
     * @param puntos los puntos que tiene el equipo cuya posición se busca
     * @return la posición correspondiente a ese equipo en la lista
     */
    private int buscarPosEquipo(List<Equipo> lista, int puntos){
        for(Equipo equipo:lista){
            if(equipo.getPuntos()<puntos){
                return (lista.indexOf(equipo));
            }
        }
        return lista.size();
    }


}