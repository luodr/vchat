/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
    if (!name) return;
    if (typeof content !== 'string') {
        content = JSON.stringify(content);
    }
    window.localStorage.setItem(name, content);
}

/**
 * 获取localStorage
 */
export const getStore = name => {
    if (!name) return;
    return window.localStorage.getItem(name);
}

/**
 * 删除localStorage
 */
export const removeStore = name => {
    if (!name) return;
    window.localStorage.removeItem(name);
}

/**
 * 函数防抖
 */
export const debounce = (fn, delay) => {
    // 记录上一次的延时器
    var timer = null;
    var delay = delay || 200;
    return function () {
        var args = arguments;
        var that = this;
        // 清除上一次延时器
        clearTimeout(timer)
        timer = setTimeout(function () {
            fn.apply(that, args)
        }, delay);
    }
}

/**
 * 函数节流
 */
export const throttle = (fn, delay) => {
    var lastTime;
    var timer;
    var delay = delay || 200;
    return function () {
        var args = arguments;
        // 记录当前函数触发的时间
        var nowTime = Date.now();
        if (lastTime && nowTime - lastTime < delay) {
            clearTimeout(timer);
            timer = setTimeout(function () {
                // 记录上一次函数触发的时间
                lastTime = nowTime;
                // 修正this指向问题
                fn.apply(this, args);
            }, delay);
        } else {
            lastTime = nowTime;
            fn.apply(this, args);
        }
    }
}

/*
 * 日期格式化
 * 使用方式：
 *  let date = new Date()
 *  dateFormat("YYYY-mm-dd HH:MM:SS", date)
 *  2019-06-06 19:45:11
 *  let date1 = new Date()
 *  dateFormat("YYYY/mm/dd HH:MM:SS", date1)
 *  2019/11/08 16:41:24
 */
export const dateFormat = (fmt, date) => {
    let ret;
    let opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}

/*
 * 时间戳转化为日期格式
 * 参数一：时间戳
 * 参数二：分隔符 如'/','-'
 * timeStampTurnTime(new Date().getTime()/1000)
 * 2019-11-08 16:52:42
 * timeStampTurnTime(new Date().getTime()/1000,'/')
 * 2019/11/08 16:52:42
 */
export const timeStampTurnTime = (timeStamp, fmt = '-') => {
    if (timeStamp > 0) {
        var date = new Date();
        date.setTime(timeStamp * 1000);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + fmt + m + fmt + d + ' ' + h + ':' + minute + ':' + second;
    }
}


export function timeFormat(timestamp = null, fmt = 'yyyy-mm-dd') {
    // 其他更多是格式化有如下:
    // yyyy:mm:dd|yyyy:mm|yyyy年mm月dd日|yyyy年mm月dd日 hh时MM分等,可自定义组合
    timestamp = parseInt(timestamp);
    // 如果为null,则格式化当前时间
    if (timestamp == null) timestamp = Number(new Date());
    // 判断用户输入的时间戳是秒还是毫秒,一般前端js获取的时间戳是毫秒(13位),后端传过来的为秒(10位)
    if (timestamp.toString().length == 10) timestamp *= 1000;
    let date = new Date(timestamp);
    let ret;
    let opt = {
        "y+": date.getFullYear().toString(), // 年
        "m+": (date.getMonth() + 1).toString(), // 月
        "d+": date.getDate().toString(), // 日
        "h+": date.getHours().toString(), // 时
        "M+": date.getMinutes().toString(), // 分
        "s+": date.getSeconds().toString() // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}

/**
 * 时间戳转为多久之前
 * @param String timestamp 时间戳
 * @param String | Boolean format 如果为时间格式字符串，超出一定时间范围，返回固定的时间格式；
 * 如果为布尔值false，无论什么时间，都返回多久以前的格式
 */
export function timeFrom(timestamp = null, format = 'yyyy-mm-dd') {
    if (timestamp == null) timestamp = Number(new Date());
    timestamp = parseInt(timestamp);
    // 判断用户输入的时间戳是秒还是毫秒,一般前端js获取的时间戳是毫秒(13位),后端传过来的为秒(10位)
    if (timestamp.toString().length == 10) timestamp *= 1000;
    var timer = (new Date()).getTime() - timestamp;
    timer = parseInt(timer / 1000);
    // 如果小于5分钟,则返回"刚刚",其他以此类推
    let tips = '';

    switch (true) {
        case timer < 300:
            tips = '刚刚';
            break;
        case timer >= 300 && timer < 3600:
            tips = parseInt(timer / 60) + '分钟前';
            break;
        case timer >= 3600 && timer < 86400:
            tips = parseInt(timer / 3600) + '小时前';
            break;
        case timer >= 86400 && timer < 2592000:
            tips = parseInt(timer / 86400) + '天前';
            break;
        default:
            // 如果format为false，则无论什么时间戳，都显示xx之前
            if (format === false) {
                if (timer >= 2592000 && timer < 365 * 86400) {
                    tips = parseInt(timer / (86400 * 30)) + '个月前';
                } else {
                    tips = parseInt(timer / (86400 * 365)) + '年前';
                }
            } else {
                tips = timeFormat(timestamp, format);
            }
    }
    return tips;
}

/**
* 格式化时间 下午 08:12 | 昨日 09:12 | 2019-12-03 20:12
* @param {Date} time 输入Unix时间戳
* @return {Object} 格式化结果
*/
export function fnFormatTimeHeader(time) {
    // 格式化传入时间
    let date = new Date(parseInt(time)),
        year = date.getUTCFullYear(),
        month = date.getUTCMonth(),
        day = date.getDate(),
        hour = date.getHours(),
        minute = date.getUTCMinutes()
    // 获取当前时间
    let currentDate = new Date(),
        currentYear = date.getUTCFullYear(),
        currentMonth = date.getUTCMonth(),
        currentDay = currentDate.getDate()
    // 计算是否是同一天
    if (currentYear == year && currentMonth == month && currentDay == day) { //同一天直接返回
        if (hour > 12) {
            return `下午 ${hour}:${minute < 10 ? '0' + minute : minute}`
        } else {
            if (hour >= 10) {
                return `上午 ${hour}:${minute < 10 ? '0' + minute : minute}`
            } else {
                return `上午 0${hour}:${minute < 10 ? '0' + minute : minute}`
            }

        }
    }
    // 计算是否是昨天
    let yesterday = new Date(currentDate - 24 * 3600 * 1000)
    // console.log('123', day , yesterday.getDate())
    // console.log(month , yesterday.getUTCMonth())
    if (year == yesterday.getUTCFullYear() && month == yesterday.getUTCMonth() && day == yesterday.getDate()) { //昨天
        return `昨天 ${hour >= 10 ? hour : '0' + hour}:${minute < 10 ? '0' + minute : minute}`
    } else {
        return `${year}-${month + 1}-${day} ${hour}:${minute < 10 ? '0' + minute : minute}`
    }
}