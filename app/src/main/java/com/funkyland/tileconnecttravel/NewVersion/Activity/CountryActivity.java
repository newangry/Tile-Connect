package com.funkyland.tileconnecttravel.NewVersion.Activity;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.COUNTRY_ACTIVITY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.funkyland.tileconnecttravel.NewVersion.Adapter.CountryAdapter;
import com.funkyland.tileconnecttravel.NewVersion.Ads.Interstitial_Ad_google;
import com.funkyland.tileconnecttravel.NewVersion.Generators.ILogger;
import com.funkyland.tileconnecttravel.NewVersion.Services.MusicBackground;
import com.funkyland.tileconnecttravel.NewVersion.Services.SoundController;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IUtil;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences;
import com.funkyland.tileconnecttravel.R;

public class CountryActivity extends GameBaseActivity implements CountryAdapter.AdapterClick {

    private static SoundController mSound;
    public MusicBackground musicBackground;
    Interstitial_Ad_google interstitial_ad_google;
    private Activity activity;
    private RecyclerView rv_theme;
    private CountryAdapter countryAdapter;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        HideNavigation(activity);

        setContentView(R.layout.activity_sparkela);

        Interstitial_Ad_Google();

        rv_theme = findViewById(R.id.rv_theme);
        title = findViewById(R.id.title);

        title.setText(getString(R.string.around_the_world_home));

        rv_theme.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        if (getIntent().getStringExtra("khand").equals("asia")) {
            setAdapter(Constant.Country_Asia, Constant.Country_Asia_path, Constant.Country_Asia_image);
        } else if (getIntent().getStringExtra("khand").equals("europe")) {
            setAdapter(Constant.Country_Europe, Constant.Country_Europe_path, Constant.Country_Europe_image);
        } else if (getIntent().getStringExtra("khand").equals("america")) {
            setAdapter(Constant.Country_America, Constant.Country_America_path, Constant.Country_America_image);
        } else if (getIntent().getStringExtra("khand").equals("australia")) {
            setAdapter(Constant.Country_Australia, Constant.Country_Australia_path, Constant.Country_Australia_image);
        } else if (getIntent().getStringExtra("khand").equals("africa")) {
            setAdapter(Constant.Country_Africa, Constant.Country_Africa_path, Constant.Country_Africa_image);
        }


        findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, WorldActivity.class));
                finish();
            }
        });

        (this.musicBackground = new MusicBackground()).loadMusic((Context) this);
        this.musicBackground.play();

        (mSound = new SoundController()).loadSound((Context) this);

    }


    private void setAdapter(Integer[] list, String[] listpath, Integer[] image) {
        countryAdapter = new CountryAdapter(this, list, listpath, image);
        rv_theme.setAdapter(countryAdapter);
        rv_theme.setHasFixedSize(true);
        countryAdapter.setAdapterClick(this);
    }

    @Override
    public void ClickOnItem(int pos, String name) {
        IPreferences.getInstance(mContext).setThemePath("country/" + name);
        Pereferences.set_gameType_InSubType(name);
        ShowAdsNext();
    }


    private void ShowAdsNext() {
        mSound.playClick();

        if (IUtil.isNetworkConnected(activity) && IPreferences.getInstance(activity).getIAdShownStatus()) {
            Interstitial_Ad_google.setInterface(new Interstitial_Ad_google.Onclick() {
                @Override
                public void clicked() {
                    NextActivity();
                }
            });
            interstitial_ad_google.show_method();
        } else {
            NextActivity();
        }
    }


    private void NextActivity() {
        Intent i = new Intent(activity, GameMainActivity.class);
        i.putExtra(COUNTRY_ACTIVITY, true);
        i.putExtra("khand", getIntent().getStringExtra("khand"));
        startActivity(i);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ILogger.LogInfo("onDestroy");
        try {
            this.musicBackground.release();
        } catch (Exception ex) {
            ILogger.LogError("Play onDestroy " + ex.toString());
        }
    }

    @Override
    protected void onPause() {
        try {
            musicBackground.pause();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            musicBackground.resume();
            countryAdapter.notifyDataSetChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Interstitial_Ad_Google() {
        interstitial_ad_google = new Interstitial_Ad_google(activity);
    }

}