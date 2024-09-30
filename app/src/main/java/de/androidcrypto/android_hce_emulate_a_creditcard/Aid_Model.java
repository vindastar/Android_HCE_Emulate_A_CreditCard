package de.androidcrypto.android_hce_emulate_a_creditcard;

import java.util.List;

/**
 * This class stores the data read by the app for one AID (application). This data will NOT be
 * exported because some other AIDs may be available on the EMV card.
 * All AIDs will be collected in Aids_Model that is exported.
 */

public class Aid_Model {
    private byte[] Aid;
    private String panFoundString;
    private String expFoundString;
    private List<String> commandType;
    private List<byte[]> command;
    private List<byte[]> respond;
    private List<Byte> fileOffline;

    public Aid_Model(byte[] aid) {
        Aid = aid;
    }

    public byte[] getAid() {
        return Aid;
    }

    public void setAid(byte[] aid) {
        Aid = aid;
    }

    public String getPanFoundString() {
        return panFoundString;
    }

    public void setPanFoundString(String panFoundString) {
        this.panFoundString = panFoundString;
    }

    public String getExpFoundString() {
        return expFoundString;
    }

    public void setExpFoundString(String expFoundString) {
        this.expFoundString = expFoundString;
    }

    public List<String> getCommandType() {
        return commandType;
    }

    public void setCommandType(List<String> commandType) {
        this.commandType = commandType;
    }

    public List<byte[]> getCommand() {
        return command;
    }

    public void setCommand(List<byte[]> commands) {
        this.command = commands;
    }

    public List<byte[]> getRespond() {
        return respond;
    }

    public void setRespond(List<byte[]> respond) {
        this.respond = respond;
    }

    public List<Byte> getFileOffline() {
        return fileOffline;
    }

    public void setFileOffline(List<Byte> fileOffline) {
        this.fileOffline = fileOffline;
    }
}
