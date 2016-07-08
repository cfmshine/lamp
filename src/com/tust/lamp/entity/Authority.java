package com.tust.lamp.entity;

import java.util.ArrayList;
import java.util.List;

public class Authority extends IdEntity {

	// 权限的名字, 用于显示
	private String displayName;
	// 权限的名字: 用于配置 Shiro
	private String name;
	// 父权限
	private Authority parentAuthority;
	// URL 地址
	private String url;
	// 子权限
	private List<Authority> subAuthorities = new ArrayList<>();

	public Authority(Integer id) {
		this.id = id;
	}

	public Authority() {
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authority getParentAuthority() {
		return parentAuthority;
	}

	public void setParentAuthority(Authority parentAuthority) {
		this.parentAuthority = parentAuthority;
	}

	public List<Authority> getSubAuthorities() {
		return subAuthorities;
	}

	public void setSubAuthorities(List<Authority> subAuthorities) {
		this.subAuthorities = subAuthorities;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
