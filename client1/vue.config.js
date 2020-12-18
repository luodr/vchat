/*
 * @Description: 
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-06 09:33:42
 * @LastEditors: 
 * @LastEditTime: 2020-07-21 10:28:50
 */ 
const Config = require("./src/utils/config.js");
const path = require("path");
// 拼接路径
const resolve = dir => require('path').join(__dirname, dir)
let publicPath = process.env.NODE_ENV === 'production' ? '/test' : './'
module.exports = {
    publicPath: '/',
    outputDir: 'dist',
    productionSourceMap: false,

    lintOnSave: false, //关闭 eslint
    devServer: {
        port: 9527, // 端口号
        https: false, // https:{type:Boolean}
        open: true, //配置自动启动浏览器
        proxy: {
                "^/api": {
                  target: "http://localhost:8888/",
                  // ws: true,
                  // changeOrigin: true
                  pathRewrite: {
                    api: "api"
                  }
            },
            "/upload": {
                target: Config.domain+'users/doUpload',
                changeOrigin: true,
                pathRewrite: {
                    "^/upload": ""
                }
            }
            // "/jqr": {
            //     target: 'http://openapi.tuling123.com',
            //     changeOrigin: true,
            //     pathRewrite: {
            //         "^/jqr": ""
            //     }
            // }
        }
    },
    chainWebpack: config => {
        // 重新设置 alias
        config.resolve.alias
            .set('@', resolve('src'))
            .set('@view', resolve('src/page'))

        // 配置使用stylus全局变量
        const types = ["vue-modules", "vue", "normal-modules", "normal"];
        types.forEach(type =>
            addStyleResource(config.module.rule("stylus").oneOf(type))
        );
    },
    // css: {
    //     loaderOptions: {
    //         // 设置 stylus 公用变量文件
    //         sass: {
    //             prependData: `@import '~@/common/style.scss';`
    //         },
    // 给 stylus-loader 传递选项
    // stylus: {
    //     import: '~@/common/stylus/color.styl'
    // }
    //     }
    // },
    // CDN配置
    // configureWebpack: {
    //     externals: {
    //         'vue': 'Vue',
    //         'vuex': 'Vuex',
    //         'vue-router': 'VueRouter',
    //         'axios': 'axios'
    //     }
    // }
}

// ====定义函数addStyleResource===
function addStyleResource(rule) {
    rule
        .use("style-resource")
        .loader("style-resources-loader")
        .options({
            patterns: [path.resolve(__dirname, "./src/common/stylus/varibles.styl")] //后面跟着的路径改成你自己放公共stylus变量的路径
        });
}