package com.funkyland.tileconnecttravel.NewVersion.Utils;

import static com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences.get_gameMode;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences.get_gameType;
import static com.funkyland.tileconnecttravel.NewVersion.Utils.Pereferences.get_gameType_InSubType;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IPreferences {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private static IPreferences instance;
    private Context context;

    private IPreferences(final Context context) {
        super();
        this.context = context;
    }

    public static IPreferences getInstance(final Context context) {
        synchronized (IPreferences.class) {
            if (IPreferences.instance == null) {
                IPreferences.instance = new IPreferences(context);
            }
            return IPreferences.instance;
        }
    }

    private SharedPreferences.Editor getEditor() {
        return this.getSharedPreferences().edit();
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.context);
    }

    public boolean saveCurLevel(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "CurLevel_", n);
        return editor.commit();
    }

    public int getCurLevel() {
        return this.getSharedPreferences().getInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "CurLevel_", 1);
    }


    public boolean saveCurTotalScore(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "CurTotalScore_", n);
        return editor.commit();
    }

    public int getCurTotalScore() {
        return this.getSharedPreferences().getInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "CurTotalScore_", 0);
    }


    public boolean saveTotal_dollar(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "total_dollar", n);
        return editor.commit();
    }

    public boolean saveTotal_level(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "total_level", n);
        return editor.commit();
    }

    public int getTotal_level() {
        return this.getSharedPreferences().getInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "total_level", 1);
    }

    public int getsaveTotal_dollarForHighScoreDialog(String gameType, String gameMode, String InSubType) {
        return this.getSharedPreferences().getInt(gameType + gameMode + InSubType + "total_dollar", 0);
    }

    public int getTotal_dollar() {
        return this.getSharedPreferences().getInt(get_gameType() + get_gameMode() + get_gameType_InSubType() + "total_dollar", 0);
    }

//    public int getTotal_dollarForHighScoreDialog(String gameType, String gameMode, String InSubType) {
//        return this.getSharedPreferences().getInt(gameType + gameMode + InSubType + "CurTotalScore_", 0);
//    }

    public boolean saveStar(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("Star", n);
        return editor.commit();
    }

    public int getStar() {
        return this.getSharedPreferences().getInt("Star", 0);
    }


    public boolean saveInAppPurchase_(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("InAppPurchase_", n);
        return editor.commit();
    }

    public int getInAppPurchase_() {
        return this.getSharedPreferences().getInt("InAppPurchase_", 0);
    }

//    ######################### Function #########################################

    public boolean setHeart(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("total_heart", n);
        return editor.commit();
    }

    public int getHeart() {
        return this.getSharedPreferences().getInt("total_heart", 5);
    }

    public boolean setHint(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("total_hint", n);
        return editor.commit();
    }

    public int getHint() {
        return this.getSharedPreferences().getInt("total_hint", 5);
    }

    public boolean setOneKindRemove(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("total_OneKindRemove", n);
        return editor.commit();
    }

    public int getOneKindRemove() {
        return this.getSharedPreferences().getInt("total_OneKindRemove", 5);
    }

    public boolean setSuffle(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("total_Suffle", n);
        return editor.commit();
    }

    public int getSuffle() {
        return this.getSharedPreferences().getInt("total_Suffle", 5);
    }

//    ##################################################################


//    ######################### Sound #########################################

    public boolean setButtonSound(final boolean n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean("ButtonSound", n);
        return editor.commit();
    }

    public boolean getButtonSound() {
        return this.getSharedPreferences().getBoolean("ButtonSound", true);
    }

    public boolean setBgSound(final boolean n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean("BgSound", n);
        return editor.commit();
    }

    public boolean getBgSound() {
        return this.getSharedPreferences().getBoolean("BgSound", true);
    }

//    ######################### Sound #########################################


    public boolean setLaungauge(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("Laungauge", n);
        return editor.commit();
    }

    public int getLaungauge() {
        return this.getSharedPreferences().getInt("Laungauge", 1);
    }


    public boolean setTheme(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("Theme", n);
        return editor.commit();
    }

    public int getTheme() {
        return this.getSharedPreferences().getInt("Theme", 1);
    }

    public boolean setThemePath(final String path) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putString("ThemePath", path);
        return editor.commit();
    }

    public String getThemePath() {
        return this.getSharedPreferences().getString("ThemePath", "");
    }


    //    ######################### In App Purchase #########################################
    public String getString(String key) {
        return this.getSharedPreferences().getString(key, "");
    }

    public boolean setString(final String key, String value) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putString(key, value);
        return editor.commit();
    }

    public Boolean getBoolean(String key) {
        return this.getSharedPreferences().getBoolean(key, false);
    }

    public boolean setBoolean(final String key, Boolean value) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean(key, value);
        return editor.commit();
    }


    public List<String> getOrderID(String key) {
        Gson gson = new Gson();
        String json = this.getSharedPreferences().getString(key, null);
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> list = gson.fromJson(json, type);
        return list;
    }

    public boolean setOrderID(final String key, List<String> orderList) {
        final SharedPreferences.Editor editor = this.getEditor();
        Gson gson = new Gson();
        String jsonText = gson.toJson(orderList);
        editor.putString(key, jsonText);
        return editor.commit();
    }
//    ##################################################################


    public boolean saveIAdShownStatus(final boolean n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean("IAdShown", n);
        return editor.commit();
    }

    public boolean getIAdShownStatus() {
        return this.getSharedPreferences().getBoolean("IAdShown", true);
    }


    public boolean savePolicyStatus(final boolean n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean("IPolicy", n);
        return editor.commit();
    }

    public boolean getPolicyStatus() {
        return this.getSharedPreferences().getBoolean("IPolicy", false);
    }


}
