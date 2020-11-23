const axios = require('axios');
export default async function    request(method,url,data)  {
   return  new Promise( async (resolve, reject) =>   {
   await axios[method]('api/'+url,data).then(function (response) {
        resolve( response.data);
       })
       .catch(function (error) {
         console.log(url,'请求失败！');
         reject(response.data)
       })
 
   });
}