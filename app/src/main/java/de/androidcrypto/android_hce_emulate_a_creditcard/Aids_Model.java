package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.byteToHex;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.bytesToHexNpe;

import java.util.List;

/**
 * This class stores the data read by the app for one AID (application). This data will NOT be
 * exported because some other AIDs may be available on the EMV card.
 * All AIDs will be collected in Aids_Model that is exported.
 */

public class Aids_Model {
    private int numberOfAids;
    private byte[] selectPpseCommand;
    private byte[] selectPpseResponse;
    private List<Aid_Model> aidModel;

    public Aids_Model(int numberOfAids) {
        this.numberOfAids = numberOfAids;
    }

    public int getNumberOfAids() {
        return numberOfAids;
    }

    public void setNumberOfAids(int numberOfAids) {
        this.numberOfAids = numberOfAids;
    }

    public byte[] getSelectPpseCommand() {
        return selectPpseCommand;
    }

    public void setSelectPpseCommand(byte[] selectPpseCommand) {
        this.selectPpseCommand = selectPpseCommand;
    }

    public byte[] getSelectPpseResponse() {
        return selectPpseResponse;
    }

    public void setSelectPpseResponse(byte[] selectPpseResponse) {
        this.selectPpseResponse = selectPpseResponse;
    }

    public List<Aid_Model> getAidModel() {
        return aidModel;
    }

    public void setAidModel(List<Aid_Model> aidModel) {
        this.aidModel = aidModel;
    }

    public void dump() {
        System.out.println("=======================");
        System.out.println("=   AIDS_MODEL DUMP   =");
        System.out.println("=======================");
        System.out.println("numberOfAids: " + numberOfAids);
        System.out.println("SelectPpseCommand:  " + bytesToHexNpe(selectPpseCommand));
        System.out.println("SelectPpseResponse: " + bytesToHexNpe(selectPpseResponse));
        System.out.println("-----------------------");
        if (aidModel == null) {
            System.out.println("AID Model is NULL, aborted");
            System.out.println("=======================");
        } else {
            for (int i = 0; i < aidModel.size(); i++) {
                System.out.println("Record: " + i);
                Aid_Model am = aidModel.get(i);
                System.out.println("AID: " + bytesToHexNpe(am.getAid()));
                System.out.println("PAN: " + am.getPanFoundString());
                System.out.println("EXP: " + am.getExpFoundString());
                if (am.getCommand() == null) {
                    System.out.println("No Commands or Responses stored, aborted");
                    System.out.println("=======================");
                } else {
                    int nrOfCommands = am.getCommand().size();
                    System.out.println("Number of Commands: " + nrOfCommands);
                    List<String> comType = am.getCommandType();
                    List<byte[]> com = am.getCommand();
                    List<byte[]> res = am.getRespond();
                    List<Byte> offline = am.getFileOffline();
                    for (int j = 0; j < nrOfCommands; j++) {
                        System.out.println("-- Com/Resp: " + j + " --");
                        System.out.println("  CmdType:  " + comType.get(j));
                        System.out.println("  Command:  " + bytesToHexNpe(com.get(j)));
                        System.out.println("  Response: " + bytesToHexNpe(res.get(j)));
                        System.out.println("  Offline:  " + byteToHex(offline.get(j)));
                    }
                    System.out.println("-----------------------");
                }
            }
            System.out.println("=======================");
        }
    }

}
