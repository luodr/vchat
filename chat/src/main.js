import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VChat from '@/components'

Vue.use(ElementUI)

// import VChat from '../lib/VChat.common';
// import '../lib/VChat.css';
Vue.use(VChat)

console.log(VChat);
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
