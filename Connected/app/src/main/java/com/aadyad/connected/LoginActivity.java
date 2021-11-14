package com.aadyad.connected;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aadyad.connected.Adapters.LoginPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager loginViewPager;
    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Findviews
        tabLayout = findViewById(R.id.loginTabs);
        loginViewPager = findViewById(R.id.loginViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initialize views
        final LoginPagerAdapter adapter = new LoginPagerAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        loginViewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(loginViewPager);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser u = firebaseAuth.getCurrentUser();
                if(u != null){
                    if(!u.isEmailVerified()){
                        u.sendEmailVerification();
                        VerifyDialog d = new VerifyDialog();
                        d.show(getSupportFragmentManager(), "Verify");
                    }
                    //Todo: Start app
                }
            }
        });
    }
}