package com.btt.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btt.spring.entity.query.UserQuery;
import com.btt.spring.entity.sys.Role;
import com.btt.spring.entity.sys.User;
import com.btt.spring.service.RoleService;
import com.btt.spring.service.UserService;
import com.btt.spring.tools.sys.result.Result;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService Uservice;
	@Autowired
	private RoleService Rservice;

	/**
	 * http://localhost:8080/user/confirm 用户登录
	 */
	@RequestMapping("/confirm")
	public Object confirm(String name, String pass) {
		System.out.println("sssssssss>=" + name);
		System.out.println("sssssssss>=" + pass);
		// Map<String, Object> map = new HashMap<String, Object>();
		List<User> ulist = Uservice.selectUserByusername(name);
		User u = new User();
		if (ulist.size() != 0) {
			u = Uservice.loginUser(name, pass);

			if (ulist.get(0).getUserIsLockout()) {
				return new Result("用户已被锁定,请联系管理员解锁.电话: 1234567890 ", 0);
			} else if (u == null) {
				if (ulist.get(0).getUserPassWrongCout() == 5) {
					int lock = Uservice.updateuserState(ulist.get(0)
							.getUserId());
					return new Result("用户已被锁定,请联系管理员解锁.电话 : 1234567890 ", 0);
				} else {
					int passCount = Uservice.updateUserPassWrong(ulist.get(0)
							.getUserId());
					return new Result("密码不正确,"
							+ (5 - (ulist.get(0).getUserPassWrongCout()))
							+ "次将被锁定!", 0);
				}
			} else if (u != null) {
				int n = Uservice.updateUserLastLoginTime(ulist.get(0)
						.getUserId());
				int i = Uservice.updateClearPassCount(ulist.get(0).getUserId());
				// return new Result("登录成功", 1);
			}
		} else {
			return new Result("用户名不存在", 0);
		}
		// 根据用户Id查询出该用户的所有角色Id
		List<Integer> roleIdList = Uservice
				.queryUserRoleByUserId(u.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleIdList);
		map.put("users", u);
		map.put("userName", u.getUserName());
		map.put("userId", u.getUserId());
		return new Result(map);
	}

	/**
	 * http://localhost:8080/user/queryDynamic 用户查询
	 * 
	 * @param userQuery
	 * @return
	 */
	@RequestMapping("/queryDynamic")
	public Object queryDynamic(UserQuery userQuery) {
		System.out.println("我的查询条件:" + userQuery);
		Page<User> list = Uservice.queryByDynamicSQL(userQuery);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.getTotalElements());
		map.put("rows", list.getContent());
		System.out.println("动态查询 list=>" + list);
		return map;
	}

	/**
	 * 添加用户 http://localhost:8080/user/inser
	 * 
	 * @return
	 */
	@RequestMapping("inser")
	public Object insertUser(User user) {

		try {
			Uservice.insertUser(user);
			return new Result("添加成功", 1);
		} catch (Exception e) {
			return new Result("添加失败", 0);
		}
	}

	/**
	 * 修改用户 http://localhost:8080/user/update
	 * 
	 * @return
	 */
	@RequestMapping("update")
	public Object updateUser(User user) {

		try {
			Uservice.updateUser(user);
			return new Result("修改成功", 1);
		} catch (Exception e) {
			return new Result("修改失败", 0);
		}
	}

	/**
	 * 修改用户 http://localhost:8080/user/delete
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	public Object deleteUser(Integer userId) {
		try {
			Uservice.deleteUser(userId);
			return new Result("删除成功", 1);
		} catch (Exception e) {
			return new Result("删除失败", 0);
		}
	}

	/**
	 * 修改用户状态(是否锁定) http://localhost:8080/user/updateUserIsLock
	 * 
	 * @param userId
	 * @param userIsLockout
	 * @return
	 */
	@RequestMapping(value = "updateUserIsLock")
	public Object updateUserIsLock(Integer userId, Boolean userIsLockout) {
		// System.out.println(userIsLockout);
		int n = Uservice.updateUserIsLock(userId, userIsLockout);
		if (n > 0) {
			return new Result("成功", 1);
		} else {
			return new Result("失败", 0);
		}
	}

	/**
	 * 重置密码 http://localhost:8080/user/resetUserPwd
	 * 
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	@RequestMapping(value = "resetUserPwd")
	public Object resetUserPwd(Integer userId, String userPassword) {
		int n = Uservice.updateUserPass(userId, "ysd123");
		if (n > 0) {
			return new Result("成功", 1);
		} else {
			return new Result("失败", 0);
		}
	}

	/**
	 * 根据用户id查询该用户所拥有的角色信息 http://localhost:8080/user/queryRoleByUserId
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/queryRoleByUserId")
	public Object queryRoleByUserId(Integer userId) {
		List<Role> list = Rservice.queryRoleByuid(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", 30);
		map.put("rows", list);
		return map;

	}

	/**
	 * 根据用户id查询该用户所没有的角色信息 http://localhost:8080/user/queryNotRoleByUserId
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/queryNotRoleByUserId")
	public Object queryNotRoleByUserId(Integer userId) {
		List<Role> list = Rservice.queryNOTRoleByuid(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", 30);
		map.put("rows", list);
		return map;
	}

	/**
	 * 给用户添加角色 http://localhost:8080/user/addUserToRole?roleId=3&userId=4
	 * 
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/addUserToRole")
	public Object addUserToRole(Integer roleId, Integer userId) {
		int n = Uservice.addUserToRole(roleId, userId);
		if (n > 0) {
			return new Result("添加成功", 1);
		} else {
			return new Result("添加失败", 0);
		}

	}

	/**
	 * 给用户添加角色 http://localhost:8080/user/RemoveUserFromRole?roleId=3&userId=4
	 * 
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/RemoveUserFromRole")
	public Object RemoveUserFromRole(Integer roleId, Integer userId) {
		int n = Uservice.delectUserRoleByUserId(roleId, userId);
		if (n > 0) {
			return new Result("移除成功", 1);
		} else {
			return new Result("移除成功", 0);
		}

	}
}
