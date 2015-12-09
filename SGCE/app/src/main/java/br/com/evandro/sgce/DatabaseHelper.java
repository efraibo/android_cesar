package br.com.evandro.sgce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static int VERSAO = 2;
    private static final String BANCO_DADOS = "sgce_db";

    public DatabaseHelper (Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRIAR_TABELA_CLIENTES = "CREATE TABLE " + Cliente.TABELA_CLIENTES +
                "(" + Cliente.ID + " INTEGER PRIMARY KEY," + Cliente.NOME + " TEXT, "
                + Cliente.NUMERO + " TEXT)";
        db.execSQL(CRIAR_TABELA_CLIENTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
