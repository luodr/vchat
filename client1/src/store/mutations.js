import router from '../router'
import store from './index'
// 引入域名
const Config = require('@/utils/config.js') 

const mutations = {
    // 添加新朋友到好友列表
    addNewFriendToList(state, friend) {
        state.friendlist.push(friend)
    },

    // 获取socketid
    getSocketID(state,socketid){
        state.user.socketid = socketid
        // state.user.onLineStatus = 1
    },

    // 获取用户数据
    getUserInfo(state, user) {
        // console.log('用户：', user)
        state.user.name = user.username
        // 用之前加个判断
        if(user.img.includes('http')){
            state.user.img = user.img
        }else{
            state.user.img = Config.upload + user.img 
        }
        // state.user.onLineStatus = user.onlineStatus
        // state.user.socketid = user.socketid
        state.user.email = user.email
        state.user.logintime = user.logintime
        state.user.onLineStatus = 1
        state.user.id = user._id
    },
    // 退出登录
    logoutUser(state) {
        state.user.name = ''
        state.user.img = require('@/static/images/UserAvatar.jpg')
        state.user.onLineStatus = 2
        state.user.socketid = ''
        state.user.email = ''
    },

    // 改变用户状态
    changeStatus(state, status) {
        state.user.onLineStatus = status
    },

    // 更换头像
    changeFace(state, img) {
        state.user.img = img
    },

    // 从localStorage 中获取数据
    initData(state) {
        let data = localStorage.getItem('vue-chat');
        if (data) {
            state.chatlist = JSON.parse(data);
        }
    },
    // 获取搜索值
    search(state, value) {
        state.searchText = value
    },
    // 得知用户当前选择的是哪个对话。便于匹配对应的对话框
    selectSession(state, value) {
        // console.log(value,'选择列表哪一个聊天')
        state.selectId = value
        // 让未读消息变为已读消息
        // state.user.msgCount=0
    },
    // 得知用户当前选择的是哪个好友。
    selectFriend(state, value) {
        state.selectFriendId = value
    },
    // 发送信息
    sendMessage(state, msg) {
        // console.log(msg,state.selectId)
        let result = state.chatlist.find(session => session.id === state.selectId);
        result.messages.push({
            content: msg.content,
            date: new Date(),
            self: true
        });
        if (result.user.name === '机器人') {
            setTimeout(() => {
                result.messages.push({
                    content: msg.reply,
                    date: new Date(),
                    self: false
                });
            }, 500)
        }
    },

    // 选择好友后，点击发送信息。判断在聊天列表中是否有该好友，有的话跳到该好友对话。没有的话
    // 添加该好友的对话 并置顶
    send(state) {
        let result = state.friendlist.find(friend => friend.id === state.selectFriendId)
        let msg = state.chatlist.find(msg => msg.user.name === result.remark)
        if (!msg) {
            state.selectId = 1
            for (let i = 0; i < state.chatlist.length; i++) {
                state.chatlist[i].id++;
                state.chatlist[i].index++;
            }
            state.chatlist.unshift({
                id: 1,
                user: {
                    name: result.remark,
                    img: result.img
                },
                messages: [{
                    content: '已经置顶聊天，可以给我发信息啦！',
                    date: new Date()
                }],
                index: 1
            })
            router.push({ path: '/chat' })  // 可能不需要这行代码
        } else {
            state.selectId = msg.index
            router.push({ path: '/chat' })
        }
    },

    // 聊天列表 信息索引
    deleteChatItem(state, index) {
        state.chatDelIndex = index
        // console.log(state.chatDelIndex)
        // state.chatlist.splice(state.chatDelIndex,1)
        store.getters.searchedChatlist.splice(state.chatDelIndex, 1)
        if( state.chatlist.length > 1){
            state.selectId = state.selectId + 1
        }
        let chatlist = JSON.parse(localStorage.getItem('vue-chat'))
        chatlist.splice(state.chatDelIndex,1)
        localStorage.setItem('vue-chat',JSON.stringify(chatlist))
    }

}
export default mutations;