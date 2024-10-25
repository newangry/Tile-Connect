package com.funkyland.tileconnecttravel.OldVersion;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.funkyland.tileconnecttravel.NewVersion.Ads.Interstitial_Ad_google;
import com.funkyland.tileconnecttravel.NewVersion.Services.ForegroundService;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.OldVersion.ads.AdsActivity;
import com.funkyland.tileconnecttravel.OldVersion.ads.AdsAdapter;
import com.funkyland.tileconnecttravel.OldVersion.ads.AdsItemModel;
import com.funkyland.tileconnecttravel.OldVersion.utils.BackgroundImage;
import com.funkyland.tileconnecttravel.OldVersion.utils.DollarButton;
import com.funkyland.tileconnecttravel.OldVersion.utils.DrawLineSprite;
import com.funkyland.tileconnecttravel.OldVersion.utils.HintButton;
import com.funkyland.tileconnecttravel.OldVersion.utils.HintSprite;
import com.funkyland.tileconnecttravel.OldVersion.utils.ILogger;
import com.funkyland.tileconnecttravel.OldVersion.utils.INewVersion;
import com.funkyland.tileconnecttravel.OldVersion.utils.ItemsManager;
import com.funkyland.tileconnecttravel.OldVersion.utils.LevelManager;
import com.funkyland.tileconnecttravel.OldVersion.utils.MTMap;
import com.funkyland.tileconnecttravel.OldVersion.utils.MusicBackground;
import com.funkyland.tileconnecttravel.OldVersion.utils.OnclickChecker;
import com.funkyland.tileconnecttravel.OldVersion.utils.PauseButton;
import com.funkyland.tileconnecttravel.OldVersion.utils.ProgressBar;
import com.funkyland.tileconnecttravel.OldVersion.utils.RateButton;
import com.funkyland.tileconnecttravel.OldVersion.utils.SoundController;
import com.funkyland.tileconnecttravel.OldVersion.utils.TextLoadingSprite;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class GameMainActivity extends GameBaseActivity {

    public static SoundController mSound;
    public static GameMainActivity mPlay;
    public static int dollar_current = 0;
    public static Context instance = null;
    //    public static Handler mHandler_loadINTAd;
//    public static boolean isLoadLevelAd = false;
    public static boolean privacyDialog = false;
    public static int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    public boolean isOnPauseBefore = false;
    public boolean isThereAnyPopupDLG = false;
    public boolean GameOver;
    public BackgroundImage mBackground;
    public HintButton mButtonHint;
    public RateButton mButtonRate;
    //    public IAdButton mButtonAd;
    public INewVersion mButtonNewVersion;
    public PauseButton mButtonPause;
    public DollarButton mDollar;
    public DrawLineSprite mDrawLine;
    public Handler mHandler_gameover;
    public HintSprite mHint;
    public ItemsManager mManagerItemPikachu;
    public ProgressBar mProgressBar;
    public TextLoadingSprite mTextLoading;
    public MusicBackground musicBackground;

    public int total_dollar = 0;
    public int LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
    public int starByLevel = 0;
    public int moneyBonus = 0;
    //    public Dialog more_Apps_Dialog;
    Interstitial_Ad_google interstitial_ad_google = null;
    boolean isHasClickYesRateDialogBefore = false;
    TextView rateBtn, playBtn;
    RecyclerView recyclerView;
    ImageView btn_close;
    private boolean isGamePaused = false;
    //    private boolean isBackfromAdsINT = false;
    private List<Object> mAdsItems;

    public GameMainActivity() {
        super();
        this.GameOver = false;
        this.mHandler_gameover = new Handler() {
            public void handleMessage(final Message message) {
                super.handleMessage(message);
                GameMainActivity.this.mSound.playGameOver();
                GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_FAIL;
                showINTAd();
            }
        };


    }

    public void Interstitial_Ad_Google() {
        interstitial_ad_google = new Interstitial_Ad_google(GameMainActivity.this);
    }

    public void gameOver() {
        this.mHandler_gameover.sendEmptyMessage(0);
    }

    public void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameMainActivity.this.mProgressBar.onLoadResources();
                GameMainActivity.this.mButtonPause.onLoadResources();
                GameMainActivity.this.mButtonHint.onLoadResources();
                GameMainActivity.this.mButtonRate.onLoadResources();
//                GameMainActivity.this.mButtonAd.onLoadResources();
                GameMainActivity.this.mButtonNewVersion.onLoadResources();

                GameMainActivity.this.mDollar.onLoadResources();
                GameMainActivity.this.mHint.onLoadResources();
                GameMainActivity.this.mButtonPause.onLoadScene(GameMainActivity.this.mScene);
                GameMainActivity.this.mButtonHint.onLoadScene(GameMainActivity.this.mScene);
                GameMainActivity.this.mButtonRate.onLoadScene(GameMainActivity.this.mScene);
//                GameMainActivity.this.mButtonAd.onLoadScene(GameMainActivity.this.mScene);
                GameMainActivity.this.mButtonNewVersion.onLoadScene(GameMainActivity.this.mScene);

                GameMainActivity.this.mDollar.onLoadScene(GameMainActivity.this.mScene);
                GameMainActivity.this.mProgressBar.setXStartEnd(GameMainActivity.this.mDollar.getXendDollar(), GameMainActivity.this.mButtonPause.getStartX());
                GameMainActivity.this.mProgressBar.setMidYButtonPause(GameMainActivity.this.mButtonPause.getMidY());
                GameMainActivity.this.mProgressBar.onLoadScene(GameMainActivity.this.mScene);
//                MTMap.getTotalRowColumn(5, 166, (int) 150.0f);
                MTMap.getTotalRowColumn(GameMainActivity.this.mDollar.getStartX(), GameMainActivity.this.mDollar.getEndY(), (int) GameMainActivity.this.mButtonHint.getStartX() - 50);
                GameMainActivity.this.mManagerItemPikachu.setScene(GameMainActivity.this.mScene);
                if (!(GameMainActivity.this.mManagerItemPikachu.list_itemPikachu != null && GameMainActivity.this.mManagerItemPikachu.list_itemPikachu.size() > 0)) {
                    GameMainActivity.this.mManagerItemPikachu.addItem();
                }


                GameMainActivity.this.mDrawLine.onLoadScene(GameMainActivity.this.mScene);
                GameMainActivity.this.mTextLoading.hideTextLoading();
                GameMainActivity.this.mHint.onLoadScene(GameMainActivity.this.mScene);
                OnclickChecker.searchHint();
                GameMainActivity.this.mPlay.startGame();
            }
        }).start();
    }

    public void onBackPressed() {
        GameMainActivity.this.pauseGameWithoutDlg();

        GameMainActivity.this.mSound.playClick();
        if (IPreferences.getInstance((Context) this).getRateStatus()) {
            IConfirmDialog.showRateDialog(this, true, null,
                    new IButtonListener() {
                        @Override
                        public void onPress(final Dialog dialog) {
                            try {
                                if (GameMainActivity.instance != null) {
                                    ((Activity) GameMainActivity.instance).finish();
                                }
                                android.os.Process.killProcess(android.os.Process.myPid());
                                GameMainActivity.this.onBackPressed();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }, new IButtonListener() {
                        @Override
                        public void onPress(final Dialog dialog) {
                            GameMainActivity.this.resumeGame();
                        }
                    }
            );
        } else {
            IRateDialog.showRateDialog(this, true, null,
                    new IButtonListener() {
                        @Override
                        public void onPress(final Dialog dialog) {
                            GameMainActivity.this.resumeGame();
                        }
                    },
                    new IButtonListener() {
                        @Override
                        public void onPress(final Dialog dialog) {
                            try {
                                IPreferences.getInstance((Context) GameMainActivity.instance).saveRateStatus(true);
                                try {
                                    GameMainActivity.instance.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + GameMainActivity.instance.getPackageName())));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    GameMainActivity.instance.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + GameMainActivity.instance.getPackageName())));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, true
            );
        }
    }


    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        instance = this;

        HideNavigation(this);

        (this.musicBackground = new MusicBackground()).loadMusic((Context) this);
        this.musicBackground.play();
        OnclickChecker.isOnClickItem = true;
        GameMainActivity.mPlay = this;
        getGameStates();


        Interstitial_Ad_Google();

        (this.mBackground = new BackgroundImage()).onCreate(this.mContext, this.mEngine, this.mCamera);
        (this.mTextLoading = new TextLoadingSprite()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);
        (this.mButtonPause = new PauseButton()).onCreate(this.mContext, this.mEngine, this.mCamera);
        (this.mButtonHint = new HintButton()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);
        (this.mDollar = new DollarButton()).onCreate(this.mContext, this.mEngine, this.mCamera);
        (this.mProgressBar = new ProgressBar()).onCreate(this.mContext, this.mEngine, this.mCamera);
        (this.mHint = new HintSprite()).onCreate(this.mContext, this.mEngine, this.mCamera);
        this.mManagerItemPikachu = new ItemsManager(this.mContext, this.mEngine, this.mCamera);
        (this.mDrawLine = new DrawLineSprite()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);
        (this.mButtonRate = new RateButton()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);
