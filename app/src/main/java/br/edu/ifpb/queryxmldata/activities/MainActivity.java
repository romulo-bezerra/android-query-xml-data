package br.edu.ifpb.queryxmldata.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import br.edu.ifpb.queryxmldata.R;
import br.edu.ifpb.queryxmldata.adapters.LinhaScreenNewsAdapter;
import br.edu.ifpb.queryxmldata.services.ServicePullFeed;
import br.edu.ifpb.queryxmldata.valueObjects.Message;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    TimerTask timerTask;

    final Handler handler = new Handler();

    private BroadcastReceiver brPullFeed = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Message> responseReceiver = (ArrayList<Message>) intent.getSerializableExtra("messages");

            ListView listView = findViewById(R.id.lvNoticias);
            ArrayAdapter adapter = new LinhaScreenNewsAdapter(context, responseReceiver);
            listView.setAdapter(adapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(brPullFeed, new IntentFilter("INTENT_PULLFEED"));
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, 10000); //
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
                        final String strDate = simpleDateFormat.format(calendar.getTime());

                        //show the toast
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), strDate, duration);
                        toast.show();
                    }
                });
            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();
        Intent intent = new Intent(this, ServicePullFeed.class);
        intent.setAction("INTENT_PULLFEED");
        startService(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        startTimer();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(brPullFeed);
    }
}
