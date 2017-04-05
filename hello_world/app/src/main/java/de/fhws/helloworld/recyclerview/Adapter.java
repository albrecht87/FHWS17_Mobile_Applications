package de.fhws.helloworld.recyclerview;


import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhws.helloworld.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView mImageView;

        final TextView mTitle;

        public ViewHolder(final View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.headline);
            mImageView = (ImageView) itemView.findViewById(R.id.card_image);
        }

        public void setData(String title) {
            mTitle.setText(title);
        }
    }

    private ArrayList<String> mData;

    public Adapter(final ArrayList data) {
        mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mData.get(position));
        final Picasso picasso = Picasso.with(holder.mImageView.getContext());
        picasso.setLoggingEnabled(true);
        //picasso.cancelRequest(holder.mImageView);
        //picasso.invalidate("https://source.unsplash.com/random/800x600&raandom="+position);
        picasso.load("https://source.unsplash.com/random/800x600&raandom="+position)
                .stableKey(""+position)
                //.networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE)
                //.memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .into(holder.mImageView);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card, parent, false);
        return new ViewHolder(view);
    }
}
