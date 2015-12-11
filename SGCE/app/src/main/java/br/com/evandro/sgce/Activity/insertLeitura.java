package br.com.evandro.sgce.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import br.com.evandro.sgce.Controllers.Contador;
import br.com.evandro.sgce.Database.DatabaseHelper;
import br.com.evandro.sgce.DefaultActivity;
import br.com.evandro.sgce.R;

public class insertLeitura extends DefaultActivity {
    private DatabaseHelper helper;

    Contador contador;
    Button btnInserirData;
    EditText editInserirLeitura;
    private int ano, mes, dia;
    private Button dataConsumo;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_leitura);
        editInserirLeitura = (EditText) findViewById(R.id.editLeitura);
        btnInserirData = (Button) findViewById(R.id.btnData);

        calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataConsumo = (Button) findViewById(R.id.btnData);
        dataConsumo.setText(dia + "/" + (mes+1) + "/" + ano);
    }

    public void salvarContador(View view){

        contador = new Contador();
        contador.setData(btnInserirData.getText().toString());
        contador.setNumero_leitura(editInserirLeitura.getText().toString());

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

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

    private void clearCampos (){
        editInserirLeitura.setText("");
    }

}
