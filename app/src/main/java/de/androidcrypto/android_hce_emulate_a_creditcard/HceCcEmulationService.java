package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.bytesToHexNpe;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.concatenateByteArrays;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.GET_PROCESSING_OPTONS_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.GET_PROCESSING_OPTONS_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_03_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_03_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_04_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_04_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_05_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_05_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_06_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.READ_FILE_06_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.SELECT_AID_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.SELECT_AID_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.SELECT_PPSE_COMMAND;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.SELECT_PPSE_RESPONSE;
import static de.androidcrypto.android_hce_emulate_a_creditcard.VisaSampleCard.SELECT_OK_SW;

import android.content.Context;
import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.view.View;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Arrays;

public class HceCcEmulationService extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] bytes, Bundle bundle) {
        sendMessageToActivity("# processCommandApdu received", bytesToHexNpe(bytes));
        //sendUsingStaticallyRegisteredBroadcastReceiver("# processCommandApdu received " + bytesToHexNpe(bytes));
        System.out.println("processCommandApdu: " + bytesToHexNpe(bytes));

        // step 01
        if (Arrays.equals(bytes, SELECT_PPSE_COMMAND)) {
            // step 01 selecting the PPSE
            sendMessageToActivity("step 01 Select PPSE Command ", bytesToHexNpe(bytes));
            sendMessageToActivity("step 01 Select PPSE Response", bytesToHexNpe(SELECT_PPSE_RESPONSE));
            return concatenateByteArrays(SELECT_PPSE_RESPONSE, SELECT_OK_SW);
        }

        // step 02
        if (Arrays.equals(bytes, SELECT_AID_COMMAND)) {
            // step 02 selecting the AID
            sendMessageToActivity("step 02 Select AID Command ", bytesToHexNpe(bytes));
            sendMessageToActivity("step 02 Select AID Response", bytesToHexNpe(SELECT_AID_RESPONSE));
            return concatenateByteArrays(SELECT_AID_RESPONSE, SELECT_OK_SW);
        }

        // step 03
        if (Arrays.equals(bytes, GET_PROCESSING_OPTONS_COMMAND)) {
            // step 03 Get Processing Options
            sendMessageToActivity("step 03 Get Processing Options (GPO) Command", bytesToHexNpe(bytes));
            sendMessageToActivity("step 03 Get Processing Options (GPO) Response", bytesToHexNpe(GET_PROCESSING_OPTONS_RESPONSE));
            return concatenateByteArrays(GET_PROCESSING_OPTONS_RESPONSE, SELECT_OK_SW);
        }
/*
        // step 04
        if (Arrays.equals(bytes, GET_PROCESSING_OPTONS_COMMAND)) {
            // step 03 Get Processing Options
            sendMessageToActivity("step 04 Get Processing Options (GPO) Command", bytesToHexNpe(bytes));
            sendMessageToActivity("step 04 Get Processing Options (GPO) Response", bytesToHexNpe(GET_PROCESSING_OPTONS_RESPONSE));
            return concatenateByteArrays(GET_PROCESSING_OPTONS_RESPONSE, SELECT_OK_SW);
        }
*/
        // step 05
        if (Arrays.equals(bytes, READ_FILE_03_COMMAND)) {
            // step 05 Read File 03 Command
            sendMessageToActivity("step 05 Read File 03 Command", bytesToHexNpe(bytes));
            sendMessageToActivity("step 05 Read File 03 Response", bytesToHexNpe(READ_FILE_03_RESPONSE));
            return concatenateByteArrays(READ_FILE_03_RESPONSE, SELECT_OK_SW);
        }

        // step 06
        if (Arrays.equals(bytes, READ_FILE_04_COMMAND)) {
            // step 06 Read File 04 Command
            sendMessageToActivity("step 06 Read File 04 Command", bytesToHexNpe(bytes));
            sendMessageToActivity("step 06 Read File 05 Response", bytesToHexNpe(READ_FILE_04_RESPONSE));
            return concatenateByteArrays(READ_FILE_04_RESPONSE, SELECT_OK_SW);
        }

        // step 07
        if (Arrays.equals(bytes, READ_FILE_05_COMMAND)) {
            // step 05 Read File 03 Command
            sendMessageToActivity("step 07 Read File 05 Command", bytesToHexNpe(bytes));
            sendMessageToActivity("step 07 Read File 05 Response", bytesToHexNpe(READ_FILE_05_RESPONSE));
            return concatenateByteArrays(READ_FILE_05_RESPONSE, SELECT_OK_SW);
        }

        // step 08
        if (Arrays.equals(bytes, READ_FILE_06_COMMAND)) {
            // step 05 Read File 03 Command
            sendMessageToActivity("step 08 Read File 06 Command", bytesToHexNpe(bytes));
            sendMessageToActivity("step 08 Read File 06 Response", bytesToHexNpe(READ_FILE_06_RESPONSE));
            return concatenateByteArrays(READ_FILE_06_RESPONSE, SELECT_OK_SW);
        }

        return SELECT_OK_SW;
    }

    @Override
    public void onDeactivated(int reason) {

    }

/*
    public void sendUsingStaticallyRegisteredBroadcastReceiver(String message) {
        Intent intent = new Intent();
        intent.setAction("HceCcEmulatorService");
        //intent.putExtra("Message", message);
        Context context = this;
        context.sendBroadcast(intent);
        System.out.println("HCE broadcast sent");
    }
*/
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
