package com.android2ee.formation.android.idgeo.juilmmxvi.firstporject;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.model.Human;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.view.main.MainActivity;

public class MySmsService extends Service {
    private static final String TAG = "MySmsService";
    public MySmsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"New Sms received ("+TAG+")");
        Bundle bundle= intent.getExtras() ;
        if(bundle!=null){
            //Retrieve the data store in the SMS
            Object[] pdus=(Object[])bundle.get("pdus");
            //Declare the associated SMS Messages
            SmsMessage[] smsMessages=new SmsMessage[pdus.length];
            //Rebuild your SMS Messages
            for(int i=0;i<pdus.length;i++){
                smsMessages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
            }
            //Parse your SMS Message
            SmsMessage currentMessage;
            String body,from;
            long when;
            Human human;
            for(int i=0;i<smsMessages.length;i++){
                currentMessage=smsMessages[i];
                body=currentMessage.getDisplayMessageBody();
                from=currentMessage.getDisplayOriginatingAddress();
                when=currentMessage.getTimestampMillis();
                showNotification(from,body,when);
                human=new Human(from,body);
                human.save();
                Log.e(TAG,"New Sms received de("+from+"):"+body);
            }
        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification(String from,String body,long when){
        Intent startAct=new Intent(this, MainActivity.class);
        PendingIntent pdIntent=PendingIntent.getActivity(this,0,startAct,PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setContentTitle("A new Sms has come from "+from)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setOngoing(false)
                .setTicker("A new Sms just arrived")
                .setWhen(when)
                .setContentIntent(pdIntent);
        NotificationManagerCompat.from(this).notify(131274,builder.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy in SmsService");
        super.onDestroy();
    }
}
