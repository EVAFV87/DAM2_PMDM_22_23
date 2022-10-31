package es.alejandra.android.ejemplorecyclerview.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import es.alejandra.android.ejemplorecyclerview.R;

public class AniadirEquipoActivity extends AppCompatActivity {
    // VIEWS
    CheckBox cbSinFoto;
    Button btAniadirFoto;
    ImageView ivEscudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_equipo);

        initReferences();
        setListenersToCheckBoxes();
    }

    /** Método que obtiene las referencias a las vistas XML
     *
     */
    private void initReferences(){
        cbSinFoto=findViewById(R.id.cbSinFoto);
        btAniadirFoto=findViewById(R.id.btAniadirFoto);
        ivEscudo=findViewById(R.id.ivEscudoAniadir);
    }

    /** Método que asigna los listeners a los checkBoxes
     *
     */
    private void setListenersToCheckBoxes(){
        cbSinFoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //activo el botón añadir foto cuando esté el checbox marcado o lo desactivo en caso contrario
                btAniadirFoto.setEnabled(!isChecked);
                if(isChecked){
                    //cargo imagen genérica en el ImageView del escudo
                    ivEscudo.setImageResource(R.drawable.generica);
                    ivEscudo.setTag(R.drawable.generica); //lo necesitaré después saber qué imagen está
                }else{
                    // quito la foto del ImageView sólo si es la genérica, porque puede ser que
                    // hubiese una foto tomada con la cámara
                    if((int)ivEscudo.getTag()==R.drawable.generica){
                        ivEscudo.setImageDrawable(null);
                    }
                }
            }
        });
    }
}