package com.icegone.spring.boot.blog.service;

import com.icegone.spring.boot.blog.domain.WechatJssdkInfo;

import java.util.List;

/**
 * @author Icegone
 * @date 2019/6/1
 */
public interface WechatJssdkInfoService {
    /**
     * @description: 保存数据入库
     * @return: java.util.List<com.icegone.spring.boot.blog.domain.WechatJssdkInfo>
     * @author: icegone
     * @date: 2019/6/1
     */
    WechatJssdkInfo save( WechatJssdkInfo wechatJssdkInfo);
}
