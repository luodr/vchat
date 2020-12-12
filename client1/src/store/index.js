/*
 * @Description: 
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-06 09:56:29
 * @LastEditors: 
 * @LastEditTime: 2020-07-16 10:38:16
 */
import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router'
Vue.use(Vuex)

import createPersistedState from 'vuex-persistedstate'

// 引入对应的核心文件
import state from './state'
import mutations from './mutations'
import getters from './getters'
import actions from './actions'

const store = new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [
    createPersistedState({
      storage: window.localStorage,
      reducer(val) {
        // console.log('所有state:', val)
        return {
          user: val.user
        }
      }
    })
  ]
})

// 监听聊天列表的值， 发生变化就保存在localStorage中
store.watch(
  (state) => state.chatlist,
  (val) => {
    localStorage.setItem('vue-chat', JSON.stringify(val));
  }, {
  deep: true
}
)
export default store;