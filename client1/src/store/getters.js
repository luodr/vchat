const getters = {
    // 筛选出含有搜索值的聊天列表
    searchedChatlist(state) {
        state.unReadCount=0;
        let sessions = state.friendlist.filter(friend => friend.messages.length>0);
        let groups = state.groups.filter(group => group.messages.length>0);
        sessions.push(...groups);
     
            // 排序
            if (Array.isArray(sessions)) {
              for (var i = 0; i < sessions.length - 1; i++) {
                var minIdex = i;
                for (var j = i + 1; j < sessions.length; j++) {
                    let jMsgs=sessions[j].messages; 
                    let mMsgs=sessions[minIdex].messages;
                    if(!jMsgs.length||!mMsgs.length){
                      continue; 
                     }
                     let j_updateAt=new Date(jMsgs[jMsgs.length-1].updateAt).getTime()
                     let minIdex_updateAt=new Date(mMsgs[mMsgs.length-1].updateAt).getTime()
                  minIdex = j_updateAt >minIdex_updateAt ? j : minIdex;
                }
                [sessions[i], sessions[minIdex]] = [sessions[minIdex], sessions[i]];
              }
            }

        if(!state.selectItem.messages&&sessions.length>0)state.selectItem=sessions[0];

        sessions.forEach(element => {
            element.messages.forEach((item)=>{
                if(item.id&&!item.read&&state.user.id!==item.send_user_id)
                {
                    state.unReadCount++;
                }
            })
                
            
        });

        state.unReadCount
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
         console.log(state.selectItem,'state.selectItem');
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