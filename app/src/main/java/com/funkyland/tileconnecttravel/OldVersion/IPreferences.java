/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funkyland.tileconnecttravel.OldVersion;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    public boolean saveRateStatus(final boolean n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean("IRate", n);
        return editor.commit();
    }

    public boolean getRateStatus() {
        return this.getSharedPreferences().getBoolean("IRate", false);
    }


    public boolean savePolicyStatus(final boolean n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean("IPolicy", n);
        return editor.commit();
    }

    public boolean getPolicyStatus() {
        return this.getSharedPreferences().getBoolean("IPolicy", false);
    }

    public boolean saveCurLevel(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("CurLevel", n);
        return editor.commit();
    }

    public int getCurLevel() {
        return this.getSharedPreferences().getInt("CurLevel", 1);
    }

    public boolean saveTotal_dollar(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("total_dollar", n);
        return editor.commit();
    }

    public int getTotal_dollar() {
        return this.getSharedPreferences().getInt("total_dollar", 0);
    }

    public boolean saveCurrent_dollar(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("dollar_current", n);
        return editor.commit();
    }

    public int getCurrent_dollar() {
        return this.getSharedPreferences().getInt("dollar_current", 0);
    }

    public boolean saveStar(final int n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putInt("Star", n);
        return editor.commit();
    }

    public int getStar() {
        return this.getSharedPreferences().getInt("Star", 0);
    }

    public boolean setBgSound(final boolean n) {
        final SharedPreferences.Editor editor = this.getEditor();
        editor.putBoolean("Sound", n);
        return editor.commit();
    }

    public boolean getBgSound() {
        return this.getSharedPreferences().getBoolean("Sound", true);
    }

}
