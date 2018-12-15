package higrometro.projeto.com.higrometro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.adapter.ListaAdapter;
import higrometro.projeto.com.higrometro.model.Lista;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ListView lista = (ListView) findViewById(R.id.lista_settings);
        ArrayAdapter adapter = new ListaAdapter(this,listagemTarefas());
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        Intent intent= new Intent(getApplicationContext(),esp32config.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });

    }


    private ArrayList<Lista> listagemTarefas()
        {
        ArrayList<Lista> lista = new ArrayList<Lista>();

        Lista e1 = new Lista("Reiniciar medidor",R.drawable.esp);
        lista.add(e1);
        return lista;
        }}

