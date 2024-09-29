# Girocard comdirect M

Found 3 applications on the tag:
```none
application Id (AID): a00000005945430100   : Zentraler Kreditausschuss (ZKA) - Girocard Electronic Cash
application Id (AID): a0000003591010028001 : Euro Alliance of Payment Schemes s.c.r.l. EAPS - Girocard EAPS (abandoned sometime after 2013)
application Id (AID): d27600002547410100   : ZKA - Girocard ATM
```

PAN/Exp Date:
```none
PAN: 6802422206553002003
Expiration date (YYMMDD): 251231


```

```none
NFC tag discovered
TagId: 0808f597
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
01 select PPSE response length 107 data: 6f67840e325041592e5359532e4444463031a555bf0c5261194f09a000000059454301008701019f0a080001050100000000611a4f0aa00000035910100280018701019f0a08000105010000000061194f09d276000025474101008701019f0a0800010501000000009000
------------------------------------
6F 67 -- File Control Information (FCI) Template
      84 0E -- Dedicated File (DF) Name
            32 50 41 59 2E 53 59 53 2E 44 44 46 30 31 (BINARY)
      A5 55 -- File Control Information (FCI) Proprietary Template
            BF 0C 52 -- File Control Information (FCI) Issuer Discretionary Data
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
90 00 -- Command successfully executed (OK)
------------------------------------


*********************************
************ step 02 ************
* search applications on card   *
*********************************
02 analyze select PPSE response and search for tag 0x4F (applications on card)
Found tag 0x4F 3 times:
application Id (AID): a00000005945430100
application Id (AID): a0000003591010028001
application Id (AID): d27600002547410100


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
05 get the processing options response length: 34 data: 771e8202198094181801020020010100400303000805050108070701080303019000
------------------------------------
77 1E -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 18 -- Application File Locator (AFL)
            18 01 02 00 20 01 01 00 40 03 03 00 08 05 05 01
            08 07 07 01 08 03 03 01 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 24 data: 180102002001010040030300080505010807070108030301


*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 6 entries to read
for SFI 18 we read 2 records
readRecord SFI 18 file 2 command length: 5 data: 00b2011c00
readRecord response length: 51 data: 702f8f01069f320103921c1396d56eda444cee78d8cb716ce376532834590dbcb4d63558309d89000000000000000000009000
------------------------------------
70 2F -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 18 file 2 command length: 5 data: 00b2021c00
readRecord response length: 256 data: 7081fb9081f8057298a1b711d8a052ff2b51f3a84a48f505c15a01a25323776d502e222d47dc95e47240a8446e94e9cbeaf97361960dacd776a1d86c38fdc42e9662f15db306896a73670e43efeffa8fb226ec122ae6e4312e4c71342045c6c98710783425765e23ba2ee65b50680f7d64d9d757aee56028535ec538ae9260c08e1fac0071f60dc307351fce480459f66e360aa382fcbfcaa6f79301e4c05ad25ff1b407b647f07412b17b6f3721bfa6416d1b23471c4e2cb459fb7dd20b527a2abc77b5569fe37cdf08a1cc6a4bd490750d9efbc94c14214b446efdefee52ed991f5dbfb9a29652c492c4ccaed85cc469c2cd0a1df9afb10abb9940baaa9000
------------------------------------
70 81 FB -- Record Template (EMV Proprietary)
         90 81 F8 -- Issuer Public Key Certificate
                  05 72 98 A1 B7 11 D8 A0 52 FF 2B 51 F3 A8 4A 48
                  F5 05 C1 5A 01 A2 53 23 77 6D 50 2E 22 2D 47 DC
                  95 E4 72 40 A8 44 6E 94 E9 CB EA F9 73 61 96 0D
                  AC D7 76 A1 D8 6C 38 FD C4 2E 96 62 F1 5D B3 06
                  89 6A 73 67 0E 43 EF EF FA 8F B2 26 EC 12 2A E6
                  E4 31 2E 4C 71 34 20 45 C6 C9 87 10 78 34 25 76
                  5E 23 BA 2E E6 5B 50 68 0F 7D 64 D9 D7 57 AE E5
                  60 28 53 5E C5 38 AE 92 60 C0 8E 1F AC 00 71 F6
                  0D C3 07 35 1F CE 48 04 59 F6 6E 36 0A A3 82 FC
                  BF CA A6 F7 93 01 E4 C0 5A D2 5F F1 B4 07 B6 47
                  F0 74 12 B1 7B 6F 37 21 BF A6 41 6D 1B 23 47 1C
                  4E 2C B4 59 FB 7D D2 0B 52 7A 2A BC 77 B5 56 9F
                  E3 7C DF 08 A1 CC 6A 4B D4 90 75 0D 9E FB C9 4C
                  14 21 4B 44 6E FD EF EE 52 ED 99 1F 5D BF B9 A2
                  96 52 C4 92 C4 CC AE D8 5C C4 69 C2 CD 0A 1D F9
                  AF B1 0A BB 99 40 BA AA (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 20 we read 1 record
readRecord SFI 20 file 1 command length: 5 data: 00b2012400
readRecord response length: 55 data: 70339f47030100010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009000
------------------------------------
70 33 -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 40 we read 1 record
readRecord SFI 40 file 3 command length: 5 data: 00b2034400
readRecord response length: 256 data: 7081fb9f4681f03e61d2663d872bf24cf5b8ffc516738cab29be0f85cea3f05bbf6ebd792fae6830596276e1ab8baf4a9fe0bfa587589232da410bdc4fe2fc86f6b06c441c2b416cf9397243145e4e7daafdfd551d498357e6b5d2033e386f5c54aed458ea190dccbbabd23fb3661f5e979742f3ed4c4aca4eac85a4c24c7b81b72739b062acf9303bf0d50ac4e0210e113cd7959f7ff64cb970ad325485afd40b7d1af978fb066010e27598e702957491420fa900cb7fb0b5528da224e51fdf0d0e0063d65e90649ceb5f89d5ad449f577d78dc627b361f5e64d9f2029c99d85d0a931862446afe8a0ed2ee914fc951a8b740f59e58f6000000000000009000
------------------------------------
70 81 FB -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 5 command length: 5 data: 00b2050c00
readRecord response length: 60 data: 70385f24032512315a0a6802422206553002003f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c9000
------------------------------------
70 38 -- Record Template (EMV Proprietary)
      5F 24 03 -- Application Expiration Date
               25 12 31 (NUMERIC)
      5A 0A -- Application Primary Account Number (PAN)
            68 02 42 22 06 55 30 02 00 3F (NUMERIC)
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

found tag 0x5a in the readRecordResponse length: 10 data: 6802422206553002003f
found tag 0x5f24 in the readRecordResponse length: 3 data: 251231


*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID a00000005945430100
PAN: 6802422206553002003
Expiration date (YYMMDD): 251231

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
readRecord response length: 65 data: 703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136802422206553002003d25122219206507723f9000
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
            68 02 42 22 06 55 30 02 00 3D 25 12 22 19 20 65
            07 72 3F (BINARY)
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
05 get the processing options response length: 34 data: 771e8202198094181801020020010100400303000805050108070701080303019000
------------------------------------
77 1E -- Response Message Template Format 2
      82 02 -- Application Interchange Profile
            19 80 (BINARY)
      94 18 -- Application File Locator (AFL)
            18 01 02 00 20 01 01 00 40 03 03 00 08 05 05 01
            08 07 07 01 08 03 03 01 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 24 data: 180102002001010040030300080505010807070108030301


*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 6 entries to read
for SFI 18 we read 2 records
readRecord SFI 18 file 2 command length: 5 data: 00b2011c00
readRecord response length: 51 data: 702f8f01069f320103921c1396d56eda444cee78d8cb716ce376532834590dbcb4d63558309d89000000000000000000009000
------------------------------------
70 2F -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord SFI 18 file 2 command length: 5 data: 00b2021c00
readRecord response length: 256 data: 7081fb9081f8057298a1b711d8a052ff2b51f3a84a48f505c15a01a25323776d502e222d47dc95e47240a8446e94e9cbeaf97361960dacd776a1d86c38fdc42e9662f15db306896a73670e43efeffa8fb226ec122ae6e4312e4c71342045c6c98710783425765e23ba2ee65b50680f7d64d9d757aee56028535ec538ae9260c08e1fac0071f60dc307351fce480459f66e360aa382fcbfcaa6f79301e4c05ad25ff1b407b647f07412b17b6f3721bfa6416d1b23471c4e2cb459fb7dd20b527a2abc77b5569fe37cdf08a1cc6a4bd490750d9efbc94c14214b446efdefee52ed991f5dbfb9a29652c492c4ccaed85cc469c2cd0a1df9afb10abb9940baaa9000
------------------------------------
70 81 FB -- Record Template (EMV Proprietary)
         90 81 F8 -- Issuer Public Key Certificate
                  05 72 98 A1 B7 11 D8 A0 52 FF 2B 51 F3 A8 4A 48
                  F5 05 C1 5A 01 A2 53 23 77 6D 50 2E 22 2D 47 DC
                  95 E4 72 40 A8 44 6E 94 E9 CB EA F9 73 61 96 0D
                  AC D7 76 A1 D8 6C 38 FD C4 2E 96 62 F1 5D B3 06
                  89 6A 73 67 0E 43 EF EF FA 8F B2 26 EC 12 2A E6
                  E4 31 2E 4C 71 34 20 45 C6 C9 87 10 78 34 25 76
                  5E 23 BA 2E E6 5B 50 68 0F 7D 64 D9 D7 57 AE E5
                  60 28 53 5E C5 38 AE 92 60 C0 8E 1F AC 00 71 F6
                  0D C3 07 35 1F CE 48 04 59 F6 6E 36 0A A3 82 FC
                  BF CA A6 F7 93 01 E4 C0 5A D2 5F F1 B4 07 B6 47
                  F0 74 12 B1 7B 6F 37 21 BF A6 41 6D 1B 23 47 1C
                  4E 2C B4 59 FB 7D D2 0B 52 7A 2A BC 77 B5 56 9F
                  E3 7C DF 08 A1 CC 6A 4B D4 90 75 0D 9E FB C9 4C
                  14 21 4B 44 6E FD EF EE 52 ED 99 1F 5D BF B9 A2
                  96 52 C4 92 C4 CC AE D8 5C C4 69 C2 CD 0A 1D F9
                  AF B1 0A BB 99 40 BA AA (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 20 we read 1 record
readRecord SFI 20 file 1 command length: 5 data: 00b2012400
readRecord response length: 55 data: 70339f47030100010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009000
------------------------------------
70 33 -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 40 we read 1 record
readRecord SFI 40 file 3 command length: 5 data: 00b2034400
readRecord response length: 256 data: 7081fb9f4681f03e61d2663d872bf24cf5b8ffc516738cab29be0f85cea3f05bbf6ebd792fae6830596276e1ab8baf4a9fe0bfa587589232da410bdc4fe2fc86f6b06c441c2b416cf9397243145e4e7daafdfd551d498357e6b5d2033e386f5c54aed458ea190dccbbabd23fb3661f5e979742f3ed4c4aca4eac85a4c24c7b81b72739b062acf9303bf0d50ac4e0210e113cd7959f7ff64cb970ad325485afd40b7d1af978fb066010e27598e702957491420fa900cb7fb0b5528da224e51fdf0d0e0063d65e90649ceb5f89d5ad449f577d78dc627b361f5e64d9f2029c99d85d0a931862446afe8a0ed2ee914fc951a8b740f59e58f6000000000000009000
------------------------------------
70 81 FB -- Record Template (EMV Proprietary)
90 00 -- Command successfully executed (OK)
------------------------------------

for SFI 8 we read 1 record
readRecord SFI 8 file 5 command length: 5 data: 00b2050c00
readRecord response length: 60 data: 70385f24032512315a0a6802422206553002003f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c9000
------------------------------------
70 38 -- Record Template (EMV Proprietary)
      5F 24 03 -- Application Expiration Date
               25 12 31 (NUMERIC)
      5A 0A -- Application Primary Account Number (PAN)
            68 02 42 22 06 55 30 02 00 3F (NUMERIC)
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

found tag 0x5a in the readRecordResponse length: 10 data: 6802422206553002003f
found tag 0x5f24 in the readRecordResponse length: 3 data: 251231


*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID a0000003591010028001
PAN: 6802422206553002003
Expiration date (YYMMDD): 251231

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
readRecord response length: 65 data: 703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136802422206553002003d25122219206507723f9000
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
            68 02 42 22 06 55 30 02 00 3D 25 12 22 19 20 65
            07 72 3F (BINARY)
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
readRecord response length: 65 data: 703d8c1b9f02069f03069f1a0295055f2a029a039c019f37049f35019f34038d0991108a0295059f370457136802422206553002003d25122219206507723f9000
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
            68 02 42 22 06 55 30 02 00 3D 25 12 22 19 20 65
            07 72 3F (BINARY)
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
readRecord response length: 60 data: 70385f24032512315a0a6802422206553002003f5f3401005f280202809f0702ffc09f0d05fc40a4800c9f0e0500101800009f0f05fc40a4980c9000
------------------------------------
70 38 -- Record Template (EMV Proprietary)
      5F 24 03 -- Application Expiration Date
               25 12 31 (NUMERIC)
      5A 0A -- Application Primary Account Number (PAN)
            68 02 42 22 06 55 30 02 00 3F (NUMERIC)
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

found tag 0x5a in the readRecordResponse length: 10 data: 6802422206553002003f
found tag 0x5f24 in the readRecordResponse length: 3 data: 251231


*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID d27600002547410100
PAN: 6802422206553002003
Expiration date (YYMMDD): 251231


*********************************
************ step 99 ************
* our journey ends              *
*********************************

```