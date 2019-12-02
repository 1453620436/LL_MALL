import axios from 'axios';
import store from '../store';
import {getToken } from '@/utils/auth';

export const baseURL = process.env.baseURL;
// 创建axios实例
const service = axios.create({
 // baseURL: baseURL, //node环境的不同，对应不同的baseURL
  timeout: 15000 ,//请求超时时间
  //设置默认请求头，使post请求发送的是formdata格式数据
  //axios的header默认的Content-Type好像是'application/json;charset=UTF-8'
  headers: {
    "Content-Type": "application/json;charset=UTF-8"
  },
  withCredentials: true // 允许携带cookie
});

// request拦截器
service.interceptors.request.use(config => {
  if (store.getters.token) {
    config.headers['Authorization'] = getToken(); // 如果前端有token，我每次都把token给后端，后端拿到这个token后会到后端再进行一次权限校验
  }
  return config
}, error => {
  // Do something with request error
  console.log(error); // for debug
  Promise.reject(error);
});

// respone拦截器（处理响应数据）
service.interceptors.response.use(response => {
    const res = response.data;
    if (res.code !== 200) {//操作失败
      Message({
        message: res.message,
        type: 'error',
        duration: 3 * 1000
      })

      // 401:未登录;
      if (res.code === 401||res.code === 403) {
        MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload()// 为了重新实例化vue-router对象 避免bug
          });
        });
      }
      return Promise.reject('error')
    } else {
      //返回查询结果集
      return response.data;
    }
  },
  error => {
    console.log('err' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 3 * 1000
    });
    return Promise.reject(error)
  }
);

export default service
