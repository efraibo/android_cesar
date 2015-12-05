package br.com.evandro.sgce;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText editDate;
    public DateDialog(View view){
        editDate=(EditText)view;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c=Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        return  new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }

    public  void  onDateSet(DatePicker view, int ano, int mes, int dia){
        String date = dia+"-"+(mes+1)+"-"+ano;
        editDate.setText(date);
    }
}
