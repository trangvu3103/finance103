package hcmus.selab.finace101.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmus.selab.finace101.R;
import hcmus.selab.finace101.RoomDB.Entities.finRecord;

public class recordRecyclerView extends RecyclerView.Adapter<recordRecyclerView.recordRecyclerView_Holder> {

//    final LinkedList<String> list_mRecord_amount, list_mRecord_title, list_mRecord_cat;
    private int[] list_mRecord_icons = {
      R.drawable.icon_expand,
      R.drawable.icon_income,
    };
    private List<finRecord> records;
    private LayoutInflater mInflater;
//
    public class recordRecyclerView_Holder extends RecyclerView.ViewHolder {
        public final TextView record_amount, record_title;
        ImageView record_icon;
//        final recordRecyclerView mAdapter;

        public recordRecyclerView_Holder(View mItemView, recordRecyclerView recordRecyclerViewAdapter) {
            super(mItemView);
            this.record_amount = mItemView.findViewById(R.id.record_amount);
            this.record_title = mItemView.findViewById(R.id.record_title);
            this.record_icon = mItemView.findViewById(R.id.record_icon);
//            this.mAdapter = recordRecyclerViewAdapter;
//            mItemView.setOnClickListener(this);
        }

    }
//
//    public recordRecyclerView(Context context, LinkedList<String> mRecord_amount, LinkedList<String> mRecord_title, LinkedList<String> mRecord_cat) {
//        this.list_mRecord_amount = mRecord_amount;
//        this.list_mRecord_title = mRecord_title;
//        this.list_mRecord_cat = mRecord_cat;
//        this.mInflater = LayoutInflater.from(context);
//    }
//
    public recordRecyclerView(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }
//
//    @NonNull
//    @Override
//    public recordRecyclerView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View mItemView = mInflater.inflate(R.layout.record, parent,false);
//        return new recordRecyclerView_Holder(mItemView,this);
//    }

    @NonNull
    @Override
    public recordRecyclerView_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.record, parent,false);
        return new recordRecyclerView_Holder(mItemView,this);
    }

//
//    @Override
//    public void onBindViewHolder(@NonNull recordRecyclerView_Holder holder, int position) {
//        String mCurrent = list_mRecord_amount.get(position);
//        String mTitle = list_mRecord_title.get(position);
//        String mCat = list_mRecord_cat.get(position);
//
//        holder.record_amount.setText(mCurrent);
//        holder.record_title.setText(mTitle);
//
//        if (mCat.equalsIgnoreCase("Expand")){
//            holder.record_icon.setImageResource(R.drawable.icon_expand);
//        }else{
//            holder.record_icon.setImageResource(R.drawable.icon_income);
//
//        }
//    }
//

    @Override
    public void onBindViewHolder(@NonNull recordRecyclerView_Holder holder, int position) {
//        String mCurrent = list_mRecord_amount.get(position);
//        String mTitle = list_mRecord_title.get(position);
//        String mCat = list_mRecord_cat.get(position);

//        holder.record_amount.setText(mCurrent);
//        holder.record_title.setText(mTitle);

//        if (mCat.equalsIgnoreCase("Expand")){
//            holder.record_icon.setImageResource(R.drawable.icon_expand);
//        }else{
//            holder.record_icon.setImageResource(R.drawable.icon_income);
//
//        }

        if (records != null) {
            finRecord record = records.get(position);
            holder.record_amount.setText(String.valueOf(record.getFin_record_value())+" VND");
            holder.record_title.setText(record.getFin_record_name());
            if (record.getFin_record_status().equalsIgnoreCase("Expand") || record.getFin_record_status().equalsIgnoreCase("Expand_U")){
                holder.record_icon.setImageResource(R.drawable.icon_expand);
            }else{
                holder.record_icon.setImageResource(R.drawable.icon_income);

            }

        } else {
            // Covers the case of data not being ready yet.
//            holder.wordItemView.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        if (records != null) {
            return records.size();
        }else return 0;
    }


    public void setRecords(List<finRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

}
