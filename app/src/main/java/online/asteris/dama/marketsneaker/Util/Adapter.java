package online.asteris.dama.marketsneaker.Util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import online.asteris.dama.marketsneaker.R;

/**
 * Created by dama on 08/09/2017.
 */

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView item_id = (TextView) convertView.findViewById(R.id.item_id);
        TextView item_name = (TextView) convertView.findViewById(R.id.item_name);
        TextView item_price = (TextView) convertView.findViewById(R.id.item_price);

        Data data = items.get(position);

        item_id.setText(data.getItem_id());
        item_name.setText(data.getItem_name());
        item_price.setText(data.getItem_price());

        return convertView;
    }
}
