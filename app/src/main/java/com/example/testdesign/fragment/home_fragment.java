package com.example.testdesign.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdesign.utilities.ApiClient;
import com.example.testdesign.utilities.ApiInterface;
import com.example.testdesign.Adapter.ItemAdapter;
import com.example.testdesign.Adapter.ItemAdapter2;
import com.example.testdesign.Mcart;
import com.example.testdesign.utilities.MyPreferences;
import com.example.testdesign.MyWishlist;
import com.example.testdesign.Mycart;
import com.example.testdesign.ProductData;
import com.example.testdesign.models.Products;
import com.example.testdesign.R;
import com.example.testdesign.Activity.SearchActivity;
import com.example.testdesign.Adapter.SliderAdapter;
import com.example.testdesign.models.SliderData;
import com.example.testdesign.models.Sliders;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class home_fragment extends Fragment {
    MyWishlist myWishlist;
    Mcart mcart;
    FragmentManager fragmentManager;

    RecyclerView recyclerView, recyclerView2;
    List<ProductData> productDataList;
    List<SliderData> sliderDataList;
    ViewPager2 viewPager2;
    SliderAdapter sliderAdapter;
    private MyPreferences myPreferences;

    private static  String pId, uId;

    TextView myCart,searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);


        myWishlist=new MyWishlist();
        mcart=new Mcart();
        myFragment(myWishlist,"abc");
        myPreferences = new MyPreferences(getActivity());
        uId = myPreferences.readUserId();
        String uname = myPreferences.readUname();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        viewPager2 = view.findViewById(R.id.viewPager2);

        getProducts();
        getSlideShow();

        myCart=view.findViewById(R.id.cart);
        myCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), Mycart.class);
                startActivity(i);
            }
        });

        searchView=view.findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getContext(), SearchActivity.class);
                startActivity(i1);
            }
        });

        return view;
    }

    public void myFragment(Fragment fragment, String abc)
    {
        if (fragment!=null)
        {
            fragmentManager=getChildFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.).commit();
        }
    }

    private void getProducts() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Products> call = apiInterface.getProducts();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    productDataList = response.body().getData();
                    if (response.body().getResponse().equals("ok")) {
                        Log.i(TAG, "onResponseData: " + productDataList);
                        ItemAdapter itemAdapter = new ItemAdapter(productDataList, getActivity());
                        recyclerView.setAdapter(itemAdapter);
                        itemAdapter.notifyDataSetChanged();
                        ItemAdapter2 itemAdapter2 = new ItemAdapter2(productDataList, getActivity());
                        recyclerView2.setAdapter(itemAdapter2);
                        itemAdapter2.notifyDataSetChanged();
                    } else
                        {
                           Toast.makeText(getActivity(), "Empty data", Toast.LENGTH_SHORT).show();
                        }
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSlideShow() {
        ApiInterface apiInterface3 = ApiClient.getClient().create(ApiInterface.class);
        Call<Sliders> call = apiInterface3.getSlideShow();
        call.enqueue(new Callback<Sliders>() {
            @Override
            public void onResponse(Call<Sliders> call, Response<Sliders> response) {
                if (response.isSuccessful()) {
                    sliderDataList = response.body().getData();
                    if (response.body().getResponse().equals("ok")) {
                        Log.i(TAG, "onResponseSlider: " + sliderDataList);
                        sliderAdapter = new SliderAdapter(sliderDataList, getActivity());
                        viewPager2.setAdapter(sliderAdapter);
                        sliderAdapter.notifyDataSetChanged();
                    } else
                        {
                        Toast.makeText(getActivity(), "Empty data", Toast.LENGTH_SHORT).show();
                        }
                }
            }

            @Override
            public void onFailure(Call<Sliders> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}