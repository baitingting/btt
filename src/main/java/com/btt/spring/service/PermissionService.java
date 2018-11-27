package com.btt.spring.service;

public interface PermissionService {
	/**
	 * 查询用户所拥有的权限(根据用户Id)
	 * 
	 * @param id
	 * @return
	 */
	public String queryPermissionValueByUserId(Integer id);

}
