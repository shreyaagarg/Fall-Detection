package com.example.gagandeepchugh.falldetection;

/**
 * Created by Gagandeep Chugh on 30-07-2017.
 */



    import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLOutput;

    public class Main2Activity extends AppCompatActivity {
        EditText phone;
        Button delete;
        int j=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            phone = (EditText) findViewById(R.id.editText);
            delete = (Button) findViewById(R.id.del);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Editable hi = phone.getText();
                    SharedPreferences pref = getSharedPreferences("REFS_NAME",MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();
                    String [] numbers = new String[20];
                    numbers=pref.getString("key","").split(",");
                    String[] arr2 =new String[20];

//                System.out.println(pref.getString("key","")+"uygghv");
                    for(int i=0;i<numbers.length;i++)
                    {
                        if(!numbers[i].equals(hi)){
//                        editor.putString("key",pref.getString("key","")+numbers[i]);
                            arr2[j]=numbers[i];
                            j++;
                        }
                    }
                    for(int i=0;i<arr2.length;i++)
                    {
                        if(i!=arr2.length-1)
                            editor.putString("key",arr2[i]+",");
                        else
                            editor.putString("key",arr2[i]);
                    }
                    System.out.println("oyq");

                    System.out.println(pref.getString("key",""));

                }
            });
        }
    }


