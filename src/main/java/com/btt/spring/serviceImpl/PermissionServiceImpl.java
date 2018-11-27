package com.btt.spring.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btt.spring.dao.PermissionRepository;
import com.btt.spring.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository repository;

	/**
	 * 查询用户所拥有的权限(根据用户Id)
	 */
	@Override
	public String queryPermissionValueByUserId(Integer uid) {
		// TODO Auto-generated method stub
		return repository.queryPermissionValueByUserId(uid);
	}

}
