package performance.zenkun.com.class1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;
import performance.zenkun.com.class1.R;
import performance.zenkun.com.class1.adapter.AdapterItem;
import performance.zenkun.com.class1.model.ModelItem;
import performance.zenkun.com.class1.sql.ItemDataSource;

/**
 * Created by hacke on 09/06/2016.
 */
//nótese que es Fragment from app.Fragment y no de support-v4.app.Fragment
public class FragmentItem extends Fragment implements View.OnClickListener {
    private ListView lv;
    private EditText mItem;
    private ItemDataSource itemDataSource;
   // private ArrayList<ModelItem> itemList= new ArrayList<>();
    private int counter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDataSource= new ItemDataSource(getActivity());
      List<ModelItem> items = itemDataSource.getAllItems();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_item,container,false);
        view.findViewById(R.id.btnAddItem).setOnClickListener(this);
        lv = (ListView) view.findViewById(R.id.itemList);
        mItem = (EditText) view.findViewById(R.id.mIteDesc);
        lv.setAdapter(new AdapterItem(getActivity(),itemDataSource.getAllItems()));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnAddItem:
                addItem();
        }
    }

    private void addItem() {
        String item= mItem.getText().toString();
        if(TextUtils.isEmpty(item))
            Toast.makeText(getActivity(),R.string.item_empty,Toast.LENGTH_SHORT).show();
        else
        {
            mItem.setText("");
            ModelItem m = new ModelItem();
            m.id=counter;
            m.name =item;
            itemDataSource.saveItem(m);
            lv.setAdapter(new AdapterItem(getActivity(),itemDataSource.getAllItems()));
            counter++;
        }
    }
}
