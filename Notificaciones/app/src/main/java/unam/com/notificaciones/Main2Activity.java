package unam.com.notificaciones;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final EditText name = (EditText) findViewById(R.id.etName);
        final EditText desc= (EditText) findViewById(R.id.etDesc);
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("key_name",name.getText().toString());
                i.putExtra("key_desc",desc.getText().toString());
                setResult(RESULT_OK,i);
                finish();

            }
        });


    }

}
