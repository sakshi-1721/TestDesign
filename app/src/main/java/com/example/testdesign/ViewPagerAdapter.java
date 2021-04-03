package com.example.testdesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

//public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
//    int[] images;
//    public ViewPagerAdapter(int[] images, FragmentActivity activity)
//    {
//        this.images=images;
//    }
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imageView.setBackgroundResource(images[position]);
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return images.length;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView,imageView2;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView=itemView.findViewById(R.id.imageView);
//            imageView2=itemView.findViewById(R.id.imageView2);
//        }
//    }
//}
