package edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class NutritionContract {

    private final String projectName = String.valueOf(R.string.app_name);
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String FMUID = "";
    private String MUID = "";
    private String formDate = "";
    private String deviceId = "";
    private String devicetagID = "";
    private String user = "";
    private String app_ver = "";

    private String sB6 = "";


    private String synced = "";
    private String syncedDate = "";

    private String updatedate = "";

    public NutritionContract() {
    }


    public String getFMUID() {
        return FMUID;
    }

    public void setFMUID(String FMUID) {
        this.FMUID = FMUID;
    }

    public String getMUID() {
        return MUID;
    }

    public void setMUID(String MUID) {
        this.MUID = MUID;
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

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncedDate() {
        return syncedDate;
    }

    public void setSyncedDate(String syncedDate) {
        this.syncedDate = syncedDate;
    }


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }


    public String getsB6() {
        return sB6;
    }

    public void setsB6(String sB6) {
        this.sB6 = sB6;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public NutritionContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(NutritionTable.COLUMN__ID);
        this._UID = jsonObject.getString(NutritionTable.COLUMN_UID);
        this._UUID = jsonObject.getString(NutritionTable.COLUMN_UUID);
        this.MUID = jsonObject.getString(NutritionTable.COLUMN_MUID);
        this.FMUID = jsonObject.getString(NutritionTable.COLUMN_FM_UID);
        this.formDate = jsonObject.getString(NutritionTable.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(NutritionTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(NutritionTable.COLUMN_DEVICETAGID);
        this.user = jsonObject.getString(NutritionTable.COLUMN_USER);
        this.app_ver = jsonObject.getString(NutritionTable.COLUMN_APP_VER);
        this.sB6 = jsonObject.getString(NutritionTable.COLUMN_SB6);
        this.synced = jsonObject.getString(NutritionTable.COLUMN_SYNCED);
        this.syncedDate = jsonObject.getString(NutritionTable.COLUMN_SYNCEDDATE);


        return this;

    }

    public NutritionContract Hydrate(Cursor cursor, int type) {

        this._ID = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_UUID));
        this.MUID = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_MUID));
        this.FMUID = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_FM_UID));
        this.sB6 = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_SB6));

        if (type == 0) {
            this.formDate = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_FORMDATE));
            this.deviceId = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_DEVICEID));
            this.devicetagID = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_DEVICETAGID));
            this.user = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_USER));
            this.app_ver = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_APP_VER));
            this.synced = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_SYNCED));
            this.syncedDate = cursor.getString(cursor.getColumnIndex(NutritionTable.COLUMN_SYNCEDDATE));
        }

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(NutritionTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(NutritionTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(NutritionTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(NutritionTable.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(NutritionTable.COLUMN_MUID, this.MUID == null ? JSONObject.NULL : this.MUID);
        json.put(NutritionTable.COLUMN_FM_UID, this.FMUID == null ? JSONObject.NULL : this.FMUID);
        json.put(NutritionTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);

        json.put(NutritionTable.COLUMN_UPDATEDATE, this.updatedate == null ? JSONObject.NULL : this.updatedate);

        json.put(NutritionTable.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(NutritionTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(NutritionTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(NutritionTable.COLUMN_APP_VER, this.app_ver == null ? JSONObject.NULL : this.app_ver);


        if (!this.sB6.equals("")) {
            json.put(NutritionTable.COLUMN_SB6, this.sB6.equals("") ? JSONObject.NULL : new JSONObject(this.sB6));
        }

        /*json.put(NutritionTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(NutritionTable.COLUMN_SYNCEDDATE, this.syncedDate == null ? JSONObject.NULL : this.syncedDate);*/


        return json;
    }

    public static abstract class NutritionTable implements BaseColumns {

        public static final String TABLE_NAME = "nutrition";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FM_UID = "fmuid";
        public static final String COLUMN_MUID = "muid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_APP_VER = "app_ver";

        public static final String COLUMN_SB6 = "sb6";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCEDDATE = "synceddate";

        public static final String COLUMN_UPDATEDATE = "updatedate";

        public static String _URL = "nutritions.php";
    }
}
