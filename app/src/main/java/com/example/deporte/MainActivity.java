package com.example.deporte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bregistrar;
    private Button blistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bregistrar = findViewById(R.id.bregistrar);
        blistar = findViewById(R.id.blistar);

    }

    public void go(View view){

        Intent x = new Intent(MainActivity.this, Registrar.class);
        startActivity(x);

    }

    public void lista(View view){
        Intent x = new Intent(MainActivity.this, Listar.class);
        startActivity(x);

    }

}