package com.example.mars.day_service;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    TextView textView;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Service test");
        textView = (TextView) findViewById(R.id.textView);



        Log.w("MyApp", "onCreate");
        //flow = new Flow(textView, this);
    }

    @Override
    protected void onStart() {
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyService.MY_ACTION);
        registerReceiver(myReceiver, intentFilter);
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        // unregisterReceiver(myReceiver);
        Log.w("MyApp", "onStop");
        try {
            unregisterReceiver(myReceiver);
        }catch (IllegalArgumentException e){

        }

        super.onStop();
    }

    public void onClickStart(View v) {
        //Start our own service
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }

    public void onClickStop(View v) {
       // unregisterReceiver(myReceiver);
        try {
            unregisterReceiver(myReceiver);
        }catch (IllegalArgumentException e){

        }
        stopService(new Intent(this, MyService.class));
    }


    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
           // int datapassed = arg1.getIntExtra("DATAPASSED", 0);

            String text =  arg1.getExtras().getString("DATAPASSED");
            textView.setText(text);
            /*
            Toast.makeText(MainActivity.this,
                    text,
                    Toast.LENGTH_LONG)
                    .show();*/
            //   textView.setText();
        }
    }
}
