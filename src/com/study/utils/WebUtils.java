package com.study.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * ClassName: WebUtils
 * Description:
 * date: 2021/8/3 19:27
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class WebUtils {
    public static <T> T CopyParamToBean(T bean,Map value){
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * @Author Quensty
     * @Description 将String类型转化为int类型
     * @Date 13:21 2021/8/4
     * @param strInt
     * @param defaultValue
     * @return int
     **/
    public static int parseInt(String strInt,int defaultValue ){
        try {
            if(strInt != null && !"".equals(strInt)){
                return Integer.parseInt(strInt);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
