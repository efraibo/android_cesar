package br.com.evandro.sgce;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private DatabaseHelper helper;

    protected static final int TIMER_RUNTIME = 2000;

    protected boolean mbActive;
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DatabaseHelper(this);
        progressStart();
    }

    public void progressStart(){
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        final Thread timerThread = new Thread(){

            @Override
            public void run(){
                mbActive = true;

                try {
                    int waited = 0;
                    while (mbActive && (waited < TIMER_RUNTIME)){
                        sleep(200);
                        if (mbActive){
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    finish();
                    onContinue();
                }
            }
        };
        timerThread.start();
    }

    public void updateProgress(final int timePassed){
        if (null != mProgressBar){

            final  int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
            mProgressBar.setProgress(progress);
        }
    }

    public void onContinue(){
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }
}
