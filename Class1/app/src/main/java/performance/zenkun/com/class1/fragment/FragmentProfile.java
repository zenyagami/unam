package performance.zenkun.com.class1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import performance.zenkun.com.class1.R;

/**
 * Created by hacke on 09/06/2016.
 */
public class FragmentProfile extends Fragment {

    public static FragmentProfile newInstance(String user)
    {
        FragmentProfile f= new FragmentProfile();
        Bundle b = new Bundle();
        b.putString("key_user",user);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        TextView mUser = (TextView) view.findViewById(R.id.txtUsername);
        String userName = getArguments().getString("key_user","Unknown");
        userName= String.format("USER:\n%s",userName);
        mUser.setText(userName);
        return view;
    }
}
