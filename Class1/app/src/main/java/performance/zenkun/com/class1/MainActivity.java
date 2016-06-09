package performance.zenkun.com.class1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUser;
    private EditText mPassword;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
                processLogin();
                break;
        }
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
        if(user.equals("unam") && password.equals("contraseña"))
        {
            //iniciamos la actividad con contenido
            startActivity(new Intent(getApplicationContext(),ActivityContent.class));
        }else
            showError(R.string.wrong_credentials);
    }

    private void showError(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }
}
