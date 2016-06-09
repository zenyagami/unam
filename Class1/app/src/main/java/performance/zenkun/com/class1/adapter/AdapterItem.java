package performance.zenkun.com.class1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import performance.zenkun.com.class1.R;
import performance.zenkun.com.class1.model.ModelItem;

/**
 * Created by hacke on 09/06/2016.
 */
public class AdapterItem extends ArrayAdapter<ModelItem> {


    public AdapterItem(Context context, List<ModelItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_row,parent,false);

        TextView id = (TextView) convertView.findViewById(R.id.row_item_id);
        TextView item = (TextView) convertView.findViewById(R.id.row_item_name);
        ModelItem data = (ModelItem) getItem(position);
        id.setText(String.valueOf(data.id));
        item.setText(data.name);

        return convertView;
    }
}
