package performance.zenkun.com.class1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import performance.zenkun.com.class1.R;

/**
 * Created by hacke on 09/06/2016.
 */
//nótese que es Fragment from app.Fragment y no de support-v4.app.Fragment
public class FragmentItem extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_item,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
