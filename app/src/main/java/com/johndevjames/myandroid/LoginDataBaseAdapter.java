package com.johndevjames.myandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.ArrayList;
import java.util.List;

public class LoginDataBaseAdapter implements BaseColumns {
    public static final String COLUMN_NAME_TITLE = "USERNAME";
    public static final String COLUMN_NAME_TITLE2 = "PASSWORD";
    static final String DATABASE_CREATE = "create table LOGIN( ID integer primary key autoincrement  NOT NULL,USER_ID Integer,USER_NAME  text,USER_STATUS  text,BRANCH_NAME text,COMPANY_NAME  text,COMPANY_ID text,COM_STATUS  text,EID text,EUID text,PREFIX text,MAX_ORDER_NO Integer,ERROR text,ERROR_MSG text,MOB text,EMAIL text); ";
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    public static final String TABLE_NAME = "LOGIN";
    private  Context context = null;
    public SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    public LoginDataBaseAdapter(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, NAME_COLUMN);

    }

    public LoginDataBaseAdapter open() throws SQLException {
        this.db = this.dbHelper.getWritableDatabase();
        return this;
    }

    public LoginDataBaseAdapter op() throws SQLException {
        this.db = this.dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        this.db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return this.db;
    }

    public void insertEntry(String branchName, Integer companyid, String companyName, String comStatus, String eid, String email, String error, String erroMsg, String euid, String userName, String Mob, int userId, String status, String prfeix, Integer max_order_no) {
        ContentValues newValues = new ContentValues();
        newValues.put("BRANCH_NAME", branchName);
        newValues.put("COMPANY_NAME", companyName);
        newValues.put("COM_STATUS", comStatus);
        newValues.put("EID", eid);
        newValues.put("EMAIL", email);
        newValues.put("ERROR", error);
        newValues.put("ERROR_MSG", erroMsg);
        newValues.put("COMPANY_ID", companyid);
        newValues.put("EUID", euid);
        newValues.put("USER_ID", Integer.valueOf(userId));
        newValues.put("USER_NAME", userName);
        newValues.put("MOB", Mob);
        newValues.put("USER_STATUS", status);
        newValues.put("PREFIX", prfeix);
        newValues.put("MAX_ORDER_NO", max_order_no);
        this.db.insert(TABLE_NAME, null, newValues);
    }

    public int CheckOrder(Integer user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select MAX_ORDER_NO from LOGIN where USER_ID=?", strArr, null);
        int i = 0;
        while (cursor.moveToNext()) {
            i = cursor.getInt(cursor.getColumnIndex("MAX_ORDER_NO"));
            if (i != 0) {
                i += NAME_COLUMN;
            }
        }
        cursor.close();
        return i;
    }

    public String get_Prfeix(Integer user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select PREFIX from LOGIN where USER_ID=?", strArr, null);
        String i = null;
        while (cursor.moveToNext()) {
            i = cursor.getString(cursor.getColumnIndex("PREFIX"));
        }
        cursor.close();
        return i;
    }

    public int deleteEntry(String UserName) {
        SQLiteDatabase sQLiteDatabase = this.db;
        String str = TABLE_NAME;
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = UserName;
        return sQLiteDatabase.delete(str, "USERNAME=?", strArr);
    }

    public String getSinlgeEntry(String userName) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = userName;
        Cursor cursor = this.db.query(TABLE_NAME, null, " USERNAME=?", strArr, null, null, null);
        if (cursor.getCount() < NAME_COLUMN) {
            cursor.close();
            return userName;
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE2));
        String username = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE));
        cursor.close();
        return password;
    }

    public List getEntry() {
        Cursor cursor = this.db.rawQuery("Select * from LOGIN", null);
        List Users = new ArrayList();
        while (cursor.moveToNext()) {
            Users.add(cursor.getString(cursor.getColumnIndex("USER_NAME")));
        }
        cursor.close();
        return Users;
    }

    public String getValidate(Integer user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select USER_STATUS from LOGIN where USER_ID=?", strArr, null);
        String type = null;
        while (cursor.moveToNext()) {
            type = cursor.getString(cursor.getColumnIndex("USER_STATUS"));
        }
        cursor.close();
        return type;
    }

    public String getValidated(Integer user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select USER_NAME from LOGIN where USER_ID=?", strArr, null);
        String user_name = null;
        while (cursor.moveToNext()) {
            user_name = cursor.getString(cursor.getColumnIndex("USER_NAME"));
        }
        cursor.close();
        return user_name;
    }

    public String getEid(Integer user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select EUID from LOGIN where USER_ID=?", strArr, null);
        String eu_id = null;
        while (cursor.moveToNext()) {
            eu_id = cursor.getString(cursor.getColumnIndex("EUID"));
        }
        cursor.close();
        return eu_id;
    }

    public String getUsername(Integer user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select USER_NAME from LOGIN where USER_ID=?", strArr, null);
        String username = null;
        while (cursor.moveToNext()) {
            username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
        }
        cursor.close();
        return username;
    }

    public String getStatus(Integer user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select USER_STATUS from LOGIN where USER_ID=?", strArr, null);
        String userstatus = null;
        while (cursor.moveToNext()) {
            userstatus = cursor.getString(cursor.getColumnIndex("USER_STATUS"));
        }
        cursor.close();
        return userstatus;
    }

    public String checkUsername(String user_name) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_name);
        Cursor cursor = this.db.rawQuery("Select USER_NAME from LOGIN where USER_NAME=?", strArr, null);
        String username = null;
        while (cursor.moveToNext()) {
            username = cursor.getString(cursor.getColumnIndex("USER_NAME"));
        }
        cursor.close();
        return username;
    }

    public String getuser_id(String user_name) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_name);
        Cursor cursor = this.db.rawQuery("Select USER_ID from LOGIN where USER_NAME=?", strArr, null);
        String userid = null;
        while (cursor.moveToNext()) {
            userid = cursor.getString(cursor.getColumnIndex("USER_ID"));
        }
        cursor.close();
        return userid;
    }

    public Integer getValidated2(int user_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(user_id);
        Cursor cursor = this.db.rawQuery("Select USER_ID from LOGIN where USER_ID=?", strArr, null);
        Integer id = Integer.valueOf(0);
        while (cursor.moveToNext()) {
            id = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("USER_ID")));
        }
        cursor.close();
        return id;
    }

    public void readEntry(String userName) {
        String[] projection = new String[]{COLUMN_NAME_TITLE, COLUMN_NAME_TITLE2};
        String selection = projection + " = ?";
        String[] selectionArgs = new String[NAME_COLUMN];
        selectionArgs[0] = userName;
        Cursor cursor = this.db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            String u = cursor.getString(Integer.parseInt(COLUMN_NAME_TITLE));
            cursor.getString(Integer.parseInt(COLUMN_NAME_TITLE2));
        }
        cursor.close();
    }

    public void updateEntry(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_NAME_TITLE, userName);
        updatedValues.put(COLUMN_NAME_TITLE2, password);
        SQLiteDatabase sQLiteDatabase = this.db;
        String str = TABLE_NAME;
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = userName;
        sQLiteDatabase.update(str, updatedValues, "USERNAME = ?", strArr);
    }
}
