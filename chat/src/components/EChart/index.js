import EChart from './index.vue'

EChart.install = function (Vue) {
  Vue.component(EChart.name, EChart)
}

export default EChart