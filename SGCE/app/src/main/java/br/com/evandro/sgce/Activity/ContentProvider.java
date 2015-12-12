package br.com.evandro.sgce.Activity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class ContentProvider {

    static String[] projecao = new String[] { ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME };


    public static ArrayList<String> getContatos(Context mContext){

        ArrayList<String> contatos = new ArrayList<String>();

        //COntato filtrado por numero
        //Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //String incomingNumber = "1234567890";
        // Uri lookupUri =	Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, incomingNumber);

        //Pegar todos os contatos
        Uri lookupUri =	ContactsContract.Contacts.CONTENT_URI;
        //String orderBy = Contacts.DISPLAY_NAME + " ASC";

        //String where = ContactsContract.Contacts._ID + " =  1";

        Cursor cursor = mContext.getContentResolver()
                .query(lookupUri, projecao, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)) + "-"+ cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contatos.add(name);

            }
        }

        return contatos;

    }

}
