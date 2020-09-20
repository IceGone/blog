package com.icegone.spring.boot.blog.util;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * @author Icegone
 * @date 2019/6/1
 */
public class PropertiesUtil {
    private final String file;
    private static Properties props;
    /**
     * 构造函数
     * @param propertiesName 文件名称,须带“/”，格式如："/thresholds.properties"
     */
    public PropertiesUtil(String propertiesName){
        file = propertiesName;
        if(props == null){
            props = new Properties();
        }
        InputStreamReader in;
        try {
            in = new InputStreamReader(PropertiesUtil.class.getResourceAsStream(propertiesName),"utf-8");
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Key查询键值
     * @param key
     * @return
     */
    public String getProValue(String key){
        return props.getProperty(key);
    }

}
