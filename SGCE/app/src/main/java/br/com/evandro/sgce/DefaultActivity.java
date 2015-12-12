package br.com.evandro.sgce;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;

public class DefaultActivity extends Activity {

    public void logout (){
        // Criando o alert
        AlertDialog.Builder alertDialog =
                new AlertDialog.Builder(this);
        alertDialog.setTitle("Cancelar");
        alertDialog.setMessage("Deseja realmente sair do SGCE ?");

        // Botão de confirmação
        alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Finalizando a activity
                // passando um intent anonimo para fehcar aplicação direcionando para a home do dipositivo
                finish();
                startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        // Botão cancelamento
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Cria a dialog e exibe
        alertDialog.create().show();
    }

    public boolean validarCampos(EditText editText){
        if (!String.valueOf(editText.getText()).isEmpty()){
            return true;
        }
        return false;
    }
}
