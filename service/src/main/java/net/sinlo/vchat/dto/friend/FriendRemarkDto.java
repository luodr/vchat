package net.sinlo.vchat.dto.friend;

public class FriendRemarkDto {
    private int friendId;
    private String remark;
    public int getFriendId() {
        return friendId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FriendRemarkDto{" +
                "friendId=" + friendId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
}
