package edu.aku.hassannaqvi.nns2018_teamleadersapp.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.BLRandomContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.ListingContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.other.householdListAdapter;

public class HouseholdListActivity extends MenuActivity {

    @BindView(R.id.lstHH)
    RecyclerView lstHH;
    
    @BindView(R.id.btnSendHH)
    FloatingActionButton btnSendHH;

    householdListAdapter householdListAdapter;

    DatabaseHelper db;

    public static ArrayList<Integer> hhAssigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_list);
        ButterKnife.bind(this);

        this.setTitle("Household List");

        db = new DatabaseHelper(this);

        hhAssigned = new ArrayList<>();

        new ApplicationsTask(this).execute();
        
    }

    public class ApplicationsTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        private Context context;

        public ApplicationsTask(Context mContext) {
            context = mContext;
            dialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);

        }

        protected void onPreExecute() {
            this.dialog.setMessage("Generating List.");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            lstHH.setAdapter(householdListAdapter);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    if (!success) {
                        Toast.makeText(context, "Error in getting Data!!", Toast.LENGTH_LONG).show();
                    }

                    householdListAdapter.notifyDataSetChanged();
                }
            }, 800);

        }

        protected Boolean doInBackground(final String... args) {
            try {

                householdListAdapter = new householdListAdapter(context, HouseholdDivInfoActivity.lstList);
                householdListAdapter.notifyDataSetChanged();

                return true;
            } catch (Exception e) {
                Log.e("tag", "error", e);
                return false;
            }
        }
    }

}
