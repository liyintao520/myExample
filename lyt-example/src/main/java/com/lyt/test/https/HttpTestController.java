package com.lyt.test.https;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lyt.module.lawyerTeam.TLawyerInfo;
import com.lyt.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpTest
 * @Description TODO
 * @Author liyintao
 * @Date 2020/4/28 18:29
 */
@Slf4j
@RestController
public class HttpTestController {

    @Value("${fruit.name}")
    private String name = "";

    @Value("${rrl_getList}")
    private String url = "";

    /**
     * 获取律师列表接口
     * http://localhost:8888/doGetLawyerList?licenseNumber=58688888888888888&realName=蜡笔小新度假酒店
     * @param licenseNumber 执业证号
     * @param realName    律师姓名
     * @return
     */
    @GetMapping(value = "/doGetLawyerList")
    public List<TLawyerInfo> doGetLawyerList(String licenseNumber, String realName) {
        log.info("name = {}, url = {}", this.name, this.url);
        log.info("name = {}", this.name);
        log.info("LawyerTeamDaoImpl-->doGetLawyerList() input licenseNumber = "+ licenseNumber + ", realName = " + realName);
        JSONObject setJson = new JSONObject();
        List<TLawyerInfo> listInfo = new ArrayList<>();
        try {
            // 请求地址
//            String url = Configer.getInstance().getProperty("rrl_getList");

            log.info("LawyerTeamDaoImpl-->doGetLawyerList() Request fzbd rrl_getList interface url>>>>>>>>>:"+ url);
            Map<String, String> setMap = Maps.newHashMap();
            setMap.put("realName", realName);   //  模糊匹配
            setMap.put("licenseNumbers", licenseNumber);    // 精确查找
            long start = System.currentTimeMillis();
            setJson = HttpUtil.doHttpsPost(url, setMap,"UTF-8");
            long end = System.currentTimeMillis();
            log.info("LawyerTeamDaoImpl-->doGetLawyerList() Request fzbd rrl_getList return Param>>>>>>>>>:"+ setJson.toString() + ", total time: " + (end - start));
            if (null == setJson.get("data")) {
                return listInfo;
            } else {
                String data = setJson.get("data").toString();
                // JSONArray数组转换成List<T>对象
                listInfo = (List<TLawyerInfo>) JSONArray.parseArray(data, TLawyerInfo.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("LawyerTeamDaoImpl-->doGetLawyerList()" + e.getMessage());
        }
        return listInfo;
    }
}
