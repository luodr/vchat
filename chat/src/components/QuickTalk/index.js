
import Talk from './index.vue'

Talk.install = function (Vue) {
  Vue.component(Talk.name, Talk)
}

export default Talk