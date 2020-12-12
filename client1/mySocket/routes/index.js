const express = require('express');
const router = express.Router();
const axios = require('axios')

const multer = require('multer')
const upload = multer({ dest: 'public/uploads/' })
// 验证码
const nodemailer = require("nodemailer");
// 引入工具函数
const funcUtils = require('../utils/func')

// 引入验证码模型
let codeModel = require('../db/schemas/msgcode');
/**
 * 插入 单条数据 / 多条数据
 * params: data 可是是对象或者数组
 * 调用方式：insertCode({email: '2825225@qq.com',code:'123456'})
 */
function insertCode(data) {
  return codeModel.create(data)
}

// 开启一个 SMTP 连接池
let transport = nodemailer.createTransport({
  host: "smtp.163.com", // 主机
  port: 465, // SMTP 端口
  secure: true, // 使用 SSL
  auth: {
    user: "wq18252718866@163.com", // 账号
    pass: "FVDVUPSTCLBEHRVC" // 邮箱授权密码
  }
});
//发送邮件
router.post('/sendemail', (req, res) => {
  // 随机生成的6位数字 验证码
  let msgcode = funcUtils.MathRand2() || 888888
  // 设置邮箱内容、收件人邮箱地址
  let mailOptions = {
    from: "wq18252718866@163.com", // 发件地址
    to: req.body.email, // 收件地址
    subject: "仿微信网页版聊天H5", // 标题
    html: `
      <b>thanks a for visiting 仿微信网页版聊天H5!</b>
      </br>您的邮箱验证码为：${msgcode}  <span style="color:green;font-weight:bold;font-size:16px;margin-left:15px;">十分钟内有效</span>
      `
  }
  // 判断 用户输入的 验证码 和 随机生成的数字 是否一致
  if (!req.body.email.includes("@")) {
    return res.json({ code: 100, msg: '邮箱格式不正确' })
  }
  transport.sendMail(mailOptions, function (error, response) {
    if (error) {
      return res.json({ code: 100, msg: '验证码发送失败' })
    } else {
      // 发送成功后 插入到集合
      let obj = {email:req.body.email,code:msgcode}
      insertCode(obj).then(codeRes=>{
        console.log('结果：',codeRes)
        return res.json({ code: 200, msg: '成功发送验证码', msgcode: msgcode })
      }).catch(err=>{
        // return res.json(err)
      })
    }
    transport.close(); // 如果没用，关闭连接池
  });
})
router.get('/email', (req, res) => {
  res.render("email");
})

// 机器人聊天
router.get('/robot', function (req, res) {
  let datas = {
    key: 'free',
    appid: 0,
    msg: req.query.content
  };
  // http://api.qingyunke.com/api.php?key=free&appid=0&msg=你好
  axios.get('http://api.qingyunke.com/api.php', {
    params: datas
  })
    .then(function (response) {
      // console.log(response.data)
      return res.json(response.data)
    })
    .catch(function () {
      return res.json({
        "result": 500,
        "content": "机器人出问题了~~"
      })
    });
});

// 更换头像（上传图片）
router.get('/upload', (req, res) => {
  res.render("upload");
})

// 上传单张
// router.post('/doUpload',upload.single('pic'),(req,res,next) => {
//   // req.file 是 `avatar` 文件的信息
//   // req.body 将具有文本域数据，如果存在的话
//   res.json(req.file)
//   /**
//    *  {
//         fieldname: 'pic',
//         originalname: 'vue.jpg',
//         encoding: '7bit',
//         mimetype: 'image/jpeg',
//         destination: 'uploads/',
//         filename: 'e2f5cecdaa7b1539a0f547e8be6cc9e6',
//         path: 'uploads\\e2f5cecdaa7b1539a0f547e8be6cc9e6',
//         size: 2828
//       }
//    */
// })

// 上传多张
var cpUpload = upload.fields([{ name: 'pic1', maxCount: 1 }, { name: 'pic2', maxCount: 1 }])
router.post('/doUpload', cpUpload, function (req, res, next) {
  // req.files 是一个对象 (String -> Array) 键是文件名，值是文件数组
  console.log(req.files)
})

module.exports = router;
