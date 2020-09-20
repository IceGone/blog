package com.icegone.spring.boot.blog.repository;

import com.icegone.spring.boot.blog.domain.WechatJssdkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Icegone
 * @date 2019/6/1
 */
public interface WechatJssdkInfoRepository extends JpaRepository<WechatJssdkInfo,Long> {

}
