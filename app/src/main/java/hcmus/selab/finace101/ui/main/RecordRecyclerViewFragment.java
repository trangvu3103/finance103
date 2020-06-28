package hcmus.selab.finace101.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import hcmus.selab.finace101.R;

public class RecordRecyclerViewFragment extends Fragment {

    addRecordListener listener;
    RecyclerView mrecyclerView;
    recordRecyclerView mAdapter;
    private LinkedList<String> mRecord_cat = new LinkedList<String>();
    private LinkedList<String> mRecord_mamount = new LinkedList<String>();
    private LinkedList<String> mRecord_title = new LinkedList<String>();

    // Initialize the String title, amount, cat to the default.
    private static String mRec_amount = "mAmount";
    private static String mRec_title = "titl";
    private static String mRec_cat = "cat";


    public addRecordListener getListener() {
        return listener;
    }

    public void setFragListener(addRecordListener listener){
        this.listener = listener;
    }

    public interface addRecordListener {
        public void addRecord(String moneyAmount, String title, String cat);
    }

    public void RecordRecyclerViewFragment(){
    }


    public static RecordRecyclerViewFragment newInstance(String amount, String title, String cat) {
        RecordRecyclerViewFragment fragment = new RecordRecyclerViewFragment();
        Bundle arguments = new Bundle();
        arguments.putString(mRec_amount, amount);
        arguments.putString(mRec_title, title);
        arguments.putString(mRec_cat, cat);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_wallet, container,false);

        mrecyclerView = view.findViewById(R.id.recycler_record);
        mAdapter = new recordRecyclerView(view.getContext(), mRecord_mamount, mRecord_title, mRecord_cat);
        mrecyclerView.setAdapter(mAdapter);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


//        setData("","","");
        return view;
    }

    public void setData(String amount, String title, String cat){
        mRecord_mamount.addLast(amount);
        mRecord_title.addLast(title);
        mRecord_cat.addLast(cat);

        Log.d("TAG", "setData: "+ mrecyclerView.toString());
        Log.d("TAG", "setData: "+ mAdapter.toString());
    }


}
