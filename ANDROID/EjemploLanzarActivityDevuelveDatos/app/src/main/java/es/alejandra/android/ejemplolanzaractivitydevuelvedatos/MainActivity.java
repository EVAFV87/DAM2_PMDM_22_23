package es.alejandra.android.ejemplolanzaractivitydevuelvedatos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {
    // VIEWS
    TextView tvCiudad, tvNumHijos;
    Button btCompletarPerfil;

    // CONTROL DEVOLUCIÓN LLAMADA ACTIVITY
    private ActivityResultLauncher<Intent> mLauncherActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferences();
        setListenersToButtons();
        // registro devolución de llamadas de resultados provenientes de otra activity
        mLauncherActivity=registroDevolucionLLamadaActivity();

    }



    /** Método que inicializa las Views de la UI
     *
     */
    private void initReferences() {
        tvCiudad=findViewById(R.id.tvCiudad);
        tvNumHijos=findViewById(R.id.tvNumeroHijos);
        btCompletarPerfil=findViewById(R.id.btCompletarPerfil);
    }

    /** Método asigna escuchadores a botones
     *
     */
    private void setListenersToButtons() {
        btCompletarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarActivityFormPerfil();
            }
        });
    }


    /** Método que registra la devolución de llamada de resultados provenientes de
     *  otra activity
     * @return ActivityResultLauncher<Intent> objeto que nos permitirá lanzar nuestra segunda activity
     */
    private ActivityResultLauncher<Intent> registroDevolucionLLamadaActivity() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // código que se ejecuta cuando la segunda activity devuelve el control a esta
                        if(result.getResultCode()==RESULT_OK){
                            Intent data=result.getData();
                            completarPerfil(data);
                        }
                    }
                }
        );
    }



    /** Método que lanza la activity con el Formulario para completar el perfil
     *
     */
    private void lanzarActivityFormPerfil() {
        Intent iFormPerfilActivity= new Intent(this, FormPerfilActivity.class);
        mLauncherActivity.launch(iFormPerfilActivity);
    }


    /** Método que completa el perfil con los datos que hay en data
     *
     * @param data datos con el resto del perfil del usuario
     */
    private void completarPerfil(Intent data) {
        tvCiudad.setVisibility(View.VISIBLE);
        tvNumHijos.setVisibility(View.VISIBLE);
        btCompletarPerfil.setVisibility(View.GONE);
        tvCiudad.setText(data.getStringExtra(FormPerfilActivity.EXTRA_CIUDAD));
        tvNumHijos.setText(data.getStringExtra(FormPerfilActivity.EXTRA_NUM_HIJOS));
    }




}