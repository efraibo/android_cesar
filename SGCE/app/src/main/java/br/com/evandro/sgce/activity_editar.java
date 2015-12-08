package br.com.evandro.sgce;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_editar extends Activity {

    private Cliente cliente;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        cliente = (Cliente) getIntent().getSerializableExtra("cliente");
        helper = new DatabaseHelper(this);
        preencherCampos();
    }

    private void preencherCampos(){
        ((EditText)findViewById(R.id.editNomeEditar)).setText(cliente.getNome());
    }

    public void editar(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Cliente.NOME, ((EditText)findViewById(R.id.editNomeEditar)).getText().toString());


        long resultado = db.update(Cliente.TABELA_CLIENTES, values, "id=?", new String[]{cliente.getId()});

        if(resultado != -1 ){
            Toast.makeText(this, "Cliente salvo!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Não foi possível salvar o cliente",
                    Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(this, Cadastro.class));
    }

    public void apagar(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        long resultado = db.delete(Cliente.TABELA_CLIENTES, "id=?", new String[]{cliente.getId()});

        if(resultado != -1 ){
            Toast.makeText(this, "Clinte excluido com Sucesso!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Não foi possível apagar o cliente",
                    Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(this, Cadastro.class));
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }

}
