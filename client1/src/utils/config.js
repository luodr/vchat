/*
 * @Description: 环境选择
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-08 13:24:34
 * @LastEditors: 
 * @LastEditTime: 2020-07-22 11:16:45
 */ 

let Config = {
	official: 3,
	domin: '',
	upload:''	//上传文件使用
}
/*
 *  1 表示正式环境 
 *  2 表示测试环境
 *  3 表示本地环境
 */

if (Config.official === 1) {
	Config.domain = "https://xxxxxxx"; 			// 正式
	Config.upload = "http://xxxx:8888/";		// 本地
} else if (Config.official === 2) {
	Config.domain = "http://xxxxxx"   			// 测试
	Config.upload = "http://xxxx:8888/";		// 测试
} else if(Config.official === 3) {
	Config.domain = "http://localhost:8888/";	// 本地
	Config.upload = "http://localhost:8888/";	// 本地
}

module.exports = Config