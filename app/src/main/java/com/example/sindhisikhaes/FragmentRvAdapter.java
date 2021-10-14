package com.example.sindhisikhaes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragmentRvAdapter extends RecyclerView.Adapter<FragmentRvAdapter.MyViewHolder> {

    private Context context;
    private Fragment fragment;
    private List<Word> wordList;
    int colorId;


    public FragmentRvAdapter(Context context, List<Word> wordList, int colorId) {
        this.context = context;
        this.wordList = wordList;
        this.colorId = colorId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = wordList.get(position);
        int color = ContextCompat.getColor(context, colorId);
        holder.image.setImageResource(word.getImageId());
        holder.constraintLayout.setBackgroundColor(color);
        holder.englishTranslation.setText(word.getEnglishTranslation());
        holder.sindhiTranslation.setText(word.getSindhiTranslation());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView englishTranslation, sindhiTranslation;
        ConstraintLayout constraintLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            itemView.setBackgroundColor(colorId);

            image = (ImageView) itemView.findViewById(R.id.image);
            englishTranslation = (TextView) itemView.findViewById(R.id.tvEnglishTrans);
            sindhiTranslation = (TextView) itemView.findViewById(R.id.tvSindhiTrans);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constraintLayout);

        }
    }

}
