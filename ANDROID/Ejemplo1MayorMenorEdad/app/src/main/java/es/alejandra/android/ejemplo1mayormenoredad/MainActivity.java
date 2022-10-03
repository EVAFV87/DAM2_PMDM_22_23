package es.alejandra.android.ejemplo1mayormenoredad;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // CONSTANTES
    private static final String TEXTO_BASE_MAYOR_EDAD = " es mayor de edad!!";
    private static final String TEXTO_BASE_MENOR_EDAD = " es menor de edad!!";

    // Vistas de la interfaz gráfica
    EditText etNombre, etEdad;
    Button btAceptar;
    TextView tvResultCheckEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializo las referencias a las View de la UI
        initReferences();

        //asigno los listeners
        setListenersToButtons();
    }


    /**
     * Método obtiene referencias a vistas XML
     */
    private void initReferences() {
        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        btAceptar = findViewById(R.id.btAceptar);
        tvResultCheckEdad = findViewById(R.id.tvResultadoEdad);
    }


    /**
     * Método que asigna los listeners click a los botones
     */
    private void setListenersToButtons() {
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //comprobar mayoría edad
                if (!TextUtils.isEmpty(etEdad.getText())) {
                    if (esMayorEdad(etEdad.getText().toString())) {
                        tvResultCheckEdad.setText(etNombre.getText().toString() + TEXTO_BASE_MAYOR_EDAD);
                    } else {
                        tvResultCheckEdad.setText(etNombre.getText().toString() + TEXTO_BASE_MENOR_EDAD);
                    }
                }
            }
        });
    }


    /**
     * Método que comprueba si la edad que se pasa como String es mayor o no de edad
     *
     * @param edadString la edad en formato String
     * @return true si es mayor de edad y false en caso contrario
     */
    private boolean esMayorEdad(String edadString) {
        int edad;
        try {
            edad = Integer.parseInt(edadString);
            return (edad >= 18) ? true : false;

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Edad no es un número, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}