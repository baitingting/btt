package com.btt.spring.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roletb")
@GenericGenerator(name = "roleid", strategy = "increment")
public class Role implements Serializable {
	@Id
	@GeneratedValue
	// 自动增长列
	@Column(columnDefinition = "int(10) unsigned NOT NULL  COMMENT '角色ID'")
	private Integer roleId;
	@Column(columnDefinition = "varchar(20) NOT NULL COMMENT '角色名称'")
	private String roleName;
	@Column(columnDefinition = "varchar(100) DEFAULT NULL COMMENT '角色说明'")
	private String roleExplain;
	// @JsonFormat(locale = "zh", timezone = "GMT+8", pattern =
	// "yyyy-MM-dd HH:mm:ss")
	// 日期格式化为中国的时区 东8区
	@Column(columnDefinition = "timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '角色创建时间'")
	@CreatedDate
	private Date roleCreateTime;
	// @JsonFormat(locale = "zh", timezone = "GMT+8", pattern =
	// "yyyy-MM-dd HH:mm:ss")
	// 日期格式化为中国的时区 东8区
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '角色最近修改时间'")
	@CreatedDate
	private Date roleLastUpdateTime;

	// --------------------------配置关联信息-----------------------
	/*
	 * 用户-角色,多对多的关系1.一个用户可以有多个角色2.一个角色可以被多个用户拥有
	 */
	@JsonIgnore
	// 忽略Json转换
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role_tb", joinColumns = { @JoinColumn(name = "user_role_tb_role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_role_tb_user_id") })
	@NotFound(action = NotFoundAction.IGNORE)
	// NotFound:意思是找不到引用的外键数据时忽略,
	private List<User> userList;// 用户所拥有的角色集合

	/*
	 * 角色--权限 ,多对多关系 1.一个角色可以有多个权限2.一个权限可以被多个角色拥有
	 */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "role_permission_tb", joinColumns = { @JoinColumn(name = "role_permission_tb_role_id") }, inverseJoinColumns = { @JoinColumn(name = "role_permission_tb_permission_id") })
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Permission> permissionsList;

	/*
	 * 角色--模块 多对多关系 1.一个角色有多个模块2.一个模块被多个角色拥有
	 */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "role_module_tb", joinColumns = { @JoinColumn(name = "role_module_tb_role_id") }, inverseJoinColumns = { @JoinColumn(name = "role_module_tb_module_id") })
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Module> modules;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleExplain() {
		return roleExplain;
	}

	public void setRoleExplain(String roleExplain) {
		this.roleExplain = roleExplain;
	}

	public Date getRoleCreateTime() {
		return roleCreateTime;
	}

	public void setRoleCreateTime(Date roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}

	public Date getRoleLastUpdateTime() {
		return roleLastUpdateTime;
	}

	public void setRoleLastUpdateTime(Date roleLastUpdateTime) {
		this.roleLastUpdateTime = roleLastUpdateTime;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Permission> getPermissionsList() {
		return permissionsList;
	}

	public void setPermissionsList(List<Permission> permissionsList) {
		this.permissionsList = permissionsList;
	}

	public Role(Integer roleId, String roleName, String roleExplain,
			Date roleCreateTime, Date roleLastUpdateTime, List<User> userList,
			List<Permission> permissionsList) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleExplain = roleExplain;
		this.roleCreateTime = roleCreateTime;
		this.roleLastUpdateTime = roleLastUpdateTime;
		this.userList = userList;
		this.permissionsList = permissionsList;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleExplain=" + roleExplain + ", roleCreateTime="
				+ roleCreateTime + ", roleLastUpdateTime=" + roleLastUpdateTime
				+ ", userList=" + userList + ", permissionsList="
				+ permissionsList + "]";
	}

}
