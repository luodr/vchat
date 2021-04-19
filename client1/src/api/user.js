
const { post, get } = require("./request");
import store from '../store';
export   function login(data) {
 return new Promise( (resolve,reject)=>{
 post("login", data).then(res=>{
  if (res.data) {
    localStorage.token = res.data;
  }else{
    localStorage.token =""
  }
  resolve(res)
  })
 })
  }
 
 
export  function getInfo(data) {
  return new Promise( (resolve,reject)=>{
    get("myInfo", data).then(res=>{
   if (res.data) {
    resolve(res.data)  
   }
   resolve(res)
   })
  })
   }

   export  function updateImg(data) {
    return new Promise( (resolve,reject)=>{
      post("update/img", data).then(res=>{
     if (res.data) {
      resolve(res.data)  
     }
     resolve(res)
     })
    })
     }



     export  function updateName(data) {
      return new Promise( (resolve,reject)=>{
        post("update/name", data).then(res=>{
       if (res.data) {
        resolve(res.data)  
       }
       resolve(res)
       })
      })
       }
  


    


export  function register(data) {
  return new Promise( (resolve,reject)=>{
    post("register", data).then(res=>{
   if (res.data) {
    resolve(res.data)  
   }
   resolve(res.data)
   })
  })
}

export  function forget(data) {
  return new Promise( (resolve,reject)=>{
    post("forget", data).then(res=>{
   if (res.data) {
    resolve(res.data)  
   }
   resolve(res.data)
   })
  })
}


export  function code(data) {
  return new Promise( (resolve,reject)=>{
    get("code", data).then(res=>{
   if (res.data) {
    resolve(res.data)  
   }
   resolve(res)
   })
  })
}
export function loginOut(){
  localStorage.token =""
  store.state.ws.close();
  
}