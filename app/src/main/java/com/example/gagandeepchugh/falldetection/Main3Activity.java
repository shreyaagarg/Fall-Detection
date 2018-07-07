package com.example.gagandeepchugh.falldetection;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    ArrayList<String> nos = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        lv = (ListView) findViewById(R.id.list_a);

        SharedPreferences pref = getSharedPreferences("REFS_NAME",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        String[] some = new String[20];
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nos);
        some=pref.getString("key","").split(",");
        for(int i=0;i<some.length;i++)
        {
            nos.add(some[i]);
        }
        adapter.notifyDataSetChanged();
        lv.setAdapter (adapter); ;

    }
}