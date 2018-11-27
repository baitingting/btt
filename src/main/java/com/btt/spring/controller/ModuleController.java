package com.btt.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btt.spring.entity.sys.Module;
import com.btt.spring.service.ModuleService;
import com.btt.spring.tools.sys.result.Result;

@RestController
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	private ModuleService Mservice;

	/**
	 * http://localhost:8080/module/queryTree
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/queryTree")
	public Object queryTree(Integer uid) {
		List<Module> list = Mservice.queryModuleByUserId(0, uid);
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return new Result(map);

	}

	/**
	 * 查询模块数据表 TreeGrid http://localhost:8080/module/queryTreeGrid
	 * 
	 * @return
	 */
	@RequestMapping("/queryTreeGrid")
	public Object queryTreeGrid(String moduleName) {
		return Mservice.queryTreeGrid("%" + moduleName + "%");
	}

	/**
	 * 添加模块 http://localhost:8080/module/insert
	 */
	@RequestMapping("/insert")
	public Object insertModule(Module module) {
		System.out.println("ssssssss" + module);
		try {
			Mservice.insertModule(module);
			return new Result("添加成功", 1);
		} catch (Exception e) {
			return new Result("添加失败", 0);
		}
	}

	/**
	 * 修改模块 http://localhost:8080/module/update
	 */
	@RequestMapping("/update")
	public Object updateModule(Module module) {
		System.out.println("ssssssss" + module);
		try {
			Mservice.updateModule(module);
			return new Result("修改成功", 1);
		} catch (Exception e) {
			return new Result("修改失败", 0);
		}
	}

	/**
	 * 修改模块 http://localhost:8080/module/delete
	 */
	@RequestMapping("/delete")
	public Object deleteModule(Integer moduleId) {
		System.out.println("ssssssss" + moduleId);
		try {
			Mservice.deleteModule(moduleId);
			return new Result("删除成功", 1);
		} catch (Exception e) {
			return new Result("删除失败", 0);
		}
	}

}
