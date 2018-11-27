package com.btt.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.btt.spring.entity.sys.Permission;

public interface PermissionRepository extends
		JpaRepository<Permission, Integer> {
	// 查询用户所拥有的权限(根据用户Id)
	@Query(value = "SELECT permissionValue FROM permissiontb WHERE permissionId "
			+ "IN(SELECT permissionId FROM rolepermissiontb WHERE roleId IN"
			+ "(SELECT roleId FROM userroletb WHERE userId = ?1))", nativeQuery = true)
	public String queryPermissionValueByUserId(Integer id);
}
