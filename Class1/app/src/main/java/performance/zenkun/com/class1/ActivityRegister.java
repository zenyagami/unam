package performance.zenkun.com.class1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import performance.zenkun.com.class1.model.ModelUser;
import performance.zenkun.com.class1.preferences.PreferencesUtil;

/**
 * Created by zenkun on 6/17/16. zenyagami@gmail.com
 */

public class ActivityRegister extends AppCompatActivity {
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    final EditText mUser = (EditText) findViewById(R.id.mUserNameRegister);
    final EditText mPassword = (EditText) findViewById(R.id.mPasswordRegister);
    findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //we save in shared preferences
        //TODO validate if is empty
        new PreferencesUtil(getApplicationContext()).saveUser(new ModelUser(mUser.getText().toString(),mPassword.getText().toString()));
        //finalizamos la actividad
        finish();
        //TODO ya estamos registrados deberíamos mandar ya a login
      }
    });
  }
}
