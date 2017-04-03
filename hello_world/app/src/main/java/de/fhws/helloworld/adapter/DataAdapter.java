package de.fhws.helloworld.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhws.helloworld.R;

public class DataAdapter extends BaseAdapter {

    private Context mContext;

    private final ArrayList mData;

    public DataAdapter(final Context context, final ArrayList data){
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(final int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(mContext).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView headLine = (TextView) convertView.findViewById(R.id.headline);
        TextView subHeadline = (TextView) convertView.findViewById(R.id.subheadline);
        // Populate the data into the template view using the data object
        headLine.setText((String)getItem(position));
        subHeadline.setText("Some additional text...");
        // Return the completed view to render on screen
        return convertView;
    }
}
