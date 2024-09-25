# Android HCE Emulate a Credit Card (EMV card)

This app will explain how to run an emulation of a Credit Card ("EMV card", CC) using the **Host Based 
Card Emulation (HCE)** feature on Android devices. It is showing the basics on how to get called 
by a Credit Card Terminal (NFC Reader), process the incoming messages from the terminal and respond 
accordingly to the terminal.

The complete process is a kind of "Question & Answer" game - the terminal requests some data, the
(emulated) card responds the requested data, the terminal requests new data (based on the data send 
to the terminal in the first step) - the card is answering and so on. It may take up to 10 rounds 
before a transaction is finished and processed by the CC terminal ("POS Terminal").

As this repository is the accompanying resource for a tutorial on medium.com I wrote there is no real 
"final" code but a lot of pieces that will form the complete picture.

The tutorial is published here: soon...

This app is based on the tutorial "**How to use Host-based Card Emulation (HCE) in Android — a beginner 
tutorial (Java)**" that is published here: https://medium.com/@androidcrypto/how-to-use-host-based-card-emulation-hce-in-android-a-beginner-tutorial-java-32974dd89529.

The tutorial is accompanied by a GitHub repository as well: https://github.com/AndroidCrypto/Android_HCE_Beginner_App/. 

**I strongly recommend to read this tutorial before starting with this one as I won't repeat some parts 
here.**

## To avoid any disappointments
This app is just the base for a final app and will help you to understand how the basics of the Host 
Based Emulation for emulating Credit Cards is setup, but it will not be a ready to application that 
is being able to run a complete transaction on a POS Terminal !

A second note: for good reasons a Credit Card (EMV card) is not a simple NFC storage tag that you can read or 
write. Even when comparing a Credit Card with a MIFARE DESFire EV3 tag the EMV tag has some features 
that go far beyond the DESFire one. The most important reason is that sensitive data should never leave 
the tag - they will never be exposed by a read command. Some tasks when working with an EMV card include 
cryptographic calculations, they are completely done within the tag and only the result will be 
responded to the POS Terminal. Some operation e.g. are secured by a digital signature that is 
calculated by the EMV card on base of transaction parameters. A digital signature is done by using a 
**Private Key** and later verified by the "**Public Key**. As the Public Key is available (as the name 
says) in the public everyone can use them to verify the response (or better the signature based on 
the response). The Private Key is written to tag during card manufacturing by the Credit Card issuer 
and you will never get knowledge about this key. So your emulated CC will never being able to create 
a valid signature that can get responded to the terminal and has a valid signature. Of course, not 
every transaction may get processed with high "security" priority, but in general it is really hard 
to make a working code.

## Steps to create a HCE Credit Card emulator





