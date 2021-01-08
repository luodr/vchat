const getters = {
    // 筛选出含有搜索值的聊天列表
    searchedChatlist(state) {
        let sessions = state.friendlist.filter(friend => friend.messages.length>0);
        let groups = state.groups.filter(group => group.messages.length>0);
        sessions.push(...groups);
        if(!state.selectItem.messages&&sessions.length>0)state.selectItem=sessions[0];
        return sessions
    },
    // 筛选出含有搜索值的好友列表
    searchedFriendlist(state) {
        let friends = state.friendlist.filter(friends => {
            if(friends.remark) return friends.remark.includes(state.searchText)
            if(friends.myFriend.name) return friends.myFriend.name.includes(state.searchText)

        });
        return friends
    },
     // 获取所有群聊
    getGruops(state) {
        return state.groups
    },
    getChats(state) {
        return state.groups
    },
    // 通过当前选择是哪个对话匹配相应的对话
    selectedChat(state) {
     if(state.selectItem.id){
         return state.selectItem
     }
        let session = state.friendlist.find(session => session.id === state.selectId);
        
        return session
    },
    // 通过当前选择是哪个好友匹配相应的好友
    selectedFriend(state) {
         if(state.selectListItem) return state.selectListItem
        let friend = state.friendlist.find(friend => friend.id === state.selectFriendId);
        if(!friend)return {id:0}
        return friend
    },
      // 通过当前选择是哪个好友匹配相应的好友
    selectedSearch(state) {
        return state.searchList[state.searchList.length-1]
    },
    messages(state) {
        return state.selectItem.messages
    }
}

export default getters;