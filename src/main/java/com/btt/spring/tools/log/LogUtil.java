package com.btt.spring.tools.log;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志记录辅助工具类
 * 
 * @author MS
 *
 */
public class LogUtil {

	/**
	 * 获取客户端的真实IP地址,透视代理IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

}
