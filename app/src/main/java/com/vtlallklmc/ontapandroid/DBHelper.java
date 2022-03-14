package com.vtlallklmc.ontapandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "contact.db";
    public static final int DB_VERSION = 1;
    public static String TB_NAME = "tblXe", ID = "id", NAME = "name", PIC = "pic";
    public Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TB_NAME + " ("
        + ID + " INTEGER PRIMARY KEY, "
        + NAME + " TEXT, "
        + PIC + " BLOB);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(sqLiteDatabase);
    }

    public void them(Xe xe){
        ContentValues themValues = new ContentValues();
        themValues.put(NAME,xe.getName());
        themValues.put(PIC,xe.getPic());
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TB_NAME,null,themValues);
        if(result!=-1)
            Toast.makeText(context, "Thêm thành công :>", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Thêm thất bại :<", Toast.LENGTH_SHORT).show();
    }
    public void sua(Xe xe, String name){
        ContentValues suaValues = new ContentValues();
        suaValues.put(NAME,xe.getName());
        suaValues.put(PIC,xe.getPic());
        SQLiteDatabase db = getWritableDatabase();
        long result = db.update(TB_NAME,suaValues,NAME + " LIKE % ?",new String[]{name});
        if(result>0)
            Toast.makeText(context, "Sửa thành công :>", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Sửa thất bại :<", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<Xe> getAllXe(){
        ArrayList<Xe> lstXe = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+TB_NAME,null);
        while(cursor.moveToNext()){
            Xe xe = new Xe(cursor.getBlob(2), cursor.getString(1));
        }
        return lstXe;
    }
}
