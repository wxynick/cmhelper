/**
 * 
 */
package com.wxxr.mobile.callhelper.service;

import com.wxxr.mobile.callhelper.app.bean.UserDetailBean;

/**
 * @author neillin
 *
 */
public interface IUserService {
	
	/**
	 * 获取用户详细信息
	 * @param monitor
	 */
	public UserDetailBean getUserDetail();
	
	
//	/**
//	 * 获取用户详细信息
//	 * @param monitor
//	 */
//	public boolean updateUserDetail(UserDetailBean newValue);
	
	/**
	 * 上传图片
	 * 
	 * @return
	 */
	boolean uploadIcon(String deviceId,byte[] icon);
	
	/**
	 * 下载图片
	 * 
	 * @return
	 */
    public byte[] findIcon(String deviceId);
    
    /**
     * 设置昵称
     * @param name
     */
    public void setNickName(String name);
    
    /**
     * 设置性别
     * @param name
     */
    public void setGender(String gender);
    
    /**
     * 设置年龄
     * @param age
     */
    public void setAge(int age);
    
    /**
     * 设置省份
     * @param province
     */
    public void setProvince(String province);
    

}
