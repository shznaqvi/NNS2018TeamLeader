package edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;

/**
 * Created by javed.khan on 1/22/2018.
 */

public class ChildContract {


    private final String projectName = String.valueOf(R.string.app_name);
    private String _ID = "";
    private String _UID = "";
    private String _UUID = "";
    private String FMUID = "";
    private String MUID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String c1SerialNo = "";

    private String sC1 = ""; // sB
    private String sC2 = ""; // sB
    private String sC3 = ""; // sB
    private String sC4 = ""; // sB
    private String sC5 = ""; // sB

    //private String sC = ""; //
    //private String sD = "";


    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion;
    private String cstatus = "";
    private String cstatus88x = "";

    public ChildContract() {

    }


    public ChildContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(ChildTable.COLUMN__ID);
        this._UID = jsonObject.getString(ChildTable.COLUMN__UID);
        this._UUID = jsonObject.getString(ChildTable.COLUMN__UUID);
        this.FMUID = jsonObject.getString(ChildTable.COLUMN_FM_UID);
        this.MUID = jsonObject.getString(ChildTable.COLUMN_MUID);

        this.formDate = jsonObject.getString(ChildTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(ChildTable.COLUMN_USER);
        this.c1SerialNo = jsonObject.getString(ChildTable.COLUMN_C1SERIALNO);
        this.sC1 = jsonObject.getString(ChildTable.COLUMN_SC1);
        this.sC2 = jsonObject.getString(ChildTable.COLUMN_SC2);
        this.sC3 = jsonObject.getString(ChildTable.COLUMN_SC3);
        this.sC4 = jsonObject.getString(ChildTable.COLUMN_SC4);
        this.sC5 = jsonObject.getString(ChildTable.COLUMN_SC5);
        this.deviceID = jsonObject.getString(ChildTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(ChildTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(ChildTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(ChildTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(ChildTable.COLUMN_APPVERSION);
        this.cstatus = jsonObject.getString(ChildTable.COLUMN_CSTATUS);
        this.cstatus88x = jsonObject.getString(ChildTable.COLUMN_CSTATUS88x);


        return this;

    }

    public ChildContract Hydrate(Cursor cursor, int type) {


        this._ID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN__UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN__UUID));
        this.FMUID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_FM_UID));
        this.MUID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_MUID));

        if (type == 1 || type == 2) {
            this.sC2 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SC2));
        }
        if (type == 1 || type == 3) {
            this.sC3 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SC3));
        }
        if (type == 1 || type == 4) {
            this.sC4 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SC4));
        }
        if (type == 1 || type == 5) {
            this.sC5 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SC5));
        }

        if (type == 1) {
            this.formDate = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_FORMDATE));
            this.user = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_USER));
            this.c1SerialNo = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_C1SERIALNO));
            this.sC1 = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SC1));
            this.deviceID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_DEVICEID));
            this.devicetagID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_DEVICETAGID));
            this.synced = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SYNCED));
            this.synced_date = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SYNCED_DATE));
            this.appversion = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_APPVERSION));
            this.cstatus = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CSTATUS));
            this.cstatus88x = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_CSTATUS88x));
        }
        // TODO:

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(ChildTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(ChildTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(ChildTable.COLUMN__UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(ChildTable.COLUMN__UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(ChildTable.COLUMN_FM_UID, this.FMUID == null ? JSONObject.NULL : this.FMUID);
        json.put(ChildTable.COLUMN_MUID, this.MUID == null ? JSONObject.NULL : this.MUID);

        json.put(ChildTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(ChildTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(ChildTable.COLUMN_C1SERIALNO, this.c1SerialNo == null ? JSONObject.NULL : this.c1SerialNo);

        if (!this.sC1.equals("")) {
            json.put(ChildTable.COLUMN_SC1, this.sC1.equals("") ? JSONObject.NULL : new JSONObject(this.sC1));
            /*  json.put(ChildTable.COLUMN_SC1, this.sC1 == null ? JSONObject.NULL : this.sC1);*/
        }

        if (!this.sC2.equals("")) {
            json.put(ChildTable.COLUMN_SC2, this.sC2.equals("") ? JSONObject.NULL : new JSONObject(this.sC2));
            // json.put(ChildTable.COLUMN_SC2, this.sC2 == null ? JSONObject.NULL : this.sC2);
        }

        if (!this.sC3.equals("")) {
            json.put(ChildTable.COLUMN_SC3, this.sC3.equals("") ? JSONObject.NULL : new JSONObject(this.sC3));
//            json.put(ChildTable.COLUMN_SC3, this.sC3 == null ? JSONObject.NULL : this.sC3);
        }

        if (!this.sC4.equals("")) {
            json.put(ChildTable.COLUMN_SC4, this.sC4.equals("") ? JSONObject.NULL : new JSONObject(this.sC4));
//            json.put(ChildTable.COLUMN_SC4, this.sC4 == null ? JSONObject.NULL : this.sC4);
        }

        if (!this.sC5.equals("")) {
            json.put(ChildTable.COLUMN_SC5, this.sC5.equals("") ? JSONObject.NULL : new JSONObject(this.sC5));
//            json.put(ChildTable.COLUMN_SC5, this.sC5 == null ? JSONObject.NULL : this.sC5);
        }

        json.put(ChildTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(ChildTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        /*json.put(ChildTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(ChildTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);*/
        json.put(ChildTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(ChildTable.COLUMN_CSTATUS, this.cstatus == null ? JSONObject.NULL : this.cstatus);
        json.put(ChildTable.COLUMN_CSTATUS88x, this.cstatus88x == null ? JSONObject.NULL : this.cstatus88x);


        return json;
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


    public String getC1SerialNo() {
        return c1SerialNo;
    }

    public void setC1SerialNo(String c1SerialNo) {
        this.c1SerialNo = c1SerialNo;
    }

    public String getsC1() {
        return sC1;
    }

    public void setsC1(String sC1) {
        this.sC1 = sC1;
    }

    public String getsC2() {
        return sC2;
    }

    public void setsC2(String sC2) {
        this.sC2 = sC2;
    }

    public String getsC3() {
        return sC3;
    }

    public void setsC3(String sC3) {
        this.sC3 = sC3;
    }

    public String getsC4() {
        return sC4;
    }

    public void setsC4(String sC4) {
        this.sC4 = sC4;
    }

    public String getsC5() {
        return sC5;
    }

    public void setsC5(String sC5) {
        this.sC5 = sC5;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public String getCstatus88x() {
        return cstatus88x;
    }

    public void setCstatus88x(String cstatus88x) {
        this.cstatus88x = cstatus88x;
    }

    public String getMUID() {
        return MUID;
    }

    public void setMUID(String MUID) {
        this.MUID = MUID;
    }

    public static abstract class ChildTable implements BaseColumns {

        public static final String TABLE_NAME = "child";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN__UID = "_uid";
        public static final String COLUMN__UUID = "_uuid";
        public static final String COLUMN_FM_UID = "fmuid";
        public static final String COLUMN_MUID = "muid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_C1SERIALNO = "c1serialno";
        public static final String COLUMN_SC1 = "sc1";
        public static final String COLUMN_SC2 = "sc2";
        public static final String COLUMN_SC3 = "sc3";
        public static final String COLUMN_SC4 = "sc4";
        public static final String COLUMN_SC5 = "sc5";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_CSTATUS = "cstatus";
        public static final String COLUMN_CSTATUS88x = "cstatus88x";

        public static String _URL = "children.php";
    }

}