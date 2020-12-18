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

import store from '../store';
import { createLogger } from 'vuex';

Vue.use(Router)

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
    },
  ],
  linkActiveClass: 'active'
})


//路由守卫
// router.beforeEach((to, from, next) => {
//   let flag = store.state.user.name || false
//   if (to.matched.some(record => record.meta.requiresAuth)) { // 判断该路由是否需要登录权限
//       if (flag) { //也可以用vuex来判断
//           next()
//       } else {
//           next('/login')
//       }
//   } else {
//       if (flag) { //也可以用vuex来判断
//           if (to.path === '/login') {
//               next('/chat')
//           } else {
//               next()
//           }
//       } else {
//           next()
//       }
//   }

// })

export default router