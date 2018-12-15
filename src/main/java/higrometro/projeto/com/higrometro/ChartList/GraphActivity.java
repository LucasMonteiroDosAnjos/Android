package higrometro.projeto.com.higrometro.ChartList;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.vision.text.Line;


import java.util.ArrayList;

import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.helper.ConfiguracaoFirebase;

public class GraphActivity extends AppCompatActivity {
    LineChart lineChart;
    float x = 0.0f;
    double y = 0.0f;
    ArrayList<Entry> entries = new ArrayList<>();
    int escolha;
    Float y1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        lineChart = (LineChart) findViewById(R.id.lineChart);
        Bundle bundle = getIntent().getExtras();
        escolha = bundle.getInt("graph_type");

        switch(escolha)
        {
            case 0:
                generateSin();
                break;
            case 1:
                generateCos();
                break;
        }



    }
    private void generateSin()
    {
        for(int i=0;i<30;i++)
        {
            y1 =(float) Math.sin((y*(3.141592654d))/180);
            Log.i("TESTE",String.valueOf((y1)));
            entries.add(new Entry(x,y1));
            x = x + 1.0f;
            y = y + 30f;
        }
        LineDataSet dataSet = new LineDataSet(entries,"Sin");
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.BLUE);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
    private void generateCos()
    {
        for(int i=0;i<30;i++)
        {
            y1 =(float) Math.cos((y*(3.141592654d))/180);
            Log.i("TESTE",String.valueOf((y1)));
            entries.add(new Entry(x,y1));
            x = x + 1.0f;
            y = y + 30f;
        }
        LineDataSet dataSet = new LineDataSet(entries,"Sin");
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.BLUE);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}
