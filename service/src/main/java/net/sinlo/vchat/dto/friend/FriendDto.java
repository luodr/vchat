package net.sinlo.vchat.dto.friend;

public class FriendDto {
    private int friendId;

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "FriendDto{" +
                "friendId=" + friendId +
                '}';
    }
}
