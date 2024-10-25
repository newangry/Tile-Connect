package com.funkyland.tileconnecttravel.NewVersion.Button;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.funkyland.tileconnecttravel.NewVersion.Activity.GameMainActivity;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPurchaseAllFeature;
import com.funkyland.tileconnecttravel.NewVersion.Dailog.DialogPurchaseSuccessfully;
import com.funkyland.tileconnecttravel.NewVersion.InAppPurchase.GoogleBilling;
import com.funkyland.tileconnecttravel.NewVersion.Utils.DialogAction;
import com.funkyland.tileconnecttravel.R;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;

public class PurchaseButton {


    private Activity activity;
    private Context context;
    private Engine mEngine;
    private Camera mCamera;


    private ImageView btn_purchase;

    public void onCreate(Activity activity, Context context, Engine mEngine, Camera mCamera) {

        this.activity = activity;
        this.context = context;
        this.mEngine = mEngine;
        this.mCamera = mCamera;

        btn_purchase = activity.findViewById(R.id.btn_purchase);

        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_purchase.setEnabled(false);

                GameMainActivity.mPlay.mSound.playClick();
                GameMainActivity.mPlay.mProgressBar.setPause(true);
                GameMainActivity.mPlay.musicBackground.pause();
                

                DialogPurchaseAllFeature dialogPurchaseAllFeature = new DialogPurchaseAllFeature(activity);
                dialogPurchaseAllFeature.setDialogAction(new DialogAction() {
                    @Override
                    public void clickCloseBtn(Boolean isBuy, String packId) {
                        btn_purchase.setEnabled(true);

                        if (isBuy) {
                            GameMainActivity.mPlay.googleBilling.setPaymentListner(new GoogleBilling.PaymentListner() {
                                @Override
                                public void FlowListner(Boolean paymentsucsess, String pack) {
                                    if (paymentsucsess) {
                                        DialogPurchaseSuccessfully dialogPurchaseSuccessfully = new DialogPurchaseSuccessfully(activity);
                                        dialogPurchaseSuccessfully.setDialogClick(new DialogPurchaseSuccessfully.DialogClicks() {
                                            @Override
                                            public void click(Boolean isClick) {

                                                GameMainActivity.mPlay.mProgressBar.setPause(false);
                                                GameMainActivity.mPlay.musicBackground.resume();

                                                GameMainActivity.mPlay.mButtonOneKind.setOneKindRemoveText();
                                                GameMainActivity.mPlay.mButtonHint.setHintText();
                                                GameMainActivity.mPlay.mButtonSuffle.setSuffleText();
                                            }
                                        });
                                        dialogPurchaseSuccessfully.Init(pack);
                                    } else {
                                        GameMainActivity.mPlay.mProgressBar.setPause(false);
                                        GameMainActivity.mPlay.musicBackground.resume();
                                    }
                                }
                            });
                            GameMainActivity.mPlay.googleBilling.OpenPaymentFlow(packId);
                        } else {
                            GameMainActivity.mPlay.mProgressBar.setPause(false);
                            GameMainActivity.mPlay.musicBackground.resume();
                        }
                    }
                });
                dialogPurchaseAllFeature.show();


            }
        });
    }


}
