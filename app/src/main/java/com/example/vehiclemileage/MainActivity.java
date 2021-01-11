package com.example.vehiclemileage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText lastReserve,currentReserve,price,fuel,date;
    TextView mileageInr,mileageKm;
    Button calculate,viewData,delete,save;
    float getMileageLtr=0.0f;
    float getMileageInr=0.f;

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
        calculate=findViewById(R.id.calculate);
        viewData=findViewById(R.id.viewData);
        delete=findViewById(R.id.delete);
        save=findViewById(R.id.save);

        DbHelper db=new DbHelper(MainActivity.this);

        String currentDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        date.setText(currentDate);

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

                Boolean checkDataInsert=db.inserUserData(sLastReserve,sCurrentReserve,sPrice,sFuel,sDate,sMileageKm,sMileageInr);

                if(checkDataInsert=true)
                {
                    Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
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



                Cursor res2=db.viewData();
                if(res2.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "there is no entry", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res2.moveToNext())
                {
                    buffer.append("Date : "+res2.getString(4)+"\n");
                    buffer.append(res2.getString(0)+" - "+res2.getString(1)+"\n");
                    buffer.append("You Spend "+res2.getString(2)+" inr for "+res2.getString(3)+"\n");
                    buffer.append(res2.getString(5)+" km/ltr || "+res2.getString(6)+" inr/km"+"\n\n");

                }

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setIcon(R.drawable.ic_launcher_foreground);
                builder.setTitle("User Vehicle Mileage");
                builder.setPositiveButton("Okey Bro !",null);
                builder.setMessage(buffer.toString());
                builder.show();


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
                        getMileageLtr=Float.parseFloat(sLastReserve)/Float.parseFloat(sFuel);
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        mileageInr.setText(df.format(getMileageLtr)+" km/ltr");

                        getMileageInr=Float.parseFloat(sCurrentReserve)-Float.parseFloat(sLastReserve);
                        getMileageInr=Float.parseFloat(sPrice)/getMileageInr;
                        DecimalFormat df2=new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        mileageKm.setText(df2.format(getMileageInr)+" inr/km");
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
}