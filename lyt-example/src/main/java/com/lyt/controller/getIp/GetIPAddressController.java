package com.lyt.controller.getIp;

import com.lyt.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GetIPAddressController
 * @Description 测试工具类获取ip地址
 * @Author liyintao
 * @Date 2021/6/1 14:39
 */
@Slf4j
//返回页面要使用Controller注解
@RestController
@RequestMapping("/ipService")
public class GetIPAddressController {

    /**
     * http://localhost:8010/ipService/getIp
     * 获取本服务器ip地址
     * @return
     */
    @RequestMapping("/getIp")
    public String getIp() {
        String ipString = IpUtil.getLocalIP();
        log.info("获得本服务器的IP地址:{}", ipString);
        return ipString;
    }

    /**
     * http://localhost:8010/ipService/getIp
     * 获取【调用者】服务器ip地址
     * @return
     */
    @RequestMapping("/getIp2")
    public String getIp2() {
        String ipString = IpUtils.getRequestIp();
        log.info("获得用户请求的服务器的IP地址:{}", ipString);
        return ipString;
    }
}