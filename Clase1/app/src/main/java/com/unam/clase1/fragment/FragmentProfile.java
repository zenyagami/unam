package com.unam.clase1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unam.clase1.R;

/**
 * Created by hacke on 11/06/2016.
 */
public class FragmentProfile extends Fragment {
    private ImageView imgProfile;
    private boolean change=true;

    public static FragmentProfile newInstance(String name)
    {
        FragmentProfile f = new FragmentProfile();
        Bundle b = new Bundle();
        b.putString("user_key",name);
        f.setArguments(b);
        return f;
    }

    public void changeImage()
    {
        imgProfile.setImageResource(change?R.drawable.ic_device_signal_wifi_4_bar: R.drawable.ic_action_settings_voice);
        change=!change;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        imgProfile = (ImageView) view.findViewById(R.id.imgProfile);
        TextView txt = (TextView) view.findViewById(R.id.txtUserFragment);
        Bundle bundle=getArguments();
        String user ;
        if(bundle!=null)
         user=bundle.getString("user_key");
        else
        user= "XML inflate";

        txt.setText(user);
        return view;
    }
}
