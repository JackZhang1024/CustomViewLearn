package com.lucky.customviewlearn;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

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
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(
                        getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                        getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)
                ))
                .debug(true)
                .build();
        Twitter.initialize(config);

    }


}
