package br.com.evandro.sgce.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ProgressBar;

import br.com.evandro.sgce.Database.DatabaseHelper;
import br.com.evandro.sgce.R;

public class MainActivity extends Activity {
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressStart(this);
    }

    public void progressStart(final Context context){
        // Pegando o o progressBar
        // Criando uma nova thread necessaria para execução
        ProgressBar mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        new Thread(){
            @Override
            public void run(){
                try {
                    // Apenas pra quando for muito rápido mostrar pelo menos um segundo a tela inicial
                    sleep(1000);
                    Class classe;

                    // Conexão com o banco
                    SQLiteDatabase db =
                            new DatabaseHelper(context).getReadableDatabase();

                    // Executando consulta
                    Cursor cursor =
                            db.rawQuery("SELECT * FROM usuarios",null);

                    if(cursor.getCount() > 0){
                        classe = insertLeitura.class;
                    }
                    else {
                        classe = Cadastro.class;
                    }

                    finish();
                    startActivity(new Intent(context, classe));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
