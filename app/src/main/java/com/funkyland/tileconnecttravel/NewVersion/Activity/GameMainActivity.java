package com.funkyland.tileconnecttravel.NewVersion.Activity;

import static com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleRewardedAdMoreSeconds.Rewardedfail;
import static com.funkyland.tileconnecttravel.NewVersion.Button.HintButton.hintVideoPlay;
import static com.funkyland.tileconnecttravel.NewVersion.Button.OneKindButton.OneKindVideoPlay;
import static com.funkyland.tileconnecttravel.NewVersion.Button.SuffleButton.SuffleVideoPlay;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.COUNTRY_ACTIVITY;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.Country_Africa_path;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.Country_America_path;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.Country_Asia_path;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.Country_Australia_path;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.Country_Europe_path;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_ATS;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_ATW;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.SPARKELA_ACTIVITY;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.theme_path;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.ui_flags;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleAdBanner;
import com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleRewardedAdHint;
import com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleRewardedAdMoreSeconds;
import com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleRewardedAdRMOKind;
import com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleRewardedAdShuffle;
import com.funkyland.tileconnecttravel.NewVersion.Ads.Interstitial_Ad_google;
import com.funkyland.tileconnecttravel.NewVersion.Button.AdButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.DollarButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.HeartButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.HintButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.OneKindButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.PauseButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.ProgressBar;
import com.funkyland.tileconnecttravel.NewVersion.Button.PurchaseButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.SuffleButton;
import com.funkyland.tileconnecttravel.NewVersion.Button.TextLoadingSprite;
import com.funkyland.tileconnecttravel.NewVersion.Button.ThemeButton;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPurchaseOneCard;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPurchaseSuccessfully;
import com.funkyland.tileconnecttravel.NewVersion.Generators.BackgroundImage;
import com.funkyland.tileconnecttravel.NewVersion.Generators.DrawLineSprite;
import com.funkyland.tileconnecttravel.NewVersion.Generators.GameConfiguration;
import com.funkyland.tileconnecttravel.NewVersion.Generators.HintSprite;
import com.funkyland.tileconnecttravel.NewVersion.Generators.ILogger;
import com.funkyland.tileconnecttravel.NewVersion.Generators.ItemsManager;
import com.funkyland.tileconnecttravel.NewVersion.Generators.LevelManager;
import com.funkyland.tileconnecttravel.NewVersion.Generators.MTMap;
import com.funkyland.tileconnecttravel.NewVersion.Generators.OnclickChecker;
import com.funkyland.tileconnecttravel.NewVersion.InAppPurchase.GoogleBilling;
import com.funkyland.tileconnecttravel.NewVersion.Services.ForegroundService;
import com.funkyland.tileconnecttravel.NewVersion.Services.MusicBackground;
import com.funkyland.tileconnecttravel.NewVersion.Services.SoundController;
import com.funkyland.tileconnecttravel.NewVersion.Utils.DialogAction;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IUtil;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;


public class GameMainActivity extends GameBaseActivity {

    public static Context instance = null;
    public static GameMainActivity mPlay;
    // Preferences
    public static int dollar_current = 0;
//    public static int scoreLevelWise = 0;

    public static SoundController mSound;
    //Unused
    public static boolean isLoadLevelAd = false;
    public static Handler mHandler_loadINTAd;
    public static boolean VideoPlay110Successfully = false;

    private static DialogPurchaseOneCard dialogPurchaseOneCard = null;
    //back Ground
    public BackgroundImage mBackground;
    //Extra
    public boolean isThereAnyPopupDLG = false;
    public int moneyBonus = 0;
    public int LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
    public boolean isOnPauseBefore = false;
    public ItemsManager mManagerItemPikachu;
    public DrawLineSprite mDrawLine;
    //    public int total_dollar = 0;
    public int starByLevel = 0;
    //Components
    public TextLoadingSprite mTextLoading;
    public HintSprite mHint;
    public PauseButton mButtonPause;
    public HintButton mButtonHint;
    public DollarButton mDollar;
    public ProgressBar mProgressBar;
    public HeartButton mButtonheart;
    public SuffleButton mButtonSuffle;
    public OneKindButton mButtonOneKind;
    public PurchaseButton mButtonPurchase;
    public ThemeButton mThemeButton;
    public AdButton mButtonAd;
    //Game Status
    public boolean GameOver;
    //Sound Controll
    public MusicBackground musicBackground;
    //Handler
    public Handler mHandler_gameover;
    public boolean isStopgame = true;
    public boolean isShowPuaseDialogInOnresume = true;
    public ImageView btn_item_one, btn_item_two, btn_item_three;
    public ImageView btn_item_four, btn_item_five, btn_item_six;
    FrameLayout frame;
    RelativeLayout toolbar;
    AlertDialog alertDialog_gameover;
    IPreferences mPreferences;
    AlertDialog alertDialog_pause;
    boolean isBackFromThePauseGameNewButton = false;
    //5
    Interstitial_Ad_google interstitial_ad_google;
    int totalScore;
    AlertDialog alertDialog_110 = null;
    AlertDialog alertDialog_showTheme;
    AlertDialog alertDialog_purchase_success;
    AlertDialog alertDialog_VideoPlaySuccess;
    //1
    AlertDialog dialog_before_watchVideo_Onekind;
    //2
    AlertDialog dialog_before_watchVideo_Suffle;
    //3
    AlertDialog dialog_before_watchVideo_hint;
    boolean isHasClickYesRateDialogBefore = false;
    private String TAG = getClass().getSimpleName();
    private boolean isGamePaused = false;
    private boolean isBackfromAdsINT = false;
    private boolean isShowAdDialogToadd120Second = true;
    private int Score, Level = 1;

    public static GoogleBilling googleBilling = null;

    public GameMainActivity() {
        super();
        this.GameOver = false;
        this.mHandler_gameover = new Handler() {
            public void handleMessage(final Message message) {
                super.handleMessage(message);
                GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_FAIL;
                showINTAd();
            }
        };

        this.mHandler_loadINTAd = new Handler() {
            public void handleMessage(final Message message) {
                super.handleMessage(message);
                try {
                    GameMainActivity.this.isLoadLevelAd = true;
                } catch (Exception eee) {
                    eee.printStackTrace();
                }
            }
        };

    }

    public void gameOver() {

        if (isShowAdDialogToadd120Second) {
            GameMainActivity.mPlay.runOnUiThread(new Runnable() {
                public void run() {
                    Log.d(TAG, "FirstTime gameOver : ");
                    ShowDialogForWatchAdd();
                }
            });

        } else {
            GameMainActivity.mPlay.runOnUiThread(new Runnable() {
                public void run() {
                    Log.d(TAG, "SecondTime gameOver : ");
                    GameMainActivity.this.mSound.playGameOver();
                    showGameOverDialog();
                }
            });
        }


    }

