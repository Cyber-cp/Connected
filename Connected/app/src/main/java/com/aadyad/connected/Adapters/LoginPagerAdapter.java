package com.aadyad.connected.Adapters;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aadyad.connected.Fragments.LoginFragment;
import com.aadyad.connected.Fragments.RegisterFragment;

public class LoginPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    String[] pageTitles = new String[]{"Login", "Register"};

    public LoginPagerAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
