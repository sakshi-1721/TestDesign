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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    List<ProductData> productsList;
    Context context;

    public ItemAdapter(List<ProductData> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {

        String id = String.valueOf(productsList.get(position).getId());
        holder.t1.setText(id);
        String name = productsList.get(position).getName();
        holder.t2.setText(name);
        String thumbnail = productsList.get(position).getThumbnail();
        Picasso.get().load(thumbnail).into(holder.i1);
        String oldPrice=String.valueOf(productsList.get(position).getOld_price());
        holder.t3.setText(oldPrice);
        String newPrice=String.valueOf(productsList.get(position).getNew_price());
        holder.t4.setText(newPrice);


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1, t2,t3,t4;
        ImageView i1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3=itemView.findViewById(R.id.oldPrice);
            t4=itemView.findViewById(R.id.newPrice);
            i1 = itemView.findViewById(R.id.iv);
            //id = String.valueOf(productsList.get(getAdapterPosition()).id);
            //  Log.i(TAG, "ViewHolderk: "+id);


            i1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ImageDescription.class);
                    i.putExtra("id", productsList.get(getAdapterPosition()).getId());
                    i.putExtra("imagename", productsList.get(getAdapterPosition()).getThumbnail());
                    i.putExtra("header", productsList.get(getAdapterPosition()).getName());
                    //i.putExtra("desc", productsList.get(getAdapterPosition()).getId());
                    i.putExtra("oldprice",productsList.get(getAdapterPosition()).getOld_price());
                    i.putExtra("newprice",productsList.get(getAdapterPosition()).getNew_price());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}

