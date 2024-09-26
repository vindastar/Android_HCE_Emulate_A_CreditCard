package de.androidcrypto.android_hce_emulate_a_creditcard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StaticBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("** Broadcast On Receive **");
        String message = intent.getStringExtra("Message");
        Toast.makeText(context, "Static Broadcast: " + message, Toast.LENGTH_SHORT).show();
    }
}
