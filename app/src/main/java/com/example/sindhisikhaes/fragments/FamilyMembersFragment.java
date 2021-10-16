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

public class FamilyMembersFragment extends Fragment {

    FragmentRvBinding binding;
    private FragmentRvAdapter adapter;
    private List<Word> wordList;

    public FamilyMembersFragment() {

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
        adapter = new FragmentRvAdapter(getContext(), wordList, R.color.category_family);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerview.setAdapter(adapter);
    }

    private void prepareWordList() {


        wordList = new ArrayList<Word>();
        wordList.add(new Word("Father", "Pii", R.drawable.family_father, R.raw.audio_family_father));
        wordList.add(new Word("Mother", "Maa", R.drawable.family_mother, R.raw.audio_family_mother));
        wordList.add(new Word("Son", "Put", R.drawable.family_son, R.raw.audio_family_son));
        wordList.add(new Word("Daughter", "Dii", R.drawable.family_daughter, R.raw.audio_family_daughter));
        wordList.add(new Word("Elder Brother", "Vado Bha", R.drawable.family_older_brother, R.raw.audio_family_older_brother));
        wordList.add(new Word("Younger Brother", "Nando Bha", R.drawable.family_younger_brother, R.raw.audio_family_younger_brother));
        wordList.add(new Word("Elder Sister", "Vadi Bhend", R.drawable.family_older_sister, R.raw.audio_family_older_sister));
        wordList.add(new Word("Younger Sister", "Nandi Bhend", R.drawable.family_younger_sister, R.raw.audio_family_younger_sister));
        wordList.add(new Word("GrandMother", "Dadi", R.drawable.family_grandmother, R.raw.audio_family_grandmother));
        wordList.add(new Word("GrandFather", "Dado", R.drawable.family_grandfather, R.raw.audio_family_grandfather));
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.freeMediaResources();
    }
}
