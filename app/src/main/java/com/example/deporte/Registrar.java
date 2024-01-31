package com.example.deporte;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;



public class Registrar extends AppCompatActivity {

    private EditText rut;
    private EditText name;
    private EditText deport;
    private EditText anios;

    private Button bguardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        rut = findViewById(R.id.rut);
        name = findViewById(R.id.name);
        deport = findViewById(R.id.deport);
        anios = findViewById(R.id.anios);

        bguardar = findViewById(R.id.bguardar);

        bguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rutd, named, deportd, aniosd;
                rutd = rut.getText().toString();
                named = name.getText().toString();
                deportd = deport.getText().toString();
                aniosd = anios.getText().toString();

                registrar(rutd, named, deportd, aniosd);
              }
        }
        );
    }


    private void registrar(String rutd, String named, String deportd, String aniosd) {
        if(validar()) {
            String url = "http://192.168.142.144/WSDeporte/CRUDDeportista.php?";
            url = url + "rut=" + rutd + "&name=" + named + "&deport=" + deportd + "&anios=" + aniosd;
            Log.d("registrar", url);

            new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("registrar OnSuccess", "");
                    if (statusCode == 200) {
                        Log.d("IF", "");
                        String res = new String(responseBody);
                        Toast.makeText(Registrar.this, res, Toast.LENGTH_LONG).show();
                        Intent x = new Intent(Registrar.this, Listar.class);
                        startActivity(x);

                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("registrar onFailure", "");
                    System.out.println(error);
                    bguardar.setText("error");
                    //reg.setText("....Registro Fallido....");
                }
            });
        }
    }


    public boolean validar() {
        boolean respuesta = true;
        String t1 = rut.getText().toString();
        if (t1.equals("")) {
            rut.setError("Ingrese el RUT");
            Toast.makeText(this, "Falta RUT del Deportista", Toast.LENGTH_LONG).show();
            respuesta = false;
        }
        String t2 = name.getText().toString();
        if (t2.equals("")) {
            name.setError("Ingrese el Nombre");
            Toast.makeText(this, "Falta Nombre del Deportista", Toast.LENGTH_LONG).show();
            respuesta = false;
        }
        String t3 = deport.getText().toString();
        if (t3.equals("")) {
            deport.setError("Ingrese el Deporte");
            Toast.makeText(this, "Falta indicar el Deporte", Toast.LENGTH_LONG).show();
            respuesta = false;
        }
        String t4 = anios.getText().toString();
        if (t4.equals("")) {
            anios.setError("Ingrese los Años");
            Toast.makeText(this, "Falta la cantidad de Años", Toast.LENGTH_LONG).show();
            respuesta = false;
        }
        return respuesta;
    }


    public void registro(View view) {
        validar();
        String result = name.getText().toString();
        Intent x = new Intent(Registrar.this, MainActivity.class);
        x.putExtra("Se registró con éxito ", result);
        startActivity(x);

    }


    public void atras(View view) {
        Intent x = new Intent(Registrar.this, MainActivity.class);
        startActivity(x);
    }


}