package org.springframework.arcln.sp;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-06
 */
public class LogUtil{
	public static void println(Object obj,String msg){
		System.out.println("**********"+obj.getClass().getName()+","+msg);
	}
}