    public void showGameOverDialog() {

        if (IPreferences.getInstance(activity).getTotal_dollar() < GameMainActivity.mPlay.dollar_current) {
            IPreferences.getInstance(activity).saveTotal_dollar(GameMainActivity.mPlay.dollar_current);
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_game_over, viewGroup, false);
        builder.setView(dialogView);

        alertDialog_gameover = builder.create();
        alertDialog_gameover.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog_gameover.setCancelable(false);
        alertDialog_gameover.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        alertDialog_gameover.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog_gameover.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog_gameover.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        LinearLayout ad_googleBanner = dialogView.findViewById(R.id.ad_googleBanner);
        new GoogleAdBanner(activity, ad_googleBanner).googleBanner();

        TextView txt_level = dialogView.findViewById(R.id.txt_level);
        txt_level.setText("" + LevelManager.levelCurrent);

        TextView txt_score = dialogView.findViewById(R.id.txt_score);
        txt_score.setText("" + GameMainActivity.mPlay.dollar_current);

        TextView txt_total_doller = dialogView.findViewById(R.id.txt_total_doller);
        txt_total_doller.setText(getString(R.string.best_score) + " " + IPreferences.getInstance(activity).getTotal_dollar());


        TextView btn_next_level = dialogView.findViewById(R.id.btn_next_level);
        btn_next_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (IUtil.isNetworkConnected(activity) && IPreferences.getInstance(activity).getIAdShownStatus()) {

                        Interstitial_Ad_google.setInterface(new Interstitial_Ad_google.Onclick() {
                            @Override
                            public void clicked() {
                                alertDialog_gameover.dismiss();

                                DialogPurchaseOneCard dialogPurchaseOneCard = new DialogPurchaseOneCard(activity);
                                dialogPurchaseOneCard.setDialogAction(new DialogAction() {
                                    @Override
                                    public void clickCloseBtn(Boolean isBuy, String packId) {
                                        if (isBuy) {
                                            googleBilling.setPaymentListner(new GoogleBilling.PaymentListner() {
                                                @Override
                                                public void FlowListner(Boolean paymentsucsess, String pack) {
                                                    if (paymentsucsess) {

                                                        DialogPurchaseSuccessfully dialogPurchaseSuccessfully = new DialogPurchaseSuccessfully(activity);
                                                        dialogPurchaseSuccessfully.setDialogClick(new DialogPurchaseSuccessfully.DialogClicks() {
                                                            @Override
                                                            public void click(Boolean isClick) {
                                                                setNextGameAfterGameOver();
                                                            }
                                                        });
                                                        dialogPurchaseSuccessfully.Init(pack);
                                                    } else {
                                                        setNextGameAfterGameOver();
                                                    }
                                                }
                                            });
                                            googleBilling.OpenPaymentFlow(packId);
                                        } else {
                                            setNextGameAfterGameOver();
                                        }
                                    }
                                });
                                dialogPurchaseOneCard.show();

                            }
                        });
                        interstitial_ad_google.show_method();
                    } else {
                        alertDialog_gameover.dismiss();

                        DialogPurchaseOneCard dialogPurchaseOneCard = new DialogPurchaseOneCard(activity);
                        dialogPurchaseOneCard.setDialogAction(new DialogAction() {
                            @Override
                            public void clickCloseBtn(Boolean isBuy, String packId) {
                                if (isBuy) {
                                    googleBilling.setPaymentListner(new GoogleBilling.PaymentListner() {
                                        @Override
                                        public void FlowListner(Boolean paymentsucsess, String pack) {
                                            if (paymentsucsess) {

                                                DialogPurchaseSuccessfully dialogPurchaseSuccessfully = new DialogPurchaseSuccessfully(activity);
                                                dialogPurchaseSuccessfully.setDialogClick(new DialogPurchaseSuccessfully.DialogClicks() {
                                                    @Override
                                                    public void click(Boolean isClick) {
                                                        setNextGameAfterGameOver();
                                                    }
                                                });
                                                dialogPurchaseSuccessfully.Init(pack);
                                            } else {
                                                setNextGameAfterGameOver();
                                            }
                                        }
                                    });
                                    googleBilling.OpenPaymentFlow(packId);
                                } else {
                                    setNextGameAfterGameOver();
                                }
                            }
                        });
                        dialogPurchaseOneCard.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (!alertDialog_gameover.isShowing()) {
            alertDialog_gameover.show();
        }

    }

    private void setNextGameAfterGameOver() {

//        if (Pereferences.get_gameMode().equals(GAME_MODE_ATW) || Pereferences.get_gameMode().equals(GAME_MODE_ATS)) {
//            dollar_current = IPreferences.getInstance(activity).getCurTotalScore();
//            mDollar.updateDollar();
//            mHandler_gameover.sendEmptyMessage(0);
//        } else {


        mButtonOneKind.setOneKindRemoveText();
        mButtonHint.setHintText();
        mButtonSuffle.setSuffleText();


        mDollar.reset();
        IPreferences.getInstance(activity).saveCurLevel(1);
        IPreferences.getInstance(activity).saveCurTotalScore(0);
        LevelManager.levelCurrent = 1;
        mDollar.updateDollar();
        mHandler_gameover.sendEmptyMessage(0);
//        }


    }

    public void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                GameMainActivity.this.mHint.onLoadResources();

//                MTMap.getTotalRowColumn(GameMainActivity.this.mDollar.getStartX(), GameMainActivity.this.mDollar.getEndY(), (int) GameMainActivity.this.mButtonHint.getStartX());
//                MTMap.getTotalRowColumn(5, 166, (int) 150.0f);
                MTMap.getTotalRowColumn(toolbar.getScrollX(), toolbar.getScrollY(), GameConfiguration.SCREENWIDTH);
                GameMainActivity.this.mManagerItemPikachu.setScene(GameMainActivity.this.mScene);
                if (!(GameMainActivity.this.mManagerItemPikachu.list_itemPikachu != null
                        && GameMainActivity.this.mManagerItemPikachu.list_itemPikachu.size() > 0)) {
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
//        Pereferences.set_scoreLevelWise(scoreLevelWise);
        GameMainActivity.this.pauseGameWithoutDlg();
        GameMainActivity.this.mSound.playClick();
        pauseGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        HideNavigation(this);
        (this.musicBackground = new MusicBackground()).loadMusic((Context) this);
        this.musicBackground.play();

        OnclickChecker.isOnClickItem = true;
        GameMainActivity.mPlay = this;


        if (googleBilling == null) {
            googleBilling = new GoogleBilling(activity);
            googleBilling.Initialize();
            googleBilling.connectTogoogleBilling();
        }

        mPreferences = IPreferences.getInstance((Context) this);
        getGameStates();

        toolbar = findViewById(R.id.toolbar);

        (this.mBackground = new BackgroundImage()).onCreate(this.mContext, this.mEngine, this.mCamera);

        (this.mTextLoading = new TextLoadingSprite()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);
        (this.mButtonPause = new PauseButton()).onCreate(this.activity, this.mEngine, this.mCamera);
        (this.mButtonHint = new HintButton()).onCreate(GameMainActivity.mPlay, this.mEngine, this.mCamera);
        (this.mDollar = new DollarButton()).onCreate(this.activity, this.mEngine, this.mCamera);
        (this.mProgressBar = new ProgressBar()).onCreate(this.activity, this.mEngine, this.mCamera);
        (this.mButtonheart = new HeartButton()).onCreate(this.activity, this.mContext, this.mEngine, this.mCamera);
        (this.mButtonSuffle = new SuffleButton()).onCreate(this.activity, this.mContext, this.mEngine, this.mCamera);
        (this.mButtonOneKind = new OneKindButton()).onCreate(this.activity, this.mContext, this.mEngine, this.mCamera);
        (this.mButtonPurchase = new PurchaseButton()).onCreate(this.activity, this.mContext, this.mEngine, this.mCamera);
        (this.mThemeButton = new ThemeButton()).onCreate(this.activity, this.mContext, this.mEngine, this.mCamera);
        (this.mButtonAd = new AdButton()).onCreate(this.activity, this.mContext, this.mEngine, this.mCamera);

        (this.mHint = new HintSprite()).onCreate(this.mContext, this.mEngine, this.mCamera);
        this.mManagerItemPikachu = new ItemsManager(this.mContext, this.mEngine, this.mCamera);
        (this.mDrawLine = new DrawLineSprite()).onCreate((Context) GameMainActivity.mPlay, this.mEngine, this.mCamera);

        (GameMainActivity.this.mSound = new SoundController()).loadSound((Context) this);
        GameMainActivity.this.isThereAnyPopupDLG = false;

        Interstitial_Ad_Google();


//        Bundle extras = getIntent().getExtras();
//        if (extras == null) {
//
//        } else {
//            try {
//                dollar_current = extras.getInt("Score");
//                LevelManager.levelCurrent = extras.getInt("Level");
//                Log.d("dollar_current 374:", LevelManager.levelCurrent + " || " + dollar_current);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        Pereferences.set_scoreLevelWise(Pereferences.get_scoreLevelWise());
//        dollar_current = dollar_current - Pereferences.get_scoreLevelWise();
//
//
//        if (dollar_current < 0) {
//            Log.d("dollar_current 384:", "1");
//            mDollar.reset();
//            mDollar.updateDollar();
//            scoreLevelWise = 0;
//
//            Pereferences.set_scoreLevelWise(0);
//
//
//        } else {
//            Log.d("dollar_current 384:", "2");
//            mDollar.updateDollar();
//            scoreLevelWise = 0;
//
//            Pereferences.set_scoreLevelWise(0);
//
//
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        TextView txt_level = findViewById(R.id.txt_level);
//        String currentString = txt_level.getText().toString().trim();
//        String[] separated = currentString.split("SCORE:");
//
//        Pereferences.set_scoreLevelWise(scoreLevelWise);
//        Pereferences.set_gameLevel(LevelManager.levelCurrent);
//        Pereferences.set_gamescore(Integer.parseInt(separated[1]));
//
//        Log.d("Destroy_scoreLevelWise1", scoreLevelWise+"=========");
//        Log.d("Destroy_scoreLevelWise2", LevelManager.levelCurrent+"");
//        Log.d("Destroy_scoreLevelWise3", Integer.parseInt(separated[1])+"");


        ILogger.LogInfo("onDestroy");
        try {
            this.mProgressBar.onDestroy();
            this.mHint.onDestroy();
            this.musicBackground.release();
            stopService();
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


        TextView txt_level = findViewById(R.id.txt_level);
        String currentString = txt_level.getText().toString().trim();
        String[] separated = currentString.split(":");

//        if (Pereferences.get_gameType().equals("Easy")) {
//            Pereferences.set_scoreLevelWiseEasy(scoreLevelWise);
//            Pereferences.set_gameLevelEasy(LevelManager.levelCurrent);
//            Pereferences.set_gamescoreEasy(Integer.parseInt(separated[1]));
//        } else {
//        Pereferences.set_scoreLevelWise(scoreLevelWise);
//        Pereferences.set_gameLevel(LevelManager.levelCurrent);
//        Pereferences.set_gamescore(Integer.parseInt(separated[1]));
//        }


        if (isStopgame) {

            isStopgame = false;
            Log.d("onStop", "onStop");
            isBackfromAdsINT = false;
            if (isShowPuaseDialogInOnresume) {
                if (isOnPauseBefore) {
                    isOnPauseBefore = false;
                    showLevelContextDialog();
                }
            }
        }


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

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_pause);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_pause, viewGroup, false);
        builder.setView(dialogView);

        alertDialog_pause = builder.create();
        alertDialog_pause.getWindow().getDecorView().setSystemUiVisibility(ui_flags);

        LinearLayout ad_googleBanner = dialogView.findViewById(R.id.ad_googleBanner);
        new GoogleAdBanner(activity, ad_googleBanner).googleBanner();

        TextView btn_resume = dialogView.findViewById(R.id.btn_resume);
        TextView btn_new_game = dialogView.findViewById(R.id.btn_new_game);
        TextView btn_how_to_play = dialogView.findViewById(R.id.btn_how_to_play);
        btn_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mButtonheart.setHeartText();

                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                    GameMainActivity.this.isThereAnyPopupDLG = false;
                    GameMainActivity.this.mSound.playClick();
                    GameMainActivity.mPlay.musicBackground.resume();
                    GameMainActivity.mPlay.resumeGame();
                    alertDialog_pause.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                isBackFromThePauseGameNewButton = true;

                if (IUtil.isNetworkConnected(activity) && IPreferences.getInstance(activity).getIAdShownStatus()) {

                    Interstitial_Ad_google.setInterface(new Interstitial_Ad_google.Onclick() {
                        @Override
                        public void clicked() {

                            mButtonheart.setHeartText();
                            isShowAdDialogToadd120Second = true;
                            alertDialog_pause.dismiss();
                            GameMainActivity.mPlay.isShowPuaseDialogInOnresume = false;
                            GameMainActivity.this.isThereAnyPopupDLG = false;
                            GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                            GameMainActivity.this.mSound.playClick();
                            GameMainActivity.mPlay.musicBackground.resume();
                            //GameMainActivity.mPlay.dollar_current = GameMainActivity.mPlay.total_dollar;
                            //GameMainActivity.mPlay.dollar_current = 0;
                            IPreferences.getInstance(activity).saveCurLevel(1);
                            LevelManager.levelCurrent = 1;
                            mDollar.reset();
                            getGameStates();
                            GameMainActivity.mPlay.resetGame();

                        }
                    });
                    interstitial_ad_google.show_method();
                } else {
                    mButtonheart.setHeartText();
                    isShowAdDialogToadd120Second = true;
                    alertDialog_pause.dismiss();
                    GameMainActivity.this.isThereAnyPopupDLG = false;
                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                    GameMainActivity.this.mSound.playClick();
                    GameMainActivity.mPlay.musicBackground.resume();
                    //GameMainActivity.mPlay.dollar_current = GameMainActivity.mPlay.total_dollar;
                    //GameMainActivity.mPlay.dollar_current = 0;
                    IPreferences.getInstance(activity).saveCurLevel(1);
                    LevelManager.levelCurrent = 1;
                    mDollar.reset();
                    getGameStates();
                    GameMainActivity.mPlay.resetGame();
                }


            */
            }
        });

        ImageView btn_home = dialogView.findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog_pause.dismiss();
                GameMainActivity.this.mSound.playClick();
                Intent i = null;

                if (getIntent().getBooleanExtra(COUNTRY_ACTIVITY, false)) {
                    i = new Intent(GameMainActivity.this, CountryActivity.class);
                    i.putExtra("khand", getIntent().getStringExtra("khand"));
                } else if (getIntent().getBooleanExtra(SPARKELA_ACTIVITY, false)) {
                    i = new Intent(GameMainActivity.this, SparkelaActivity.class);
                } else {
                    i = new Intent(GameMainActivity.this, StartActivity.class);
                }
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        ImageView btn_sound = dialogView.findViewById(R.id.btn_sound);
        if (IPreferences.getInstance(mContext).getButtonSound()) {
            btn_sound.setImageResource(R.drawable.ic_btn_music_on);
        } else {
            btn_sound.setImageResource(R.drawable.ic_btn_music_off);
        }

        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameMainActivity.this.mSound.playClick();
                if (IPreferences.getInstance(mContext).getButtonSound()) {
                    IPreferences.getInstance(mContext).setButtonSound(false);
                    btn_sound.setImageResource(R.drawable.ic_btn_music_off);
                } else {
                    IPreferences.getInstance(mContext).setButtonSound(true);
                    btn_sound.setImageResource(R.drawable.ic_btn_music_on);
                }
            }
        });

        ImageView btn_bg_sound = dialogView.findViewById(R.id.btn_bg_sound);
        if (IPreferences.getInstance(mContext).getBgSound()) {
            btn_bg_sound.setImageResource(R.drawable.ic_bg_music_on);
        } else {
            btn_bg_sound.setImageResource(R.drawable.ic_bg_music_off);
        }

        btn_bg_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameMainActivity.this.mSound.playClick();
                if (IPreferences.getInstance(mContext).getBgSound()) {
                    IPreferences.getInstance(mContext).setBgSound(false);
                    btn_bg_sound.setImageResource(R.drawable.ic_bg_music_off);
                    musicBackground.pause();

                } else {
                    IPreferences.getInstance(mContext).setBgSound(true);
                    btn_bg_sound.setImageResource(R.drawable.ic_bg_music_on);
                    GameMainActivity.mPlay.musicBackground.resume();
                }
            }
        });

        if (!alertDialog_pause.isShowing()) {
            alertDialog_pause.show();
        }
        alertDialog_pause.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

    }

    public void Interstitial_Ad_Google() {
        interstitial_ad_google = new Interstitial_Ad_google(GameMainActivity.this);
    }

    public void removeItem(final int n, final int n2) {
        try {
            if (this.mManagerItemPikachu.removeItem(n, n2) <= 18) {
                if (!GameMainActivity.this.isLoadLevelAd) {
                    try {
                        this.mHandler_loadINTAd.sendEmptyMessage(0);
                    } catch (Exception exxxx) {
                        exxxx.printStackTrace();
                    }
                }
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

                GameMainActivity.this.mHint.resetChild();

                MTMap.reRandomMT();
                GameMainActivity.this.mManagerItemPikachu.addItem();
                try {
                    Thread.sleep(2000L);
                    GameMainActivity.this.mTextLoading.hideTextLoading();
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

                totalScore = GameMainActivity.mPlay.dollar_current + moneyBonus;
                if (IPreferences.getInstance(activity).getTotal_dollar() < totalScore) {
                    IPreferences.getInstance(activity).saveTotal_dollar(totalScore);
                }

                if (IPreferences.getInstance(activity).getTotal_level() < LevelManager.levelCurrent) {
                    IPreferences.getInstance(activity).saveTotal_level(LevelManager.levelCurrent);
                }

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

        //GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;

        if (googleBilling == null) {
            googleBilling = new GoogleBilling(activity);
            googleBilling.Initialize();
            googleBilling.connectTogoogleBilling();
        }

        if (com.funkyland.tileconnecttravel.OldVersion.IPreferences.getInstance((Context) this).getRateStatus() && isHasClickYesRateDialogBefore == true) {
            isHasClickYesRateDialogBefore = false;
            try {
                GameMainActivity.mPlay.dollar_current = totalScore;
                GameMainActivity.mPlay.mDollar.updateDollar();
                //mDollar.reset();
                GameMainActivity.this.isThereAnyPopupDLG = false;
                GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                GameMainActivity.this.mSound.playClick();
                GameMainActivity.mPlay.musicBackground.resume();
                GameMainActivity.mPlay.resetGame();

                showLevelContextDialog();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {

            stopService();

            if (!isBackfromAdsINT) {

                if (isGamePaused) {
                    isGamePaused = false;
                } else {
                    isGamePaused = true;
                }

            }


            isBackfromAdsINT = false;
            if (isShowPuaseDialogInOnresume) {
                if (isOnPauseBefore) {
                    isOnPauseBefore = false;

                    /*if(isBackFromThePauseGameNewButton == true){
                        isBackFromThePauseGameNewButton = false;
                    }else {
                        showLevelContextDialog();
                    }*/
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            startService();
            isShowPuaseDialogInOnresume = true;
            isOnPauseBefore = true;

            GameMainActivity.mPlay.pauseGameWithoutDlg2();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showINTAd() {
        GameMainActivity.this.isLoadLevelAd = false;
        showLevelContextDialog();
        GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
    }

    public void showLevelContextDialog() {
        if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_DEFAULT) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {
                GameMainActivity.this.isThereAnyPopupDLG = true;
                GameMainActivity.mPlay.pauseGame();
                isStopgame = true;
            }
        } else if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_COMPLETE) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {

                GameMainActivity.this.isThereAnyPopupDLG = true;

                isShowAdDialogToadd120Second = true;

//                scoreLevelWise = 0;
//                Pereferences.set_scoreLevelWise(0);

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_level_complete, viewGroup, false);
                builder.setView(dialogView);

                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                alertDialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                alertDialog.setCancelable(false);

                TextView txt_unlock_level = dialogView.findViewById(R.id.txt_unlock_level);


                if (Pereferences.get_gameMode().equals(GAME_MODE_ATW) || Pereferences.get_gameMode().equals(GAME_MODE_ATS)) {
                    if (Pereferences.get_gameMode().equals(GAME_MODE_ATW)) {
                        if (LevelManager.levelCurrent == 15) {

                            if (Arrays.asList(Country_Asia_path).contains(Pereferences.get_gameType_InSubType())) {
                                setTextInNextLevel(txt_unlock_level, Country_Asia_path);
                            } else if (Arrays.asList(Country_Europe_path).contains(Pereferences.get_gameType_InSubType())) {
                                setTextInNextLevel(txt_unlock_level, Country_Europe_path);
                            } else if (Arrays.asList(Country_America_path).contains(Pereferences.get_gameType_InSubType())) {
                                setTextInNextLevel(txt_unlock_level, Country_America_path);
                            } else if (Arrays.asList(Country_Australia_path).contains(Pereferences.get_gameType_InSubType())) {
                                setTextInNextLevel(txt_unlock_level, Country_Australia_path);
                            } else if (Arrays.asList(Country_Africa_path).contains(Pereferences.get_gameType_InSubType())) {
                                setTextInNextLevel(txt_unlock_level, Country_Africa_path);
                            }
                        } else {
                            txt_unlock_level.setVisibility(View.GONE);
                        }
                    }
                    if (Pereferences.get_gameMode().equals(GAME_MODE_ATS)) {
                        if (LevelManager.levelCurrent == 15) {
                            Pereferences.set_LockTypeStatus(Pereferences.get_gameType_InSubType(), true);
                            setTextInNextLevel(txt_unlock_level, theme_path);
                        } else {
                            txt_unlock_level.setVisibility(View.GONE);
                        }
                    }
                } else {
                    txt_unlock_level.setVisibility(View.GONE);
                }


                TextView txt_level = dialogView.findViewById(R.id.txt_level);
                txt_level.setText("" + Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1));

                TextView txt_score = dialogView.findViewById(R.id.txt_score);
                txt_score.setText("" + GameMainActivity.mPlay.dollar_current);

                TextView txt_time_bonus = dialogView.findViewById(R.id.txt_time_bonus);
                txt_time_bonus.setText("" + moneyBonus);

                TextView txt_total_doller = dialogView.findViewById(R.id.txt_total_doller);
                txt_total_doller.setText(getString(R.string.totalscore) + " " + totalScore);

                TextView btn_next_level = dialogView.findViewById(R.id.btn_next_level);
                btn_next_level.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (com.funkyland.tileconnecttravel.OldVersion.IPreferences.getInstance(mContext).getRateStatus()) {

                            try {
                                if (IUtil.isNetworkConnected(activity) && IPreferences.getInstance(activity).getIAdShownStatus()) {

                                    Interstitial_Ad_google.setInterface(new Interstitial_Ad_google.Onclick() {
                                        @Override
                                        public void clicked() {
                                            //1
                                            alertDialog.dismiss();

                                            GameMainActivity.mPlay.dollar_current = totalScore;
                                            GameMainActivity.mPlay.mDollar.updateDollar();
                                            //mDollar.reset();
                                            GameMainActivity.this.isThereAnyPopupDLG = false;
                                            GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                            GameMainActivity.this.mSound.playClick();
                                            GameMainActivity.mPlay.musicBackground.resume();
                                            GameMainActivity.mPlay.resetGame();
                                        }
                                    });
                                    interstitial_ad_google.show_method();

                                } else {
                                    alertDialog.dismiss();

                                    GameMainActivity.mPlay.dollar_current = totalScore;
                                    GameMainActivity.mPlay.mDollar.updateDollar();
                                    //mDollar.reset();
                                    GameMainActivity.this.isThereAnyPopupDLG = false;
                                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                    GameMainActivity.this.mSound.playClick();
                                    GameMainActivity.mPlay.musicBackground.resume();
                                    GameMainActivity.mPlay.resetGame();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {

                            if ((Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1)).equals("3") ||
                                    (Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1)).equals("6") ||
                                    (Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1)).equals("9") ||
                                    (Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1)).equals("12") ||
                                    (Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1)).equals("15")) {

                                alertDialog.dismiss();
                                rateAppDialog();

                            } else {

                                try {
                                    if (IUtil.isNetworkConnected(activity) && IPreferences.getInstance(activity).getIAdShownStatus()) {

                                        Interstitial_Ad_google.setInterface(new Interstitial_Ad_google.Onclick() {
                                            @Override
                                            public void clicked() {
                                                //1
                                                alertDialog.dismiss();

                                                GameMainActivity.mPlay.dollar_current = totalScore;
                                                GameMainActivity.mPlay.mDollar.updateDollar();
                                                //mDollar.reset();
                                                GameMainActivity.this.isThereAnyPopupDLG = false;
                                                GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                                GameMainActivity.this.mSound.playClick();
                                                GameMainActivity.mPlay.musicBackground.resume();
                                                GameMainActivity.mPlay.resetGame();
                                            }
                                        });
                                        interstitial_ad_google.show_method();

                                    } else {
                                        alertDialog.dismiss();

                                        GameMainActivity.mPlay.dollar_current = totalScore;
                                        GameMainActivity.mPlay.mDollar.updateDollar();
                                        //mDollar.reset();
                                        GameMainActivity.this.isThereAnyPopupDLG = false;
                                        GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                        GameMainActivity.this.mSound.playClick();
                                        GameMainActivity.mPlay.musicBackground.resume();
                                        GameMainActivity.mPlay.resetGame();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        }

                    }
                });

                if (!alertDialog.isShowing()) {
                    alertDialog.show();
                }

            }
        } else if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_FAIL) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {
                GameMainActivity.this.isThereAnyPopupDLG = true;
                isShowAdDialogToadd120Second = true;
//                scoreLevelWise = 0;
//                if (Pereferences.get_gameType().equals("Easy")) {
//                    Pereferences.set_scoreLevelWiseEasy(0);
//                } else {
//                Pereferences.set_scoreLevelWise(0);
//                }


                GameMainActivity.this.isThereAnyPopupDLG = false;
                GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                GameMainActivity.this.mSound.playClick();
                GameMainActivity.mPlay.musicBackground.resume();
//                GameMainActivity.mPlay.dollar_current = GameMainActivity.mPlay.total_dollar;

                getGameStates();

//                GameMainActivity.mPlay.dollar_current = 0;

                GameMainActivity.mPlay.resetGame();


            }
        } else if (GameMainActivity.this.LevelResultCODE == GameConfiguration.LevelResultCODE_WINNER) {
            if (!GameMainActivity.this.isThereAnyPopupDLG) {
                GameMainActivity.this.isThereAnyPopupDLG = true;
//                scoreLevelWise = 0;
//                if (Pereferences.get_gameType().equals("Easy")) {
//                    Pereferences.set_scoreLevelWiseEasy(0);
//                } else {
//                Pereferences.set_scoreLevelWise(0);
//                }


                Toast.makeText(mContext, "showLevelContextDialog LevelResultCODE_WINNER ", Toast.LENGTH_SHORT).show();


//                ILevelWinerDialog.showShareDialog(GameMainActivity.mPlay, GameMainActivity.mPlay.total_dollar, starByLevel, moneyBonus, null,
//                        new IButtonListener() {
//                            @Override
//                            public void onPress(final Dialog dialog) {
                try {
                    GameMainActivity.this.isThereAnyPopupDLG = false;
                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                    GameMainActivity.this.mSound.playClick();
                    GameMainActivity.mPlay.musicBackground.resume();
                    LevelManager.levelCurrent = 1;
                    GameMainActivity.mPlay.dollar_current = 0;
//                    GameMainActivity.mPlay.total_dollar = 0;
                    GameMainActivity.mPlay.resetGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                            }
//                        },
//                        new IButtonListener() {
//                            @Override
//                            public void onPress(final Dialog dialog) {
//                                try {
//                                    GameMainActivity.this.isThereAnyPopupDLG = false;
//                                    GameMainActivity.this.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
//                                    GameMainActivity.this.mSound.playClick();
//                                    GameMainActivity.mPlay.musicBackground.resume();
//                                    LevelManager.levelCurrent = 1;
//                                    GameMainActivity.mPlay.dollar_current = 0;
//                                    GameMainActivity.mPlay.total_dollar = 0;
//                                    GameMainActivity.mPlay.resetGame();
//
//                                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                                    sharingIntent.setType("text/plain");
//                                    sharingIntent.putExtra(Intent.EXTRA_TEXT, ("https://play.google.com/store/apps/details?id=" + GameMainActivity.mPlay.getPackageName()));
//                                    PackageManager packManager = GameMainActivity.mPlay.getPackageManager();
//                                    List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(sharingIntent, PackageManager.MATCH_DEFAULT_ONLY);
//
//                                    boolean resolved = false;
//                                    for (ResolveInfo resolveInfo : resolvedInfoList) {
//                                        if (resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")) {
//                                            sharingIntent.setClassName(
//                                                    resolveInfo.activityInfo.packageName,
//                                                    resolveInfo.activityInfo.name);
//                                            resolved = true;
//                                            break;
//                                        }
//                                    }
//                                    if (resolved) {
//                                        GameMainActivity.mPlay.startActivity(sharingIntent);
//                                    } else {
//                                        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(GameMainActivity.mPlay);
//                                        alert.setTitle("Warning");
//                                        alert.setMessage("Facebook not found on your phone :((. Install it and try again!");
//                                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
//
//                                            }
//                                        });
//                                        alert.show();
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                );
            }
        }
    }

    private void setTextInNextLevel(TextView txt_unlock_level, String[] array) {

        int pos = new ArrayList<String>(Arrays.asList(array)).indexOf(Pereferences.get_gameType_InSubType());

        if (pos == (array.length - 1)) {
            txt_unlock_level.setVisibility(View.GONE);
        } else {
            txt_unlock_level.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.blink));
            String strMeatFormat = getResources().getString(R.string.you_have_unlock_next_sparkela, array[pos + 1]);
            txt_unlock_level.setText(strMeatFormat);
            txt_unlock_level.setVisibility(View.VISIBLE);
            Pereferences.set_LockTypeStatus(array[pos + 1], true);
        }

    }

    public boolean getGameStates() {
        try {
            LevelManager.levelCurrent = IPreferences.getInstance((Context) this).getCurLevel();
            dollar_current = IPreferences.getInstance((Context) this).getCurTotalScore();
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
            IPreferences.getInstance((Context) this).saveCurTotalScore(totalScore);
            IPreferences.getInstance((Context) this).saveStar(GameMainActivity.mPlay.starByLevel);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void ShowDialogForWatchAdd() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_show_ad, viewGroup, false);
        builder.setView(dialogView);

        if (alertDialog_110 == null) {
            alertDialog_110 = builder.create();
            alertDialog_110.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            alertDialog_110.setCancelable(false);
            alertDialog_110.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
            alertDialog_110.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog_110.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog_110.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }


        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    alertDialog_110.dismiss();
                    isShowAdDialogToadd120Second = false;
                    GameMainActivity.this.mSound.playClick();
                    GameMainActivity.mPlay.musicBackground.pause();
                    GameMainActivity.this.mSound.playGameOver();
                    showGameOverDialog();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_yes.setEnabled(false);
                try {
                    GameMainActivity.this.mSound.playClick();
                    GameMainActivity.mPlay.musicBackground.pause();

                    new GoogleRewardedAdMoreSeconds(activity);

                    if (IUtil.isNetworkConnected(activity)) {

                        GoogleRewardedAdMoreSeconds.setInterfaceInter(new GoogleRewardedAdMoreSeconds.OnclickInter() {
                            @Override
                            public void clicked() {

                                btn_yes.setEnabled(true);
                                isShowAdDialogToadd120Second = false;

                                if (Rewardedfail = true) {
                                    Rewardedfail = false;

                                    GameOver = false;
                                    mProgressBar.setTotalTime(110);

                                    alertDialog_110.dismiss();
                                    GameMainActivity.mPlay.isShowPuaseDialogInOnresume = false;
                                    mProgressBar.start();
                                    GameMainActivity.mPlay.musicBackground.resume();

                                } else {
                                    alertDialog_110.dismiss();
                                    GameMainActivity.mPlay.isShowPuaseDialogInOnresume = false;
                                    mProgressBar.start();
                                    GameMainActivity.mPlay.musicBackground.resume();
                                }
                            }
                        });

                        GoogleRewardedAdMoreSeconds.setEndedRewardedAd(new GoogleRewardedAdMoreSeconds.EndedRewardedAd() {
                            @Override
                            public void endedRwardAd() {
                                isShowAdDialogToadd120Second = false;
                                btn_yes.setEnabled(true);

                                VideoPlay110Successfully = true;
                                GameOver = false;
                                mProgressBar.setTotalTime(110);
                            }
                        });

                    } else {
                        btn_yes.setEnabled(true);
                        IUtil.showToast(activity, activity.getResources().getString(R.string.no_internet));
                    }

                } catch (Exception e) {
                    btn_yes.setEnabled(true);
                    alertDialog_110.dismiss();
                    e.printStackTrace();
                }


            }
        });
        if (alertDialog_110 != null) {
            if (!alertDialog_110.isShowing()) {
                alertDialog_110.show();
            }
        }


    }


    public void ShowDialogVideoPlaySuccess() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_videoplay_successful, viewGroup, false);
        builder.setView(dialogView);


        alertDialog_VideoPlaySuccess = builder.create();
        alertDialog_VideoPlaySuccess.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog_VideoPlaySuccess.setCancelable(false);
        alertDialog_VideoPlaySuccess.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        alertDialog_VideoPlaySuccess.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog_VideoPlaySuccess.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog_VideoPlaySuccess.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        TextView des = dialogView.findViewById(R.id.des);

        LinearLayout ll_onekind = dialogView.findViewById(R.id.ll_onekind);
        LinearLayout ll_suffle = dialogView.findViewById(R.id.ll_suffle);
        LinearLayout ll_hint = dialogView.findViewById(R.id.ll_hint);
        TextView tv_onekind = dialogView.findViewById(R.id.tv_onekind);
        TextView tv_suffle = dialogView.findViewById(R.id.tv_suffle);
        TextView tv_hint = dialogView.findViewById(R.id.tv_hint);
        ImageView iv_onekind = dialogView.findViewById(R.id.iv_onekind);
        ImageView iv_suffle = dialogView.findViewById(R.id.iv_suffle);
        ImageView iv_hint = dialogView.findViewById(R.id.iv_hint);


        if (OneKindVideoPlay == true) {
            OneKindVideoPlay = false;
            ll_onekind.setVisibility(View.VISIBLE);
            tv_onekind.setText("+1");
        } else if (SuffleVideoPlay == true) {
            SuffleVideoPlay = false;
            ll_suffle.setVisibility(View.VISIBLE);
            tv_suffle.setText("+1");
        } else if (hintVideoPlay == true) {
            hintVideoPlay = false;
            ll_hint.setVisibility(View.VISIBLE);
            tv_hint.setText("+1");
        } else if (VideoPlay110Successfully = true) {
            VideoPlay110Successfully = false;
            des.setText(getString(R.string.videoads110_successfully));
        }

        TextView btn_ok = dialogView.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog_VideoPlaySuccess.dismiss();

                if (GameMainActivity.mPlay == null) {

                } else {
                    if (GameMainActivity.mPlay.mProgressBar.isPause == true) {
                        GameMainActivity.mPlay.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                        GameMainActivity.mPlay.isThereAnyPopupDLG = false;
                        GameMainActivity.mPlay.musicBackground.resume();
                        GameMainActivity.mPlay.resumeGame();
                    }
                }

            }
        });

        if (!alertDialog_VideoPlaySuccess.isShowing()) {
            alertDialog_VideoPlaySuccess.show();
        }
    }

    public void DialogBefore_watchVideo_Onekind() {

        mProgressBar.setPause(true);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_before_video, viewGroup, false);
        builder.setView(dialogView);

        dialog_before_watchVideo_Onekind = builder.create();
        dialog_before_watchVideo_Onekind.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_before_watchVideo_Onekind.setCancelable(false);
        dialog_before_watchVideo_Onekind.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        dialog_before_watchVideo_Onekind.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_before_watchVideo_Onekind.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_before_watchVideo_Onekind.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        ImageView img = dialogView.findViewById(R.id.img);
        img.setImageResource(R.drawable.ic_one_kind_remove);

        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setPause(false);
                dialog_before_watchVideo_Onekind.dismiss();
                mButtonOneKind.btn_remove_same.setEnabled(true);

            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (IUtil.isNetworkConnected(GameMainActivity.mPlay)) {

                    GameMainActivity.mPlay.mProgressBar.setPause(true);
                    GameMainActivity.mPlay.musicBackground.pause();

                    GoogleRewardedAdRMOKind googleRewardedAd = new GoogleRewardedAdRMOKind(GameMainActivity.mPlay);

                    googleRewardedAd.setInterfaceInter(new GoogleRewardedAdRMOKind.OnclickInter() {
                        @Override
                        public void clicked() {
                            GameMainActivity.mPlay.isShowPuaseDialogInOnresume = false;
                            GameMainActivity.mPlay.musicBackground.resume();
                            GameMainActivity.mPlay.resumeGame();

                            mButtonOneKind.btn_remove_same.setEnabled(true);
                        }
                    });

                    googleRewardedAd.setEndedRewardedAd(new GoogleRewardedAdRMOKind.EndedRewardedAd() {
                        @Override
                        public void endedRwardAd() {
                            mButtonOneKind.btn_remove_same.setEnabled(true);
                            OneKindVideoPlay = true;
                            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 1);

                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GameMainActivity.mPlay.mButtonOneKind.setOneKindRemoveText();
                                }
                            });

                        }
                    });

                } else {
                    IUtil.showToast(activity, activity.getString(R.string.no_internet));
                    mButtonOneKind.btn_remove_same.setEnabled(true);
                }

                dialog_before_watchVideo_Onekind.dismiss();

            }
        });

        if (!dialog_before_watchVideo_Onekind.isShowing()) {
            dialog_before_watchVideo_Onekind.show();
        }
    }

    public void ShowDialogBefore_watchVide_Suffle() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_before_video, viewGroup, false);
        builder.setView(dialogView);


        dialog_before_watchVideo_Suffle = builder.create();
        dialog_before_watchVideo_Suffle.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_before_watchVideo_Suffle.setCancelable(false);
        dialog_before_watchVideo_Suffle.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        dialog_before_watchVideo_Suffle.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_before_watchVideo_Suffle.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_before_watchVideo_Suffle.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        TextView des = dialogView.findViewById(R.id.des);
        ImageView img = dialogView.findViewById(R.id.img);

        img.setImageResource(R.drawable.ic_suffle);

        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog_before_watchVideo_Suffle.dismiss();

            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (IUtil.isNetworkConnected(GameMainActivity.mPlay)) {

                    GameMainActivity.mPlay.mProgressBar.setPause(true);
                    GameMainActivity.mPlay.musicBackground.pause();

                    GoogleRewardedAdShuffle googleRewardedAd = new GoogleRewardedAdShuffle(GameMainActivity.mPlay);

                    googleRewardedAd.setInterfaceInter(new GoogleRewardedAdShuffle.OnclickInter() {
                        @Override
                        public void clicked() {
                            GameMainActivity.mPlay.isShowPuaseDialogInOnresume = false;
                            GameMainActivity.mPlay.musicBackground.resume();
                            GameMainActivity.mPlay.resumeGame();
                        }
                    });

                    googleRewardedAd.setEndedRewardedAd(new GoogleRewardedAdShuffle.EndedRewardedAd() {
                        @Override
                        public void endedRwardAd() {

                            SuffleVideoPlay = true;
                            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 1);

                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GameMainActivity.mPlay.mButtonSuffle.setSuffleText();
                                }
                            });

                        }
                    });

                } else {
                    IUtil.showToast(activity, activity.getString(R.string.no_internet));
                }

                dialog_before_watchVideo_Suffle.dismiss();

            }
        });

        if (!dialog_before_watchVideo_Suffle.isShowing()) {
            dialog_before_watchVideo_Suffle.show();
        }
    }

    public void ShowDialogBefore_watchVideo_Hint() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_before_video, viewGroup, false);
        builder.setView(dialogView);


        dialog_before_watchVideo_hint = builder.create();
        dialog_before_watchVideo_hint.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_before_watchVideo_hint.setCancelable(false);
        dialog_before_watchVideo_hint.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        dialog_before_watchVideo_hint.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_before_watchVideo_hint.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_before_watchVideo_hint.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        TextView des = dialogView.findViewById(R.id.des);
        ImageView img = dialogView.findViewById(R.id.img);

        img.setImageResource(R.drawable.ic_hint);

        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog_before_watchVideo_hint.dismiss();

            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!GameMainActivity.mPlay.mHint.visiable()) {

                    if (IUtil.isNetworkConnected(GameMainActivity.mPlay)) {

                        GameMainActivity.mPlay.mProgressBar.setPause(true);
                        GameMainActivity.mPlay.musicBackground.pause();

                        GoogleRewardedAdHint googleRewardedAd = new GoogleRewardedAdHint(GameMainActivity.mPlay);

                        googleRewardedAd.setInterfaceInter(new GoogleRewardedAdHint.OnclickInter() {
                            @Override
                            public void clicked() {
                                GameMainActivity.mPlay.isShowPuaseDialogInOnresume = false;
                                GameMainActivity.mPlay.musicBackground.resume();
                                GameMainActivity.mPlay.resumeGame();
                            }
                        });

                        googleRewardedAd.setEndedRewardedAd(new GoogleRewardedAdHint.EndedRewardedAd() {
                            @Override
                            public void endedRwardAd() {
                                hintVideoPlay = true;
                                IPreferences.getInstance(mContext).setHint(IPreferences.getInstance(mContext).getHint() + 1);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        GameMainActivity.mPlay.mButtonHint.setHintText();
                                    }
                                });

                            }
                        });

                    } else {
                        IUtil.showToast(mContext, mContext.getString(R.string.no_internet));
                    }
                }


                dialog_before_watchVideo_hint.dismiss();

            }
        });

        if (!dialog_before_watchVideo_hint.isShowing()) {
            dialog_before_watchVideo_hint.show();
        }
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Wish you a nice day and have fun playing with us ^^");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }

    private void rateAppDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_rating, viewGroup, false);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCancelable(true);
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        //SetLaunguageSelected(IPreferences.getInstance(mContext).getLaungauge());

        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    alertDialog.dismiss();
                    GameMainActivity.mPlay.dollar_current = totalScore;
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
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.funkyland.tileconnecttravel.OldVersion.IPreferences.getInstance(mContext).saveRateStatus(true);
                isHasClickYesRateDialogBefore = true;
                alertDialog.dismiss();
                mSound.playClick();

                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }

            }
        });

        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }


    //######################### THEME CODE ###########################

    public void showThemeDialog() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_theme, viewGroup, false);
        builder.setView(dialogView);

        btn_item_one = dialogView.findViewById(R.id.btn_item_one);
        btn_item_two = dialogView.findViewById(R.id.btn_item_two);
        btn_item_three = dialogView.findViewById(R.id.btn_item_three);
        btn_item_four = dialogView.findViewById(R.id.btn_item_four);
        btn_item_five = dialogView.findViewById(R.id.btn_item_five);
        btn_item_six = dialogView.findViewById(R.id.btn_item_six);


        alertDialog_showTheme = builder.create();
        alertDialog_showTheme.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog_showTheme.setCancelable(true);
        alertDialog_showTheme.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
        alertDialog_showTheme.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog_showTheme.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog_showTheme.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        ImageView btn_close = dialogView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog_showTheme.dismiss();
                mSound.playClick();
                GameMainActivity.mPlay.musicBackground.resume();
                GameMainActivity.mPlay.resumeGame();

            }
        });


        SetThemeSelected(IPreferences.getInstance(mContext).getTheme());

        if (!alertDialog_showTheme.isShowing()) {
            alertDialog_showTheme.show();
        }

    }

    public void SetThemeSelected(int laungauge) {

        switch (laungauge) {
            case 1:
                btn_item_one.setAlpha(1.0f);
                btn_item_two.setAlpha(0.5f);
                btn_item_three.setAlpha(0.5f);
                btn_item_four.setAlpha(0.5f);
                btn_item_five.setAlpha(0.5f);
                btn_item_six.setAlpha(0.5f);
                break;
            case 2:
                btn_item_one.setAlpha(0.5f);
                btn_item_two.setAlpha(1.0f);
                btn_item_three.setAlpha(0.5f);
                btn_item_four.setAlpha(0.5f);
                btn_item_five.setAlpha(0.5f);
                btn_item_six.setAlpha(0.5f);
                break;
            case 3:
                btn_item_one.setAlpha(0.5f);
                btn_item_two.setAlpha(0.5f);
                btn_item_three.setAlpha(1.0f);
                btn_item_four.setAlpha(0.5f);
                btn_item_five.setAlpha(0.5f);
                btn_item_six.setAlpha(0.5f);
                break;
            case 4:
                btn_item_one.setAlpha(0.5f);
                btn_item_two.setAlpha(0.5f);
                btn_item_three.setAlpha(0.5f);
                btn_item_four.setAlpha(1.0f);
                btn_item_five.setAlpha(0.5f);
                btn_item_six.setAlpha(0.5f);
                break;
            case 5:
                btn_item_one.setAlpha(0.5f);
                btn_item_two.setAlpha(0.5f);
                btn_item_three.setAlpha(0.5f);
                btn_item_four.setAlpha(0.5f);
                btn_item_five.setAlpha(1.0f);
                btn_item_six.setAlpha(0.5f);
                break;
            case 6:
                btn_item_one.setAlpha(0.5f);
                btn_item_two.setAlpha(0.5f);
                btn_item_three.setAlpha(0.5f);
                btn_item_four.setAlpha(0.5f);
                btn_item_five.setAlpha(0.5f);
                btn_item_six.setAlpha(1.0f);
                break;
        }
    }

    public void OnClickTheme(View v) {
        switch (v.getId()) {
            case R.id.btn_item_one:
                setThemeSetting(1);
                break;
            case R.id.btn_item_two:
                setThemeSetting(2);
                break;
            case R.id.btn_item_three:
                setThemeSetting(3);
                break;
            case R.id.btn_item_four:
                setThemeSetting(4);
                break;
            case R.id.btn_item_five:
                setThemeSetting(5);
                break;
            case R.id.btn_item_six:
                setThemeSetting(6);
                break;
        }
        SetThemeSelected(IPreferences.getInstance(mContext).getTheme());
    }

    private void setThemeSetting(int i) {

        IPreferences.getInstance(mContext).setTheme(i);
        SetThemeSelected(IPreferences.getInstance(mContext).getTheme());

        this.mProgressBar.setStop(true);
        isStopgame = false;
        alertDialog_showTheme.dismiss();

        Intent j = new Intent(GameMainActivity.this, GameMainActivity.class);
        startActivity(j);

    }


}