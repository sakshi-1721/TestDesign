package com.example.testdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.testdesign.Activity.Login;
import com.example.testdesign.fragment.MyCartFragment;
import com.example.testdesign.fragment.MyOrdersFragment;
import com.example.testdesign.fragment.MyWishListFragment;
import com.example.testdesign.fragment.account_fragment;
import com.example.testdesign.fragment.home_fragment;
import com.example.testdesign.fragment.order_fragment;
import com.example.testdesign.fragment.product_fragment;
import com.example.testdesign.utilities.MyPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG ="abc" ;
    BottomNavigationView bottomNavigationView;
    home_fragment hf;
    product_fragment pf;
    order_fragment of;
    account_fragment af;
    FragmentManager fm;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        myPreferences = new MyPreferences(this);

//        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomePage.this, "Menu clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        hf = new home_fragment();
        pf = new product_fragment();
        of = new order_fragment();
        af = new account_fragment();
        myFragment(hf, "def");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        myFragment(hf, "24dy");
                        break;
                    case R.id.item2:
                        myFragment(pf, "fgj");
                        break;
                    case R.id.item3:
                        myFragment(of, "abc");
                        break;
                    case R.id.item4:
                        myFragment(af, "cde");
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void myFragment(Fragment fragment, String abc)
    {
        if (fragment != null)
        {
            fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment_container, fragment, abc).commit();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.allCategories:
                Toast.makeText(this, "All Categories", Toast.LENGTH_SHORT).show();
                break;
            case R.id.offerZone:
                Toast.makeText(this, "Offer Zone", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myOrder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyOrdersFragment()).commit();
                break;
            case R.id.myCoupons:
                Toast.makeText(this, "myCoupons", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myCart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyCartFragment()).commit();
                break;
            case R.id.myWishList:
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyWishListFragment()).commit();
                 break;
            case R.id.myAccount:
                 Toast.makeText(this, "myAccount", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.myNotification:
                 Toast.makeText(this, "myNotification", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.myChat:
                 Toast.makeText(this, "myChat", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.logout:
                myPreferences.writeLoginStatus(false);
                myPreferences.writeUserId("");
                myPreferences.writeUsername("");

                Intent i = new Intent(getApplicationContext(), Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}