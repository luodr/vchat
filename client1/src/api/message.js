import { post, get } from "./request";
export const getMessages =  () => {
  return new Promise( (resolve,reject)=>{
     get("/message/list", {}).then(res=>{
        resolve(res.data)
     })
    })
   
};

export const sendPrivateChat =  (data) => {
   return new Promise( (resolve,reject)=>{
      post("chat/sendPrivateChat",data).then(res=>{
         resolve(res.data)
      })
     })
    
 };


 export const sendGroupChat =  (data) => {
   return new Promise( (resolve,reject)=>{
      post("chat/sendGroupChat",data).then(res=>{
         resolve(res.data)
      })
     })
    
 };