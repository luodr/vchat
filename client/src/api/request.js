const axios = require("axios");

axios.interceptors.request.use(
  config => {
    let token = window.localStorage.getItem("token");

    if (token) {
      config.headers.token = token;
    }
    return config;
  },
  function(error) {
    return Promise.reject(error);
  }
);

export const post = async (url, data) => {
  //localStorage.token
  return new Promise(async (resolve, reject) => {
    await axios
      .post("api/" + url, data)
      .then(function(response) {
        resolve(response);
      })
      .catch(function(error) {
        console.log(error, "请求失败！");
      });
  });
};

export const get = async (url, params) => {
  return new Promise(async (resolve, reject) => {
    await axios
      .get("api/" + url, {
        params
      })
      .then(function(response) {
        resolve(response);
      })
      .catch(function(error) {
        console.log(error, "请求失败！");
      });
  });
};
