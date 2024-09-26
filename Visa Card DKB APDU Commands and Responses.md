# APDU Commands and Responses for a Visa Card (here: DKB)

**Note on the Application Primary Account Number (PAN) in this document (= CreditCard number)** 
The PAN shown in this document is from an **outdated and deactivated Credit Card** so the data 
cannot be used for real transactions.

**OLD STUFF**

The PAN 5375050000160110 is found at 3 places in the data:
- read file SFI 08 file 01 9F 6B 13 -- Track 2 Data **53 75 05 00 00 16 01 10** D2 40 32 21 00 00 00 00
- read file SFI 10 file 01 5A 08 -- Application Primary Account Number (PAN) **53 75 05 00 00 16 01 10** (NUMERIC)
- read file SFI 10 file 01 57 13 -- Track 2 Equivalent Data **53 75 05 00 00 16 01 10** D2 40 32 21 27 94 32 90

The EXPIRE DATE 03/2024 is found at 3 places in the data (difficult to see !): 
- read file SFI 08 file 01 9F 6B 13 -- Track 2 Data 53 75 05 00 00 16 01 10 D**2 40 3**2 21 00 00 00 00
- read file SFI 10 file 01 57 13 -- Track 2 Equivalent Data 53 75 05 00 00 16 01 10 D**2 40 3**2 21 27 94 32 90

So if you are going to change any data you have to do this at several places ! A good help is to search for the 
hex encoded responses and replace the data accordingly. Please do not change the length of the PAN as the data 
gets invalidated by this.

