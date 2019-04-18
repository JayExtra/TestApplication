package com.example.testapplication;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    private Toolbar homeToolbar;
    private BottomNavigationView bottomNavigationViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeToolbar=findViewById(R.id.home_toolbar);
        bottomNavigationViewHome=findViewById(R.id.bottom_navigation);

        setSupportActionBar(homeToolbar);
        getSupportActionBar().setTitle("Test Application");

        bottomNavigationViewHome.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            return false;
        }
    };
}
