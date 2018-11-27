package com.btt.spring.entity.sys;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "permissiontb")
@GenericGenerator(name = "permissionid", strategy = "increment")
public class Permission implements Serializable {
	@Id
	@GeneratedValue
	@Column(columnDefinition = "int(10) unsigned NOT NULL  COMMENT '权限ID'")
	private Integer permissionId;// 权限ID

	@Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '权限资源对象'")
	private String permissionValue;// 权限
	@Column(columnDefinition = "varchar(50) NOT NULL COMMENT '权限所属模块'")
	private String permissionModule;// 权限所属模块
	@Column(columnDefinition = "varchar(50) NOT NULL COMMENT '权限名称'")
	private String permissionName;// 权限备注说明介绍
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权限最近一次修改时间'")
	private Timestamp permissionLastUpdateTime;// 权限修改日期时间

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public String getPermissionModule() {
		return permissionModule;
	}

	public void setPermissionModule(String permissionModule) {
		this.permissionModule = permissionModule;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Timestamp getPermissionLastUpdateTime() {
		return permissionLastUpdateTime;
	}

	public void setPermissionLastUpdateTime(Timestamp permissionLastUpdateTime) {
		this.permissionLastUpdateTime = permissionLastUpdateTime;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permission(String permissionValue, String permissionModule,
			String permissionName) {
		super();
		this.permissionValue = permissionValue;
		this.permissionModule = permissionModule;
		this.permissionName = permissionName;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId
				+ ", permissionValue=" + permissionValue
				+ ", permissionModule=" + permissionModule
				+ ", permissionName=" + permissionName
				+ ", permissionLastUpdateTime=" + permissionLastUpdateTime
				+ "]";
	}

}
