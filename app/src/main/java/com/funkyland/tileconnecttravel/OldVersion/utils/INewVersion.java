package com.funkyland.tileconnecttravel.OldVersion.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.funkyland.tileconnecttravel.NewVersion.Activity.StartActivity;
import com.funkyland.tileconnecttravel.OldVersion.GameConfiguration;
import com.funkyland.tileconnecttravel.OldVersion.GameMainActivity;
import com.funkyland.tileconnecttravel.OldVersion.IButtonListener;
import com.funkyland.tileconnecttravel.OldVersion.INewVersionDialog;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class INewVersion extends ISprite {

    public BitmapTextureAtlas buttoniad_BTA;
    public Sprite buttoniad_SP;
    public TextureRegion buttoniad_TR;

    public void onClickButtonINew() {

        //chua shown iad dialog
        INewVersionDialog.showINWDialog(GameMainActivity.mPlay, true, null,
                new IButtonListener() {
                    @Override
                    public void onPress(final Dialog dialog) {
                    }
                },
                new IButtonListener() {
                    @Override
                    public void onPress(final Dialog dialog) {
                        //open app
                        try {

                            GameMainActivity.instance.startActivity(new Intent(GameMainActivity.mPlay, StartActivity.class));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, true
        );
    }

    @Override
    public void onCreate(final Context context, final Engine engine, final Camera camera) {
        this.ini(context, engine, camera);
    }

    @Override
    public void onDestroy() {
    }

    public void reset() {
        try {
            this.mScene.attachChild(this.buttoniad_SP);
            this.mScene.registerTouchArea((Scene.ITouchArea) this.buttoniad_SP);
        } catch (Exception exx) {
            exx.printStackTrace();
        }
    }

    @Override
    public void onLoadResources() {
        this.buttoniad_BTA = new BitmapTextureAtlas(128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.buttoniad_TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttoniad_BTA, this.mContext, "new_version.png", 0, 0);
        this.mEngine.getTextureManager().loadTextures(this.buttoniad_BTA);
    }

    @Override
    public void onLoadScene(final Scene mScene) {
        this.mScene = mScene;
        final int n = (int) (this.buttoniad_TR.getWidth() * GameConfiguration.getRaceWidth());
        final int n2 = n * this.buttoniad_TR.getHeight() / this.buttoniad_TR.getWidth();
        this.buttoniad_SP = new Sprite((float) (GameConfiguration.SCREENWIDTH - n), (float) (-10 + (GameConfiguration.SCREENHIEGHT - 3 * n2)), (float) n, (float) n2, this.buttoniad_TR) {
            @Override
            public boolean onAreaTouched(final TouchEvent touchEvent, final float n, final float n2) {
                if (touchEvent.getAction() == 0) {
                    GameMainActivity.mSound.playClick();
                    INewVersion.this.buttoniad_SP.setScale(1.3f);
                } else if (touchEvent.getAction() == 1) {
                    INewVersion.this.buttoniad_SP.setScale(1.0f);
                    INewVersion.this.onClickButtonINew();
                }
                return true;
            }
        };
        this.mScene.registerTouchArea((Scene.ITouchArea) this.buttoniad_SP);
        this.mScene.attachChild(this.buttoniad_SP);
    }
}
