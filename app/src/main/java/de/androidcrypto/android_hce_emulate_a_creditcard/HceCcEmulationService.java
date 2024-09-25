package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.bytesToHexNpe;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.concatenateByteArrays;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.PPSE_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.PPSE_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.SELECT_OK_SW;

import android.content.Context;
import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Arrays;

public class HceCcEmulationService extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] bytes, Bundle bundle) {
        sendMessageToActivity("# processCommandApdu received", bytesToHexNpe(bytes));
        System.out.println("processCommandApdu: " + bytesToHexNpe(bytes));

        if (Arrays.equals(bytes, PPSE_COMMAND)) {
            // step 01 selecting the PPSE
            sendMessageToActivity("step 01 Select PPSE Command ", bytesToHexNpe(bytes));
            sendMessageToActivity("step 01 Select PPSE Response", bytesToHexNpe(PPSE_RESPONSE));
            return concatenateByteArrays(PPSE_RESPONSE, SELECT_OK_SW);
        }



        return SELECT_OK_SW;
    }

    @Override
    public void onDeactivated(int reason) {

    }


    /**
     * A utility method to send data with an Broadcast Intent from this service to the MainActivity class
     * @param msg
     * @param data
     */
    private void sendMessageToActivity(String msg, String data) {
        Intent intent = new Intent("HceCcEmulatorService");
        // You can also include some extra data.
        intent.putExtra("Message", msg);
        intent.putExtra("Data", data);
        /*
        Bundle b = new Bundle();
        b.putParcelable("Location", l);
        intent.putExtra("Location", b);

         */
        Context context = this;
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}
