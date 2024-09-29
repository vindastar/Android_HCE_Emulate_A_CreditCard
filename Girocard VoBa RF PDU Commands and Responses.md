# APDU Commands and Responses for a Girocard (here: VoBa Raesfeld Erle RF)

```none
PAN: 6726428902046846007
Expiration date (YYMMDD): 241231

Real IBAN: DE69 4006 9606 0204 6846 00

Change PAN from 67264289 0204684600 7
             to          0204686200 

old: 672642890204684600
new: 672642890204686200 
Search for 0204684600
found in:
SFI 8 file 5 
SFI 8 file 3


```

This tag contains 4 AIDs for different purposes:
```none
Found tag 0x4F 4 times:
application Id (AID): a00000005945430100   Zentraler Kreditausschuss (ZKA) - Girocard Electronic Cash
application Id (AID): a0000003591010028001 Euro Alliance of Payment Schemes s.c.r.l. EAPS - Girocard EAPS (abandoned sometime after 2013)
application Id (AID): d27600002547410100   ZKA - Girocard ATM
application Id (AID): a0000000043060       Mastercard International - Maestro (Debit) Card
```
```none
For usage at POS terminal I'm using the AID "A00000005945430100"
                                             A0000000031010
6F File Control Information (FCI) Template
 	84 Dedicated File (DF) Name
 	 	325041592E5359532E4444463031
 	A5 File Control Information (FCI) Proprietary Template
 	 	BF0C File Control Information (FCI) Issuer Discretionary Data
 	 	 	61 Application Template
 	 	 	 	4F Application Identifier (AID) – card
 	 	 	 	 	A0000000031010
 	 	 	 	9F0A Unknown tag
 	 	 	 	 	0001050100000000
 	 	 	 	 	
6f2b840e325041592e5359532e4444463031a519bf0c1661144f07a00000000310109f0a080001050100000000
6f2d840e325041592e5359532e4444463031a51bbf0c1861164f09A000000059454301009f0a080001050100000000	 // correct 	  	 	 	
 	 	 	 	 	                                             
```
```none
NFC tag discovered
TagId: 0801f4b4
TechList found with these entries:
android.nfc.tech.IsoDep
android.nfc.tech.NfcA
connection with card success

*********************************
************ step 00 ************
* our journey begins            *
*********************************
increase IsoDep timeout for long lasting reading
timeout old: 2000 ms
timeout new: 10000 ms

*********************************
************ step 01 ************
* select PPSE                   *
*********************************
01 select PPSE command  length 20 data: 00a404000e325041592e5359532e444446303100
01 select PPSE response length 133 data: 6f8180840e325041592e5359532e4444463031a56ebf0c6b61194f09a000000059454301008701019f0a080001050100000000611a4f0aa00000035910100280018701019f0a08000105010000000061194f09d276000025474101008701019f0a08000105010000000061174f07a00000000430608701019f0a0800010501000000009000
------------------------------------
6F 81 80 -- File Control Information (FCI) Template
         84 0E -- Dedicated File (DF) Name
               32 50 41 59 2E 53 59 53 2E 44 44 46 30 31 (BINARY)
         A5 6E -- File Control Information (FCI) Proprietary Template
               BF 0C 6B -- File Control Information (FCI) Issuer Discretionary Data
                        61 19 -- Application Template
                              4F 09 -- Application Identifier (AID) - card
                                    A0 00 00 00 59 45 43 01 00 (BINARY)
                              87 01 -- Application Priority Indicator
                                    01 (BINARY)
                              9F 0A 08 -- [UNKNOWN TAG]
                                       00 01 05 01 00 00 00 00 (BINARY)
                        61 1A -- Application Template
                              4F 0A -- Application Identifier (AID) - card
                                    A0 00 00 03 59 10 10 02 80 01 (BINARY)
                              87 01 -- Application Priority Indicator
                                    01 (BINARY)
                              9F 0A 08 -- [UNKNOWN TAG]
                                       00 01 05 01 00 00 00 00 (BINARY)
                        61 19 -- Application Template
                              4F 09 -- Application Identifier (AID) - card
                                    D2 76 00 00 25 47 41 01 00 (BINARY)
                              87 01 -- Application Priority Indicator
                                    01 (BINARY)
                              9F 0A 08 -- [UNKNOWN TAG]
                                       00 01 05 01 00 00 00 00 (BINARY)
                        61 17 -- Application Template
                              4F 07 -- Application Identifier (AID) - card
                                    A0 00 00 00 04 30 60 (BINARY)
                              87 01 -- Application Priority Indicator
                                    01 (BINARY)
                              9F 0A 08 -- [UNKNOWN TAG]
                                       00 01 05 01 00 00 00 00 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

*********************************
************ step 02 ************
* search applications on card   *
*********************************
02 analyze select PPSE response and search for tag 0x4F (applications on card)
Found tag 0x4F 4 times:
application Id (AID): a00000005945430100
application Id (AID): a0000003591010028001
application Id (AID): d27600002547410100
application Id (AID): a0000000043060

*********************************
************ step 03 ************
* select application by AID     *
*********************************
03 select application by AID a00000005945430100 (number 1)

03 select AID command  length 15 data: 00a4040009a0000000594543010000
03 select AID response length 75 data: 6f478409a00000005945430100a53a50086769726f636172648701019f38069f02069f1d025f2d046465656ebf0c1a9f4d02190a9f6e07028000003030009f0a0800010501000000009000
------------------------------------
6F 47 -- File Control Information (FCI) Template
      84 09 -- Dedicated File (DF) Name
            A0 00 00 00 59 45 43 01 00 (BINARY)
      A5 3A -- File Control Information (FCI) Proprietary Template
            50 08 -- Application Label
                  67 69 72 6F 63 61 72 64 (=girocard)
            87 01 -- Application Priority Indicator
                  01 (BINARY)
            9F 38 06 -- Processing Options Data Object List (PDOL)
                     9F 02 06 -- Amount, Authorised (Numeric)
                     9F 1D 02 -- Terminal Risk Management Data
            5F 2D 04 -- Language Preference
                     64 65 65 6E (=deen)
            BF 0C 1A -- File Control Information (FCI) Issuer Discretionary Data
                     9F 4D 02 -- Log Entry
                              19 0A (BINARY)
                     9F 6E 07 -- Visa Low-Value Payment (VLP) Issuer Authorisation Code
                              02 80 00 00 30 30 00 (BINARY)
                     9F 0A 08 -- [UNKNOWN TAG]
                              00 01 05 01 00 00 00 00 (BINARY)
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

found tag 0x9F38 (PDOL) in the selectAid with this length: 6 data: 9f02069f1d02

The card is requesting 2 tags in the PDOL

Tag  Tag Name                        Length Value
-----------------------------------------------------
9f02 Amount, Authorised (Numeric)        6  00 00 00 00 10 00 
9f1d Terminal Risk Management Data       2  00 00 
-----------------------------------------------------

*********************************
************ step 05 ************
* get the processing options    *
*********************************
05 get the processing options  command length: 16 data: 80a800000a8308000000001000000000
05 get the processing options response length: 34 data: 771e8202198094181801020020010100400404000805050108070701080303019000
------------------------------------
77 1E -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 18 -- Application File Locator (AFL)
            18 01 02 00 20 01 01 00 40 04 04 00 08 05 05 01
            08 07 07 01 08 03 03 01 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 24 data: 180102002001010040040400080505010807070108030301

*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 6 entries to read
for SFI 18 we read 2 records
readRecord SFI 18 file 2 command length: 5 data: 00b2011c00
readRecord response length: 51 data: 702f8f01059f3201039224b0568adf146b092492be46e5d57d920b026be8e734264cf34710483a0af52d46790f01ab00009000
------------------------------------
70 2F -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 18 file 2 command length: 5 data: 00b2021c00
readRecord response length: 184 data: 7081b39081b068361092b2690a2b391a6afb325df0abe55b989238b81c5b78058a093c790bab9768c6b1dca6fc0258fa2cb9af1f78ed4d8dfd95ca837fbc2a2b6d56202e6432f7e4fc7669b2a7f637866921dc642de9bfec27747b4a6a70288ab6395fbf2c63b0c1feaede3051df9b37ff0e067c23d9ab477ad5985c86e80348302e7a5c73f3d2cba543c72a8095a99305be9a88178d54954c1c5dc7957668b89f4ac98f8f40062bc60fcd68dee5b600378585b5d8ea9000
------------------------------------
70 81 B3 -- Record Template (EMV Proprietary)
         90 81 B0 -- Issuer Public Key Certificate
                  68 36 10 92 B2 69 0A 2B 39 1A 6A FB 32 5D F0 AB
                  E5 5B 98 92 38 B8 1C 5B 78 05 8A 09 3C 79 0B AB
                  97 68 C6 B1 DC A6 FC 02 58 FA 2C B9 AF 1F 78 ED
                  4D 8D FD 95 CA 83 7F BC 2A 2B 6D 56 20 2E 64 32
                  F7 E4 FC 76 69 B2 A7 F6 37 86 69 21 DC 64 2D E9
                  BF EC 27 74 7B 4A 6A 70 28 8A B6 39 5F BF 2C 63
                  B0 C1 FE AE DE 30 51 DF 9B 37 FF 0E 06 7C 23 D9
                  AB 47 7A D5 98 5C 86 E8 03 48 30 2E 7A 5C 73 F3
                  D2 CB A5 43 C7 2A 80 95 A9 93 05 BE 9A 88 17 8D
                  54 95 4C 1C 5D C7 95 76 68 B8 9F 4A C9 8F 8F 40
                  06 2B C6 0F CD 68 DE E5 B6 00 37 85 85 B5 D8 EA (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 20 we read 1 record
readRecord SFI 20 file 1 command length: 5 data: 00b2012400
readRecord response length: 55 data: 70339f47030100019f480a4947098e18cbf3fba1fb00000000000000000000000000000000000000000000000000000000000000009000
------------------------------------
70 33 -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 40 we read 1 record
readRecord SFI 40 file 4 command length: 5 data: 00b2044400
readRecord response length: 185 data: 7081b49f4681b04bc08f3692571942b499ceff18236a9281ac29a85956aebf05f1fec66364229be5366e4a81efcfdaf3b4d9ec7295214893cf9a56c81989f4617b3fc1f86825a2caf028ca74fdd15ad58ba528ea83dee573ee1007c30215a0e3db978a623cbb2cb385ebeaa991c52ab8cabd83e14753b2e1d10abf42683acb3dff67922af5800e667617e63aca34fde3fc8bbe5392709408f0fc3853c10f2f516b1c288bf8f83ca091a01a290c513e321c7f17901259029000
------------------------------------
70 81 B4 -- Record Template (EMV Proprietary)
         9F 46 81 B0 -- ICC Public Key Certificate
                     4B C0 8F 36 92 57 19 42 B4 99 CE FF 18 23 6A 92
                     81 AC 29 A8 59 56 AE BF 05 F1 FE C6 63 64 22 9B
                     E5 36 6E 4A 81 EF CF DA F3 B4 D9 EC 72 95 21 48
                     93 CF 9A 56 C8 19 89 F4 61 7B 3F C1 F8 68 25 A2
                     CA F0 28 CA 74 FD D1 5A D5 8B A5 28 EA 83 DE E5
                     73 EE 10 07 C3 02 15 A0 E3 DB 97 8A 62 3C BB 2C
                     B3 85 EB EA A9 91 C5 2A B8 CA BD 83 E1 47 53 B2
                     E1 D1 0A BF 42 68 3A CB 3D FF 67 92 2A F5 80 0E
                     66 76 17 E6 3A CA 34 FD E3 FC 8B BE 53 92 70 94
                     08 F0 FC 38 53 C1 0F 2F 51 6B 1C 28 8B F8 F8 3C
                     A0 91 A0 1A 29 0C 51 3E 32 1C 7F 17 90 12 59 02 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 5 command length: 5 data: 00b2050c00
readRecord response length: 60 data: 70385f24032412315a0a6726428902046846007f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c9000
------------------------------------
70 38 -- Record Template (EMV Proprietary)
      5F 24 03 -- Application Expiration Date
               24 12 31 (NUMERIC)
      5A 0A -- Application Primary Account Number (PAN)
            67 26 42 89 02 04 68 46 00 7F (NUMERIC)
      5F 34 01 -- Application Primary Account Number (PAN) Sequence Number
               00 (NUMERIC)
      5F 28 02 -- Issuer Country Code
               02 80 (NUMERIC)
      9F 07 02 -- Application Usage Control
               FF C0 (BINARY)
      9F 0D 05 -- Issuer Action Code - Default
               FC 40 A4 80 0C (BINARY)
      9F 0E 05 -- Issuer Action Code - Denial
               00 10 18 00 00 (BINARY)
      9F 0F 05 -- Issuer Action Code - Online
               FC 40 A4 98 0C (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

found tag 0x5a in the readRecordResponse length: 10 data: 6726428902046846007f
found tag 0x5f24 in the readRecordResponse length: 3 data: 241231

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID a00000005945430100
PAN: 6726428902046846007
Expiration date (YYMMDD): 241231

for SFI 8 we read 1 record
readRecord SFI 8 file 7 command length: 5 data: 00b2070c00
readRecord response length: 27 data: 70178e0c00000000000000001f0302039f080200029f4a01829000
------------------------------------
70 17 -- Record Template (EMV Proprietary)
      8E 0C -- Cardholder Verification Method (CVM) List
            00 00 00 00 00 00 00 00 1F 03 02 03 (BINARY)
      9F 08 02 -- Application Version Number - card
               00 02 (BINARY)
      9F 4A 01 -- Static Data Authentication Tag List
               82 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 3 command length: 5 data: 00b2030c00
readRecord response length: 65 data: 703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136726428902046846007d24122010210104669f9000
------------------------------------
70 3D -- Record Template (EMV Proprietary)
      8C 1B -- Card Risk Management Data Object List 1 (CDOL1)
            9F 02 06 -- Amount, Authorised (Numeric)
            9F 03 06 -- Amount, Other (Numeric)
            9F 1A 02 -- Terminal Country Code
            95 05 -- Terminal Verification Results (TVR)
            5F 2A 02 -- Transaction Currency Code
            9A 03 -- Transaction Date
            9C 01 -- Transaction Type
            9F 37 04 -- Unpredictable Number
            9F 35 01 -- Terminal Type
            9F 34 03 -- Cardholder Verification (CVM) Results
      8D 09 -- Card Risk Management Data Object List 2 (CDOL2)
            91 10 -- Issuer Authentication Data
            8A 02 -- Authorisation Response Code
            95 05 -- Terminal Verification Results (TVR)
            9F 37 04 -- Unpredictable Number
      57 13 -- Track 2 Equivalent Data
            67 26 42 89 02 04 68 46 00 7D 24 12 20 10 21 01
            04 66 9F (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

*********************************
************ step 03 ************
* select application by AID     *
*********************************
03 select application by AID a0000003591010028001 (number 2)

03 select AID command  length 16 data: 00a404000aa000000359101002800100
03 select AID response length 76 data: 6f48840aa0000003591010028001a53a50086769726f636172648701019f38069f02069f1d025f2d046465656ebf0c1a9f4d02190a9f6e07028000003030009f0a0800010501000000009000
------------------------------------
6F 48 -- File Control Information (FCI) Template
      84 0A -- Dedicated File (DF) Name
            A0 00 00 03 59 10 10 02 80 01 (BINARY)
      A5 3A -- File Control Information (FCI) Proprietary Template
            50 08 -- Application Label
                  67 69 72 6F 63 61 72 64 (=girocard)
            87 01 -- Application Priority Indicator
                  01 (BINARY)
            9F 38 06 -- Processing Options Data Object List (PDOL)
                     9F 02 06 -- Amount, Authorised (Numeric)
                     9F 1D 02 -- Terminal Risk Management Data
            5F 2D 04 -- Language Preference
                     64 65 65 6E (=deen)
            BF 0C 1A -- File Control Information (FCI) Issuer Discretionary Data
                     9F 4D 02 -- Log Entry
                              19 0A (BINARY)
                     9F 6E 07 -- Visa Low-Value Payment (VLP) Issuer Authorisation Code
                              02 80 00 00 30 30 00 (BINARY)
                     9F 0A 08 -- [UNKNOWN TAG]
                              00 01 05 01 00 00 00 00 (BINARY)
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

found tag 0x9F38 (PDOL) in the selectAid with this length: 6 data: 9f02069f1d02

The card is requesting 2 tags in the PDOL

Tag  Tag Name                        Length Value
-----------------------------------------------------
9f02 Amount, Authorised (Numeric)        6  00 00 00 00 10 00 
9f1d Terminal Risk Management Data       2  00 00 
-----------------------------------------------------

*********************************
************ step 05 ************
* get the processing options    *
*********************************
05 get the processing options  command length: 16 data: 80a800000a8308000000001000000000
05 get the processing options response length: 34 data: 771e8202198094181801020020010100400404000805050108070701080303019000
------------------------------------
77 1E -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 18 -- Application File Locator (AFL)
            18 01 02 00 20 01 01 00 40 04 04 00 08 05 05 01
            08 07 07 01 08 03 03 01 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 24 data: 180102002001010040040400080505010807070108030301

*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 6 entries to read
for SFI 18 we read 2 records
readRecord SFI 18 file 2 command length: 5 data: 00b2011c00
readRecord response length: 51 data: 702f8f01059f3201039224b0568adf146b092492be46e5d57d920b026be8e734264cf34710483a0af52d46790f01ab00009000
------------------------------------
70 2F -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 18 file 2 command length: 5 data: 00b2021c00
readRecord response length: 184 data: 7081b39081b068361092b2690a2b391a6afb325df0abe55b989238b81c5b78058a093c790bab9768c6b1dca6fc0258fa2cb9af1f78ed4d8dfd95ca837fbc2a2b6d56202e6432f7e4fc7669b2a7f637866921dc642de9bfec27747b4a6a70288ab6395fbf2c63b0c1feaede3051df9b37ff0e067c23d9ab477ad5985c86e80348302e7a5c73f3d2cba543c72a8095a99305be9a88178d54954c1c5dc7957668b89f4ac98f8f40062bc60fcd68dee5b600378585b5d8ea9000
------------------------------------
70 81 B3 -- Record Template (EMV Proprietary)
         90 81 B0 -- Issuer Public Key Certificate
                  68 36 10 92 B2 69 0A 2B 39 1A 6A FB 32 5D F0 AB
                  E5 5B 98 92 38 B8 1C 5B 78 05 8A 09 3C 79 0B AB
                  97 68 C6 B1 DC A6 FC 02 58 FA 2C B9 AF 1F 78 ED
                  4D 8D FD 95 CA 83 7F BC 2A 2B 6D 56 20 2E 64 32
                  F7 E4 FC 76 69 B2 A7 F6 37 86 69 21 DC 64 2D E9
                  BF EC 27 74 7B 4A 6A 70 28 8A B6 39 5F BF 2C 63
                  B0 C1 FE AE DE 30 51 DF 9B 37 FF 0E 06 7C 23 D9
                  AB 47 7A D5 98 5C 86 E8 03 48 30 2E 7A 5C 73 F3
                  D2 CB A5 43 C7 2A 80 95 A9 93 05 BE 9A 88 17 8D
                  54 95 4C 1C 5D C7 95 76 68 B8 9F 4A C9 8F 8F 40
                  06 2B C6 0F CD 68 DE E5 B6 00 37 85 85 B5 D8 EA (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 20 we read 1 record
readRecord SFI 20 file 1 command length: 5 data: 00b2012400
readRecord response length: 55 data: 70339f47030100019f480a4947098e18cbf3fba1fb00000000000000000000000000000000000000000000000000000000000000009000
------------------------------------
70 33 -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 40 we read 1 record
readRecord SFI 40 file 4 command length: 5 data: 00b2044400
readRecord response length: 185 data: 7081b49f4681b04bc08f3692571942b499ceff18236a9281ac29a85956aebf05f1fec66364229be5366e4a81efcfdaf3b4d9ec7295214893cf9a56c81989f4617b3fc1f86825a2caf028ca74fdd15ad58ba528ea83dee573ee1007c30215a0e3db978a623cbb2cb385ebeaa991c52ab8cabd83e14753b2e1d10abf42683acb3dff67922af5800e667617e63aca34fde3fc8bbe5392709408f0fc3853c10f2f516b1c288bf8f83ca091a01a290c513e321c7f17901259029000
------------------------------------
70 81 B4 -- Record Template (EMV Proprietary)
         9F 46 81 B0 -- ICC Public Key Certificate
                     4B C0 8F 36 92 57 19 42 B4 99 CE FF 18 23 6A 92
                     81 AC 29 A8 59 56 AE BF 05 F1 FE C6 63 64 22 9B
                     E5 36 6E 4A 81 EF CF DA F3 B4 D9 EC 72 95 21 48
                     93 CF 9A 56 C8 19 89 F4 61 7B 3F C1 F8 68 25 A2
                     CA F0 28 CA 74 FD D1 5A D5 8B A5 28 EA 83 DE E5
                     73 EE 10 07 C3 02 15 A0 E3 DB 97 8A 62 3C BB 2C
                     B3 85 EB EA A9 91 C5 2A B8 CA BD 83 E1 47 53 B2
                     E1 D1 0A BF 42 68 3A CB 3D FF 67 92 2A F5 80 0E
                     66 76 17 E6 3A CA 34 FD E3 FC 8B BE 53 92 70 94
                     08 F0 FC 38 53 C1 0F 2F 51 6B 1C 28 8B F8 F8 3C
                     A0 91 A0 1A 29 0C 51 3E 32 1C 7F 17 90 12 59 02 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 5 command length: 5 data: 00b2050c00
readRecord response length: 60 data: 70385f24032412315a0a6726428902046846007f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c9000
------------------------------------
70 38 -- Record Template (EMV Proprietary)
      5F 24 03 -- Application Expiration Date
               24 12 31 (NUMERIC)
      5A 0A -- Application Primary Account Number (PAN)
            67 26 42 89 02 04 68 46 00 7F (NUMERIC)
      5F 34 01 -- Application Primary Account Number (PAN) Sequence Number
               00 (NUMERIC)
      5F 28 02 -- Issuer Country Code
               02 80 (NUMERIC)
      9F 07 02 -- Application Usage Control
               FF C0 (BINARY)
      9F 0D 05 -- Issuer Action Code - Default
               FC 40 A4 80 0C (BINARY)
      9F 0E 05 -- Issuer Action Code - Denial
               00 10 18 00 00 (BINARY)
      9F 0F 05 -- Issuer Action Code - Online
               FC 40 A4 98 0C (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

found tag 0x5a in the readRecordResponse length: 10 data: 6726428902046846007f
found tag 0x5f24 in the readRecordResponse length: 3 data: 241231

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID a0000003591010028001
PAN: 6726428902046846007
Expiration date (YYMMDD): 241231

for SFI 8 we read 1 record
readRecord SFI 8 file 7 command length: 5 data: 00b2070c00
readRecord response length: 27 data: 70178e0c00000000000000001f0302039f080200029f4a01829000
------------------------------------
70 17 -- Record Template (EMV Proprietary)
      8E 0C -- Cardholder Verification Method (CVM) List
            00 00 00 00 00 00 00 00 1F 03 02 03 (BINARY)
      9F 08 02 -- Application Version Number - card
               00 02 (BINARY)
      9F 4A 01 -- Static Data Authentication Tag List
               82 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 3 command length: 5 data: 00b2030c00
readRecord response length: 65 data: 703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136726428902046846007d24122010210104669f9000
------------------------------------
70 3D -- Record Template (EMV Proprietary)
      8C 1B -- Card Risk Management Data Object List 1 (CDOL1)
            9F 02 06 -- Amount, Authorised (Numeric)
            9F 03 06 -- Amount, Other (Numeric)
            9F 1A 02 -- Terminal Country Code
            95 05 -- Terminal Verification Results (TVR)
            5F 2A 02 -- Transaction Currency Code
            9A 03 -- Transaction Date
            9C 01 -- Transaction Type
            9F 37 04 -- Unpredictable Number
            9F 35 01 -- Terminal Type
            9F 34 03 -- Cardholder Verification (CVM) Results
      8D 09 -- Card Risk Management Data Object List 2 (CDOL2)
            91 10 -- Issuer Authentication Data
            8A 02 -- Authorisation Response Code
            95 05 -- Terminal Verification Results (TVR)
            9F 37 04 -- Unpredictable Number
      57 13 -- Track 2 Equivalent Data
            67 26 42 89 02 04 68 46 00 7D 24 12 20 10 21 01
            04 66 9F (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

*********************************
************ step 03 ************
* select application by AID     *
*********************************
03 select application by AID d27600002547410100 (number 3)

03 select AID command  length 15 data: 00a4040009d2760000254741010000
03 select AID response length 72 data: 6f448409d27600002547410100a53750086769726f636172648701019f38039f1d025f2d046465656ebf0c1a9f4d02190a9f6e07028000003030009f0a0800010501000000009000
------------------------------------
6F 44 -- File Control Information (FCI) Template
      84 09 -- Dedicated File (DF) Name
            D2 76 00 00 25 47 41 01 00 (BINARY)
      A5 37 -- File Control Information (FCI) Proprietary Template
            50 08 -- Application Label
                  67 69 72 6F 63 61 72 64 (=girocard)
            87 01 -- Application Priority Indicator
                  01 (BINARY)
            9F 38 03 -- Processing Options Data Object List (PDOL)
                     9F 1D 02 -- Terminal Risk Management Data
            5F 2D 04 -- Language Preference
                     64 65 65 6E (=deen)
            BF 0C 1A -- File Control Information (FCI) Issuer Discretionary Data
                     9F 4D 02 -- Log Entry
                              19 0A (BINARY)
                     9F 6E 07 -- Visa Low-Value Payment (VLP) Issuer Authorisation Code
                              02 80 00 00 30 30 00 (BINARY)
                     9F 0A 08 -- [UNKNOWN TAG]
                              00 01 05 01 00 00 00 00 (BINARY)
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

found tag 0x9F38 (PDOL) in the selectAid with this length: 3 data: 9f1d02

The card is requesting 1 tag in the PDOL

Tag  Tag Name                        Length Value
-----------------------------------------------------
9f1d Terminal Risk Management Data       2  00 00 
-----------------------------------------------------

*********************************
************ step 05 ************
* get the processing options    *
*********************************
05 get the processing options  command length: 10 data: 80a80000048302000000
05 get the processing options response length: 14 data: 770a820218009404080205009000
------------------------------------
77 0A -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            18 00 (BINARY)
      94 04 -- Application File Locator (AFL)
            08 02 05 00 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 4 data: 08020500

*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 1 entry to read
for SFI 8 we read 4 records
readRecord SFI 8 file 5 command length: 5 data: 00b2020c00
readRecord response length: 9 data: 70059f080200029000
------------------------------------
70 05 -- Record Template (EMV Proprietary)
      9F 08 02 -- Application Version Number - card
               00 02 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 8 file 5 command length: 5 data: 00b2030c00
readRecord response length: 65 data: 703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136726428902046846007d24122010210104669f9000
------------------------------------
70 3D -- Record Template (EMV Proprietary)
      8C 1B -- Card Risk Management Data Object List 1 (CDOL1)
            9F 02 06 -- Amount, Authorised (Numeric)
            9F 03 06 -- Amount, Other (Numeric)
            9F 1A 02 -- Terminal Country Code
            95 05 -- Terminal Verification Results (TVR)
            5F 2A 02 -- Transaction Currency Code
            9A 03 -- Transaction Date
            9C 01 -- Transaction Type
            9F 37 04 -- Unpredictable Number
            9F 35 01 -- Terminal Type
            9F 34 03 -- Cardholder Verification (CVM) Results
      8D 09 -- Card Risk Management Data Object List 2 (CDOL2)
            91 10 -- Issuer Authentication Data
            8A 02 -- Authorisation Response Code
            95 05 -- Terminal Verification Results (TVR)
            9F 37 04 -- Unpredictable Number
      57 13 -- Track 2 Equivalent Data
            67 26 42 89 02 04 68 46 00 7D 24 12 20 10 21 01
            04 66 9F (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 8 file 5 command length: 5 data: 00b2040c00
readRecord response length: 16 data: 700c8e0a000000000000000002039000
------------------------------------
70 0C -- Record Template (EMV Proprietary)
      8E 0A -- Cardholder Verification Method (CVM) List
            00 00 00 00 00 00 00 00 02 03 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 8 file 5 command length: 5 data: 00b2050c00
readRecord response length: 60 data: 70385f24032412315a0a6726428902046846007f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c9000
------------------------------------
70 38 -- Record Template (EMV Proprietary)
      5F 24 03 -- Application Expiration Date
               24 12 31 (NUMERIC)
      5A 0A -- Application Primary Account Number (PAN)
            67 26 42 89 02 04 68 46 00 7F (NUMERIC)
      5F 34 01 -- Application Primary Account Number (PAN) Sequence Number
               00 (NUMERIC)
      5F 28 02 -- Issuer Country Code
               02 80 (NUMERIC)
      9F 07 02 -- Application Usage Control
               FF C0 (BINARY)
      9F 0D 05 -- Issuer Action Code - Default
               FC 40 A4 80 0C (BINARY)
      9F 0E 05 -- Issuer Action Code - Denial
               00 10 18 00 00 (BINARY)
      9F 0F 05 -- Issuer Action Code - Online
               FC 40 A4 98 0C (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

found tag 0x5a in the readRecordResponse length: 10 data: 6726428902046846007f
found tag 0x5f24 in the readRecordResponse length: 3 data: 241231

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID d27600002547410100
PAN: 6726428902046846007
Expiration date (YYMMDD): 241231

*********************************
************ step 03 ************
* select application by AID     *
*********************************
03 select application by AID a0000000043060 (number 4)

03 select AID command  length 13 data: 00a4040007a000000004306000
03 select AID response length 75 data: 6f478407a0000000043060a53c50074d61657374726f8701019f38099f35019f40019f1d025f2d046465656ebf0c1a9f4d02190a9f6e07028000003030009f0a0800010501000000009000
------------------------------------
6F 47 -- File Control Information (FCI) Template
      84 07 -- Dedicated File (DF) Name
            A0 00 00 00 04 30 60 (BINARY)
      A5 3C -- File Control Information (FCI) Proprietary Template
            50 07 -- Application Label
                  4D 61 65 73 74 72 6F (=Maestro)
            87 01 -- Application Priority Indicator
                  01 (BINARY)
            9F 38 09 -- Processing Options Data Object List (PDOL)
                     9F 35 01 -- Terminal Type
                     9F 40 01 -- Additional Terminal Capabilities
                     9F 1D 02 -- Terminal Risk Management Data
            5F 2D 04 -- Language Preference
                     64 65 65 6E (=deen)
            BF 0C 1A -- File Control Information (FCI) Issuer Discretionary Data
                     9F 4D 02 -- Log Entry
                              19 0A (BINARY)
                     9F 6E 07 -- Visa Low-Value Payment (VLP) Issuer Authorisation Code
                              02 80 00 00 30 30 00 (BINARY)
                     9F 0A 08 -- [UNKNOWN TAG]
                              00 01 05 01 00 00 00 00 (BINARY)
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

found tag 0x9F38 (PDOL) in the selectAid with this length: 9 data: 9f35019f40019f1d02

The card is requesting 3 tags in the PDOL

Tag  Tag Name                        Length Value
-----------------------------------------------------
9f35 Terminal Type                       1  22 
9f40 Additional Terminal Capabilities    1  00 
9f1d Terminal Risk Management Data       2  00 00 
-----------------------------------------------------

*********************************
************ step 05 ************
* get the processing options    *
*********************************
05 get the processing options  command length: 12 data: 80a800000683042200000000
05 get the processing options response length: 34 data: 771e8202198094183001020020010100400202000805050108010101080303019000
------------------------------------
77 1E -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 18 -- Application File Locator (AFL)
            30 01 02 00 20 01 01 00 40 02 02 00 08 05 05 01
            08 01 01 01 08 03 03 01 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 24 data: 300102002001010040020200080505010801010108030301

*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 6 entries to read
for SFI 30 we read 2 records
readRecord SFI 30 file 2 command length: 5 data: 00b2013400
readRecord response length: 51 data: 702f8f01059f3201039224b0568adf146b092492be46e5d57d920b026be8e734264cf34710483a0af52d46790f01ab00009000
------------------------------------
70 2F -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 30 file 2 command length: 5 data: 00b2023400
readRecord response length: 184 data: 7081b39081b03ee20a6b5b1173d8e442d86f3b09b0b41a5abd9b55b4e29e18be244b5b073d5335f7f7159fa9c9b07f543ff7848619455167f8e3add3aa094e04f1c2a6286ca90dd0fd4182f9199fc899a6757e3472d211536b8fc55ed0d7cc527f8d17c5f1fac234609aeb5f03db6cc9ec49a1d1ac1979db0d080a4c5d57092ab288e702f27fa6547439251f6853c4a6444b42333eab12c2132990ba11feb0640b54901073b777501c5aeed79136b5786b28a68403f59000
------------------------------------
70 81 B3 -- Record Template (EMV Proprietary)
         90 81 B0 -- Issuer Public Key Certificate
                  3E E2 0A 6B 5B 11 73 D8 E4 42 D8 6F 3B 09 B0 B4
                  1A 5A BD 9B 55 B4 E2 9E 18 BE 24 4B 5B 07 3D 53
                  35 F7 F7 15 9F A9 C9 B0 7F 54 3F F7 84 86 19 45
                  51 67 F8 E3 AD D3 AA 09 4E 04 F1 C2 A6 28 6C A9
                  0D D0 FD 41 82 F9 19 9F C8 99 A6 75 7E 34 72 D2
                  11 53 6B 8F C5 5E D0 D7 CC 52 7F 8D 17 C5 F1 FA
                  C2 34 60 9A EB 5F 03 DB 6C C9 EC 49 A1 D1 AC 19
                  79 DB 0D 08 0A 4C 5D 57 09 2A B2 88 E7 02 F2 7F
                  A6 54 74 39 25 1F 68 53 C4 A6 44 4B 42 33 3E AB
                  12 C2 13 29 90 BA 11 FE B0 64 0B 54 90 10 73 B7
                  77 50 1C 5A EE D7 91 36 B5 78 6B 28 A6 84 03 F5 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 20 we read 1 record
readRecord SFI 20 file 1 command length: 5 data: 00b2012400
readRecord response length: 55 data: 70339f47030100019f480a4947098e18cbf3fba1fb00000000000000000000000000000000000000000000000000000000000000009000
------------------------------------
70 33 -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 40 we read 1 record
readRecord SFI 40 file 2 command length: 5 data: 00b2024400
readRecord response length: 185 data: 7081b49f4681b09945cef64c34462e4dfb7f6d63a8991fbde4d9ec39dfc1c0c3e2d576090aa85ab52b68237a151d596c4d59bc6e84b0b31c204dae7de8ec6848dff965499d075b8b407f3a4fae8109a89b2c575de26e49b0306b1bdfb0406e4a466f03616c72d8f1d81be76f59b69bc3b62e176abdfae34d56ae84ce86a41c9f399ddabb15dd902f50471ae0a8defa70f0401467c8a2b556e1e182c0f837f30930692d6237dbd3c37fc8c08ef4a60c6770d2fb8483a3ae9000
------------------------------------
70 81 B4 -- Record Template (EMV Proprietary)
         9F 46 81 B0 -- ICC Public Key Certificate
                     99 45 CE F6 4C 34 46 2E 4D FB 7F 6D 63 A8 99 1F
                     BD E4 D9 EC 39 DF C1 C0 C3 E2 D5 76 09 0A A8 5A
                     B5 2B 68 23 7A 15 1D 59 6C 4D 59 BC 6E 84 B0 B3
                     1C 20 4D AE 7D E8 EC 68 48 DF F9 65 49 9D 07 5B
                     8B 40 7F 3A 4F AE 81 09 A8 9B 2C 57 5D E2 6E 49
                     B0 30 6B 1B DF B0 40 6E 4A 46 6F 03 61 6C 72 D8
                     F1 D8 1B E7 6F 59 B6 9B C3 B6 2E 17 6A BD FA E3
                     4D 56 AE 84 CE 86 A4 1C 9F 39 9D DA BB 15 DD 90
                     2F 50 47 1A E0 A8 DE FA 70 F0 40 14 67 C8 A2 B5
                     56 E1 E1 82 C0 F8 37 F3 09 30 69 2D 62 37 DB D3
                     C3 7F C8 C0 8E F4 A6 0C 67 70 D2 FB 84 83 A3 AE (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 5 command length: 5 data: 00b2050c00
readRecord response length: 60 data: 70385f24032412315a0a6726428902046846007f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c9000
------------------------------------
70 38 -- Record Template (EMV Proprietary)
      5F 24 03 -- Application Expiration Date
               24 12 31 (NUMERIC)
      5A 0A -- Application Primary Account Number (PAN)
            67 26 42 89 02 04 68 46 00 7F (NUMERIC)
      5F 34 01 -- Application Primary Account Number (PAN) Sequence Number
               00 (NUMERIC)
      5F 28 02 -- Issuer Country Code
               02 80 (NUMERIC)
      9F 07 02 -- Application Usage Control
               FF C0 (BINARY)
      9F 0D 05 -- Issuer Action Code - Default
               FC 40 A4 80 0C (BINARY)
      9F 0E 05 -- Issuer Action Code - Denial
               00 10 18 00 00 (BINARY)
      9F 0F 05 -- Issuer Action Code - Online
               FC 40 A4 98 0C (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

found tag 0x5a in the readRecordResponse length: 10 data: 6726428902046846007f
found tag 0x5f24 in the readRecordResponse length: 3 data: 241231

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID a0000000043060
PAN: 6726428902046846007
Expiration date (YYMMDD): 241231

for SFI 8 we read 1 record
readRecord SFI 8 file 1 command length: 5 data: 00b2010c00
readRecord response length: 32 data: 701c8e0c000000000000000002031f039f080200029f420209789f4a01829000
------------------------------------
70 1C -- Record Template (EMV Proprietary)
      8E 0C -- Cardholder Verification Method (CVM) List
            00 00 00 00 00 00 00 00 02 03 1F 03 (BINARY)
      9F 08 02 -- Application Version Number - card
               00 02 (BINARY)
      9F 42 02 -- Application Currency Code
               09 78 (NUMERIC)
      9F 4A 01 -- Static Data Authentication Tag List
               82 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 3 command length: 5 data: 00b2030c00
readRecord response length: 65 data: 703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136726428902046846007d24122010210104669f9000
------------------------------------
70 3D -- Record Template (EMV Proprietary)
      8C 1B -- Card Risk Management Data Object List 1 (CDOL1)
            9F 02 06 -- Amount, Authorised (Numeric)
            9F 03 06 -- Amount, Other (Numeric)
            9F 1A 02 -- Terminal Country Code
            95 05 -- Terminal Verification Results (TVR)
            5F 2A 02 -- Transaction Currency Code
            9A 03 -- Transaction Date
            9C 01 -- Transaction Type
            9F 37 04 -- Unpredictable Number
            9F 35 01 -- Terminal Type
            9F 34 03 -- Cardholder Verification (CVM) Results
      8D 09 -- Card Risk Management Data Object List 2 (CDOL2)
            91 10 -- Issuer Authentication Data
            8A 02 -- Authorisation Response Code
            95 05 -- Terminal Verification Results (TVR)
            9F 37 04 -- Unpredictable Number
      57 13 -- Track 2 Equivalent Data
            67 26 42 89 02 04 68 46 00 7D 24 12 20 10 21 01
            04 66 9F (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

*********************************
************ step 99 ************
* our journey ends              *
*********************************
```