package com.lucky.customviewlearn;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by zfz on 2017/7/11.
 */

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String trackingId = getPackageName();
        //String trackingId = "com.netpulse.test";
        Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .appIdentifier(trackingId).build();
        Fabric.with(fabric);
    }


}
