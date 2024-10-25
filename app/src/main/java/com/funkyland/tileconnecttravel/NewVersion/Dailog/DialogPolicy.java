package com.funkyland.tileconnecttravel.NewVersion.Dailog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.R;

public class DialogPolicy {


    private Activity activity;
    private AlertDialog.Builder builder;

    public DialogPolicy(Activity activity) {
        this.activity = activity;
    }

    public void Show() {

        builder = new AlertDialog.Builder(activity, R.style.dialog_pause);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_policy, viewGroup, false);
        builder.setView(dialogView);


        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.setCancelable(false);
        alertDialog.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        ImageView btn_close = dialogView.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                activity.finishAffinity();
            }
        });


        TextView btn_accept = dialogView.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPreferences.getInstance(activity).savePolicyStatus(true);
                alertDialog.dismiss();
            }
        });


        TextView btn_learn_more = dialogView.findViewById(R.id.btn_learn_more);
        btn_learn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(activity.getResources().getString(R.string.policy_link)));
                    activity.startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }


    }
}
