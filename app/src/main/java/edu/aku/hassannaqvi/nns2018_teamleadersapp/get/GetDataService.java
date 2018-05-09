package edu.aku.hassannaqvi.nns2018_teamleadersapp.get;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;

public class GetDataService extends IntentService {

    private static final String TAG = GetDataService.class.getName();
    Handler handler;

    public GetDataService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        /*We use this handler cause Intent Service run on its own worker thread that why it can't show toast*/
        handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // run this code in the main thread
                Toast.makeText(getApplicationContext(), "Start Service", Toast.LENGTH_SHORT).show();
            }
        });

        /*Pass the fetched data in function*/
        try {
            sendingData(getApplicationContext(), new GetData(getApplicationContext(), "User").execute().get(),"User");
            sendingData(getApplicationContext(), new GetData(getApplicationContext(), "BLRandom").execute().get(),"BLRandom");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void sendingData(final Context mContext, String result, String syncClass) {

        if (result != null) {
            String json = result;
            if (json.length() > 0) {

                DatabaseHelper db = new DatabaseHelper(mContext);
                try {
                    JSONArray jsonArray = new JSONArray(json);

                    switch (syncClass) {
                        case "Listing":
                            db.syncListing(jsonArray);
                            break;
                        case "User":
                            db.syncUser(jsonArray);
                            break;
                        case "BLRandom":
                            db.syncBLRandom(jsonArray);
                            break;
                        case "VersionApp":
                            db.syncVersionApp(jsonArray);
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // run this code in the main thread
                        Toast.makeText(mContext, "Result 0", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // run this code in the main thread
                    Toast.makeText(mContext, "Connection Error", Toast.LENGTH_SHORT).show();
                }
            });

//            mContext.stopService(new Intent(mContext, GetDataService.class));
        }
    }

}
