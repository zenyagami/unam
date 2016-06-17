package performance.zenkun.com.class1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import performance.zenkun.com.class1.model.ModelUser;
import performance.zenkun.com.class1.preferences.PreferencesUtil;
import service.ServiceCounter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUser;
    private EditText mPassword;
    private PreferencesUtil preferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //obtenemos el ID de cada view
        mUser = (EditText) findViewById(R.id.mUser);
        mPassword= (EditText) findViewById(R.id.mPassword);
        //solo necesitamos obtener el OnClickListener y este existe desde la clase padre View
        //entonces no necesitamos hacer casting
        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
        preferencesUtil= new PreferencesUtil(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
                processLogin();
                break;
            case R.id.btnRegister:
                startRegister();
                break;
        }
    }

    private void startRegister() {
        startActivity(new Intent(getApplicationContext(),ActivityRegister.class));
    }

    private void processLogin() {
        //no nececitamos validar si mUser o mPassword ==null, nunca será nulo si fue "inflado" en el setcontentview
        String user =mUser.getText().toString();
        String password =mPassword.getText().toString();
        if(TextUtils.isEmpty(user))
            showError(R.string.login_empty);
        else if(TextUtils.isEmpty(password))
            showError(R.string.password_empty);
        else
           validateLogin(user,password);
    }

    private void validateLogin(String user, String password) {
        ModelUser modelUser = preferencesUtil.getUser();
        if(modelUser==null)
        {
            //no se ha registrado
            showError(R.string.need_registration);

        }else if(user.equals(modelUser.username) && password.equals(modelUser.password))
        {
            //iniciamos la actividad con contenido
            startService(new Intent(getApplicationContext(),ServiceCounter.class));
            startActivity(new Intent(getApplicationContext(),ActivityContent.class).putExtra("user",user));
        }else
            showError(R.string.wrong_credentials);
    }

    private void showError(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }


}
