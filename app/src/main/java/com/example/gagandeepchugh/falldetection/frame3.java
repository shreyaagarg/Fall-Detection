package com.example.gagandeepchugh.falldetection;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class frame3 extends AppCompatActivity {
        private SensorManager sensorManager;
        int i,bell = 0;
        double earth_range = 0;
        double earth_prev = 0;
        float[] gravityValues = null, magneticValues = null, earthvalues = new float[3];
        float[] Ri = new float[16], I = new float[16], earthAcc = new float[16], inv = new float[16];
        double y_gra=0;
        double z_gra=0;
        long start  , time;
        int as,dc,fs,fd = 0;
        String ac = null;
        RelativeLayout rl;
        Button b5,b6;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.frame3);
            rl = (RelativeLayout) findViewById(R.id.fr3);
            //rl.setBackgroundColor(Color.GREEN);
            b5 = (Button) findViewById(R.id.button3);
            b6 = (Button) findViewById(R.id.button4);
            time = System.currentTimeMillis();


            sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
            sensorManager.registerListener(sensorlistener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
            sensorManager.registerListener(sensorlistener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_FASTEST);
            sensorManager.registerListener(sensorlistener, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_FASTEST);

        }

        SensorEventListener sensorlistener = new SensorEventListener() {

            public void onAccuracyChanged(Sensor arg0, int arg1) {
            }


            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                try {
                    switch (sensor.getType()) {
                        case Sensor.TYPE_MAGNETIC_FIELD:
                            magneticValues = event.values;
                            break;
                        case Sensor.TYPE_GRAVITY:
                            y_gra = event.values[1];
                            z_gra = event.values[2];
                            gravityValues = event.values;
                            break;
                    }
                    if ((gravityValues != null) && (magneticValues != null)
                            && (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)) {
                        float[] deviceRelativeAcceleration = new float[4];
                        deviceRelativeAcceleration[0] = event.values[0];
                        deviceRelativeAcceleration[1] = event.values[1];
                        deviceRelativeAcceleration[2] = event.values[2];
                        deviceRelativeAcceleration[3] = 0;

                        SensorManager.getRotationMatrix(Ri, I, gravityValues, magneticValues);
                        earthAcc = new float[16];




                        android.opengl.Matrix.invertM(inv, 0, Ri, 0);
                        android.opengl.Matrix.multiplyMV(earthAcc, 0, inv, 0, deviceRelativeAcceleration, 0);
                        List<Float> earth_d = new ArrayList<>();


                        if (System.currentTimeMillis() <= time + 30000) {

                            if (earthAcc[2] > 15) {
                                as++;
                                start = System.currentTimeMillis();

                            }


                            if (as >0 && as <= 5){

                                if(earthAcc[2]<=11)
                                {
                                    if (y_gra>7 && z_gra<5) {
                                        dc++;

                                        // time = System.currentTimeMillis();
                                        //as = 0;
                                    }
                                }
                                SharedPreferences pref = getSharedPreferences("REFS_NAME",MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();

                                if (dc == 0)
                                {
                                    if (System.currentTimeMillis() >= start + 10000) {
                                        ac = "Fall";
                                       // rl.setBackgroundColor(Color.RED);
                                       // rl.setBackground(getDrawable(R.drawable.alert));
                                        if(fd==0)
                                        {//activate this before final code is submitted
                                        String[] smshere=new String[20];
//                                            String number="9463880165";
                                            smshere = pref.getString("key","").split(",");
                                        String message="Fall has been detected";
                                        SmsManager manager=SmsManager.getDefault();
                                        for(int i=0;i<smshere.length;i++)
                                            manager.sendTextMessage(smshere[i],null,message,null,null);
                                            fd++;
                                            /*try {// to be changed
                                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                                r.play();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }*/


                                        }
                                        if(bell<7) {
                                            try {// to be changed
                                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                                r.play();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            bell++;
                                        }
                                        //a="5";
                                   /*     Toast.makeText(frame3.this, "ghj", Toast.LENGTH_SHORT).show();
                                    MediaPlayer mediaplayer=MediaPlayer.create(getApplicationContext(),R.raw.alarm1);
                                     mediaplayer.prepare();
                                     mediaplayer.start();
                                        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mediaPlayer) {
                                                //mediaPlayer.release();
                                                Toast.makeText(frame3.this, "hjghgjhghj", Toast.LENGTH_SHORT).show();
                                                mediaPlayer=null;
                                            }
                                        });*/

                                    } else
                                    {
                                        if (earthAcc[2] > 15)
                                        {
                                            as++;
                                        }
                                    }
                                }
                                if(dc!=0)
                                {
                                    //should show a message on screen..
                                    //that are you alright
                                   // rl.setBackgroundColor(Color.YELLOW);
                                   // RelativeLayout h= (RelativeLayout) findViewById(R.id.fr3);
                                    rl.setBackground(getDrawable(R.drawable.normal));
                                    dc=0;
                                    fs=10;
                                }
                            }
                            if (as > 6 && as < 10) {
                                ac = "Walking";
                            } else
                                ac = "Running";

                        } else {
                            time = System.currentTimeMillis();
                            as = 0;
                            fd=0;
                            rl.setBackground(getDrawable(R.drawable.front));
                           // rl.setBackgroundColor(Color.GREEN);
                        }
                        b5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                time = System.currentTimeMillis();
                                as = 0;
                                fd=0;
                                //rl.setBackgroundColor(Color.GREEN);
                                rl.setBackground(getDrawable(R.drawable.front));
                            }
                        });
                        b6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                String number= "9463880165";
//                                String message="Fall has been detected";
//                                SmsManager manager=SmsManager.getDefault();
//                                manager.sendTextMessage(number,null,message,null,null);
                                String[] smshere=new String[20];
//                                            String number="9463880165";
                                SharedPreferences pref = getSharedPreferences("REFS_NAME",MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();

                                smshere = pref.getString("key","").split(",");
                                String message="Fall has been detected";
                                SmsManager manager=SmsManager.getDefault();
                                for(int i=0;i<smshere.length;i++)
                                    manager.sendTextMessage(smshere[i],null,message,null,null);

                            }
                        });


                    }
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
            }
        };

        public void onStop() {
            super.onStop();
            sensorManager.unregisterListener(sensorlistener);
        }




    }




