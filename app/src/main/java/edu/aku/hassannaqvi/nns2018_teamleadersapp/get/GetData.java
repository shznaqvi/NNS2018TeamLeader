package edu.aku.hassannaqvi.nns2018_teamleadersapp.get;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.BLRandomContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.ListingContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.UsersContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.VersionAppContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.MainApp;

/**
 * Created by ali.azaz on 7/14/2017.
 */

public class GetData extends AsyncTask<String, String, String> {

    HttpURLConnection urlConnection;
    private String TAG = "";
    private Context mContext;


    private String syncClass;


    public GetData(Context context, String syncClass) {
        mContext = context;
        this.syncClass = syncClass;

        TAG = "Get" + syncClass;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... args) {

        StringBuilder result = new StringBuilder();

        URL url = null;
        try {
            switch (syncClass) {
                case "Listing":
                    url = new URL(MainApp._HOST_URL + ListingContract.ListingEntry._URI);
                    break;
                case "User":
                    url = new URL(MainApp._HOST_URL + UsersContract.UsersTable._URI);
                    break;
                case "BLRandom":
                    url = new URL(MainApp._HOST_URL + BLRandomContract.singleRandomHH._URI);
                    break;
                case "VersionApp":
                    url = new URL(MainApp._UPDATE_URL + VersionAppContract.VersionAppTable._URI);
                    break;
            }

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            switch (syncClass) {
                case "Listing":

                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("charset", "utf-8");
                    urlConnection.setUseCaches(false);

                    // Starts the query
                    urlConnection.connect();
                    JSONArray jsonSync = new JSONArray();
                    DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                    JSONObject json = new JSONObject();
                    try {
                        json.put("cluster", MainApp.listingCluster);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    Log.d(TAG, "downloadUrl: " + json.toString());
                    wr.writeBytes(json.toString());
                    wr.flush();
                    wr.close();

                    break;
            }

            Log.d(TAG, "doInBackground: " + urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i(TAG, syncClass + " In: " + line);
                    result.append(line);
                }
            }
        } catch (java.net.SocketTimeoutException e) {
            return null;
        } catch (java.io.IOException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
//        mContext.stopService(new Intent(mContext, intentService.class));
    }

}
