/**
 * 用户信息
 */
let mongoose = require('../dbConfig.js'),
    Schema = mongoose.Schema;

let UserSchema = new Schema({
    socketid: { type: String,default:'' },	// socket id
    img: { type: String, default: 'http://lc-00SHgiOJ.cn-n1.lcfile.com/df7b90871188e32060f8/%E5%A4%A7%E9%A3%9E%E5%93%A5.jpg'},	// 头像
    username: { type: String,required: true },     //用户账号
    pwd: { type: String,required: true },          //密码
    age: { type: Number , default:18 },            //年龄
    email: { type: String },                       //邮箱
    logintime: { type: Date,default: Date.now },    //最近登录时间
    onlineStatus: { type: Number, default: 2 }
},{
    collection: 'users',
  });

module.exports = mongoose.model('userModel',UserSchema);

