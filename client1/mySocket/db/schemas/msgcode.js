/**
 * 邮箱验证码集合
 */
let mongoose = require('../dbConfig.js'),
    Schema = mongoose.Schema;

let codeSchema = new Schema({
    email: { type: String },                       //邮箱
    sendtime: { type: Date, default: Date.now },   //时间
    code: { type: String }
}, {
    collection: 'msgcode',
});

module.exports = mongoose.model('codeSchema', codeSchema);
