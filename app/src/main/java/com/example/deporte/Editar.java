package com.example.deporte;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Editar extends AppCompatActivity {

    private TextView rutd;
    private EditText named;
    private EditText deportd;
    private EditText aniosd;

    private TextView men;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        rutd = findViewById(R.id.rutd);
        named = findViewById(R.id.named);
        deportd = findViewById(R.id.deportd);
        aniosd = findViewById(R.id.aniosd);

        men = findViewById(R.id.men);

        Intent x = getIntent();
        String rut = x.getStringExtra("rut");
        String name = x.getStringExtra("name");
        String deport = x.getStringExtra("deport");
        String anios = x.getStringExtra("anios");

        rutd.setText(rut);
        named.setText(name);
        deportd.setText(deport);
        aniosd.setText(anios);

    }

    @SuppressLint("NotConstructor")
    public void Edita(View view) {
        RequestParams params = new RequestParams();
        params.put("rut", rutd.getText().toString());
        params.put("name", named.getText().toString());
        params.put("deport", deportd.getText().toString());
        params.put("anios", aniosd.getText().toString());
        Log.d("Edita", "antesURL");
        String url = "http://192.168.142.144/WSDeporte/EditarDeportista.php";
        Log.d("Edita", "despuesURL");
        new AsyncHttpClient().post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    men.setText("OK");
                    volver(view);
                    Log.d("Editar", "onSuccess");
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                men.setText("Error al Editar...");
                Log.d("Editar", "onFailure");
            }
        });
    }


    public void volver(View view) {
        Intent x = new Intent(Editar.this, Listar.class);
        startActivity(x);
    }




}