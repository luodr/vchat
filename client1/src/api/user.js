
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

// export  function register(data) {
//   const res =  post("register", data);
//   return res;
// }
// export  function code(phone) {
//   console.log(get);
//   const res = await get("code", { phone });
//   return res;
// }
