package es.alejandra.android.ejemplolanzaractivitydevuelvedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class FormPerfilActivity extends AppCompatActivity {
    // CONSTANTES
    static final String EXTRA_CIUDAD = "ciudad";
    static final String EXTRA_NUM_HIJOS = "num_hijos";
    // VIEWS
    EditText etCiudad, etNumHijos;
    Button btVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_perfil);

        initReferences();
        setListenersToButtons();
    }


    /**
     * Método inicializa las View de la UI
     */
    private void initReferences() {
        etCiudad = findViewById(R.id.etCiudad);
        etNumHijos = findViewById(R.id.etNumeroHijos);
        btVolver = findViewById(R.id.btVolverPerfil);
    }

    /**
     * Método que asigna escuchadores clic a los botones
     */
    private void setListenersToButtons() {
        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etCiudad.getText()) && !TextUtils.isEmpty(etNumHijos.getText())) {
                    Intent iDatos = new Intent();
                    iDatos.putExtra(EXTRA_CIUDAD, etCiudad.getText().toString());
                    iDatos.putExtra(EXTRA_NUM_HIJOS,etNumHijos.getText().toString());
                    setResult(RESULT_OK,iDatos);
                    finish();
                }else{
                    Snackbar.make(view,"Completa los datos",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}