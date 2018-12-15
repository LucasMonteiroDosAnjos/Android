package higrometro.projeto.com.higrometro.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import higrometro.projeto.com.higrometro.R;
import higrometro.projeto.com.higrometro.model.Usuario;

public class CadastroActivity extends AppCompatActivity {
    private EditText email,senha,senha2;
    private FirebaseAuth firebaseAuth;
    private CardView cadastro;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        email = (EditText) findViewById(R.id.cadastro_email);
        senha = (EditText) findViewById(R.id.cadastro_senha);
        senha2 = (EditText) findViewById(R.id.cadastro_senha2);
        cadastro = (CardView) findViewById(R.id.cadastrar);
        firebaseAuth = FirebaseAuth.getInstance();
        //Cadastro
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                cadastrarUsuario();

            }
        });
    }
    private void cadastrarUsuario()
    {
        if(usuario.getEmail().isEmpty()||usuario.getSenha().isEmpty()||senha2.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Dados faltando!", Toast.LENGTH_SHORT).show();
        }else
        {
            if(senha.getText().toString().equals(senha2.getText().toString()))
            {
                firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete( Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else
                                {
                                    Toast.makeText(getApplicationContext(),"Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }else
            {
                Toast.makeText(getApplicationContext(),"Senha incorreta!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
