package higrometro.projeto.com.higrometro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import higrometro.projeto.com.higrometro.ChartList.GraphActivity;
import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.adapter.ListaAdapter;
import higrometro.projeto.com.higrometro.model.Lista;

public class chartoptions extends AppCompatActivity {
    private ListView optionsChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartoptions);
        optionsChart = (ListView) findViewById(R.id.optionsChart);
        ArrayAdapter adapter = new ListaAdapter(this,listagemTarefas());
        optionsChart.setAdapter(adapter);
        optionsChart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
                        intent.putExtra("graph_type",position);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), GraphActivity.class);
                        intent1.putExtra("graph_type",position);
                        startActivity(intent1);
                        break;

                }
            }
        });
    }
    private ArrayList<Lista> listagemTarefas()
    {
       ArrayList<Lista> lista = new ArrayList<>();
       lista.add(new Lista("Sin",R.drawable.chart));
       lista.add(new Lista("Cos",R.drawable.chart));
       return lista;
    }
}
