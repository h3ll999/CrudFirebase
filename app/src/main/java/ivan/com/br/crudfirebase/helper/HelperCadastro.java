package ivan.com.br.crudfirebase.helper;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ivan.com.br.crudfirebase.R;
import ivan.com.br.crudfirebase.entities.EntidadeCadastro;
import ivan.com.br.crudfirebase.validator.CadValidator;

public class HelperCadastro {

    private AppCompatActivity context;

    private EditText edt_nome;
    private EditText edt_email;
    private EditText edt_senha;
    private Button btn_cadastrar;
    ProgressDialog progressDialog;

    private FirebaseAuth auth;
    private FirebaseUser user;

    public HelperCadastro (AppCompatActivity context){
        this.context = context;

    }

    public HelperCadastro cast(){

        edt_nome = (EditText) context.findViewById(R.id.edt_nome);
        edt_email = (EditText) context.findViewById(R.id.edt_email);
        edt_senha = (EditText) context.findViewById(R.id.edt_senha);
        btn_cadastrar = (Button) context.findViewById(R.id.btn_cadastrar);

        return this;
    }

    public HelperCadastro OnClick(){

        auth = FirebaseAuth.getInstance();
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = ProgressDialog.show(context, "Cadastrando", "Aguarde...");

                boolean validaCampos = new CadValidator(edt_nome.getText().toString(),
                        edt_nome.getText().toString(), edt_senha.getText().toString()).validaCampos();

                if(validaCampos == false){

                    progressDialog.dismiss();
                    Toast.makeText(context, "Campo(s) em branco ou senha inválida!", Toast.LENGTH_SHORT).show();

                }else{

                    auth.createUserWithEmailAndPassword(edt_email.getText().toString().trim(), edt_senha.getText().toString().trim())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {



                                    user = authResult.getUser();

                                    EntidadeCadastro entidadeCadastro = new EntidadeCadastro();
                                    entidadeCadastro.setNome(edt_nome.getText().toString().trim());
                                    entidadeCadastro.setEmail(edt_email.getText().toString().trim());
                                    entidadeCadastro.setSenha(edt_senha.getText().toString().trim());

                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                                    ref.child("USUARIOS").child("CADASTRO").child(user.getUid()).setValue(entidadeCadastro)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(context, "Sucesso!", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.dismiss();
                                            Toast.makeText(context, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                    }
            }
        });

        return this;
    }

}
