package com.arshackerofficial.vipmodtool;

import android.content.Context;
import android.widget.Toast;

public class SketchwareUtil {
    public static void showMessage(Context applicationContext, String s) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show();
    }
}
