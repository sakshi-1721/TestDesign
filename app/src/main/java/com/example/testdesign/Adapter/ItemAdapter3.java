package com.example.testdesign.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdesign.Activity.ImageDescription;
import com.example.testdesign.ProductData;
import com.example.testdesign.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter3 extends RecyclerView.Adapter<ItemAdapter3.ViewHolder> {
    List<ProductData> productDataList;
    Context context;

    public ItemAdapter3(List<ProductData> productDataList, Context context) {
        this.productDataList = productDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id=String.valueOf(productDataList.get(position).getId());
        holder.t1.setText(id);
        String name=productDataList.get(position).getName();
        holder.t2.setText(name);
        String thumbnail=productDataList.get(position).getThumbnail();
        Picasso.get().load(thumbnail).into(holder.i1);
        String oldPrice=String.valueOf(productDataList.get(position).getOld_price());
        holder.t3.setText(oldPrice);
        String newPrice=String.valueOf(productDataList.get(position).getNew_price());
        holder.t4.setText(newPrice);

        final ProductData temp=productDataList.get(position);

        holder.i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ImageDescription.class);
                i.putExtra("imagename",temp.getThumbnail());
                i.putExtra("header",temp.getName());
                i.putExtra("desc",temp.getId());
                i.putExtra("oldprice",temp.getOld_price());
                i.putExtra("newprice",temp.getNew_price());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount()
    {
        return productDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView i1;
        TextView t1,t2,t3,t4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            i1=itemView.findViewById(R.id.iv);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
            t3=itemView.findViewById(R.id.oldPrice);
            t4=itemView.findViewById(R.id.newPrice);
        }
    }
}
