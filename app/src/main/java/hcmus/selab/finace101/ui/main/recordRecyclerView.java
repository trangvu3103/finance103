package hcmus.selab.finace101.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import hcmus.selab.finace101.R;

import static androidx.core.content.res.ResourcesCompat.getDrawable;

public class recordRecyclerView extends RecyclerView.Adapter<recordRecyclerView.recordRecyclerView_Holder> {

    final LinkedList<String> list_mRecord_amount, list_mRecord_title, list_mRecord_cat;
    private int[] list_mRecord_icons = {
      R.drawable.icon_expand,
      R.drawable.icon_income,
    };
    private LayoutInflater mInflater;

    public class recordRecyclerView_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView record_amount, record_title;
        ImageView record_icon;
        final recordRecyclerView mAdapter;

        public recordRecyclerView_Holder(View mItemView, recordRecyclerView recordRecyclerViewAdapter) {
            super(mItemView);
            this.record_amount = mItemView.findViewById(R.id.record_amount);
            this.record_title = mItemView.findViewById(R.id.record_title);
            this.record_icon = mItemView.findViewById(R.id.record_icon);
            this.mAdapter = recordRecyclerViewAdapter;
            mItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public recordRecyclerView(Context context, LinkedList<String> mRecord_amount, LinkedList<String> mRecord_title, LinkedList<String> mRecord_cat) {
        this.list_mRecord_amount = mRecord_amount;
        this.list_mRecord_title = mRecord_title;
        this.list_mRecord_cat = mRecord_cat;
        this.mInflater = LayoutInflater.from(context);
    }

    public recordRecyclerView(Context context) {
        this.list_mRecord_amount = null;
        this.list_mRecord_title = null;
        this.list_mRecord_cat = null;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public recordRecyclerView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.record, parent,false);
        return new recordRecyclerView_Holder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull recordRecyclerView_Holder holder, int position) {
        String mCurrent = list_mRecord_amount.get(position);
        String mTitle = list_mRecord_title.get(position);
        String mCat = list_mRecord_cat.get(position);

        holder.record_amount.setText(mCurrent);
        holder.record_title.setText(mTitle);

        if (mCat.equalsIgnoreCase("Expand")){
            holder.record_icon.setImageResource(R.drawable.icon_expand);
        }else{
            holder.record_icon.setImageResource(R.drawable.icon_income);

        }
    }

    @Override
    public int getItemCount() {
        return list_mRecord_amount.size();
    }

}
