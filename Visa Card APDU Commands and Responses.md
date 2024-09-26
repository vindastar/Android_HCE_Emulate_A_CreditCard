# APDU Commands and Responses for a Visa Card (here: Lloyds Bank)

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
TagId: 020155a0
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
01 select PPSE response length 47 data: 6f2b840e325041592e5359532e4444463031a519bf0c1661144f07a00000000310109f0a0800010501000000009000
------------------------------------
6F 2B -- File Control Information (FCI) Template
      84 0E -- Dedicated File (DF) Name
            32 50 41 59 2E 53 59 53 2E 44 44 46 30 31 (BINARY)
      A5 19 -- File Control Information (FCI) Proprietary Template
            BF 0C 16 -- File Control Information (FCI) Issuer Discretionary Data
                     61 14 -- Application Template
                           4F 07 -- Application Identifier (AID) - card
                                 A0 00 00 00 03 10 10 (BINARY)
                           9F 0A 08 -- [UNKNOWN TAG]
                                    00 01 05 01 00 00 00 00 (BINARY)
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
* select application by AID     *a
*********************************
03 select application by AID a0000000031010 (number 1)

03 select AID command  length 13 data: 00a4040007a000000003101000
03 select AID response length 97 data: 6f5d8407a0000000031010a5525010564953412044454249542020202020208701029f38189f66049f02069f03069f1a0295055f2a029a039c019f37045f2d02656ebf0c1a9f5a0531082608269f0a080001050100000000bf6304df2001809000
------------------------------------
6F 5D -- File Control Information (FCI) Template
      84 07 -- Dedicated File (DF) Name
            A0 00 00 00 03 10 10 (BINARY)
      A5 52 -- File Control Information (FCI) Proprietary Template
            50 10 -- Application Label
                  56 49 53 41 20 44 45 42 49 54 20 20 20 20 20 20 (=VISA DEBIT      )
            87 01 -- Application Priority Indicator
                  02 (BINARY)
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
            5F 2D 02 -- Language Preference
                     65 6E (=en)
            BF 0C 1A -- File Control Information (FCI) Issuer Discretionary Data
                     9F 5A 05 -- Terminal transaction Type (Interac)
                              31 08 26 08 26 (BINARY)
                     9F 0A 08 -- [UNKNOWN TAG]
                              00 01 05 01 00 00 00 00 (BINARY)
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
05 get the processing options response length: 203 data: 7781c68202200094041003060057134921828094896752d25022013650000000000f9f100706040a03a020009f2608569f946d585cb0139f2701809f360203a49f4b818076f770b228b28c0b5a3f2b08c3f2ec3be6de27a2708de06180fdaaec5482e38cd62d5fe43cfcc8c3ff7462d7be99deb035a0abe5d0f82bdadbfb5d81377c42ae64f3339893df51e55f635251d1e1b0f4db959e60407684e03c92b5b0c06fcf5fa5bb366ec660f9d0b0dc3795eae505b5d2c982352c762f06c32647e0068f28469f6c0216009000
------------------------------------
77 81 C6 -- Response Message Template Format 2
         82 02 -- Application Interchange Profile
               20 00 (BINARY)
         94 04 -- Application File Locator (AFL)
               10 03 06 00 (BINARY)
         57 13 -- Track 2 Equivalent Data
               49 21 82 80 94 89 67 52 D2 50 22 01 36 50 00 00
               00 00 0F (BINARY)
         9F 10 07 -- Issuer Application Data
                  06 04 0A 03 A0 20 00 (BINARY)
         9F 26 08 -- Application Cryptogram
                  56 9F 94 6D 58 5C B0 13 (BINARY)
         9F 27 01 -- Cryptogram Information Data
                  80 (BINARY)
         9F 36 02 -- Application Transaction Counter (ATC)
                  03 A4 (BINARY)
         9F 4B 81 80 -- Signed Dynamic Application Data
                     76 F7 70 B2 28 B2 8C 0B 5A 3F 2B 08 C3 F2 EC 3B
                     E6 DE 27 A2 70 8D E0 61 80 FD AA EC 54 82 E3 8C
                     D6 2D 5F E4 3C FC C8 C3 FF 74 62 D7 BE 99 DE B0
                     35 A0 AB E5 D0 F8 2B DA DB FB 5D 81 37 7C 42 AE
                     64 F3 33 98 93 DF 51 E5 5F 63 52 51 D1 E1 B0 F4
                     DB 95 9E 60 40 76 84 E0 3C 92 B5 B0 C0 6F CF 5F
                     A5 BB 36 6E C6 60 F9 D0 B0 DC 37 95 EA E5 05 B5
                     D2 C9 82 35 2C 76 2F 06 C3 26 47 E0 06 8F 28 46 (BINARY)
         9F 6C 02 -- Mag Stripe Application Version Number (Card)
                  16 00 (BINARY)
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
found tag 0x57 in the gpoResponse length: 19 data: 4921828094896752d25022013650000000000f
found a PAN 4921828094896752 with Expiration date: 2502

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tag 0x57 (Track 2 Equivalent Data)
data for AID a0000000031010
PAN: 4921828094896752
Expiration date (YYMM): 2502

