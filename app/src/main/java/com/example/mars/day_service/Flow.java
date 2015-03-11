package com.example.mars.day_service;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TextView;

public class Flow extends Thread {
    MyService service;
    final static String MY_ACTION = "MY_ACTION";

    Flow(MyService service) {
        super("Второй поток");
        this.service = service;

        setTextView(R.string.app_name + "");
        System.out.println("Создан второй поток " + this);
        //start(); // Запускаем поток
    }

    public void run() {
        try {
            int i = 0;
            setTextView("Cтарт");
            Thread.sleep(1000);

            while (true) {
                i++;
                System.out.println("Второй поток: " + i);
                setTextView("" + i);
                Thread.sleep(1000);
            }
            //setTextView(R.string.flow_stop + "");
        } catch (InterruptedException e) {
            System.out.println("Второй поток прерван");
            Thread.currentThread().interrupt();
        }
        System.out.println("Второй поток завершён");
    }

    public void stopFlow() {
        this.interrupt();
    }

    private void setTextView(String text) {

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