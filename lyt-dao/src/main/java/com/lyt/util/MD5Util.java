package com.lyt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @version 3.0
 */
public class MD5Util
{
  /**
   *  加密过程：
   *  @return String
   */
  private static String compute(String inStr, MessageDigest md5)
  {
    char[] charArray = inStr.toCharArray();
    byte[] byteArray = new byte[charArray.length];
    for (int i=0; i<charArray.length; i++)
    {         
      byteArray[i] = (byte) charArray[i];
    }
    
    byte[] md5Bytes = md5.digest(byteArray);
    StringBuffer hexValue = new StringBuffer();
    for(int i=0; i<md5Bytes.length; i++)
    {
      int val = (md5Bytes[i] ) & 0xff; 
      if (val < 16) hexValue.append("0");
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString();
  }
  
  /**
   * MD5加密函数，供外部调用使用
   * @param strPasswd 要进行加密的字符串
   * @return 加密后的字符串
   * @throws NoSuchAlgorithmException
   */
  public static String EncodeString(String strPasswd) throws NoSuchAlgorithmException
  {
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    String postString = compute(strPasswd,md5);
    return postString;
  }
  
  public static String getMD5Password(String strPasswd)
  {
	MessageDigest md5 = null;
	try {
		md5 = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	String postString = compute(strPasswd, md5);
	return postString;
  }
  
  public static void main(String[] args)
  {
    try
    {
      String ss = MD5Util.EncodeString( "123" );
      System.out.println( "ss:"+ss );
    }
    catch ( NoSuchAlgorithmException e )
    {
      e.printStackTrace();
    }
  }

  public static String md5(String s) {
    try {
      // Create MD5 Hash
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(s.getBytes());
      byte bytes[] = digest.digest();

      // Create Hex String
      StringBuilder hexString = new StringBuilder();
      for (byte b : bytes) {
        String hex = Integer.toHexString(0xFF & b);
        if (hex.length() == 1)
          hex = "0" + hex;
        hexString.append(hex);
      }
      return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static String sha1(String s) {
    try {
      // Create MD5 Hash
      MessageDigest digest = MessageDigest.getInstance("SHA-1");
      digest.update(s.getBytes());
      byte bytes[] = digest.digest();

      // Create Hex String
      StringBuilder hexString = new StringBuilder();
      for (byte b : bytes) {
        String hex = Integer.toHexString(0xFF & b);
        if (hex.length() == 1)
          hex = "0" + hex;
        hexString.append(hex);
      }
      return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }
}

