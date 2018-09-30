package com.johndevjames.myandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL("create table LOGIN( ID integer primary key autoincrement  NOT NULL,USER_ID Integer,USER_NAME  text,USER_STATUS  text,BRANCH_NAME text,COMPANY_NAME  text,COMPANY_ID text,COM_STATUS  text,EID text,EUID text,PREFIX text,MAX_ORDER_NO Integer,ERROR text,ERROR_MSG text,MOB text,EMAIL text); ");
        _db.execSQL("create table CUSTDETAILS( ID integer primary key autoincrement  NOT NULL,SHOP_ID  integer,OWNER_NAME  text,SHOP_NAME text,SHOP_PLACE text,SHOP_ADDRESS text,PHONE_NUMBER text,MOBILE_NUMBER text,AREA text,DISTRICT text,EMAIL TEXT,PIN num,TIN num,LAND_MARK text,PENDING_AMOUNT text,ADDED_BY text,LAST_UPDATE text ); ");
        _db.execSQL("create table ORDERING( ID integer primary key autoincrement NOT NULL,ITEM_ID  integer,ITEM_NAME text,ITEM_CODE text,QUANTITY integer,RATE num,MRP num); ");
        _db.execSQL("create table ORDERED_ITEMS( ID integer primary key autoincrement  NOT NULL,CUSTOMER_ID integer ,ORDER_DATE  text,ORDER_NO integer,ORDERED_USER integer,PRODUCT_ID integer,NARRATION  text,QTY integer,LAST_SYNC,FOREIGN KEY (ORDERED_USER) REFERENCES LOGIN(USER_ID)FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTDETAILS(SHOP_ID)FOREIGN KEY (PRODUCT_ID) REFERENCES ORDERING(ITEM_ID));");
        _db.execSQL("create table SETTINGS( ID integer primary key autoincrement NOT NULL,LAST_SYNC text,LAST_SERVER_DATE text,COMPANY_ID text); ");
    }

    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to " + _newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS TEMPLATE");
        onCreate(_db);
    }
}
