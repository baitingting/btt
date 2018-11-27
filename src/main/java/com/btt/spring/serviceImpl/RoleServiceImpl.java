package com.btt.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.btt.spring.dao.ModuleRepository;
import com.btt.spring.dao.RoleRepository;
import com.btt.spring.entity.sys.Module;
import com.btt.spring.entity.sys.Role;
import com.btt.spring.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository repository;

	@Autowired
	private ModuleRepository mrepository;

	/**
	 * 角色分页+多条件
	 * 
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public Page<Role> queryRoleNameLikeAllPage(String name, Integer page,
			Integer size) {
		Sort sort = new Sort(Sort.Direction.ASC, "roleId");
		Pageable pageable = new PageRequest(page, size, sort);

		return repository.findByRoleNameLike("%" + name + "%", pageable);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	@Override
	public void insertRole(Role role) {
		repository.save(role);
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 */
	@Override
	public void updateRole(Role role) {
		repository.save(role);

	}

	/**
	 * 删除角色
	 * 
	 * @param rid
	 */
	@Override
	public void deleteRole(Integer rid) {
		repository.delete(rid);
	}

	/**
	 * 根据用户Id查询该用户拥有的角色信息
	 * 
	 * @param uid
	 * @return
	 */
	@Override
	public List<Role> queryRoleByuid(Integer uid) {
		// TODO Auto-generated method stub
		return repository.queryRoleByuid(uid);
	}

	/**
	 * 根据用户Id查询该用户mei拥有的角色信息
	 * 
	 * @param uid
	 * @return
	 */
	@Override
	public List<Role> queryNOTRoleByuid(Integer uid) {
		// TODO Auto-generated method stub
		return repository.queryNOTRoleByuid(uid);
	}

	/**
	 * 查询角色已经拥有的模块
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public List<Module> queryRoleModuleRoleId(Integer roleId) {
		List<Integer> checkedmodule = repository
				.queryRoleModuleByRoleId(roleId);// 查询角色拥有的模块id
		System.out.println("查询角色拥有的模块id" + checkedmodule);
		List<Module> parentList = mrepository.queryChildrenById(0);// 查询一级菜单
		System.out.println("查询一级菜单" + parentList);
		setChildrens(parentList, checkedmodule);// 已经选中所有的模块了
		return parentList;
	}

	/**
	 * 给角色菜单模块 设置孩子
	 * 
	 * @param parentList
	 */
	private void setChildrens(List<Module> parentList,
			List<Integer> moduleIdList) {
		for (Module m : parentList) { // 一级菜单
			// 查询出子菜单
			List<Module> childrenList = mrepository.queryChildrenById(m
					.getModuleId());
			if (moduleIdList != null && !moduleIdList.isEmpty()) { // 角色拥有的模块id不为空
				if (moduleIdList.contains(m.getModuleId())) {
					if (childrenList == null || childrenList.isEmpty()) {
						// 查看是否包含模块id
						m.setChecked(true);// 选中状态
					}
				}
			}
			// 如果没有子菜单则递归结束
			if (childrenList != null && !childrenList.isEmpty()) {// 有子菜单
				// 设置子菜单
				System.out.println("设置的子菜单是=>" + childrenList);
				m.setChildren(childrenList);
				// 如果有子菜单则继续递归设置子菜单
				this.setChildrens(childrenList, moduleIdList);
			}
		}
	}

	/**
	 * 保存角色模块
	 * 
	 * @param roleId
	 * @param moduleId
	 * @return
	 */
	@Override
	public int saveRoleMole(Integer roleId, Integer moduleId) {
		// TODO Auto-generated method stub
		return repository.saveRoleMole(roleId, moduleId);
	}

	/**
	 * 根据角色Id清除角色模块关系
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public int deleteRoleModuleByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return repository.deleteRoleModuleByRoleId(roleId);
	}

}
