//package com.lyt.util;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
///**
// * @ClassName MyBatisConfig
// * @Description 数据源配置
// * @Author liyintao
// * @Date 2012/4/23 下午18:50
// */
//@Configuration
//@MapperScan(basePackages="com.lyt.module.*.dao")
//public class MyBatisConfig {
//    @Autowired
//    private Environment env;
//
//    /**
//     * @Title: getDataSource
//     * @Description: 创建数据源
//     * @param @return
//     * @return DataSource
//     * @throws
//     */
//    @Bean
//    public DataSource getDataSource(){
//        Properties props = new Properties();
//        props.put("driverClass", env.getProperty("jdbc.driverClassName"));
//        props.put("url", env.getProperty("jdbc.url"));
//        props.put("username", env.getProperty("jdbc.username"));
//        props.put("password", env.getProperty("jdbc.password"));
//        props.put("initialSize", "20"); //初始化连接:连接池启动时创建的初始化连接数量
//        props.put("minIdle", "20"); //最小空闲连接:连接池中容许保持空闲状态的最小连接数量,低于这个数量将创建新的连接,如果设置为0则不创建
//        props.put("maxActive", "500"); //最大活动连接:连接池在同一时间能够分配的最大活动连接的数量,如果设置为非正数则表示不限制
//        props.put("maxWait", "60000"); //最大等待时间:当没有可用连接时,连接池等待连接被归还的最大时间(以毫秒计数),超过时间则抛出异常,如果设置为-1表示无限等待
//        props.put("maxOpenPreparedStatements", "500"); //statement池能够同时分配的打开的statements的最大数量,如果设置为0表示不限制
//        props.put("validationQuery", "SELECT 1"); //SQL查询,用来验证从连接池取出的连接,在将连接返回给调用者之前.如果指定,则查询必须是一个SQL SELECT并且必须返回至少一行记录
//        props.put("testOnBorrow", "true"); //指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
//        props.put("testWhileIdle", "true"); //指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除
//        props.put("testOnReturn", "false"); //指明是否在归还到池中前进行检验
//        props.put("timeBetweenEvictionRunsMillis", "60000"); //在空闲连接回收器线程运行期间休眠的时间值,以毫秒为单位.如果设置为非正数,则不运行空闲连接回收器线程
//        props.put("minEvictableIdleTimeMillis", "300000"); //连接在池中保持空闲而不被空闲连接回收器线程(如果有)回收的最小时间值，单位毫秒
//        props.put("logAbandoned", "true"); //标记当Statement或连接被泄露时是否打印程序的stack traces日志
//        props.put("removeAbandoned", "true"); //标记是否删除泄露的连接,如果他们超过了removeAbandonedTimout的限制.如果设置为true, 连接被认为是被泄露并且可以被删除,如果空闲时间超过removeAbandonedTimeout.设置为true可以为写法糟糕的没有关闭连接的程序修复数据库连接.
//        props.put("removeAbandonedTimeout", "180"); //泄露的连接可以被删除的超时值, 单位秒
//
//        try {
//            return DruidDataSourceFactory.createDataSource(props);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
