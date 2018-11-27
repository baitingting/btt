package com.btt.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.btt.spring.entity.sys.Module;
import com.btt.spring.entity.sys.Role;

public interface RoleService {
	/**
	 * 角色分页+多条件
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Role> queryRoleNameLikeAllPage(String name, Integer page, Integer size);

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	public void insertRole(Role role);

	/**
	 * 修改角色
	 * 
	 * @param role
	 */
	public void updateRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param rid
	 */
	public void deleteRole(Integer rid);

	/**
	 * 根据用户Id查询该用户拥有的角色信息
	 * 
	 * @param uid
	 * @return
	 */
	public List<Role> queryRoleByuid(Integer uid);

	/**
	 * 根据用户Id查询该用户mei拥有的角色信息
	 * 
	 * @param uid
	 * @return
	 */
	public List<Role> queryNOTRoleByuid(Integer uid);

	/**
	 * 查询角色已经拥有的模块
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Module> queryRoleModuleRoleId(Integer roleId);

	/**
	 * 根据角色Id清除角色模块关系
	 * 
	 * @param roleId
	 * @return
	 */
	public int deleteRoleModuleByRoleId(Integer roleId);

	/**
	 * 保存角色模块
	 * 
	 * @param roleId
	 * @param moduleId
	 * @return
	 */
	public int saveRoleMole(Integer roleId, Integer moduleId);
}
