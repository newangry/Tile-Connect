package com.funkyland.tileconnecttravel.NewVersion.Button;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.NewVersion.Generators.LevelManager;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class DollarButton {

    private Activity mContext;
    private Engine mEngine;
    private Camera mCamera;
    private TextView txt_level;

    public void onCreate(Activity mContext, Engine mEngine, Camera mCamera) {

        this.mContext = mContext;
        this.mEngine = mEngine;
        this.mCamera = mCamera;


        init();


    }

    public void init() {
        txt_level = mContext.findViewById(R.id.txt_level);
        updateDollar();
    }


    public void addDollar(final int n) {
        GameMainActivity.mPlay.dollar_current += n;
//        GameMainActivity.mPlay.scoreLevelWise += n;
        if (GameMainActivity.mPlay.dollar_current < 0) {
            GameMainActivity.mPlay.dollar_current = 0;
        }
        this.updateDollar();
    }


    public void reset() {
        GameMainActivity.mPlay.dollar_current = 0;
        this.updateDollar();
    }


    public void updateDollar() {
        String value = String.valueOf(GameMainActivity.mPlay.dollar_current);
        if (GameMainActivity.mPlay.dollar_current == 0) {
            value = "000";
        }


        Log.d("myCurrentLevel=====", mContext.getString(R.string.level) + ":" + String.valueOf(LevelManager.levelCurrent) + " || " + mContext.getString(R.string.score) + ":" + String.valueOf(value));
        if (LevelManager.levelCurrent < 10) {
            this.txt_level.setText(mContext.getString(R.string.level) + " " + String.valueOf(LevelManager.levelCurrent) + "    " + mContext.getString(R.string.score) + ":" + String.valueOf(value));
        } else if (LevelManager.levelCurrent < 100) {
            this.txt_level.setText(mContext.getString(R.string.level) + " " + String.valueOf(LevelManager.levelCurrent) + "    " + mContext.getString(R.string.score) + ":" + String.valueOf(value));
        } else if (LevelManager.levelCurrent < 1000) {
            this.txt_level.setText(mContext.getString(R.string.level) + " " + String.valueOf(LevelManager.levelCurrent) + "    " + mContext.getString(R.string.score) + ":" + String.valueOf(value));
        } else {
            this.txt_level.setText(mContext.getString(R.string.level) + " " + String.valueOf(LevelManager.levelCurrent) + "    " + mContext.getString(R.string.score) + ":" + String.valueOf(value));
        }
    }



    public void addTextSubDollar(final String s) {
        try {

            StringBuilder str = new StringBuilder();
            str.append(txt_level.getText());
            str.append(s);

            txt_level.setText(str.toString());


        } catch (Exception ex) {
        }

    }


}
