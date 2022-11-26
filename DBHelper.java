package com.example.problem_submit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(username TEXT primary key,password TEXT)");
        MyDB.execSQL("create table data(location TEXT,problem TEXT,date TEXT,time TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");
        MyDB.execSQL("drop table if exists data");



    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();

        contentValues.put("username",username);
        contentValues.put("password",password);



        long result = MyDB.insert("users",null,contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =  MyDB.rawQuery("select * from users where username = ?",new String[]{username});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =  MyDB.rawQuery("select * from users where username =? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean insertusersData(String location,String date, String time){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1 =  new ContentValues();

        contentValues1.put("location",location);
        contentValues1.put("date",date);
        contentValues1.put("time",time);




        long results = MyDB.insert("data",null,contentValues1);

        if(results==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateusersData(String location,String problem,String date, String time){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();

        contentValues.put("location",location);
        contentValues.put("problem",location);
        contentValues.put("date",date);
        contentValues.put("time",time);

        Cursor cursor = MyDB.rawQuery("select * from data  where location = ?",new String[]{location});
        if(cursor.getCount()>0){
            long result = MyDB.update("data",contentValues,"location=?",new String[]{location});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }


    public Cursor getdata(){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from data",null);
        return cursor;

    }





}


