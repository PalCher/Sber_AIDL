package com.nexp.pavel.sber_hm_aidl;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;

public class ServiceSP extends Service {
    private SharedPreferences preferences;
    private static String sPrefName = "Name";

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences(sPrefName, MODE_PRIVATE);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyAidlInterface.Stub() {
            @Override
            public String readSP() throws RemoteException {
                String text = preferences.getString("data", "DEFAULT");
                return text;
            }

            @Override
            public void writeSP( String text) throws RemoteException {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("data", text);
                editor.apply();
            }
        };
    }
}
