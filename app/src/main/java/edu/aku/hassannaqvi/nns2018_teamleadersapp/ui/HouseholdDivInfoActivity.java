package edu.aku.hassannaqvi.nns2018_teamleadersapp.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.BLRandomContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.databinding.ActivityHouseholdDivInfoBinding;

public class HouseholdDivInfoActivity extends MenuActivity {

    ActivityHouseholdDivInfoBinding binding;

    public static ArrayList<BLRandomContract> lstList;

    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_div_info);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_household_div_info);
        binding.setCallback(this);

        this.setTitle("HouseHold Information");

        db = new DatabaseHelper(this);

    }

    public class ApplicationsTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        private Context context;

        public ApplicationsTask(Context mContext) {
            context = mContext;
            dialog = new ProgressDialog(context, R.style.AppTheme_Dark_Dialog);

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

                for (BLRandomContract lst : db.getAllBLRandom(binding.nh102.getText().toString())) {
                    lstList.add(lst);
                }

                return lstList.size() > 0;

            } catch (Exception e) {
                Log.e("tag", "error", e);
                return false;
            }
        }
    }

    public void BtnCheckHH() {

        if (formValidation()) {

            try {

//              Initialization of list
                lstList = new ArrayList<>();

                Boolean flag = new ApplicationsTask(this).execute().get();

                if (flag) {
                    Toast.makeText(this, "Households Get.", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(this, HouseholdListActivity.class));

                } else {
                    Toast.makeText(this, "Households not found.", Toast.LENGTH_SHORT).show();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

    }

    public Boolean formValidation() {

//        nh102
        if (TextUtils.isEmpty(binding.nh102.getText().toString().trim())) {
            binding.nh102.setError("This data is Required! ");    // Set Error on last radio button
            return false;
        } else {
            binding.nh102.setError(null);
            binding.nh102.clearFocus();
        }

        return true;
    }

}
