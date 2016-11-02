package com.domain.longevity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.domain.longevity.DatabaseConstants.DATABASE_NAME;
import static com.domain.longevity.DatabaseConstants.USER_TABLE;
import static com.domain.longevity.DatabaseConstants.QUESTION_TABLE;
import static com.domain.longevity.DatabaseConstants.INFO_TABLE;
import static com.domain.longevity.DatabaseConstants.ID;



public class DatabaseHelper extends SQLiteOpenHelper {
    String userID = null;
    String [] questionIDList = {
            DatabaseConstants.Key_1, DatabaseConstants.Key_2, DatabaseConstants.Key_3,
            DatabaseConstants.Key_4, DatabaseConstants.Key_5, DatabaseConstants.Key_6};

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean USERTableExists = isTableExists(db, USER_TABLE);
        if(!USERTableExists){
            String userQuery = "CREATE TABLE " + USER_TABLE + "(" +
                    ID + " VARCHAR(24) PRIMARY KEY, " +
                    DatabaseConstants.USERNAME + " VARCHAR(24), " +
                    DatabaseConstants.PHONE + " VARCHAR(10), " +
                    DatabaseConstants.EMAIL + " VARCHAR(24), " +
                    DatabaseConstants.PASSWORD + " VARCHAR(24));";
            createTable(db, userQuery);
        }

        boolean QUESTIONTableExists = isTableExists(db, QUESTION_TABLE);
        if(!QUESTIONTableExists){
            String questionQuery = "CREATE TABLE " + QUESTION_TABLE + "(" +
                    ID + " VARCHAR(24) PRIMARY KEY, " +
                    DatabaseConstants.QUESTION + " VARCHAR(48));";
            createTable(db, questionQuery);

            populateQuestion(db, questionIDList[0], DatabaseConstants.Question_1);
            populateQuestion(db, questionIDList[1], DatabaseConstants.Question_2);
            populateQuestion(db, questionIDList[2], DatabaseConstants.Question_3);
            populateQuestion(db, questionIDList[3], DatabaseConstants.Question_4);
            populateQuestion(db, questionIDList[4], DatabaseConstants.Question_5);
            populateQuestion(db, questionIDList[5], DatabaseConstants.Question_6);
        }

        boolean INFOTableExists = isTableExists(db, INFO_TABLE);
        if(!INFOTableExists){
            String infoQuery = "CREATE TABLE " + INFO_TABLE + "(" +
                    ID + " VARCHAR(24) PRIMARY KEY, " +
                    DatabaseConstants.USER_ID + " VARCHAR(24), " +
                    DatabaseConstants.QUESTION_ID + " VARCHAR(24), " +
                    DatabaseConstants.ANSWER + " VARCHAR(48), " +
                    DatabaseConstants.UPDATED_AT + " VARCHAR(8));";
            createTable(db, infoQuery);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        // Returns true if the table exists else false
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND " +
                "name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst()) {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public void createTable(SQLiteDatabase db, String query) {
        // Executes the given query on the given database
        db.execSQL(query);
    }

    public void populateQuestion(SQLiteDatabase db, String key, String question){
        // Adds a row in the QUESTION table with the provided key - value (question) pair
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, key);
        contentValues.put(DatabaseConstants.QUESTION, question);

        db.insert(QUESTION_TABLE, null, contentValues);
    }

    public boolean insertUserData(String id, String username, String phone, String email,
                                  String password) {
        // Adds a row in the USER table with the provided values
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        setUserID(id);
        contentValues.put(ID, id);
        contentValues.put(DatabaseConstants.USERNAME, username);
        contentValues.put(DatabaseConstants.PHONE, phone);
        contentValues.put(DatabaseConstants.EMAIL, email);
        contentValues.put(DatabaseConstants.PASSWORD, password);

        long status = db.insert(USER_TABLE, null, contentValues);
        return status != -1;
    }

    public boolean insertInfoData(String id, String user_id, String question_id, String answer,
                                  String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, id);
        contentValues.put(DatabaseConstants.USER_ID, user_id);
        contentValues.put(DatabaseConstants.QUESTION_ID, question_id);
        contentValues.put(DatabaseConstants.ANSWER, answer);
        contentValues.put(DatabaseConstants.UPDATED_AT, date);

        long insert_status = db.insert(INFO_TABLE, null, contentValues);
        return insert_status != -1;
    }

    public String getUserID(){
        return this.userID;
    }

    public void setUserID(String id){
        this.userID = id;
    }

    public String[] getQuestionIDs(){
        return this.questionIDList;
    }
}
