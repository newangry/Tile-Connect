package com.funkyland.tileconnecttravel.OldVersion.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.funkyland.tileconnecttravel.OldVersion.GameConfiguration;

public class IUtil
{
    public static int getRandomIndex(final int n, final int n2) {
        return n + (int)(Math.random() * (1 + (n2 - n)));
    }
    
    public static void resizeDialog(final View view) {
        if (GameConfiguration.SCREENWIDTH < 800) {
            view.getLayoutParams().height = view.getLayoutParams().height * (-50 + GameConfiguration.SCREENWIDTH) / view.getLayoutParams().width;
            view.getLayoutParams().width = -50 + GameConfiguration.SCREENWIDTH;
        }
    }
    
    public static void showToast(final Context context, final String s) {
        Toast.makeText(context, (CharSequence)s, 0).show();
    }
}