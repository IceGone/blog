package com.icegone.spring.boot.blog.vo;


import java.io.Serializable;

import com.icegone.spring.boot.blog.domain.Catalog;
/**
* @Description:  catolog值对象
* @Author: icegone
* @Date: 2019/5/5
*/
public class CatalogVO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Catalog catalog;
	
	public CatalogVO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}
