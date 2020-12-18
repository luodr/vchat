import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

// 全局样式
import '@/common/global.css'

// 重置样式
import './static/css/reset.css'

// 引入 font.css
import './assets/fonts/iconfont.css';

// 引入 element-ui
import '@/elementui/index.js'

// 图片预览
import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'
Vue.use(Viewer)
Viewer.setDefaults({
  Options: { 'inline': true, 'button': true, 'navbar': true, 'title': true, 'toolbar': true, 'tooltip': true, 'movable': true, 'zoomable': true, 'rotatable': true, 'scalable': true, 'transition': true, 'fullscreen': true, 'keyboard': true, 'url': 'data-source' }
})

// 引入高德地图
import VueAMap from 'vue-amap';
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
  key: '74976c5462c5d65770f8e09772189af6',
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor', 'AMap.Geolocation'],
  v: '1.4.4'
});
let socket = new WebSocket("ws://127.0.0.1:8888/webSocket/"+parseInt(Math.random()*5))
 //打开事件
 socket.onopen = function() {
  console.log("websocket已打开");
  //socket.send("这是来自客户端的消息" + location.href + new Date());
};
//获得消息事件
socket.onmessage = function(msg) {
  var serverMsg = "收到服务端信息：" + msg.data;
  console.log(serverMsg);
  //发现消息进入    开始处理前端触发逻辑
};
//关闭事件
socket.onclose = function() {
  console.log("websocket已关闭");
};
//发生了错误事件
socket.onerror = function() {
  console.log("websocket发生了错误");
}

// socket
// import io from '@/assets/socket/socket.io.js'
// Vue.prototype.socket = io('http://localhost:8888')
// console.log(Vue.prototype.socket)

// import Router from 'vue-router'
// const routerPush = Router.prototype.push
// Router.prototype.push = function push(location) {
//   return routerPush.call(this, location).catch(error=> error)
// }

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
