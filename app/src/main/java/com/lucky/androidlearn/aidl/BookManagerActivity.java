package com.lucky.androidlearn.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.List;

/**
 * 调用AndroidLearn应用BookManagerService的方法
 *
 * @author zfz
 */
public class BookManagerActivity extends AppCompatActivity {

    private static final String TAG = "BookManagerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindBookManagerService();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: ");
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                // 添加书本
                bookManager.addBook(new Book(3, "JavaScript学习"));
                bookManager.addBook(new Book(4, "Linux学习"));
                List<Book> list = bookManager.getBookList();
                Log.e(TAG, "图书列表类型 : " + list.getClass().getCanonicalName());
                Log.e(TAG, "服务端返回的图书列表 : " + list.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: ");
        }
    };

    private void bindBookManagerService() {
        Intent intent = new Intent();
        intent.setPackage("com.lucky.androidlearn");
        intent.setAction("com.lucky.aidl.bms");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
        }
        Intent intent = new Intent();
        intent.setPackage("com.lucky.androidlearn");
        intent.setAction("com.lucky.aidl.bms");
        stopService(intent);
    }
}
