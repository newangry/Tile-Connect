package com.funkyland.tileconnecttravel.NewVersion.Activity;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.funkyland.tileconnecttravel.NewVersion.Generators.GameConfiguration;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.view.RenderSurfaceView;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import java.util.Locale;

public class GameBaseActivity extends BaseGameActivity {


    public static Context mContext;
    public Engine mEngine;
    public Scene mScene;
    public Camera mCamera;
    public Activity activity;

    public FrameLayout mFrameLayout;
    public static int currentApiVersion;


    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);

        GameConfiguration.isConnected = hasConnection();

        this.mContext = (Context) this;
        this.activity = (Activity) this;
        this.getWindow().getAttributes().windowAnimations = 2131492957;

        SetLaunguageSelected(IPreferences.getInstance(mContext).getLaungauge());

    }


    @Override
    public void onLoadComplete() {
    }

    @Override
    public Engine onLoadEngine() {
        GameConfiguration.getDisplayScreen(this);
        this.mCamera = new Camera(0.0f, 0.0f,GameConfiguration.SCREENWIDTH, GameConfiguration.SCREENHIEGHT);
        return this.mEngine =
                new Engine(new EngineOptions(true,
                        GameConfiguration.ScreenOrientation_Default,
                        new RatioResolutionPolicy(GameConfiguration.SCREENWIDTH, GameConfiguration.SCREENHIEGHT),
                        this.mCamera).setNeedsSound(true).setNeedsMusic(true));
    }

    @Override
    public void onLoadResources() {
    }

    @Override
    public Scene onLoadScene() {
        (this.mScene = new Scene()).setTouchAreaBindingEnabled(true);
        return this.mScene;
    }


    public boolean hasConnection() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnected()) {
                return true;
            }
            NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnected()) {
                return true;
            }
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    protected void onSetContentView() {
        this.setContentView(R.layout.activity_main);
        final FrameLayout mFrameLayout = findViewById(R.id.frame);
        (this.mRenderSurfaceView = new RenderSurfaceView((Context) this)).setRenderer(this.mEngine);
        mFrameLayout.addView((View) this.mRenderSurfaceView, new ViewGroup.LayoutParams((ViewGroup.LayoutParams) super.createSurfaceViewLayoutParams()));
        mFrameLayout.refreshDrawableState();
        GameBaseActivity.this.mFrameLayout = mFrameLayout;

    }


    public static void HideNavigation(Activity activity) {
        currentApiVersion = Build.VERSION.SDK_INT;

        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            activity.getWindow().getDecorView().setSystemUiVisibility(Constant.ui_flags);


            final View decorView = activity.getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(Constant.ui_flags);
                            }
                        }
                    });
        }


    }


    private void SetLaunguageSelected(int laungauge) {

        switch (laungauge) {
            case 1:
                setLanguage(activity, "en");
                break;
            case 2:
                setLanguage(activity, "pt");
                break;
            case 3:
                setLanguage(activity, "vi");
                break;
            case 4:
                setLanguage(activity, "in");
                break;
            case 5:
                setLanguage(activity, "th");
                break;
            case 6:
                setLanguage(activity, "ru");
                break;
            case 7:
                setLanguage(activity, "ja");
                break;
            case 8:
                setLanguage(activity, "ko");
                break;
            case 9:
                setLanguage(activity, "zh");
                break;
        }

    }

    private void setLanguage(Activity activity, String language) {
        Locale locale = new Locale(language);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}
