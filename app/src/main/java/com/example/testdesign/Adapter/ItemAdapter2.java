package com.example.testdesign.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdesign.ProductData;
import com.example.testdesign.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter2 extends RecyclerView.Adapter<ItemAdapter2.ViewHolder> {
    List<ProductData> productsList;
    Context context;

    public ItemAdapter2(List<ProductData> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_item2,parent,false);
        return new ItemAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id=String.valueOf(productsList.get(position).getId());
        holder.t1.setText(id);
        String name=productsList.get(position).getName();
        holder.t2.setText(name);
        String thumbnail=productsList.get(position).getThumbnail();
        Picasso.get().load(thumbnail).into(holder.i1);

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        ImageView i1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
            i1=itemView.findViewById(R.id.iv);
        }
    }
}
