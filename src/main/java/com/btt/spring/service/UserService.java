package com.btt.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.btt.spring.entity.query.UserQuery;
import com.btt.spring.entity.sys.User;

public interface UserService {

	/**
	 * 登陆 根据用户名密码 查询出没有被锁定的有效用户
	 * 
	 * @param name
	 * @param pass
	 * @return
	 */
	public User loginUser(String name, String pass);

	/**
	 * 按名字查询
	 * 
	 * @param name
	 * @return
	 */
	public List<User> selectUserByusername(String name);

	/**
	 * 登陆五次密码错误修改用户状态(根据用户ID)
	 * 
	 * @param id
	 * @return
	 */
	public int updateuserState(Integer id);

	/**
	 * 记录密码错误次数
	 * 
	 * @param id
	 * @return
	 */
	public int updateUserPassWrong(Integer id);

	/**
	 * 修改用户的最后登录时间
	 * 
	 * @param id
	 * @return
	 */
	public int updateUserLastLoginTime(Integer id);

	/**
	 * 登录成功后清空错误次数
	 * 
	 * @param id
	 * @return
	 */
	public int updateClearPassCount(Integer id);

	/**
	 * 根据用户Id查询拥有的角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<Integer> queryUserRoleByUserId(Integer userId);

	/**
	 * 多条件查询并分页
	 * 
	 * @param uq
	 * @return
	 */
	public Page<User> queryByDynamicSQL(UserQuery uq);

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void insertUser(User user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 根据Id删除用户
	 * 
	 * @param uid
	 */
	public void deleteUser(Integer uid);

	/**
	 * 是否锁定用户 (修改用户是否锁定(状态))
	 * 
	 * @param userId
	 * @param userIsLockout
	 * @return
	 */
	public int updateUserIsLock(Integer userId, Boolean userIsLockout);

	/**
	 * 重置用户密码 (修改用户密码)
	 * 
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	public int updateUserPass(Integer userId, String userPassword);

	/**
	 * 给用户添加角色
	 * 
	 * @param roleId
	 * @param userId
	 * @return
	 */
	public int addUserToRole(Integer roleId, Integer userId);

	/**
	 * 移除用户角色根据用户ID
	 * 
	 * @param roleId
	 * @param userId
	 * @return
	 */
	public int delectUserRoleByUserId(Integer roleId, Integer userId);
}
