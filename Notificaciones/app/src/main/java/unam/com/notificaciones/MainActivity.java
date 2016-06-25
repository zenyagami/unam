package unam.com.notificaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        }else
            super.onActivityResult(requestCode, resultCode, data);
    }
}
