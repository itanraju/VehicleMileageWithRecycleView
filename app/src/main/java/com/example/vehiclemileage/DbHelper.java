package com.example.vehiclemileage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "vehicleMileageDb", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table vehicleMileageDbTable (Id INTEGER PRIMARY KEY AUTOINCREMENT,lastReserve text,currentReserve text,price number,fuel text,date text,mileageKm text,mileageInr text,mileageInrLtr text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists vehicleMileageDbTable");
    }

    public  Boolean inserUserData(String lastReserve,String currentReserve,String price,String fuel,String date,String mileageKm,String mileageInr,String mileageInrLtr)
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
        contentValues.put("MileageInrLtr",mileageInrLtr);

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

        Cursor cursor=db.rawQuery("select * from vehicleMileageDbTable order by date desc",null);

        return  cursor;
    }

    public Cursor viewAllDatabyDate(String tDate)
    {
        String query="select * from vehicleMileageDbTable where date=?";

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(query,new String[]{tDate});

        return  cursor;
    }

    public  Boolean deleteUserData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from vehicleMileageDbTable where Id=?",new String[]{id});
        if(cursor.getCount()>0)
        {
            long result=db.delete("vehicleMileageDbTable","Id=?",new String[]{id});
            if(result==-1)
            {
                return  false;
            }
            else
            {
                return true;
            }

        }
        else {
            return false;
        }}




    public ArrayList<DbModel> getAllData(String date)
    {
        String query="select * from vehicleMileageDbTable where date=?";
        ArrayList<DbModel> dbList=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(query,new String[]{date});

        DbModel obj=new DbModel();

        if(cursor.moveToFirst())
        {
            do{
                obj.setLastReserve(cursor.getString(cursor.getColumnIndex("lastReserve")));
                obj.setCurrentReserve(cursor.getString(cursor.getColumnIndex("currentReserve")));
                obj.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                obj.setFuel(cursor.getString(cursor.getColumnIndex("fuel")));
                obj.setDate(cursor.getString(cursor.getColumnIndex("date")));
                obj.setMileageKm(cursor.getString(cursor.getColumnIndex("mileageKm")));
                obj.setMileageInr(cursor.getString(cursor.getColumnIndex("mileageInr")));
                obj.setMileageInrLtr(cursor.getString(cursor.getColumnIndex("mileageInrLtr")));

                dbList.add(obj);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return dbList;
    }

}
