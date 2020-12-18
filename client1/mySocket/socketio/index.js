/*
 * @Description:  封装socket.io
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-09 08:51:29
 * @LastEditors: 
 * @LastEditTime: 2020-07-17 17:01:07
 */

const socket_io = require('socket.io');
const socketio = {};
const users = [];       // 用户集合

//获取io  
socketio.getSocketio = function (server) {
    // const io = socket_io.listen(server);
    // io.sockets.on('connection', function (socket) {
    //     console.log('用户连接成功了',socket.id);
    //     // socket.on('userlogins', function (data) {
    //     //     socketio.name = socket.id
    //     //     data.user.socketid = socket.id   // 赋值 socketid
    //     //     data.user.onlineStatus = 1       // 改变用户在线状态
    //     //     users.push(data);
    //     //     console.log(data,'用户数据')
    //     //     socket.emit('getUsers', data);
    //     // })
    //     socket.emit('getUserSocketID', socket.id)
    // })
};

module.exports = socketio;  