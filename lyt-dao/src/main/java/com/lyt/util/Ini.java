package com.lyt.util;

public class Ini {

	public static final String LOG_INFO_TAG = "INFO";
	public static final String LOG_CONFIG_FILE = "log4j.properties";
	public static final String SPRING_CONFIG_FILE = "Ispring.xml";
	public static final String JEDIS_CONFIG_FILE = "Ics-jedis.xml";
	public static final String SYSTEM_CONFIG_FILE = "Ics-config.properties";
	public static final String BASE_CONFIG_FILE = "Ibase.properties";
	public static final String SESSION_USER = "userBean";
	//分布式部署参数
	public static final String SYSTEM_ID = Configer.getInstance().getProperty("SYSTEM_ID");
	public static final String SYSTEM_LEVEL = Configer.getInstance().getProperty("SYSTEM_LEVEL");
}
