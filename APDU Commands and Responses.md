# APDU Commands and Responses for the sample Visa Credit Card

Use the Online TLV decoder: https://emvlab.org/tlvutils/

## Select the PPSE application

### Select PPSE Command
```plaintext
00a404000e325041592e5359532e444446303100
```

The reader start its communication with the tag by asking for the content of the "root directory" called PPSE ("Paypass Payment System Environment").
"2PAY.SYS.DDF01"

### Select PPSE Response

[Link](https://emvlab.org/tlvutils/?data=6f2b840e325041592e5359532e4444463031a519bf0c1661144f07a00000000310109f0a080001050100000000)

```plaintext
6f2b840e325041592e5359532e4444463031a519bf0c1661144f07a00000000310109f0a080001050100000000

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
```

The response is a list of available Application Identifier (AIDs) on the card. Our sampe card has 
one appliction with AID A0000000031010.

## Select Application by AID a0000000031010

### Select  Command
```plaintext
00a4040007a000000003101000
```

The reader selects the first or best matching AID for payment, in our case the AID A0000000031010 that is a Visa Credit or Debit card

## Select Application by AID Response

[Link](https://emvlab.org/tlvutils/?data=6f5d8407a0000000031010a5525010564953412044454249542020202020208701029f38189f66049f02069f03069f1a0295055f2a029a039c019f37045f2d02656ebf0c1a9f5a0531082608269f0a080001050100000000bf6304df200180)

```plaintext 
6f5d8407a0000000031010a5525010564953412044454249542020202020208701029f38189f66049f02069f03069f1a0295055f2a029a039c019f37045f2d02656ebf0c1a9f5a0531082608269f0a080001050100000000bf6304df200180

6F File Control Information (FCI) Template
 	84 Dedicated File (DF) Name
 	 	A0000000031010
 	A5 File Control Information (FCI) Proprietary Template
 	 	50 Application Label
 	 	 	V I S A D E B I T
 	 	87 Application Priority Indicator
 	 	 	02
 	 	9F38 Processing Options Data Object List (PDOL)
 	 	 	9F66049F02069F03069F1A0295055F2A029A039C019F3704
 	 	5F2D Language Preference
 	 	 	e n
 	 	BF0C File Control Information (FCI) Issuer Discretionary Data
 	 	 	9F5A Unknown tag
 	 	 	 	3108260826
 	 	 	9F0A Unknown tag
 	 	 	 	0001050100000000
 	 	 	BF63 Unknown tag
 	 	 	 	DF20 Unknown tag
 	 	 	 	 	80
```

A Visa card usually responds with the File Control Information (FCI) Template that contains the 
Processing Options Data Object List (PDOL). The card is requesting these information's from the 
NFC Reader (POS-Terminal) to proceed.

```plaintext 
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
```

## Select the requested PDOL data to the card by GET PROCESSING OPTONS command

### GET PROCESSING OPTONS Command
```plaintext
80a8000023832127000000000000001000000000000000097800000000000978230301003839303100
```

The Reader is giving the request data.

## GET PROCESSING OPTONS Response

[Link](https://emvlab.org/tlvutils/?data=7781c68202200094041003060057134921828094896752d25022013650000000000f9f100706040a03a020009f2608569f946d585cb0139f2701809f360203a49f4b818076f770b228b28c0b5a3f2b08c3f2ec3be6de27a2708de06180fdaaec5482e38cd62d5fe43cfcc8c3ff7462d7be99deb035a0abe5d0f82bdadbfb5d81377c42ae64f3339893df51e55f635251d1e1b0f4db959e60407684e03c92b5b0c06fcf5fa5bb366ec660f9d0b0dc3795eae505b5d2c982352c762f06c32647e0068f28469f6c021600)

```plaintext 
7781c68202200094041003060057134921828094896752d25022013650000000000f9f100706040a03a020009f2608569f946d585cb0139f2701809f360203a49f4b818076f770b228b28c0b5a3f2b08c3f2ec3be6de27a2708de06180fdaaec5482e38cd62d5fe43cfcc8c3ff7462d7be99deb035a0abe5d0f82bdadbfb5d81377c42ae64f3339893df51e55f635251d1e1b0f4db959e60407684e03c92b5b0c06fcf5fa5bb366ec660f9d0b0dc3795eae505b5d2c982352c762f06c32647e0068f28469f6c021600

77 Response Message Template Format 2
 	82 Application Interchange Profile
 	 	2000
 	94 Application File Locator (AFL)
 	 	10030600
 	57 Track 2 Equivalent Data
 	 	4921828094896752D25022013650000000000F
 	9F10 Issuer Application Data
 	 	06040A03A02000
 	9F26 Application Cryptogram
 	 	569F946D585CB013
 	9F27 Cryptogram Information Data
 	 	80
 	9F36 Application Transaction Counter (ATC)
 	 	03A4
 	9F4B Signed Dynamic Application Data
 	 	76F770B228B28C0B5A3F2B08C3F2EC3BE6DE27A2708DE06180FDAAEC5482E38CD62D5FE43CFCC8C3FF7462D7BE99DEB035A0ABE5D0F82BDADBFB5D81377C42AE64F3339893DF51E55F635251D1E1B0F4DB959E60407684E03C92B5B0C06FCF5FA5BB366EC660F9D0B0DC3795EAE505B5D2C982352C762F06C32647E0068F2846
 	9F6C Unknown tag
 	 	1600
```

The card is returning additional data. Very important is the Application File Locator (AFL), as this 
is a simple directory listing and the NFC, 

Track 2 data contains the PAN 4921828094896752 ! https://dnschecker.org/credit-card-validator.php?ccn=4921828094896752

This is a valid cc as well: 4921828094891852 https://dnschecker.org/credit-card-validator.php?ccn=4921828094891852

https://www.bincodes.com/creditcard-checker/:

4921828094891852 is a valid Credit Card/Debit Card Number.

√   The card has a valid checksum.
√   The card has a correct length.
√   The card brand code (Visa) is valid.

Generated MasterCard CC no: 5552146499225052

5552146499225052 is a valid Credit Card/Debit Card Number.

√   The card has a valid checksum.
√   The card has a correct length.
√   The card brand code (MasterCard) is valid.

Generate CC Numbers: https://www.getcreditcardnumbers.com/credit-card-generator

```plaintext
Visa CC numbers generated
IssuingNetwork,CardNumber
Visa,4485238277145456
Visa,4929021625441734
Visa,4916550958249046
Visa,4916464920621870
Visa,4556495747527812
Visa,4929221669750467
Visa,4485534776764098
Visa,4532057943478585
Visa,4148387322045861
Visa,4112299006069004

MasterCard CC numbers generated
IssuingNetwork,CardNumber
MasterCard,5552146499225052
MasterCard,5525492939930131
MasterCard,5267882141201143
MasterCard,5284526048524672
MasterCard,5215290250073084
MasterCard,5263737678303247
MasterCard,5101518909142059
MasterCard,5330936118791784
MasterCard,5390290125850663
MasterCard,5572159753379206

```


## Read Files from AFL

10030600 = 4 files for SFI 10 (03, 04, 05, 06)

### Read File 03 Command
```plaintext
00b2031400
```

## Read File Response

[Link](https://emvlab.org/tlvutils/?data=7081fb9081f830f056de40a950bec2a870c59d5462222605a8f31cdef39a0537c7c175115e352ad0c55470fce5737c4e769897623e01401da73e01644bb0b491aa1aadb27fc360c0089f7c2e52a64e96a3f8a59f76e49aa6dd9a6792644f2b0b513b1a1a93b98a3cc19f0bec45e9f8edd70f893a8cafb21b62f3b8f15983775f14fd16cb36a19120e5a5068ef9f05ffaea4e714d80f134a298d167a65a92f6f57963db94ab5d3967f6675b3609a0fceb5fbb70f07cfdeab1352c6a34d6be737aa74848f3f56932f08b51f54aa3040f1ace4a0ced38684df900a395c5cd88562eb2af8d35601210c20d6c3425dcd813b9b358d1356d52a8ebd8fb5a19915d)

```plaintext 
7081fb9081f830f056de40a950bec2a870c59d5462222605a8f31cdef39a0537c7c175115e352ad0c55470fce5737c4e769897623e01401da73e01644bb0b491aa1aadb27fc360c0089f7c2e52a64e96a3f8a59f76e49aa6dd9a6792644f2b0b513b1a1a93b98a3cc19f0bec45e9f8edd70f893a8cafb21b62f3b8f15983775f14fd16cb36a19120e5a5068ef9f05ffaea4e714d80f134a298d167a65a92f6f57963db94ab5d3967f6675b3609a0fceb5fbb70f07cfdeab1352c6a34d6be737aa74848f3f56932f08b51f54aa3040f1ace4a0ced38684df900a395c5cd88562eb2af8d35601210c20d6c3425dcd813b9b358d1356d52a8ebd8fb5a19915d

70 EMV Proprietary Template
 	90 Issuer Public Key Certificate
 	 	30F056DE40A950BEC2A870C59D5462222605A8F31CDEF39A0537C7C175115E352AD0C55470FCE5737C4E769897623E01401DA73E01644BB0B491AA1AADB27FC360C0089F7C2E52A64E96A3F8A59F76E49AA6DD9A6792644F2B0B513B1A1A93B98A3CC19F0BEC45E9F8EDD70F893A8CAFB21B62F3B8F15983775F14FD16CB36A19120E5A5068EF9F05FFAEA4E714D80F134A298D167A65A92F6F57963DB94AB5D3967F6675B3609A0FCEB5FBB70F07CFDEAB1352C6A34D6BE737AA74848F3F56932F08B51F54AA3040F1ACE4A0CED38684DF900A395C5CD88562EB2AF8D35601210C20D6C3425DCD813B9B358D1356D52A8EBD8FB5A19915D
```


## Select Application by AID a0000000031010

### Select  Command
```plaintext

```

## Select Application by AID Response

Link

```plaintext 

```


## Select Application by AID a0000000031010

### Select  Command
```plaintext

```

## Select Application by AID Response

Link

```plaintext 

```


## Select Application by AID a0000000031010

### Select  Command
```plaintext

```

## Select Application by AID Response

Link

```plaintext 

```


## Select Application by AID a0000000031010

### Select  Command
```plaintext

```

## Select Application by AID Response

Link

```plaintext 

```


## Select Application by AID a0000000031010

### Select  Command
```plaintext

```

## Select Application by AID Response

Link

```plaintext 

```


