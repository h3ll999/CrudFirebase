package ivan.com.br.crudfirebase.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ivan.com.br.crudfirebase.R;
import ivan.com.br.crudfirebase.helper.HelperCadastro;

public class Cadastro extends AppCompatActivity {

    private Button btn_cadastrar;
    private HelperCadastro helperCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        btn_cadastrar = (Button) findViewById(R.id.btn_cadastrar);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helperCadastro = new HelperCadastro(Cadastro.this);
                helperCadastro.cast();
                helperCadastro.OnClick();

            }
        });
    }
}
