package com.btt.spring.entity.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserQuery {

	private String name;// 用户姓名
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date begintime;// 开始时间
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;// 结束时间
	private Boolean userIsLockout;// 是否锁定
	private String orderBy;// 排序

	private Integer page = 1;
	private Integer rows = 30;

	private Integer startIndex;

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Boolean getUserIsLockout() {
		return userIsLockout;
	}

	public void setUserIsLockout(Boolean userIsLockout) {
		this.userIsLockout = userIsLockout;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public UserQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserQuery(String name, Date begintime, Date endtime,
			Boolean userIsLockout, String orderBy) {
		super();
		this.name = name;
		this.begintime = begintime;
		this.endtime = endtime;
		this.userIsLockout = userIsLockout;
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "UserQuery [name=" + name + ", begintime=" + begintime
				+ ", endtime=" + endtime + ", userIsLockout=" + userIsLockout
				+ ", orderBy=" + orderBy + "]";
	}

}
