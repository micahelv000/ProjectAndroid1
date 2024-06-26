package com.mordouchvolobuev.Spark.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mordouchvolobuev.Spark.fragments.HomeFragment;
import com.mordouchvolobuev.Spark.fragments.ParkingMapsFragment;
import com.mordouchvolobuev.Spark.fragments.ProfileFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 3; // Number of pages
    private final String userData;

    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, String userDataString) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        userData = userDataString;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Return the fragment for each position
        Bundle bundle = new Bundle();
        bundle.putString("userData", userData);

        switch (position) {
            // Pass user data to ProfileFragment
            case 0:
                ParkingMapsFragment parkingMapsFragment = new ParkingMapsFragment();
                parkingMapsFragment.setArguments(bundle);
                return parkingMapsFragment;
            case 1:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);
                return homeFragment;
            case 2:
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle);
                return profileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}
