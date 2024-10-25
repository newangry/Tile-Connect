/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funkyland.tileconnecttravel.OldVersion;

import static com.funkyland.tileconnecttravel.OldVersion.GameMainActivity.flags;
import static com.funkyland.tileconnecttravel.OldVersion.GameMainActivity.mSound;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.OldVersion.utils.LevelManager;
import com.funkyland.tileconnecttravel.R;

import java.util.ArrayList;

public class IWinnerDialog {


    public static class LocalDlg extends Dialog {

        public LocalDlg(Context context) {
            super(context);
        }

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        protected void onStart() {
            super.onStart();
            GameMainActivity.mPlay.pauseGameWithoutDlg();
        }

        protected void onStop() {
            super.onStop();
            GameMainActivity.mPlay.resumeGame();
        }
    }



    public static String showdialog = "";
    public static ArrayList<Integer> arrayList = new ArrayList<>();

    @SuppressLint("WrongConstant")
    public static void showShareDialog(final Context context, final int money, final int stars, final int bonusMoney, final IButtonListener ssButtonPressListener, final IButtonListener ssButtonPressListener2, final IButtonListener ssButtonPressListener3) {
        try {
            final LocalDlg dialog = new LocalDlg(context);
            dialog.requestWindowFeature(1);
            dialog.setOnKeyListener(new LocalDlg.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                    }
                    return true;
                }
            });
            final RelativeLayout contentView = new RelativeLayout(context);
            contentView.setBackgroundColor(Color.argb(90, 0, 0, 0));
            final LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams((ViewGroup.LayoutParams) new RelativeLayout.LayoutParams(IDialogUtils.dp2px(context, 350.0f), -2));
            linearLayout.setOrientation(1);
            try {
                linearLayout.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_dialog_bg.9.png"));
            } catch (Exception exxx) {
                exxx.printStackTrace();
            }
            final LinearLayout linearLayout2 = new LinearLayout(context);
            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, IDialogUtils.dp2px(context, 45.0f));
            layoutParams.gravity = 17;
            linearLayout2.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
            linearLayout2.setOrientation(1);
            final TextView textView = new TextView(context);
            textView.setText((CharSequence) "Congratulation");
            textView.setTextSize(20.0f);
            textView.setTextColor(-1);
            final LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
            layoutParams2.gravity = 17;
            textView.setLayoutParams((ViewGroup.LayoutParams) layoutParams2);
            textView.setGravity(17);
            linearLayout2.addView((View) textView);
            linearLayout.addView((View) linearLayout2);
            final ImageView imageView = new ImageView(context);
            final LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
            layoutParams3.setMargins(IDialogUtils.dp2px(context, 2.0f), 0, IDialogUtils.dp2px(context, 2.0f), 0);
            imageView.setLayoutParams((ViewGroup.LayoutParams) layoutParams3);
            try {
                imageView.setBackgroundDrawable(IResourceController.getAssertDrawable(context, "mbappss_rate_sep_line.png", IDialogUtils.dp2px(context, 1.0f), IDialogUtils.dp2px(context, 2.0f)));
            } catch (Exception exxx) {
                exxx.printStackTrace();
            }
            linearLayout.addView((View) imageView);
            final TextView textView2 = new TextView(context);
            textView2.setText((CharSequence) "Level " + Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1) + " completed : " + Integer.toString(stars) + " stars" + "\n" + "Time Bonus     : " + Integer.toString(bonusMoney) + "$"
                    + " \nCurrent money : " + Integer.toString(money) + "$");
            textView2.setTextSize(18.0f);
            textView2.setTextColor(-1);
            textView2.setLineSpacing(3.0f, 1.2f);
            final LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.gravity = 16;
            layoutParams4.setMargins(IDialogUtils.dp2px(context, 6.0f), IDialogUtils.dp2px(context, 5.0f), 0, IDialogUtils.dp2px(context, 5.0f));
            textView2.setLayoutParams((ViewGroup.LayoutParams) layoutParams4);
            final int dp2px = IDialogUtils.dp2px(context, 14.0f);
            textView2.setPadding(dp2px, dp2px, dp2px, dp2px);
            linearLayout.addView((View) textView2);
            final LinearLayout linearLayout3 = new LinearLayout(context);
            final LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams5.gravity = 16;
            layoutParams5.topMargin = IDialogUtils.dp2px(context, 5.0f);
            linearLayout3.setLayoutParams((ViewGroup.LayoutParams) layoutParams5);
            linearLayout3.setPadding(0, 0, 0, IDialogUtils.dp2px(context, 10.0f));
            linearLayout3.setOrientation(0);
            final Button button = new Button(context);
            button.setTextSize(18.0f);
            button.setTextColor(-1);
            button.setText((CharSequence) "Next Level");
            final LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, IDialogUtils.dp2px(context, 50.0f));
            layoutParams6.leftMargin = IDialogUtils.dp2px(context, 10.0f);
            layoutParams6.rightMargin = IDialogUtils.dp2px(context, 10.0f);
            layoutParams6.weight = 1.0f;
            button.setLayoutParams((ViewGroup.LayoutParams) layoutParams6);
            button.setGravity(17);
            try {
                button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_yes.9.png"));
            } catch (Exception exxx) {
                exxx.printStackTrace();
            }


            button.setOnTouchListener((View.OnTouchListener) new View.OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {

                    if (IPreferences.getInstance(GameMainActivity.mPlay).getRateStatus()) {
                        switch (motionEvent.getAction()) {
                            case 0: {
                                try {
                                    button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_pressed.9.png"));
                                } catch (Exception exxx) {
                                    exxx.printStackTrace();
                                }
                                break;
                            }
                            case 1: {
                                try {
                                    button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_yes.9.png"));
                                } catch (Exception exxx) {
                                    exxx.printStackTrace();
                                }
                                dialog.dismiss();
                                if (ssButtonPressListener3 != null) {
                                    ssButtonPressListener3.onPress(dialog);
                                    break;
                                }
                                break;
                            }
                        }
                    } else {

                        int lavel = Integer.parseInt((Integer.toString((LevelManager.levelCurrent - 1) > 0 ? LevelManager.levelCurrent - 1 : 1)));

                        arrayList.clear();
                        for (int i = 3; i < 300; i += 3) {
                            arrayList.add(i);
                        }

                        Log.d("valueof1", arrayList.size() + "");
                        for (int i = 0; i < arrayList.size(); i++) {
                            Log.d("valueof2", arrayList.get(i) + "");
                            if (lavel == arrayList.get(i)) {
                                showdialog = "in";
                                Log.d("valueof3", arrayList.size() + "");
                            }
                        }

                        if (showdialog.equals("in")) {
                            switch (motionEvent.getAction()) {
                                case 0: {
                                    try {
                                        button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_pressed.9.png"));
                                    } catch (Exception exxx) {
                                        exxx.printStackTrace();
                                    }
                                    break;
                                }
                                case 1: {
                                    try {
                                        button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_yes.9.png"));
                                    } catch (Exception exxx) {
                                        exxx.printStackTrace();
                                    }
                                    dialog.dismiss();

                                    final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(GameMainActivity.mPlay, R.style.dialog_pause);
                                    ViewGroup viewGroup = GameMainActivity.mPlay.findViewById(android.R.id.content);
                                    View dialogView = LayoutInflater.from(GameMainActivity.mPlay).inflate(R.layout.dialog_update_app, viewGroup, false);
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

//                                            if (ssButtonPressListener2 != null) {
//                                                ssButtonPressListener2.onPress(dialog);
//                                                //break;
//                                            }


                                            try {
                                                GameMainActivity.mPlay.mDollar.updateDollar();
                                                //mDollar.reset();
                                                GameMainActivity.mPlay.isThereAnyPopupDLG = false;
                                                GameMainActivity.mPlay.LevelResultCODE = GameConfiguration.LevelResultCODE_DEFAULT;
                                                GameMainActivity.mPlay.mSound.playClick();
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

                                            IPreferences.getInstance(GameMainActivity.mPlay).saveRateStatus(true);

                                            alertDialog.dismiss();
                                            mSound.playClick();
                                            try {
                                                GameMainActivity.mPlay.isHasClickYesRateDialogBefore = true;

                                                Intent viewIntent =
                                                        new Intent("android.intent.action.VIEW",
                                                                Uri.parse("https://play.google.com/store/apps/details?id=com.funkyland.tileconnecttravel"));
                                                GameMainActivity.mPlay.startActivity(viewIntent);
                                            } catch (Exception e) {
                                                Toast.makeText(GameMainActivity.mPlay, "Unable to Connect Try Again...",
                                                        Toast.LENGTH_LONG).show();
                                                e.printStackTrace();
                                            }

                                        }
                                    });

                                    if (!alertDialog.isShowing()) {
                                        alertDialog.show();
                                    }


//                                if (ssButtonPressListener3 != null) {
//                                    ssButtonPressListener3.onPress(dialog);
//                                    break;
//                                }
                                    break;
                                }
                            }
                        } else {
                            switch (motionEvent.getAction()) {
                                case 0: {
                                    try {
                                        button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_pressed.9.png"));
                                    } catch (Exception exxx) {
                                        exxx.printStackTrace();
                                    }
                                    break;
                                }
                                case 1: {
                                    try {
                                        button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_yes.9.png"));
                                    } catch (Exception exxx) {
                                        exxx.printStackTrace();
                                    }
                                    dialog.dismiss();
                                    if (ssButtonPressListener3 != null) {
                                        ssButtonPressListener3.onPress(dialog);
                                        break;
                                    }
                                    break;
                                }
                            }
                        }

                    }
                    return true;

                }
            });


            linearLayout3.addView((View) button);
            linearLayout.addView((View) linearLayout3);
            contentView.addView((View) linearLayout);
            dialog.setContentView((View) contentView);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } catch (Exception eee) {
            eee.printStackTrace();
        }
    }


    public static void updateAppDialog2() {
        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(GameMainActivity.mPlay,
                R.style.dialog_pause);
        ViewGroup viewGroup = GameMainActivity.mPlay.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(GameMainActivity.mPlay).inflate(R.layout.dialog_update_app, viewGroup, false);
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
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.funkyland.tileconnecttravel"));
                    GameMainActivity.mPlay.startActivity(viewIntent);
                } catch (Exception e) {
                    Toast.makeText(GameMainActivity.mPlay, "Unable to Connect Try Again...",
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
