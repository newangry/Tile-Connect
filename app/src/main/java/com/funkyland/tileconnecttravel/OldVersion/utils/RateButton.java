/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funkyland.tileconnecttravel.OldVersion.utils;

import android.content.Context;

import com.funkyland.tileconnecttravel.OldVersion.GameConfiguration;
import com.funkyland.tileconnecttravel.OldVersion.GameMainActivity;
import com.funkyland.tileconnecttravel.OldVersion.IPreferences;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class RateButton extends ISprite {

    public BitmapTextureAtlas buttonhint_BTA;
    public Sprite buttonhint_SP;
    public TextureRegion buttonhint_TR;
    private Context context;

    public void onClickButtonHint() {

        if (IPreferences.getInstance(context).getBgSound()) {
            IPreferences.getInstance(context).setBgSound(false);
            setIcon();
            GameMainActivity.mPlay.musicBackground.pause();
        } else {
            IPreferences.getInstance(context).setBgSound(true);
            setIcon();
            GameMainActivity.mPlay.musicBackground.play();
        }

    }

    @Override
    public void onCreate(final Context context, final Engine engine, final Camera camera) {
        this.context = context;
        this.ini(context, engine, camera);
    }

    @Override
    public void onDestroy() {
    }

    public void reset() {
        try {
            this.mScene.attachChild(this.buttonhint_SP);
            this.mScene.registerTouchArea((Scene.ITouchArea) this.buttonhint_SP);
        } catch (Exception exx) {
            exx.printStackTrace();
        }
    }

    @Override
    public void onLoadResources() {
        this.buttonhint_BTA = new BitmapTextureAtlas(128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        setIcon();

    }

    private void setIcon() {
        if (IPreferences.getInstance(context).getBgSound()) {
            this.buttonhint_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonhint_BTA, this.mContext, "unmute.png", 0, 0);
        } else {
            this.buttonhint_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonhint_BTA, this.mContext, "mute.png", 0, 0);
        }
        this.mEngine.getTextureManager().loadTextures(this.buttonhint_BTA);
    }

    @Override
    public void onLoadScene(final Scene mScene) {
        this.mScene = mScene;
        final int n = (int) (this.buttonhint_TR.getWidth() * GameConfiguration.getRaceWidth());
        final int n2 = n * this.buttonhint_TR.getHeight() / this.buttonhint_TR.getWidth();
        this.buttonhint_SP = new Sprite((float) (GameConfiguration.SCREENWIDTH - n), (float) (-10 + (GameConfiguration.SCREENHIEGHT - 2 * n2)), (float) n, (float) n2, this.buttonhint_TR) {
            @Override
            public boolean onAreaTouched(final TouchEvent touchEvent, final float n, final float n2) {
                if (touchEvent.getAction() == 0) {
                    GameMainActivity.mSound.playClick();
                    RateButton.this.buttonhint_SP.setScale(1.3f);
                } else if (touchEvent.getAction() == 1) {
                    RateButton.this.buttonhint_SP.setScale(1.0f);
                    RateButton.this.onClickButtonHint();
                }
                return true;
            }
        };
        this.mScene.registerTouchArea((Scene.ITouchArea) this.buttonhint_SP);
        this.mScene.attachChild(this.buttonhint_SP);
    }
}
