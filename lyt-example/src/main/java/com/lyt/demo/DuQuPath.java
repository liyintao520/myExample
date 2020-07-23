package com.lyt.demo;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;

import java.util.Properties;

/**
 * @ClassName DuQuPath
 * @Description TODO
 * @Author liyintao
 * @Date 2020/7/23 17:42
 */
@Slf4j
public class DuQuPath extends Properties {
    public static void main(String[] args) {
        String path = GetResource.class.getClassLoader().getResource("application.yml").getPath();
        log.info(path);
    }
}
