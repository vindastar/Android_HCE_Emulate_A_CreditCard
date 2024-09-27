package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

public class VisaSampleCard {

    public static final String CARD_NAME = "Visa Card";
    public static byte[] SELECT_OK_SW = hexStringToByteArray("9000");

    // Note: the responses does not include the 0x9000h terminator
    public static byte[] SELECT_PPSE_COMMAND = hexStringToByteArray("00a404000e325041592e5359532e444446303100");
    public static byte[] SELECT_PPSE_RESPONSE = hexStringToByteArray("6f2b840e325041592e5359532e4444463031a519bf0c1661144f07a00000000310109f0a080001050100000000");

    public static byte[] SELECT_AID_COMMAND = hexStringToByteArray("00a4040007a000000003101000");
    public static byte[] SELECT_AID_RESPONSE = hexStringToByteArray("6f5d8407a0000000031010a5525010564953412044454249542020202020208701029f38189f66049f02069f03069f1a0295055f2a029a039c019f37045f2d02656ebf0c1a9f5a0531082608269f0a080001050100000000bf6304df200180");
    // selectAidResponseOk: 00a4040007a000000003101000
    //80a8000023832127000000000000001000000000000000097800000000000978230301003839303100
    // this needs to be dynamic as the response from the real NFC reader is not predictable (e.g. different amounts)
    // This is the most common beginning to comapre with: 80a800002383
    // todo this needs to be dynamic as the response from the real NFC reader is not predictable (e.g. different amounts)
    // 80a800002383
    public static byte[] GET_PROCESSING_OPTONS_COMMAND_START = hexStringToByteArray("80a8000023");
    public static byte[] GET_PROCESSING_OPTONS_COMMAND = hexStringToByteArray("80a8000023832127000000000000001000000000000000097800000000000978230301003839303100");
    public static byte[] GET_PROCESSING_OPTONS_RESPONSE = hexStringToByteArray("7781c68202200094041003060057134921828094896752d25022013650000000000f9f100706040a03a020009f2608569f946d585cb0139f2701809f360203a49f4b818076f770b228b28c0b5a3f2b08c3f2ec3be6de27a2708de06180fdaaec5482e38cd62d5fe43cfcc8c3ff7462d7be99deb035a0abe5d0f82bdadbfb5d81377c42ae64f3339893df51e55f635251d1e1b0f4db959e60407684e03c92b5b0c06fcf5fa5bb366ec660f9d0b0dc3795eae505b5d2c982352c762f06c32647e0068f28469f6c021600");

    // 94 04 -- Application File Locator (AFL) 10 03 06 00 (BINARY)
    // in total 4 files in SFI 10 (03, 04, 05, 06)
    public static byte[] READ_FILE_10_03_COMMAND = hexStringToByteArray("00b2031400");
    public static byte[] READ_FILE_10_03_RESPONSE = hexStringToByteArray("7081fb9081f830f056de40a950bec2a870c59d5462222605a8f31cdef39a0537c7c175115e352ad0c55470fce5737c4e769897623e01401da73e01644bb0b491aa1aadb27fc360c0089f7c2e52a64e96a3f8a59f76e49aa6dd9a6792644f2b0b513b1a1a93b98a3cc19f0bec45e9f8edd70f893a8cafb21b62f3b8f15983775f14fd16cb36a19120e5a5068ef9f05ffaea4e714d80f134a298d167a65a92f6f57963db94ab5d3967f6675b3609a0fceb5fbb70f07cfdeab1352c6a34d6be737aa74848f3f56932f08b51f54aa3040f1ace4a0ced38684df900a395c5cd88562eb2af8d35601210c20d6c3425dcd813b9b358d1356d52a8ebd8fb5a19915d");

    public static byte[] READ_FILE_10_04_COMMAND = hexStringToByteArray("00b2041400");
    public static byte[] READ_FILE_10_04_RESPONSE = hexStringToByteArray("70078f01099f320103");

    public static byte[] READ_FILE_10_05_COMMAND = hexStringToByteArray("00b2051400");
    public static byte[] READ_FILE_10_05_RESPONSE = hexStringToByteArray("7081b49f4681b047461ffca14b5dfdc209569c8a14f17644251aa3f4abea251262134b920982f0250741f96fccb40800293054c0d89824ba7ac44ee7bab06fa157fccf7e52d3c64b4d8acd41b9774b801519ed6fec827ec2ec29f8991167c453776559a4a06fd98c4b9bd1548a65af2f56002a836bdf9a040a9253e653584c92833c3d1aa8e08c4de9cda1026044f80f39a9326a57496598987a6b3e18a5f56a8bdede752870e8793776db9d325ccd9c7ca5db33c28f04");

    public static byte[] READ_FILE_10_06_COMMAND = hexStringToByteArray("00b2061400");
    public static byte[] READ_FILE_10_06_RESPONSE = hexStringToByteArray("702e9f4701035a0849218280948967525f3401005f24032502285f280208269f6e04207000009f690701b4cba8d61600");

    public static byte[] SELECT_COMMAND3 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE3 = hexStringToByteArray("00");

    public static byte[] SELECT_COMMAND4 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE4 = hexStringToByteArray("00");

}

