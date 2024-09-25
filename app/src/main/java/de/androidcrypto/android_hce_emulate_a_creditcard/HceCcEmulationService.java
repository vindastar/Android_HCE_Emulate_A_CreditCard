package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.bytesToHexNpe;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

public class HceCcEmulationService extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] bytes, Bundle bundle) {
        System.out.println("processCommandApdu: " + bytesToHexNpe(bytes));
        byte[] SELECT_OK_SW = hexStringToByteArray("9000");
        return SELECT_OK_SW;
    }

    @Override
    public void onDeactivated(int reason) {

    }
}
