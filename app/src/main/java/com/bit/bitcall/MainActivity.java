package com.bit.bitcall;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v){
        PackageManager pm  = MainActivity.this.getPackageManager();
        ComponentName componentName = new ComponentName(MainActivity.this, callrecei.class);
        pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Toast.makeText(getApplicationContext(), "activated", Toast.LENGTH_LONG).show();

    }

    public void stop(View v){
        PackageManager pm  = MainActivity.this.getPackageManager();
        ComponentName componentName = new ComponentName(MainActivity.this, callrecei.class);
        pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        Toast.makeText(getApplicationContext(), "cancelled", Toast.LENGTH_LONG).show();
    }
}
