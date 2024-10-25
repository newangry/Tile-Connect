package com.funkyland.tileconnecttravel.NewVersion.Button;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleRewardedAdMoreSeconds;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IUtil;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class AdButton {

    private Activity activity;
    private Context context;
    private Engine mEngine;
    private Camera mCamera;


    private ImageView btn_show_ad;

    public void onCreate(Activity activity, Context context, Engine mEngine, Camera mCamera) {

        this.activity = activity;
        this.context = context;
        this.mEngine = mEngine;
        this.mCamera = mCamera;

        btn_show_ad = activity.findViewById(R.id.btn_show_ad);

        btn_show_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GameMainActivity.mPlay.mSound.playClick();

                if (IUtil.isNetworkConnected(GameMainActivity.mPlay)) {

                    GameMainActivity.mPlay.mProgressBar.setPause(true);
                    GameMainActivity.mPlay.musicBackground.pause();

                    GoogleRewardedAdMoreSeconds googleRewardedAd = new GoogleRewardedAdMoreSeconds(GameMainActivity.mPlay);

                    googleRewardedAd.setInterfaceInter(new GoogleRewardedAdMoreSeconds.OnclickInter() {
                        @Override
                        public void clicked() {
                            GameMainActivity.mPlay.isShowPuaseDialogInOnresume = false;
                            GameMainActivity.mPlay.musicBackground.resume();
                            GameMainActivity.mPlay.resumeGame();
                        }
                    });

                    googleRewardedAd.setEndedRewardedAd(new GoogleRewardedAdMoreSeconds.EndedRewardedAd() {
                        @Override
                        public void endedRwardAd() {
                            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 1);
                            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 1);
                            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 1);

                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GameMainActivity.mPlay.mButtonOneKind.setOneKindRemoveText();
                                    GameMainActivity.mPlay.mButtonHint.setHintText();
                                    GameMainActivity.mPlay.mButtonSuffle.setSuffleText();
                                }
                            });

                        }
                    });

                } else {
                    IUtil.showToast(activity, activity.getString(R.string.no_internet));
                }

            }
        });
    }

}
