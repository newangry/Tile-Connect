package com.funkyland.tileconnecttravel.NewVersion.Dailog;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_14;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.pm.ActivityInfo;

import androidx.appcompat.app.AlertDialog;

import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.NewVersion.Utils.DialogAction;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

import java.util.Date;

public class DialogPurchaseOneCard {

    long mmillisUntilFinished;
    Date date;
    DialogAction dialogAction;
    private Activity activity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private CountDownTimer countDownTimer;

    public DialogPurchaseOneCard(Activity activity) {
        this.activity = activity;

    }

    public void show() {
        builder = new AlertDialog.Builder(activity, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_single_pack_purchase, viewGroup, false);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        alertDialog.setCancelable(false);

        ImageView btn_close = dialogView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pereferences.set_OfferEnds(mmillisUntilFinished);
                countDownTimer.cancel();
                alertDialog.dismiss();
                dialogAction.clickCloseBtn(false, PACK_14);
            }
        });

        TextView btn_buy = dialogView.findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pereferences.set_OfferEnds(mmillisUntilFinished);
                countDownTimer.cancel();
                alertDialog.dismiss();
                dialogAction.clickCloseBtn(true, PACK_14);

            }
        });

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

        TextView time = dialogView.findViewById(R.id.time);
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

        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

    public void setDialogAction(DialogAction dialogAction) {
        this.dialogAction = dialogAction;
    }
}
