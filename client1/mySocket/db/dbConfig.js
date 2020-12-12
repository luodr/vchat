var mongoose = require('mongoose'),
    DB_URL = 'mongodb://localhost:27017/learndb';

/**
 * 连接
 */
mongoose.connect(DB_URL, { useNewUrlParser: true, useUnifiedTopology: true });

/**
  * 连接成功
  */
mongoose.connection.on('connected', function () {
    console.log('Mongoose 连接成功 ' + DB_URL);
});

/**
 * 连接异常
 */
mongoose.connection.on('error', function (err) {
    console.log('Mongoose 连接失败: ' + err);
});

/**
 * 连接断开
 */
mongoose.connection.on('disconnected', function () {
    console.log('Mongoose 连接断开了');
});

//将 mongoose 暴露出去
module.exports = mongoose;