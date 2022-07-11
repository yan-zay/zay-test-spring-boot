package com.zay.constants;

/**
 * 公共的常量
 * @Author: ZhouAnYan
 * @Date: 2022/01/11 15:35
 * @Version 1.0
 */
public interface CommonConstants {

	/** 编码 */
	String UTF8 = "UTF-8";

	/** 成功标记 */
	Integer SUCCESS = 0;
	/** 失败标记  */
	Integer FAIL = 1;

	/**
	 * 通用状态码 1是/0否
	 * 			0取消/1领取
	 * 	停用/启用
	 * 			0停用/1启用
	 */
	String STATUS_NO = "0";
	String STATUS_YES = "1";

	String DICT_STATUS_YES = "1";
	String DICT_STATUS_NO = "0";

	/** table页下标  */
	Long TABLE_PAGE_INDEX_ONE = 1L;
	Long TABLE_PAGE_INDEX_TWO = 2L;
	Long TABLE_PAGE_INDEX_THREE = 3L;
	Long TABLE_PAGE_INDEX_FOUR = 4L;
	Long TABLE_PAGE_INDEX_FIVE = 5L;

	String NUMBER_ZERO = "0";

	String BASE_ORG_NO = "43101";

	String ORG_TYPE_01 = "01";
	String ORG_TYPE_02 = "02";
	String ORG_TYPE_03 = "03";
	String ORG_TYPE_04 = "04";
	String ORG_TYPE_05 = "05";
}
