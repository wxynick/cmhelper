package com.wxxr.mobile.callhelper.app.model;

import com.wxxr.mobile.core.annotation.BindableBean;

/**
 * 
 * @author fudapeng
 *
 */
@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="RegionBean")
public class Region {
	
	private long regionId;
	private long pRegionId;
	
	private String regionName;
	private String pRegionName;

	private String brandName;
	private String phoneNumber;
	

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public long getRegionId() {
		return regionId;
	}
	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}
	public long getpRegionId() {
		return pRegionId;
	}
	public void setpRegionId(long pRegionId) {
		this.pRegionId = pRegionId;
	}
	
	public String getpRegionName() {
		return pRegionName;
	}
	public void setpRegionName(String pRegionName) {
		this.pRegionName = pRegionName;
	}
	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", pRegionId=" + pRegionId
				+ ", regionName=" + regionName + ", pRegionName=" + pRegionName
				+ ", brandName=" + brandName + ", phoneNumber=" + phoneNumber
				+ "]";
	}
	
	
}
