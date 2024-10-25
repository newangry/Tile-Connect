package com.funkyland.tileconnecttravel.NewVersion.Dailog;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_1;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_10;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_11;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_12;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_13;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_14;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_2;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_3;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_4;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_5;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_6;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_7;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_8;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.PACK_9;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.funkyland.tileconnecttravel.NewVersion.InAppPurchase.GoogleBilling;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.R;

public class DialogPurchaseSuccessfully {


    private Activity activity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private DialogClicks dialogClick;

    public DialogPurchaseSuccessfully(Activity activity) {
        this.activity = activity;
    }

    public void Init(String packId) {
        builder = new AlertDialog.Builder(activity, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_purchase_successful, viewGroup, false);
        builder.setView(dialogView);

        alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        alertDialog.setCancelable(false);


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

        if (packId.equals(PACK_1)) {
            ll_onekind.setVisibility(View.VISIBLE);
            ll_suffle.setVisibility(View.VISIBLE);
            ll_hint.setVisibility(View.VISIBLE);
            tv_onekind.setText("+30");
            tv_suffle.setText("+30");
            tv_hint.setText("+30");
        } else if (packId.equals(PACK_2)) {
            ll_onekind.setVisibility(View.VISIBLE);
            ll_suffle.setVisibility(View.VISIBLE);
            ll_hint.setVisibility(View.VISIBLE);
            tv_onekind.setText("+50");
            tv_suffle.setText("+50");
            tv_hint.setText("+50");
        } else if (packId.equals(PACK_3)) {
            ll_onekind.setVisibility(View.VISIBLE);
            ll_suffle.setVisibility(View.VISIBLE);
            ll_hint.setVisibility(View.VISIBLE);
            tv_onekind.setText("+100");
            tv_suffle.setText("+100");
            tv_hint.setText("+100");
        } else if (packId.equals(PACK_4)) {
            des.setText(activity.getString(R.string.purchase_ads_successfully));
        } else if (packId.equals(PACK_5)) {
            ll_onekind.setVisibility(View.VISIBLE);
            tv_onekind.setText("+30");
        } else if (packId.equals(PACK_6)) {
            ll_onekind.setVisibility(View.VISIBLE);
            tv_onekind.setText("+50");
        } else if (packId.equals(PACK_7)) {
            ll_onekind.setVisibility(View.VISIBLE);
            tv_onekind.setText("+100");
        } else if (packId.equals(PACK_8)) {
            ll_suffle.setVisibility(View.VISIBLE);
            tv_suffle.setText("+30");
        } else if (packId.equals(PACK_9)) {
            ll_suffle.setVisibility(View.VISIBLE);
            tv_suffle.setText("+50");
        } else if (packId.equals(PACK_10)) {
            ll_suffle.setVisibility(View.VISIBLE);
            tv_suffle.setText("+100");
        } else if (packId.equals(PACK_11)) {
            ll_hint.setVisibility(View.VISIBLE);
            tv_hint.setText("+30");
        } else if (packId.equals(PACK_12)) {
            ll_hint.setVisibility(View.VISIBLE);
            tv_hint.setText("+50");
        } else if (packId.equals(PACK_13)) {
            ll_hint.setVisibility(View.VISIBLE);
            tv_hint.setText("+100");
        } else if (packId.equals(PACK_14)) {
            ll_onekind.setVisibility(View.VISIBLE);
            ll_suffle.setVisibility(View.VISIBLE);
            ll_hint.setVisibility(View.VISIBLE);
            tv_onekind.setText("+150");
            tv_suffle.setText("+150");
            tv_hint.setText("+150");
        }

        TextView btn_ok = dialogView.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                dialogClick.click(true);
                GoogleBilling.isopenSuccessDialog = true;
            }
        });

        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

    public void setDialogClick(DialogClicks dialogClick) {
        this.dialogClick = dialogClick;
    }

    public interface DialogClicks {
        public void click(Boolean isClick);
    }

}
