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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import br.edu.ifpb.queryxmldata.R;
import br.edu.ifpb.queryxmldata.adapters.LinhaScreenNewsAdapter;
import br.edu.ifpb.queryxmldata.util.NotificationUtil;
import br.edu.ifpb.queryxmldata.services.ServicePullFeed;
import br.edu.ifpb.queryxmldata.valueObjects.Message;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private TimerTask timerTask;

    private Intent intent;

    private boolean isInitialInstant;
    private ArrayList<Message> messagesComparables;
    private ArrayList<Message> responseReceiver;

    final Handler handler = new Handler();

    private BroadcastReceiver brPullFeed = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            responseReceiver = (ArrayList<Message>) intent.getSerializableExtra("messages");

            ListView listView = findViewById(R.id.lvNoticias);
            ArrayAdapter adapter = new LinhaScreenNewsAdapter(context, responseReceiver);
            listView.setAdapter(adapter);

            if (isInitialInstant) {
                isInitialInstant = false;
                messagesComparables = responseReceiver;
            }else {
                if (!messagesComparables.equals(responseReceiver)){
                    // Cria uma notificação para alertar que a lista de notícias foi atualizada
                    NotificationUtil.create(context, 1, intent, R.mipmap.ic_launcher, "Notícias atualizadas", "A lista de notícias foi atualizada!");
                }
                messagesComparables = responseReceiver;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, ServicePullFeed.class);
        intent.setAction("INTENT_PULLFEED");
        isInitialInstant = true;
        messagesComparables =  new ArrayList<>();
        responseReceiver =  new ArrayList<>();
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 0ms the TimerTask will run every 5m
        timer.schedule(timerTask, 0, 300000); //
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        startService(intent);
                    }
                });
            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();
        startTimer();
    }

    @Override
    public void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(brPullFeed, new IntentFilter("INTENT_PULLFEED"));
    }

    @Override
    public void onStop(){
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(brPullFeed);
    }
}
