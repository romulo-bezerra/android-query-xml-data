package br.edu.ifpb.queryxmldata.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.edu.ifpb.queryxmldata.R;
import br.edu.ifpb.queryxmldata.services.ServicePullFeed;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver brPullFeed = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String responseReceiver = intent.getStringExtra("messages");
            Log.d("Mensagem: ", responseReceiver);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(brPullFeed, new IntentFilter("INTENT_PULLFEED"));
    }

    @Override
    public void onStart(){
        super.onStart();
        Intent intent = new Intent(this, ServicePullFeed.class);
        intent.setAction("INTENT_PULLFEED");
        startService(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(brPullFeed);
    }
}
