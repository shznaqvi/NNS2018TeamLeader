package edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;

/**
 * Created by javed.khan on 1/22/2018.
 */

public class DeceasedContract {


    private final String projectName = String.valueOf(R.string.app_name);
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer

    private String sH8 = ""; // sB

    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion;

    public DeceasedContract() {

    }


    public DeceasedContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(DeceasedTable.COLUMN__ID);
        this._UID = jsonObject.getString(DeceasedTable.COLUMN__UID);
        this._UUID = jsonObject.getString(DeceasedTable.COLUMN__UUID);
        this.formDate = jsonObject.getString(DeceasedTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(DeceasedTable.COLUMN_USER);
        this.sH8 = jsonObject.getString(DeceasedTable.COLUMN_SH8);
        this.deviceID = jsonObject.getString(DeceasedTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(DeceasedTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(DeceasedTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(DeceasedTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(DeceasedTable.COLUMN_APPVERSION);


        return this;

    }

    public DeceasedContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN__UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN__UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_USER));
        this.sH8 = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_SH8));
        this.deviceID = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(DeceasedTable.COLUMN_APPVERSION));


        // TODO:

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(DeceasedTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(DeceasedTable.COLUMN__UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(DeceasedTable.COLUMN__UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(DeceasedTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(DeceasedTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);

        if (!this.sH8.equals("")) {
            json.put(DeceasedTable.COLUMN_SH8, this.sH8.equals("") ? JSONObject.NULL : new JSONObject(this.sH8));

        }


        json.put(DeceasedTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(DeceasedTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        /*json.put(DeceasedTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(DeceasedTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);*/
        json.put(DeceasedTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);


        return json;
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getUID() {
        return _UID;
    }

    public void setUID(String _UID) {
        this._UID = _UID;
    }


    public String getUUID() {
        return _UUID;
    }

    public void setUUID(String _UUID) {
        this._UUID = _UUID;
    }


    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public String getsH8() {
        return sH8;
    }

    public void setsH8(String sH8) {
        this.sH8 = sH8;
    }

    public static abstract class DeceasedTable implements BaseColumns {

        public static final String TABLE_NAME = "deceased";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN__UID = "_uid";
        public static final String COLUMN__UUID = "_uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_SH8 = "sh8";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";


        public static String _URL = "deceased.php";
    }

}