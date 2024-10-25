package com.funkyland.tileconnecttravel.NewVersion.Dailog;


import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.NewVersion.Utils.DialogAction;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

import java.util.Date;

public class DialogPurchaseAllFeature implements View.OnClickListener {

    DialogAction dialogAction;
    private Activity activity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private CountDownTimer countDownTimer;
    long mmillisUntilFinished;
    Date date;

    public DialogPurchaseAllFeature(Activity activity) {
        this.activity = activity;
    }

    public void setDialogAction(DialogAction dialogAction) {
        this.dialogAction = dialogAction;
    }

    public void show() {

        builder = new AlertDialog.Builder(activity, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_purchase, viewGroup, false);

        builder.setView(dialogView);

        alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCancelable(false);
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        //-------------------------- Pack 14---------------------------
        RelativeLayout card_0 = dialogView.findViewById(R.id.card_0);
//        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_14)) {
//            card_0.setVisibility(View.GONE);
//        } else {
//            card_0.setVisibility(View.VISIBLE);
//        }


        TextView time = dialogView.findViewById(R.id.time);

        long milisecond = Pereferences.get_OfferEnds();

        if (milisecond <= 0) {
            milisecond = 86400000;
        } else {
            Date date = new Date();
            Date date1 = Pereferences.get_Date();
            long s = date.getTime() - date1.getTime();
            milisecond = Pereferences.get_OfferEnds() - s;
            if (milisecond <= 0) {
                Pereferences.set_OfferEnds(0);
                milisecond = 86400000;
            }

        }

        countDownTimer = new CountDownTimer(milisecond, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                mmillisUntilFinished = millisUntilFinished;
                Pereferences.set_OfferEnds(mmillisUntilFinished);

                date = new Date();
                Pereferences.set_Date(date);

                int hourse = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                int minute = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int second = (int) ((millisUntilFinished / 1000) % 60);

                time.setText("offer ends in " + hourse + ":" + minute + ":" + second);
            }

            public void onFinish() {
                Pereferences.set_OfferEnds(0);
            }

        };

        countDownTimer.start();




