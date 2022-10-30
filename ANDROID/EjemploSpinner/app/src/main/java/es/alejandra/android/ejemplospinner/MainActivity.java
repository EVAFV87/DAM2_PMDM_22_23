package es.alejandra.android.ejemplospinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // VIEWS
    Spinner spProcesadores,         // TOMA LO DATOS DE UN String[] de java
            spEstadoCivil,          // TOMA LOS DATOS DE UN <string-array> de XML
            spPaises,               // TOMA LOS DATOS DE UN <string-array> de XML
            spProductosAComprar,    // TOMA LOS DATOS DE UN ArrayList<String>
            spCarritoCompra;        // SE VA RELLENANDO EN TIEMPO DE EJECUCIÓN AL PULSAR BOTÓN ADD CARRITO
    Button btAddCarrito, btBorrarCarrito;

    // ARRAYS
    private String[] tiposProcesadores;
    private List<String> productosDisponibles;
    private List<String> carritoCompra;

    //ADAPTADORES
    private ArrayAdapter<String> adapterProcesadores;
    private ArrayAdapter<CharSequence> adapterEstadoCivil;
    private ArrayAdapter<String> adapterPaises;
    private ArrayAdapter<String> adapterProductosComprar;
    private ArrayAdapter<String> adapterCarritoCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReferences();
        setListenersToButtons();
        // SPINNER PROCESADORES: Ej de cómo cargar un spinner con datos de un String[] de java
        configuracionSpinnerProcesadores();
        // SPINNER ESTADOS CIVILES: OPCIÓN1: Ej de cómo cargar un spinner con datos de un <string-array> de XML
        configuracionSpinnerEstadoCivil();
        // SPINNER PAISES: OPCIÓN 2: Ej de cómo cargar un spinner con datos de un <string-array> de XML pero
        // convirtiéndolo a String[] de java antes de asignarlo al adaptador.
        configuracionSpinnerPaises();
        // SPINNER PRODUCTOS A COMPRAR: Ej de cómo cargar un spinne con datos de un ArrayList<String>
        configuracionSpinnerProductosAComprar();
        // SPINNER CARRITO COMPRA: Ej de cómo ir rellenando un Spinner en tiempo de ejecución y de
        // borra datos en tiempo de ejecución según se pulse ADD CARRITO o DEL CARRITO botones.
        configuracionSpinnerCarritoCompra();


    }

    /**
     * Método que configura el Spinner Procesadores tomando los datos de un String[]
     */
    private void configuracionSpinnerProcesadores() {
        //inicializo array
        tiposProcesadores = new String[]{"Elige uno", "i3", "i5", "i7", "i9"};

        //configuro adaptador
        adapterProcesadores = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tiposProcesadores);
        adapterProcesadores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //asigno adaptador al spinner
        spProcesadores.setAdapter(adapterProcesadores);
        //asigno escuchador evento itemSelected
        spProcesadores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if (pos != 0) {
                    String elegido = adapterView.getItemAtPosition(pos).toString();
                    Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Método que configura el spinner Estado Civil tomando los datos de un <string-array> de XML
     * OPCIÓN 1: en este ejemplo se CREA EL ADAPTADOR tomando el array XML directamente
     */
    private void configuracionSpinnerEstadoCivil() {
        //creo el array a partir del array de XML
        adapterEstadoCivil = ArrayAdapter.createFromResource(this, R.array.estados_civiles, android.R.layout.simple_spinner_item);
        // configuro resto de parámetros del adaptador como en los demás casos
        adapterEstadoCivil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //asigno el adaptador
        spEstadoCivil.setAdapter(adapterEstadoCivil);
        //asigno escuchador evento itemSelected
        spEstadoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String elegido = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Método que configura el spinner Paises tomando los datos de un <string-array> de XML
     * OPCIÓN 2: en este ejemplo se convierte el array XML a String[] de java y luego se asigna
     * ese array Java al adaptador como se hizo en el spinner Procesadores
     */
    private void configuracionSpinnerPaises() {
        // recupero el array XML de la carpeta /res, fichero arrays.xml y lo convierto a String[]
        String[] paisesArray = getResources().getStringArray(R.array.paises);
        // configuro el adaptador
        adapterPaises = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paisesArray);
        adapterPaises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //asigno el adaptador
        spPaises.setAdapter(adapterPaises);
        //asigno escuchador evento itemSelected
        spPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String elegido = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Método que configura el Spinner Productos a comprar tomando los datos de un ArrayList<String>
     */
    private void configuracionSpinnerProductosAComprar() {
        //inicializo ArrayList
        productosDisponibles = new ArrayList<>();
        productosDisponibles.add("Leche");
        productosDisponibles.add("Pan");
        productosDisponibles.add("Agua");
        productosDisponibles.add("Detergente Lavadora");
        productosDisponibles.add("Champú");
        productosDisponibles.add("BodyMilk");

        //configuro adaptador
        adapterProductosComprar = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productosDisponibles);
        adapterProcesadores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //asigno adaptador al spinner
        spProductosAComprar.setAdapter(adapterProductosComprar);
        //asigno escuchador evento itemSelected
        spProductosAComprar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                String elegido = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Método que configura el Spinner Carrito de la compra en tiempo de ejecución.
     * Se va rellenando cada vez que se pulsa el botón AÑADIR AL CARRITO.
     * Se le quitan elementos del carrito cuando se pulsa el botón BORRAR DEL CARRITO.
     * Inicialmente está vacío.
     * Los datos se gestionan con un  ArrayList<String>
     */
    private void configuracionSpinnerCarritoCompra() {
        //inicializo ArrayList
        carritoCompra = new ArrayList<>(); // vacío

        //configuro adaptador
        adapterCarritoCompra = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carritoCompra);
        adapterCarritoCompra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //asigno adaptador al spinner
        spCarritoCompra.setAdapter(adapterCarritoCompra);
        //asigno escuchador evento itemSelected
        spCarritoCompra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                String elegido = adapterView.getItemAtPosition(pos).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * Método obtiene referencias a las vistas
     */
    private void initReferences() {
        spEstadoCivil = findViewById(R.id.spEstadoCivil);
        spProcesadores = findViewById(R.id.spProcesadores);
        spPaises = findViewById(R.id.spPaises);
        spProductosAComprar = findViewById(R.id.spProductosDisponibles);
        spCarritoCompra = findViewById(R.id.spCarrito);
        btAddCarrito=findViewById(R.id.btAñadirCarrito);
        btBorrarCarrito=findViewById(R.id.btBorrarDelCarrito);

    }

    /** Método que asigna escuchadores a los botones
     *
     */
    private void setListenersToButtons(){
        btAddCarrito.setOnClickListener(this);
        btBorrarCarrito.setOnClickListener(this);

    }


    /** Método que se ejecuta al pulsar botones AÑADIR CARRITO y BORRAR CARRITO
     *
     * @param view el botón pulsado
     */
    @Override
    public void onClick(View view) {
        String productoElegido= (String) spProductosAComprar.getSelectedItem();
        int pos=adapterCarritoCompra.getPosition(productoElegido);
        
        switch (view.getId()){
            case R.id.btAñadirCarrito:
                
               if(pos<0) {
                   adapterCarritoCompra.add(productoElegido);
                   adapterCarritoCompra.notifyDataSetChanged();
                   spCarritoCompra.setSelection(adapterCarritoCompra.getPosition(productoElegido));
                   
               }else{
                   Toast.makeText(this, "Ese producto ya está en el carrito", Toast.LENGTH_SHORT).show();
               }
               break;
            case R.id.btBorrarDelCarrito:
                if(pos>=0){
                    adapterCarritoCompra.remove(productoElegido);
                    adapterCarritoCompra.notifyDataSetChanged();
                    
                }else{
                    Toast.makeText(this, "Ese producto no está en el carrito", Toast.LENGTH_SHORT).show();
                }

        }

    }
}