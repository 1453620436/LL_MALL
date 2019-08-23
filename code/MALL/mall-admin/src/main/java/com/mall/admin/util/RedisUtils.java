package com.mall.admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component //把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
public class RedisUtils {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的key
     * @param keys
     */
    public void  remove(final String ...keys){
        for (String key:keys){
            remove(key);
        }
    }

    /**
     * 删除对应的key
     * @param key
     */
    public void  remove(final String key){
       if(exists(key)){
           redisTemplate.delete(key);
       }
    }

    /**
     * 判断对应的key是否存在
     * @param key
     * @return
     */
    public boolean exists (final String key){
        return  redisTemplate.hasKey(key);
    }

    /**
     * 根据key读取缓存
     * @param key
     * @return
     */
    public Object get(final  String key){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 写入redis
     * @param key
     * @param value
     */
    public boolean set(final String key,Object value){
        boolean result = false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存并设置缓存时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key,Object value,Long expireTime){
        boolean result = false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);//存入数据
            redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);//存入时间（以分钟为单位）
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
}