        TextView txt_pack_0_price = dialogView.findViewById(R.id.txt_pack_0_price);
        txt_pack_0_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_14_PRICE));
        txt_pack_0_price.setOnClickListener(this);
        //-------------------------- Pack 14---------------------------

        //-------------------------- Pack 1---------------------------
        RelativeLayout card_1 = dialogView.findViewById(R.id.card_1);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_1)) {
            card_1.setVisibility(View.GONE);
        } else {
            card_1.setVisibility(View.VISIBLE);
        }


        TextView txt_pack_1_price = dialogView.findViewById(R.id.txt_pack_1_price);
        txt_pack_1_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_1_PRICE));
        txt_pack_1_price.setOnClickListener(this);
        //-------------------------- Pack 1---------------------------

        //-------------------------- Pack 2---------------------------
        RelativeLayout card_2 = dialogView.findViewById(R.id.card_2);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_2)) {
            card_2.setVisibility(View.GONE);
        } else {
            card_2.setVisibility(View.VISIBLE);
        }


        TextView txt_pack_2_price = dialogView.findViewById(R.id.txt_pack_2_price);
        txt_pack_2_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_2_PRICE));
        txt_pack_2_price.setOnClickListener(this);
        //-------------------------- Pack 2---------------------------

        //-------------------------- Pack 3---------------------------
        RelativeLayout card_3 = dialogView.findViewById(R.id.card_3);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_3)) {
            card_3.setVisibility(View.GONE);
        } else {
            card_3.setVisibility(View.VISIBLE);
        }


        TextView txt_pack_3_price = dialogView.findViewById(R.id.txt_pack_3_price);
        txt_pack_3_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_3_PRICE));
        txt_pack_3_price.setOnClickListener(this);
        //-------------------------- Pack 3---------------------------


        //-------------------------- Pack 4---------------------------
        RelativeLayout card_4 = dialogView.findViewById(R.id.card_4);
        if (IPreferences.getInstance(activity).getIAdShownStatus() == false) {
            card_4.setVisibility(View.GONE);
        } else {
            card_4.setVisibility(View.VISIBLE);
        }


        TextView txt_pack_4_price = dialogView.findViewById(R.id.txt_pack_4_price);
        txt_pack_4_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_4_PRICE));
        txt_pack_4_price.setOnClickListener(this);
        //-------------------------- Pack 4---------------------------


        //-------------------------- Pack 5 One Kind---------------------------
        RelativeLayout card_5 = dialogView.findViewById(R.id.card_5);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_5)) {
            card_5.setVisibility(View.GONE);
        } else {
            card_5.setVisibility(View.VISIBLE);
        }


        TextView txt_pack_5_price = dialogView.findViewById(R.id.txt_pack_5_price);
        txt_pack_5_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_5_PRICE));
        txt_pack_5_price.setOnClickListener(this);
        //-------------------------- Pack 5 One Kind---------------------------


        //-------------------------- 6 One Kind---------------------------
        RelativeLayout card_6 = dialogView.findViewById(R.id.card_6);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_6)) {
            card_6.setVisibility(View.GONE);
        } else {
            card_6.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_6_price = dialogView.findViewById(R.id.txt_pack_6_price);
        txt_pack_6_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_6_PRICE));
        txt_pack_6_price.setOnClickListener(this);
        //-------------------------- Pack 6 One Kind---------------------------


        //-------------------------- 7 One Kind---------------------------
        RelativeLayout card_7 = dialogView.findViewById(R.id.card_7);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_7)) {
            card_7.setVisibility(View.GONE);
        } else {
            card_7.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_7_price = dialogView.findViewById(R.id.txt_pack_7_price);
        txt_pack_7_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_7_PRICE));
        txt_pack_7_price.setOnClickListener(this);
        //-------------------------- Pack 7 One Kind---------------------------


        //-------------------------- Pack 8 Suffle---------------------------
        RelativeLayout card_8 = dialogView.findViewById(R.id.card_8);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_8)) {
            card_8.setVisibility(View.GONE);
        } else {
            card_8.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_8_price = dialogView.findViewById(R.id.txt_pack_8_price);
        txt_pack_8_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_8_PRICE));
        txt_pack_8_price.setOnClickListener(this);
        //-------------------------- Pack 8 Suffle---------------------------

        //-------------------------- Pack 9 Suffle---------------------------
        RelativeLayout card_9 = dialogView.findViewById(R.id.card_9);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_9)) {
            card_9.setVisibility(View.GONE);
        } else {
            card_9.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_9_price = dialogView.findViewById(R.id.txt_pack_9_price);
        txt_pack_9_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_9_PRICE));
        txt_pack_9_price.setOnClickListener(this);
        //-------------------------- Pack 9 Suffle---------------------------


        //-------------------------- Pack 10 Suffle---------------------------
        RelativeLayout card_10 = dialogView.findViewById(R.id.card_10);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_10)) {
            card_10.setVisibility(View.GONE);
        } else {
            card_10.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_10_price = dialogView.findViewById(R.id.txt_pack_10_price);
        txt_pack_10_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_10_PRICE));
        txt_pack_10_price.setOnClickListener(this);
        //-------------------------- Pack 10 Suffle---------------------------


        //-------------------------- Pack 11 Hint---------------------------
        RelativeLayout card_11 = dialogView.findViewById(R.id.card_11);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_11)) {
            card_11.setVisibility(View.GONE);
        } else {
            card_11.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_11_price = dialogView.findViewById(R.id.txt_pack_11_price);
        txt_pack_11_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_11_PRICE));
        txt_pack_11_price.setOnClickListener(this);
        //-------------------------- Pack 11 Hint---------------------------

        //-------------------------- Pack 12 Hint---------------------------
        RelativeLayout card_12 = dialogView.findViewById(R.id.card_12);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_12)) {
            card_12.setVisibility(View.GONE);
        } else {
            card_12.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_12_price = dialogView.findViewById(R.id.txt_pack_12_price);
        txt_pack_12_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_12_PRICE));
        txt_pack_12_price.setOnClickListener(this);
        //-------------------------- Pack 12 Hint---------------------------

        //-------------------------- Pack 13 Hint---------------------------
        RelativeLayout card_13 = dialogView.findViewById(R.id.card_13);
        if (IPreferences.getInstance(activity).getBoolean(Constant.PACK_13)) {
            card_13.setVisibility(View.GONE);
        } else {
            card_13.setVisibility(View.VISIBLE);
        }

        TextView txt_pack_13_price = dialogView.findViewById(R.id.txt_pack_13_price);
        txt_pack_13_price.setText(IPreferences.getInstance(activity).getString(Constant.PACK_13_PRICE));
        txt_pack_13_price.setOnClickListener(this);
        //-------------------------- Pack 11 Hint---------------------------


        ImageView btn_close = dialogView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);

        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

    @Override
    public void onClick(View v) {

        Pereferences.set_OfferEnds(mmillisUntilFinished);
        countDownTimer.cancel();

        switch (v.getId()) {
            case R.id.txt_pack_1_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_1);
                alertDialog.dismiss();
                break;
            case   R.id.txt_pack_2_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_2);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_3_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_3);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_4_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_4);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_5_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_5);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_6_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_6);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_7_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_7);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_8_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_8);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_9_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_9);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_10_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_10);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_11_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_11);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_12_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_12);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_13_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_13);
                alertDialog.dismiss();
                break;
            case R.id.txt_pack_0_price:
                dialogAction.clickCloseBtn(true, Constant.PACK_14);
                alertDialog.dismiss();
                break;
            case R.id.btn_close:
                dialogAction.clickCloseBtn(false, "");
                alertDialog.dismiss();
                break;
        }
    }
}
