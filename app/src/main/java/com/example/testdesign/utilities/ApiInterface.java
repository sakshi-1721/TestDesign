package com.example.testdesign.utilities;

import com.example.testdesign.models.AddCart;
import com.example.testdesign.models.AddWishList;
import com.example.testdesign.models.DeleteCart;
import com.example.testdesign.models.DeleteWishList;
import com.example.testdesign.models.MyCart;
import com.example.testdesign.models.Products;
import com.example.testdesign.models.Register;
import com.example.testdesign.models.SignIn;
import com.example.testdesign.models.Sliders;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

   // @SerializedName("")
    @GET("allproduct")
    Call<Products> getProducts();

    @POST("sliders.php")
    Call<Sliders> getSlideShow();

    @GET("signup")
    Call<Register> getRegister(
            @Query("email_id") String email_id,
            @Query("password") String password,
            @Query("name") String name,
            @Query("contact_number") String contact_number
    );

    @GET("login")
    Call<SignIn> getSignIn(
      @Query("email_id") String email_id,
      @Query("password") String password
    );

    @GET("addcart")
    Call<AddCart> getAddCart(@Query("productid") String productid , @Query("userid") String userid);

    @GET("mycart")
    Call<MyCart> getMyCart(@Query("userid") String userid);

    @GET("deletecart")
    Call<DeleteCart> getDeleteCart(@Query("productid") String productid, @Query("userid") String userid);

    @GET("addwishlist")
    Call<AddWishList> getAddWishList(@Query("productid") String productid, @Query("userid") String userid);

    @GET("deletewishlist")
    Call<DeleteWishList> getDeleteWishList(@Query("productid") String productid, @Query("userid") String userid);
}

