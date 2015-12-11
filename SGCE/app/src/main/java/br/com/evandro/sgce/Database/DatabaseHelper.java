package br.com.evandro.sgce.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.evandro.sgce.Controllers.Contador;
import br.com.evandro.sgce.Controllers.Usuario;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static int VERSAO = 1;
    private static final String BANCO_DADOS = "sgce_db";

    public DatabaseHelper (Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Usuario.TABELA_USUARIOS +
                "(" + Usuario.ID + " INTEGER PRIMARY KEY," + Usuario.ENDERECO + " TEXT, "
                + Usuario.NUMERO + " TEXT)");

        db.execSQL("CREATE TABLE " + Contador.TABELA_CONTADOR +
                "(" + Contador.ID + " INTEGER PRIMARY KEY," + Contador.DATA + " TEXT, "
                + Contador.NUMERO_LEITURA + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Usuario.TABELA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS" + Contador.TABELA_CONTADOR);
        onCreate(db);
    }

}
