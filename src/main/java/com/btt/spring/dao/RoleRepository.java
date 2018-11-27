package com.btt.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.btt.spring.entity.sys.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	// 角色分页+多条件
	public Page<Role> findByRoleNameLike(String name, Pageable pageable);

	// 根据用户Id查询该用户拥有的角色信息
	@Query(value = "SELECT * FROM roletb WHERE role_id IN  (SELECT user_role_tb_role_id FROM user_role_tb WHERE user_role_tb_user_id=?1)", nativeQuery = true)
	public List<Role> queryRoleByuid(Integer uid);

	// 根据用户Id查询该用户mei拥有的角色信息
	@Query(value = "SELECT * FROM roletb WHERE role_id NOT IN (SELECT user_role_tb_role_id FROM user_role_tb WHERE user_role_tb_user_id=?1)", nativeQuery = true)
	public List<Role> queryNOTRoleByuid(Integer uid);

	// 根据角色Id查询角色模块
	@Query(value = "SELECT role_module_tb_module_id FROM role_module_tb WHERE role_module_tb_role_id=?1", nativeQuery = true)
	List<Integer> queryRoleModuleByRoleId(Integer roleId);

	// 根据角色Id清除角色模块关系
	@Query(value = "DELETE FROM role_module_tb WHERE role_module_tb_role_id = ?1", nativeQuery = true)
	@Modifying
	@Transactional
	public int deleteRoleModuleByRoleId(Integer roleId);

	// 保存角色模块
	@Query(value = "INSERT INTO role_module_tb (role_module_tb_module_id,role_module_tb_role_id)VALUES(?2,?1)", nativeQuery = true)
	@Modifying
	@Transactional
	public int saveRoleMole(Integer roleId, Integer moduleId);
}
