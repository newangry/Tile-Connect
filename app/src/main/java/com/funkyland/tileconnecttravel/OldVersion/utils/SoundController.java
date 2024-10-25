package com.funkyland.tileconnecttravel.OldVersion.utils;

import android.content.Context;
import android.media.SoundPool;

import com.funkyland.tileconnecttravel.OldVersion.IPreferences;
import com.funkyland.tileconnecttravel.R;

public class SoundController {

    int bad;
    int click;
    Context context;
    int finish;
    int gameover;
    int good;
    SoundPool mSoundPool;
    int random;
    float volume;

    public SoundController() {
        super();
        this.bad = -1;
        this.click = -1;
        this.finish = -1;
        this.good = -1;
        this.random = -1;
        this.gameover = -1;
        this.volume = 0.5f;
    }

    public void loadSound(final Context context) {
        this.context = context;
        this.mSoundPool = new SoundPool(3, 3, 100);
        this.bad = this.mSoundPool.load(this.context, R.raw.wrong, 1);
        this.click = this.mSoundPool.load(this.context, R.raw.touch, 1);
        this.finish = this.mSoundPool.load(this.context, R.raw.winner, 1);
        this.good = this.mSoundPool.load(this.context, R.raw.exellent, 1);
        this.random = this.mSoundPool.load(this.context, R.raw.rearrange, 1);
        this.gameover = this.mSoundPool.load(this.context, R.raw.losser, 1);
    }

    public void offSound() {
        this.volume = 0.0f;
    }

    public void playBad() {
        if (IPreferences.getInstance(context).getBgSound()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SoundController.this.mSoundPool.play(SoundController.this.bad, SoundController.this.volume, SoundController.this.volume, 1, 0, 1.0f);
                }
            }).start();
        }
    }

    public void playClick() {
        if (IPreferences.getInstance(context).getBgSound()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SoundController.this.mSoundPool.play(SoundController.this.click, SoundController.this.volume, SoundController.this.volume, 1, 0, 1.0f);
                }
            }).start();
        }
    }

    public void playFinish() {
        if (IPreferences.getInstance(context).getBgSound()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SoundController.this.mSoundPool.play(SoundController.this.finish, SoundController.this.volume, SoundController.this.volume, 1, 0, 1.0f);
                }
            }).start();
        }
    }

    public void playGameOver() {
        if (IPreferences.getInstance(context).getBgSound()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SoundController.this.mSoundPool.play(SoundController.this.gameover, SoundController.this.volume, SoundController.this.volume, 1, 0, 1.0f);
                }
            }).start();
        }
    }

    public void playGood() {
        if (IPreferences.getInstance(context).getBgSound()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SoundController.this.mSoundPool.play(SoundController.this.good, SoundController.this.volume, SoundController.this.volume, 1, 0, 1.0f);
                }
            }).start();
        }
    }

    public void playRandom() {
        if (IPreferences.getInstance(context).getBgSound()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SoundController.this.mSoundPool.play(SoundController.this.random, SoundController.this.volume, SoundController.this.volume, 1, 0, 1.0f);
                }
            }).start();
        }
    }
}
