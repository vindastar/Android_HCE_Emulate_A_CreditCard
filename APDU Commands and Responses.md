# APDU Commands and Responses for the sample Visa Credit Card

Use the Online TLV decoder: https://emvlab.org/tlvutils/

## Select the PPSE application

### Select PPSE Command
```plaintext
00a404000e325041592e5359532e444446303100
```

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
