const express = require('express');
const router = express.Router();
const md5 = require('md5');
const path = require('path');
const sd = require('silly-datetime');
const mkdirp = require('mkdirp');

const multer = require('multer')
let storage = multer.diskStorage({
  destination: async (req, file, cb) => {
    let day = sd.format(new Date(), 'YYYYMDD');
    let dir = path.join('public/uploads/', day);
    // 这是一个异步操作
    await mkdirp(dir);
    cb(null, dir)   // 上传前目录必须存在
  },
  filename: function (req, file, cb) {
    let extname = path.extname(file.originalname);	 //获取文件的后缀名
    let originname = file.originalname.split('.')[0]
    let filepath = originname + Date.now() + extname
    cb(null, filepath)
  }
})

let upload = multer({ storage: storage })

// 引入用户模型
let userModel = require('../db/schemas/users');
// 引入验证码模型
let codeModel = require('../db/schemas/msgcode');

/**********************************用户相关********************************* */
/**
 * 插入 单条数据 / 多条数据
 * params: data 可是是对象或者数组
 * 调用方式：insert({username:'张三', pwd: md5('123456'), email: '2825225@qq.com'})
 */
function insert(data) {
  return userModel.create(data)
}

/**
 * 查询
 * condition ：查询条件 可以使用正则表达式
 * options 表示 需要查找的字段 1需要展示 0不需要展示  没写的默认不查找
 * limit 可以用来分页
 */
function findAll(condition = {}, options = {}, limit = null) {
  return userModel.find(condition, options, limit)
}

/*
 * 查询单条数据 
 */
function findOne(whereStr) {
  return userModel.findOne(whereStr)
}

/*
 * 修改单条数据
 */

function updateUser(whereStr1, whereStr2) {
  return userModel.updateOne(whereStr1, { $set: whereStr2 })
}

// 根据id来修改--返回修改后数据
function updateUsertoID(id, whereStr) {
  return userModel.findByIdAndUpdate(id, whereStr, { new: true })
}
/**********************************用户相关********************************* */

/**********************************验证码********************************* */
function deleteAllMsgcode(whereStr) {
  return codeModel.remove(whereStr)
}

// 查找所有用户
router.get("/findUser", (req, res) => {
  findAll().then((data) => {
    // findAll({username:/王/},{ username:1,email:1,_id:0 }).then((data) => {
    // findAll({},{ username:1,email:1,_id:0 },{limit:3,skip:2}).then((data) => {
    res.json(data)
  }).catch((err) => {
    console.log(err);
  });
});

// 用户注册
router.post("/register", (req, res) => {
  console.log(req.body, 1111)
  let user = {
    username: req.body.username,
    pwd: md5(req.body.pwd),
    email: req.body.email,
    loginTime: new Date()
  }
  // 先判断用户是否存在
  findOne({ email: req.body.email })
    .then(result => {
      if (!result) {
        insert(user).then((data) => {
          // console.log('用户:', data);
          res.json({ code: 200, user: data, msg: '注册成功' })
        }).catch((err) => {
          console.log(err);
        });
      } else {
        res.json({
          code: 100,
          msg: '此邮箱已被占用'
        });
      }

    })
    .catch(err => {
      console.log('err:', err)
    })
});

// 用户登录
router.post("/login", (req, res) => {
  let user = {
    email: req.body.email
  }
  findAll(user).then((data) => {
    let user = data[0]
    if (data.length > 0) {
      // 有这个用户，再去判断用户名 密码
      if (user.pwd !== md5(req.body.pwd)) {
        return res.json({ code: 100, msg: '密码错误' })
      }
      // 验证码处理，用完就清空
      deleteAllMsgcode({
        email: req.body.email
      }).then(code=>{
        // console.log(code)
      }).catch(err=>{
        console.log(err)
      })
      res.json({ code: 200, user, msg: '登录成功' })
    } else {
      res.json({ code: 100, msg: '不存在此用户' })
    }
  }).catch((err) => {
    console.log(err);
  });
});

// 更换头像（上传图片）
router.get('/upload', (req, res) => {
  res.render("upload");
})

// 处理更换头像（方法一）
// router.post('/doUpload', upload.single('pic1'), (req, res) => {
//   // console.log('用户邮箱：',req.body.email)
//   let email = req.body.email
//   let id = req.body.id
//   let path = req.file.path.replace('public\\', '')
//   // 在数据库中替换原来的头像地址
//   let str1 = { email: email }
//   let str2 = { img: path }
//   // 上传到服务器
//   updateUser(str1,str2).then(result=>{
//     console.log(result)
//     res.json({ path: path, code: 200, msg: '上传成功' })
//   })
// })

// 处理更换头像（方法二）
router.post('/doUpload', upload.single('pic1'), (req, res) => {
  // console.log('用户邮箱：',req.body.email)
  let email = req.body.email
  let id = req.body.id
  let path = req.file.path.replace('public\\', '')
  // 在数据库中替换原来的头像地址
  let str1 = { email: email }
  let str2 = { img: path }
  // 上传到服务器
  updateUsertoID(id, str2).then(result => {
    // console.log(result,'返回修改后数据')
    res.json({ path: path, code: 200, msg: '上传成功' })
  })
})

// 发朋友圈--上传图片（多张）或者上传视频
var cpUpload = upload.fields([{ name: 'pic', maxCount: 5 }])
router.post('/mutiUpload', cpUpload, (req, res) => {
  console.log(req.files.pic)
  let pathArr = req.files.pic.map(item => {
    return item.path = item.path.replace('public\\', '')
  })
  res.json({ path: pathArr, code: 200, msg: '上传成功' })
})


//导出去暴露使用
module.exports = router;