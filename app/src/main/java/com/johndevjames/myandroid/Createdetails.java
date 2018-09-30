package com.johndevjames.myandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class Createdetails extends HomeActivity {
    static final String DATABASE_CREATE1 = "create table CUSTDETAILS( ID integer primary key autoincrement  NOT NULL,SHOP_ID  integer,OWNER_NAME  text,SHOP_NAME text,SHOP_PLACE text,SHOP_ADDRESS text,PHONE_NUMBER text,MOBILE_NUMBER text,AREA text,DISTRICT text,EMAIL TEXT,PIN num,TIN num,LAND_MARK text,PENDING_AMOUNT text,ADDED_BY text,LAST_UPDATE text ); ";
    static final String DATABASE_CREATE2 = "create table ORDERING( ID integer primary key autoincrement NOT NULL,ITEM_ID  integer,ITEM_NAME text,ITEM_CODE text,QUANTITY integer,RATE num,MRP num); ";
    static final String DATABASE_CREATE3 = "create table ORDERED_ITEMS( ID integer primary key autoincrement  NOT NULL,CUSTOMER_ID integer ,ORDER_DATE  text,ORDER_NO integer,ORDERED_USER integer,PRODUCT_ID integer,NARRATION  text,QTY integer,LAST_SYNC,FOREIGN KEY (ORDERED_USER) REFERENCES LOGIN(USER_ID)FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTDETAILS(SHOP_ID)FOREIGN KEY (PRODUCT_ID) REFERENCES ORDERING(ITEM_ID));";
    static final String DATABASE_CREATE4 = "create table SETTINGS( ID integer primary key autoincrement NOT NULL,LAST_SYNC text,LAST_SERVER_DATE text,COMPANY_ID text); ";
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    private final Context context;
    public SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public Createdetails(Context _context) {
        this.context = _context;
        this.dbHelper = new DataBaseHelper(this.context, DATABASE_NAME, null, NAME_COLUMN);
    }

    public Createdetails() {
        this.context = null;
    }

    public Createdetails open() throws SQLException {
        this.db = this.dbHelper.getWritableDatabase();
        return this;
    }

    public Createdetails read() throws SQLException {
        this.db = this.dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        this.db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return this.db;
    }

    public int Check_max_id_Order() {
        Cursor cursor = this.db.rawQuery("Select MAX(ORDER_NO) from ORDERED_ITEMS", null, null);
        int i = 0;
        while (cursor.moveToNext()) {
            i = cursor.getInt(0);
        }
        cursor.close();
        return i;
    }

    public void deleteTABLE_TableExists() {
        this.db.execSQL("delete from CUSTDETAILS");
        this.db.execSQL("delete from ORDERING");
        this.db.execSQL("delete from ORDERED_ITEMS");
        this.db.execSQL("delete from SETTINGS");
    }

    public void insertOrder(int CUSTOMER_ID, String ORDER_DATE, int ORDER_NO, int ORDERED_USER, int PRODUCT_ID, int QTY, String last_updated, String narrate) {
        ContentValues newValues1 = new ContentValues();
        newValues1.put("CUSTOMER_ID", Integer.valueOf(CUSTOMER_ID));
        newValues1.put("ORDER_DATE", ORDER_DATE);
        newValues1.put("ORDER_NO", Integer.valueOf(ORDER_NO));
        newValues1.put("ORDERED_USER", Integer.valueOf(ORDERED_USER));
        newValues1.put("PRODUCT_ID", Integer.valueOf(PRODUCT_ID));
        newValues1.put("QTY ", Integer.valueOf(QTY));
        newValues1.put("NARRATION ", narrate);
        newValues1.put("LAST_SYNC ", last_updated);
        this.db.insert("ORDERED_ITEMS", null, newValues1);
    }

    public void insertItems(String itemId, String itemName, String itemCode, String Qty, String Rate, String Mrp) {
        ContentValues newValues1 = new ContentValues();
        newValues1.put("ITEM_ID", itemId);
        newValues1.put("ITEM_NAME", itemName);
        newValues1.put("ITEM_CODE", itemCode);
        newValues1.put("QUANTITY", Qty);
        newValues1.put("RATE", Rate);
        newValues1.put("MRP ", Mrp);
        this.db.insert("ORDERING", null, newValues1);
    }

    public void updateItems(String itemId, String itemName, String itemCode, String Qty, String Rate, String Mrp) {
        ContentValues updateitems = new ContentValues();
        updateitems.put("ITEM_ID", itemId);
        updateitems.put("ITEM_NAME", itemName);
        updateitems.put("ITEM_CODE", itemCode);
        updateitems.put("QUANTITY", Qty);
        updateitems.put("RATE", Rate);
        updateitems.put("MRP ", Mrp);
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = itemId;
        this.db.update("ORDERING", updateitems, "ITEM_ID = ?", strArr);
    }

    public void addnew_item_Order_Entry(String ORDER_DATE, String PRODUCT_ID, String QTY, String last_updated, Integer position) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("ORDER_DATE", ORDER_DATE);
        updatedValues.put("PRODUCT_ID", PRODUCT_ID);
        updatedValues.put("QTY", QTY);
        updatedValues.put("LAST_SYNC", last_updated);
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        this.db.update("ORDERED_ITEMS", updatedValues, "ID = ?", strArr);
    }

    public void updateOrderEntry(String QTY, Integer position, String last_updated) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("QTY", QTY);
        updatedValues.put("LAST_SYNC", last_updated);
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        this.db.update("ORDERED_ITEMS", updatedValues, "ID = ?", strArr);
    }

    public void insertEntry(String ShopId, String ShopName, String OwnerName, String ShopPlace, String ShopAddress, String PhoneNumber, String MobileNumber, String Area, String District, String Email, String Pin, String Tin, String Land_mark, String PenAmt, String usr_name, String last_update) {
        ContentValues newValues = new ContentValues();
        newValues.put("SHOP_ID", ShopId);
        newValues.put("SHOP_NAME", ShopName);
        newValues.put("OWNER_NAME", OwnerName);
        newValues.put("SHOP_PLACE", ShopPlace);
        newValues.put("SHOP_ADDRESS", ShopAddress);
        newValues.put("PHONE_NUMBER", PhoneNumber);
        newValues.put("MOBILE_NUMBER", MobileNumber);
        newValues.put("AREA", Area);
        newValues.put("DISTRICT", District);
        newValues.put("EMAIL", Email);
        newValues.put("PIN", Pin);
        newValues.put("TIN", Tin);
        newValues.put("LAND_MARK", Land_mark);
        newValues.put("PENDING_AMOUNT", PenAmt);
        newValues.put("ADDED_BY", usr_name);
        newValues.put("LAST_UPDATE", last_update);

        this.db.insert("CUSTDETAILS", null, newValues);
    }

    public void insertInternalEntry(String ShopName, String OwnerName, String ShopPlace, String ShopAddress, String PhoneNumber, String MobileNumber, String Area, String District, String Email, String Pin, String Tin, String Land_mark, String PenAmt, String usr_name, String last_update) {
        ContentValues newValues = new ContentValues();
        newValues.put("SHOP_NAME", ShopName);
        newValues.put("OWNER_NAME", OwnerName);
        newValues.put("SHOP_PLACE", ShopPlace);
        newValues.put("SHOP_ADDRESS", ShopAddress);
        newValues.put("PHONE_NUMBER", PhoneNumber);
        newValues.put("MOBILE_NUMBER", MobileNumber);
        newValues.put("AREA", Area);
        newValues.put("DISTRICT", District);
        newValues.put("EMAIL", Email);
        newValues.put("PIN", Pin);
        newValues.put("TIN", Tin);
        newValues.put("LAND_MARK", Land_mark);
        newValues.put("PENDING_AMOUNT", PenAmt);
        newValues.put("ADDED_BY", usr_name);
        newValues.put("LAST_UPDATE", last_update);
        this.db.insert("CUSTDETAILS", null, newValues);
    }

    public List getSinlgeEntry() {
        Cursor cursor = this.db.rawQuery("Select * from CUSTDETAILS", null, null);
        List Details = new ArrayList();
        while (cursor.moveToNext()) {
            Details.add(new Tutorial2(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ID"))),
                    cursor.getString(cursor.getColumnIndex("SHOP_NAME")),
                    Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PENDING_AMOUNT")))));
        }
        cursor.close();
        return Details;
    }

    public List getAllCustDeatils() {
        Cursor cursor = this.db.rawQuery("Select * from CUSTDETAILS", null, null);
        List Details = new ArrayList();
        while (cursor.moveToNext()) {
            Details.add(new Tutorial2(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("SHOP_ID"))),
                    cursor.getString(cursor.getColumnIndex("SHOP_NAME")),
                    Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PENDING_AMOUNT")))));
        }
        cursor.close();
        return Details;
    }

    public void updateEntry(String ShopId, String ShopName, String OwnerName,
                            String ShopPlace, String ShopAddress, String PhoneNumber,
                            String MobileNumber, String Area, String district, String Email,
                            String Pin, String Tin, String Land_mark, String PenAmt, String usr_name,
                            String last_update, Integer position) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("SHOP_ID", ShopId);
        updatedValues.put("SHOP_NAME", ShopName);
        updatedValues.put("OWNER_NAME", OwnerName);
        updatedValues.put("SHOP_PLACE", ShopPlace);
        updatedValues.put("SHOP_ADDRESS", ShopAddress);
        updatedValues.put("MOBILE_NUMBER", PhoneNumber);
        updatedValues.put("PHONE_NUMBER", MobileNumber);
        updatedValues.put("AREA", Area);
        updatedValues.put("DISTRICT", district);
        updatedValues.put("EMAIL", Email);
        updatedValues.put("PIN", Pin);
        updatedValues.put("TIN", Tin);
        updatedValues.put("LAND_MARK", Land_mark);
        updatedValues.put("PENDING_AMOUNT", PenAmt);
        updatedValues.put("ADDED_BY", usr_name);
        updatedValues.put("LAST_UPDATE", last_update);
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        this.db.update("CUSTDETAILS", updatedValues, "ID = ?", strArr);
    }

    public void updateEntryUsingcus_id(String ShopId, String ShopName, String OwnerName, String ShopPlace, String ShopAddress, String PhoneNumber, String MobileNumber, String Area, String district, String Email, String Pin, String Tin, String Land_mark, String PenAmt, String usr_name, String last_update, String cus_id) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("SHOP_ID", ShopId);
        updatedValues.put("SHOP_NAME", ShopName);
        updatedValues.put("OWNER_NAME", OwnerName);
        updatedValues.put("SHOP_PLACE", ShopPlace);
        updatedValues.put("SHOP_ADDRESS", ShopAddress);
        updatedValues.put("MOBILE_NUMBER", PhoneNumber);
        updatedValues.put("PHONE_NUMBER", MobileNumber);
        updatedValues.put("AREA", Area);
        updatedValues.put("DISTRICT", district);
        updatedValues.put("EMAIL", Email);
        updatedValues.put("PIN", Pin);
        updatedValues.put("TIN", Tin);
        updatedValues.put("LAND_MARK", Land_mark);
        updatedValues.put("PENDING_AMOUNT", PenAmt);
        updatedValues.put("ADDED_BY", usr_name);
        updatedValues.put("LAST_UPDATE", last_update);
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = cus_id;
        this.db.update("CUSTDETAILS", updatedValues, "SHOP_ID = ?", strArr);
    }

    public List getSinlgeE(String position) {
        SQLiteDatabase sQLiteDatabase = this.db;
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = position;
        Cursor cursor = sQLiteDatabase.rawQuery("Select * from CUSTDETAILS where SHOP_ID=?", strArr, null);
        List Details = new ArrayList();
        while (cursor.moveToNext()) {
            Integer id = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ID")));
            String ShopName = cursor.getString(cursor.getColumnIndex("SHOP_NAME"));
            String OwnerName = cursor.getString(cursor.getColumnIndex("OWNER_NAME"));
            String ShopPlace = cursor.getString(cursor.getColumnIndex("SHOP_PLACE"));
            String ShopAddress = cursor.getString(cursor.getColumnIndex("SHOP_ADDRESS"));
            String PhoneNumber = cursor.getString(cursor.getColumnIndex("PHONE_NUMBER"));
            String MobileNumber = cursor.getString(cursor.getColumnIndex("MOBILE_NUMBER"));
            String Area = cursor.getString(cursor.getColumnIndex("AREA"));
            String District = cursor.getString(cursor.getColumnIndex("DISTRICT"));
            String Email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            Integer Pin = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PIN")));
            String Tin = cursor.getString(cursor.getColumnIndex("TIN"));
            String Land_mark = cursor.getString(cursor.getColumnIndex("LAND_MARK"));
            int columnIndex = cursor.getColumnIndex("ADDED_BY");
            Details.add(new Tutorial(id, ShopName, OwnerName, ShopPlace, ShopAddress, PhoneNumber, MobileNumber, Area, District, Email, Pin, Tin, Land_mark, cursor.getString(columnIndex)));
        }
        cursor.close();
        return Details;
    }

    public List getIDSinlgeE(String position) {
        SQLiteDatabase sQLiteDatabase = this.db;
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = position;
        Cursor cursor = sQLiteDatabase.rawQuery("Select * from CUSTDETAILS where  ID=?", strArr, null);
        List Details = new ArrayList();
        while (cursor.moveToNext()) {
            Integer id = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ID")));
            String ShopName = cursor.getString(cursor.getColumnIndex("SHOP_NAME"));
            String OwnerName = cursor.getString(cursor.getColumnIndex("OWNER_NAME"));
            String ShopPlace = cursor.getString(cursor.getColumnIndex("SHOP_PLACE"));
            String ShopAddress = cursor.getString(cursor.getColumnIndex("SHOP_ADDRESS"));
            String PhoneNumber = cursor.getString(cursor.getColumnIndex("PHONE_NUMBER"));
            String MobileNumber = cursor.getString(cursor.getColumnIndex("MOBILE_NUMBER"));
            String Area = cursor.getString(cursor.getColumnIndex("AREA"));
            String District = cursor.getString(cursor.getColumnIndex("DISTRICT"));
            String Email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            Integer Pin = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PIN")));
            String Tin = cursor.getString(cursor.getColumnIndex("TIN"));
            String Land_mark = cursor.getString(cursor.getColumnIndex("LAND_MARK"));
            int columnIndex = cursor.getColumnIndex("ADDED_BY");
            Details.add(new Tutorial(id, ShopName, OwnerName, ShopPlace, ShopAddress, PhoneNumber, MobileNumber, Area, District, Email, Pin, Tin, Land_mark, cursor.getString(columnIndex)));
        }
        cursor.close();
        return Details;
    }

    public String getnullSinlgeEE(Integer position) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        Cursor cursor = this.db.rawQuery("Select * from CUSTDETAILS where ID=?", strArr, null);
        String ShopId = null;
        while (cursor.moveToNext()) {
            ShopId = cursor.getString(cursor.getColumnIndex("SHOP_ID"));
        }
        cursor.close();
        return ShopId;
    }

    public String getnullSinlgeE(Integer position) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        Cursor cursor = this.db.rawQuery("Select * from CUSTDETAILS where SHOP_ID=?", strArr, null);
        String ShopId = null;
        while (cursor.moveToNext()) {
            ShopId = cursor.getString(cursor.getColumnIndex("SHOP_ID"));
        }
        cursor.close();
        return ShopId;
    }

    public List getcustomerdetailsafterthedate(String last_update) {
        SQLiteDatabase sQLiteDatabase = this.db;
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = last_update;
        Cursor cursor = sQLiteDatabase.rawQuery("Select * from CUSTDETAILS where datetime(LAST_UPDATE)>?", strArr, null);
        List Details = new ArrayList();
        while (cursor.moveToNext()) {
            Integer cusid = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("SHOP_ID")));
            String ShopName = cursor.getString(cursor.getColumnIndex("SHOP_NAME"));
            String OwnerName = cursor.getString(cursor.getColumnIndex("OWNER_NAME"));
            String ShopPlace = cursor.getString(cursor.getColumnIndex("SHOP_PLACE"));
            String ShopAddress = cursor.getString(cursor.getColumnIndex("SHOP_ADDRESS"));
            String PhoneNumber = cursor.getString(cursor.getColumnIndex("PHONE_NUMBER"));
            String MobileNumber = cursor.getString(cursor.getColumnIndex("MOBILE_NUMBER"));
            String Area = cursor.getString(cursor.getColumnIndex("AREA"));
            String District = cursor.getString(cursor.getColumnIndex("DISTRICT"));
            String Email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            Integer Pin = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PIN")));
            String Tin = cursor.getString(cursor.getColumnIndex("TIN"));
            int columnIndex = cursor.getColumnIndex("LAND_MARK");
            Details.add(new Tutorial(cusid, ShopName, OwnerName, ShopPlace, ShopAddress, PhoneNumber, MobileNumber, Area, District, Email, Pin, Tin, cursor.getString(columnIndex), null));
        }
        cursor.close();
        return Details;
    }

    public ArrayList getAllOrder() {
        Cursor cursor = this.db.rawQuery("Select * from ORDERING", null, null);
        ArrayList<DataModel> Order_Details = new ArrayList();
        while (cursor.moveToNext()) {
            Order_Details.add(new DataModel(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ITEM_ID"))), cursor.getString(cursor.getColumnIndex("ITEM_NAME")), cursor.getString(cursor.getColumnIndex("ITEM_CODE")), cursor.getString(cursor.getColumnIndex("QUANTITY")), cursor.getString(cursor.getColumnIndex("RATE")), cursor.getString(cursor.getColumnIndex("MRP"))));
        }
        cursor.close();
        return Order_Details;
    }

    public List getProductEntry() {
        Cursor cursor = this.db.rawQuery("Select ITEM_NAME from ORDERING", null, null);
        List ItemsW = new ArrayList();
        while (cursor.moveToNext()) {
            ItemsW.add(cursor.getString(cursor.getColumnIndex("ITEM_NAME")));
        }
        return ItemsW;
    }

    public List getAllOrderDetails() {
        Cursor cursor = this.db.rawQuery("Select * from ORDERED_ITEMS GROUP BY ORDER_NO,CUSTOMER_ID,ORDERED_USER", null, null);
        List Order2_Details = new ArrayList();
        while (cursor.moveToNext()) {
            Order2_Details.add(new DataModel3(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("CUSTOMER_ID"))), cursor.getString(cursor.getColumnIndex("ORDER_DATE")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ORDER_NO"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ORDERED_USER")))));
        }
        cursor.close();
        return Order2_Details;
    }

    public List getAllOrderDetailsbasedonorderno(int orderNo) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(orderNo);
        Cursor cursor = this.db.rawQuery("Select * from ORDERED_ITEMS where ORDER_NO=?", strArr, null);
        List Order2_Details = new ArrayList();
        while (cursor.moveToNext()) {
            Order2_Details.add(new DataModel2(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("CUSTOMER_ID"))), cursor.getString(cursor.getColumnIndex("ORDER_DATE")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ORDER_NO"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PRODUCT_ID"))), cursor.getString(cursor.getColumnIndex("NARRATION")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("QTY"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ID"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ORDERED_USER")))));
        }
        cursor.close();
        return Order2_Details;
    }

    public List getAllOrderDetailsAgain() {
        Cursor cursor = this.db.rawQuery("Select * from ORDERED_ITEMS", null, null);
        List Order2_DetailsAgain = new ArrayList();
        while (cursor.moveToNext()) {
            Order2_DetailsAgain.add(cursor.getString(cursor.getColumnIndex("ID")));
        }
        cursor.close();
        return Order2_DetailsAgain;
    }

    public int getUPDATED_STATUS(String date) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = date;
        Cursor cursor = this.db.rawQuery("Select ID from CUSTDETAILS where LAST_UPDATE>=?", strArr, null);
        int last_update = 0;
        while (cursor.moveToNext()) {
            last_update = cursor.getInt(cursor.getColumnIndex("ID"));
        }
        cursor.close();
        return last_update;
    }

    public String getUPDATED_STATUS2() {
        Cursor cursor = this.db.rawQuery("Select LAST_SYNC from SETTINGS", null);
        String last_sync = null;
        while (cursor.moveToNext()) {
            last_sync = cursor.getString(cursor.getColumnIndex("LAST_SYNC"));
        }
        cursor.close();
        return last_sync;
    }

    public String getLAST_SERVER_STATUS2() {
        Cursor cursor = this.db.rawQuery("Select LAST_SERVER_DATE from SETTINGS", null);
        String last_sync = null;
        while (cursor.moveToNext()) {
            last_sync = cursor.getString(cursor.getColumnIndex("LAST_SERVER_DATE"));
        }
        cursor.close();
        return last_sync;
    }

    public void update_last_date(String last_update) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("LAST_SYNC", last_update);
        this.db.update("SETTINGS", updatedValues, null, null);
    }

    public void Server_update_last_date(String last_update) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("LAST_SERVER_DATE", last_update);
        this.db.update("SETTINGS", updatedValues, null, null);
    }

    public void createfirstlastdate(String last_update) {
        ContentValues newValues = new ContentValues();
        newValues.put("LAST_SYNC", last_update);
        this.db.insert("SETTINGS", null, newValues);
    }

    public Integer checkifnumberalreadyexist(String number) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = number;
        Cursor cursor = this.db.rawQuery("Select MOBILE_NUMBER from CUSTDETAILS where MOBILE_NUMBER=?", strArr, null);
        Integer MOBILE_NUMBER = Integer.valueOf(0);
        while (cursor.moveToNext()) {
            MOBILE_NUMBER = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("MOBILE_NUMBER")));
        }
        cursor.close();
        return MOBILE_NUMBER;
    }

    public String GETfinalOrder(Integer position) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        Cursor cursor = this.db.rawQuery("Select SHOP_NAME from CUSTDETAILS where SHOP_ID=?", strArr, null);
        String ShopName = null;
        while (cursor.moveToNext()) {
            ShopName = cursor.getString(cursor.getColumnIndex("SHOP_NAME"));
        }
        cursor.close();
        return ShopName;
    }

    public String getItemName(Integer item_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(item_id);
        Cursor cursor = this.db.rawQuery("Select * from ORDERING where ITEM_ID=?", strArr, null);
        String item_name = null;
        while (cursor.moveToNext()) {
            item_name = cursor.getString(cursor.getColumnIndex("ITEM_NAME"));
        }
        cursor.close();
        return item_name;
    }

    public ArrayList GETfinalOrder2(Integer item_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(item_id);
        Cursor cursor = this.db.rawQuery("Select * from ORDERING where ITEM_ID=?", strArr, null);
        ArrayList Al = new ArrayList();
        while (cursor.moveToNext()) {
            Al.add(new DataModel(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ITEM_ID"))), cursor.getString(cursor.getColumnIndex("ITEM_NAME")), cursor.getString(cursor.getColumnIndex("ITEM_CODE")), cursor.getString(cursor.getColumnIndex("QUANTITY")), cursor.getString(cursor.getColumnIndex("RATE")), cursor.getString(cursor.getColumnIndex("MRP"))));
        }
        cursor.close();
        return Al;
    }

    public int get_the_already_enterd_item(Integer item_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(item_id);
        Cursor cursor = this.db.rawQuery("Select * from ORDERING where ITEM_ID=?", strArr, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("ITEM_ID"));
        }
        cursor.close();
        return id;
    }

    public ArrayList getitem_id(Integer order_no) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(order_no);
        Cursor cursor = this.db.rawQuery("Select * from ORDERED_ITEMS where ORDER_NO=?", strArr, null);
        ArrayList AO = new ArrayList();
        while (cursor.moveToNext()) {
            AO.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PRODUCT_ID"))));
        }
        cursor.close();
        return AO;
    }

    public ArrayList getqty(Integer order_no) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(order_no);
        Cursor cursor = this.db.rawQuery("Select * from ORDERED_ITEMS where ORDER_NO=?", strArr, null);
        ArrayList AO = new ArrayList();
        while (cursor.moveToNext()) {
            AO.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("QTY"))));
        }
        cursor.close();
        return AO;
    }

    public ArrayList getAllOrderDetails2() {
        Cursor cursor = this.db.rawQuery("Select * from ORDERED_ITEMS", null, null);
        ArrayList Order2_Detailsagain = new ArrayList();
        while (cursor.moveToNext()) {
            Order2_Detailsagain.add(cursor.getString(cursor.getColumnIndex("CUSTOMER_ID")));
            Order2_Detailsagain.add(cursor.getString(cursor.getColumnIndex("ORDER_DATE")));
            Order2_Detailsagain.add(cursor.getString(cursor.getColumnIndex("ORDER_NO")));
            Order2_Detailsagain.add(cursor.getString(cursor.getColumnIndex("PRODUCT_ID")));
            Order2_Detailsagain.add(cursor.getString(cursor.getColumnIndex("QTY")));
        }
        return Order2_Detailsagain;
    }

    public ArrayList getnullcusid() {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = null;
        Cursor cursor = this.db.rawQuery("Select SHOP_ID from CUSTDETAILS where SHOP_ID=?", strArr, null);
        ArrayList getnullids = new ArrayList();
        while (cursor.moveToNext()) {
            getnullids.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("SHOP_ID"))));
        }
        cursor.close();
        return getnullids;
    }

    public Integer getalreadtentercusid(String cus_id) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = cus_id;
        Cursor cursor = this.db.rawQuery("Select * from CUSTDETAILS where SHOP_ID=? ", strArr, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("SHOP_ID"));
        }
        cursor.close();
        return Integer.valueOf(id);
    }

    public int getnullphoneno(String mobile) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = mobile;
        Cursor cursor = this.db.rawQuery("Select * from CUSTDETAILS where MOBILE_NUMBER=?", strArr, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("ID"));
        }
        cursor.close();
        return id;
    }

    public List getorderdetaislafterdate(String last_date) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = last_date;
        Cursor cursor = this.db.rawQuery("Select * from ORDERED_ITEMS where datetime(LAST_SYNC)>?", strArr, null);
        List Order_Details_afterdate = new ArrayList();
        while (cursor.moveToNext()) {
            Order_Details_afterdate.add(new DataModel2(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("CUSTOMER_ID"))), cursor.getString(cursor.getColumnIndex("ORDER_DATE")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ORDER_NO"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("PRODUCT_ID"))), cursor.getString(cursor.getColumnIndex("NARRATION")), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("QTY"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ID"))), Integer.valueOf(cursor.getInt(cursor.getColumnIndex("ORDERED_USER")))));
        }
        cursor.close();
        return Order_Details_afterdate;
    }

    public boolean delete(Integer position) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        this.db.delete("ORDERED_ITEMS", "ID = ?", strArr);
        return true;
    }

    public boolean deletecustomer(Integer position) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        this.db.delete("CUSTDETAILS", "ID = ?", strArr);
        return true;
    }

    public boolean deleteEnteredCustomer(Integer position) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        this.db.delete("CUSTDETAILS", "SHOP_ID = ?", strArr);
        return true;
    }

    public String sameorderno(Integer position) {
        String[] strArr = new String[NAME_COLUMN];
        strArr[0] = String.valueOf(position);
        Cursor cursor = this.db.rawQuery("Select MAX(LAST_SYNC) from ORDERED_ITEMS where ORDER_NO=?", strArr, null);
        String date = null;
        while (cursor.moveToNext()) {
            date = cursor.getString(0);
        }
        cursor.close();
        return date;
    }

    public int getMaxId() {
        Cursor cursor = this.db.rawQuery("Select MAX(SHOP_ID) from CUSTDETAILS", null, null);
        int id = 0;
        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
}
