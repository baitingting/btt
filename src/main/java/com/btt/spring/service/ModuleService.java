package com.btt.spring.service;

import java.util.List;

import com.btt.spring.entity.sys.Module;

public interface ModuleService {
	/**
	 * 根据用户Id查出该用户所属模块
	 * 
	 * @param parentId
	 * @param userId
	 * @return
	 */
	public List<Module> queryModuleByUserId(Integer parentId, Integer userId);

	// ^^^^^^^^^^^^^^^^^^ 查询 TreeGrid ^^^^^^^^^
	/**
	 * 
	 * 查询模块表格 查询模块 TreeGrid 树形数据表格
	 */
	public List<Module> queryTreeGrid(String moduleName);

	/**
	 * 添加模块
	 * 
	 * @param module
	 */
	public void insertModule(Module module);

	/**
	 * 修改模块
	 * 
	 * @param module
	 */
	public void updateModule(Module module);

	/**
	 * 删除模块
	 * 
	 * @param module
	 */
	public void deleteModule(Integer moduleId);

}
