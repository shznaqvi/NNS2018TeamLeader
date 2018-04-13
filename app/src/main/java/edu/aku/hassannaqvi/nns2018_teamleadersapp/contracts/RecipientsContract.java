package edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class RecipientsContract {

    private final String projectName = String.valueOf(R.string.app_name);
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String FMUID = "";
    private String formDate = "";
    private String deviceId = "";
    private String devicetagID = "";
    private String user = "";
    private String app_ver = "";

    private String a8aSNo = "";
    private String sA8A = "";


    private String synced = "";
    private String syncedDate = "";

    public RecipientsContract() {
    }

    public RecipientsContract(RecipientsContract ec) {
        this.a8aSNo = ec.getA8aSNo();


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

    public String getsA8A() {
        return sA8A;
    }

    public void setsA8A(String sA8A) {
        this.sA8A = sA8A;
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

    public String getA8aSNo() {
        return a8aSNo;
    }

    public void setA8aSNo(String a8aSNo) {
        this.a8aSNo = a8aSNo;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public RecipientsContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID = jsonObject.getString(RecipientsTable.COLUMN__ID);
        this._UID = jsonObject.getString(RecipientsTable.COLUMN_UID);
        this._UUID = jsonObject.getString(RecipientsTable.COLUMN_UUID);
        this.FMUID = jsonObject.getString(RecipientsTable.COLUMN_FM_UID);
        this.formDate = jsonObject.getString(RecipientsTable.COLUMN_FORMDATE);
        this.deviceId = jsonObject.getString(RecipientsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(RecipientsTable.COLUMN_DEVICETAGID);
        this.user = jsonObject.getString(RecipientsTable.COLUMN_USER);
        this.app_ver = jsonObject.getString(RecipientsTable.COLUMN_APP_VER);
        this.a8aSNo = jsonObject.getString(RecipientsTable.COLUMN_A8ASNO);
        this.sA8A = jsonObject.getString(RecipientsTable.COLUMN_SA8A);
        this.synced = jsonObject.getString(RecipientsTable.COLUMN_SYNCED);
        this.syncedDate = jsonObject.getString(RecipientsTable.COLUMN_SYNCEDDATE);


        return this;

    }

    public RecipientsContract Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_UUID));
        this.FMUID = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_FM_UID));
        this.formDate = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_FORMDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_DEVICETAGID));
        this.user = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_USER));
        this.app_ver = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_APP_VER));
        this.a8aSNo = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_A8ASNO));
        this.sA8A = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_SA8A));
        this.synced = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_SYNCED));
        this.syncedDate = cursor.getString(cursor.getColumnIndex(RecipientsTable.COLUMN_SYNCEDDATE));


        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(RecipientsTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(RecipientsTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(RecipientsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(RecipientsTable.COLUMN_UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(RecipientsTable.COLUMN_FM_UID, this.FMUID == null ? JSONObject.NULL : this.FMUID);
        json.put(RecipientsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(RecipientsTable.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(RecipientsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(RecipientsTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(RecipientsTable.COLUMN_APP_VER, this.app_ver == null ? JSONObject.NULL : this.app_ver);
        json.put(RecipientsTable.COLUMN_A8ASNO, this.a8aSNo == null ? JSONObject.NULL : this.a8aSNo);

        if (!this.sA8A.equals("")) {
            json.put(RecipientsTable.COLUMN_SA8A, this.sA8A.equals("") ? JSONObject.NULL : new JSONObject(this.sA8A));
        }

        /*json.put(RecipientsTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(RecipientsTable.COLUMN_SYNCEDDATE, this.syncedDate == null ? JSONObject.NULL : this.syncedDate);*/


        return json;
    }

    public static abstract class RecipientsTable implements BaseColumns {

        public static final String TABLE_NAME = "recipeints";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN_UID = "uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FM_UID = "fmuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_APP_VER = "app_ver";
        public static final String COLUMN_A8ASNO = "a8asno";
        public static final String COLUMN_SA8A = "sa8a";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCEDDATE = "synceddate";


        public static String _URL = "recipients.php";
    }
}
