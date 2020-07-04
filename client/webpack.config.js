const path = require("path");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const webpack =  require('webpack');

module.exports = {
  mode: 'production',
  devtool: 'source-map',
  entry: "./src/index.js",
  output: {
    path: path.join(__dirname, "../src/main/resources/static/"),
    filename: "js/bundle.[hash].js"
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)?$/,
        exclude: /node_modules/,
        resolve: {
          extensions: [".jsx", ".js", ".json"]
        },
        use: {
          loader: "babel-loader",
        },
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"]
      },
      {
        test: /\.svg$/,
        loader: 'svg-inline-loader'
      }
    ]
  },
  plugins: [
    new webpack.ProgressPlugin(),
    new CleanWebpackPlugin(),
    new HtmlWebpackPlugin(
        {
          title: "Events",
          meta: {
            'viewport' : "width=device-width, initial-scale=1",
            'theme-color' : "#000000"
          },
          filename: "index.html",
          template: "./index.html",
          inject: true
        }
    ),
    new CopyPlugin(
        {
          patterns: [
            {
              from: 'src/*.svg',
              to: 'dest/',
              transform(content, path) {
                return Promise.resolve(content);
              },
            },
          ],
        }
    ),
  ]
};