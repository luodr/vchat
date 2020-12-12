<!--
 * @Description: 
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-06 09:56:05
 * @LastEditors: 
 * @LastEditTime: 2020-07-15 14:17:57
--> 
<template>
	<div class="content">
		<div class="friend-wrapper">
			<search></search>
      <friendlist></friendlist>
		</div>
		<div class="friendinfo">
			<info></info>
		</div>
	</div>
</template>

<script>
import search from '../../components/search/search'
import friendlist from '../../components/friendlist/friendlist'
import info from '../../components/info/info'
export default{
    components: {
        search,
        friendlist,
        info
    }
}
</script>

<style lang="stylus" scoped>
.content
  display: flex
  width: 800px
  .friend-wrapper
    width: 250px
    background: rgb(230,230,230)
  .friendinfo
    flex: 1
</style>