package performance.zenkun.com.class1;

import android.app.Fragment;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import performance.zenkun.com.class1.fragment.FragmentItem;
import performance.zenkun.com.class1.fragment.FragmentProfile;

/**
 * Created by hacke on 09/06/2016.
 */
public class ActivityContent extends AppCompatActivity implements View.OnClickListener {
    private String user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        //no necesitamos hacer cast por que solo necesitamos el onClickListener
        findViewById(R.id.btnFragment1).setOnClickListener(this);
        findViewById(R.id.btnFragment2).setOnClickListener(this);
        user = getIntent().getExtras().getString("user");
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


}
