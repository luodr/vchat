const { post, get } = require("./request");

export async function login(data) {
  const res = await post("login", data);
  console.log(res);
  if (res.data) {
    localStorage.token = res.data;
  }

  return res;
}
export async function register(data) {
  const res = await post("register", data);
  return res;
}
export async function code(phone) {
  console.log(get);
  const res = await get("code", { phone });
  return res;
}
