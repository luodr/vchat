import { post, get } from "./request";
export const getMessages =  () => {
  return new Promise( (resolve,reject)=>{
     get("/message/list", {}).then(res=>{
        resolve(res.data)
     })
    })
   
};

