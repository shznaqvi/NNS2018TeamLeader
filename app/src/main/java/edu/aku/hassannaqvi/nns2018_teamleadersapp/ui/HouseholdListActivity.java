package edu.aku.hassannaqvi.nns2018_teamleadersapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.WifiDirect.WiFiDirectActivity;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.BLRandomContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.other.householdListAdapter;

public class HouseholdListActivity extends AppCompatActivity {

    @BindView(R.id.lstHH)
    RecyclerView lstHH;

    @BindView(R.id.btnSendHH)
    FloatingActionButton btnSendHH;

    householdListAdapter householdListAdapter;

    DatabaseHelper db;

    public static ArrayList<Integer> hhAssigned;
    public static ArrayList<BLRandomContract> selectedRan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_list);
        ButterKnife.bind(this);

        this.setTitle("Household List");

        db = new DatabaseHelper(this);

        hhAssigned = new ArrayList<>();

        new ApplicationsTask(this).execute();

        /*lstHH.addOnItemTouchListener(
                new householdListAdapter.RecyclerItemClickListener(getApplicationContext(), new householdListAdapter.RecyclerItemClickListener.OnItemClickListener() {
                    Boolean delFlag = true;

                    @Override
                    public void onItemClick(View view, final int position) {
                        // TODO Handle item click

                        if (position != -1) {
                            Toast.makeText(HouseholdListActivity.this, HouseholdDivInfoActivity.lstList.get(position).getAssignHH().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }));*/

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

    @OnClick(R.id.btnSendHH)
    public void BtnSendHH() {

        selectedRan = new ArrayList<>();

        for (byte i = 0; i < HouseholdDivInfoActivity.lstList.size(); i++) {
            if (HouseholdDivInfoActivity.lstList.get(i).getAssignHH().equals("1")) {
                selectedRan.add(HouseholdDivInfoActivity.lstList.get(i));
            }
        }

        if (selectedRan.size() > 0) {
            Snackbar mySnackbar =
                    Snackbar.make(findViewById(android.R.id.content), "Total Selected Household No." + selectedRan.size(), Snackbar.LENGTH_SHORT)
                            .setAction("Proceed", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent wifidirect = new Intent(getApplicationContext(), WiFiDirectActivity.class);
                                    startActivity(wifidirect);
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.pink_100))
                            .setDuration(7000);

            mySnackbar.show();
        } else {
            Toast.makeText(this, "Please select HH for sending!!", Toast.LENGTH_SHORT).show();
        }

    }

}
