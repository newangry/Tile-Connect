package com.funkyland.tileconnecttravel.NewVersion.Button;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.NewVersion.Generators.OnclickChecker;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class HintButton {

    private Activity mContext;
    private Engine mEngine;
    private Camera mCamera;

    private ImageView btn_hint;
    private TextView txt_total_hint;

    public static boolean hintVideoPlay = false;

    public void onCreate(Activity mContext, Engine mEngine, Camera mCamera) {

        this.mContext = mContext;
        this.mEngine = mEngine;
        this.mCamera = mCamera;

        this.btn_hint = mContext.findViewById(R.id.btn_hint);
        this.txt_total_hint = mContext.findViewById(R.id.txt_total_hint);

        setHintText();

        btn_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameMainActivity.mSound.playClick();
                HintButton.this.onClickButtonHint();
            }
        });


    }

    public void setHintText() {

        if (IPreferences.getInstance(mContext).getHint() > 0) {
            txt_total_hint.setText("" + IPreferences.getInstance(mContext).getHint());
        } else {
            txt_total_hint.setText(mContext.getString(R.string.ad));
        }

    }

    @SuppressLint("WrongConstant")
    public void onClickButtonHint() {

        if (IPreferences.getInstance(mContext).getHint() > 0) {
            OnclickChecker.activeSearhHint();
            if (!GameMainActivity.mPlay.mHint.visiable()) {
                IPreferences.getInstance(mContext).setHint(IPreferences.getInstance(mContext).getHint() - 1);
            }
            GameMainActivity.mPlay.mHint.setVisiable(true);
            setHintText();
        } else {
            GameMainActivity.mPlay.ShowDialogBefore_watchVideo_Hint();
        }
    }
}
