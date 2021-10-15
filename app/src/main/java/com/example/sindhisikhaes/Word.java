package com.example.sindhisikhaes;

import androidx.annotation.NonNull;

public class Word {

    private String sindhiTranslation, englishTranslation;
    private int imageId = NO_IMG, audioResource;
    private static final int NO_IMG = 1;

    public Word(String sindhiTranslation, String englishTranslation, int imageId, int audioResource) {
        this.sindhiTranslation = sindhiTranslation;
        this.englishTranslation = englishTranslation;
        this.imageId = imageId;
        this.audioResource = audioResource;
    }

    public Word(String sindhiTranslation, String englishTranslation, int audioResource) {
        this.sindhiTranslation = sindhiTranslation;
        this.englishTranslation = englishTranslation;
        this.audioResource = audioResource;
    }

    public String getSindhiTranslation() {
        return sindhiTranslation;
    }

    public void setSindhiTranslation(String sindhiTranslation) {
        this.sindhiTranslation = sindhiTranslation;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getAudioResource() {
        return audioResource;
    }

    public void setAudioResource(int audioResource) {
        this.audioResource = audioResource;
    }

    public boolean hasImage() {
        return (imageId != NO_IMG);
    }

    @NonNull
    @Override
    public String toString() {
        return "Word{" +
                "sindhiTranslation='" + sindhiTranslation + '\'' +
                ", englishTranslation='" + englishTranslation + '\'' +
                ", imageId=" + imageId +
                ", audioResource=" + audioResource +
                '}';
    }
}
