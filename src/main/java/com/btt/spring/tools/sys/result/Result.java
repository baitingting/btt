package com.btt.spring.tools.sys.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @Description: 统一处理响应数据
 * @author Mashuai
 * @Date 2018-5-18 下午11:02:46
 * @Email 1119616605@qq.com
 */
@JsonInclude(Include.NON_NULL)
// 如果该属性为NULL则不参与序列化
public class Result {
	/**
	 * 1成功，0失败 默认失败0
	 */
	private Integer state = 0;// 0失败，1成功
	/**
	 * 响应失败状态码 1未登录 2token过期 3无权访问
	 * 
	 * 
	 * 0其它未知异常 默认未登录
	 */
	private Integer code = null;// 响应失败状态码

	/**
	 * 失败原因
	 */
	private String msg = null;

	/**
	 * state = 1 请求成功时返回的数据
	 */
	private Object data = null;

	/**
	 * 响应成功的数据 state = 1 成功 code = 0 未知 msg = null
	 * 
	 * @param data
	 */
	public Result(Object data) {
		super();
		this.state = 1;// 成功
		this.data = data;
	}

	/**
	 * 响应失败提示信息
	 * 
	 * @param msg
	 *            提示信息
	 * @param state
	 *            0
	 */
	public Result(String msg, Integer state) {
		super();
		this.state = state;
		this.msg = msg;
	}

	public Result(Integer state, Integer code, String msg) {
		super();
		this.state = state;
		this.code = code;
		this.msg = msg;
	}

	public Result(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Result(Integer state, Integer code) {
		super();
		this.state = state;
		this.setCode(code);
	}

	public Result(Integer code) {
		super();
		this.setCode(code);
	}

	public Result() {
		super();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 响应失败状态码，并设置失败原因
	 * 
	 * @param code
	 */
	public void setCode(Integer code) {
		this.code = code;
		switch (code) {
		case 1:
			setMsg(ResultConst.UNLOGIN);
			break;// 1未登录
		case 2:
			setMsg(ResultConst.TOKENEXPIRED);
			break;// 2token过期
		case 3:
			setMsg(ResultConst.NOPERMISSION);
			break;// 3无权访问

		default:
			break;
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
