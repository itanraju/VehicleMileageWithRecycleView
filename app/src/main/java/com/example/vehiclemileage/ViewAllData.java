package com.example.vehiclemileage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllData extends AppCompatActivity {

    ArrayList<DbModel> dbModelArrayList;

    DbModel dbModel;
    DbHelper dbHelper;

    RecyclerView recyclerView;

    Handler handler = new Handler();
    Runnable refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);

        dbHelper=new DbHelper(ViewAllData.this);
        recyclerView=findViewById(R.id.recycleView);


        getData();
        refresh();


      /*  Cursor res=dbHelper.viewData();
        if(res.getCount()==0)
        {
            Toast.makeText(ViewAllData.this, "there is no entry", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext())
        {
            dbModel.setLastReserve(res.getString(1));
            dbModel.setCurrentReserve(res.getString(2));
            dbModel.setPrice(res.getString(3));
            dbModel.setFuel(res.getString(4));
            dbModel.setDate(res.getString(5));
            dbModel.setMileageKm(res.getString(6));
            dbModel.setMileageInr(res.getString(7));
            dbModel.setMileageInrLtr(res.getString(8));

            dbModelArrayList.add(dbModel);
        }

        DbAdapter dbAdapter=new DbAdapter(ViewAllData.this,dbModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewAllData.this));
        recyclerView.setAdapter(dbAdapter);
        recyclerView.setHasFixedSize(true);
*/

    }

    public void getData() {

        StringBuffer buffer=new StringBuffer();
        dbModelArrayList=new ArrayList<DbModel>();

        dbHelper=new DbHelper(ViewAllData.this);

        Cursor res2;

        res2=dbHelper.viewData();
        /*if(res2.getCount()==0)
        {
            Toast.makeText(ViewAllData.this, "there is no entry", Toast.LENGTH_SHORT).show();
            return;
        }*/


        dbModelArrayList=new ArrayList<>();

        dbModelArrayList.clear();

        while (res2.moveToNext())
        {
            buffer.append("Date : "+res2.getString(5)+"\n");
            buffer.append(res2.getString(1)+" - "+res2.getString(2)+"\n");
            buffer.append("You Spend "+res2.getString(3)+" inr for "+res2.getString(4)+" liters fuel"+"\n");
            buffer.append(res2.getString(6)+" km/ltr || "+res2.getString(7)+" inr/km || "+res2.getString(8)+" inr/ltr"+"\n\n");

            Log.d("ck",res2.getString(6)+" "+res2.getString(7)+" "+res2.getString(8));


            DbModel dbModel=new DbModel();

            dbModel.setId(res2.getString(0));
            dbModel.setLastReserve(res2.getString(1));
            dbModel.setCurrentReserve(res2.getString(2));
            dbModel.setPrice(res2.getString(3));
            dbModel.setFuel(res2.getString(4));
            dbModel.setDate(res2.getString(5));
            dbModel.setMileageKm(res2.getString(6));
            dbModel.setMileageInr(res2.getString(8));
            dbModel.setMileageInrLtr(res2.getString(7));

            dbModelArrayList.add(dbModel);

        }

        DbAdapter dbAdapter=new DbAdapter(ViewAllData.this,dbModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewAllData.this));
        recyclerView.setAdapter(dbAdapter);
        recyclerView.setHasFixedSize(true);
        dbAdapter.notifyDataSetChanged();

    }

    public void refresh()
    {
        refresh = new Runnable() {
            public void run() {
                // Do something
                getData();
                handler.postDelayed(refresh, 100);
            }
        };
        handler.post(refresh);

    }

}
