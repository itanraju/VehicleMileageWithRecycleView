package com.example.vehiclemileage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText lastReserve,currentReserve,price,fuel,date;
    TextView mileageInr,mileageKm,mileageinrLtr;
    Button calculate,viewData,delete,save,reset;
    float getMileageLtr=0.0f;
    float getMileageInr=0.0f;
    float getMileageinrLtr=0.0f;

    String DTmileageKm,DTmileageInr,DTmileageInrLtr;

    DbHelper db;
    Cursor res2;
    StringBuffer buffer;

    List<String> listGroup;
    List<String> listChild;

    ArrayList<DbModel> arrayList;

    HashMap<String, List<String>> bindingList;

    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastReserve=findViewById(R.id.lastReserve);
        currentReserve=findViewById(R.id.currentReserve);
        price=findViewById(R.id.price);
        fuel=findViewById(R.id.fuel);
        date=findViewById(R.id.date);
        mileageInr=findViewById(R.id.mileage_txt);
        mileageKm=findViewById(R.id.mileage_txt_km);
        mileageinrLtr=findViewById(R.id.mileage_txt_inr_ltr);
        calculate=findViewById(R.id.calculate);
        viewData=findViewById(R.id.viewData);
        delete=findViewById(R.id.delete);
        save=findViewById(R.id.save);
        reset=findViewById(R.id.reset);

       /* getGroupList();
        getChildList();*/
        /*collectionList();*/


        db=new DbHelper(MainActivity.this);


        String currentDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        date.setText(currentDate);


        db=new DbHelper(MainActivity.this);




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastReserve.setText(null);
                currentReserve.setText(null);
                price.setText(null);
                fuel.setText(null);
                date.setText(null);
                mileageKm.setText("0 km/ltr");
                mileageInr.setText("0 inr/km");
                mileageinrLtr.setText("0 inr/ltr");
                save.setEnabled(true);

                String currentDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
                date.setText(currentDate);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sLastReserve=lastReserve.getText().toString().trim();
                String sCurrentReserve=currentReserve.getText().toString().trim();
                String sPrice=price.getText().toString().trim();
                String sFuel=fuel.getText().toString().trim();
                String sDate=date.getText().toString().trim();

                String sMileageKm=String.valueOf(getMileageLtr);
                String sMileageInr=String.valueOf(getMileageInr);
                String sMileageInrLtr=String.valueOf(getMileageinrLtr);

                if((!sLastReserve.isEmpty() && !sCurrentReserve.isEmpty() && !sPrice.isEmpty() && !sFuel.isEmpty() && !sDate.isEmpty() && !sMileageKm.isEmpty() && !sMileageInr.isEmpty() && !sMileageInrLtr.isEmpty()))
                {
                    db=new DbHelper(MainActivity.this);
                    Boolean checkDataInsert=db.inserUserData(sLastReserve,sCurrentReserve,sPrice,sFuel,sDate,DTmileageKm,DTmileageInr,DTmileageInrLtr);

                    if(checkDataInsert=true)
                    {
                        Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                        save.setEnabled(false);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "First of all you have to calculate the mileage !!", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.delete();
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewdata();

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sLastReserve=lastReserve.getText().toString().trim();
                String sCurrentReserve=currentReserve.getText().toString().trim();
                String sPrice=price.getText().toString().trim();
                String sFuel=fuel.getText().toString().trim();
                String sDate=date.getText().toString().trim();

                if(!sLastReserve.isEmpty() && !sCurrentReserve.isEmpty() && !sPrice.isEmpty() && !sFuel.isEmpty() && !sDate.isEmpty())
                {
                    if(Integer.parseInt(sCurrentReserve)>Integer.parseInt(sLastReserve))
                    {
                        save.setEnabled(true);

                        getMileageLtr=Float.parseFloat(sCurrentReserve)-Float.parseFloat(sLastReserve);
                        getMileageLtr=getMileageLtr/Float.parseFloat(sFuel);
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        mileageInr.setText(df.format(getMileageLtr)+" km/ltr");
                        DTmileageKm=df.format(getMileageLtr);


                        getMileageInr=Float.parseFloat(sCurrentReserve)-Float.parseFloat(sLastReserve);
                        getMileageInr=Float.parseFloat(sPrice)/getMileageInr;
                        DecimalFormat df2=new DecimalFormat();
                        df2.setMaximumFractionDigits(2);
                        mileageKm.setText(df2.format(getMileageInr)+" inr/km");
                        DTmileageInr=df2.format(getMileageInr);

                        getMileageinrLtr=Float.parseFloat(sPrice)/Float.parseFloat(sFuel);
                        DecimalFormat df3=new DecimalFormat();
                        df3.setMaximumFractionDigits(2);
                        mileageinrLtr.setText(df3.format(getMileageinrLtr)+" inr/ltr");
                        DTmileageInrLtr=df3.format(getMileageinrLtr);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Current Reverse is Always Gretter Than Last Revers !", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "please fillup all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

  /*  private void getGroupList()
    {
        listGroup=new ArrayList<>();
        db=new DbHelper(MainActivity.this);
        res2=db.viewDatabyDate();

        while (res2.moveToNext())
        {
           listGroup.add(res2.getString(5));
        }

    }*/
   /* private void getChildList()
    {
        listChild=new ArrayList<String>();
        db=new DbHelper(MainActivity.this);
        Log.d("group",listGroup.toString());

            res2=db.viewDatabyDate();

            while (res2.moveToNext()) {

                listChild.add(res2.getString(1) + " - " + res2.getString(2) + "\n" +
                        "You Spend " + res2.getString(3) + " inr for " + res2.getString(4) + " liters fuel" + "\n"
                        + res2.getString(6) + " km/ltr || " + res2.getString(7) + " inr/km || " + res2.getString(8) + " inr/ltr");
            }

    }*/
   /* private void collectionList()
    {
        bindingList=new HashMap<String, List<String>>();

        Log.d("child",listChild.toString());
        for(int i=0;i<listGroup.size();i++)
        {
            bindingList.put(listGroup.get(i), Collections.singletonList(listChild.get(i)));
        }
        Log.d("coll",bindingList.toString());

    }
*/
    private void viewdata() {

        Intent i=new Intent(MainActivity.this,ViewAllData.class);
        startActivity(i);
       /* db=new DbHelper(MainActivity.this);

        res2=db.viewAllDatabyDate("19-Jan-2021");
        if(res2.getCount()==0)
        {
            Toast.makeText(MainActivity.this, "there is no entry", Toast.LENGTH_SHORT).show();
            return;
        }
        buffer=new StringBuffer();
        DbModel dbModel=new DbModel();
        arrayList=new ArrayList<>();
        while (res2.moveToNext())
        {
            buffer.append("Date : "+res2.getString(5)+"\n");
            buffer.append(res2.getString(1)+" - "+res2.getString(2)+"\n");
            buffer.append("You Spend "+res2.getString(3)+" inr for "+res2.getString(4)+" liters fuel"+"\n");
            buffer.append(res2.getString(6)+" km/ltr || "+res2.getString(7)+" inr/km || "+res2.getString(8)+" inr/ltr"+"\n\n");

            dbModel.setLastReserve(res2.getString(1));
            dbModel.setCurrentReserve(res2.getString(2));
            dbModel.setPrice(res2.getString(3));
            dbModel.setFuel(res2.getString(4));
            dbModel.setDate(res2.getString(5));
            dbModel.setMileageKm(res2.getString(6));
            dbModel.setMileageInr(res2.getString(7));
            dbModel.setMileageInrLtr(res2.getString(8));

            arrayList.add(dbModel);
        }

        for (int i=0;i<arrayList.size();i++)
        {

            Log.d("price",arrayList.get(i).getPrice());
        }*/

      /*  AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setTitle("User Vehicle Mileage");
        builder.setPositiveButton("Okey Bro !",null);
        builder.setMessage(buffer.toString());
        builder.show();*/
    }
}