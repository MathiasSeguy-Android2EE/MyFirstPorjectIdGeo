package com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.MySmsService;

public class MySmsReceiver extends BroadcastReceiver {
    private static final String TAG = "MySmsReceiver";
    public MySmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"New Sms received ("+TAG+")");
        Intent startSmsServiceIntent=new Intent(context, MySmsService.class);
        startSmsServiceIntent.putExtras(intent.getExtras());
        context.startService(startSmsServiceIntent);
    }
}
