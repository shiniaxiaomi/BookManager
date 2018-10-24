package util;

/**
 * 字符串工具类
 * @author lyj80
 *
 */
public class StringUtil {

	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null || "".equals(str.trim())) {//str为空或者str是空串
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 判断是否不是空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(str!=null && !"".equals(str.trim())) {//str为不为空并且str不是空串
			return true;
		}else {
			return false;
		}
	}
}
