package br.com.evandro.sgce;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends Activity {

    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        helper = new DatabaseHelper(this);
    }
    /*
    public void dataButton(View view){
        DateDialog dialog = new DateDialog(view);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft, "DatePicker");
    }*/

    public void salvar(View view){
        Cliente cliente = new Cliente();
        String nome = ((EditText) findViewById(R.id.editNome)).getText().toString();

        cliente.setNome(nome);

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Cliente.NOME, cliente.getNome());


        long resultado = db.insert(Cliente.TABELA_CLIENTES, null, values);

        if(resultado != -1 ){
            Toast.makeText(this, "Cadastrado com Sucesso!",
                    Toast.LENGTH_SHORT).show();

            //limpando editText
            ((EditText) findViewById(R.id.editNome)).setText("");


        }else{
            Toast.makeText(this, "Não foi possível salvar o cliente",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void listarTodos(View view){
        Intent i = new Intent(this, Relatorio.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }


}
