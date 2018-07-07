package com.example.gagandeepchugh.falldetection;
import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.data;

public class frame2 extends AppCompatActivity {
    Button Import;int i=0;
    Button remove ;
    Button show;
    int PICK_CONTACT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame2);
        Import = (Button) findViewById(R.id.button);
        remove = (Button) findViewById(R.id.remove);
        show = (Button) findViewById(R.id.button2);
        Import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r_intent=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(r_intent);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showing=new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(showing);
            }
        });
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data){ super.onActivityResult(reqCode, resultCode, data);
        SharedPreferences pref = getSharedPreferences("REFS_NAME",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        switch(reqCode)
        {
            case 1:
                if (resultCode == Activity.RESULT_OK)
                {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst())
                    {
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone =
                                c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1"))
                        {
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                            phones.moveToFirst();
                            String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            // Toast.makeText(getApplicationContext(), cNumber, Toast.LENGTH_SHORT).show();
                            // String pehle=pref.getString("key",null);
                            //pehle=pref.getString("key",null)+cNumber;
                            if(i!=0)          {      editor.putString("key",pref.getString("key","")+","+cNumber);;}
                            else {
                                editor.putString("key",pref.getString("key","")+cNumber);i++;}
                            editor.apply();
                            System.out.println(pref.getString("key",""));
                        }

                    }
                }
        }


    }







}