import Vue from "vue";
import App from "./App.vue";
import LemonIMUI from "../src";
import ElementUI from "element-ui";
import router from "./router";
Vue.use(LemonIMUI);
Vue.use(ElementUI);
Vue.config.productionTip = false;
import "element-ui/lib/theme-chalk/index.css";
new Vue({
  router,
  // 注入到根实例中
  components: { App },
  render: h => h(App)
}).$mount("#app");
