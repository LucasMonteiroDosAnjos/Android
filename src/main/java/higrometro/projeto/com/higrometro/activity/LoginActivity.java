package higrometro.projeto.com.higrometro.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.helper.ConfiguracaoFirebase;
import higrometro.projeto.com.higrometro.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private CardView login;
    private TextView registrar, email, senha;
    private FirebaseAuth firebaseAuth;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        verificarUsuarioLogado();
        login = (CardView) findViewById(R.id.botão_login);
        registrar = (TextView) findViewById(R.id.botão_registro);
        email = (TextView) findViewById(R.id.login_email);
        senha = (TextView) findViewById(R.id.login_senha);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                if(usuario.getSenha().isEmpty()||usuario.getEmail().isEmpty())
                {
                  Toast.makeText(getApplicationContext(),"Dados faltando!", Toast.LENGTH_SHORT).show();
                }
                else {
                    validarLogin();
                }
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void validarLogin()
    {
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Sucesso ao fazer login!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else
                    {
                        Toast.makeText(getApplicationContext(),"Erro ao fazer login!", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
    private void verificarUsuarioLogado()
    {
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
