package performance.zenkun.com.class1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hacke on 09/06/2016.
 */
public class ActivityContent extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //no necesitamos hacer cast por que solo necesitamos el onClickListener
        findViewById(R.id.btnFragment1).setOnClickListener(this);
        findViewById(R.id.btnFragment2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnFragment1:
                setFragment();
                break;
            case R.id.btnFragment2:
                setFragment();
                break;
        }

    }

    private void setFragment() {
    }


}
