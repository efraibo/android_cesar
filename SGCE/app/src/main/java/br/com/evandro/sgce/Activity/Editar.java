package br.com.evandro.sgce.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.evandro.sgce.Database.DatabaseHelper;
import br.com.evandro.sgce.R;
import br.com.evandro.sgce.base.Usuario;

public class Editar extends Activity {

    private Usuario usuario;
    private DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        String temp = (String) getIntent().getStringExtra("usuario");
        usuario = new Usuario();

        String endereco = temp.split(" - ")[0];
        String numero = temp.split(" - ")[1];
        usuario.setEndereco(endereco);
        usuario.setNumero(numero);

        helper = new DatabaseHelper(this);

        preencherCampos();
    }

    private void preencherCampos() {
        ((EditText)findViewById(R.id.editEnderecoEditar)).setText(usuario.getEndereco());
        ((EditText)findViewById(R.id.editNumRelogioEditar)).setText(usuario.getNumero());
    }

    public void editar(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Usuario.ENDERECO, ((EditText)findViewById(R.id.editEnderecoEditar)).getText().toString());
        values.put(Usuario.NUMERO, ((EditText) findViewById(R.id.editNumRelogioEditar)).getText().toString());


        long resultado = db.update(Usuario.TABELA_USUARIOS, values, "id=?", new String[]{usuario.getId()});

        if(resultado != -1 ){
            Toast.makeText(this, "Usuario Editado com Sucesso!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Não foi possível alterar usuario!",
                    Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(this, insertLeitura.class));
    }

    public void apagar(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        long resultado = db.delete(Usuario.TABELA_USUARIOS, "id=?", new String[]{usuario.getId()});

        if(resultado != -1 ){
            Toast.makeText(this, "Contato apagado!",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Não foi possível apagar o contato",
                    Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(this, insertLeitura.class));
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
