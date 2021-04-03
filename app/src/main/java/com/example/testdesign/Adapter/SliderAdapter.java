package com.example.testdesign.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testdesign.R;
import com.example.testdesign.models.SliderData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private static final String TAG = SliderAdapter.class.getSimpleName();
    List<SliderData> sliderDataList;
    Context context;
    public SliderAdapter(List<SliderData> sliderDataList,Context context)
    {
        this.sliderDataList=sliderDataList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id=String.valueOf(sliderDataList.get(position).getId());
        String photoStr = sliderDataList.get(position).getPhoto();

        Log.i(TAG, "onBindViewHolderphoto: "+photoStr);
        Picasso.get().load(photoStr).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sliderDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
