package com.example.sindhisikhaes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
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
    private List<Word> wordList;
    int colorId;


    MediaPlayer mediaPlayer;
    AudioAttributes audioAttributes;
    AudioFocusRequest audioFocusRequest;
    boolean playbackDelayed = false;
    boolean resumefocusGain = false;
    final Object focusLock = new Object();
    AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    if (playbackDelayed || resumefocusGain) {
                        synchronized (focusLock) {
                            playbackDelayed = false;
                            resumefocusGain = false;
                        }
                        mediaPlayer.start();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    synchronized (focusLock) {
                        // this is not a transient loss, we shouldn't automatically resume for now
                        resumefocusGain = false;
                        playbackDelayed = false;
                    }
                    freeMediaResources();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    // we handle all transient losses the same way because we never duck audio books
                    synchronized (focusLock) {
                        // we should only resume if playback was interrupted
                        resumefocusGain = mediaPlayer.isPlaying();
                        playbackDelayed = false;
                    }
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
            }
        }
    };


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
        if (word.hasImage()) {
            holder.image.setImageResource(word.getImageId());
        }
        else {
            holder.image.setVisibility(View.GONE);
        }
        holder.constraintLayout.setBackgroundColor(color);
        holder.englishTranslation.setText(word.getEnglishTranslation());
        holder.sindhiTranslation.setText(word.getSindhiTranslation());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("narayan", "Current word object in Colors category: " + word);

                freeMediaResources();

                audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

                audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build();

                audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                        .setAudioAttributes(audioAttributes)
                        .setAcceptsDelayedFocusGain(true)
                        .setWillPauseWhenDucked(true)
                        .setOnAudioFocusChangeListener(audioFocusChangeListener)
                        .build();

                //Requesting Audio Focus
                int res = audioManager.requestAudioFocus(audioFocusRequest);
                synchronized (focusLock) {
                    if (res == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                        playbackDelayed = false;
                    } else if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        // We have audio focus now:
                        playbackDelayed = false;
                        mediaPlayer = MediaPlayer.create(context, word.getAudioResource());
                        mediaPlayer.setAudioAttributes(audioAttributes);
                        mediaPlayer.start();
                        playingSoundCompleted();
                    } else if (res == AudioManager.AUDIOFOCUS_REQUEST_DELAYED) {
                        playbackDelayed = true;
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView englishTranslation, sindhiTranslation;
        ConstraintLayout constraintLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            image = (ImageView) itemView.findViewById(R.id.image);
            englishTranslation = (TextView) itemView.findViewById(R.id.tvEnglishTrans);
            sindhiTranslation = (TextView) itemView.findViewById(R.id.tvSindhiTrans);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constraintLayout);

        }
    }

    public void freeMediaResources() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    public void playingSoundCompleted() {
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    freeMediaResources();
                }
            });
        }
    }



}
