package com.nexp.pavel.sber_hm_aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentWrite.WriteCallback, FragmentRead.ReadCallback {

    private MyAidlInterface myAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, ServiceSP.class);
        intent.setAction(MyAidlInterface.class.getName());
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = MyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface = null;
        }
    };



    @Override
    public void write(String text) {
        try {
            myAidlInterface.writeSP(text);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() {
        try {
            return myAidlInterface.readSP();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return "Something Wrong";
    }
}
