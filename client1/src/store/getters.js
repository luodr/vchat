const getters = {
    // 筛选出含有搜索值的聊天列表
    searchedChatlist(state) {
    
        let sessions = state.friendlist.filter(friend => friend.messages.length>0);
   
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
    // 通过当前选择是哪个对话匹配相应的对话
    selectedChat(state) {
        let session = state.friendlist.find(session => session.id === state.selectId);
        return session
    },
    // 通过当前选择是哪个好友匹配相应的好友
    selectedFriend(state) {

        let friend = state.friendlist.find(friend => friend.id === state.selectFriendId);
        if(!friend)return {id:0}
        return friend
    },
      // 通过当前选择是哪个好友匹配相应的好友
    selectedSearch(state) {
        console.log(state.searchList);
        // let friend = state.searchList.find(friend => friend.id === state.selectFriendId);
        console.log(state.searchList[state.searchList.length-1],'state.searchList[state.searchList.length-1]');
        return state.searchList[state.searchList.length-1]
    },
    messages(state) {
        let session = state.friendlist.find(session => session.id === state.selectId);
         if(session)
        return session.messages
    }
}

export default getters;