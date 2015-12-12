package br.com.evandro.sgce.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import br.com.evandro.sgce.Database.DatabaseHelper;
import br.com.evandro.sgce.DefaultActivity;
import br.com.evandro.sgce.R;
import br.com.evandro.sgce.base.Contador;

public class insertLeitura extends DefaultActivity {
    private DatabaseHelper helper;

    SQLiteDatabase db;
    Contador contador;
    EditText editInserirLeitura;
    Calendar calendar;
    private int ano, mes, dia;
    private Button dataConsumo;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_leitura);

        btnSalvar = (Button) findViewById(R.id.btnSalvarCadastro);
        editInserirLeitura = (EditText) findViewById(R.id.editLeitura);

        calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataConsumo = (Button) findViewById(R.id.btnData);
        dataConsumo.setText(dia + "/" + (mes+1) + "/" + ano);
    }

    public void salvarContador(View view){
        if (!String.valueOf(editInserirLeitura.getText()).isEmpty()){
            helper = new DatabaseHelper(this);
            db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();

            contador = new Contador();
            contador.setData(dataConsumo.getText().toString());
            contador.setNumero_leitura(editInserirLeitura.getText().toString());

            values.put(Contador.DATA, contador.getData());
            values.put(Contador.NUMERO_LEITURA, contador.getNumero_leitura());

            long resultado = db.insert(Contador.TABELA_CONTADOR, null, values);

            if (resultado != -1){
                Toast.makeText(this, "Cadastrado com Sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Não foi possível salvar Contador",
                        Toast.LENGTH_SHORT).show();
            }

            db.close();
            clearCampos(view);
        }
        else {
            Toast.makeText(this, "Por favor preencha todos os campos!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(helper != null){
            helper.close();
        }
    }

    public void selecionarData(View view){
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (R.id.btnData == id){
            return new DatePickerDialog(this,
                    listener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataConsumo.setText(dia + "/" + (mes+1) + "/" + ano);
        }
    };

    public void clearCampos (View view){
        editInserirLeitura.setText("");
    }

    public void chamarAgenda(View view){
        Intent i = new Intent(this, ContentProviderMain.class);
        startActivity(i);
    }

    public void editarCadatro(View view) {

    }
}
