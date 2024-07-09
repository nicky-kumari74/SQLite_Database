package com.example.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="contactDB";
    private static final int VERSION=1;
    private static final String TABLE_NAME="register";
    private static final String KEY_ID="id";
    private static final String UNAME="Name";
    private static final String EMAIL="Email";
    private static final String PASSWORD="Password";
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+UNAME+" TEXT,"+EMAIL+" TEXT,"+PASSWORD+" TEXT"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void addUser(String uname,String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(UNAME,uname);
        values.put(EMAIL,email);
        values.put(PASSWORD,password);
        db.insert(TABLE_NAME,null,values);
    }
    public ArrayList<Model> fetchdata(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<Model> arr=new ArrayList<>();
        while(cursor.moveToNext()){
            Model model=new Model();
            model.id=cursor.getInt(0);
            model.uname=cursor.getString(1);
            model.email=cursor.getString(2);
            model.password=cursor.getString(3);
            arr.add(model);
        }
        return arr;
    }
    public void updatetable(Model model){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(PASSWORD,model.password);
        db.update(TABLE_NAME,cv,KEY_ID+" = "+model.id,null);

    }
    public void deletedata(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"="+id,null);
    }
}

