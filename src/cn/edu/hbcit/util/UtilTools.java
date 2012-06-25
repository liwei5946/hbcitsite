package cn.edu.hbcit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: �ӱ���ҵְҵ����ѧԺ</p>
 *
 * @author ���� : liwei5946@gmail.com
 * @version ����ʱ�䣺Jun 21, 2009 12:34:21 AM
 */
public class UtilTools {
	/**
	 * �ж��ַ����Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str)
	{
	Pattern pattern = Pattern.compile("[0-9]*");
	Matcher isNum = pattern.matcher(str);
	if( !isNum.matches() ){
		return false;
	}
		return true;
	} 
	/*
	 * ��access���ݿ��У���Memo��ע���ֶ������������ȡ��
	 * @param InputStream
	 * @return StringBuilder
	 */
	public StringBuilder accessdbMemoToString(InputStream in)
	{
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		StringBuilder sb=new StringBuilder();
		String line=null;
		try {
		while((line=reader.readLine())!=null){
			sb.append(line);
			}
		} catch (IOException e) {
		e.printStackTrace();
		  }
		return sb;
	}

}
