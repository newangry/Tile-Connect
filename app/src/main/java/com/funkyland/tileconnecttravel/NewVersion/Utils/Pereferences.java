package com.funkyland.tileconnecttravel.NewVersion.Utils;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_MODE_CLAS;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_SUB_MODE;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_TYPE;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Constant.GAME_TYPE_EASY;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;

public class Pereferences extends Application {

    static SharedPreferences preferences;
    static SharedPreferences.Editor prefEditor;
    private static Pereferences mInstance;
    private static Context mContext;

    public static synchronized Context getContext() {
        return mContext;
    }

    public static String get_gameType_InSubType() {
        return preferences.getString(GAME_SUB_MODE, "");
    }

    public static void set_gameType_InSubType(String gameType_InSubType) {
        prefEditor.putString(GAME_SUB_MODE, gameType_InSubType).commit();
    }


    public static String get_gameType() {
        return preferences.getString(GAME_TYPE, GAME_TYPE_EASY);
    }

    public static void set_gameType(String gameType) {
        prefEditor.putString(GAME_TYPE, gameType).commit();
    }


    public static String get_gameMode() {
        return preferences.getString(GAME_MODE, GAME_MODE_CLAS);
    }

    public static void set_gameMode(String gameMode) {
        prefEditor.putString(GAME_MODE, gameMode).commit();
    }

    public static boolean get_LockTypeStatus(String key) {
        return preferences.getBoolean(get_gameType() + get_gameMode() + key + "gameLock_", false);
    }

    public static void set_LockTypeStatus(String key, boolean level) {
        prefEditor.putBoolean(get_gameType() + get_gameMode() + key + "gameLock_", level).commit();
    }


    public static long get_OfferEnds() {
        return preferences.getLong("OfferEnds", 0);
    }

    public static void set_OfferEnds(long milisecond) {
        prefEditor.putLong("OfferEnds", milisecond).commit();
    }

    public static Date get_Date() {
        Gson gson = new Gson();
        String my = preferences.getString("Signup", null);
        Date signupModel = gson.fromJson(my, new TypeToken<Date>() {
        }.getType());
        return signupModel;
    }

    public static void set_Date(Date date) {
        Gson gson = new Gson();
        String list = gson.toJson(date);
        prefEditor.putString("Signup", list).commit();
    }


    public static int get_dialog() {
        return preferences.getInt("dialog", 0);
    }

    public static void set_dialog(int r_id) {
        prefEditor.putInt("dialog", r_id).commit();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mContext = this;

        preferences = getSharedPreferences("Pet_Animal", MODE_PRIVATE);
        prefEditor = preferences.edit();


    }

}
