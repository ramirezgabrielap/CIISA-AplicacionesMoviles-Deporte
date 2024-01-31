package com.example.deporte;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Eliminar extends AppCompatActivity {

    private TextView rute;
    private TextView namee;
    private TextView deporte;
    private TextView aniose;
    private TextView men2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        rute = findViewById(R.id.rute);
        namee = findViewById(R.id.namee);
        deporte = findViewById(R.id.deporte);
        aniose = findViewById(R.id.aniose);

        men2 = findViewById(R.id.men2);

        Intent x = getIntent();
        String ru = x.getStringExtra("ru");
        String na = x.getStringExtra("na");
        String de = x.getStringExtra("de");
        String an = x.getStringExtra("an");

        rute.setText(ru);
        namee.setText(na);
        deporte.setText(de);
        aniose.setText(an);

    }

    public void Elimina(View view) {

        RequestParams params = new RequestParams();
        params.put("rut", rute.getText().toString());

        String url = "http://192.168.142.144/WSDeporte/EliminarDeportista.php";
        new AsyncHttpClient().post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("Elimina", "onSuccess");
                if (statusCode == 200) {
                    Log.d("Elimina", "if 200");
                    men2.setText("Eliminación OK");
                    volver(view);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Elimina", "onFailure");
                men2.setText("Error al Eliminar...");
            }
        });
    }

    public void volver(View view){
        Intent x = new Intent(Eliminar.this, Listar.class);
        startActivity(x);
    }

}