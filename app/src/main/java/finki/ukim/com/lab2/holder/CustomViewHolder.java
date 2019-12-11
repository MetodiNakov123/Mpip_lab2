package finki.ukim.com.lab2.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import finki.ukim.com.lab2.R;
import finki.ukim.com.lab2.model.OmdbShortItem;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    private ImageView mImageView;
    private TextView mTitleTextView;
    private TextView mYearTextView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
        mImageView = itemView.findViewById(R.id.imageView);
        mTitleTextView = itemView.findViewById(R.id.titleTextView);
        mYearTextView = itemView.findViewById(R.id.yearTextView);
        itemView.setTag(this);
    }

    public void bindItem(OmdbShortItem omdbShortItem, View.OnClickListener listener) {
        Glide.with(itemView.getContext()).load(omdbShortItem.getPoster()).into(mImageView);
        mTitleTextView.setText("Title : " + omdbShortItem.getTitle().toString());
        mYearTextView.setText("Year : " +omdbShortItem.getYear());
        itemView.setOnClickListener(listener);
    }

    public View getmView() {
        return mView;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public TextView getmTitleTextView() {
        return mTitleTextView;
    }

    public TextView getmYearTextView() {
        return mYearTextView;
    }
}
