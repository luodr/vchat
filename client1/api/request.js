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

export const post =  (url, data) => {
  //localStorage.token
  return new Promise( (resolve, reject) => {
     axios
      .post("api/" + url, data)
      .then(function(response) {
        resolve(response);
      })
      .catch(function(error) {
        console.log(error, "请求失败！");
      });
  });
};

export const get =  (url, params) => {
  return new Promise( (resolve, reject) => {
     axios
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
