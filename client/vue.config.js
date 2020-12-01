const path = require("path");
function resolve(dir) {
  return path.join(__dirname, "", dir);
}
module.exports = {
  pages: {
    index: {
      entry: "src/main.js",
      template: "public/index.html",
      filename: "index.html"
    }
  },
  publicPath: "",
  productionSourceMap: false,
  configureWebpack: {
    resolve: {
      alias: {
        components: resolve("src/components"),
        mixins: resolve("src/mixins"),
        styles: resolve("src/styles"),
        utils: resolve("src/utils")
      }
    }
  },
  devServer: {
    proxy: {
      "^/api": {
        target: "http://localhost:8888/",
        // ws: true,
        // changeOrigin: true
        pathRewrite: {
          api: "api"
        }
      }
    }
  }
};
