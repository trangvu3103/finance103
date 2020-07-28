package hcmus.selab.finace101.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmus.selab.finace101.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment2 extends Fragment {

    Spinner currency_spinner;
    Button convertCurrency;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
//        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.fragment_news, container, false);

        currency_spinner = (Spinner) view.findViewById(R.id.currency_spinner);
//        Log.d("TAG", "onCreateView: "+curConvertView.getPaddingTop());
        ArrayAdapter<String> mCurSpinnerAddapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1,
                this.getResources().getStringArray(R.array.toCur));

        Log.d("TAG", "onCreateView: "+this.getResources().getStringArray(R.array.toCur).length);
        Log.d("TAG", "onCreateView: "+mCurSpinnerAddapter.getCount());

        mCurSpinnerAddapter.setDropDownViewResource(R.layout.spinner_row);
        currency_spinner.setAdapter(mCurSpinnerAddapter);

        convertCurrency = view.findViewById(R.id.convert_btn);

        convertCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }



}