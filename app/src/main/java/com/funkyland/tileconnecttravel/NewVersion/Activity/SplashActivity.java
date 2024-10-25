package com.funkyland.tileconnecttravel.NewVersion.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.funkyland.tileconnecttravel.NewVersion.InAppPurchase.GoogleBilling;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.R;

public class SplashActivity extends AppCompatActivity {

    public static Activity activity;

    public Context mContext;
    private TextView txt_percentage;
    int i = 0;

    private static GoogleBilling googleBilling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = (Context) this;
        this.activity = (Activity) this;

        Log.d("Kishan", "Test");

        GameBaseActivity.HideNavigation(activity);
        setContentView(R.layout.activity_splash);

        txt_percentage = findViewById(R.id.txt_percentage);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int loadingImgIndex = 0;

            public void run() {
                loadingImgIndex++;

                if (IPreferences.getInstance(SplashActivity.this).getInAppPurchase_() == 1) {
                    if (googleBilling == null) {
                        googleBilling = new GoogleBilling(activity);
                        googleBilling.Initialize();
                        googleBilling.connectTogoogleBilling();
                    }
                } else {
                    googleBilling = new GoogleBilling(activity);
                    googleBilling.Initialize();
                    googleBilling.connectTogoogleBilling();

                    IPreferences.getInstance(activity).saveInAppPurchase_(1);
                }

                if (loadingImgIndex == 100) {
                    handler.removeCallbacks(this::run);
                    startActivity(new Intent(activity, StartActivity.class));
                    finish();
                    return;
                }

                txt_percentage.setText("" + loadingImgIndex + "%");
                handler.postDelayed(this, 30);
            }
        };
        handler.postDelayed(runnable, 30);

    }


}