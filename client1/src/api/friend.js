import { post, get } from "./request";
export const getFriends =  () => {
  return new Promise( (resolve,reject)=>{
     get("friend/list", {}).then(res=>{
  console.log(res.data);
        resolve(res.data)
     })
    })
   
};
export const deleteFriends =  data => {
  return new Promise( (resolve,reject)=>{
     get("friend/deleteFriend", {}).then(res=>{
        resolve(res.data)
     })
    })
};
export const search =  data => {
   return new Promise( (resolve,reject)=>{
      get("search", data).then(res=>{
         resolve(res.data)
      })
     })
 };
 export const addFriends =  data => {
   return new Promise( (resolve,reject)=>{
      post("friend/add", data).then(res=>{
         resolve(res.data)
      })
     })
 };

 

 export const getAddFriends =  data => {
   return new Promise( (resolve,reject)=>{
      get("friend/getFriendAdds", data).then(res=>{
         resolve(res.data)
      })
     })
 };


 export const agreeFriend =  data => {
   return new Promise( (resolve,reject)=>{
      post("friend/agree", data).then(res=>{
         resolve(res.data)
      })
     })
 };

 export const refuseFriend =  data => {
   return new Promise( (resolve,reject)=>{
      post("friend/refuse", data).then(res=>{
         resolve(res.data)
      })
     })
 };