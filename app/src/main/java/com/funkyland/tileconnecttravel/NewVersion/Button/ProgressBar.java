package com.funkyland.tileconnecttravel.NewVersion.Button;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_TYPE_RELAXED;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class ProgressBar {

    public static int current_time;
    public static int total_time;
    public boolean isPause;
    public boolean isStop;
    private Activity mContext;
    private Engine mEngine;
    private Camera mCamera;
    private AppCompatSeekBar progressbar;

    public ProgressBar() {
        super();
        this.isPause = false;
        this.isStop = false;
    }

    public int getTimeEnd() {
        return Math.abs(this.current_time - this.total_time);
    }

    public void onDestroy() {
        this.isStop = true;
    }

    public void setPause(final boolean isPause) {
        this.isPause = isPause;
    }

    public void setStop(final boolean isStop) {
        this.isStop = isStop;
    }

    public void setTotalTime(final int total_time) {
        this.total_time = total_time;
    }

    @SuppressLint("NewApi")
    public void onCreate(Activity mContext, Engine mEngine, Camera mCamera) {
        this.mContext = mContext;
        this.mEngine = mEngine;
        this.mCamera = mCamera;
        progressbar = mContext.findViewById(R.id.progressbar);

        if (Pereferences.get_gameType().equals(GAME_TYPE_RELAXED)) {
            progressbar.setVisibility(View.GONE);
        }

        progressbar.setClickable(false);
        progressbar.setEnabled(false);

        progressbar.setProgress(100);
        progressbar.setMax(100);
    }

    public void start() {
        if (this.total_time >= 0) {
            this.current_time = this.total_time;
            this.isStop = false;
            this.isPause = false;

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (!ProgressBar.this.isStop && ProgressBar.this.current_time > 0) {
                        if (!ProgressBar.this.isPause) {
                            try {

                                long oldtime = System.currentTimeMillis();
                                Thread.sleep(1000L);
                                long newtime = System.currentTimeMillis();
                                long timeee = newtime - oldtime;
                                ProgressBar.this.current_time -= 1;

                                ProgressBar.this.updateProgressBar(current_time, total_time);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }

                    if (Pereferences.get_gameType().equals(GAME_TYPE_RELAXED)) {

                    } else {
                        if (ProgressBar.this.current_time <= 0) {
                            GameMainActivity.mPlay.gameOver();
                        }
                    }
                }
            }).start();
        }
    }

    public void updateProgressBar(int ctime, int ttime) {

        if (this.current_time > 0 && !this.isStop) {
            long c = ctime * 1000;
            long t = ttime * 1000;
            progressbar.setProgress(getProgressPercentage(c, t));
        } else {

        }
    }

    public int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }
}
