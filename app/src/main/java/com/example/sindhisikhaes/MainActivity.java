package com.example.sindhisikhaes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sindhisikhaes.databinding.ActivityMainBinding;
import com.example.sindhisikhaes.fragments.ColorsFragment;
import com.example.sindhisikhaes.fragments.FamilyMembersFragment;
import com.example.sindhisikhaes.fragments.NumbersFragment;
import com.example.sindhisikhaes.fragments.PhrasesFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final String[] tabTitles = {"Numbers", "Family Members", "Colors", "Phrases"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();


    }

    public void init(){

        binding.viewPager.setAdapter(new FragmentPagerAdapter(this));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> tab.setText(tabTitles[position])).attach();

    }

    public class FragmentPagerAdapter extends FragmentStateAdapter{

        public FragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new NumbersFragment();
                case 1:
                    return new FamilyMembersFragment();
                case 2:
                    return new ColorsFragment();
                case 3:
                    return new PhrasesFragment();
            }
            return new Fragment();
        }

        @Override
        public int getItemCount() {
            return tabTitles.length;
        }
    }





}