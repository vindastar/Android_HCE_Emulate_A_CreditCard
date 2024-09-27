package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

public class VisaAnonymizedSampleCard {

    /**
     * This class is using a generated Credit Card number from
     * https://www.bincodes.com/bank-creditcard-generator/
     * The Expire Date is fixed to 10 2029
     */


/*

Details for 4779447134861024
BIN	477944
Account Number	713486102
Checksum	4

BIN Details

Details for BIN 477944
BIN	477944
Issuing Bank	CITIBANK
Card Brand	VISA
Card Type	CREDIT
Card Level
ISO Country Name
GERMANY
ISO Country A2	DE
ISO Country A3	DEU
ISO Country Number	276
Issuers Website	http://www.citibank.de/
Issuers Contact	49 69 1366 0

*/

    // The data is based on the old Visa Card from DKB
    // old PAN: 4930005025003985
    // new PAN: 4779447134861024
    // old Expire Date: 260930
    // new Expire Date: 291030
    public static final String CARD_NAME = "Visa Card Anonymized";
    public static byte[] SELECT_OK_SW = hexStringToByteArray("9000");

    // Note: the responses does not include the 0x9000h terminator
    public static byte[] SELECT_PPSE_COMMAND = hexStringToByteArray("00a404000e325041592e5359532e444446303100");
    public static byte[] SELECT_PPSE_RESPONSE = hexStringToByteArray("6f38840e325041592e5359532e4444463031a526bf0c2361214f07a000000003101050085649534120444b428701019f0a080001050200000000");

    public static byte[] SELECT_AID_COMMAND = hexStringToByteArray("00a4040007a000000003101000");
    public static byte[] SELECT_AID_RESPONSE = hexStringToByteArray("6f578407a0000000031010a54c50085649534120444b428701015f2d046465656e9f38189f66049f02069f03069f1a0295055f2a029a039c019f3704bf0c1a9f0a0800010502000000009f5a053109780276bf6304df200180");

    /*
    The card is requesting this data:
    9F 38 18 -- Processing Options Data Object List (PDOL)
                     9F 66 04 -- Terminal Transaction Qualifiers
                     9F 02 06 -- Amount, Authorised (Numeric)
                     9F 03 06 -- Amount, Other (Numeric)
                     9F 1A 02 -- Terminal Country Code
                     95 05 -- Terminal Verification Results (TVR)
                     5F 2A 02 -- Transaction Currency Code
                     9A 03 -- Transaction Date
                     9C 01 -- Transaction Type
                     9F 37 04 -- Unpredictable Number
     */

    // selectAidResponseOk: 00a4040007a000000003101000
    //80a8000023832127000000000000001000000000000000097800000000000978230301003839303100
    // todo this needs to be dynamic as the response from the real NFC reader is not predictable (e.g. different amounts)
    // 80a800002383
    public static byte[] GET_PROCESSING_OPTONS_COMMAND_START = hexStringToByteArray("80a8000023");
    //public static byte[] GET_PROCESSING_OPTONS_COMMAND = hexStringToByteArray("80a8000023832127000000000000001000000000000000097800000000000978230301003839303100");
    public static byte[] GET_PROCESSING_OPTONS_RESPONSE = hexStringToByteArray("7781c68202200094041002040057134779447134861024d29102012166408100000f9f100706010a03a020009f2608de1c4251186f7a5e9f2701809f3602007a9f6c0238009f4b81800705bd7c81dbb20c935af91e4ee6f084014891643b84d4cbb276cf5e1de6adaeb6045d40afb4a0bf1e1eb7494261c5d5007f2e6ce7eea4743910b7271ae4de4855fb51e15818d6547e16e7065e92aef57d82e5301a1b5387b05b88fff04dd8741e902e7f9b502ad1620fee4d790ad3f68fcffd58a60f88c9fe43196f242f9bf4");
    //public static byte[] GET_PROCESSING_OPTONS_RESPONSE = hexStringToByteArray("7781c68202200094041002040057134930005025003985d26092012166408100000f9f100706010a03a020009f2608de1c4251186f7a5e9f2701809f3602007a9f6c0238009f4b81800705bd7c81dbb20c935af91e4ee6f084014891643b84d4cbb276cf5e1de6adaeb6045d40afb4a0bf1e1eb7494261c5d5007f2e6ce7eea4743910b7271ae4de4855fb51e15818d6547e16e7065e92aef57d82e5301a1b5387b05b88fff04dd8741e902e7f9b502ad1620fee4d790ad3f68fcffd58a60f88c9fe43196f242f9bf4");

    // 94 04 -- Application File Locator (AFL) 10 03 06 00 (BINARY)
    // in total 3 files in SFI 10 (02, 03, 04)
    public static byte[] READ_FILE_10_02_COMMAND = hexStringToByteArray("00b2021400");
    public static byte[] READ_FILE_10_02_RESPONSE = hexStringToByteArray("7081fb9081f88893cf85a81325ab8da6a4196eb5787291db7205f61b172b26deb867da427f1d0e438e86400aea81a0f2826b250da618108389bdabe2a75c0168a28bb97645158b57ca8faa1d38d7a56e0a4171ec0d5e048d048dd98106bcadb3b5cac80485ff9c0fc970b4ea95d557fb9dd065bf75eb06f51df5a2c20479058ede6c8a376d9bfbf0c05b9e2b5aac1ec5982e2a9d861573e892da87b68357306e88cb054ab0090e01670a73d23fa239f4ae1283110fca40d46edc6c8021d15b3c147251b3c5e754f0fa9d82b7934ed34a12ef3d0a66c0c2a26a32e9722b10653516b356440aa8eece8d1d023829394adc2f9309ff60fc5baf51c0b24690be");

    public static byte[] READ_FILE_10_03_COMMAND = hexStringToByteArray("00b2031400");
    public static byte[] READ_FILE_10_03_RESPONSE = hexStringToByteArray("70079f3201038f0109");

    public static byte[] READ_FILE_10_04_COMMAND = hexStringToByteArray("00b2041400");
    public static byte[] READ_FILE_10_04_RESPONSE = hexStringToByteArray("7081eb9f4681b07e3b33a489fb75a23643407d2ebf48a808957165aa538d681213d71495b577086e63a24e847ed29d2ceba4bb3b1784361221287607ace4b8bfce09dd8364d4709293ed52b528623472fb6157094b12367534d7cf5c20b810058c817fb87c130111ee53c3855fd2b2a95449d03795541ea7c6ef942b0b069bfa7caa5d0ec6db0e428f18d03adcf7f92fb7e5516403adc629f3ffbd6900a1f308fbe5d28cba795c6c62d7573333abed15ad00a4da4ba8a99f4701035a0847794471348610245f24032910305f280202765f3401009f0702c0809f4a01829f6e04207000009f690701b419c7273800");

    public static byte[] SELECT_COMMAND3 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE3 = hexStringToByteArray("00");

    public static byte[] SELECT_COMMAND4 = hexStringToByteArray("00");
    public static byte[] SELECT_RESPONSE4 = hexStringToByteArray("00");

}

