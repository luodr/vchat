const getters = {
    // 筛选出含有搜索值的聊天列表
    searchedChatlist(state) {
        let sessions = state.chatlist.filter(sessions => sessions.user.name.includes(state.searchText));
        return sessions
    },
    // 筛选出含有搜索值的好友列表
    searchedFriendlist(state) {
        let friends = state.friendlist.filter(friends => friends.remark.includes(state.searchText));
        return friends
    },
    // 通过当前选择是哪个对话匹配相应的对话
    selectedChat(state) {
        let session = state.chatlist.find(session => session.id === state.selectId);
        return session
    },
    // 通过当前选择是哪个好友匹配相应的好友
    selectedFriend(state) {
        let friend = state.friendlist.find(friend => friend.id === state.selectFriendId);
        return friend
    },
    messages(state) {
        let session = state.chatlist.find(session => session.id === state.selectId);
        return session.messages
    }
}

export default getters;