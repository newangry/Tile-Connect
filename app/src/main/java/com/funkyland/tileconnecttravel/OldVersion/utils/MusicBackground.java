package com.funkyland.tileconnecttravel.OldVersion.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.funkyland.tileconnecttravel.OldVersion.IPreferences;
import com.funkyland.tileconnecttravel.R;

public class MusicBackground {
    public MediaPlayer mediaPlayer;
    private Context context;

    public void loadMusic(final Context context) {
        this.context = context;

        (this.mediaPlayer = MediaPlayer.create(context, R.raw.musicbackground)).setVolume(0.5f, 0.5f);
        this.mediaPlayer.setOnCompletionListener((MediaPlayer.OnCompletionListener) new MediaPlayer.OnCompletionListener() {
            public void onCompletion(final MediaPlayer mediaPlayer) {
                MusicBackground.this.play();
            }
        });
    }

    public void pause() {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
        }
    }

    public void play() {
        if (IPreferences.getInstance(context).getBgSound()) {
            this.mediaPlayer.seekTo(0);
            this.mediaPlayer.start();
        }
    }

    public void release() {
        this.mediaPlayer.release();
    }

    public void resume() {
        if (IPreferences.getInstance(context).getBgSound()) {
            if (!this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.start();
            }
        }
    }
}