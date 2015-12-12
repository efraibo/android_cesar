package br.com.evandro.sgce.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import br.com.evandro.sgce.Controllers.Usuario;
import br.com.evandro.sgce.Database.DatabaseHelper;
import br.com.evandro.sgce.DefaultActivity;
import br.com.evandro.sgce.R;

public class Cadastro extends DefaultActivity {
    private DatabaseHelper helper;

    Usuario usuario;
    EditText editTextNumero;
    EditText editTextEndereco;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        helper = new DatabaseHelper(this);

        editTextNumero = (EditText) findViewById(R.id.editNumRelogio);
        editTextEndereco = (EditText) findViewById(R.id.editEndereco);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void salvar(View view) {
        if (validarCampos(editTextNumero) && validarCampos(editTextEndereco)){

            usuario =  new Usuario();
            usuario.setNumero(editTextNumero.toString());
            usuario.setEndereco(editTextEndereco.toString());

            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Usuario.ENDERECO, usuario.getEndereco());
            values.put(Usuario.NUMERO, usuario.getNumero());

            long resultado = db.insert(Usuario.TABELA_USUARIOS, null, values);

            if (resultado != -1) {
                Toast.makeText(this, "Cadastrado com Sucesso!",
                        Toast.LENGTH_SHORT).show();

                finish();
                startActivity(new Intent(this, insertLeitura.class));
            }
            else {
                Toast.makeText(this, "Não foi possível salvar o cliente",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Por favor preencha todos os campos!",
                    Toast.LENGTH_SHORT).show();
        }
    }



    public void listarTodos(View view) {
        Intent i = new Intent(this, Relatorio.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(helper != null){
            helper.close();
        }
    }

    public void cancel(View view) {
        Log.d("Cadastro:", "cancel");
        logout();
    }
}
