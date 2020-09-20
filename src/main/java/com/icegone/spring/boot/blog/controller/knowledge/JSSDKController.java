package com.icegone.spring.boot.blog.controller.knowledge;

import com.alibaba.fastjson.JSON;
import com.icegone.spring.boot.blog.domain.WechatJssdkInfo;
import com.icegone.spring.boot.blog.service.WechatJssdkInfoService;
import com.icegone.spring.boot.blog.util.wechat.WechatUtil;
import com.icegone.spring.boot.blog.vo.general.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Icegone
 * @date 2019/6/1
 */
@Controller
@RequestMapping(value = "/knowledge/jsSDK")
public class JSSDKController {
    @Autowired
    private WechatJssdkInfoService wechatJssdkInfoService;

    /**
     * @description: 跳转jsSDJ首页
     * @param: []
     * @return: void
     * @author: icegone
     * @date: 2019/6/1
     */
    @RequestMapping(value = "/")
    public String goIndex(){
        return "knowledge/jsSDK/jssdkIndex";
    }

    /**
     * @description: 获取微信签名
     * @param: [url]
     * @return: ResultMap
     * @author: icegone
     * @date: 2019/6/1
     */
    @RequestMapping("/getJsSignature")
    @ResponseBody
    public ResultMap getJsSignature(String url){
        ResultMap resultMap=new ResultMap();
        Map<String, String> jsSignatureMap = WechatUtil.getJsSignature(url);
        resultMap.setSuccess(1);
        resultMap.setData(jsSignatureMap);
        return resultMap;
    }
    /**
     * @description: 保存数据
     * @param: [url]
     * @return: ResultMap
     * @author: icegone
     * @date: 2019/6/1
     */
    @RequestMapping(value = "/svaeDate")
    public String svaeDate(HttpServletRequest request,String jsonData){
        WechatJssdkInfo wechatJssdkInfo = JSON.parseObject(jsonData,WechatJssdkInfo.class);
        wechatJssdkInfoService.save(wechatJssdkInfo);
        return "knowledge/jsSDK/jssdkIndex";
    }

}
