package com.lyt.controller.getIp;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 获取IP地址工具类
 * @author hupeng
 * @date 2020-04-30
 */
public final class IpUtils {

    private static final String UNKNOWN = "unknown";
    private static final String IPV6_LOCAL = "0:0:0:0:0:0:0:1";

    private IpUtils(){
        throw new AssertionError();
    }

    /**
     * 获取请求用户的IP地址
     * @return
     */
    public static String getRequestIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return getRequestIp(request);
    }

    /**
     * 获取请求用户的IP地址
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip.contains(",")){
            return ip.split(",")[0];
        }
        if (IPV6_LOCAL.equals(ip)){
            ip = getLocalhostIp();
        }
        return ip;
    }

    public static String getLocalhostIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return null;
    }

}
