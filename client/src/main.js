import Vue from "vue";
import App from "./App.vue";
import LemonIMUI from "../src";
import ElementUI from 'element-ui';
Vue.use(LemonIMUI);
Vue.use(ElementUI);
Vue.config.productionTip = false;
import 'element-ui/lib/theme-chalk/index.css';
new Vue({
  render: h => h(App)
}).$mount("#app");
