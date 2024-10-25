/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funkyland.tileconnecttravel.OldVersion;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ILoserDialog {

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

    public static void showShareDialog(final Context context, final int money, final IButtonListener ssButtonPressListener, final IButtonListener ssButtonPressListener2, final IButtonListener ssButtonPressListener3) {
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
            textView.setText((CharSequence) "You could do it Better!");
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
            textView2.setText((CharSequence) "Current money  : " + Integer.toString(money) + "$");
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
            button.setText((CharSequence) "Play Again");
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
                                button.setBackgroundDrawable(IResourceController.getAssertNinePatch(context, "mbappss_rate_btn_no.9.png"));
                            } catch (Exception exxx) {
                                exxx.printStackTrace();
                            }
                            dialog.dismiss();
                            if (ssButtonPressListener2 != null) {
                                ssButtonPressListener2.onPress(dialog);
                                break;
                            }
                            break;
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
}