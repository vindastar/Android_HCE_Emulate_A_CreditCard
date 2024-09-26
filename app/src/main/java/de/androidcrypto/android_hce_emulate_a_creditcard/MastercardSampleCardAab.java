package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

public class MastercardSampleCardAab {

    public static final String CARD_NAME = "Mastercard AAB";
    public static byte[] SELECT_OK_SW = hexStringToByteArray("9000");

    // Note: the responses does not include the 0x9000h terminator
    public static byte[] SELECT_PPSE_COMMAND = hexStringToByteArray("00a404000e325041592e5359532e444446303100");
    public static byte[] SELECT_PPSE_RESPONSE = hexStringToByteArray("6f3c840e325041592e5359532e4444463031a52abf0c2761254f07a000000004101050104465626974204d6173746572436172648701019f0a0400010101");

    public static byte[] SELECT_AID_COMMAND = hexStringToByteArray("00a4040007a000000004101000");
    public static byte[] SELECT_AID_RESPONSE = hexStringToByteArray("6f528407a0000000041010a54750104465626974204d6173746572436172649f12104465626974204d6173746572436172648701019f1101015f2d046465656ebf0c119f0a04000101019f6e0702800000303000");
// selectAidResponseOk: 00a4040007a000000003101000
    //80a8000023832127000000000000001000000000000000097800000000000978230301003839303100
    // this needs to be dynamic as the response from the real NFC reader is not predictable (e.g. different amounts)
    // as no PDOL was returned in SELECT_AID_RESPONSE this is an 'empty' GPO call
    public static byte[] GET_PROCESSING_OPTONS_COMMAND = hexStringToByteArray("80a8000002830000");
    public static byte[] GET_PROCESSING_OPTONS_RESPONSE_ORG = hexStringToByteArray("771282021980940c080101001001010120010200"); // 4 files
    public static byte[] GET_PROCESSING_OPTONS_RESPONSE = hexStringToByteArray("770D8202198094080801010010010101"); // 2 files

    /*
    05 get the processing options response length: 22 data: 771282021980940c0801010010010101200102009000
    ------------------------------------
    77 12 -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 0C -- Application File Locator (AFL)
            08 01 01 00 10 01 01 01 20 01 02 00 (BINARY)
    90 00 -- Command successfully executed (OK)
    ------------------------------------

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

    // 94 0C -- Application File Locator (AFL) 08 01 01 00 10 01 01 01 20 01 02 00 (BINARY)
    // The AFL contains 3 entries to read
    //for SFI 8 we read 1 record in file 01
    public static byte[] READ_FILE_08_01_COMMAND = hexStringToByteArray("00b2010c00");
    public static byte[] READ_FILE_08_01_RESPONSE = hexStringToByteArray("70759f6c0200019f6206000000000f009f63060000000000fe563442353337353035303030303136303131305e202f5e323430333232313237393433323930303030303030303030303030303030309f6401029f65020f009f660200fe9f6b135375050000160110d24032210000000000000f9f670102");

    // for SFI 10 we read 1 record in file 01
    public static byte[] READ_FILE_10_01_COMMAND = hexStringToByteArray("00b2011400");
    public static byte[] READ_FILE_10_01_RESPONSE = hexStringToByteArray("7081a69f420209785f25032203015f24032403315a0853750500001601105f3401009f0702ffc09f080200028c279f02069f03069f1a0295055f2a029a039c019f37049f35019f45029f4c089f34039f21039f7c148d0c910a8a0295059f37049f4c088e0e000000000000000042031e031f039f0d05b4508400009f0e0500000000009f0f05b4708480005f280202809f4a018257135375050000160110d24032212794329000000f");

    // in the original card there are two additional files in SFI 20 (01 + 02), they were skipped out
    public static byte[] READ_FILE_20_01_COMMAND = hexStringToByteArray("00b2012400");
    public static byte[] READ_FILE_20_01_RESPONSE = hexStringToByteArray("7081b89f4701039f4681b03cada902afb40289fbdfea01950c498191442c1b48234dcaff66bca63cbf821a3121fa808e4275a4e894b154c1874bddb00f16276e92c73c04468253b373f1e6a9a89e2705b4670682d0adff05617a21d7684031a1cdb438e66cd98d591dc376398c8aab4f137a2226122990d9b2b4c72ded6495d637338fefa893ae7fb4eb845f8ec2e260d2385a780f9fda64b3639a9547adad806f78c9bc9f17f9d4c5b26474b9ba03892a754ffdf24df04c702f86");

    public static byte[] READ_FILE_20_02_COMMAND = hexStringToByteArray("00b2022400");
    public static byte[] READ_FILE_20_02_RESPONSE = hexStringToByteArray("7081e08f01059f3201039224abfd2ebc115c3796e382be7e9863b92c266ccabc8bd014923024c80563234e8a11710a019081b004cc60769cabe557a9f2d83c7c73f8b177dbf69288e332f151fba10027301bb9a18203ba421bda9c2cc8186b975885523bf6707f287a5e88f0f6cd79a076319c1404fcdd1f4fa011f7219e1bf74e07b25e781d6af017a9404df9fd805b05b76874663ea88515018b2cb6140dc001a998016d28c4af8e49dfcc7d9cee314e72ae0d993b52cae91a5b5c76b0b33e7ac14a7294b59213ca0c50463cfb8b040bb8ac953631b80fa85a698b00228b5ff44223");

    public static byte[] SELECT_COMMAND3 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE3 = hexStringToByteArray("00");

    public static byte[] SELECT_COMMAND4 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE4 = hexStringToByteArray("00");

}

