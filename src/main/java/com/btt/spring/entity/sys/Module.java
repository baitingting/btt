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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "moduletb")
@GenericGenerator(name = "moduleid", strategy = "increment")
public class Module implements Serializable {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "int unsigned NOT NULL  COMMENT '模块Id'")
	@JsonProperty("id")
	private Integer moduleId;
	@Column(columnDefinition = "int unsigned NOT NULL DEFAULT 0 COMMENT '父模块Id'")
	private Integer parentId;
	@Column(columnDefinition = "varchar(20) DEFAULT NULL COMMENT '模块名称'")
	@JsonProperty("text")
	private String moduleName;
	@Column(columnDefinition = "int(10) unsigned DEFAULT 0 COMMENT '模块权重,列表顺序'")
	private Integer moduleWeight;
	@Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '模块对应页面url路径'")
	private String moduleUrl;
	@Column(columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '模块创建时间'")
	private Date moduleCreateTime;
	@Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '模块最近一次修改时间'")
	private Date moduleLastUpdateTime;
	@Transient
	@JsonInclude(Include.NON_NULL)
	// 如果该属性为NULL则不参与序列化
	private List<Module> children;
	@Transient
	private boolean checked;

	// ----------------------------配置关联信息-----------------------------------
	/*
	 * 角色--模块 多对多关系 1.一个角色有多个模块2.一个模块被多个角色拥有
	 */
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "role_module_tb", joinColumns = { @JoinColumn(name = "role_module_tb_module_id") }, inverseJoinColumns = { @JoinColumn(name = "role_module_tb_role_id") })
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Role> roles;

	@Transient
	// 临时参数,不映射到数据库表字段
	private String moduleParam;

	public String getModuleParam() {
		return moduleParam;
	}

	public void setModuleParam(String moduleParam) {
		this.moduleParam = moduleParam;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getModuleWeight() {
		return moduleWeight;
	}

	public void setModuleWeight(Integer moduleWeight) {
		this.moduleWeight = moduleWeight;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Date getModuleCreateTime() {
		return moduleCreateTime;
	}

	public void setModuleCreateTime(Date moduleCreateTime) {
		this.moduleCreateTime = moduleCreateTime;
	}

	public Date getModuleLastUpdateTime() {
		return moduleLastUpdateTime;
	}

	public void setModuleLastUpdateTime(Date moduleLastUpdateTime) {
		this.moduleLastUpdateTime = moduleLastUpdateTime;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Module(Integer moduleId, Integer parentId, String moduleName,
			Integer moduleWeight, String moduleUrl, Date moduleCreateTime,
			Date moduleLastUpdateTime, List<Module> children, boolean checked,
			List<Role> roles) {
		super();
		this.moduleId = moduleId;
		this.parentId = parentId;
		this.moduleName = moduleName;
		this.moduleWeight = moduleWeight;
		this.moduleUrl = moduleUrl;
		this.moduleCreateTime = moduleCreateTime;
		this.moduleLastUpdateTime = moduleLastUpdateTime;
		this.children = children;
		this.checked = checked;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", parentId=" + parentId
				+ ", moduleName=" + moduleName + ", moduleWeight="
				+ moduleWeight + ", moduleUrl=" + moduleUrl
				+ ", moduleCreateTime=" + moduleCreateTime
				+ ", moduleLastUpdateTime=" + moduleLastUpdateTime
				+ ", children=" + children + ", checked=" + checked
				+ ", roles=" + roles + "]";
	}

}
