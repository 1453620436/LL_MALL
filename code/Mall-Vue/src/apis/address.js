import request from "@/utils/request";
//import qs from 'qs';

//新增收货地址
export function addAddress(data){
  return request({
    url:'/admin/shopAddress/insertShopAddress',
    method:'post',
    data:data
  });
}

//查询收货地址
export function getAddress(idCard){
  return request({
    url:'/admin/shopAddress/getAddress',
    method:'post',
    data:idCard
  });
}

//修改收货地址
export function updateAddress(data){
  return request({
    url:'/admin/shopAddress/updateShopAddress',
    method:'post',
    data:data
  });
}

//删除收货地址
export function deleteAddress(data){
  return request({
    url:'/admin/shopAddress/deleteShopAddress',
    method:'post',
    data:data
  });
}
