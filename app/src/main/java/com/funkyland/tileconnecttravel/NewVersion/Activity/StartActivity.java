package com.funkyland.tileconnecttravel.NewVersion.Activity;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_ADVE;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_ATS;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_ATW;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_CLAS;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_TYPE_EASY;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_TYPE_HARD;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_TYPE_RELAXED;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.START_ACTIVITY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.funkyland.tileconnecttravel.NewVersion.Ads.GoogleAdBanner;
import com.funkyland.tileconnecttravel.NewVersion.Ads.Interstitial_Ad_google;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPolicy;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPurchaseAllFeature;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPurchaseOneCard;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPurchaseSuccessfully;
import com.funkyland.tileconnecttravel.NewVersion.Generators.ILogger;
import com.funkyland.tileconnecttravel.NewVersion.InAppPurchase.GoogleBilling;
import com.funkyland.tileconnecttravel.NewVersion.Services.MusicBackground;
import com.funkyland.tileconnecttravel.NewVersion.Services.SoundController;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.NewVersion.Utils.DialogAction;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IUtil;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class StartActivity extends GameBaseActivity {

    public static boolean startPurSuccess = false;
    //public static LinearLayout ad_googleBanner;
    private static SoundController mSound;
    private static Activity activity;
    public MusicBackground musicBackground;
    public ImageView img_usa, img_brazil, img_vietnam;
    public ImageView img_indonesia, img_thailand, img_russia;
    public ImageView img_japan, img_south_korea, img_flag_taiwan;
    Interstitial_Ad_google interstitial_ad_google;
    CheckBox checkBoxHard, checkBoxEasy, checkBoxRelaxed;
    private Context context;
    private TextView btn_around_the_world, btn_adventure, btn_around_the_sparkela, btn_classic;

    private static GoogleBilling googleBilling = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        activity = this;
        context = this;
        HideNavigation(activity);

        //ad_googleBanner = findViewById(R.id.ad_googleBanner);
        //new GoogleAdBanner(activity, ad_googleBanner).googleBanner();

        Locale.getDefault().getDisplayLanguage();

        (this.musicBackground = new MusicBackground()).loadMusic((Context) this);
        this.musicBackground.play();

        (mSound = new SoundController()).loadSound((Context) this);

        Interstitial_Ad_Google();

        if (googleBilling == null) {
            googleBilling = new GoogleBilling(activity);
            googleBilling.Initialize();
            googleBilling.connectTogoogleBilling();

        }

        TextView txt_score = findViewById(R.id.txt_score);
        txt_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHighScore();
            }
        });


        btn_around_the_world = findViewById(R.id.btn_around_the_world);


        btn_around_the_world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pereferences.set_gameType_InSubType("india");
                Pereferences.set_gameMode(GAME_MODE_ATW);
                NextActivity();
            }
        });



        findViewById(R.id.btn_rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSound.playClick();
                rateAppDialog();
            }
        });

        findViewById(R.id.btn_language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSound.playClick();
                showLanguageDialog();
            }
        });

        findViewById(R.id.btn_store).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSound.playClick();

                DialogPurchaseAllFeature dialogPurchaseAllFeature = new DialogPurchaseAllFeature(activity);
                dialogPurchaseAllFeature.setDialogAction(new DialogAction() {
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
                                                //ad_googleBanner.setVisibility(View.GONE);
                                                //Toast.makeText(context, "you are trying now ", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        dialogPurchaseSuccessfully.Init(pack);
                                    } else {
                                        Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            googleBilling.OpenPaymentFlow(packId);
                        }


                    }
                });
                dialogPurchaseAllFeature.show();
            }
        });

        if (IPreferences.getInstance(activity).getPolicyStatus()) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
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
                                                }
                                            });
                                            dialogPurchaseSuccessfully.Init(pack);
                                        }
                                    }
                                });
                                googleBilling.OpenPaymentFlow(packId);
                            }
                        }
                    });
                    dialogPurchaseOneCard.show();
                }
            }, 100);

        } else {
            new DialogPolicy(activity).Show();
        }

        findViewById(R.id.btn_moreapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new DialogPurchaseOneCard(activity, googleBilling).show();

                //getLanguage = true;
                //mSound.playClick();

//                String Language = Resources.getSystem().getConfiguration().locale.getLanguage();
//                Log.d("getLanguage", Language);
//
//                if (Language.equals("en")) {
//                    IPreferences.getInstance(mContext).setLaungauge(1);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("pt")) {
//                    IPreferences.getInstance(mContext).setLaungauge(2);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("vi")) {
//                    IPreferences.getInstance(mContext).setLaungauge(3);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("in")) {
//                    IPreferences.getInstance(mContext).setLaungauge(4);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("th")) {
//                    IPreferences.getInstance(mContext).setLaungauge(5);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("ru")) {
//                    IPreferences.getInstance(mContext).setLaungauge(6);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("ja")) {
//                    IPreferences.getInstance(mContext).setLaungauge(7);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("ko")) {
//                    IPreferences.getInstance(mContext).setLaungauge(8);
//                    setLanguage(activity, Language);
//                } else if (Language.equals("zh")) {
//                    IPreferences.getInstance(mContext).setLaungauge(9);
//                    setLanguage(activity, Language);
//                } else {
//                    IPreferences.getInstance(mContext).setLaungauge(1);
//                    setLanguage(activity, "en");
//                }

                //Intent i = new Intent(StartActivity.this, MoreAppActivity.class);
                //startActivity(i);
            }
        });


        checkBoxHard = findViewById(R.id.checkBoxHard);
        checkBoxEasy = findViewById(R.id.checkBoxEasy);
        checkBoxRelaxed = findViewById(R.id.checkBoxRelaxed);

        checkBoxHard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Pereferences.set_gameType(GAME_TYPE_HARD);
                }
                setCheckbox();
            }
        });

        checkBoxEasy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Pereferences.set_gameType(GAME_TYPE_EASY);
                }
                setCheckbox();
            }
        });

        checkBoxRelaxed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Pereferences.set_gameType(GAME_TYPE_RELAXED);
                }
                setCheckbox();
            }
        });

        setCheckbox();
    }

    private void ShowAdsNext() {

        if (IUtil.isNetworkConnected(activity) && IPreferences.getInstance(activity).getIAdShownStatus()) {
            Interstitial_Ad_google.setInterface(new Interstitial_Ad_google.Onclick() {
                @Override
                public void clicked() {

                    Intent i = new Intent(StartActivity.this, GameMainActivity.class);
                    i.putExtra("isFrom", START_ACTIVITY);
                    startActivity(i);

                }
            });
            interstitial_ad_google.show_method();
        } else {
            Intent i = new Intent(StartActivity.this, GameMainActivity.class);
            i.putExtra("isFrom", START_ACTIVITY);
            startActivity(i);
        }
    }

    private void NextActivity() {

        startPurSuccess = false;
        mSound.playClick();
        musicBackground.release();

        if (Pereferences.get_gameMode().equals(GAME_MODE_ATW)) {
            Intent i = new Intent(StartActivity.this, WorldActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        } else if (Pereferences.get_gameMode().equals(GAME_MODE_ATS)) {
            Intent i = new Intent(StartActivity.this, SparkelaActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        } else if (Pereferences.get_gameMode().equals(GAME_MODE_CLAS)) {
            ShowAdsNext();
        }
    }

    private void setCheckbox() {
        if (Pereferences.get_gameType().equals(GAME_TYPE_EASY)) {
            checkBoxEasy.setChecked(true);
            checkBoxHard.setChecked(false);
            checkBoxRelaxed.setChecked(false);

        } else if (Pereferences.get_gameType().equals(GAME_TYPE_HARD)) {
            checkBoxEasy.setChecked(false);
            checkBoxHard.setChecked(true);
            checkBoxRelaxed.setChecked(false);

        } else if (Pereferences.get_gameType().equals(GAME_TYPE_RELAXED)) {
            checkBoxEasy.setChecked(false);
            checkBoxHard.setChecked(false);
            checkBoxRelaxed.setChecked(true);
        }
    }

    private void showDialogHighScore() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_high_score, viewGroup, false);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCancelable(true);
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        TextView btn_around_the_world = dialogView.findViewById(R.id.btn_around_the_world);
        TextView btn_adventure = dialogView.findViewById(R.id.btn_adventure);
        TextView btn_around_the_sparkela = dialogView.findViewById(R.id.btn_around_the_sparkela);
        TextView btn_classic = dialogView.findViewById(R.id.btn_classic);

        LinearLayout ll_score = dialogView.findViewById(R.id.ll_score);
        LinearLayout ll_btn = dialogView.findViewById(R.id.ll_btn);

        TextView textViewHardScore = dialogView.findViewById(R.id.textViewHardScore);
        TextView textViewEasyScore = dialogView.findViewById(R.id.textViewEasyScore);
        TextView textViewRelaxScore = dialogView.findViewById(R.id.textViewRelaxScore);

        ll_btn.setVisibility(View.GONE);
        ll_score.setVisibility(View.VISIBLE);
        int GAME_TYPE_EASY_GAME_MODE_ATW = 0;
        int GAME_TYPE_HARD_GAME_MODE_ATW = 0;
        int GAME_TYPE_RELAXED_GAME_MODE_ATW = 0;

        List myList = new ArrayList();
        Collections.addAll(myList, Constant.Country_Asia_path);
        Collections.addAll(myList, Constant.Country_Europe_path);
        Collections.addAll(myList, Constant.Country_America_path);
        Collections.addAll(myList, Constant.Country_Australia_path);
        Collections.addAll(myList, Constant.Country_Africa_path);

        for (int d = 0; d < myList.size(); d++) {

            if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_ATW, myList.get(d).toString()) > GAME_TYPE_EASY_GAME_MODE_ATW) {
                GAME_TYPE_EASY_GAME_MODE_ATW = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_ATW, myList.get(d).toString());
            }
            if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_ATW, myList.get(d).toString()) > GAME_TYPE_HARD_GAME_MODE_ATW) {
                GAME_TYPE_HARD_GAME_MODE_ATW = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_ATW, myList.get(d).toString());
            }
            if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_ATW, myList.get(d).toString()) > GAME_TYPE_RELAXED_GAME_MODE_ATW) {
                GAME_TYPE_RELAXED_GAME_MODE_ATW = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_ATW, myList.get(d).toString());
            }
        }

        textViewHardScore.setText(getString(R.string.easy_score) + " : " + GAME_TYPE_EASY_GAME_MODE_ATW);
        textViewEasyScore.setText(getString(R.string.hard_score) + " : " + GAME_TYPE_HARD_GAME_MODE_ATW);
        textViewRelaxScore.setText(getString(R.string.relax_score) + " : " + GAME_TYPE_RELAXED_GAME_MODE_ATW);

        btn_around_the_world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_btn.setVisibility(View.GONE);

                int GAME_TYPE_EASY_GAME_MODE_ATW = 0;
                int GAME_TYPE_HARD_GAME_MODE_ATW = 0;
                int GAME_TYPE_RELAXED_GAME_MODE_ATW = 0;

                List myList = new ArrayList();
                Collections.addAll(myList, Constant.Country_Asia_path);
                Collections.addAll(myList, Constant.Country_Europe_path);
                Collections.addAll(myList, Constant.Country_America_path);
                Collections.addAll(myList, Constant.Country_Australia_path);
                Collections.addAll(myList, Constant.Country_Africa_path);

                for (int d = 0; d < myList.size(); d++) {

                    if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_ATW, myList.get(d).toString()) > GAME_TYPE_EASY_GAME_MODE_ATW) {
                        GAME_TYPE_EASY_GAME_MODE_ATW = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_ATW, myList.get(d).toString());
                    }
                    if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_ATW, myList.get(d).toString()) > GAME_TYPE_HARD_GAME_MODE_ATW) {
                        GAME_TYPE_HARD_GAME_MODE_ATW = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_ATW, myList.get(d).toString());
                    }
                    if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_ATW, myList.get(d).toString()) > GAME_TYPE_RELAXED_GAME_MODE_ATW) {
                        GAME_TYPE_RELAXED_GAME_MODE_ATW = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_ATW, myList.get(d).toString());
                    }
                }

                textViewHardScore.setText(getString(R.string.easy_score) + " : " + GAME_TYPE_EASY_GAME_MODE_ATW);
                textViewEasyScore.setText(getString(R.string.hard_score) + " : " + GAME_TYPE_HARD_GAME_MODE_ATW);
                textViewRelaxScore.setText(getString(R.string.relax_score) + " : " + GAME_TYPE_RELAXED_GAME_MODE_ATW);
                ll_score.setVisibility(View.VISIBLE);
            }
        });

        btn_around_the_sparkela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_btn.setVisibility(View.GONE);

                int GAME_TYPE_EASY_GAME_MODE_ATS = 0;
                int GAME_TYPE_HARD_GAME_MODE_ATS = 0;
                int GAME_TYPE_RELAXED_GAME_MODE_ATS = 0;


                for (int d = 0; d < Constant.theme_path.length; d++) {

                    if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_ATS, Constant.theme_path[d]) > GAME_TYPE_EASY_GAME_MODE_ATS) {
                        GAME_TYPE_EASY_GAME_MODE_ATS = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_ATS, Constant.theme_path[d]);
                    }
                    if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_ATS, Constant.theme_path[d]) > GAME_TYPE_HARD_GAME_MODE_ATS) {
                        GAME_TYPE_HARD_GAME_MODE_ATS = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_ATS, Constant.theme_path[d]);
                    }
                    if (IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_ATS, Constant.theme_path[d]) > GAME_TYPE_RELAXED_GAME_MODE_ATS) {
                        GAME_TYPE_RELAXED_GAME_MODE_ATS = IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_ATS, Constant.theme_path[d]);
                    }
                }


                textViewHardScore.setText(getString(R.string.easy_score) + " : " + GAME_TYPE_EASY_GAME_MODE_ATS);
                textViewEasyScore.setText(getString(R.string.hard_score) + " : " + GAME_TYPE_HARD_GAME_MODE_ATS);
                textViewRelaxScore.setText(getString(R.string.relax_score) + " : " + GAME_TYPE_RELAXED_GAME_MODE_ATS);
                ll_score.setVisibility(View.VISIBLE);
            }
        });

        btn_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_btn.setVisibility(View.GONE);
                textViewHardScore.setText(getString(R.string.easy_score) + " : " + IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_ADVE, ""));
                textViewEasyScore.setText(getString(R.string.hard_score) + " : " + IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_ADVE, ""));
                textViewRelaxScore.setText(getString(R.string.relax_score) + " : " + IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_ADVE, ""));
                ll_score.setVisibility(View.VISIBLE);
            }
        });

        btn_classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_btn.setVisibility(View.GONE);
                textViewHardScore.setText(getString(R.string.easy_score) + " : " + IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_EASY, GAME_MODE_CLAS, ""));
                textViewEasyScore.setText(getString(R.string.hard_score) + " : " + IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_HARD, GAME_MODE_CLAS, ""));
                textViewRelaxScore.setText(getString(R.string.relax_score) + " : " + IPreferences.getInstance(context).getsaveTotal_dollarForHighScoreDialog(GAME_TYPE_RELAXED, GAME_MODE_CLAS, ""));
                ll_score.setVisibility(View.VISIBLE);
            }
        });

        ImageView btn_close = dialogView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                mSound.playClick();
            }
        });
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

    public void Interstitial_Ad_Google() {
        interstitial_ad_google = new Interstitial_Ad_google(StartActivity.this);
    }

    @Override
    public void onBackPressed() {
        ShowExitDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ILogger.LogInfo("onDestroy");
        try {
            this.musicBackground.release();
        } catch (Exception ex) {
            ILogger.LogError("Play onDestroy " + ex.toString());
        }
    }

    @Override
    protected void onPause() {
        try {
            musicBackground.pause();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            musicBackground.resume();

            if (googleBilling == null) {
                googleBilling = new GoogleBilling(activity);
                googleBilling.Initialize();
                googleBilling.connectTogoogleBilling();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void ShowExitDialog() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_exit, viewGroup, false);
        builder.setView(dialogView);


        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dollar_current = 0;
                //GameMainActivity.mPlay.dollar_current = 0;
                //Toast.makeText(activity, "score " + dollar_current, Toast.LENGTH_SHORT).show();
                //GameMainActivity.mPlay.dollar_current = 0;
                //mDollar.updateDollar();
                finishAffinity();

            }
        });


        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

    private void rateAppDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_rating, viewGroup, false);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCancelable(true);
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        //SetLaunguageSelected(IPreferences.getInstance(mContext).getLaungauge());

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

    private void showLanguageDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_language, viewGroup, false);
        builder.setView(dialogView);


        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCancelable(true);
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        img_usa = dialogView.findViewById(R.id.img_usa);
        img_brazil = dialogView.findViewById(R.id.img_uk);
        img_vietnam = dialogView.findViewById(R.id.img_vietnam);

        img_indonesia = dialogView.findViewById(R.id.img_germany);
        img_thailand = dialogView.findViewById(R.id.img_french);
        img_russia = dialogView.findViewById(R.id.img_australia);

        img_japan = dialogView.findViewById(R.id.img_japan);
        img_south_korea = dialogView.findViewById(R.id.img_south_korea);
        img_flag_taiwan = dialogView.findViewById(R.id.img_flag_india);

        RelativeLayout btn_close = dialogView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        SetLaunguageSelected(IPreferences.getInstance(mContext).getLaungauge());


        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

    private void SetLaunguageSelected(int laungauge) {

        switch (laungauge) {
            case 1:
//                setLanguage(activity, "en");
                img_usa.setAlpha(1.0f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 2:
//                setLanguage(activity, "pt");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(1.0f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 3:
//                setLanguage(activity, "vi");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(1.0f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 4:
//                setLanguage(activity, "in");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(1.0f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 5:
//                setLanguage(activity, "th");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(1.0f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 6:
//                setLanguage(activity, "ru");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(1.0f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 7:
//                setLanguage(activity, "ja");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(1.0f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 8:
//                setLanguage(activity, "ko");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(1.0f);
                img_flag_taiwan.setAlpha(0.5f);
                break;
            case 9:
//                setLanguage(activity, "zh");
                img_usa.setAlpha(0.5f);
                img_brazil.setAlpha(0.5f);
                img_vietnam.setAlpha(0.5f);
                img_indonesia.setAlpha(0.5f);
                img_thailand.setAlpha(0.5f);
                img_russia.setAlpha(0.5f);
                img_japan.setAlpha(0.5f);
                img_south_korea.setAlpha(0.5f);
                img_flag_taiwan.setAlpha(1.0f);
                break;
        }

    }

    public void OnClickLanguage(View v) {
        switch (v.getId()) {
            case R.id.img_usa:
                IPreferences.getInstance(mContext).setLaungauge(1);
                setLanguage(activity, "en");
                break;
            case R.id.img_uk:
                IPreferences.getInstance(mContext).setLaungauge(2);
                setLanguage(activity, "pt");
                break;
            case R.id.img_vietnam:
                IPreferences.getInstance(mContext).setLaungauge(3);
                setLanguage(activity, "vi");
                break;
            case R.id.img_germany:
                IPreferences.getInstance(mContext).setLaungauge(4);
                setLanguage(activity, "in");
                break;
            case R.id.img_french:
                IPreferences.getInstance(mContext).setLaungauge(5);
                setLanguage(activity, "th");
                break;
            case R.id.img_australia:
                IPreferences.getInstance(mContext).setLaungauge(6);
                setLanguage(activity, "ru");
                break;
            case R.id.img_japan:
                IPreferences.getInstance(mContext).setLaungauge(7);
                setLanguage(activity, "ja");
                break;
            case R.id.img_south_korea:
                IPreferences.getInstance(mContext).setLaungauge(8);
                setLanguage(activity, "ko");
                break;
            case R.id.img_flag_india:
                IPreferences.getInstance(mContext).setLaungauge(9);
                setLanguage(activity, "zh");
                break;
        }

        SetLaunguageSelected(IPreferences.getInstance(mContext).getLaungauge());

    }

    public void setLanguage(Activity activity, String language) {
        Locale locale = new Locale(language);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        recreate();
    }
}