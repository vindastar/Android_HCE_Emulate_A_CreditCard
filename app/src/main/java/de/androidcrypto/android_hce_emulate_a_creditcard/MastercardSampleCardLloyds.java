package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

public class MastercardSampleCardLloyds {

    public static final String CARD_NAME = "Mastercard Lloyds";
    public static byte[] SELECT_OK_SW = hexStringToByteArray("9000");

    // Note: the responses does not include the 0x9000h terminator
    public static byte[] SELECT_PPSE_COMMAND = hexStringToByteArray("00a404000e325041592e5359532e444446303100");
    public static byte[] SELECT_PPSE_RESPONSE = hexStringToByteArray("6f2f840e325041592e5359532e4444463031a51dbf0c1a61184f07a0000000041010500a4d617374657243617264870101");

    public static byte[] SELECT_AID_COMMAND = hexStringToByteArray("00a4040007a000000004101000");
    public static byte[] SELECT_AID_RESPONSE = hexStringToByteArray("6f388407a0000000041010a52d500a4d6173746572436172648701015f2d02656e9f1101019f120a4d617374657243617264bf0c059f4d020b0a");

    // as no PDOL was returned in SELECT_AID_RESPONSE this is an 'empty' GPO call
    public static byte[] GET_PROCESSING_OPTONS_COMMAND = hexStringToByteArray("80a8000002830000");
    public static byte[] GET_PROCESSING_OPTONS_RESPONSE_ORG = hexStringToByteArray("771682021980941008010100100102011801020020010200"); // 8 files
    public static byte[] GET_PROCESSING_OPTONS_RESPONSE = hexStringToByteArray(""); // x files

    /*
    05 get the processing options response length: 26 data: 7716820219809410080101001001020118010200200102009000
    ------------------------------------
    77 16 -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 10 -- Application File Locator (AFL)
            08 01 01 00 10 01 02 01 18 01 02 00 20 01 02 00 (BINARY)
    90 00 -- Command successfully executed (OK)
    ------------------------------------

    OLD DATA - NOT CHANGED

    changed to:
    05 get the processing options response length: 22 data: 770D82021980940808010100100101019000
    ------------------------------------
    77 0D -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 08 -- Application File Locator (AFL)
            08 01 01 00 10 01 01 01 (BINARY)
    90 00 -- Command successfully executed (OK)
    ------------------------------------
     */

    // 94 10 -- Application File Locator (AFL) 08 01 01 00 10 01 02 01 18 01 02 00 20 01 02 00 (BINARY)
    // The AFL contains 3 entries to read
    // for SFI 8 we read 1 record in file 01
    public static byte[] READ_FILE_08_01_COMMAND = hexStringToByteArray("00b2010c00");
    public static byte[] READ_FILE_08_01_RESPONSE = hexStringToByteArray("706a9f6c0200019f620600000000000e9f63060000000007f0562942353138373931303331313233363637375e202f5e31373131323031303430303030303030303030309f6401049f6502000e9f660207f09f6b135187910311236677d17112010400000000000f9f670104");

    // for SFI 10 read read 2 records file 01
    public static byte[] READ_FILE_10_01_COMMAND = hexStringToByteArray("00b2011400");
    public static byte[] READ_FILE_10_01_RESPONSE = hexStringToByteArray("7081815f25031409015f24031711309f07023d005a0851879103112366775f3401049f0d0500000400009f0e05b4708000009f0f0500000480005f280208268e0e000000000000000042031e031f039f4a01828c219f02069f03069f1a0295055f2a029a039c019f37049f35019f45029f4c089f34038d0c910a8a0295059f37049f4c08");

    // SFI 10 File 02
    public static byte[] READ_FILE_10_02_COMMAND = hexStringToByteArray("00b2021400");
    public static byte[] READ_FILE_10_02_RESPONSE = hexStringToByteArray("701a57135187910311236677d17112013369738100553f9f08020002");

    // for SFI 18 we read 2 records file 02
    public static byte[] READ_FILE_18_01_COMMAND = hexStringToByteArray("00b2011c00");
    public static byte[] READ_FILE_18_01_RESPONSE = hexStringToByteArray("7081e08f01059f3201039224f2235c27dea9e81221a1eaf9040628e27950708a387694eee0a5304202b52236a5e159a99081b07945cb9f6776b7fb188ba542ea469c53ba91eccbb0202902fb9c7011eb9e1f7bad06a329240ec8e69b90f61c752248d5e36e14075bef1df7fd518d8a7db3a9dfde119237a1e95a9fb1645bc39936393632725c1ab7a2bfd92c47746bba7b3f7f99acd1317a8f9af50397a7ed9ff3b52d4d8b1b05be5b140efab84f28db4ab560728144ee75e91c909ba12124b717d7283c6f007c5639a94519767114f28e56e2909c664f99dc01e3ec8190390ac3a385");

    // SFI 18 file 02
    public static byte[] READ_FILE_18_02_COMMAND = hexStringToByteArray("00b2021c00");
    public static byte[] READ_FILE_18_02_RESPONSE = hexStringToByteArray("70039301ff");

    // for SFI 20 we read 2 records file 01
    public static byte[] READ_FILE_20_01_COMMAND = hexStringToByteArray("00b2012400");
    public static byte[] READ_FILE_20_01_RESPONSE = hexStringToByteArray("70049f470103");

    public static byte[] READ_FILE_20_02_COMMAND = hexStringToByteArray("00b2022400");
    public static byte[] READ_FILE_20_02_RESPONSE = hexStringToByteArray("7081b49f4681b04622d517f70ac589ca80c872d683c40de548cc4937192a87662b2ddf3479c809e8f7a9aabc35cb9ca294d1415f495f41dd3109f05cff541f5f546a344debe5b85649b7e02717f166954be264586831c30e7d5982d3d5d0f3e0de67a369be8d86e63d85519ef07b1f6ddbbecc2bfffae696608e49d1a627a642200fc2a32063608b5901e687571337f675d86b8a2693623fbcb0216731cb860dd4605684d4f96e7b48e7ff41126ce673ba2f16fb68868a");

    public static byte[] SELECT_COMMAND3 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE3 = hexStringToByteArray("00");

    public static byte[] SELECT_COMMAND4 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE4 = hexStringToByteArray("00");

}

