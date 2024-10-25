package com.funkyland.tileconnecttravel.NewVersion.Ads;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IUtil;
import com.funkyland.tileconnecttravel.R;

public class GoogleAdBanner {

    View view;
    private Activity activity;
    private LinearLayout linearLayout_banner;

    public GoogleAdBanner(Activity activity, View v) {
        this.activity = activity;
        view = v;
        linearLayout_banner = view.findViewById(R.id.linearLayout_banner);
        linearLayout_banner.setVisibility(View.GONE);
    }

    public void googleBanner() {
        if (IUtil.isNetworkConnected(activity) && IPreferences.getInstance(activity).getIAdShownStatus()) {
            com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(activity);
            adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
            adView.setAdUnitId(activity.getString(R.string.AdUnitId_Banner));
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {

                }

                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                }


                @Override
                public void onAdOpened() {
                }

                @Override
                public void onAdClicked() {
                }

                @Override
                public void onAdClosed() {
                }
            });


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            linearLayout_banner.addView(adView, params);
            linearLayout_banner.setVisibility(View.VISIBLE);

        }
    }


}
