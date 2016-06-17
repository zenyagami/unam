package performance.zenkun.com.class1;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import performance.zenkun.com.class1.fragment.FragmentItem;
import performance.zenkun.com.class1.fragment.FragmentProfile;
import service.ServiceCounter;

/**
 * Created by hacke on 09/06/2016.
 */
public class ActivityContent extends AppCompatActivity implements View.OnClickListener {
    private String user;
    private TextView txtSession;

    private void log(String message)
    {
        Log.d("unan",message);
    }
    private BroadcastReceiver mBroadcastReceiver= new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            log("onReceive");
            txtSession.setText(String.valueOf(intent.getExtras().getLong("time")));

        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //no necesitamos hacer cast por que solo necesitamos el onClickListener
        findViewById(R.id.btnFragment1).setOnClickListener(this);
        findViewById(R.id.btnFragment2).setOnClickListener(this);
        user = getIntent().getExtras().getString("user");
        txtSession = (TextView) findViewById(R.id.txtSessionLenght);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnFragment1:
                setFragment(FragmentProfile.newInstance(user));
                break;
            case R.id.btnFragment2:
                setFragment(new FragmentItem());
                break;
        }

    }

    private void setFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragmentContent,fragment).commit();
    }

    @Override protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver,new IntentFilter(ServiceCounter.BROADCAST_SEND_TIME));
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getApplicationContext(),ServiceCounter.class));
    }

    @Override protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }
}
