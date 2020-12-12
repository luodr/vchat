import originAxios from 'axios'
import Config from './config.js';

export default function axios(option) {
    return new Promise((resolve, reject) => {
        // 1.创建axios的实例
        const instance = originAxios.create({
            baseURL: process.env.NODE_ENV === 'development' ? '/api' : Config.domain,
            timeout: 10000
        });

        // 配置请求和响应拦截
        instance.interceptors.request.use(config => {
            return config
        }, err => {
            // console.log('来到了request拦截failure中');
            return err
        })

        instance.interceptors.response.use(response => {
            // console.log('来到了response拦截success中');
            return response.data
        }, err => {
            if (err && err.response) {
                switch (err.response.status) {
                    case 400:
                        err.message = '请求错误'
                        break
                    case 401:
                        err.message = '未授权的访问'
                        break
                }
            }
            return err
        })

        // 2.传入对象进行网络请求
        instance(option).then(res => {
            resolve(res)
        }).catch(err => {
            reject(err)
        })
    })
}
