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

## Link my NFC Credit Card Reader tutorial

**Talk to your Credit Card**: https://medium.com/@androidcrypto/talk-to-your-credit-card-part-1-select-ppse-paypass-payment-system-environment-674bbc9745eb

The final app is available on GitHub for self compiling (recommended) or you can download the final 
app (subfolder debug-release) of part 7 here: https://github.com/AndroidCrypto/TalkToYourCreditCardPart7

It is recommended to use this app for your first steps as this is an EMV/Credit Card Reader application 
that is acting like a real POS terminal. Of course - if you should have an own POS terminal feel free to 
use the app. But please be aware: the final app won't allow to make payments as a lot of steps are missing. 
It is unpredictable how a business terminal act's on my emulated Credit Card.

## These are the steps to create a HCE Credit Card emulator

### 1 Setup an activity with just a MainActivity class

As the HCE Emulation is done in a background service we do not need much UI stuff. Create a service class
(e.g. "HceCcEmulationService.java") that **extends HostApduService**. See the HCE Beginner tutorial 
how on to do so: https://medium.com/@androidcrypto/how-to-use-host-based-card-emulation-hce-in-android-a-beginner-tutorial-java-32974dd89529

### 2 Modify your AndroidManifest.xml file

As we need a binding of the service class to our app we need to append the HCE related service data:

```plaintext
<service                                                                                         
    android:name=".HceCcEmulationService"                                                        
    android:exported="true"                                                                      
    android:permission="android.permission.BIND_NFC_SERVICE">                                    
    <intent-filter>                                                                              
        <action android:name="android.nfc.cardemulation.action.HOST_APDU_SERVICE" />             
        <!-- category required!!! this was not included in official android HCE documentation -->
        <category android:name="android.intent.category.DEFAULT" />                              
    </intent-filter>                                                                             
    <meta-data                                                                                   
        android:name="android.nfc.cardemulation.host_apdu_service"                               
        android:resource="@xml/apduservice" />                                                   
</service>                                                                                       
```

The are two file names defined: the *android:name=".HceCcEmulationService"* is pointing to the service 
class you created in 1) and the resource for meta-data: *android:resource="@xml/apduservice" /> * - 
this is the file "apduservice.xml", located in the "res" part of your project in the subfolder "xml".

This is one of the most important settings you are going to do. The content may look like mine:

```plaintext
<?xml version="1.0" encoding="utf-8"?>
<host-apdu-service xmlns:android="http://schemas.android.com/apk/res/android"
    android:description="@string/servicedesc"
    android:apduServiceBanner="@drawable/hce_260_96"
    android:requireDeviceUnlock="false">
    <aid-group android:description="@string/aiddescription"
        android:category="other">
        <!-- select PPSE 2PAY.SYS.DDF01 -->
        <aid-filter android:name="@string/hcePpse"/>
        <aid-filter android:name="@string/aidVisaCard"/>
        <aid-filter android:name="@string/aidMastercard"/>
    </aid-group>
</host-apdu-service>
```

There are 3 **aid-filter** defined in the file. The data behind the placeholders is in the res/values/strings.xml 
file:

```plaintext
<!-- select PPSE 2PAY.SYS.DDF01 -->
<string name="hcePpse">325041592E5359532E4444463031</string>
<!-- visa credit or debit card -->
<string name="aidVisaCard">A0000000031010</string>
<!-- mastercard credit or debit card -->
<string name="aidMastercard">A0000000041010</string>
```

You should not change the information given here but you can append new filters. The first filter 
("hcePpse") defines a "Select Payment Card" ("PPSE" command) call that is used by all POS terminals 
in the world when using contactless (NFC) cards. If you leave this command out or modify it your app 
will never called by terminal in default configuration. The emulated tag will respond some data to 
inform the terminal which applications are available on the 
(HCE) tag. 

The second ("aidVisaCard") and third ("aidMastercard") entries define a possible application of a Credit 
Card Issuer on the tag - that are the major Credit Card Companies like "Mastercard" or "Visa". At minimum one the 
two AIDs need to match the data that was responded by our HCE emulated tag on the "Select Payment Card" 
command. As the POS terminal receives the "PPSE respond" it selects which of (possible) multiple AIDs 
on the tag it should use for processing a payment. This application is called by a "Select Application 
by AID" command. If our Android application does not include this AID the call will never get forwarded 
from Android's OS to the app (or better to the service).

### 3 Modify your blank service class

Simply add three lines of code to your class. The "SELECT_OK_SW" defines a response that is "SUCCESS". 
The print on System's console prints out a byte array in hex encoding. After the print the method 
rezrns a "SUCCESS" to the reader.

```plaintext
...
public static byte[] SELECT_OK_SW = hexStringToByteArray("9000");
...
@Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle bundle) {
        System.out.println("processCommandApdu: " + bytesToHexNpe(commandApdu));
        return SELECT_OK_SW;
    }
```


# Stuff to delete

Get the PAN and Expiration Date using the Public Key data on the tag. See NfcEmvExampleG8 in CryptoStuffActivity




