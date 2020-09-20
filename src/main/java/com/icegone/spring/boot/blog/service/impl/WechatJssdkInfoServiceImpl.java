package com.icegone.spring.boot.blog.service.impl;

import com.icegone.spring.boot.blog.domain.WechatJssdkInfo;
import com.icegone.spring.boot.blog.repository.WechatJssdkInfoRepository;
import com.icegone.spring.boot.blog.service.WechatJssdkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Icegone
 * @date 2019/6/1
 */
@Service
public class WechatJssdkInfoServiceImpl implements WechatJssdkInfoService {
    @Autowired
    private WechatJssdkInfoRepository wechatJssdkInfoRepository;

    @Override
    public WechatJssdkInfo save(WechatJssdkInfo wechatJssdkInfo) {
        return wechatJssdkInfoRepository.save(wechatJssdkInfo);
    }
}
