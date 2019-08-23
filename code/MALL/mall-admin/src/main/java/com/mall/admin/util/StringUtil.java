package com.mall.admin.util;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * String字符串工具类
 * Created by macro on 2019/8/23.
 */
public class StringUtil {
    private static final Log LOG = LogFactory.getLog(StringUtil.class);

    /**
     * 私有构造
     */
    private StringUtil(){

    }

    /**
     * 判断字符串是否为null
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return null ==str || "".equals(str);
    }

    /**
     * 判断数组对象是否为null
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object [] obj) {
        return null == obj ||  0 == obj.length;
    }

    /**
     * 判断对象是否为null
     * @param obj
     * @return
     */
    public static  boolean isEmpty(Object obj){
        if(null == obj){
            return true;
        }
        if(obj instanceof  String){
            return obj.toString().trim().isEmpty();
        }
        return !(obj instanceof  Number) ? false :true;
    }

    /**
     * 判断集合是否为null
     * @param list
     * @return
     */
    public static  boolean isEmpty(List<?> list){
        return null == list || list.isEmpty();
    }

    /**
     * 判断map集合是否为null
     * @param map
     * @return
     */
    public static  boolean isEmpty(Map<?,?> map){
        return  null == map || map.isEmpty();
    }

    /**
     * 判断一个字符串是否由数字组成
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        if (StringUtils.isBlank(str)){
            return false;
        }else {
            return str.matches("\\d*");
        }
    }


}
