import { post, get } from "./request";
export const getFriends =  () => {
  return new Promise( (resolve,reject)=>{
     get("friend/list", {}).then(res=>{
  
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
