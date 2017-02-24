package com.hudawei.client_aidl_sample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hudawei.server_aidl_sample.ISimpleService;


public class MainActivity extends AppCompatActivity {
    ISimpleService simpleService;
    ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this,"onServiceConnected Success",Toast.LENGTH_SHORT).show();
            simpleService=ISimpleService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            simpleService=null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=new Intent("com.hudawei.server_aidl_sample.ISimpleService");
        intent.setPackage("com.hudawei.server_aidl_sample");
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    public void processClick(View view){
        try {
            Toast.makeText(MainActivity.this,"服务："+simpleService.get(),Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
