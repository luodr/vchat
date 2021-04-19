import { post, get } from "./request";
export const getDynamic =  () => {
  return new Promise( (resolve,reject)=>{
     get("dynamic/list", {}).then(res=>{
       resolve(res.data)
     })
    })
   }
   
export const sendDynamic =  (data) => {
      return new Promise( (resolve,reject)=>{
         post("dynamic/send", data).then(res=>{
           resolve(res.data)
         })
        })
       }
       
export const doodDynamic =  (id) => {
         return new Promise( (resolve,reject)=>{
            post("dynamic/good/"+id, {}).then(res=>{
              resolve(res.data)
            })
           })
          }
export const commentDynamic =  (data) => {
            return new Promise( (resolve,reject)=>{
               post("dynamic/comment", data).then(res=>{
                 resolve(res.data)
               })
              })
             }
             
             
export const delteDynamic =  (id) => {
              return new Promise( (resolve,reject)=>{
                 post("dynamic/update/delete/"+id, ).then(res=>{
                   resolve(res.data)
                 })
                })
               }