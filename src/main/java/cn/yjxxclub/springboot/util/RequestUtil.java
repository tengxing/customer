package cn.yjxxclub.springboot.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-27
 * Time: 下午4:15
 * Describe: 请求工具类
 */
public class RequestUtil {

    /**
     * 获取真实IP
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        String  port = request.getHeader("Host");
        System.out.println(port+"\n"+request.getRemoteAddr()+request.getRemoteHost());
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 应用程序路径
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("");
    }
}