//        (this.mButtonAd = new IAdButton()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);
        (this.mButtonNewVersion = new INewVersion()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);

        (GameMainActivity.this.mSound = new SoundController()).loadSound((Context) this);
        GameMainActivity.this.isThereAnyPopupDLG = false;


        if (IPreferences.getInstance((Context) this).getPolicyStatus()) {
//DA SHOW POLICY DIALOG ROIF.
            if (GameConfiguration.isConnected) {
                /*GameMainActivity.this.adView = new com.google.android.gms.ads.AdView(GameMainActivity.this);
                GameMainActivity.this.adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
                GameMainActivity.this.adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                GameMainActivity.this.adView.setAdUnitId(GameMainActivity.this.getString(R.string.admob_id));
                GameMainActivity.this.adView.setAdListener(new com.google.android.gms.ads.AdListener() {
                    public void onAdLoaded() {
                        try {
                            boolean isAds_Addbefore = false;
                            for (int i = 0; i < GameMainActivity.this.mFrameLayout.getChildCount(); ++i) {
                                if (GameMainActivity.this.mFrameLayout.getChildAt(i) == GameMainActivity.this.adView) {
                                    isAds_Addbefore = true;
                                }
                            }

                            if (!isAds_Addbefore) {
                                FrameLayout.LayoutParams frameLayout$LayoutParams2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, 81);
                                GameMainActivity.this.mFrameLayout.addView(GameMainActivity.this.adView, (ViewGroup.LayoutParams) frameLayout$LayoutParams2);
                            }
                        } catch (Exception exx) {
                            exx.printStackTrace();
                        }
                    }

                    public void onAdFailedToLoad(int errorCode) {

                    }

                    public void onAdOpened() {

                    }

                    public void onAdClosed() {

                    }

                    public void onAdLeftApplication() {

                    }

                });
                GameMainActivity.this.adView.loadAd(new AdRequest.Builder().build());*/
            }
        } else {
            IPolicyDialog.showPolicyDialog(this, true, null,
                    new IButtonListener() {
                        @Override
                        public void onPress(final Dialog dialog) {
                            try {
                                String url = "http://ghuyonline.webstarterz.com/PrivacyPolicy.html";
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                GameMainActivity.this.startActivity(i);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    },
                    new IButtonListener() {
                        @Override
                        public void onPress(final Dialog dialog) {
                            try {
                                IPreferences.getInstance((Context) GameMainActivity.instance).savePolicyStatus(true);

                                if (GameConfiguration.isConnected) {
                                    /*GameMainActivity.this.adView = new com.google.android.gms.ads.AdView(GameMainActivity.this);
                                    GameMainActivity.this.adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
                                    GameMainActivity.this.adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    GameMainActivity.this.adView.setAdUnitId(GameMainActivity.this.getString(R.string.admob_id));
                                    GameMainActivity.this.adView.setAdListener(new com.google.android.gms.ads.AdListener() {
                                        public void onAdLoaded() {
                                            try {
                                                boolean isAds_Addbefore = false;
                                                for (int i = 0; i < GameMainActivity.this.mFrameLayout.getChildCount(); ++i) {
                                                    if (GameMainActivity.this.mFrameLayout.getChildAt(i) == GameMainActivity.this.adView) {
                                                        isAds_Addbefore = true;
                                                    }
                                                }

                                                if (!isAds_Addbefore) {
                                                    FrameLayout.LayoutParams frameLayout$LayoutParams2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, 81);
                                                    GameMainActivity.this.mFrameLayout.addView(GameMainActivity.this.adView, (ViewGroup.LayoutParams) frameLayout$LayoutParams2);
                                                }
                                            } catch (Exception exx) {
                                                exx.printStackTrace();
                                            }
                                        }

                                        public void onAdFailedToLoad(int errorCode) {

                                        }

                                        public void onAdOpened() {

                                        }

                                        public void onAdClosed() {

                                        }

                                        public void onAdLeftApplication() {

                                        }

                                    });
                                    GameMainActivity.this.adView.loadAd(new AdRequest.Builder().build());*/
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, true
            );
        }

        if (Pereferences.get_dialog() == 0) {
            //moreAppsDialog();
            updateAppDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ILogger.LogInfo("onDestroy");
        try {
            this.mProgressBar.onDestroy();
            this.mHint.onDestroy();
            this.musicBackground.release();
        } catch (Exception ex) {
            ILogger.LogError("Play onDestroy " + ex.toString());
        }
    }

    @Override
    public void onLoadComplete() {
    }

    @Override
    public Engine onLoadEngine() {
        super.onLoadEngine();
        ILogger.LogInfo("onLoadEngine");
        return this.mEngine;
    }

    @Override
    public void onLoadResources() {
        super.onLoadResources();
        this.mBackground.onLoadResources();
        this.mTextLoading.onLoadResources();
        ILogger.LogInfo("onLoadResources");
    }

    @Override
    public Scene onLoadScene() {
        super.onLoadScene();
        this.mBackground.onLoadScene(this.mScene);
        this.mTextLoading.onLoadScene(this.mScene);
        this.load();
        ILogger.LogInfo("onLoadScene");
        return this.mScene;
    }

    protected void onStop() {
        super.onStop();
    }

    public void pauseGameWithoutDlg() {
        this.mProgressBar.setPause(true);
    }

    public void pauseGameWithoutDlg2() {
        this.musicBackground.pause();
        this.mProgressBar.setPause(true);
    }

    public void pauseGame() {
        this.mProgressBar.setPause(true);
        this.musicBackground.pause();
        IPauseDialog.showShareDialog(GameMainActivity.mPlay, GameMainActivity.mPlay.dollar_current, null, null,
                new IButtonListener() {
                    @Override
                    public void onPress(final Dialog dialog) {
                        try {
                            GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                            GameMainActivity.this.isThereAnyPopupDLG = false;
                            GameMainActivity.this.mSound.playClick();
                            GameMainActivity.mPlay.musicBackground.resume();
                            GameMainActivity.mPlay.resumeGame();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void removeItem(final int n, final int n2) {
        try {
            if (this.mManagerItemPikachu.removeItem(n, n2) <= 18) {
//                if (!GameMainActivity.this.isLoadLevelAd) {
                try {
//                        this.mHandler_loadINTAd.sendEmptyMessage(0);
                } catch (Exception exxxx) {
                    exxxx.printStackTrace();
                }
//                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void resetGame() {
        this.GameOver = false;
        ILogger.LogInfo("resetGame");
        this.mProgressBar.setStop(true);
        this.mTextLoading.showTextLoading();
        this.mHint.setVisiable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OnclickChecker.isOnClickItem = true;
                GameMainActivity.this.mBackground.resetBackground();
                GameMainActivity.this.mManagerItemPikachu.reset();

                //
                GameMainActivity.this.mProgressBar.reset();
                GameMainActivity.this.mButtonPause.reset();
                GameMainActivity.this.mButtonHint.reset();
                GameMainActivity.this.mButtonRate.reset();
//                GameMainActivity.this.mButtonAd.reset();
                GameMainActivity.this.mButtonNewVersion.reset();

                GameMainActivity.this.mDollar.resetChild();
                GameMainActivity.this.mHint.resetChild();
                //

                MTMap.reRandomMT();
                GameMainActivity.this.mManagerItemPikachu.addItem();
                try {
                    Thread.sleep(1000L);
                    GameMainActivity.this.mTextLoading.hideTextLoading();
                    GameMainActivity.this.mDollar.reset();
                    GameMainActivity.this.mManagerItemPikachu.showItemEffect(LevelManager.levelCurrent % 8, 1);
                    GameMainActivity.this.mProgressBar.setTotalTime(LevelManager.getTimeLevel());
                    GameMainActivity.this.mProgressBar.start();
                    GameMainActivity.this.sortChildren();
                    OnclickChecker.searchHint();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void resumeGame() {
        this.mProgressBar.setPause(false);
    }

    public void setHint(final int n, final int n2, final int n3, final int n4) {
        this.mHint.setHint(n, n2, n3, n4);
    }

    public void showDialogCompleted() {
        GameMainActivity.this.mSound.playFinish();
        final int timeEnd = this.mProgressBar.getTimeEnd();
        this.mProgressBar.setStop(true);
        GameMainActivity.mPlay.total_dollar = GameMainActivity.mPlay.dollar_current;
        new Handler().postDelayed((Runnable) new Runnable() {
            @Override
            public void run() {
                starByLevel = LevelManager.getStarByLevel(LevelManager.levelCurrent, timeEnd);
                moneyBonus = 10 * LevelManager.levelCurrent;
                switch (starByLevel) {
                    case 1: {
                        moneyBonus += 100;
                        break;
                    }
                    case 2: {
                        moneyBonus += 150;
                        break;
                    }
                    case 3: {
                        moneyBonus += 300;
                        break;
                    }
                }
                GameMainActivity.mPlay.total_dollar += moneyBonus;
                GameMainActivity.mPlay.dollar_current = GameMainActivity.mPlay.total_dollar;
                GameMainActivity.mPlay.mDollar.updateDollar();
                saveGameStates();

                if (LevelManager.levelCurrent > LevelManager.totalLevel) {
                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_WINNER;
                    showINTAd();
                } else {
                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_COMPLETE;
                    showINTAd();
                }
            }
        }, 800L);

        /*if (!IPreferences.getInstance((Context) this).getRateStatus() && LevelManager.levelCurrent == 1) {
            IRateDialog.showRateDialog(this, true, null,
                    new IButtonListener() {
                @Override
                public void onPress(final Dialog dialog) {
                    GameMainActivity.this.resumeGame();
                }
            },
                    new IButtonListener() {
                @Override
                public void onPress(final Dialog dialog) {
                    try {
                        IPreferences.getInstance((Context) GameMainActivity.instance).saveRateStatus(true);
                        try {
                            GameMainActivity.instance.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + GameMainActivity.instance.getPackageName())));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            GameMainActivity.instance.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + GameMainActivity.instance.getPackageName())));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, true
            );
        }*/

        LevelManager.levelCurrent += 1;
    }

    public void sortChildren() {
        try {
            this.mScene.sortChildren();
        } catch (Exception ex) {
        }
    }

    public void startGame() {
        OnclickChecker.isOnClickItem = true;
        this.GameOver = false;
        this.mProgressBar.setTotalTime(LevelManager.getTimeLevel());
        this.mProgressBar.start();
        this.mManagerItemPikachu.showItemEffect(LevelManager.levelCurrent % 8, 1);
    }

    public void swapItem() {
        this.mManagerItemPikachu.swapItem();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (IPreferences.getInstance((Context) this).getRateStatus() && isHasClickYesRateDialogBefore == true) {

            try {
                isHasClickYesRateDialogBefore = false;
                //GameMainActivity.mPlay.dollar_current = totalScore;
                GameMainActivity.mPlay.mDollar.updateDollar();
                //mDollar.reset();
                GameMainActivity.this.isThereAnyPopupDLG = false;
                GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                GameMainActivity.this.mSound.playClick();
                GameMainActivity.mPlay.musicBackground.resume();
                GameMainActivity.mPlay.resetGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
//            if (!isBackfromAdsINT) {
//                if (isGamePaused) {
//                    isGamePaused = false;
//                } else {
//                    isGamePaused = true;
//                }
//            }

//            isBackfromAdsINT = false;


            if (isOnPauseBefore) {
                isOnPauseBefore = false;
                showLevelContextDialog();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            isOnPauseBefore = true;
            GameMainActivity.mPlay.pauseGameWithoutDlg2();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showINTAd() {
//        GameMainActivity.this.isLoadLevelAd = false;

        if (GameMainActivity.this.interstitial_ad_google != null && com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences.getInstance(mContext).getIAdShownStatus()) {
            Interstitial_Ad_google.setInterface(new Interstitial_Ad_google.Onclick() {
                @Override
                public void clicked() {
                    showLevelContextDialog();
                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                }
            });
            interstitial_ad_google.show_method();
        } else {
            showLevelContextDialog();
            GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
        }

    }

    public void showLevelContextDialog() {
        if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_DEFAULT) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {
                GameMainActivity.this.isThereAnyPopupDLG = true;
                GameMainActivity.mPlay.pauseGame();
            }
        } else if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_COMPLETE) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {
                GameMainActivity.this.isThereAnyPopupDLG = true;

                IWinnerDialog.showShareDialog(GameMainActivity.mPlay, GameMainActivity.mPlay.total_dollar, starByLevel, moneyBonus, null, null,
                        new IButtonListener() {
                            @Override
                            public void onPress(final Dialog dialog) {
                                try {
                                    GameMainActivity.this.isThereAnyPopupDLG = false;
                                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                    GameMainActivity.this.mSound.playClick();
                                    GameMainActivity.mPlay.musicBackground.resume();
                                    GameMainActivity.mPlay.resetGame();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            }
        } else if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_FAIL) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {
                GameMainActivity.this.isThereAnyPopupDLG = true;
                ILoserDialog.showShareDialog(GameMainActivity.mPlay, GameMainActivity.mPlay.dollar_current, null,
                        new IButtonListener() {
                            @Override
                            public void onPress(final Dialog dialog) {
                                GameMainActivity.this.isThereAnyPopupDLG = false;
                                GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                GameMainActivity.this.mSound.playClick();
                                GameMainActivity.mPlay.musicBackground.resume();
                                GameMainActivity.mPlay.dollar_current = GameMainActivity.mPlay.total_dollar;
                                getGameStates();

                                GameMainActivity.mPlay.resetGame();
                            }
                        },
                        null
                );
            }
        } else if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_WINNER) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {
                GameMainActivity.this.isThereAnyPopupDLG = true;
                ILevelWinerDialog.showShareDialog(GameMainActivity.mPlay, GameMainActivity.mPlay.total_dollar, starByLevel, moneyBonus, null,
                        new IButtonListener() {
                            @Override
                            public void onPress(final Dialog dialog) {
                                try {
                                    GameMainActivity.this.isThereAnyPopupDLG = false;
                                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                    GameMainActivity.this.mSound.playClick();
                                    GameMainActivity.mPlay.musicBackground.resume();
                                    LevelManager.levelCurrent = 1;
                                    GameMainActivity.mPlay.dollar_current = 0;
                                    GameMainActivity.mPlay.total_dollar = 0;
                                    GameMainActivity.mPlay.resetGame();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new IButtonListener() {
                            @Override
                            public void onPress(final Dialog dialog) {
                                try {
                                    GameMainActivity.this.isThereAnyPopupDLG = false;
                                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                    GameMainActivity.this.mSound.playClick();
                                    GameMainActivity.mPlay.musicBackground.resume();
                                    LevelManager.levelCurrent = 1;
                                    GameMainActivity.mPlay.dollar_current = 0;
                                    GameMainActivity.mPlay.total_dollar = 0;
                                    GameMainActivity.mPlay.resetGame();

                                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                    sharingIntent.setType("text/plain");
                                    sharingIntent.putExtra(Intent.EXTRA_TEXT, ("https://play.google.com/store/apps/details?id=" + GameMainActivity.mPlay.getPackageName()));
                                    PackageManager packManager = GameMainActivity.mPlay.getPackageManager();
                                    List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(sharingIntent, PackageManager.MATCH_DEFAULT_ONLY);

                                    boolean resolved = false;
                                    for (ResolveInfo resolveInfo : resolvedInfoList) {
                                        if (resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")) {
                                            sharingIntent.setClassName(
                                                    resolveInfo.activityInfo.packageName,
                                                    resolveInfo.activityInfo.name);
                                            resolved = true;
                                            break;
                                        }
                                    }
                                    if (resolved) {
                                        GameMainActivity.mPlay.startActivity(sharingIntent);
                                    } else {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(GameMainActivity.mPlay);
                                        alert.setTitle("Warning");
                                        alert.setMessage("Facebook not found on your phone :((. Install it and try again!");
                                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();

                                            }
                                        });
                                        alert.show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
            }
        }
    }

    public boolean getGameStates() {
        try {
            LevelManager.levelCurrent = IPreferences.getInstance((Context) this).getCurLevel();
            GameMainActivity.mPlay.dollar_current = IPreferences.getInstance((Context) this).getCurrent_dollar();
            GameMainActivity.mPlay.total_dollar = IPreferences.getInstance((Context) this).getTotal_dollar();
            GameMainActivity.mPlay.starByLevel = IPreferences.getInstance((Context) this).getStar();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean saveGameStates() {
        try {
            IPreferences.getInstance((Context) this).saveCurLevel(LevelManager.levelCurrent);
            IPreferences.getInstance((Context) this).saveCurrent_dollar(GameMainActivity.mPlay.dollar_current);
            IPreferences.getInstance((Context) this).saveTotal_dollar(GameMainActivity.mPlay.total_dollar);
            IPreferences.getInstance((Context) this).saveStar(GameMainActivity.mPlay.starByLevel);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private void initView() {

        addAdsItemsFromJson();
        createAdsView();
    }

    private void createAdsView() {
        AdsAdapter adsAdapter = new AdsAdapter(getApplicationContext(), mAdsItems);
        recyclerView.setAdapter(adsAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void showRateUsDialog(Context context) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void setTransparentStatusBar() {
        // Set Transparent Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void addAdsItemsFromJson() {
        mAdsItems = new ArrayList<>();

        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray itemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < itemsJsonArray.length(); ++i) {

                JSONObject itemObject = itemsJsonArray.getJSONObject(i);

                String adsAppIcon = itemObject.getString("app_icon");
                String adsAppItemName = itemObject.getString("app_name");
                String adsAppItemDescription = itemObject.getString("app_description");
                String adsAppPackageName = itemObject.getString("package_name");

                AdsItemModel adsItemModel = new AdsItemModel(adsAppIcon, adsAppItemName, adsAppItemDescription, adsAppPackageName);
                mAdsItems.add(adsItemModel);
            }
        } catch (IOException | JSONException exception) {
            Log.e(AdsActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
    }

    private String readJsonDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.ads_apps);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }


    public void updateAppDialog() {

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this,
                R.style.dialog_pause);
        ViewGroup viewGroup = GameMainActivity.this.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_update_app, viewGroup, false);
        builder.setView(dialogView);

        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCancelable(true);
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                mSound.playClick();

                try {

                    Pereferences.set_dialog(1);
                    com.funkyland.tileconnecttravel.OldVersion.IPreferences.getInstance(mContext).saveRateStatus(true);

                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.funkyland.tileconnecttravel"));
                    startActivity(viewIntent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Unable to Connect Try Again...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });

        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

}
