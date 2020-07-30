package hcmus.selab.finace101.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import hcmus.selab.finace101.R;
import hcmus.selab.finace101.support.moneyConverter;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment2 extends Fragment {

    Spinner currency_spinner;
    Button convertCurrency;

    String toCur = "USD";

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
//        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.fragment_news, container, false);

        currency_spinner = (Spinner) view.findViewById(R.id.currency_spinner);
//        Log.d("TAG", "onCreateView: "+curConvertView.getPaddingTop());
        ArrayAdapter<String> mCurSpinnerAddapter = new ArrayAdapter<String>(this.getContext(),
                R.layout.currency_spinner_item,
                this.getResources().getStringArray(R.array.toCur));

        mCurSpinnerAddapter.setDropDownViewResource(R.layout.spinner_row);
        currency_spinner.setAdapter(mCurSpinnerAddapter);

        TextView convert_input = view.findViewById(R.id.covert_input);
        TextView convert_output = view.findViewById(R.id.convert_result);

        convertCurrency = view.findViewById(R.id.convert_btn);

        convertCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"EYYYYY",Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onClick: "+ view);
                Double amountCur = Double.parseDouble(String.valueOf(convert_input.getText()));
                currency_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        try {
                            toCur = parentView.getItemAtPosition(position).toString();

                        }catch (Exception e){
                            Toast.makeText(selectedItemView.getContext(), "Choose currency exchanged FAILED", Toast.LENGTH_LONG).show();
                            Log.wtf("ChooseCurState_Err:", e.getMessage());
                            return;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                        toCur = "USD";
                    }

                });

                Double convertedAMount = Convert_currency("VND", toCur, amountCur);
                if (convertedAMount == -1.0) {
                    Toast.makeText(view.getContext(),"converted FAIL",Toast.LENGTH_SHORT).show();
//                    return;
                }else{
                    convert_output.setText(String.valueOf(convertedAMount)+" "+toCur);
                }

            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    public Double Convert_currency (String fromCur, String toCur, Double amount){
//        String getStrCur = cur1.getText().toString();
//        if (!getStrCur.equalsIgnoreCase("")){
//        moneyConverter test = (moneyConverter) new moneyConverter(fromCur,toCur,amount).execute();
        String test = "";
        Double cur_amount = -1.0;

        try {
            test = new moneyConverter(fromCur,toCur,amount).execute().get();

            JSONObject jsO = new JSONObject(test);
            cur_amount = jsO.getJSONObject("response").getDouble("value");

////            textView_cur2.setText(cur_amount.toString());
            Log.d("TAG", "onPostExecute: "+ jsO.getJSONObject("response").getDouble("value"));
        } catch (ExecutionException e) {
            Log.d("TAG", "Convert_currency: "+e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Log.d("TAG", "Convert_currency: "+e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("TAG", "Convert_currency: "+e.getMessage());
            e.printStackTrace();
        }

        Log.d("TAG", "Convert_currency: "+ test);
//        }else{
//            Toast.makeText(this, "Converted Failed", Toast.LENGTH_SHORT).show();
//        }
        return cur_amount;
    }

}