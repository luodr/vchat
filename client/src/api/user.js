const request=require('./request').default

export async function  login(data){
  const res=await request('post','login',data);
    if(res.data){
      localStorage.token=res.data.token;
    }
  return  res
}
export async function  register(data){
  const res=await request('post','register',data);
    if(res.data){
      localStorage.token=res.data.token;
    }
  return  res
}