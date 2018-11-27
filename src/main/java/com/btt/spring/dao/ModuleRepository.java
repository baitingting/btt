package com.btt.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.btt.spring.entity.sys.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

	// 根据用户Id查出该用户所属模块
	@Query(value = "SELECT * FROM moduletb  WHERE parent_id=:parentId AND module_id IN(SELECT role_module_tb_module_id FROM role_module_tb WHERE role_module_tb_role_id IN(SELECT user_role_tb_role_id FROM user_role_tb WHERE user_role_tb_user_id=:userId))", nativeQuery = true)
	public List<Module> queryModuleByUserId(
			@Param("parentId") Integer parentId, @Param("userId") Integer userId);

	/**
	 * 模糊查询模块
	 * 
	 * @param moduleName
	 * @return
	 */

	public List<Module> findByModuleNameLike(String moduleName);

	/**
	 * 查询模块表格 TreeGrid 根据ID查询出所有孩子
	 * 
	 * @param parentId
	 * @return
	 */
	@Query(value = "SELECT * FROM moduletb WHERE parent_id =?1  order by module_weight desc", nativeQuery = true)
	public List<Module> queryTreeGridChildrenById(Integer parentId);

	/**
	 * 根据ID查询出所有孩子
	 * 
	 * @param parentId
	 * @return
	 */
	@Query(value = "SELECT * FROM moduletb WHERE parent_id = ?1", nativeQuery = true)
	public List<Module> queryChildrenById(Integer parentId);
}
