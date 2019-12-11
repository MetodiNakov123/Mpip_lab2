package finki.ukim.com.lab2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import finki.ukim.com.lab2.R;
import finki.ukim.com.lab2.holder.CustomViewHolder;
import finki.ukim.com.lab2.model.OmdbShortItem;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context mContext;
    private List<OmdbShortItem> mList;
    public View.OnClickListener listener;

    public CustomAdapter(Context mContext, List<OmdbShortItem> mList, View.OnClickListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_view_item, viewGroup, false);

        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customCardViewHolder, int i) {
        customCardViewHolder.bindItem(mList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void updateData(List<OmdbShortItem> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public int getClickedItemId(CustomViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return mList.get(adapterPosition).getId();
    }

    public String getClickedItemTitle(CustomViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return mList.get(adapterPosition).getTitle();
    }

    public String getClickedItemYear(CustomViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return mList.get(adapterPosition).getYear();
    }

    public String getClickedItemImage(CustomViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return mList.get(adapterPosition).getPoster();
    }

    public String getClickedItemimdbID(CustomViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return mList.get(adapterPosition).getImdbID();
    }
}
