package com.funkyland.tileconnecttravel.OldVersion.utils;

import android.content.Context;

import com.funkyland.tileconnecttravel.OldVersion.GameMainActivity;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;

public abstract class ISprite
{
    public Camera mCamera;
    public Context mContext;
    public Engine mEngine;
    public GameMainActivity mPlay;
    public Scene mScene;
    
    public ISprite() {
        super();
    }
    
    public void ini(final Context mContext, final Engine mEngine, final Camera mCamera) {
        this.mContext = mContext;
        this.mEngine = mEngine;
        this.mCamera = mCamera;
        this.mPlay = (GameMainActivity)mContext;
    }
    
    public abstract void onCreate(final Context p0, final Engine p1, final Camera p2);
    
    public abstract void onDestroy();
    
    public abstract void onLoadResources();
    
    public abstract void onLoadScene(final Scene p0);
}