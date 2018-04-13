package edu.aku.hassannaqvi.nns2018_teamleadersapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.WifiDirect.WiFiDirectActivity;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.ChildContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.DeceasedContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.EligibleMembersContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.FormsContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.MWRAContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.NutritionContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.OutcomeContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.RecipientsContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.AndroidDatabaseManager;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.MainApp;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.get.GetAllData;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.sync.SyncAllData;

public class MenuActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    String DirectoryName;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(this);
        dbBackup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_example, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sync:
                onSyncDataClick();
                return true;
            case R.id.menu_upload:
                syncServer();
                return true;

            case R.id.menu_openDB:
                Intent dbmanager = new Intent(getApplicationContext(), AndroidDatabaseManager.class);
                startActivity(dbmanager);
                return true;

            case R.id.menu_wifiDirect:
                Intent wifidirect = new Intent(getApplicationContext(), WiFiDirectActivity.class);
                startActivity(wifidirect);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onSyncDataClick() {
        //TODO implement

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            new syncData(this).execute();

        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    public void dbBackup() {

        sharedPref = getSharedPreferences("src", MODE_PRIVATE);
        editor = sharedPref.edit();

        if (sharedPref.getBoolean("flag", false)) {

            String dt = sharedPref.getString("dt", new SimpleDateFormat("dd-MM-yy").format(new Date()).toString());

            if (dt != new SimpleDateFormat("dd-MM-yy").format(new Date()).toString()) {
                editor.putString("dt", new SimpleDateFormat("dd-MM-yy").format(new Date()).toString());

                editor.commit();
            }

            File folder = new File(Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME);
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }
            if (success) {

                DirectoryName = folder.getPath() + File.separator + sharedPref.getString("dt", "");
                folder = new File(DirectoryName);
                if (!folder.exists()) {
                    success = folder.mkdirs();
                }
                if (success) {

                    try {
                        File dbFile = new File(this.getDatabasePath(DatabaseHelper.DATABASE_NAME).getPath());
                        FileInputStream fis = new FileInputStream(dbFile);

                        String outFileName = DirectoryName + File.separator +
                                DatabaseHelper.DB_NAME;

                        // Open the empty db as the output stream
                        OutputStream output = new FileOutputStream(outFileName);

                        // Transfer bytes from the inputfile to the outputfile
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            output.write(buffer, 0, length);
                        }
                        // Close the streams
                        output.flush();
                        output.close();
                        fis.close();
                    } catch (IOException e) {
                        Log.e("dbBackup:", e.getMessage());
                    }

                }

            } else {
                Toast.makeText(this, "Not create folder", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void syncServer() {

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            DatabaseHelper db = new DatabaseHelper(this);
            //syncStatus.setText(null);
            Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Forms",
                    "updateSyncedForms",
                    FormsContract.class,
                    MainApp._HOST_URL + FormsContract.FormsTable._URL,
                    db.getUnsyncedForms(), this.findViewById(R.id.syncStatus)
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Family Members", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Family Members",
                    "updateSyncedFamilyMembers",
                    FamilyMembersContract.class,
                    MainApp._HOST_URL + FamilyMembersContract.familyMembers._URL,
                    db.getUnsyncedFamilyMembers(), this.findViewById(R.id.syncStatus)
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing WRAs", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "WRAs",
                    "updateSyncedMWRAForm",
                    MWRAContract.class,
                    MainApp._HOST_URL + MWRAContract.MWRATable._URL,
                    db.getUnsyncedMWRA(), this.findViewById(R.id.syncStatus)
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Children", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Children",
                    "updateSyncedChildForm",
                    ChildContract.class,
                    MainApp._HOST_URL + ChildContract.ChildTable._URL,
                    db.getUnsyncedChildForms(), this.findViewById(R.id.syncStatus)
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Eligibles", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Eligibles",
                    "updateSyncedEligibles",
                    EligibleMembersContract.class,
                    MainApp._HOST_URL + EligibleMembersContract.eligibleMembers._URL,
                    db.getUnsyncedEligbleMembers(), this.findViewById(R.id.syncStatus)
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Outcomes", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Outcomes",
                    "updateSyncedOutcomeForm",
                    OutcomeContract.class,
                    MainApp._HOST_URL + OutcomeContract.outcomeTable._URL,
                    db.getUnsyncedOutcome(), this.findViewById(R.id.syncStatus)
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Recepients", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Recepients",
                    "updateSyncedRecepientsForm",
                    RecipientsContract.class,
                    MainApp._HOST_URL + RecipientsContract.RecipientsTable._URL,
                    db.getUnsyncedRecipients(), this.findViewById(R.id.syncStatus)
            ).execute();

            Toast.makeText(getApplicationContext(), "Syncing Nutrition", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Nutrition",
                    "updateSyncedNutrition",
                    NutritionContract.class,
                    MainApp._HOST_URL + NutritionContract.NutritionTable._URL,
                    db.getUnsyncedNutrition(), this.findViewById(R.id.syncStatus)
            ).execute();


            Toast.makeText(getApplicationContext(), "Syncing Deceased", Toast.LENGTH_SHORT).show();
            new SyncAllData(
                    this,
                    "Deceased",
                    "updateSyncedDeceasedForm",
                    DeceasedContract.class,
                    MainApp._HOST_URL + DeceasedContract.DeceasedTable._URL,
                    db.getUnsyncedDeceasedMembers(), this.findViewById(R.id.syncStatus)
            ).execute();

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastUpSyncServer", dtToday);

            editor.apply();

        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }

    public class syncData extends AsyncTask<String, String, String> {

        private Context mContext;

        public syncData(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(MenuActivity.this, "Sync Enum Blocks", Toast.LENGTH_LONG).show();
                    new GetAllData(mContext, "EnumBlock").execute();
                    Toast.makeText(MenuActivity.this, "Sync Users", Toast.LENGTH_LONG).show();
                    new GetAllData(mContext, "User").execute();
                    Toast.makeText(MenuActivity.this, "Sync Users", Toast.LENGTH_LONG).show();
                    new GetAllData(mContext, "BLRandom").execute();
                    Toast.makeText(MenuActivity.this, "Sync App Version", Toast.LENGTH_LONG).show();
                    new GetAllData(mContext, "VersionApp").execute();
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

//                    populateSpinner(mContext);

                    editor.putBoolean("flag", true);
                    editor.commit();

                    dbBackup();

                }
            }, 1200);
        }
    }
}
