package ivan.com.br.crudfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ivan.com.br.crudfirebase.views.Cadastro;
import ivan.com.br.crudfirebase.views.Login;

public class MainActivity extends AppCompatActivity {

    private Button btn_cadasto;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cadasto = (Button) findViewById(R.id.btn_cadastro);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_cadasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Cadastro.class);
                startActivity(i);

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);

            }
        });
    }
}
