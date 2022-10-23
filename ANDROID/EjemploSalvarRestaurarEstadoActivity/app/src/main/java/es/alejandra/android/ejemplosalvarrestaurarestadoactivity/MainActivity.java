package es.alejandra.android.ejemplosalvarrestaurarestadoactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // CONSTANTES
    private static final String RESULTADO_MULTIPLICACION="resultado";

    // VISTAS
    EditText etNumero1, etNumero2;
    Button btCalcular;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializo referencias a las Views
        initReferences();
        // asigno los escuchadores a los botones
        setListenersToButtons();
    }


    /** Guardo el estado de las vistas de la Activity
     *
     * @param outState Bundle donde se guarda la información
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int resultado=-1;
        super.onSaveInstanceState(outState);
        if(!TextUtils.isEmpty(tvResultado.getText())){
            resultado=Integer.parseInt(tvResultado.getText().toString());
        }
        outState.putInt(RESULTADO_MULTIPLICACION,resultado);
    }


    /** Restauro el estado de las vistas de la activity cuando se recree tras haber
     * sido destruida
     * @param savedInstanceState Bundle donde está la información que se guardó previamente
     *                           con onSaveInstanceState.
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int resultado=savedInstanceState.getInt(RESULTADO_MULTIPLICACION);
        if (resultado!=-1){
            // se guarda -1 cuando está vacío el resultado de la operación
            tvResultado.setText(String.valueOf(resultado));
        }


    }

    /**
     * Método que asocia las Vistas de las UI con nuestros objetos java
     */
    private void initReferences() {
        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        btCalcular = findViewById(R.id.btCalcular);
        tvResultado = findViewById(R.id.tvResultado);
    }


    /**
     * Método que asigna los escuchadores a los botones
     */
    private void setListenersToButtons() {
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etNumero1.getText()) && !TextUtils.isEmpty(etNumero2.getText())) {
                    tvResultado.setText(String.valueOf(Integer.parseInt(etNumero1.getText().toString()) *
                            Integer.parseInt(etNumero2.getText().toString())));
                } else{
                    tvResultado.setText("");
                    Toast.makeText(MainActivity.this,"Debes introducir números!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}