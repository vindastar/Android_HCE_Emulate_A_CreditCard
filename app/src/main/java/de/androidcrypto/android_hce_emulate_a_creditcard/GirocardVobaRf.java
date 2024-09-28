package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

public class GirocardVobaRf {

    public static final String CARD_NAME = "Visa Card";
    public static byte[] SELECT_OK_SW = hexStringToByteArray("9000");

    // Note: the responses does not include the 0x9000h terminator
    public static byte[] SELECT_PPSE_COMMAND = hexStringToByteArray("00a404000e325041592e5359532e444446303100");
    public static byte[] SELECT_PPSE_RESPONSE_MODIFIED = hexStringToByteArray("6f2d840e325041592e5359532e4444463031a51bbf0c1861164f09A000000059454301009f0a080001050100000000");
    public static byte[] SELECT_PPSE_RESPONSE = hexStringToByteArray("6f2b840e325041592e5359532e4444463031a519bf0c1661144f07a00000000310109f0a080001050100000000");
    // 6f8180840e325041592e5359532e4444463031a56ebf0c6b61194f09a000000059454301008701019f0a080001050100000000611a4f0aa00000035910100280018701019f0a08000105010000000061194f09d276000025474101008701019f0a08000105010000000061174f07a00000000430608701019f0a080001050100000000

    // this is for AID A00000005945430100 ZKA Girocard
    public static byte[] SELECT_AID_COMMAND = hexStringToByteArray("00a4040007a000000003101000");
    public static byte[] SELECT_AID_RESPONSE = hexStringToByteArray("6f478409a00000005945430100a53a50086769726f636172648701019f38069f02069f1d025f2d046465656ebf0c1a9f4d02190a9f6e07028000003030009f0a080001050100000000");
    // selectAidResponseOk: 00a4040007a000000003101000
    //80a8000023832127000000000000001000000000000000097800000000000978230301003839303100
    // this needs to be dynamic as the response from the real NFC reader is not predictable (e.g. different amounts)
    // This is the most common beginning to comapre with: 80a800002383
    // todo this needs to be dynamic as the response from the real NFC reader is not predictable (e.g. different amounts)
    // 80a800002383
    public static byte[] GET_PROCESSING_OPTONS_COMMAND_START = hexStringToByteArray("80a80000");
    public static byte[] GET_PROCESSING_OPTONS_COMMAND = hexStringToByteArray("80a800000a8308000000001000000000");
    public static byte[] GET_PROCESSING_OPTONS_RESPONSE = hexStringToByteArray("771e820219809418180102002001010040040400080505010807070108030301");

    // 94 18 -- Application File Locator (AFL)
    //            18 01 02 00 20 01 01 00 40 04 04 00 08 05 05 01
    //            08 07 07 01 08 03 03 01 (BINARY)
    // in total 2 files in SFI 18 (01, 02), 1 file in SFI 20 (01), 1 file in SFI 40 (04), 3 files in SFI 08 (05, 07, 03) (7 files total)

    public static byte[] READ_FILE_18_01_COMMAND = hexStringToByteArray("00b2011c00");
    public static byte[] READ_FILE_18_01_RESPONSE = hexStringToByteArray("702f8f01059f3201039224b0568adf146b092492be46e5d57d920b026be8e734264cf34710483a0af52d46790f01ab0000");

    public static byte[] READ_FILE_18_02_COMMAND = hexStringToByteArray("00b2021c00");
    public static byte[] READ_FILE_18_02_RESPONSE = hexStringToByteArray("7081b39081b068361092b2690a2b391a6afb325df0abe55b989238b81c5b78058a093c790bab9768c6b1dca6fc0258fa2cb9af1f78ed4d8dfd95ca837fbc2a2b6d56202e6432f7e4fc7669b2a7f637866921dc642de9bfec27747b4a6a70288ab6395fbf2c63b0c1feaede3051df9b37ff0e067c23d9ab477ad5985c86e80348302e7a5c73f3d2cba543c72a8095a99305be9a88178d54954c1c5dc7957668b89f4ac98f8f40062bc60fcd68dee5b600378585b5d8ea");

    public static byte[] READ_FILE_20_01_COMMAND = hexStringToByteArray("00b2012400");
    public static byte[] READ_FILE_20_01_RESPONSE = hexStringToByteArray("70339f47030100019f480a4947098e18cbf3fba1fb0000000000000000000000000000000000000000000000000000000000000000");

    public static byte[] READ_FILE_40_04_COMMAND = hexStringToByteArray("00b2044400");
    public static byte[] READ_FILE_40_04_RESPONSE = hexStringToByteArray("7081b49f4681b04bc08f3692571942b499ceff18236a9281ac29a85956aebf05f1fec66364229be5366e4a81efcfdaf3b4d9ec7295214893cf9a56c81989f4617b3fc1f86825a2caf028ca74fdd15ad58ba528ea83dee573ee1007c30215a0e3db978a623cbb2cb385ebeaa991c52ab8cabd83e14753b2e1d10abf42683acb3dff67922af5800e667617e63aca34fde3fc8bbe5392709408f0fc3853c10f2f516b1c288bf8f83ca091a01a290c513e321c7f1790125902");

    public static byte[] READ_FILE_08_03_COMMAND = hexStringToByteArray("00b2030c00");
    public static byte[] READ_FILE_08_03_RESPONSE = hexStringToByteArray("703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136726428902046846007d24122010210104669f");

    public static byte[] READ_FILE_08_05_COMMAND = hexStringToByteArray("00b2050c00");
    public static byte[] READ_FILE_08_05_RESPONSE = hexStringToByteArray("70385f24032412315a0a6726428902046846007f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c");

    public static byte[] READ_FILE_08_07_COMMAND = hexStringToByteArray("00b2070c00");
    public static byte[] READ_FILE_08_07_RESPONSE = hexStringToByteArray("70178e0c00000000000000001f0302039f080200029f4a0182");


    /*
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
*/
}

