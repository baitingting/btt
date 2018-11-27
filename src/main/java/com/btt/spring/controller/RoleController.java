package com.btt.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btt.spring.entity.sys.Module;
import com.btt.spring.entity.sys.Role;
import com.btt.spring.service.ModuleService;
import com.btt.spring.service.RoleService;
import com.btt.spring.tools.sys.result.Result;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService Rservice;

	@Autowired
	private ModuleService Mservice;

	/**
	 * http://localhost:8080/role/queryRolePage?page=1&rows=30
	 * 
	 * @param name
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/queryRolePage")
	public Object queryRolePage(String name, Integer page, Integer rows) {
		Page<Role> page1 = null;
		page1 = Rservice.queryRoleNameLikeAllPage(name, page - 1, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page1.getTotalElements());
		map.put("rows", page1.getContent());
		return map;
	}

	/**
	 * 添加角色 http://localhost:8080/role/inser
	 * 
	 * @return
	 */
	@RequestMapping("inser")
	public Object insertRole(Role role) {

		try {
			Rservice.insertRole(role);
			return new Result("添加成功", 1);
		} catch (Exception e) {
			return new Result("添加失败", 0);
		}
	}

	/**
	 * 修改角色 http://localhost:8080/role/update
	 * 
	 * @return
	 */
	@RequestMapping("update")
	public Object updateRole(Role role) {

		try {
			Rservice.updateRole(role);
			return new Result("修改成功", 1);
		} catch (Exception e) {
			return new Result("修改失败", 0);
		}
	}

	/**
	 * 删除角色 http://localhost:8080/role/delete
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	public Object deleteRole(Integer rid) {

		try {
			Rservice.deleteRole(rid);
			return new Result("删除成功", 1);
		} catch (Exception e) {
			return new Result("删除失败", 0);
		}
	}

	/**
	 * 根据角色Id查询角色模块 http://localhost:8080/role/queryRoleModuleByRoleId
	 * 
	 * @return
	 */
	@RequestMapping("/queryRoleModuleByRoleId")
	public Object queryRoleModuleByRoleId(Integer roleId) {
		List<Module> list = Rservice.queryRoleModuleRoleId(roleId);
		System.out.println("ssssssssss" + list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tree", list);
		return new Result(map);
	}

	/**
	 * 保存角色模块 http://localhost:8080/role/saveRloeModule
	 * 
	 * @param ids
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/saveRloeModule")
	public Object saveRloeModule(
			@RequestParam(value = "modulesIds", required = false) String modulesIds,
			@RequestParam Integer roleId) {
		int add = 0;
		System.out.println("我的模块Ids" + modulesIds);
		if (modulesIds == null || modulesIds == "") {
			int n = Rservice.deleteRoleModuleByRoleId(roleId);
			if (n > 0) {
				return new Result("保存成功", 1);
			} else {
				return new Result("请选择一个模块进行保存", 0);
			}
		} else {
			String[] strings = modulesIds.split(",");
			int[] idss = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				idss[i] = Integer.parseInt(strings[i]);
			}
			int de = Rservice.deleteRoleModuleByRoleId(roleId);
			System.out.println("删除的结果>>>>>>>>>>" + de);
			for (Integer id : idss) {
				add = Rservice.saveRoleMole(roleId, id);
			}
			if (add > 0) {
				return new Result("添加成功", 1);
			} else {
				return new Result("添加失败", 0);
			}
		}
	}

}
