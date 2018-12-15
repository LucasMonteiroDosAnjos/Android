package higrometro.projeto.com.higrometro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import higrometro.projeto.com.higrometro.ChartList.GraphActivity;
import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.helper.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView valor;
    private TextView Data;
    private DatabaseReference firebase, firebase1,firebase2,firebase3;
    private TextView analogvalue;
    private ToggleButton toggleButton;
    SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
    String date = SDF.format(new Date());
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        Data = (TextView) findViewById(R.id.data);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sensor Higrômetro");
        valor = (TextView) findViewById(R.id.valor);
        analogvalue = (TextView) findViewById(R.id.analogValue);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButtonId);
        firebase2 = ConfiguracaoFirebase.getFirebase().child("StateLed");
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    firebase2.setValue(1);
                }else
                    {
                        firebase2.setValue(0);
                    }
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        firebase = database.getReference("/HumidityValue");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String porcentagem = dataSnapshot.getValue().toString();
                    valor.setText(porcentagem);
                }else
                    {
                        firebase.removeEventListener(this);
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        firebase1 = database.getReference("AnalogValue");
        firebase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String analogico = dataSnapshot.getValue().toString();
                    analogvalue.setText(analogico);
                }else
                    {
                        firebase1.removeEventListener(this);
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        firebase3 = ConfiguracaoFirebase.getFirebase();
        firebase3.child("Date").setValue(date);
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat formataData = DateFormat.getDateInstance();
        Data.setText(formataData.format(data));
}

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(),settings.class);
                startActivity(intent);
                finish();
                break;
            case R.id.action_bar:
                Intent intent1= new Intent(getApplicationContext(),historico.class);
                startActivity(intent1);
                break;
            case R.id.action_logout:
                autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                autenticacao.signOut();
                Intent intent2 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.action_chat:
                Intent intent3 = new Intent(getApplicationContext(),MlActivity.class);
                startActivity(intent3);
                break;
            case R.id.action_graph:
                Intent intent4 = new Intent(getApplicationContext(),chartoptions.class);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
