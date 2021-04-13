import { post, get } from "./request";
export const getMessages =  () => {
  return new Promise( (resolve,reject)=>{
     get("/message/list", {}).then(res=>{
        resolve(res.data)
     })
    })
   
};
export const readMessages =  (data) => {
   return new Promise( (resolve,reject)=>{
      post("/message/read",data).then(res=>{
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

 export const uploadOne=(data)=>{
   return new Promise( (resolve,reject)=>{
      post("file/upload/one",data).then(res=>{
         resolve(res.data)
      })
     })

 }

 export const uploadBlobOne=(data)=>{
   return new Promise( (resolve,reject)=>{
      post("file/uploadBlob/one",data).then(res=>{
         resolve(res.data)
      })
     })

 }

 export const speech=(data)=>{
   return new Promise( (resolve,reject)=>{
      post("message/speech",data).then(res=>{
         resolve(res.data)
      })
     })

 }

 export const imageToText=(data)=>{
   return new Promise( (resolve,reject)=>{
      post("message/imageToText",data).then(res=>{
         resolve(res.data)
      })
     })

 }
 export const translate=(data)=>{
   return new Promise( (resolve,reject)=>{
      post("message/translate",data).then(res=>{
         resolve(res.data)
      })
     })

 }
 export const withdrawMessage=(id,data)=>{
   return new Promise( (resolve,reject)=>{
      post("message/withdraw/"+id,data).then(res=>{
         resolve(res.data)
      })
     })

 }




 