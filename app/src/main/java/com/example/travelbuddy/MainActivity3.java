package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

import com.example.travelbuddy.UtilsService.SharedPreferenceClass;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity {
    com.example.travelbuddy.databinding.ActivityMain3Binding binding;

//    FloatingActionButton fabbutton;

    SharedPreferenceClass sharedPreferenceClass; //*

//    private AppBarConfiguration mAppBarConfiguration;

//    @Override
//    public void onBackPressed() {
//
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//
//        if (count == 0) {
//            super.onBackPressed();
//            //additional code
//        } else {
//            getSupportFragmentManager().popBackStack();
//        }
//}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.travelbuddy.databinding.ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home_fragment_User());


//        fabbutton = findViewById(R.id.chatbot2);
//
//        fabbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity3.this,"Welcome to the Bot",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity3.this, Chatbot.class);
//                startActivity(intent);
//            }
//        });

        binding.bottomNavigation.setOnItemSelectedListener(item ->
        {
            Fragment selectedFragment = null;
            switch (item.getItemId())
            {
                case R.id.home:
                    selectedFragment  = new Home_fragment_User();
                    break;
                case R.id.ar:
                    selectedFragment  = new arFragment();
                    break;
                case R.id.offer:
                    selectedFragment  = new offer_frag();
                    break;
//                case R.id.notification:
//                    selectedFragment  = new notifi_frag();
//                    break;
//                case R.id.user:
//                    replaceFragment(new user_frag());
//                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.containerr,selectedFragment,null).addToBackStack(null).commit();

            return true;
        });

        sharedPreferenceClass = new SharedPreferenceClass(this); //*


//        binding.navigationView.setNavigationItemSelectedListener(item ->
//        {
//            switch (item.getItemId())
//            {
//                case R.id.home:
//                    replaceFragment(new Home_fragment_User());
//                    break;
//                case R.id.offer:
//                    replaceFragment(new offer_frag());
//                    break;
//                case R.id.notification:
//                    replaceFragment(new notifi_frag());
//                    break;
////                case R.id.user:
////                    replaceFragment(new user_frag());
////                    break;
//            }
//
//            return true;
//        });

//        binding.navigationView.setOnClickListener(item -> {
//            switch (item.getId()){
//                case R.id.home:
//                    replaceFragment(new Home_fragment_User());
//                    break;
//                case R.id.offer:
//                    replaceFragment(new offer_frag());
//                    break;
//                case R.id.notification:
//                    replaceFragment(new notifi_frag());
//                    break;
////                case R.id.user:
////                    replaceFragment(new user_frag());
////                    break;
//            }
//        });

        //new
//        //tollbar //WORKING
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.home,R.id.offer,R.id.notification)
//                .setDrawerLayout(drawerLayout)
//                .build();

//        NavController navController = Navigation.findNavController(this,R.id.containerr);
//        NavigationUI.setupActionBarWithNavController(this,navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView,navController);
//
//        BottomNavigationView bottom_nav_viw = findViewById(R.id.bottom_navigation);
//        NavigationUI.setupWithNavController( binding.bottomNavigation,navController);


        navigationView.bringToFront();
        toolbar.getBackground().setAlpha(0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        //Secelctioon item ***
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                setDrawerClick(item.getItemId()); //*
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {

                    case R.id.managepro:
                        replaceFragment(new manage_profile());
                        break;
                    case R.id.ar:
                        replaceFragment(new arFragment());
                        break;
                    case R.id.offer:
                        replaceFragment(new offer_frag());
                        break;
//                    case R.id.notification:
//                        replaceFragment(new notifi_frag());
//                        break;
                }
//                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

//    public boolean loadFragment(Fragment fragment)
//    {
//        if(fragment!=null)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.containerr,fragment).commit();
//        }
//        return true;
//    }

//    @Override       // fine working
//    public void onBackPressed()
//    {
////        if(binding.navigationView.getitem==R.id.managepro)
////        {
////            super.onBackPressed();
////            binding.bottomNavigation.setSelectedItemId(R.id.home);
////        }
//        if(binding.bottomNavigation.getSelectedItemId()==R.id.home)
//        {
//            super.onBackPressed();
//            super.onBackPressed();
//            binding.bottomNavigation.setSelectedItemId(R.id.home);
//            finish();//exit
//        }
//        else
//        {
//            binding.bottomNavigation.setSelectedItemId(R.id.home);
//        }
//    }


//    Staic count;
    @Override       // TEST
    public void onBackPressed()
    {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(binding.bottomNavigation.getSelectedItemId()==R.id.home)
        {
            super.onBackPressed();
            super.onBackPressed();
            binding.bottomNavigation.setSelectedItemId(R.id.home);
//            finish();//exit
//            replaceFragment(new Home_fragment_User());
        }
        else
        {
            super.onBackPressed();
            super.onBackPressed();

//            replaceFragment(new manage_profile());

            binding.bottomNavigation.setSelectedItemId(R.id.home);
//            finish();

        }

//        if(binding.bottomNavigation.getSelectedItemId()==R.id.home)
//        {
//            super.onBackPressed();
//            super.onBackPressed();
//            binding.bottomNavigation.setSelectedItemId(R.id.home);
//            finish();//exit
//        }
//        else
//        {
//            binding.bottomNavigation.setSelectedItemId(R.id.home);
//        }
    }


//    boolean doubleBackToExitPressedOnce = false;
//
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//        else {
//            getSupportFragmentManager().popBackStack();
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }


    private void replaceFragment(Fragment fragment){

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////      fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right);
//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
//        fragmentTransaction.replace(R.id.containerr,fragment);
//        fragmentTransaction.commit();

//        getSupportFragmentManager().beginTransaction().replace(R.id.containerr,fragment,null).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.containerr,fragment,null).addToBackStack(null).commit();
//        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void setDrawerClick(int itemId){
        switch (itemId){
            case R.id.managepro:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerr,new manage_profile()).commit();
                break;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerr,new translate_fragment()).commit();
                break;
//            case R.id.bookingsddffsfd:
//                getSupportFragmentManager().beginTransaction().replace(R.id.containerr,new ViewBooking()).commit();
//                break;
            case R.id.credit:
                getSupportFragmentManager().beginTransaction().replace(R.id.containerr,new credits_devloper()).commit();
                break;
            case R.id.finish:
                Toast.makeText(MainActivity3.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                sharedPreferenceClass.clear();
                startActivity(new Intent(MainActivity3.this,MainActivity.class));


        }
    }

}