package com.wxxr.mobile.callhelper.service;

/**
 * 导入服务，在系统第一次进入时，把该导入的短信重新导入一遍
 * @author fudapeng
 */
public interface IHistoryDataImportService {
	/**
	 * 导入数据
	 * @return
	 */
	boolean importHistroyData();
	
	/**
	 * 是否需要导入
	 * @return
	 */
	boolean isNeedImport();
	
	/**
	 * 设置需要导入
	 * @param value
	 */
	void setNeedImport(boolean value);
}
