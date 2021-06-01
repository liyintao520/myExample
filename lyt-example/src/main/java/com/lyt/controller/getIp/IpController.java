package com.lyt.controller.getIp;

import com.lyt.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GetIPAddressController
 * @Description 测试工具类获取ip地址
 * @Author liyintao
 * @Date 2021/6/1 14:39
 */
@Slf4j
//返回页面要使用Controller注解
@RestController
@RequestMapping("/ipService1")
public class IpController {

    /**
     * http://localhost:8080/ipService1/getIp.do
     * 获取本服务器ip地址
     * @return
     */
    @RequestMapping(value = "/getIp.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getIp(HttpServletRequest request) {
        String ip = getIpAddress(request);
        log.info("getIp:{}", ip);
        return ip;
    }


    public static String getIpAddress(HttpServletRequest request) {
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
        log.info("ip:"+ip);
        if(ip.contains(",")){
            return ip.split(",")[0];
        }
        return ip;
    }
}