package br.com.evandro.sgce.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.evandro.sgce.R;

public class ContentProviderMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        ListView list = (ListView) findViewById(R.id.listContatos);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ContentProvider.getContatos(this));

        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos,
                                    long arg3) {

                String item = (String) adapterView.getItemAtPosition(pos);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://com.android.contacts/contacts/" + item.split("-")[0]));
                startActivity(intent);
            }

        });

    }

}
