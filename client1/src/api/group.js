import { post, get } from "./request";
export const createGroup =  (data) => {
  return new Promise( (resolve,reject)=>{
   post("/group/create", data).then(res=>{
        resolve(res.data)
     })
    })
   
};

export const getMyGroup =  (data) => {
   return new Promise( (resolve,reject)=>{
    get("/group/", data).then(res=>{
         resolve(res.data)
      })
     })
    
 };

 export const leaveGroup =  (data) => {
   return new Promise( (resolve,reject)=>{
    post("/group/leave/"+data).then(res=>{
         resolve(res.data)
      })
     })
    
 };
 export const joinGroup =  (id,data) => {
   return new Promise( (resolve,reject)=>{
    post("group/join/"+id,data).then(res=>{
         resolve(res.data)
      })
     })
    
 };