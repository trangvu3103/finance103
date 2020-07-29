package hcmus.selab.finace101.support.fxRate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//this is an background class to dynamically get FX rate from AlphaVantage
public class ForexRate extends AsyncTask<String, ArrayList<DailyFXRate>, ArrayList<DailyFXRate>> {
    private static final String DOMAIN_LINK = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=EUR";
    private static final String KEY = "N79VBWA4GHHHD6Y0";
    private ArrayList<DailyFXRate> dailyFXRates = new ArrayList<DailyFXRate>();

    @Override
    protected ArrayList<DailyFXRate> doInBackground(String... strings) {
        dailyFXRates = this.analyzeData(this.fetchFXRate("USD"));
        return dailyFXRates;
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);

    }

    public ArrayList<DailyFXRate> getResult() {
        return dailyFXRates;
    }

    // get data from domain server
    // let the users input choose the pair code
    private String fetchFXRate(String code) {
        HttpURLConnection urlConnect = null;
        BufferedReader reader = null;
        String fxData = null;
        Uri buildURI = Uri.parse(DOMAIN_LINK).buildUpon().
                appendQueryParameter("to_symbol", code).
                appendQueryParameter("apikey", KEY).
                build();

        Log.d("TAG", "fetchFXRate: " + buildURI);

        try {
            URL requestURL = new URL(buildURI.toString());

            urlConnect = (HttpURLConnection) requestURL.openConnection();
            urlConnect.setRequestMethod("GET");
            urlConnect.connect();
//            Log.d("TAG", "set up connection");

            InputStream inputStream = urlConnect.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

//            Log.d("TAG", "append data to string builder" + stringBuilder);

            if (stringBuilder.length() == 0) {
                return null;
            }

            fxData = stringBuilder.toString();
//            Log.d("TAG", "make data to string" + fxData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnect != null) {
                urlConnect.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fxData;
    }

    // convert data and analyze
    private ArrayList<DailyFXRate> analyzeData (String data) {
        ArrayList<DailyFXRate> dailyFXRates = new ArrayList<DailyFXRate>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject itemsArray = jsonObject.getJSONObject("Time Series FX (Daily)");
            JSONArray date_arr = itemsArray.names();


//            Iterator<String> key_date = itemsArray.keys();
//            Log.d("TAG", "analyzeData: " + key_date.getClass());
            int i = 0;

            // get data to class
            while (i < 30) {
                String a_date = date_arr.getString(i);

                double a_open = itemsArray.getJSONObject(a_date).getDouble("1. open");
                double a_high = itemsArray.getJSONObject(a_date).getDouble("2. high");
                double a_low = itemsArray.getJSONObject(a_date).getDouble("3. low");
                double a_close = itemsArray.getJSONObject(a_date).getDouble("4. close");

                DailyFXRate a_daily = new DailyFXRate(a_date, a_open, a_high, a_low, a_close);
//                Log.d("TAG", "analyzeData: " + a_daily.getDailyDate());


                dailyFXRates.add(a_daily);

                i++;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return dailyFXRates;
    }
}
