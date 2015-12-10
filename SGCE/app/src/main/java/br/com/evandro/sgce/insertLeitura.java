package br.com.evandro.sgce;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class insertLeitura extends Activity {

    private int ano, mes, dia;
    private Button dataConsumo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_leitura);


        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        dataConsumo = (Button) findViewById(R.id.btnData);
        dataConsumo.setText(dia + "/" + (mes+1) + "/" + ano);
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
}
