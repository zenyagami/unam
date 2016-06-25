package unam.com.notificaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import unam.com.notificaciones.service.ServiceNotification;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    private TextView txtName;
    private TextView txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnNotif).setOnClickListener(this);
        findViewById(R.id.btnEdit).setOnClickListener(this);
        txtName = (TextView)findViewById(R.id.txtNombre);
        txtDesc = (TextView)findViewById(R.id.txtDescription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(android.R.drawable.sym_def_app_icon);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.ic_stat_action_perm_identity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnNotif:
                startService(new Intent(getApplicationContext(), ServiceNotification.class));
                break;
            case R.id.btnEdit:
                startActivityForResult(
                        new Intent(getApplicationContext(),Main2Activity.class)
                        ,REQUEST_CODE_SECOND_ACTIVITY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CODE_SECOND_ACTIVITY==requestCode && resultCode==RESULT_OK)
        {
            String name = data.getExtras().getString("key_name","");
            String desc = data.getExtras().getString("key_desc","");
            txtName.setText(name);
            txtDesc.setText(desc);
            getSupportActionBar().setTitle(name);
        }else
            super.onActivityResult(requestCode, resultCode, data);
    }
}
