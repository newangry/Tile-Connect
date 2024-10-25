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

public class OneKindButton {

    public static boolean OneKindVideoPlay = false;
    private Activity activity;
    private Context context;
    private Engine mEngine;
    private Camera mCamera;
    public ImageView btn_remove_same;
    private TextView txt_total_remove_same;

    public void onCreate(Activity activity, Context context, Engine mEngine, Camera mCamera) {

        this.activity = activity;
        this.context = context;
        this.mEngine = mEngine;
        this.mCamera = mCamera;

        btn_remove_same = activity.findViewById(R.id.btn_remove_same);
        txt_total_remove_same = activity.findViewById(R.id.txt_total_remove_same);

        setOneKindRemoveText();
        btn_remove_same.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                GameMainActivity.mPlay.mSound.playClick();

                if (IPreferences.getInstance(activity).getOneKindRemove() > 0) {
                    try {
                        IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() - 1);
                        GameMainActivity.mPlay.mManagerItemPikachu.oneKindRemove();
                        setOneKindRemoveText();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    btn_remove_same.setEnabled(false);
                    GameMainActivity.mPlay.DialogBefore_watchVideo_Onekind();
                }

            }
        });
    }

    public void setOneKindRemoveText() {
        if (IPreferences.getInstance(activity).getOneKindRemove() > 0) {
//            txt_total_remove_same.setVisibility(View.VISIBLE);
            txt_total_remove_same.setText("" + IPreferences.getInstance(activity).getOneKindRemove());
        } else {
//            txt_total_remove_same.setVisibility(View.GONE);
            txt_total_remove_same.setText(activity.getString(R.string.ad));
        }
    }

}
