module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "./" : "/",
  outputDir: "dist",
  assetsDir: "assets",
  productionSourceMap: false,
  filenameHashing: false,
  lintOnSave: true,
  /* webpack-dev-server configuration */
  devServer: {
    open: false,
    port: 8080,
    https: false,
    hotOnly: false,
    proxy: {
      "/user": {
        target: "http://localhost:8080", 
        secure: false, 
        ws: true, 
        proxyTimeout: 60000,
        changeOrigin: true,
        pathRewrite: {
          "^/user": "/",
        },
      },
      "/api": {
        target: "http://localhost:8080", 
        secure: false, 
        ws: true, 
        proxyTimeout: 60000,
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/api",
        },
      },
    },
  },
};
