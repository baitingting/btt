package com.btt.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.btt.spring.entity.sys.User;

public interface UserRepository extends JpaRepository<User, Integer>,
		JpaSpecificationExecutor<User> {

	// 用户登录
	@Query(value = "SELECT * FROM usertb u WHERE u.user_name=?1 AND u.user_passWord = ?2", nativeQuery = true)
	public User loginUser(String name, String pass);

	// 按名字查询
	@Query(value = "SELECT * FROM usertb u WHERE u.user_name=?1", nativeQuery = true)
	public List<User> selectUserByusername(String name);

	// 登陆五次密码错误修改用户状态(根据用户ID)
	@Query("UPDATE  User u SET u.userIsLockout=1,u.userPassWrongCout=5 WHERE u.userId=:id")
	@Modifying
	@Transactional
	public int updateuserState(@Param("id") Integer id);

	// 记录密码错误次数
	@Query("UPDATE  User u SET u.userPassWrongCout=u.userPassWrongCout+1 WHERE u.userId=:id")
	@Modifying
	@Transactional
	public int updateUserPassWrong(@Param("id") Integer id);

	// 修改用户的最后登录时间
	@Query("UPDATE  User u SET u.userLastLoginTime=NOW() WHERE u.userId=?1")
	@Modifying
	@Transactional
	public int updateUserLastLoginTime(Integer id);

	// 登录成功后清空错误次数
	@Query("UPDATE  User u SET u.userIsLockout=0,u.userPassWrongCout=0 WHERE u.userId=?1")
	@Modifying
	@Transactional
	public int updateClearPassCount(Integer id);

	// 根据用户Id查询拥有的角色
	@Query(value = "SELECT user_role_tb_role_id FROM user_role_tb WHERE user_role_tb_user_id=?1", nativeQuery = true)
	public List<Integer> queryUserRoleByUserId(Integer userId);

	// 是否锁定用户 (修改用户是否锁定(状态))
	@Query(value = "UPDATE usertb SET user_is_lockout=?2 WHERE user_id=?1", nativeQuery = true)
	@Modifying
	@Transactional
	public int updateUserIsLock(Integer userId, Boolean userIsLockout);

	// 重置用户密码 (修改用户密码)
	@Query(value = "UPDATE usertb SET user_password=?2 WHERE user_id=?1", nativeQuery = true)
	@Modifying
	@Transactional
	public int updateUserPass(Integer userId, String userPassword);

	// 给用户添加角色
	@Query(value = "INSERT INTO user_role_tb (user_role_tb_user_id,user_role_tb_role_id) VALUES(?2,?1)", nativeQuery = true)
	@Modifying
	@Transactional
	public int addUserToRole(Integer roleId, Integer userId);

	// 移除用户角色根据用户ID
	@Query(value = "DELETE FROM user_role_tb WHERE user_role_tb_user_id=?2 AND user_role_tb_role_id=?1", nativeQuery = true)
	@Modifying
	@Transactional
	public int delectUserRoleByUserId(Integer roleId, Integer userId);

}
