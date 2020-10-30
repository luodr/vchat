import UserCard from './index.vue'

UserCard.install = function (Vue) {
  Vue.component(UserCard.name, UserCard)
}

export default UserCard