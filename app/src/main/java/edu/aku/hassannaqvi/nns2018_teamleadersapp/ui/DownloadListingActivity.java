package edu.aku.hassannaqvi.nns2018_teamleadersapp.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.DatabaseHelper;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.MainApp;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.databinding.ActivityDownloadListingBinding;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.get.GetAllData;

public class DownloadListingActivity extends MenuActivity {

    ActivityDownloadListingBinding binding;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem listingSync = menu.findItem(R.id.menu_listing_sync);

        listingSync.setVisible(false);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_listing);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_download_listing);
        binding.setCallback(this);

        this.setTitle("HouseHold Information");

        db = new DatabaseHelper(this);

    }

    public void BtnDownloadLL() {

        if (formValidation()) {

            MainApp.listingCluster = binding.nh102.getText().toString();

            Toast.makeText(this, "Sync Enum Blocks", Toast.LENGTH_LONG).show();
            new GetAllData(this, "Listing").execute();

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
