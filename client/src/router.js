import Vue from "vue";
import VueRouter from "vue-router";
import index from "./components/pages/index.vue";
import login from "./components/pages/login.vue";
Vue.use(VueRouter);
export default new VueRouter({
  routes: [
    { path: "/", component: index },
    { path: "/login", component: login }
  ]
});
