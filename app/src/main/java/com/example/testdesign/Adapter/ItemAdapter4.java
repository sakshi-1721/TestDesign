package com.example.testdesign.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdesign.ProductData;
import com.example.testdesign.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemAdapter4 extends RecyclerView.Adapter<ItemAdapter4.ViewHolder> implements Filterable {
    List<ProductData> productDataList;
    Context context;
    List<String> productNameStrList;
    List<String> allList;

    public ItemAdapter4(List<ProductData> productDataList, Context context) {
        this.productDataList = productDataList;
        this.context = context;
//        this.allList=new ArrayList<>(productNameStrList);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
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

    }

    @Override
    public int getItemCount()
    {
        return productDataList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList= new ArrayList<>();
            if (constraint.toString().isEmpty())
            {
                filteredList.addAll(allList);
            }
            else
            {
                for (String item:allList)
                {
                    if (item.toLowerCase().contains(constraint.toString().toLowerCase()))
                    {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            productNameStrList.clear();
            productNameStrList.addAll((Collection<? extends String>) results.values);
            notifyDataSetChanged();

        }
    };



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

