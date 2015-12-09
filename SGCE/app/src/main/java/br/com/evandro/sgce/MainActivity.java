package br.com.evandro.sgce;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DatabaseHelper(this);
        //listaCadastrados();
    }







    /*public void listaCadastrados()
    {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCadastrados);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query(Cliente.TABELA_CLIENTES, new String[]{Cliente.NOME},
                null, null, null, null, null);

        final List<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente;

        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++){
            cliente = new Cliente();
            cliente.setNome(c.getString(c.getColumnIndex(Cliente.NOME)));
            clientes.add(cliente);
            c.moveToNext();
        }
        c.close();
        String [] nomes = new String[clientes.size()];
        for(int i = 0; i < clientes.size(); i++){
            nomes[i] = clientes.get(i).getNome();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1, nomes);
        spinner.setAdapter(adapter);
    }*/

    public void cadastrarCliente(View view){
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }
}
