package br.com.evandro.sgce.Activity;

import android.app.Activity;
import android.os.Bundle;

import br.com.evandro.sgce.Database.DatabaseHelper;
import br.com.evandro.sgce.R;

public class Relatorio extends Activity {

    private DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        helper = new DatabaseHelper(this);
        //listarClientes();
        
    }

    /*private void listarClientes() {
        ListView listView = (ListView) findViewById(R.id.listViewClientes);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query(Cliente.TABELA_CLIENTES, new String[]{Cliente.ID, Cliente.NOME},
                null, null, null, null, null);

        final List<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente;

        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++){
            cliente = new Cliente();
            cliente.setId(c.getString(c.getColumnIndex(Cliente.ID)));
            cliente.setNome(c.getString(c.getColumnIndex(Cliente.NOME)));
            clientes.add(cliente);
            c.moveToNext();
        }
        c.close();
        String [] nomes = new String[clientes.size()];
        for(int i = 0; i < clientes.size(); i++){
            nomes[i] = clientes.get(i).getNome();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nomes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nome = (String) parent.getAdapter().getItem(position);
                for (Cliente cont : clientes) {
                    if (cont.getNome().equals(nome)) {
                        Intent it = new Intent(getApplicationContext(), ClienteEditar.class);
                        it.putExtra("cliente", cont);
                        startActivity(it);
                    }
                }
            }
        });
    }*/

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
