package com.funkyland.tileconnecttravel.NewVersion.Button;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_CLAS;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class ThemeButton {


    private Activity activity;
    private Context context;
    private Engine mEngine;
    private Camera mCamera;


    private ImageView btn_theme;

    public void onCreate(Activity activity, Context context, Engine mEngine, Camera mCamera) {

        this.activity = activity;
        this.context = context;
        this.mEngine = mEngine;
        this.mCamera = mCamera;

        btn_theme = activity.findViewById(R.id.btn_theme);

        if (Pereferences.get_gameMode().equals(GAME_MODE_CLAS)) {
            btn_theme.setVisibility(View.VISIBLE);
        } else {
            btn_theme.setVisibility(View.GONE);
        }

        btn_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameMainActivity.mPlay.mSound.playClick();
                GameMainActivity.mPlay.mProgressBar.setPause(true);
                GameMainActivity.mPlay.musicBackground.pause();
                ThemeButton.this.onClickTheme();

            }
        });
    }


    public void onClickTheme() {
        GameMainActivity.mPlay.showThemeDialog();
    }


}