workflow c)
the response is of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x77 in the gpoResponse
found 'AFL' [tag 0x94] in the response of type 'Response Message Template Format 2' [tag 0x77]
found tag 0x94 in the gpoResponse length: 4 data: 10030600

*********************************
************ step 06 ************
* read files & search PAN       *
*********************************
06 read the files from card and search for PAN & Expiration date

The AFL contains 1 entry to read
for SFI 10 we read 4 records
readRecord  command length: 5 data: 00b2031400
readRecord response length: 256 data: 7081fb9081f830f056de40a950bec2a870c59d5462222605a8f31cdef39a0537c7c175115e352ad0c55470fce5737c4e769897623e01401da73e01644bb0b491aa1aadb27fc360c0089f7c2e52a64e96a3f8a59f76e49aa6dd9a6792644f2b0b513b1a1a93b98a3cc19f0bec45e9f8edd70f893a8cafb21b62f3b8f15983775f14fd16cb36a19120e5a5068ef9f05ffaea4e714d80f134a298d167a65a92f6f57963db94ab5d3967f6675b3609a0fceb5fbb70f07cfdeab1352c6a34d6be737aa74848f3f56932f08b51f54aa3040f1ace4a0ced38684df900a395c5cd88562eb2af8d35601210c20d6c3425dcd813b9b358d1356d52a8ebd8fb5a19915d9000
------------------------------------
70 81 FB -- Record Template (EMV Proprietary)
         90 81 F8 -- Issuer Public Key Certificate
                  30 F0 56 DE 40 A9 50 BE C2 A8 70 C5 9D 54 62 22
                  26 05 A8 F3 1C DE F3 9A 05 37 C7 C1 75 11 5E 35
                  2A D0 C5 54 70 FC E5 73 7C 4E 76 98 97 62 3E 01
                  40 1D A7 3E 01 64 4B B0 B4 91 AA 1A AD B2 7F C3
                  60 C0 08 9F 7C 2E 52 A6 4E 96 A3 F8 A5 9F 76 E4
                  9A A6 DD 9A 67 92 64 4F 2B 0B 51 3B 1A 1A 93 B9
                  8A 3C C1 9F 0B EC 45 E9 F8 ED D7 0F 89 3A 8C AF
                  B2 1B 62 F3 B8 F1 59 83 77 5F 14 FD 16 CB 36 A1
                  91 20 E5 A5 06 8E F9 F0 5F FA EA 4E 71 4D 80 F1
                  34 A2 98 D1 67 A6 5A 92 F6 F5 79 63 DB 94 AB 5D
                  39 67 F6 67 5B 36 09 A0 FC EB 5F BB 70 F0 7C FD
                  EA B1 35 2C 6A 34 D6 BE 73 7A A7 48 48 F3 F5 69
                  32 F0 8B 51 F5 4A A3 04 0F 1A CE 4A 0C ED 38 68
                  4D F9 00 A3 95 C5 CD 88 56 2E B2 AF 8D 35 60 12
                  10 C2 0D 6C 34 25 DC D8 13 B9 B3 58 D1 35 6D 52
                  A8 EB D8 FB 5A 19 91 5D (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord  command length: 5 data: 00b2041400
readRecord response length: 11 data: 70078f01099f3201039000
------------------------------------
70 07 -- Record Template (EMV Proprietary)
      8F 01 -- Certification Authority Public Key Index - card
            09 (BINARY)
      9F 32 01 -- Issuer Public Key Exponent
               03 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord  command length: 5 data: 00b2051400
readRecord response length: 185 data: 7081b49f4681b047461ffca14b5dfdc209569c8a14f17644251aa3f4abea251262134b920982f0250741f96fccb40800293054c0d89824ba7ac44ee7bab06fa157fccf7e52d3c64b4d8acd41b9774b801519ed6fec827ec2ec29f8991167c453776559a4a06fd98c4b9bd1548a65af2f56002a836bdf9a040a9253e653584c92833c3d1aa8e08c4de9cda1026044f80f39a9326a57496598987a6b3e18a5f56a8bdede752870e8793776db9d325ccd9c7ca5db33c28f049000
------------------------------------
70 81 B4 -- Record Template (EMV Proprietary)
         9F 46 81 B0 -- ICC Public Key Certificate
                     47 46 1F FC A1 4B 5D FD C2 09 56 9C 8A 14 F1 76
                     44 25 1A A3 F4 AB EA 25 12 62 13 4B 92 09 82 F0
                     25 07 41 F9 6F CC B4 08 00 29 30 54 C0 D8 98 24
                     BA 7A C4 4E E7 BA B0 6F A1 57 FC CF 7E 52 D3 C6
                     4B 4D 8A CD 41 B9 77 4B 80 15 19 ED 6F EC 82 7E
                     C2 EC 29 F8 99 11 67 C4 53 77 65 59 A4 A0 6F D9
                     8C 4B 9B D1 54 8A 65 AF 2F 56 00 2A 83 6B DF 9A
                     04 0A 92 53 E6 53 58 4C 92 83 3C 3D 1A A8 E0 8C
                     4D E9 CD A1 02 60 44 F8 0F 39 A9 32 6A 57 49 65
                     98 98 7A 6B 3E 18 A5 F5 6A 8B DE DE 75 28 70 E8
                     79 37 76 DB 9D 32 5C CD 9C 7C A5 DB 33 C2 8F 04 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

readRecord  command length: 5 data: 00b2061400
readRecord response length: 50 data: 702e9f4701035a0849218280948967525f3401005f24032502285f280208269f6e04207000009f690701b4cba8d616009000
------------------------------------
70 2E -- Record Template (EMV Proprietary)
      9F 47 01 -- ICC Public Key Exponent
               03 (BINARY)
      5A 08 -- Application Primary Account Number (PAN)
            49 21 82 80 94 89 67 52 (NUMERIC)
      5F 34 01 -- Application Primary Account Number (PAN) Sequence Number
               00 (NUMERIC)
      5F 24 03 -- Application Expiration Date
               25 02 28 (NUMERIC)
      5F 28 02 -- Issuer Country Code
               08 26 (NUMERIC)
      9F 6E 04 -- Visa Low-Value Payment (VLP) Issuer Authorisation Code
               20 70 00 00 (BINARY)
      9F 69 07 -- UDOL
               01 B4 CB A8 D6 16 00 (BINARY)
90 00 -- Command successfully executed (OK)
------------------------------------

found tag 0x5a in the readRecordResponse length: 8 data: 4921828094896752
found tag 0x5f24 in the readRecordResponse length: 3 data: 250228

*********************************
************ step 07 ************
* print PAN & expire date       *
*********************************
07 get PAN and Expiration date from tags 0x5a and 0x5f24
data for AID a0000000031010
PAN: 4921828094896752
Expiration date (YYMMDD): 250228

*********************************
************ step 99 ************
* our journey ends              *
*********************************
```