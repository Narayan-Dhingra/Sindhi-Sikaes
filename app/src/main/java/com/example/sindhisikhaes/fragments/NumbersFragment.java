package com.example.sindhisikhaes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sindhisikhaes.FragmentRvAdapter;
import com.example.sindhisikhaes.R;
import com.example.sindhisikhaes.Word;
import com.example.sindhisikhaes.databinding.FragmentRvBinding;

import java.util.ArrayList;
import java.util.List;


public class NumbersFragment extends Fragment {

    FragmentRvBinding binding;
    private FragmentRvAdapter adapter;
    private List<Word> wordList;


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRvBinding.inflate(inflater, container, false);

        prepareWordList();

        adapter = new FragmentRvAdapter(getContext(), wordList, R.color.category_numbers);
        binding.recyclerview.setAdapter(adapter);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRvBinding.bind(view);

        prepareWordList();
        prepareAdapterAndRv();

    }

    private void prepareAdapterAndRv() {
        adapter = new FragmentRvAdapter(getContext(), wordList, R.color.category_numbers);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerview.setAdapter(adapter);
    }



    private void prepareWordList() {


        wordList = new ArrayList<Word>();
        wordList.add(new Word("One", "Hik", R.drawable.number_one, R.raw.audio_number_one));
        wordList.add(new Word("Two", "Bha", R.drawable.number_two, R.raw.audio_number_two));
        wordList.add(new Word("Three", "Tre", R.drawable.number_three, R.raw.audio_number_three));
        wordList.add(new Word("Four", "Char", R.drawable.number_four, R.raw.audio_number_four));
        wordList.add(new Word("five", "Panj", R.drawable.number_five, R.raw.audio_number_five));
        wordList.add(new Word("Six", "Ch", R.drawable.number_six, R.raw.audio_number_six));
        wordList.add(new Word("Seven", "Sat", R.drawable.number_seven, R.raw.audio_number_seven));
        wordList.add(new Word("Eight", "Ath", R.drawable.number_eight, R.raw.audio_number_eight));
        wordList.add(new Word("Nine", "Nav", R.drawable.number_nine, R.raw.audio_number_nine));
        wordList.add(new Word("Ten", "Dae", R.drawable.number_ten, R.raw.audio_number_ten));
    }


}