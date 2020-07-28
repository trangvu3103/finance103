package hcmus.selab.finace101.support;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class get_curconvertAPI {
    HttpURLConnection urlConnection;
    get_curconvertAPI(){
        urlConnection = null;
    }

    public String getcurExchangedAmount(String fromCur, String toCur, int money){
        BufferedReader reader = null;

        String bookJSONString = null;
        Log.d("TAG", "doInBackground: Currency_converter");

        try{
            Uri builtURI = Uri.parse("https://api.currencyscoop.com/v1/convert").buildUpon()

                    .appendQueryParameter("api_key", "aa788b84201d83279d0c520990c1162d92e0df38e89a6513a82c13b04fb16628 ")

                    .appendQueryParameter("base", fromCur)

                    .appendQueryParameter("to", toCur)

                    .appendQueryParameter("amount", String.valueOf(money))

                    .build();

            // Convert the URI to a URL,

            URL requestURL = new URL(builtURI.toString());

            // Open the network connection.

            urlConnection = (HttpURLConnection) requestURL.openConnection();

            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            // Get the InputStream.

            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.

            reader = new BufferedReader(new InputStreamReader(inputStream));



            // Use a StringBuilder to hold the incoming response.

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {

                // Add the current line to the string.

                builder.append(line);

                // Since this is JSON, adding a newline isn't necessary (it won't

                // affect parsing) but it does make debugging a *lot* easier

                // if you print out the completed buffer for debugging.

                builder.append("\n");

            }



            if (builder.length() == 0) {

                // Stream was empty.  Exit without parsing.
                return "";
            }
            Log.d("TAG", "getcurExchangedAmount: "+ builder);
            bookJSONString = builder.toString();
            Log.d("TAG", "doInBackground: " + String.valueOf(bookJSONString));


        }catch (IOException e) {

            e.printStackTrace();

        } finally {

            // Close the connection and the buffered reader.

            if (urlConnection != null) {

                urlConnection.disconnect();

            }

            if (reader != null) {

                try {

                    reader.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

        return bookJSONString;
    }
}
