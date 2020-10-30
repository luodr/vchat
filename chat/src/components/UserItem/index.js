import Item from './index.vue'

Item.install = function (Vue) {
  Vue.component(Item.name, Item)
}

export default Item