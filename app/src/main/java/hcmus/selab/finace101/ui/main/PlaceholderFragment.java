package hcmus.selab.finace101.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import hcmus.selab.finace101.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment{
    addRecordListener callback;

    static RecyclerView mRecyclerView;

    TextView curVal;

    public void setFragListener(addRecordListener callback) {
        this.callback = callback;
    }

    public interface addRecordListener {
        public void getRecyclerView(RecyclerView recyclerView, TextView curVal);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recycler_record);
        curVal = view.findViewById(R.id.current_money);

        callback.getRecyclerView(mRecyclerView, curVal);

//        curVal.setText(String.valueOf(mFinRecordViewModel.getCurVal())+" VND");

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  addRecordListener){
            callback = (addRecordListener) context;
        }else{
            throw new RuntimeException(context.toString()+"must implement addRecordListener");
        }
    }
}