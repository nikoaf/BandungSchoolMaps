package com.bandungschoolmaps.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class ToastHelper {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public ToastHelper(Context context) {
        ToastHelper.context = context;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    void showToastWhitDuration(int duration, String message) {
        final Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
        new CountDownTimer(duration, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                toast.show();
            }

            @Override
            public void onFinish() {
                toast.cancel();
            }
        }.start();
    }
}
