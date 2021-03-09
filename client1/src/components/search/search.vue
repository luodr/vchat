<!-- 搜索框 -->
<template>
<div class="wrapper">
	<div class="search-wrapper">
		<input type="text" class="searchInput" v-model="search" @keyup="change" placeholder="搜索">
		<i class="icon iconfont icon-search" v-show="noText"></i>
		<div class="searchInput-delete" v-show="haveText" @click="del"></div>
		<el-button type="text" @click="openPlus"><i class="el-icon-plus searchInput-add"></i></el-button>
			
	</div>

<el-dialog
  title="创建群聊"
  :visible.sync="dialogVisible"
  width='40%'>
   <el-transfer
    filterable
    :filter-method="filterMethod"
    filter-placeholder="搜索"
    v-model="value"
	:titles="titles"
    :data="data">
  </el-transfer>
    <el-button type="success" round style="float:right;top:-15px;     position: relative" @click="createGroupBT">创建群聊</el-button>
</el-dialog>
</div>
</template>

<script>
import {search} from "@/api/friend"
import {createGroup} from "@/api/group"
import { mapState, mapActions ,mapGetters } from 'vuex'

export default {
	 methods: {
		   ...mapActions([
             'selectFriend','send',
        ])  ,
		 createGroupBT(){
			createGroup(this.value).then(data=>{
		
		     
this.selectFriend(data)
       this.$store.state.groups.push(data)
	   this.selectFriend(data)
		this.send(data)
	       
				  		//    this.$store.dispatch("send");
		
			  
			})
		 },

		 openPlus(){
			this.dialogVisible = true
			const data = [];
			this.searchedFriendlist.forEach((item,index)=>{
				data.push({
					label:item.remark||item.myFriend.name,
					key:{
						id:item.id,
						name:item.myFriend.name
					},
					pinyin:item.remark||item.myFriend.name,
				})
			})
			this.value=[];
			this.data=data;
		 },
        change () {
        	// this.$store.dispatch('search', this.search)
        },
        del () {
          search({phone:this.search}).then((data)=>{
			        console.log(data,'搜索到的数据');
			        this.$store.state.searchList=[];
			        this.$store.state. searchList.push(data);
                    this.$router.push("/search")
		  });
        }
     },
     data () {
     	return {
     		   search: '',
			   active: false,
			   dialogVisible: false,
			   data:[],
			   titles:["勾选联系人","已勾选"],
               value: [],
        filterMethod(query, item) {
          return item.pinyin.indexOf(query) > -1;
        }
     	}
     },
     computed: {
		   ...mapGetters([
            'searchedFriendlist'
        ]),
        noText () {
   	      if(this.search  === '') return true
   	      return false
        },
        haveText () {
   	      if(this.search  === '') return false
   	      return true
        }
     }
}
</script>

<style lang="stylus" scoped>
.wrapper
    padding: 22px 12px 12px 12px
	.search-wrapper
	  position: relative
	  display: flex
	  box-sizing: border-box
	  height: 26px
	  width: 100%
	  background-color: #e5e3e2
	//   border: 1px solid #d9d7d6
	//   border-radius: 2px
	  .searchInput
	    flex: 1
	    font-size: 12px
	    padding: 6px
	    background-color: #e5e3e2
	    outline: none
	    &:focus
	    	background-color: #f2efee
	  .icon-search
	    display: inline-block
	    width: 24px
	    height: 24px
	    font-size: 14px
	    line-height: 24px
	    text-align: center
	  .searchInput-delete
	    display: block
	    position: absolute
	    outline: none
	    top: 0;
	    right: 0;
	    width: 24px
	    height: 100%
	    background-image: url(delete.png)
	    background-size: 26px
	    background-position: center
	    background-repeat: no-repeat
	    cursor: pointer
	  .searchInput-add
	    display: block
	    position: absolute
	    outline: none
	    top: 4px;
	    right: -20px;
	    width: 24px
	    height: 100%
        
	    background-size: 26px
	    background-position: center
	    background-repeat: no-repeat
	    cursor: pointer
</style>
