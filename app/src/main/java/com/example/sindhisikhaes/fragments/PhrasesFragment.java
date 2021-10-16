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

public class PhrasesFragment extends Fragment {

    FragmentRvBinding binding;
    private FragmentRvAdapter adapter;
    private List<Word> wordList;

    public PhrasesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
        adapter = new FragmentRvAdapter(getActivity(), wordList, R.color.category_phrases);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerview.setAdapter(adapter);
    }

    private void prepareWordList() {


        wordList = new ArrayList<Word>();
        wordList.add(new Word("Where are you going?", "Kade toh vanje?", R.raw.audio_phrase_where_are_you_going));
        wordList.add(new Word("What is your name?", "Tunjo Naalo Shae?", R.raw.audio_phrase_what_is_your_name));
        wordList.add(new Word("My name is...", "Munjo Naalo aaye...", R.raw.audio_phrase_my_name_is));
        wordList.add(new Word("How are you Feeling?", "Kian Lage toh?", R.raw.audio_phrase_how_are_you_feeling));
        wordList.add(new Word("I am feeling good.", "Mukhe sutho lage toh.", R.raw.audio_phrase_im_feeling_good));
        wordList.add(new Word("Are you coming?", "Hale toh?", R.raw.audio_phrase_are_you_coming));
        wordList.add(new Word("Yes, I'm coming.", "Haan, maa ache toh.", R.raw.audio_phrase_yes_im_coming));
        wordList.add(new Word("Lets Go.", "Hal.", R.raw.audio_phrase_lets_go));
        wordList.add(new Word("Come Here.", "Hede Ach.", R.raw.audio_phrase_come_here));
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.freeMediaResources();
    }
}
