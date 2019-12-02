import request from "@/utils/request";
import { getToken, setToken, removeToken } from '@/utils/auth'

//用户登录
export function login(username, password) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data: {
      username,
      password
    }
  });
}

//用户注册
export function register(data){
  return request({
     url: '/admin/user/register',
     method:'post',
     data:data
  });
}
