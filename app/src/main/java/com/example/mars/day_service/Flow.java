package com.example.mars.day_service;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TextView;

public class Flow extends Thread{
    TextView textView;
    Activity activity;
    Resources res;
    MyService service;
    final static String MY_ACTION = "MY_ACTION";
    Flow(MyService service){
        super("Второй поток");
        this.service = service;
     //   this.textView = textView;
      //  this.activity = activity;
       // res = this.activity.getResources();
        System.out.println("Создан второй поток " + this);
        start(); // Запускаем поток
    }

    public void run() {
        try {
            for (int i = 0; i < 60; i++) {
                System.out.println("Второй поток: " + i);
                setTextView("Второй поток: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Второй поток прерван");
            setTextView(res.getString(R.string.flow_stop));
        }
        System.out.println("Второй поток завершён");
        setTextView(res.getString(R.string.flow_end));
    }

    private void setTextView(String text){

        Intent intent = new Intent();
        intent.setAction(MY_ACTION);
        intent.putExtra("DATAPASSED", text);
        service.sendBroadcast(intent);
      /*  activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(_t);
            }
        });*/
    }
}