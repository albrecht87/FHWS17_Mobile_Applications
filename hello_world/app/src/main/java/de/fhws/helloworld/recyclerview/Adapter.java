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

    class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView mImageView;

        final TextView mTitle;

        ViewHolder(final View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.headline);
            mImageView = (ImageView) itemView.findViewById(R.id.card_image);
        }

        void setClickListener(final int position) {
            final View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mListener.onItemClick(position);
                }
            };
            mTitle.setOnClickListener(onClickListener);
            mImageView.setOnClickListener(onClickListener);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    public interface OnBottomReachListener {

        void onBottom(int postition);
    }

    private static final String TAG = Adapter.class.getSimpleName();

    private ArrayList<String> mData;

    private final OnItemClickListener mListener;

    private final OnBottomReachListener mOnBottomReachListener;


    public Adapter(final OnItemClickListener listener, final ArrayList data,
            final OnBottomReachListener onBottomReachListener) {
        mListener = listener;
        mData = data;
        mOnBottomReachListener = onBottomReachListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position >= getItemCount() - 1) {
            mOnBottomReachListener.onBottom(position);
        }

        holder.mTitle.setText(mData.get(position));
        final Picasso picasso = Picasso.with(holder.mImageView.getContext());
        picasso.setLoggingEnabled(true);
        picasso.load("https://source.unsplash.com/random/800x600&raandom=" + position)
                .stableKey("" + position)
                .into(holder.mImageView);
        holder.setClickListener(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card, parent, false);
        return new ViewHolder(view);
    }
}
