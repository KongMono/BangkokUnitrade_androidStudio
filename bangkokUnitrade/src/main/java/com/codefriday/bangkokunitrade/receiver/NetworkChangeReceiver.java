package com.codefriday.bangkokunitrade.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.codefriday.bangkokunitrade.util.NetworkUtil;
 
public class NetworkChangeReceiver extends BroadcastReceiver {
 
    @Override
    public void onReceive(final Context context, final Intent intent) {
 
        String status = NetworkUtil.getConnectivityStatusString(context);
 
//        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}