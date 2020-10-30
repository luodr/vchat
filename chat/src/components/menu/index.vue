<template>
  <div class="login-container" :style="boxStyle">
    
  </div>
</template>

<script>
export default {
  name: 'VChat-login',
  props: {
    width: {
      type: String,
      default: '100vw'
    },
  
  },
  data () {
    return {
      loginForm: {},
      passwordType: 'password',
    }
  },
  computed: {
  
  },
  methods: {
    
  }
}
</script>

<style lang="scss" scoped>
$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

</style>
