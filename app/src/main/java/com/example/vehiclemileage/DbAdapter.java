package com.example.vehiclemileage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DbAdapter extends RecyclerView.Adapter<DbAdapter.ViewHolder> {

    Context context;
    ArrayList<DbModel> arrayList;

    public DbAdapter(Context context, ArrayList<DbModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.mileage_layout,null,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        DbHelper db=new DbHelper(context);

        Log.d("id",arrayList.get(position).getId());

        holder.lR.setText(arrayList.get(position).getLastReserve()+" - ");
        holder.cR.setText(arrayList.get(position).getCurrentReserve()+"  Distance You Traveled");
        holder.price.setText("You spend "+arrayList.get(position).getPrice());
        holder.fuel.setText(" for "+arrayList.get(position).getFuel()+" liters fuel");
        holder.date.setText(arrayList.get(position).getDate());
        holder.mKm.setText(arrayList.get(position).getMileageKm()+" km/ltr || ");
        holder.mLtr.setText(arrayList.get(position).getMileageInr()+" inr/ltr || ");
        holder.mInr.setText(arrayList.get(position).getMileageInrLtr()+" inr/km");


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setMessage("Do you want to delete this data ?");
                builder.setPositiveButton("obviously", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Boolean checkInsertData=db.deleteUserData(arrayList.get(position).getId());
                        if(checkInsertData==true)
                        {

                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, "Data already deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Na bhai",null);
                builder.show();


            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lR,cR,price,fuel,date,mKm,mInr,mLtr;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lR=itemView.findViewById(R.id.lastReserve_layout);
            cR=itemView.findViewById(R.id.current_reserve);
            price=itemView.findViewById(R.id.money);
            fuel=itemView.findViewById(R.id.ltr_layout);
            date=itemView.findViewById(R.id.date_layout);
            mKm=itemView.findViewById(R.id.kmperltr);
            mInr=itemView.findViewById(R.id.inrperkm);
            mLtr=itemView.findViewById(R.id.inrperltr);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
