package com.bit.bitcall;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class callrecei extends BroadcastReceiver {
    private static final String TAG = "PhoneStatReceiver::::";

    private static boolean incomingFlag = false;

    private static String incoming_number = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(":::","STARTED");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.e("MY_DEBUG_TAG:::", state);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String phoneNumber = extras
                        .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.e("MY_DEBUG_TAG:::", phoneNumber);
                Intent i = new Intent(context, mainserv.class);
                i.putExtra("NUM",phoneNumber);
                context.startService(i);
            }
        }



        /*
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){

            incomingFlag = false;

            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Log.e(TAG, "call OUT:"+phoneNumber);

        }else{

            TelephonyManager tm =

                    (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);



            switch (tm.getCallState()) {

                case TelephonyManager.CALL_STATE_RINGING:

                    incomingFlag = true;

                    incoming_number = intent.getStringExtra("incoming_number");

                    Log.e(TAG, "RINGING :::"+ incoming_number);

                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:

                    if(incomingFlag){

                        Log.e(TAG, "incoming ACCEPT :::"+ incoming_number);

                    }

                    break;



                case TelephonyManager.CALL_STATE_IDLE:

                    if(incomingFlag){

                        Log.e(TAG, ":::incoming IDLE");

                    }

                    break;

            }

        }
            */
    }

}
