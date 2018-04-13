package edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class OutcomeContract {

    private final String projectName = String.valueOf(R.string.app_name);
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String MUID = "";
    private String FMUID = "";
    private String formDate = "";
    private String updatedate = "";
    private String deviceId = "";
    private String devicetagID = "";
    private String user = "";
    private String app_ver = "";

    private String b1aPregSNo = "";
    private String sB1A = "";

    private String synced = "";
    private String syncedDate = "";

    public OutcomeContract() {
    }

    public OutcomeContract(OutcomeContract ec) {
        this.b1aPregSNo = ec.getB1aPregSNo();


    }

    public String getMUID() {
        return MUID;
    }

    public void setMUID(String MUID) {
        this.MUID = MUID;
    }

    public String getFMUID() {
        return FMUID;
    }

    public void setFMUID(String FMUID) {
        this.FMUID = FMUID;
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

    public String getsB1A() {
        return sB1A;
    }

    public void setsB1A(String sB1A) {
        this.sB1A = sB1A;
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

    public String getB1aPregSNo() {
        return b1aPregSNo;
    }

    public void setB1aPregSNo(String b1aPregSNo) {
        this.b1aPregSNo = b1aPregSNo;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public OutcomeContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(outcomeTable.COLUMN__ID);
        this._UID = jsonObject.getString(outcomeTable.COLUMN_UID);
        this._UUID = jsonObject.getString(outcomeTable.COLUMN_UUID);
        this.MUID = jsonObject.getString(outcomeTable.COLUMN_MUID);
        //this.FMUID = jsonObject.getString(outcomeTable.COLUMN_FM_UID);
        this.formDate = jsonObject.getString(outcomeTable.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(outcomeTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(outcomeTable.COLUMN_DEVICETAGID);
        this.user = jsonObject.getString(outcomeTable.COLUMN_USER);
        this.app_ver = jsonObject.getString(outcomeTable.COLUMN_APP_VER);
        this.b1aPregSNo = jsonObject.getString(outcomeTable.COLUMN_B1APregSNO);
        this.sB1A = jsonObject.getString(outcomeTable.COLUMN_SB1A);
        this.synced = jsonObject.getString(outcomeTable.COLUMN_SYNCED);
        this.syncedDate = jsonObject.getString(outcomeTable.COLUMN_SYNCEDDATE);


        return this;

    }

    public OutcomeContract Hydrate(Cursor cursor, int type) {

        this._ID = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_UUID));
        //this.FMUID = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_FM_UID));
        this.MUID = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_MUID));

        this.sB1A = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_SB1A));

        if (type == 0) {
            this.formDate = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_FORMDATE));
            this.deviceId = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_DEVICEID));
            this.devicetagID = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_DEVICETAGID));
            this.user = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_USER));
            this.app_ver = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_APP_VER));
            this.b1aPregSNo = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_B1APregSNO));
            this.synced = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_SYNCED));
            this.syncedDate = cursor.getString(cursor.getColumnIndex(outcomeTable.COLUMN_SYNCEDDATE));
        }

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(outcomeTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(outcomeTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(outcomeTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(outcomeTable.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        //json.put(outcomeTable.COLUMN_FM_UID, this.FMUID == null ? JSONObject.NULL : this.FMUID);
        json.put(outcomeTable.COLUMN_MUID, this.MUID == null ? JSONObject.NULL : this.MUID);

        json.put(outcomeTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);

        json.put(outcomeTable.COLUMN_UPDATEDATE, this.updatedate == null ? JSONObject.NULL : this.updatedate);

        json.put(outcomeTable.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(outcomeTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(outcomeTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(outcomeTable.COLUMN_APP_VER, this.app_ver == null ? JSONObject.NULL : this.app_ver);
        json.put(outcomeTable.COLUMN_B1APregSNO, this.b1aPregSNo == null ? JSONObject.NULL : this.b1aPregSNo);

        if (!this.sB1A.equals("")) {
            json.put(outcomeTable.COLUMN_SB1A, this.sB1A.equals("") ? JSONObject.NULL : new JSONObject(this.sB1A));
        }

        /*json.put(outcomeTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(outcomeTable.COLUMN_SYNCEDDATE, this.syncedDate == null ? JSONObject.NULL : this.syncedDate);*/


        return json;
    }

    public static abstract class outcomeTable implements BaseColumns {

        public static final String TABLE_NAME = "outcome";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_MUID = "muid";
        public static final String COLUMN_FM_UID = "fmuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_UPDATEDATE = "updatedate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_APP_VER = "app_ver";
        public static final String COLUMN_B1APregSNO = "b1serialno";
        public static final String COLUMN_SB1A = "sb1a";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCEDDATE = "synceddate";


        public static String _URL = "outcomes.php";
    }
}
