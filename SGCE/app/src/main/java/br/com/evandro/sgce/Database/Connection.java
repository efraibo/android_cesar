package br.com.evandro.sgce.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Connection {
    Context context = null;
    SQLiteDatabase db;
    Cursor cursor;
    DatabaseHelper connection = null;

    // Instanciando uma conexao
    public Connection(Context context){
        this.context = context;
        connection = openConexao();
    }

    private DatabaseHelper openConexao(){
        return new DatabaseHelper(context);
    }

    private void closeConexao (){
        connection.close();
    }
}
