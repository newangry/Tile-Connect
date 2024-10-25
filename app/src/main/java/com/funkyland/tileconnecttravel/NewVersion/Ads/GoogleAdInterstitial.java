package com.funkyland.tileconnecttravel.NewVersion.Ads;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.funkyland.tileconnecttravel.R;

public class GoogleAdInterstitial {

    private InterstitialAd mInterstitialAd;
    public static Activity activity;
    public static OnclickInter onclickInter;

    public GoogleAdInterstitial(Activity activity) {
        this.activity = activity;
        Interstitial_Ad1();
    }

    public void Interstitial_Ad1() {
//        Dialog progress = new Dialog(activity);
//        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        progress.setContentView(R.layout.dialog_loader);
//        progress.setCancelable(false);
//        progress.show();

        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, activity.getString(R.string.AdUnitId_InterstitialAd),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(activity);
                        }

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
//                                progress.dismiss();

                                //1
                                onclickInter.clicked();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                //Toast.makeText(activity, "1", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
//                        progress.dismiss();

                        //2
                        onclickInter.clicked();
                    }
                });
    }


    public interface OnclickInter {
        void clicked();
    }

    public static void setInterfaceInter(OnclickInter onclickInter1) {
        onclickInter = onclickInter1;
    }

}
