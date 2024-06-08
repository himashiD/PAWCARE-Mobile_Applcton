package com.example.petcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database name
    private static final String DATABASE_NAME = "Petcare_db";
    //Table Table name
    private static final String TABLE_NAME = "pet";
    //Table Columns
    private static final String ID = "id";
    private static final String petcategory = "petcategory";
    private static final String petname = "petname";
    private static final String petage = "petage";
    private static final String petbread = "petbread";
    private static final String petgender = "petgender";
    private static final String petcolour = "petcolour";
    private static final String petnote = "petnote";
    private static final String created_at = "created_at";


    //caregiver tavle

    private static final String TABLE_NAME_CARE = "caregiverprofile";
    //Table Columns
    private static final String CID = "cid";
    private static final String careid = "careid";
    private static final String carename = "carename";
    private static final String carecontact = "carecontact";
    private static final String carelocation = "carelocation";
    private static final String carepayment = "carepayment";
    private static final String careexperience = "careexperience";
    private static final String created_at_care = "createdst_at_care";

    //Appointment Table name
    private static final String TABLE_NAME2 = "appointment";
    //Table Columns
    private static final String AID = "aid";
    private static final String appcare = "appcare";
    private static final String apppetown = "apppetown";
    private static final String apppet = "apppet";
    private static final String apppetnote = "apppetnote";
    private static final String appduration = "appduration";
    private static final String appconf = "appconf";
    private static final String created_ata = "created_ata";


    //Feedback
    private static final String TABLE_NAMEFEED = "feedback";
    //Table Columns
    private static final String FID = "fid";
    private static final String feedusername = "feedusername";
    private static final String feednotes = "feednotes";
    private static final String feedstatus = "feedstatus";
    private static final String created_atf = "created_atf";


    //creating table query

    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_query = "CREATE TABLE if not EXISTS " + TABLE_NAME +
                "(" +
                ID + " INTEGER PRIMARY KEY ," +
                petcategory + " TEXT ," +
                petname + " TEXT ," +
                petage + " TEXT ," +
                petbread + " TEXT ," +
                petgender + " TEXT ," +
                petcolour + " TEXT ," +
                petnote + " TEXT ," +
                created_at + " TEXT " +
                ")";

        db.execSQL(table_query);

        String caregiver_table_query = "CREATE TABLE if not EXISTS " + TABLE_NAME_CARE +
                "(" +
                CID + " INTEGER PRIMARY KEY ," +
                careid + " TEXT ," +
                carename + " TEXT ," +
                carecontact + " TEXT ," +
                carelocation + " TEXT ," +
                carepayment + " TEXT ," +
                careexperience + " TEXT ," +
                created_at_care + " TEXT " +
                ")";

        db.execSQL(caregiver_table_query);

        String apptable_query = "CREATE TABLE if not EXISTS " + TABLE_NAME2 +
                "(" +
                AID + " INTEGER PRIMARY KEY ," +
                appcare + " TEXT ," +
                apppetown + " TEXT ," +
                apppet + " TEXT ," +
                apppetnote + " TEXT ," +
                appduration + " TEXT ," +
                appconf + " TEXT ," +
                created_ata + " TEXT " +
                ")";

        db.execSQL(apptable_query);

        String feedtable_query = "CREATE TABLE if not EXISTS " + TABLE_NAMEFEED +
                "(" +
                FID + " INTEGER PRIMARY KEY ," +
                feedusername + " TEXT ," +
                feednotes + " TEXT ," +
                feedstatus + " TEXT ," +
                created_atf + " TEXT " +
                ")";

        db.execSQL(feedtable_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CARE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEFEED);
    }

    public void addpet(PetRegisterClass registerpetClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(petcategory, registerpetClass.getPetcategory());
        contentValues.put(petname, registerpetClass.getPetname());
        contentValues.put(petage, registerpetClass.getPetage());
        contentValues.put(petbread, registerpetClass.getPetbread());
        contentValues.put(petgender, registerpetClass.getPetgender());
        contentValues.put(petcolour, registerpetClass.getPetcolour());
        contentValues.put(petnote, registerpetClass.getPetnote());
        contentValues.put(created_at, registerpetClass.getCreated_at());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public PetRegisterClass getpet(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, petcategory, petname, petage, petbread, petgender, petcolour, petnote, created_at}, ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        PetRegisterClass registerpetClass = new PetRegisterClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        db.close();
        return registerpetClass;
    }

    public List<PetRegisterClass> getAllpet() {
        List<PetRegisterClass> registerList = new ArrayList<>();
        String query = "SELECT * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                PetRegisterClass registerpetClass = new PetRegisterClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
                registerList.add(registerpetClass);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return registerList;
    }

    public int updatepet(PetRegisterClass registerpetClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(petcategory, registerpetClass.getPetcategory());
        contentValues.put(petname, registerpetClass.getPetname());
        contentValues.put(petage, registerpetClass.getPetage());
        contentValues.put(petbread, registerpetClass.getPetage());
        contentValues.put(petgender, registerpetClass.getPetgender());
        contentValues.put(petcolour, registerpetClass.getPetcolour());
        contentValues.put(petnote, registerpetClass.getPetnote());
        return db.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(registerpetClass.getId())});
    }

    public void deletepet(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=?", new String[]{id});
        db.close();
    }

    public int getTotal() {
        String query = "SELECT * from " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor.getCount();
    }


    // Caregiver Job
    public void addcaregiverjob(CaregiverMakeJobClass caregiverMakeJobClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(careid, caregiverMakeJobClass.getCareid());
        contentValues.put(carename, caregiverMakeJobClass.getCarename());
        contentValues.put(carecontact, caregiverMakeJobClass.getCarecontact());
        contentValues.put(carelocation, caregiverMakeJobClass.getCarelocation());
        contentValues.put(carepayment, caregiverMakeJobClass.getCarepayment());
        contentValues.put(careexperience, caregiverMakeJobClass.getCareexperience());
        contentValues.put(created_at_care, caregiverMakeJobClass.getCreated_at_care());
        db.insert(TABLE_NAME_CARE, null, contentValues);
        db.close();
    }

    public CaregiverMakeJobClass getcaregiverjob(int cid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_CARE, new String[]{CID, careid, carename, carecontact, carelocation, carepayment, careexperience, created_at_care}, CID + " = ?", new String[]{String.valueOf(cid)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        CaregiverMakeJobClass caregiverMakeJobClass = new CaregiverMakeJobClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
        db.close();
        return caregiverMakeJobClass;
    }

    public List<CaregiverMakeJobClass> getAllcaregiverjob() {
        List<CaregiverMakeJobClass> caregiverjobList = new ArrayList<>();
        String query = "SELECT * from " + TABLE_NAME_CARE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                CaregiverMakeJobClass caregiverMakeJobClass = new CaregiverMakeJobClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
                caregiverjobList.add(caregiverMakeJobClass);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return caregiverjobList;
    }

    public int updatecaregiverjob(CaregiverMakeJobClass caregiverMakeJobClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(careid, caregiverMakeJobClass.getCareid());
        contentValues.put(carename, caregiverMakeJobClass.getCarename());
        contentValues.put(carecontact, caregiverMakeJobClass.getCarecontact());
        contentValues.put(carelocation, caregiverMakeJobClass.getCarelocation());
        contentValues.put(carepayment, caregiverMakeJobClass.getCarepayment());
        contentValues.put(careexperience, caregiverMakeJobClass.getCareexperience());
        return db.update(TABLE_NAME_CARE, contentValues, CID + "=?", new String[]{String.valueOf(caregiverMakeJobClass.getCid())});
    }

    public void deletecaregiverjob(String cid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_CARE, CID + "=?", new String[]{cid});
        db.close();
    }

    public int getTotalcaregiverjob() {
        String query = "SELECT * from " + TABLE_NAME_CARE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor.getCount();
    }

    //Appointment
    public void addapp(AppointmentClass appointmentClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(appcare, appointmentClass.getAppcare());
        contentValues.put(apppetown, appointmentClass.getApppetown());
        contentValues.put(apppet, appointmentClass.getApppet());
        contentValues.put(apppetnote, appointmentClass.getApppetnote());
        contentValues.put(appduration, appointmentClass.getAppduration());
        contentValues.put(appconf, appointmentClass.getAppconf());
        contentValues.put(created_ata, appointmentClass.getCreated_ata());
        db.insert(TABLE_NAME2, null, contentValues);
        db.close();
    }

    public AppointmentClass getapp(int aid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME2, new String[]{AID, appcare, apppetown, apppet, apppetnote, appduration, appconf, created_ata}, AID + " = ?", new String[]{String.valueOf(aid)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        AppointmentClass appointmentClass = new AppointmentClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
        db.close();
        return appointmentClass;
    }

    public List<AppointmentClass> getAllapp() {
        List<AppointmentClass> appointmentList = new ArrayList<>();
        String query = "SELECT * from " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                AppointmentClass appointmentClass = new AppointmentClass(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
                appointmentList.add(appointmentClass);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return appointmentList;
    }

    public int updateapp(AppointmentClass appointmentClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(appcare, appointmentClass.getAppcare());
        contentValues.put(apppetown, appointmentClass.getApppetown());
        contentValues.put(apppet, appointmentClass.getApppet());
        contentValues.put(apppetnote, appointmentClass.getApppetnote());
        contentValues.put(appduration, appointmentClass.getAppduration());
        contentValues.put(appconf, appointmentClass.getAppconf());
        return db.update(TABLE_NAME2, contentValues, AID + "=?", new String[]{String.valueOf(appointmentClass.getAid())});
    }

    public void deleteapp(String aid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, AID + "=?", new String[]{aid});
        db.close();
    }

    public int getTotala() {
        String query = "SELECT * from " + TABLE_NAME2;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor.getCount();
    }


    //feedback

    public void addfeedback (FeedbackClass feedbackClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(feedusername,feedbackClass.getFeedusername());
        contentValues.put(feednotes,feedbackClass.getFeednotes());
        contentValues.put(feedstatus,feedbackClass.getFeedstatus());
        contentValues.put(created_atf,feedbackClass.getCreated_atf());
        db.insert(TABLE_NAMEFEED, null,contentValues);
        db.close();
    }

    public FeedbackClass getfeedback (int fid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAMEFEED,new String[]{FID,feedusername,feednotes,feedstatus,created_atf},FID+" = ?",new String[]{String.valueOf(fid)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        FeedbackClass feedbackClass=new FeedbackClass(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        db.close();
        return feedbackClass;
    }

    public List<FeedbackClass> getAllfeedback(){
        List<FeedbackClass> feedbackClassList=new ArrayList<>();
        String query="SELECT * from "+TABLE_NAMEFEED;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                FeedbackClass feedbackClass=new FeedbackClass(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                feedbackClassList.add(feedbackClass);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return feedbackClassList;
    }
    public int updatefeedback (FeedbackClass feedbackClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(feedusername, feedbackClass.getFeedusername());
        contentValues.put(feednotes, feedbackClass.getFeednotes());
        contentValues.put(feedstatus, feedbackClass.getFeedstatus());
        return db.update(TABLE_NAMEFEED, contentValues, FID+ "=?",new String[]{String.valueOf(feedbackClass.getFid())});
    }

    public void deletefeedback (String fid){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAMEFEED,FID+"=?",new String[]{fid});
        db.close();
    }

    public int getTotalfeedback (){
        String query="SELECT * from "+TABLE_NAMEFEED;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }

}