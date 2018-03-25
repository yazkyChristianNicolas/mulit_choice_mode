package ar.com.yazkychristian.multichoicemodeexample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ar.com.yazkychristian.multichoicemodeexample.R;
import ar.com.yazkychristian.multichoicemodeexample.vo.Item;

/**
 * Created by Christian on 24/03/2018.
 */

public class ItemArrayAdapter extends ArrayAdapter<Item> {
    private Context mContext;

    private static class ViewHolder {
        private TextView name, description;
    }

    public ItemArrayAdapter(@NonNull Context context, @NonNull List<Item> objects) {
        super(context, R.layout.item_layout, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item currentItem = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
            setUpViewHolder(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        fillViewHolder(viewHolder, currentItem, position);

        return convertView;
    }


    private void setUpViewHolder(ViewHolder viewHolder, View convertView){
        viewHolder.name = convertView.findViewById(R.id.name_textView);
        viewHolder.description = convertView.findViewById(R.id.description_textView);
    }

    private void fillViewHolder(final ViewHolder viewHolder,final Item item,final int position){
        viewHolder.name.setText(item.getName());
        viewHolder.description.setText(item.getDescription());
    }
}
