package com.btt.spring.tools.sys.result;

/**
 * 
 * @Description: 系统常量值
 * @author Mashuai
 * @Date 2018-5-18 下午10:10:40
 * @Email 1119616605@qq.com
 */
public class ResultConst {

	public static boolean isOpen = false;

	// Token
	public static final String UNLOGIN = "未登录或请求没有携带合法Token!";
	public static final String TOKENEXPIRED = "请求Token过期或携带信息不安全!";
	public static final String NOPERMISSION = "权限不足或无权访问请求,请获取合法身份登陆!";

	// 操作成功状态
	public static final String SUCCESS_PUT = "添加成功。";
	public static final String SUCCESS_POST = "更新成功。";
	public static final String SUCCESS_DELETE = "删除成功。";

	// 操作失败状态
	public static final String NULL_ENTITY_PUT = "添加失败！";
	public static final String NULL_ENTITY_POST = "更新失败！";
	public static final String NULL_ENTITY_DELETE = "删除失败！";

	// 登录
	public static final String LOGIN_ERROR_PWD = "登录失败,密码输入错误！";
	public static final String LOGIN_ERROR_NOT = "登录失败,请重新登录！";
	public static final String LOGIN_ERROR_USERNAME = "该用户不存在！";

	// 注册
	public static final String REGISTER_ERROR_INFO = "注册失败！请核对信息后重新注册！";
	public static final String REGISTER_ERROR_NOT = "注册失败！请尝试人工注册！";

}
