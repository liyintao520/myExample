package com.lyt.util.reidsLock;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringContextUtil
 * @Description TODO
 * @Author liyintao
 * @Date 2019/9/3 7:07 PM
 * <h1>获取当前 classpath 中的 Bean</h1>
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class c) throws BeansException {
        return (T) applicationContext.getBean(c);
    }
}
