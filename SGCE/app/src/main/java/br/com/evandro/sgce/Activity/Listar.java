package br.com.evandro.sgce.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.evandro.sgce.Database.DatabaseHelper;
import br.com.evandro.sgce.R;
import br.com.evandro.sgce.base.Usuario;


public class Listar extends Activity {

    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        helper = new DatabaseHelper(this);
        listarCadastro();
    }

    private void listarCadastro() {
        ListView listView = (ListView) findViewById(R.id.listarCadastro);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query(Usuario.TABELA_USUARIOS, new String[]{Usuario.ID, Usuario.ENDERECO, Usuario.NUMERO},
                null, null, null, null, null);

        final List<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario usuario;

        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++){
            usuario = new Usuario();
            usuario.setId(c.getString(c.getColumnIndex(Usuario.ID)));
            usuario.setEndereco(c.getString(c.getColumnIndex(Usuario.ENDERECO)));
            usuario.setNumero(c.getString(c.getColumnIndex(Usuario.NUMERO)));
            usuarios.add(usuario);
            c.moveToNext();
        }
        c.close();
        String [] cadastros = new String[usuarios.size()];
        for(int i = 0; i < usuarios.size(); i++){
            cadastros[i] = usuarios.get(i).getEndereco();
            cadastros[i] = usuarios.get(i).getNumero();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, cadastros);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String endereco = (String) parent.getAdapter().getItem(position);
                String numero = (String) parent.getAdapter().getItem(position);
                for (Usuario cont : usuarios) {
                    if (cont.getEndereco().equals(endereco) && cont.getNumero().equals(numero)) {
                        Intent it = new Intent(getApplicationContext(), Editar.class);
                        it.putExtra("usuario", cont);
                        startActivity(it);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
