package com.btt.spring.entity.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "usertb")
@GenericGenerator(name = "userid", strategy = "increment")
public class User implements Serializable {
	@Id
	@GeneratedValue
	// 自动增长列
	@Column(columnDefinition = "int unsigned   COMMENT '用户ID'", nullable = false)
	private Integer userId;
	@Column(columnDefinition = "varchar(20) NOT NULL COMMENT '用户登录名'")
	private String userName;
	@Column(columnDefinition = "varchar(100) NOT NULL COMMENT '用户密码'")
	private String userPassword;
	@Column(columnDefinition = "tinyint(1) DEFAULT 0 COMMENT '用户是否锁定:默认不锁定'")
	private Boolean userIsLockout;
	// @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户信息最后一次被修改时间:数据库自己维护记录的修改时间'")
	@CreatedDate
	private Date userUpdateTime;
	@Column(columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户账号创建时间'")
	@CreatedDate
	private Date userCreateTime;
	@Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户上一次登录时间'")
	@CreatedDate
	private Date userLastLoginTime;
	@Column(columnDefinition = "varchar(20) DEFAULT NULL COMMENT '用户上一次登录实际IP地址'")
	private String userLastLoginIp;
	@Column(columnDefinition = "tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '用户输入密码错误次数'")
	private Integer userPassWrongCout = 0;
	@Column(columnDefinition = "datetime DEFAULT NULL COMMENT '用户输入密码错误次数达到上限锁定时间'")
	private Date userLockoutTime;
	@Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '用户密保邮箱'")
	private String userEmail;
	@Column(columnDefinition = "varchar(20) DEFAULT NULL COMMENT '用户密保电话'")
	private String userTelephone;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Boolean getUserIsLockout() {
		return userIsLockout;
	}

	public void setUserIsLockout(Boolean userIsLockout) {
		this.userIsLockout = userIsLockout;
	}

	public Date getUserUpdateTime() {
		return userUpdateTime;
	}

	public void setUserUpdateTime(Date userUpdateTime) {
		this.userUpdateTime = userUpdateTime;
	}

	public Date getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	public Date getUserLastLoginTime() {
		return userLastLoginTime;
	}

	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}

	public String getUserLastLoginIp() {
		return userLastLoginIp;
	}

	public void setUserLastLoginIp(String userLastLoginIp) {
		this.userLastLoginIp = userLastLoginIp;
	}

	public Integer getUserPassWrongCout() {
		return userPassWrongCout;
	}

	public void setUserPassWrongCout(Integer userPassWrongCout) {
		this.userPassWrongCout = userPassWrongCout;
	}

	public Date getUserLockoutTime() {
		return userLockoutTime;
	}

	public void setUserLockoutTime(Date userLockoutTime) {
		this.userLockoutTime = userLockoutTime;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserTelephone() {
		return userTelephone;
	}

	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String userPassword, String userLastLoginIp,
			String userEmail, String userTelephone) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userLastLoginIp = userLastLoginIp;
		this.userEmail = userEmail;
		this.userTelephone = userTelephone;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userIsLockout="
				+ userIsLockout + ", userUpdateTime=" + userUpdateTime
				+ ", userCreateTime=" + userCreateTime + ", userLastLoginTime="
				+ userLastLoginTime + ", userLastLoginIp=" + userLastLoginIp
				+ ", userPassWrongCout=" + userPassWrongCout
				+ ", userLockoutTime=" + userLockoutTime + ", userEmail="
				+ userEmail + ", userTelephone=" + userTelephone + "]";
	}

}
