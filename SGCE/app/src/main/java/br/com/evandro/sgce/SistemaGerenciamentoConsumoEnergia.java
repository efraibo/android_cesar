package br.com.evandro.sgce;

import android.app.Application;
import android.util.Log;

public class SistemaGerenciamentoConsumoEnergia extends Application {
    private static String TAG = "SistemaGerenciamentoConsumoEnergia";
    private static SistemaGerenciamentoConsumoEnergia instance = null;

    // Retornando a instancia da aplicação
    private static SistemaGerenciamentoConsumoEnergia getInstance (){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + " onCreate");
        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, TAG + " onTerminate");
    }
}
