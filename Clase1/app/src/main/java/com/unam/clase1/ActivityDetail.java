package com.unam.clase1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.unam.clase1.fragment.FragmentList;
import com.unam.clase1.fragment.FragmentProfile;

/**
 * Created by hacke on 10/06/2016.
 */
public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String userName=getIntent().getExtras().getString("key_user");
        String hello = String.format(getString(R.string.hello),userName);
        //txt.setText(hello);
        findViewById(R.id.btnFragmentA).setOnClickListener(this);
        findViewById(R.id.btnFragmentB).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnFragmentA:
                changeFragmentA();
                break;
            case R.id.btnFragmentB:
                changeFragmentB();
                break;
        }
    }

    private void changeFragmentB() {
        //FragmentProfile f = FragmentProfile.newInstance(" Adios mundo :(");
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new FragmentList()).commit();
        /*FragmentProfile fragment= (FragmentProfile) getFragmentManager().findFragmentById(R.id.fragment_xml);
        if(fragment!=null)
        {
            fragment.changeImage();
        }*/
    }

    private void changeFragmentA() {
        FragmentProfile f = FragmentProfile.newInstance(" Hola mundo");
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();
    }
}
