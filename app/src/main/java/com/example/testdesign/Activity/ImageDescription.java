package com.example.testdesign.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.testdesign.Adapter.ItemAdapter3;
import com.example.testdesign.ProductData;
import com.example.testdesign.R;
import com.example.testdesign.models.AddCart;
import com.example.testdesign.models.AddWishList;
import com.example.testdesign.models.Products;
import com.example.testdesign.utilities.ApiClient;
import com.example.testdesign.utilities.ApiInterface;
import com.example.testdesign.utilities.MyPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ImageDescription extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ProductData> productDataList;
    ImageView iv;
    TextView tv1, tv2, tv3, tv4;
    ToggleButton toggleButton;
    TextView addToCart, buyNow;
    private MyPreferences myPreferences;
    private static String pId, uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description);

        iv = findViewById(R.id.desc_img);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        addToCart = findViewById(R.id.carttext);
        buyNow = findViewById(R.id.buytext);
        toggleButton = findViewById(R.id.toggleButton);

        myPreferences = new MyPreferences(getApplicationContext());
        uId = myPreferences.readUserId();

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddToCart(pId, uId);
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addWishList(pId, uId);
                } else {
                    Toast.makeText(ImageDescription.this, "Removed from WishList", Toast.LENGTH_SHORT).show();
                }

            }
        });


        String thumbnail = getIntent().getStringExtra("imagename");
        Picasso.get().load(thumbnail).into(iv);

        tv1.setText(getIntent().getStringExtra("header"));
        tv2.setText(getIntent().getStringExtra("desc"));

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        getProducts();
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
                        pId = String.valueOf(response.body().getData().get(0).getId());
                        Log.i(TAG, "onResponseData: " + productDataList);
                        ItemAdapter3 itemAdapter3 = new ItemAdapter3(productDataList, getApplicationContext());
                        recyclerView.setAdapter(itemAdapter3);
                        itemAdapter3.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "Empty data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAddToCart(String pId, String uId) {
        Log.i(TAG, "getAddToCart: " + pId + "  " + uId);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddCart> call = apiInterface.getAddCart(pId, uId);
        call.enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("true")) {
                        Toast.makeText(ImageDescription.this, "Item added Successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ImageDescription.this, "This item has already added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {
                Toast.makeText(ImageDescription.this, "Item has not added due to server error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void addWishList(String pId, String uId) {
        Log.i(TAG, "addWishList: " + pId + " " + uId);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddWishList> call = apiInterface.getAddWishList(pId, uId);
        call.enqueue(new Callback<AddWishList>() {
            @Override
            public void onResponse(Call<AddWishList> call, Response<AddWishList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("true")) {
                        Toast.makeText(ImageDescription.this, "Added to WishList", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ImageDescription.this, "Already Added to WishList", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddWishList> call, Throwable t) {
                Toast.makeText(ImageDescription.this, "Server Error...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void myWishList() {

    }

}