package com.btt.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btt.spring.dao.ModuleRepository;
import com.btt.spring.entity.sys.Module;
import com.btt.spring.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleRepository repository;

	/**
	 * 根据用户Id查出该用户所属模块 (登录后左边显示的树)
	 * 
	 * @param parentId
	 * @param userId
	 * @return
	 */
	@Override
	public List<Module> queryModuleByUserId(Integer parentId, Integer userId) {
		// 查询出所有根节点
		List<Module> rootList = repository
				.queryModuleByUserId(parentId, userId);
		this.setChildrens(rootList, userId);
		return rootList;
	}

	/**
	 * 给菜单模块 设置孩子
	 * 
	 * @param parentList
	 */
	private void setChildrens(List<Module> parentList, Integer userId) {
		for (Module m : parentList) {
			// 查询出子菜单
			List<Module> childrenList = this.queryModuleByUserId(
					m.getModuleId(), userId);
			// System.out.println("*****************************************************设置子菜单=>"+m.getModuleName());
			// 如果没有子菜单则递归结束
			if (childrenList != null && !childrenList.isEmpty()) {// 有子菜单
				// 设置子菜单
				// System.out.println("设置的子菜单是=>"+childrenList);
				m.setChildren(childrenList);
				// 如果有子菜单则继续递归设置子菜单
				this.setChildrens(childrenList, userId);
			}
		}
	}

	@Override
	public List<Module> queryTreeGrid(String moduleName) {
		List<Module> rootList = null;
		if (moduleName != null && moduleName != "") {
			rootList = repository.findByModuleNameLike(moduleName);
		} else {
			// 查询出所有根菜单
			rootList = repository.queryTreeGridChildrenById(0);
			// 递归设置子菜单
			this.setTreeGridChildrens(rootList);
			System.out.println("rootList==>" + rootList);
			// return rootList;
		}
		return rootList;
	}

	/**
	 * 
	 * 给菜单模块 设置孩子
	 * 
	 * @param parentList
	 */
	private void setTreeGridChildrens(List<Module> parentList) {
		for (Module m : parentList) {
			// 查询出子菜单
			List<Module> childrenList = this.queryTreeGridChildrenById(m
					.getModuleId());
			// 如果没有子菜单则递归结束
			if (childrenList == null || childrenList.isEmpty()) {// 有子菜单
			} else {
				// 设置子菜单
				System.out.println("设置的子菜单是=>" + childrenList);
				m.setChildren(childrenList);
				// 如果有子菜单则继续递归设置子菜单
				this.setTreeGridChildrens(childrenList);
			}
		}
	}

	/**
	 * TreeGrid 查询模块表格 TreeGrid 查询孩子菜单
	 */
	private List<Module> queryTreeGridChildrenById(Integer parentId) {
		return repository.queryTreeGridChildrenById(parentId);
	}

	/**
	 * 添加模块
	 * 
	 * @param module
	 */
	@Override
	public void insertModule(Module module) {
		repository.save(module);
	}

	/**
	 * 修改模块
	 * 
	 * @param module
	 */
	public void updateModule(Module module) {
		repository.save(module);
	}

	/**
	 * 删除模块
	 * 
	 * @param module
	 */
	public void deleteModule(Integer moduleId) {
		repository.delete(moduleId);
	}
}
