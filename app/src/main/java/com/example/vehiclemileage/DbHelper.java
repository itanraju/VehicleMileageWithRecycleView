package com.example.vehiclemileage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "vehicleMileageDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table vehicleMileageDbTable (lastReserve number,currentReserve number,price number,fuel number,date text,mileageKm float,mileageInr float)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists vehicleMileageDbTable");
    }

    public  Boolean inserUserData(String lastReserve,String currentReserve,String price,String fuel,String date,String mileageKm,String mileageInr)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("LastReserve",lastReserve);
        contentValues.put("CurrentReserve",currentReserve);
        contentValues.put("Price",price);
        contentValues.put("Fuel",fuel);
        contentValues.put("Date",date);
        contentValues.put("MileageKm",mileageKm);
        contentValues.put("MileageInr",mileageInr);

        long result=db.insert("vehicleMileageDbTable",null,contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public void delete()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from vehicleMileageDbTable");
    }

    public Cursor viewData()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from vehicleMileageDbTable",null);

        return  cursor;
    }

}
