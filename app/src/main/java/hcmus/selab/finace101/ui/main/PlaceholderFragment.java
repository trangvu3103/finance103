package hcmus.selab.finace101.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import hcmus.selab.finace101.MainActivity;
import hcmus.selab.finace101.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String AMOUNT = "moneyAmount";
    private static final String TITLE = "moneyAmount";
    private static final String CAT = "moneyAmount";

    addRecordListener callback;
//    LinkedList<String> mRecordAmount_list = new LinkedList<String>();
//    LinkedList<String> mRecordTitle_list = new LinkedList<String>();
//    LinkedList<String> mRecordCat_list = new LinkedList<String>();

    static RecyclerView mRecyclerView;
    private recordRecyclerView mAdapter;

    public void setFragListener(addRecordListener callback) {
        this.callback = callback;
    }

    public interface addRecordListener {
        public void getRecyclerView(RecyclerView recyclerView);
//        public void getRecyclerView(RecyclerView recyclerView, LinkedList<String> mMon, LinkedList<String> mTit, LinkedList<String> mCat);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

//        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recycler_record);
//        Log.d("TAG", "onCreateView: "+String.valueOf(mRecyclerView));
//
//        //create an adapter and supply with RecyclerView.
//        mAdapter = new recordRecyclerView(view.getContext(), mRecordAmount_list, mRecordTitle_list, mRecordCat_list);
////
////        // Connect the adapter with the RecyclerView.
//        mRecyclerView.setAdapter(mAdapter);
////
////        // Give the RecyclerView a default layout manager.
//        mRecyclerView.setLayoutManager(new LinearLayoutManager((view.getContext())));

        callback.getRecyclerView(mRecyclerView);

        Log.d("TAG", "onCreate Frag: "+mRecyclerView.getAdapter().toString());

        // Inflate the layout for this fragment
        return view;
    }

//    public void setData (String moneyAmount, String title, String cat){
//        int wordListSize = mRecordAmount_list.size();
//        // Add a new Data to the linkedList.
//        mRecordAmount_list.addLast(moneyAmount);
//        mRecordTitle_list.addLast(title);
//        mRecordCat_list.addLast(cat);
//
//        Log.d("TAG", "onCreateView: "+String.valueOf(mRecyclerView));
//        Log.d("TAG", "setData: " + String.valueOf(mRecordAmount_list));
//        Log.d("TAG", "setData: " + String.valueOf(mRecordTitle_list));
//        Log.d("TAG", "setData: " + String.valueOf(mRecordCat_list));
//        // Notify the adapter, that the data has changed.
//        mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
//
//        // Scroll to the bottom.
//        mRecyclerView.smoothScrollToPosition(wordListSize);
//    }
//
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  addRecordListener){
            callback = (addRecordListener) context;
        }else{
            throw new RuntimeException(context.toString()+"must impletement addRecordListener");
        }
    }




}