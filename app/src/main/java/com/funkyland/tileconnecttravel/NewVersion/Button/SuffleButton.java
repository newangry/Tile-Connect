package com.funkyland.tileconnecttravel.NewVersion.Button;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class SuffleButton {

    private Activity activity;
    private Context context;
    private Engine mEngine;
    private Camera mCamera;
    public static boolean SuffleVideoPlay = false;

    private ImageView btn_suffle;
    private TextView txt_total_suffle;

    public void onCreate(Activity activity, Context context, Engine mEngine, Camera mCamera) {

        this.activity = activity;
        this.context = context;
        this.mEngine = mEngine;
        this.mCamera = mCamera;

        btn_suffle = activity.findViewById(R.id.btn_suffle);
        txt_total_suffle = activity.findViewById(R.id.txt_total_suffle);

        setSuffleText();
        btn_suffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameMainActivity.mPlay.mSound.playClick();

                if (IPreferences.getInstance(activity).getSuffle() > 0) {
                    IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() - 1);
                    GameMainActivity.mPlay.swapItem();
                    setSuffleText();
                } else {
                    GameMainActivity.mPlay.ShowDialogBefore_watchVide_Suffle();
                }
            }
        });

    }

    public void setSuffleText() {
        if (IPreferences.getInstance(activity).getSuffle() > 0) {
            txt_total_suffle.setText("" + IPreferences.getInstance(activity).getSuffle());
        } else {
            txt_total_suffle.setText(activity.getString(R.string.ad));
        }
    }

}
