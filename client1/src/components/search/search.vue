<!-- 搜索框 -->
<template>
<div class="wrapper">
	<div class="search-wrapper">
		<input type="text" class="searchInput" v-model="search" @keyup="change" placeholder="搜索">
		<i class="icon iconfont icon-search" v-show="noText"></i>
		<div class="icon iconfont icon-search" v-show="haveText" @click="del"></div>
		<el-button type="text" @click="openPlus"><i class="el-icon-plus searchInput-add"></i></el-button>
			
	</div>

<el-dialog
  title="加入群聊"
  :visible.sync="dialogVisible"
@closed="handleClose"
  width='40%'>

    <el-checkbox v-model="item.checked" v-for="item in data" :key="item.id" width="150px"> <div> 
		<img class="avatar" width="42" height="42"   :src="item.myFriend?item.myFriend.img:item.img" />
		
		{{item.name}} </div>
		</el-checkbox>

  <br/>
   <br/>
    <br/>
    <el-button type="success" round style="float:right;top:-15px;     position: relative" @click="createGroupBT">加入群聊</el-button>
</el-dialog>
</div>
</template>

<script>
import {search} from "@/api/friend"
import {createGroup,joinGroup,getMyGroup} from "@/api/group"
import { mapState, mapActions ,mapGetters } from 'vuex'

export default {
	watch:{
		'$store.state.inviteFriendsGruop':function(){

 if(this.$store.state.inviteFriendsGruop){
	
		 this.openPlus();
  }
}
		
	},
	 methods: {
		   ...mapActions([
             'selectFriend','send',
        ])  ,
		  ...mapState([
            'inviteFriendsGruop'
        ]),
	
		 createGroupBT(){
			 this.dialogVisible = false
			console.log(this.data);
			this.value=[];
			this.data.forEach(item=>{
				if(item.checked){
						this.value.push(item.key)
				}
			})

		if(this.$store.state.inviteFriendsGruop){
			joinGroup(this.$store.state.inviteFriendsGruop.id,this.value)
			.then(data=>{
				   getMyGroup().then(res=>{
                   this.$store.state.groups=res
                   })
			})
		}else{
			createGroup(this.value).then(data=>{
           this.selectFriend(data)
           this.$store.state.groups.push(data)
	       this.selectFriend(data)
	      	this.send(data)
	       
				  		//    this.$store.dispatch("send");
		
			  
			})
		}


		 },
handleClose(){
	this.dialogVisible = false;
	this.$store.state.inviteFriendsGruop=null;
},
		 openPlus(){
			this.dialogVisible = true
			console.log("open");
			const data = [];
			    let newArray=null
			if(this.$store.state.inviteFriendsGruop){
            newArray=this.searchedFriendlist.filter((item,index)=>{
				let flag=true;
                  this.$store.state.inviteFriendsGruop. users.forEach(user=>{
					  console.log(item.id,user.id);
					  if(item.id==user.id){
                         flag=false;
					  }
				  })
				
               return flag;
			})
			}
		newArray=newArray?newArray:this.searchedFriendlist
           newArray.forEach((item,index)=>{
				data.push({
					label:item.remark||item.myFriend.name,
					img:item.myFriend.img,
					id:item.id,
						name:item.myFriend.name,
						checked:false,
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
			   checkList:[],
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
            'searchedFriendlist','getXX'
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
