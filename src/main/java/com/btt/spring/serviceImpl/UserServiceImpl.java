package com.btt.spring.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.btt.spring.dao.UserRepository;
import com.btt.spring.entity.query.UserQuery;
import com.btt.spring.entity.sys.User;
import com.btt.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * 登陆 根据用户名密码 查询出没有被锁定的有效用户
	 * 
	 * @param name
	 * @param pass
	 * @return
	 */
	@Override
	public User loginUser(String name, String pass) {
		// TODO Auto-generated method stub
		return repository.loginUser(name, pass);
	}

	/**
	 * 按名字查询
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public List<User> selectUserByusername(String name) {
		// TODO Auto-generated method stub
		return repository.selectUserByusername(name);
	}

	/**
	 * 登陆五次密码错误修改用户状态(根据用户ID)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int updateuserState(Integer id) {
		// TODO Auto-generated method stub
		return repository.updateuserState(id);
	}

	/**
	 * 记录密码错误次数
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int updateUserPassWrong(Integer id) {
		// TODO Auto-generated method stub
		return repository.updateUserPassWrong(id);
	}

	/**
	 * 修改用户的最后登录时间
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int updateUserLastLoginTime(Integer id) {
		// TODO Auto-generated method stub
		return repository.updateUserLastLoginTime(id);
	}

	/**
	 * 登录成功后清空错误次数
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public int updateClearPassCount(Integer id) {
		// TODO Auto-generated method stub
		return repository.updateClearPassCount(id);
	}

	/**
	 * 根据用户Id查询拥有的角色
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<Integer> queryUserRoleByUserId(Integer userId) {
		return repository.queryUserRoleByUserId(userId);
	}

	/**
	 * 多条件查询并分页
	 * 
	 * @param uq
	 * @return
	 */
	@Override
	public Page<User> queryByDynamicSQL(UserQuery uq) {
		// 排序列
		String property = "userId";
		if (uq.getOrderBy() != null && !"".equals(uq.getOrderBy())) {
			property = uq.getOrderBy();
		}
		Sort sort = new Sort(Sort.Direction.ASC, property);
		Pageable pageable = new PageRequest(uq.getPage() - 1, uq.getRows(),
				sort);
		return repository.findAll(this.getUserWhere(uq), pageable);
	}

	private Specification<User> getUserWhere(final UserQuery uQuery) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();// 动态SQL表达式
				List<Expression<Boolean>> pList = predicate.getExpressions();// 动态SQL表达式集合
				if (uQuery.getName() != null && !"".equals(uQuery.getName())) {
					pList.add(cb.like(root.<String> get("userName"), "%"
							+ uQuery.getName() + "%"));
				}
				if (uQuery.getBegintime() != null) {
					pList.add(cb.greaterThanOrEqualTo(
							root.<Date> get("userCreateTime"),
							uQuery.getBegintime()));// 大于等于起始日期
				}
				if (uQuery.getEndtime() != null) {
					pList.add(cb.lessThanOrEqualTo(root.get("userCreateTime")
							.as(Date.class), uQuery.getEndtime()));// 小于等于截止日期
				}
				if (uQuery.getUserIsLockout() != null) {
					pList.add(cb.equal(
							root.get("userIsLockout").as(Boolean.class),
							uQuery.getUserIsLockout()));
				}
				return predicate;
			}

		};

	}

	/**
	 * 添加用户
	 */
	@Override
	public void insertUser(User user) {
		user.setUserPassword("ysd123");
		repository.save(user);
	}

	/**
	 * 修改
	 */
	@Override
	public void updateUser(User user) {
		user.setUserPassword("ysd123");
		repository.save(user);
	}

	/**
	 * 删除
	 */
	public void deleteUser(Integer uid) {
		repository.delete(uid);
	}

	/**
	 * 是否锁定用户 (修改用户是否锁定(状态))
	 * 
	 * @param userId
	 * @param userIsLockout
	 * @return
	 */
	@Override
	public int updateUserIsLock(Integer userId, Boolean userIsLockout) {
		// TODO Auto-generated method stub
		return repository.updateUserIsLock(userId, userIsLockout);
	}

	/**
	 * 重置用户密码 (修改用户密码)
	 * 
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	@Override
	public int updateUserPass(Integer userId, String userPassword) {
		// TODO Auto-generated method stub
		return repository.updateUserPass(userId, userPassword);
	}

	/**
	 * 给用户添加角色
	 * 
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@Override
	public int addUserToRole(Integer roleId, Integer userId) {
		// TODO Auto-generated method stub
		return repository.addUserToRole(roleId, userId);
	}

	/**
	 * 移除用户角色根据用户ID
	 * 
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@Override
	public int delectUserRoleByUserId(Integer roleId, Integer userId) {
		// TODO Auto-generated method stub
		return repository.delectUserRoleByUserId(roleId, userId);
	}

}
