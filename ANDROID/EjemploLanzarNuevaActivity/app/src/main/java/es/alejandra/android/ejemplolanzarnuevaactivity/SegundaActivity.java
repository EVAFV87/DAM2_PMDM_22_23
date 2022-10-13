package es.alejandra.android.ejemplolanzarnuevaactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        String texto=getIntent().getStringExtra("TEXTO_ESCRITO");
        TextView tvTexto=findViewById(R.id.tvTextoSegundaPantalla);
        tvTexto.setText("Hola "+texto+ " estás en la segunda pantalla");

    }
}