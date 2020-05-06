package com.lyt.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class Configer extends Properties {
	private static Configer instance;
	private static FileInputStream in;

	private Configer() {
		String path = this.getClass().getClassLoader()
				.getResource(Ini.SYSTEM_CONFIG_FILE).getPath();
		log.debug("读取系统配置文件,path=" + path);
		File file = new File(path);
		try {
			in = new FileInputStream(file);
			// in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Configer getInstance() {
		if (instance == null) {
			synchronized (Configer.class) {
				if (instance == null) {
					instance = new Configer();
					try {
						instance.load(in);
					} catch (IOException e) {
						instance = null;
						e.printStackTrace();
					}
				}
			}
		}
		return instance;
	}
	
	public int getInt(String key){
		String value=instance.getProperty(key);
		if(value==null)
			return -1;
		try{
			return Integer.parseInt(value);
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	public static void main(String[] arg) {
		log.debug(Configer.getInstance().get("SYSTEM_ID").toString());
	}
}
