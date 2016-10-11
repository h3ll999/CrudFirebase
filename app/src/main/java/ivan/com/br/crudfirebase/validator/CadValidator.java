package ivan.com.br.crudfirebase.validator;

public class CadValidator {

    private String nome;
    private String email;
    private String senha;

    public CadValidator(){

    }


    public CadValidator(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public boolean validaCampos(){

        if(nome.equals("") || email.equals("") || senha.equals("")){
            return false;
        }else if(senha.length() < 6){
            return false;
        }else{
            return true;
        }
    }
}
