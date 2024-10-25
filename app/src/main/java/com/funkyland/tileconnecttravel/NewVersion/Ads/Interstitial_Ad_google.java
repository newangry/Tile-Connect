package com.funkyland.tileconnecttravel.NewVersion.Ads;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.funkyland.tileconnecttravel.R;

public class Interstitial_Ad_google {

    //5
    private InterstitialAd mInterstitialAd;
    public static Activity activity;
    public static Onclick onclick;

    public Interstitial_Ad_google(Activity activity) {
        this.activity = activity;

        Interstitial_Ad1();
    }

    public void Interstitial_Ad1() {

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

                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    Log.d("TAG_Interstitial", "The ad was dismissed.");
                                    super.onAdDismissedFullScreenContent();
                                    mInterstitialAd = null;
                                    Interstitial_Ad1();

                                    //1
                                    onclick.clicked();
                                }
                            });
                        }else {
                            //2
                            onclick.clicked();
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }


                });

    }

    public void show_method() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            //3
            onclick.clicked();
        }
    }

    public interface Onclick {
        void clicked();
    }

    public static void setInterface(Onclick onclick1) {
        onclick = onclick1;
    }

}
