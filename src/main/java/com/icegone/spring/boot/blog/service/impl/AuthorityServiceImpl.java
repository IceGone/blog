/**
 * 
 */
package com.icegone.spring.boot.blog.service.impl;

import com.icegone.spring.boot.blog.repository.AuthorityRepository;
import com.icegone.spring.boot.blog.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icegone.spring.boot.blog.domain.Authority;

/**
 * Authority 服务.
 */
@Service
public class AuthorityServiceImpl  implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
