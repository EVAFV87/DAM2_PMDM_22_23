package es.alejandra.android.ejemplolanzarnuevaactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etTexto;
    Button btIrSegundaPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferences();
        setListeners();
    }


    /**
     * Método que inicializa vistas
     */
    private void initReferences() {
        etTexto = findViewById(R.id.etTexto);
        btIrSegundaPantalla = findViewById(R.id.btAbrirSegundaActivity);
    }


    /**
     * Método que asigna listener a botones
     */
    private void setListeners() {
        btIrSegundaPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarSegundaPantalla();
            }
        });
    }


    /** Método que lanza las segunda pantalla
     *
     */
    private void lanzarSegundaPantalla() {
        Intent iSegundaPantalla = new Intent(this, SegundaActivity.class);
        iSegundaPantalla.putExtra("TEXTO_ESCRITO", etTexto.getText().toString());
        startActivity(iSegundaPantalla);
    }
}