```plaintext
NFC tag discovered
TagId: 0581d28b80f100
TechList found with these entries:
android.nfc.tech.IsoDep
android.nfc.tech.NfcA
connection with card success

*********************************
************ step 00 ************
* our journey begins            *
*********************************
increase IsoDep timeout for long lasting reading
timeout old: 618 ms
timeout new: 10000 ms

*********************************
************ step 01 ************
* select PPSE                   *
*********************************
01 select PPSE command  length 20 data: 00a404000e325041592e5359532e444446303100
01 select PPSE response length 60 data: 6f38840e325041592e5359532e4444463031a526bf0c2361214f07a000000003101050085649534120444b428701019f0a0800010502000000009000
------------------------------------
6F 38 -- File Control Information (FCI) Template
      84 0E -- Dedicated File (DF) Name
            32 50 41 59 2E 53 59 53 2E 44 44 46 30 31 (BINARY)
      A5 26 -- File Control Information (FCI) Proprietary Template
            BF 0C 23 -- File Control Information (FCI) Issuer Discretionary Data
                     61 21 -- Application Template
                           4F 07 -- Application Identifier (AID) - card
                                 A0 00 00 00 03 10 10 (BINARY)
                           50 08 -- Application Label
                                 56 49 53 41 20 44 4B 42 (=VISA DKB)
                           87 01 -- Application Priority Indicator
                                 01 (BINARY)
                           9F 0A 08 -- [UNKNOWN TAG]
                                    00 01 05 02 00 00 00 00 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

*********************************
************ step 02 ************
* search applications on card   *
*********************************
02 analyze select PPSE response and search for tag 0x4F (applications on card)
Found tag 0x4F 1 time:
application Id (AID): a0000000031010

*********************************
************ step 03 ************
* select application by AID     *
*********************************
03 select application by AID a0000000031010 (number 1)

03 select AID command  length 13 data: 00a4040007a000000003101000
03 select AID response length 91 data: 6f578407a0000000031010a54c50085649534120444b428701015f2d046465656e9f38189f66049f02069f03069f1a0295055f2a029a039c019f3704bf0c1a9f0a0800010502000000009f5a053109780276bf6304df2001809000
------------------------------------
6F 57 -- File Control Information (FCI) Template
      84 07 -- Dedicated File (DF) Name
            A0 00 00 00 03 10 10 (BINARY)
      A5 4C -- File Control Information (FCI) Proprietary Template
            50 08 -- Application Label
                  56 49 53 41 20 44 4B 42 (=VISA DKB)
            87 01 -- Application Priority Indicator
                  01 (BINARY)
            5F 2D 04 -- Language Preference
                     64 65 65 6E (=deen)
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
            BF 0C 1A -- File Control Information (FCI) Issuer Discretionary Data
                     9F 0A 08 -- [UNKNOWN TAG]
                              00 01 05 02 00 00 00 00 (BINARY)
                     9F 5A 05 -- Terminal transaction Type (Interac)
                              31 09 78 02 76 (BINARY)
                     BF 63 04 -- [UNKNOWN TAG]
                              DF 20 01 -- [UNKNOWN TAG]
                                       80 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

*********************************
************ step 04 ************
* search for tag 0x9F38         *
*********************************
04 search for tag 0x9F38 in the selectAid response
Available predefined values for PDOL and CDOL
List of predefined tag and values for PDOL and CDOL
Tag  Name                            Value
-------------------------------------------------
9f66 Terminal Transaction Qualifiers 27000000
9f66 Terminal Transaction Qualifiers 27000000
9f66 Terminal Transaction Qualifiers b7604000
9f66 Terminal Transaction Qualifiers a0000000
9f66 Terminal Transaction Qualifiers f0204000
9f02 Transaction Amount              000000001000
9f03 Amount, Other (Numeric)         000000000000
9f1a Terminal Country Code           0978
95   Terminal Verificat.Results      0000000000
5f2a Transaction Currency Code       0978
9a   Transaction Date                230301
9c   Transaction Type                00
9f37 Unpredictable Number            38393031
9f35 Terminal Type                   22
9f45 Data Authentication Code        0000
9f4c ICC Dynamic Number              0000000000000000
9f34 Terminal Transaction Qualifiers 000000
9f21 Transaction Time (HHMMSS)       111009
9f7c Merchant Custom Data            0000000000000000000000000000
00   Tag not found                   00

### processing the American Express, VisaCard and GiroCard path ###

found tag 0x9F38 (PDOL) in the selectAid with this length: 24 data: 9f66049f02069f03069f1a0295055f2a029a039c019f3704

The card is requesting 9 tags in the PDOL

Tag  Tag Name                        Length Value
-----------------------------------------------------
9f66 Terminal Transaction Qualifiers     4  27 00 00 00 
9f02 Amount, Authorised (Numeric)        6  00 00 00 00 10 00 
9f03 Amount, Other (Numeric)             6  00 00 00 00 00 00 
9f1a Terminal Country Code               2  09 78 
95   Terminal Verification Results (TVR) 5  00 00 00 00 00 
5f2a Transaction Currency Code           2  09 78 
9a   Transaction Date                    3  23 03 01 
9c   Transaction Type                    1  00 
9f37 Unpredictable Number                4  38 39 30 31 
-----------------------------------------------------

*********************************
************ step 05 ************
* get the processing options    *
*********************************
05 get the processing options  command length: 41 data: 80a8000023832127000000000000001000000000000000097800000000000978230301003839303100
05 get the processing options response length: 203 data: 7781c68202200094041002040057134930005025003985d26092012166408100000f9f100706010a03a020009f2608de1c4251186f7a5e9f2701809f3602007a9f6c0238009f4b81800705bd7c81dbb20c935af91e4ee6f084014891643b84d4cbb276cf5e1de6adaeb6045d40afb4a0bf1e1eb7494261c5d5007f2e6ce7eea4743910b7271ae4de4855fb51e15818d6547e16e7065e92aef57d82e5301a1b5387b05b88fff04dd8741e902e7f9b502ad1620fee4d790ad3f68fcffd58a60f88c9fe43196f242f9bf49000
------------------------------------
77 81 C6 -- Response Message Template Format 2
         82 02 -- Application Interchange Profile
               20 00 (BINARY)
         94 04 -- Application File Locator (AFL)
               10 02 04 00 (BINARY)
         57 13 -- Track 2 Equivalent Data
               49 30 00 50 25 00 39 85 D2 60 92 01 21 66 40 81
               00 00 0F (BINARY)
         9F 10 07 -- Issuer Application Data
                  06 01 0A 03 A0 20 00 (BINARY)
         9F 26 08 -- Application Cryptogram
                  DE 1C 42 51 18 6F 7A 5E (BINARY)
         9F 27 01 -- Cryptogram Information Data
                  80 (BINARY)
         9F 36 02 -- Application Transaction Counter (ATC)
                  00 7A (BINARY)
         9F 6C 02 -- Mag Stripe Application Version Number (Card)
                  38 00 (BINARY)
         9F 4B 81 80 -- Signed Dynamic Application Data
                     07 05 BD 7C 81 DB B2 0C 93 5A F9 1E 4E E6 F0 84
                     01 48 91 64 3B 84 D4 CB B2 76 CF 5E 1D E6 AD AE
                     B6 04 5D 40 AF B4 A0 BF 1E 1E B7 49 42 61 C5 D5
                     00 7F 2E 6C E7 EE A4 74 39 10 B7 27 1A E4 DE 48
                     55 FB 51 E1 58 18 D6 54 7E 16 E7 06 5E 92 AE F5
                     7D 82 E5 30 1A 1B 53 87 B0 5B 88 FF F0 4D D8 74
                     1E 90 2E 7F 9B 50 2A D1 62 0F EE 4D 79 0A D3 F6
                     8F CF FD 58 A6 0F 88 C9 FE 43 19 6F 24 2F 9B F4 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

workflow a)

*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card skipped
the response contains a Track 2 Equivalent Data tag [tag 0x57]
the response contains a Track 2 Equivalent Data tag [tag 0x57]
found tag 0x57 in the gpoResponse length: 19 data: 4930005025003985d26092012166408100000f
found a PAN 4930005025003985 with Expiration date: 2609

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tag 0x57 (Track 2 Equivalent Data)
data for AID a0000000031010
PAN: 4930005025003985
Expiration date (YYMM): 2609

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 4 data: 10020400

*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 1 entry to read
for SFI 10 we read 3 records
readRecord  command length: 5 data: 00b2021400
readRecord response length: 256 data: 7081fb9081f88893cf85a81325ab8da6a4196eb5787291db7205f61b172b26deb867da427f1d0e438e86400aea81a0f2826b250da618108389bdabe2a75c0168a28bb97645158b57ca8faa1d38d7a56e0a4171ec0d5e048d048dd98106bcadb3b5cac80485ff9c0fc970b4ea95d557fb9dd065bf75eb06f51df5a2c20479058ede6c8a376d9bfbf0c05b9e2b5aac1ec5982e2a9d861573e892da87b68357306e88cb054ab0090e01670a73d23fa239f4ae1283110fca40d46edc6c8021d15b3c147251b3c5e754f0fa9d82b7934ed34a12ef3d0a66c0c2a26a32e9722b10653516b356440aa8eece8d1d023829394adc2f9309ff60fc5baf51c0b24690be9000
------------------------------------
70 81 FB -- Record Template (EMV Proprietary)
         90 81 F8 -- Issuer Public Key Certificate
                  88 93 CF 85 A8 13 25 AB 8D A6 A4 19 6E B5 78 72
                  91 DB 72 05 F6 1B 17 2B 26 DE B8 67 DA 42 7F 1D
                  0E 43 8E 86 40 0A EA 81 A0 F2 82 6B 25 0D A6 18
                  10 83 89 BD AB E2 A7 5C 01 68 A2 8B B9 76 45 15
                  8B 57 CA 8F AA 1D 38 D7 A5 6E 0A 41 71 EC 0D 5E
                  04 8D 04 8D D9 81 06 BC AD B3 B5 CA C8 04 85 FF
                  9C 0F C9 70 B4 EA 95 D5 57 FB 9D D0 65 BF 75 EB
                  06 F5 1D F5 A2 C2 04 79 05 8E DE 6C 8A 37 6D 9B
                  FB F0 C0 5B 9E 2B 5A AC 1E C5 98 2E 2A 9D 86 15
                  73 E8 92 DA 87 B6 83 57 30 6E 88 CB 05 4A B0 09
                  0E 01 67 0A 73 D2 3F A2 39 F4 AE 12 83 11 0F CA
                  40 D4 6E DC 6C 80 21 D1 5B 3C 14 72 51 B3 C5 E7
                  54 F0 FA 9D 82 B7 93 4E D3 4A 12 EF 3D 0A 66 C0
                  C2 A2 6A 32 E9 72 2B 10 65 35 16 B3 56 44 0A A8
                  EE CE 8D 1D 02 38 29 39 4A DC 2F 93 09 FF 60 FC
                  5B AF 51 C0 B2 46 90 BE (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord  command length: 5 data: 00b2031400
readRecord response length: 11 data: 70079f3201038f01099000
------------------------------------
70 07 -- Record Template (EMV Proprietary)
      9F 32 01 -- Issuer Public Key Exponent
               03 (BINARY)
      8F 01 -- Certification Authority Public Key Index - card
            09 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord  command length: 5 data: 00b2041400
readRecord response length: 240 data: 7081eb9f4681b07e3b33a489fb75a23643407d2ebf48a808957165aa538d681213d71495b577086e63a24e847ed29d2ceba4bb3b1784361221287607ace4b8bfce09dd8364d4709293ed52b528623472fb6157094b12367534d7cf5c20b810058c817fb87c130111ee53c3855fd2b2a95449d03795541ea7c6ef942b0b069bfa7caa5d0ec6db0e428f18d03adcf7f92fb7e5516403adc629f3ffbd6900a1f308fbe5d28cba795c6c62d7573333abed15ad00a4da4ba8a99f4701035a0849300050250039855f24032609305f280202765f3401009f0702c0809f4a01829f6e04207000009f690701b419c72738009000
------------------------------------
70 81 EB -- Record Template (EMV Proprietary)
         9F 46 81 B0 -- ICC Public Key Certificate
                     7E 3B 33 A4 89 FB 75 A2 36 43 40 7D 2E BF 48 A8
                     08 95 71 65 AA 53 8D 68 12 13 D7 14 95 B5 77 08
                     6E 63 A2 4E 84 7E D2 9D 2C EB A4 BB 3B 17 84 36
                     12 21 28 76 07 AC E4 B8 BF CE 09 DD 83 64 D4 70
                     92 93 ED 52 B5 28 62 34 72 FB 61 57 09 4B 12 36
                     75 34 D7 CF 5C 20 B8 10 05 8C 81 7F B8 7C 13 01
                     11 EE 53 C3 85 5F D2 B2 A9 54 49 D0 37 95 54 1E
                     A7 C6 EF 94 2B 0B 06 9B FA 7C AA 5D 0E C6 DB 0E
                     42 8F 18 D0 3A DC F7 F9 2F B7 E5 51 64 03 AD C6
                     29 F3 FF BD 69 00 A1 F3 08 FB E5 D2 8C BA 79 5C
                     6C 62 D7 57 33 33 AB ED 15 AD 00 A4 DA 4B A8 A9 (BINARY)
         9F 47 01 -- ICC Public Key Exponent
                  03 (BINARY)
         5A 08 -- Application Primary Account Number (PAN)
               49 30 00 50 25 00 39 85 (NUMERIC)
         5F 24 03 -- Application Expiration Date
                  26 09 30 (NUMERIC)
         5F 28 02 -- Issuer Country Code
                  02 76 (NUMERIC)
         5F 34 01 -- Application Primary Account Number (PAN) Sequence Number
                  00 (NUMERIC)
         9F 07 02 -- Application Usage Control
                  C0 80 (BINARY)
         9F 4A 01 -- Static Data Authentication Tag List
                  82 (BINARY)
         9F 6E 04 -- Visa Low-Value Payment (VLP) Issuer Authorisation Code
                  20 70 00 00 (BINARY)
         9F 69 07 -- UDOL
                  01 B4 19 C7 27 38 00 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

found tag 0x5a in the readRecordResponse length: 8 data: 4930005025003985
found tag 0x5f24 in the readRecordResponse length: 3 data: 260930

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID a0000000031010
PAN: 4930005025003985
Expiration date (YYMMDD): 260930

*********************************
************ step 99 ************
* our journey ends              *
*********************************
```