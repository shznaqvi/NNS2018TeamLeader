package edu.aku.hassannaqvi.nns2018_teamleadersapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.BLRandomContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.databinding.ActivityHouseholdDivInfoBinding;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.other.blClustersListAdapter;

public class HouseholdDivInfoActivity extends Activity {

    ActivityHouseholdDivInfoBinding binding;

    public static ArrayList<BLRandomContract> lstList;

    public ArrayList<BLRandomContract> clusterList;

    blClustersListAdapter BLClustersListAdapter;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_div_info);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_household_div_info);
        binding.setCallback(this);

        this.setTitle("Cluster Information");
        binding.collapsingToolbar.setTitle("Available Randomized Clusters");

        db = new DatabaseHelper(this);


        new ApplicationsTask(this).execute();

        binding.recyclerBlClusters.addOnItemTouchListener(
                new blClustersListAdapter.RecyclerItemClickListener(getApplicationContext(), new blClustersListAdapter.RecyclerItemClickListener.OnItemClickListener() {
                    Boolean delFlag = true;

                    @Override
                    public void onItemClick(View view, final int position) {
                        // TODO Handle item click
                        if (position != -1) {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    HouseholdDivInfoActivity.this);
                            alertDialogBuilder
                                    .setMessage("Are you sure to open this cluster?")
                                    .setCancelable(false)
                                    .setPositiveButton("Ok",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    Boolean flag = null;
                                                    try {

                                                        lstList = new ArrayList<>();

                                                        flag = new ClickingRecyclerTask(HouseholdDivInfoActivity.this, lstList.get(position).getSubVillageCode()).execute().get();

                                                        if (flag) {
                                                            Toast.makeText(getApplicationContext(), "Households Get.", Toast.LENGTH_SHORT).show();

                                                            finish();

                                                            startActivity(new Intent(HouseholdDivInfoActivity.this, HouseholdListActivity.class));

                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Households not found.", Toast.LENGTH_SHORT).show();
                                                        }

                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    } catch (ExecutionException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                            alertDialogBuilder.setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                    }
                })
        );

    }


    public class ApplicationsTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        private Context context;

        public ApplicationsTask(Context mContext) {
            context = mContext;
            dialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);

            clusterList = new ArrayList<>();
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Analyzing Data");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            binding.recyclerBlClusters.setAdapter(BLClustersListAdapter);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    if (!success) {
                        Toast.makeText(context, "Error in getting Data!!", Toast.LENGTH_LONG).show();
                    }

                    BLClustersListAdapter.notifyDataSetChanged();
                }
            }, 1000);

        }

        protected Boolean doInBackground(final String... args) {
            try {

                for (BLRandomContract lst : db.getAllBLRandom()) {
                    clusterList.add(lst);
                }

                BLClustersListAdapter = new blClustersListAdapter(context, clusterList);
                BLClustersListAdapter.notifyDataSetChanged();

                return true;
            } catch (Exception e) {
                Log.e("tag", "error", e);
                return false;
            }
        }
    }

    public class ClickingRecyclerTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        private Context context;

        String clusterNo;

        public ClickingRecyclerTask(Context mContext, String cluster) {
            context = mContext;
            dialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);
            clusterNo = cluster;
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Searching List.");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    if (!success) {
                        Toast.makeText(context, "Some issue in getting Data!!", Toast.LENGTH_LONG).show();
                    }

                }
            }, 1500);

        }

        protected Boolean doInBackground(final String... args) {
            try {

                for (BLRandomContract lst : db.getAllBLRandomHH(clusterNo)) {
                    lstList.add(lst);
                }

                return lstList.size() > 0;

            } catch (Exception e) {
                Log.e("tag", "error", e);
                return false;
            }
        }
    }

}
