package com.lucky.customviewlearn;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import timber.log.Timber;

/**
 * Created by zfz on 2017/7/11.
 */

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();

    }

    private void initApp() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
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


}
