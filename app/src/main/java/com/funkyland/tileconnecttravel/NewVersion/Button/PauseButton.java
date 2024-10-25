package com.funkyland.tileconnecttravel.NewVersion.Button;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class PauseButton {

  private Activity mContext;
  private Engine mEngine;
  private Camera mCamera;


  private ImageView btn_pause;


  public void onCreate(Activity mContext, Engine mEngine, Camera mCamera) {

    this.mContext = mContext;
    this.mEngine = mEngine;
    this.mCamera = mCamera;

    this.btn_pause = mContext.findViewById(R.id.btn_pause);


    btn_pause.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//                if (touchEvent.getAction() == 0) {
        GameMainActivity.mSound.playClick();
//                    PauseButton.this.buttonpause_SP.setScale(1.3f);
//                } else if (touchEvent.getAction() == 1) {
//                    PauseButton.this.buttonpause_SP.setScale(1.0f);
        PauseButton.this.onClickButtonPause();
//                }
      }
    });
  }


  public void onClickButtonPause() {
    GameMainActivity.mPlay.pauseGame();
  }
}
