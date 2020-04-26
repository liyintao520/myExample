package com.lyt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 特殊字符校验
 * @author liyintao
 * 调用：
 * 		boolean checkName = SpecialCharacterCheck.checkString(org.getOrgName(), 2);
 */
public class SpecialCharacterCheck {
	/**
	 * 校验字符串是否存在特殊字符，目前仅支持一类特殊字符、二类特殊字符校验。
	 * @param str 需要校验的字符串
	 * @param i	字符串类型 1：一类特殊字符；2：二类特殊字符
	 * @return true：存在特殊字符； false：不存在特殊字符
	 */
	public static boolean checkString(String str, int i){
		//一类特殊字符 ^~`!@#$%()-+*,.&\\\/"'><}{|?:;，。：“‘！@#￥）（｛｝【】～《》？’”｜、
		String specialCharI="^[^^~`!@#$%()+*--,.&\\\\/\"'><}{|?:;，。：“‘！@#￥）（｛｝【】～《》？’”｜、]+$";
		//二类特殊字符^#%*&\\\/"'><}{|?
		String specialCharII="^[^^#%*&\\\\/\"'><}{|?]+$";
		//中文
		String isChinese = "^[\u0391-\uFFE5]+$";
		//中文英文数字
		String stringCheckII = "^[a-zA-Z0-9\u0391-\uFFE5]+$";
		//英文和数字
		String englishCheckSub = "^[a-zA-Z0-9]+$";
		if(i==1){
			return doCheck(specialCharI,str);
		}
		if(i==2){
			return doCheck(specialCharII,str);
		}
		if(i==3){
			return doCheck(isChinese,str);
		}
		if(i==4){
			return doCheck(englishCheckSub,str);
		}
		if(i==5){
			return doCheck(stringCheckII,str);
		}
		return true;
	}
	
	/**
	 * @Description: 数字校验
	 * @param str
	 * @param i
	 * @return
	 * @author: liuzhi
	 * @date: 2019年3月4日 下午4:23:05
	 */
	public static boolean checkNumber(String str, int i){
		
		String positive= "^[0-9]*[1-9][0-9]*$";//正整数
		if(i==1){
			return doCheck(positive,str);
		}
		return true;
	}
	
	
	/**
	 * 校验字符串
	 * @param check 正则表达式
	 * @param str	需要校验的字符串
	 * @return	false：存在特殊字符； true：不存在特殊字符
	 */
	private static boolean doCheck(String check, String str) {
		Pattern p = Pattern.compile(check);
		Matcher m = p.matcher(str);
		return m.find();
	}
}
