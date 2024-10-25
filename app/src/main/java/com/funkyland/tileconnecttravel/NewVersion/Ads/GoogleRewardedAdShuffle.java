package com.funkyland.tileconnecttravel.NewVersion.Ads;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.R;

public class GoogleRewardedAdShuffle {

    public static Activity activity;
    public static OnclickInter onclickInter;
    public static EndedRewardedAd endedRewardedAd;
    public static RewardedAd mRewardedAd;
    public static boolean Rewardedfail = false;
    private final String TAG = "GoogleRewardedAd";

    public GoogleRewardedAdShuffle(Activity activity) {
        this.activity = activity;

        RewardedAd();

    }

    private void RewardedAd() {

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
        RewardedAd.load(activity, activity.getString(R.string.AdUnitId_RewardedAdShuffle),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Rewardedfail = true;

                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
//                        progress.dismiss();
                        onclickInter.clicked();



//                        endedRewardedAd.endedRwardAd();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d(TAG, "Ad was loaded.");

//                        progress.dismiss();
                        mRewardedAd.show(activity, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                                endedRewardedAd.endedRwardAd();

                            }
                        });


                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad was shown.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.d(TAG, "Ad failed to show.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d(TAG, "Ad was dismissed.");
                                mRewardedAd = null;
                                onclickInter.clicked();
                                GameMainActivity.mPlay.ShowDialogVideoPlaySuccess();
                            }
                        });

                    }
                });

    }


    public interface OnclickInter {
        void clicked();
    }

    public static void setInterfaceInter(OnclickInter onclickInter1) {
        onclickInter = onclickInter1;
    }

    public interface EndedRewardedAd {
        void endedRwardAd();
    }

    public static void setEndedRewardedAd(EndedRewardedAd endedRewardedAd1) {
        endedRewardedAd = endedRewardedAd1;
    }

}
