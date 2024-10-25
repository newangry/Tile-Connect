package com.funkyland.tileconnecttravel.NewVersion.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.funkyland.tileconnecttravel.NewVersion.Generators.ILogger;
import com.funkyland.tileconnecttravel.NewVersion.Services.MusicBackground;
import com.funkyland.tileconnecttravel.NewVersion.Services.SoundController;
import com.funkyland.tileconnecttravel.R;

public class WorldActivity extends GameBaseActivity implements View.OnClickListener{


    private static SoundController mSound;
    public MusicBackground musicBackground;
    private Activity activity;
    private TextView btn_new_asia, btn_new_europe, btn_new_america, btn_new_australia, btn_new_africa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        activity = this;
        HideNavigation(activity);

        btn_new_asia = findViewById(R.id.btn_new_asia);
        btn_new_europe = findViewById(R.id.btn_new_europe);
        btn_new_america = findViewById(R.id.btn_new_america);
        btn_new_australia = findViewById(R.id.btn_new_australia);
        btn_new_africa = findViewById(R.id.btn_new_africa);

        btn_new_asia.setOnClickListener(this);
        btn_new_europe.setOnClickListener(this);
        btn_new_america.setOnClickListener(this);
        btn_new_australia.setOnClickListener(this);
        btn_new_africa.setOnClickListener(this);

        (this.musicBackground = new MusicBackground()).loadMusic((Context) this);
        this.musicBackground.play();

        (mSound = new SoundController()).loadSound((Context) this);

        findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSound.playClick();
                startActivity(new Intent(activity, StartActivity.class));
                finish();
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_asia:
                NextActivity("asia");
                break;
            case R.id.btn_new_europe:
                NextActivity("europe");
                break;
            case R.id.btn_new_america:
                NextActivity("america");
                break;
            case R.id.btn_new_australia:
                NextActivity("australia");
                break;
            case R.id.btn_new_africa:
                NextActivity("africa");
                break;
        }
    }


    private void NextActivity(String khand) {

        mSound.playClick();
        Intent i = new Intent(activity, CountryActivity.class);
        i.putExtra("khand", khand);
        startActivity(i);
        overridePendingTransition(0, 0);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}