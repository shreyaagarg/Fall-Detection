package com.example.gagandeepchugh.falldetection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

Button b1,b2,b3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.button);
        b3 = (Button) findViewById(R.id.b2);
        /*SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        SharedPreferences.Editor editor = pref.edit();

        String number=pref.getString("phone1","");
        String num2=pref.getString("phone2","");
        String num3=pref.getString("phone3","");
        String num4=pref.getString("phone4","");
        if(!number.equalsIgnoreCase(""))
        {
            b1.setVisibility(View.GONE);
        }*/
       /* b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,frame2.class);
                        startActivity(intent);

            }
        });*/
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(MainActivity.this,frame2.class);
                        startActivity(intent1);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,frame3.class);
                startActivity(intent);

            }
        });
    }
}
