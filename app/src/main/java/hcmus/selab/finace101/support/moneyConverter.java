package hcmus.selab.finace101.support;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

public class moneyConverter extends AsyncTask<Void, Void, String>{
    private double moneyCur;
    private String fromCur;
    private String toCur;

    private double convertedCur;

//    HttpURLConnection urlConnection;

    public moneyConverter(String fromCur, String toCur, double curAmount){
        this.fromCur = fromCur;
        this.toCur = toCur;
        this.moneyCur = curAmount;
    }

    @Override
    protected String doInBackground(Void... voids) {
        get_curconvertAPI test = new get_curconvertAPI();
        String teststr= test.getcurExchangedAmount(this.fromCur,this.toCur, this.moneyCur);
        return teststr;
    }

//    @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        try{
//            JSONObject jsO = new JSONObject(s);
////            Double cur_amount;
////            cur_amount = Double.parseDouble(textView_cur.getText().toString()) * jsO.getJSONObject("rates").getDouble(curState);
////            textView_cur2.setText(cur_amount.toString());
//            Log.d("TAG", "onPostExecute: "+ jsO.getJSONObject("response").getDouble("value"));
////            this.convertedCur = jsO.getJSONObject("rates").getDouble(curState);
//
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//        return;
//    }

//    public double moneyConverter(double moneyCur, String fromCur, String toCur){
//        moneyConverter test = (moneyConverter) new moneyConverter(fromCur,toCur,moneyCur).execute();
//
//        return 0;
//    }

}

