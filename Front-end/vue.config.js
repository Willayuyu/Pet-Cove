module.exports = {
    devServer: {
      proxy: {
        '/api': {
          target: 'http://localhost:8080', // replace with your backend API URL
          changeOrigin: true,
          pathRewrite: {
            '^/api': ''
          }
        }
      }
    }
  };
  