package com.unam.clase1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unam.clase1.model.ModelUser;
import com.unam.clase1.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUser;
    private EditText mPassword;
    private View loading;
    private PreferenceUtil preferenceUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser= (EditText) findViewById(R.id.activity_main_user);
        mPassword= (EditText) findViewById(R.id.activity_main_password);
        findViewById(R.id.activity_main_login).setOnClickListener(this);
        loading=findViewById(R.id.progress);
        findViewById(R.id.btnRegisterLogin).setOnClickListener(this);
        preferenceUtil= new PreferenceUtil(getApplicationContext());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_main_login:
                processData();
                break;
            case R.id.btnRegisterLogin:
                launchRegister();
                break;
        }
    }

    private void launchRegister() {
        Intent intent=new Intent(getApplicationContext(),ActivityRegister.class);
        startActivity(intent);
    }

    private void processData() {
        final String user = mUser.getText().toString();
        final String pass = mPassword.getText().toString();
        loading.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.GONE);
                ModelUser modelUser = preferenceUtil.getUser();
                if(modelUser==null)
                {
                    Toast.makeText(getApplicationContext(),"You need register",Toast.LENGTH_SHORT).show();
                }else if(user.equals(modelUser.userName) && pass.equals(modelUser.password))
                {
                    Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),ActivityDetail.class);
                    intent.putExtra("key_user",user);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        },1000*1);


        //Toast.makeText(getApplicationContext(),)
    }
}
