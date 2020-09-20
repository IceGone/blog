package com.icegone.spring.boot.blog.controller.knowledge;

import com.alibaba.fastjson.JSONObject;
import com.icegone.spring.boot.blog.vo.general.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Icegone
 * @date 2019/6/18
 */
@Controller
@RequestMapping(value = "/knowledge/spring")
public class Spring {
    /**@description: 使用redis 工具*/
    @Autowired
    private StringRedisTemplate redis;

    /**
     * @description: 跳转spring主页
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/6/18
     */
    @RequestMapping(value = "/")
    public String index(){
        return "knowledge/spring/springIndex";
    }

    /**
     * @description: springSessionShare
     * @param: []
     * @return: java.lang.String
     * @author: icegone
     * @date: 2019/6/18
     */
    @RequestMapping(value = "/springSessionShare")
    public String springSessionShare(){

        return "knowledge/spring/springSessionShare";
    }
    @Value("${server.port}")
    Integer port;

    /**
     * @description: 设置session
     * @param: [session]
     * @return: com.icegone.spring.boot.blog.vo.general.ResultMap
     * @author: icegone
     * @date: 2019/6/18
     */
    @RequestMapping(value = "/setSession")
    @ResponseBody
    public ResultMap setSession(HttpServletRequest request, String jsonData ){
        ResultMap resultMap = new ResultMap();
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        String sessionName = jsonObject.getString("sessionName");
        String sessionValue = jsonObject.getString("sessionValue");
        //存session
        request.getSession().setAttribute(sessionName,sessionValue);
        //存入redis
//        redis.opsForValue().set(sessionName,sessionValue);
        resultMap.setMessage("保存入session成功");
        return resultMap;
    }

    /**
     * @description: 获取session
     * @param: [session]
     * @return: com.icegone.spring.boot.blog.vo.general.ResultMap
     * @author: icegone
     * @date: 2019/6/18
     */
    @RequestMapping(value = "/getSession")
    @ResponseBody
    public ResultMap getSession(HttpServletRequest request,String jsonData){
        ResultMap resultMap = new ResultMap();
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        String sessionName = jsonObject.getString("sessionName");
        String sessionValue = (String)request.getSession().getAttribute(sessionName);
        resultMap.setData("port:"+port+" "+sessionValue);
        resultMap.setMessage("获取sessionValue成功！");
        return resultMap;
    }

}
