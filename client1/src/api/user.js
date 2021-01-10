
const { post, get } = require("./request");

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



export  function register(data) {
  return new Promise( (resolve,reject)=>{
    post("register", data).then(res=>{
   if (res.data) {
    resolve(res.data)  
   }
   resolve(res)
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
