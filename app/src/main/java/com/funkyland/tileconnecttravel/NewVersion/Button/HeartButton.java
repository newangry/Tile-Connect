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

public class HeartButton {

    private Activity activity;
    private Context context;
    private Engine mEngine;
    private Camera mCamera;

    private ImageView btn_heart;
    private TextView txt_total_heart;

    public void onCreate(Activity activity, Context context, Engine mEngine, Camera mCamera) {

        this.activity = activity;
        this.context = context;
        this.mEngine = mEngine;
        this.mCamera = mCamera;

        btn_heart = activity.findViewById(R.id.btn_heart);
        txt_total_heart = activity.findViewById(R.id.txt_total_heart);

        setHeartText();

        btn_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameMainActivity.mSound.playClick();
//                GameMainActivity.mPlay.showPurchaseDilaog(Constant.Heart);
            }
        });

    }

    public void setHeartText() {
        if (IPreferences.getInstance(activity).getHeart() > 0) {
            txt_total_heart.setVisibility(View.VISIBLE);
            txt_total_heart.setText("" + IPreferences.getInstance(activity).getHeart());
        } else {
            txt_total_heart.setVisibility(View.GONE);
        }
    }

    public void UseHeart() {
        IPreferences.getInstance(context).setHeart(IPreferences.getInstance(context).getHeart() - 1);
        setHeartText();
    }
}
