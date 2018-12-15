package higrometro.projeto.com.higrometro.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import higrometro.projeto.com.higrometro.R;

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_app_intro);

        addSlide(AppIntroFragment.newInstance("Agricultura inteligente!","Controle níveis de humidade do solo " +
                        "diretamente do seu smartphone!"
                ,R.drawable.agricultura, ContextCompat.getColor(getApplicationContext(),R.color.intro1)));

        addSlide(AppIntroFragment.newInstance("Iluminação inteligente!","Controle vários Leds acoplados no equi" +
                        "pamento para ajustar a iluminação!"
                ,R.drawable.iluminacao, ContextCompat.getColor(getApplicationContext(),R.color.intro2)));

        addSlide(AppIntroFragment.newInstance("Banco de dados em tempo real!","Com auxílio do Firebase consiga" +
                        " acesso ao registro de leituras direto de seu smartphone!"
                ,R.drawable.firebase, ContextCompat.getColor(getApplicationContext(),R.color.intro3)));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
