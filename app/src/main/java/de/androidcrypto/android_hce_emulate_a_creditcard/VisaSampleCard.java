package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

public class VisaSampleCard {

    public static byte[] SELECT_OK_SW = hexStringToByteArray("9000");
    public static byte[] PPSE_COMMAND = hexStringToByteArray("00a404000e325041592e5359532e444446303100");
    public static byte[] PPSE_RESPONSE = hexStringToByteArray("6f2b840e325041592e5359532e4444463031a519bf0c1661144f07a00000000310109f0a080001050100000000");

}

