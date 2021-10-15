package com.example.sindhisikhaes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sindhisikhaes.FragmentRvAdapter;
import com.example.sindhisikhaes.R;
import com.example.sindhisikhaes.Word;
import com.example.sindhisikhaes.databinding.FragmentRvBinding;

import java.util.ArrayList;
import java.util.List;

public class ColorsFragment extends Fragment {

    FragmentRvBinding binding;
    private FragmentRvAdapter adapter;
    private List<Word> wordList;

    public ColorsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRvBinding.bind(view);

        prepareWordList();
        prepareAdapterAndRv();

    }

    private void prepareAdapterAndRv() {
        adapter = new FragmentRvAdapter(getContext(), wordList, R.color.category_colors);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerview.setAdapter(adapter);
    }

    private void prepareWordList() {


        wordList = new ArrayList<Word>();
        wordList.add(new Word("Red", "Gado", R.drawable.color_red, R.raw.audio_color_red));
        wordList.add(new Word("Green", "Saoo", R.drawable.color_green, R.raw.audio_color_green));
        wordList.add(new Word("Brown", "bhure", R.drawable.color_brown, R.raw.audio_color_brown));
        wordList.add(new Word("Orange", "Narangi", R.drawable.color_mustard_yellow, R.raw.audio_color_mustard_yellow));
        wordList.add(new Word("Black", "Kaalo", R.drawable.color_black, R.raw.audio_color_black));
        wordList.add(new Word("White", "Safa", R.drawable.color_white, R.raw.audio_color_white));
        wordList.add(new Word("Pink", "Gulabi", R.drawable.color_red, R.raw.audio_color_red));
        wordList.add(new Word("Blue", "Neero", R.drawable.color_gray, R.raw.audio_color_gray));
    }

}
