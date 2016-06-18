package com.unam.clase1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.unam.clase1.model.ModelUser;
import com.unam.clase1.util.PreferenceUtil;

/**
 * Created by hacke on 17/06/2016.
 */
public class ActivityRegister extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText mUser = (EditText) findViewById(R.id.mUserRegister);
        final EditText mPassword = (EditText) findViewById(R.id.mPasswordRegister);
        findViewById(R.id.btnRegisterUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String mUserName = mUser.getText().toString();
             String password= mPassword.getText().toString();
             //salvar al disco ;)
                //TODO validar si vienen vacios
                PreferenceUtil util = new PreferenceUtil(getApplicationContext());
                util.saveUser(new ModelUser(mUserName,password));
                finish();


            }
        });
    }
}
