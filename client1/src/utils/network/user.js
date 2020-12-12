/*
 * @Description: 关于用户相关的请求方法
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-08 13:31:07
 * @LastEditors: 
 * @LastEditTime: 2020-07-22 10:28:19
 */
import axios from '../request'

// 登录
export function userLogin(data) {
    return axios({
        url: '/users/login',
        method: 'post',
        data
    })
}

// 注册
export function userRegister(data) {
    return axios({
        url: '/users/register',
        method: 'post',
        data
    })
}

// 机器人聊天
export function robotChat(data) {
    return axios({
        url: '/robot',
        method: 'get',
        params:data
    })
}

// 上传头像
export function uploadImg(data){
    return axios({
        url: '/users/doUpload',
        method: 'post',
        data,
        //设置header信息
        headers:{'Content-Type':'multipart/form-data'}
    })
}

// 上传图片、视频（多张）
export function mutiUploadFile(data){
    return axios({
        url: '/users/mutiUpload',
        method: 'post',
        data,
        //设置header信息
        headers:{'Content-Type':'multipart/form-data'}
    })
}

// 发送验证码
export function sendEmailinfo(data) {
    return axios({
        url: '/sendemail',
        method: 'post',
        data
    })
}