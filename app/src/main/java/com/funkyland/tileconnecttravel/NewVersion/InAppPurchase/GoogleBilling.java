package com.funkyland.tileconnecttravel.NewVersion.InAppPurchase;

import static com.android.billingclient.api.BillingClient.SkuType.INAPP;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.funkyland.tileconnecttravel.NewVersion.Utils.Constant;
import com.funkyland.tileconnecttravel.NewVersion.Utils.IPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoogleBilling implements PurchasesUpdatedListener {

    public BillingClient billingClient;
    private static PaymentListner paymentListner;
    private String TAG = getClass().getSimpleName();

    //################# Init #######################
    private Activity activity;

    public GoogleBilling(Activity activity) {
        this.activity = activity;
    }

    public void Initialize() {
        Log.d(TAG, "Initialize : ");
        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases().setListener(this).build();
    }

    public void connectTogoogleBilling() {
        Log.d(TAG, "connectTogoogleBilling : ");

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                Log.d(TAG, "onBillingServiceDisconnected : ");


                //Toast.makeText(activity, "Payment service disconnected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                Log.d(TAG, "getResponseCode : " + billingResult.getResponseCode());
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    Log.d(TAG, "getProductDetail : ");
                    getProductDetail();
                }

            }
        });
    }


    //################# Payment flow #######################

    private void getProductDetail() {

        List<String> productId = new ArrayList<>();
        productId.add(Constant.PACK_1);
        productId.add(Constant.PACK_2);
        productId.add(Constant.PACK_3);
        productId.add(Constant.PACK_4);
        productId.add(Constant.PACK_5);
        productId.add(Constant.PACK_6);
        productId.add(Constant.PACK_7);
        productId.add(Constant.PACK_8);
        productId.add(Constant.PACK_9);
        productId.add(Constant.PACK_10);
        productId.add(Constant.PACK_11);
        productId.add(Constant.PACK_12);
        productId.add(Constant.PACK_13);
        productId.add(Constant.PACK_14);

        SkuDetailsParams skuDetailsParams = SkuDetailsParams
                .newBuilder()
                .setSkusList(productId)
                .setType(INAPP)
                .build();

        billingClient.querySkuDetailsAsync(skuDetailsParams, new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {

                Log.d(TAG, "getResponseCode : " + billingResult.getResponseCode());

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK || list != null) {

                    for (SkuDetails skuDetails : list) {

                        Log.d(TAG, "SKU : " + skuDetails.getSku());
                        Log.d(TAG, "PRICE : " + skuDetails.getPrice());
                        Log.d(TAG, "CurrencyCode : " + skuDetails.getPriceCurrencyCode());

                        if (skuDetails.getSku().equals(Constant.PACK_1)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_1_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_1_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());

                        } else if (skuDetails.getSku().equals(Constant.PACK_2)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_2_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_2_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());

                        } else if (skuDetails.getSku().equals(Constant.PACK_3)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_3_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_3_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());

                        } else if (skuDetails.getSku().equals(Constant.PACK_4)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_4_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_4_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_5)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_5_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_5_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());

                        } else if (skuDetails.getSku().equals(Constant.PACK_6)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_6_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_6_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_7)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_7_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_7_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_8)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_8_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_8_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_9)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_9_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_9_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_10)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_10_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_10_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_11)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_11_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_11_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_12)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_12_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_12_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_13)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_13_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_13_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        } else if (skuDetails.getSku().equals(Constant.PACK_14)) {

                            IPreferences.getInstance(activity).setString(Constant.PACK_14_PRICE, skuDetails.getPrice());
                            IPreferences.getInstance(activity).setString(Constant.PACK_14_CURRENCY_CODE, skuDetails.getPriceCurrencyCode());
                        }
                    }
                }
            }
        });

    }

    public void OpenPaymentFlow(String GoogleProductID) {

        List<String> skuList = new ArrayList<>();
        skuList.add(GoogleProductID);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                            if (skuDetailsList != null && skuDetailsList.size() > 0) {
                                BillingFlowParams flowParams = BillingFlowParams.newBuilder().setSkuDetails(skuDetailsList.get(0)).build();
                                billingClient.launchBillingFlow(activity, flowParams);
                            }
                        } else {
                            paymentListner.FlowListner(false, GoogleProductID);
                        }
                    }
                });

    }

    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
        Log.d(TAG, "onPurchasesUpdated");

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                    for (Purchase purchase : list) {
                        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
                            handlePurchase(purchase);
                        }
                    }
                } else {
                    paymentListner.FlowListner(false, "");
                }
            }
        });


    }

    public void handlePurchase(Purchase purchases) {

        ConsumeParams consumeParams = ConsumeParams.newBuilder().setPurchaseToken(purchases.getPurchaseToken()).build();
        List<String> OrderList = IPreferences.getInstance(activity).getOrderID(Constant.ORDERID_LIST);

        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, String s) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    try {
                        JSONObject jsonObject = new JSONObject(purchases.getOriginalJson());
                        String productId = jsonObject.getString("productId");
                        String orderId = jsonObject.getString("orderId");

                        List<String> OrderListt = IPreferences.getInstance(activity).getOrderID(Constant.ORDERID_LIST);
                        if (OrderListt == null) {

                            List<String> list = new ArrayList<>();
                            list.add(orderId);
                            IPreferences.getInstance(activity).setOrderID(Constant.ORDERID_LIST, list);

                            updateHeart(productId);
                            return;
                        } else {
                            for (String orderid : OrderList) {
                                if (orderid.equals(orderId)) {

                                    OrderList.add(orderId);
                                    IPreferences.getInstance(activity).setOrderID(Constant.ORDERID_LIST, OrderList);

                                    updateHeart(productId);
                                    return;
                                } else {

                                    OrderList.add(orderId);
                                    IPreferences.getInstance(activity).setOrderID(Constant.ORDERID_LIST, OrderList);

                                    updateHeart(productId);
                                }
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    public static boolean isopenSuccessDialog = true;

    private void updateHeart(String productId) {

        if (!isopenSuccessDialog) {
            return;
        }
        isopenSuccessDialog = false;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("Kishan", " " + productId);
                paymentListner.FlowListner(true, productId);

            }
        });

        if (productId.equals(Constant.PACK_1)) {

            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 30);
            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 30);
            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 30);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_1, false);
        }

        if (productId.equals(Constant.PACK_2)) {

            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 50);
            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 50);
            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 50);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_2, false);

        }

        if (productId.equals(Constant.PACK_3)) {
            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 100);
            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 100);
            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 100);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_3, false);
        }

        if (productId.equals(Constant.PACK_4)) {
            IPreferences.getInstance(activity).saveIAdShownStatus(false);
        }

        if (productId.equals(Constant.PACK_5)) {
            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 30);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_5, false);
        }
        if (productId.equals(Constant.PACK_6)) {
            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 50);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_6, false);
        }
        if (productId.equals(Constant.PACK_7)) {
            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 100);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_7, false);
        }

        if (productId.equals(Constant.PACK_8)) {
            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 30);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_8, false);
        }
        if (productId.equals(Constant.PACK_9)) {
            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 50);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_9, false);
        }
        if (productId.equals(Constant.PACK_10)) {
            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 100);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_10, false);
        }

        if (productId.equals(Constant.PACK_11)) {
            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 30);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_11, false);
        }
        if (productId.equals(Constant.PACK_12)) {
            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 50);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_12, false);
        }
        if (productId.equals(Constant.PACK_13)) {
            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 100);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_13, false);
        }

        if (productId.equals(Constant.PACK_14)) {
            IPreferences.getInstance(activity).setHint(IPreferences.getInstance(activity).getHint() + 150);
            IPreferences.getInstance(activity).setOneKindRemove(IPreferences.getInstance(activity).getOneKindRemove() + 150);
            IPreferences.getInstance(activity).setSuffle(IPreferences.getInstance(activity).getSuffle() + 150);
            IPreferences.getInstance(activity).setBoolean(Constant.PACK_14, false);
        }

//
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (GameMainActivity.mPlay == null) {
//
//                } else {
//                    GameMainActivity.mPlay.mButtonOneKind.setOneKindRemoveText();
//                    GameMainActivity.mPlay.mButtonHint.setHintText();
//                    GameMainActivity.mPlay.mButtonSuffle.setSuffleText();
//                }
//            }
//        });

    }


    public void setPaymentListner(PaymentListner paymentListner) {
        this.paymentListner = paymentListner;
    }

    public interface PaymentListner {
        public void FlowListner(Boolean paymentsucsess, String pack);
    }


}
