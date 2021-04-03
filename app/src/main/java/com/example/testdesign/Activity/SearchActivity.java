package com.example.testdesign.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.testdesign.utilities.ApiClient;
import com.example.testdesign.utilities.ApiInterface;
import com.example.testdesign.Adapter.ItemAdapter4;
import com.example.testdesign.ProductData;
import com.example.testdesign.R;
import com.example.testdesign.models.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    List<ProductData> productDataList;
    RecyclerView recyclerView;
    ItemAdapter4 itemAdapter;
    List<String> productNameStrList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        getProducts();
        searchView=findViewById(R.id.searchView2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                itemAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private void getProducts()
    {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Products> call=apiInterface.getProducts();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful())
                {
                    productDataList = response.body().getData();
                    if (response.body().getResponse().equals("ok"))
                    {
                        getProductName(productDataList);
                        Log.i(TAG, "onResponseDataname: " + getProductName(productDataList));
                        Log.i(TAG, "onResponseData: " + productDataList);
                        itemAdapter=new ItemAdapter4(productDataList,getApplicationContext());
                        recyclerView.setAdapter(itemAdapter);
                        itemAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        Toast.makeText(SearchActivity.this, "empty data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "server error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private List<String> getProductName(List<ProductData> productDataList) {

        for (ProductData productData : productDataList){
            String productName = productData.name;
            productNameStrList.add(productName);

        }
        return productNameStrList;
    }
}