package net.sinlo.vchat.dto;

public class WithdrawMessage {
    private  int id;
    private String type;
    private  int userID;

    public WithdrawMessage() {
    }

    public WithdrawMessage(int id, String type, int userID) {
        this.id = id;
        this.type = type;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
