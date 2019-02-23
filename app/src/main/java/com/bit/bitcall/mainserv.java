package com.bit.bitcall;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;



public class mainserv extends Service implements TextToSpeech.OnInitListener {
    public mainserv() {
    }
    String TAG=":::";
    String data,conc;
    TextToSpeech tts;
    protected static SpeechRecognizer mSpeechRecognizer;
    protected Intent mSpeechRecognizerIntent;
    Context c=this;
    @Override
    public void onInit(int status) {
        Log.v(TAG, "oninit");
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.getDefault());
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.v(TAG, "Language is not available.");
            } else {
                for(int i=0;i<=5000;i++);
                AudioManager mobilemode = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
                mobilemode.setStreamVolume(AudioManager.STREAM_MUSIC,mobilemode.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                mobilemode.setStreamVolume(AudioManager.STREAM_RING,0,0);
                //mobilemode.setRingerVolume(AudioManager.STREAM_MUSIC,mobilemode.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                //mobilemode.setStreamVolume(AudioManager.STREAM_RING,mobilemode.getStreamMaxVolume(AudioManager.STREAM_RING),0);

                sayHello("Biswajit u got a call from "+conc+"     Would you like to accept or reject");
                 while(tts.isSpeaking());
                 for(int i=0;i<=20000;i++);
                  //mobilemode.setStreamVolume(AudioManager.STREAM_MUSIC,mobilemode.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                  //mobilemode.setStreamVolume(AudioManager.STREAM_RING,mobilemode.getStreamMaxVolume(AudioManager.STREAM_RING),0);

                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);




            }
        } else {
            Log.v(TAG, "Could not initialize TextToSpeech.");
        }
    }
    private void sayHello(String str) {
        tts.speak(str,
                TextToSpeech.QUEUE_FLUSH,
                null);
    }
/*
    @Override
    public void onDestroy(){
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }*/
    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        tts = new TextToSpeech(this,
                this  // OnInitListener
        );
        tts.setSpeechRate(0.5f);
        /*
        SpeechRecognitionListener h = new SpeechRecognitionListener();
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizer.setRecognitionListener(h);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        Log.d("avail", " " + mSpeechRecognizer.isRecognitionAvailable(this));
        if (mSpeechRecognizer.isRecognitionAvailable(this))
            Log.d("created", "onBeginingOfSpeech");
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        Log.v(TAG, "oncreate_service");
*/

        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        data=(String) intent.getExtras().get("NUM");
        conc=getContactName(data,this);
        Log.e(":::final",conc+data);
        /*
        tts = new TextToSpeech(this, this);
        tts.setLanguage(Locale.US);
        tts.speak("Text to say aloud", TextToSpeech.QUEUE_ADD, null);*/
        return START_STICKY;
    }


    public String getContactName(final String phoneNumber, Context context)
    {
        Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName="NONE";
        Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName=cursor.getString(0);
            }
            cursor.close();
        }

        return contactName;
    }

    class SpeechRecognitionListener implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle bundle) {

            Log.d("onReady", "service");
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int i) {
            Log.d("ERROR","ERROR");
        }

        @Override
        public void onResults(Bundle resultsBundle) {
            Log.d("Results", "onResults");
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    }
}
