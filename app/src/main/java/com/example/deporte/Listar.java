package com.example.deporte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

import java.util.Objects;

public class Listar extends AppCompatActivity {

    TableLayout tablelistar;

    private EditText ibuscar;
    private Button bbuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        tablelistar = findViewById(R.id.tablelistar);
        ibuscar = findViewById(R.id.ibuscar);
        bbuscar = findViewById(R.id.bbuscar);

        mostrar("");
    }

    private void mostrar(String buscar){

        String url = "http://192.168.142.144" +
                "/WSDeporte/ListarDeportista.php";

        if(!buscar.equals("")){
            url = "http://192.168.142.144" +
                    "/WSDeporte/BuscarDeportista.php?buscar="+buscar;
        }

        Log.d("mostar", url);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    tablelistar.removeAllViews();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("deportistas");

                    if(jsonArray.length() == 0 ) {
                        Toast.makeText(Listar.this, "No se encuentran coincidencias", Toast.LENGTH_SHORT).show();

                    } else {
                        TableRow tableRowTh = new TableRow(Listar.this);

                        TextView rutTh = new TextView(Listar.this);
                        rutTh.setText("Rut    ");
                        TextView nameTh = new TextView(Listar.this);
                        nameTh.setText("Nombre    ");
                        TextView depTh = new TextView(Listar.this);
                        depTh.setText("Deporte    ");
                        TextView aniosTh = new TextView(Listar.this);
                        aniosTh.setText(" Años ");

                        tableRowTh.addView(rutTh);
                        tableRowTh.addView(nameTh);
                        tableRowTh.addView(depTh);
                        tableRowTh.addView(aniosTh);

                        tablelistar.addView(tableRowTh);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Context context = Listar.this;

                            TableRow tableRow = new TableRow(Listar.this);

                            TextView rutd = new TextView(context);
                            TextView named = new TextView(context);
                            TextView deportd = new TextView(context);
                            TextView aniosd = new TextView(context);

                            Button btnEdit = new Button(context);
                            Button btnDelete = new Button(context);

                            rutd.setText(object.getString("RUT_DEPORTISTA"));
                            named.setText(object.getString("NOMBRE_DEPORTISTA"));
                            deportd.setText(object.getString("NOMBRE_DEPORTE"));
                            aniosd.setText(object.getString("ANOS_DEPORTE"));

                            btnEdit.setText("Editar");
                            btnEdit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Button button = (Button) view;
                                    Intent x = new Intent(Listar.this, Editar.class);
                                    x.putExtra("rut", rutd.getText().toString());
                                    x.putExtra("name", named.getText().toString());
                                    x.putExtra("deport", deportd.getText().toString());
                                    x.putExtra("anios", aniosd.getText().toString());
                                    startActivity(x);
                                    Log.d("mostrar", "onClick edit");
                                }
                            });

                            btnDelete.setText("Eliminar");
                            btnDelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Button button = (Button) view;
                                    Intent x = new Intent(Listar.this, Eliminar.class);
                                    x.putExtra("ru", rutd.getText().toString());
                                    x.putExtra("na", named.getText().toString());
                                    x.putExtra("de", deportd.getText().toString());
                                    x.putExtra("an", aniosd.getText().toString());
                                    startActivity(x);
                                    Log.d("mostrar", "onClick delete");
                                }
                            });

                            Log.d("mostrar", "onResponse aqui");

                            tableRow.addView(rutd);
                            tableRow.addView(named);
                            tableRow.addView(deportd);
                            tableRow.addView(aniosd);

                            tableRow.addView(btnEdit);
                            tableRow.addView(btnDelete);

                            tablelistar.addView(tableRow);
                        }
                    }
                } catch (JSONException ex) {
                    Toast.makeText(Listar.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mostrar", "onErrorResponse");
                System.out.println(error.getMessage());
                Toast.makeText(Listar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        Volley.newRequestQueue(this).add(request);
    }


    public void buscar(View view){
        String textobuscar = ibuscar.getText().toString();
        mostrar(textobuscar);
    }

    public void volver(View view){
        Intent x = new Intent(Listar.this, MainActivity.class);
        startActivity(x);
    }

}