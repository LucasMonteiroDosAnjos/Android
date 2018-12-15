package higrometro.projeto.com.higrometro.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.adapter.ViewPagerAdapter;
import higrometro.projeto.com.higrometro.adapter.ViewPagerAdapter;
import higrometro.projeto.com.higrometro.fragments.fragmentA;
import higrometro.projeto.com.higrometro.fragments.fragmentB;

public class historico extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewPager;
    final int ICONS[] = new int[]{R.drawable.ic_humid,R.drawable.ic_sensor};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new fragmentA(),"Umidade(%)");
        adapter.AddFragment(new fragmentB(),"Analógico(N)");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        tablayout.getTabAt(0).setIcon(ICONS[0]);
        tablayout.getTabAt(1).setIcon(ICONS[1]);
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
