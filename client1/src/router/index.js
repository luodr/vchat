/*
 * @Description: 
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-06 09:19:13
 * @LastEditors: 
 * @LastEditTime: 2020-07-15 09:39:16
 */
import Vue from 'vue'
import Router from 'vue-router'
import longSock from "@/socket/socket";
import store from '../store';
import { createLogger } from 'vuex';
import {getFriends,getAddFriends} from "@/api/friend";
import {getInfo} from "@/api/user";
import {getMyGroup} from "@/api/group"
Vue.use(Router)
import { 

  Notification 
} from 'element-ui';
const router = new Router({
  // 共三个页面： 聊天页面，好友页面，个人简历分别对应一下路由
  routes: [
    {
      path: '/',
      redirect: '/chat'
    },
    {
      path: '/chat',
      component: () => import('@/page/chat/chat.vue'),
      meta: {
        keepAlive: true, // true :缓存  false :不缓存
        isBack: false, //用于判断上一个页面是哪个
        isShowAside: true,
        requiresAuth: true
      }
    },
    {
      path: '/friend',
      component: () => import('@/page/friend/friend.vue'),
      meta: {
        keepAlive: true, // true :缓存  false :不缓存
        isBack: false, //用于判断上一个页面是哪个
        isShowAside: true,
        requiresAuth: true
      }
    },
    {
      path: '/search',
      component: () => import('@/page/search/searchpage.vue'),
      meta: {
        keepAlive: true, // true :缓存  false :不缓存
        isBack: false, //用于判断上一个页面是哪个
        isShowAside: true,
        requiresAuth: true
      }
    },
    
    {
      path: '/my',
      component: () => import('@/page/resume/resume.vue'),
      meta: {
        keepAlive: true, // true :缓存  false :不缓存
        isBack: false, //用于判断上一个页面是哪个
        isShowAside: true,
        requiresAuth: true
      }
    },
    {
      path: '/myfile',
      component: () => import('@/page/myfile/myfile.vue'),
      meta: {
        keepAlive: true, // true :缓存  false :不缓存
        isBack: false, //用于判断上一个页面是哪个
        isShowAside: true,
        requiresAuth: true
      }
    },
    {
      path: '/login',
      component: () => import('@/page/login.vue'),
      meta: {
        keepAlive: true, // true :缓存  false :不缓存
        isBack: false, //用于判断上一个页面是哪个
        isShowAside: false,
        requiresAuth: false
      }
    },{
      path: '/reg',
      component: () => import('@/page/register.vue'),
      meta: {
        keepAlive: true, // true :缓存  false :不缓存
        isBack: false, //用于判断上一个页面是哪个
        isShowAside: false,
        requiresAuth: false
      }
    },
  ],
  linkActiveClass: 'active'
})

function initData(){
  // if(!store.state.user.phone){
    getInfo().then(res=>{
         store.state.user=res
    })
  // }
     getFriends().then(res=>{
       console.log('朋友们',res)
        store.state.friendlist=res
        if(res&&res.length>0)
          store.state. selectId=res[0].id
      });
      getAddFriends().then(res=>{
        store.state.newFriendList=res
       
      });
      getMyGroup().then(res=>{
        store.state.groups=res
      })
      if(!store.state.ws)
     store.state.ws=longSock("ws://127.0.0.1:8880/webSocket/"+localStorage.token,(evt, ws)=>{    
       if(evt.data){
         let obj=JSON.parse(evt.data)
        
         console.log(obj,"接收到的！");
         console.log(store.state.selectItem,obj)
         switch(obj.type){
           case 'PrivateChat':
           let session = store.state.friendlist.find(session => session.id === obj.data.send_user_id)
         session.messages.push(obj.data)
         Notification({
          title: session.remark||session.myFriend.name,
          message: obj.data.context,
          position: 'bottom-right'
        });
         break;
         case 'addFriend':
          store.state.newFriendList.push(obj.data)
          Notification({
            title: "好友申请",
            message: obj.data.user.name,
            position: 'bottom-right'
          });
           break;
           case 'GroupChat':
            let g = store.state.groups.find(group => group.id === obj.data.to_group_id)
            g.messages.push(obj.data)
           break;
           case 'CreateGroupChat':
            let newG = store.state.groups.find(group => group.id === obj.data.id)
            if(!newG){ store.state.groups.push(obj.data)}
           break;
         }
        
       }
});
store.state.dataInit=true
}
router.beforeEach((to, from, next) => {
 
    if(!localStorage.token&&to.path!=='/login'){
      next('/login')
    }else{
      //&&!store.state.dataInit
       if(localStorage.token){
          initData()
       }
      next()
    }
})

export default router