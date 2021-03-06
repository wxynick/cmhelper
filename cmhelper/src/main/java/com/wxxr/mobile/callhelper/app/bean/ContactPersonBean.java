/*
 * Generated code, don't modified !
 */
package com.wxxr.mobile.callhelper.app.bean;

import android.graphics.Bitmap;
import com.wxxr.mobile.core.bean.api.IBindableBean;
import com.wxxr.mobile.core.bean.api.IPropertyChangeListener;
import com.wxxr.mobile.core.ui.common.BindableBeanSupport;

/**
 * Generated by Bindable Bean generator
 *
 */
public class ContactPersonBean implements IBindableBean {
	
	private final BindableBeanSupport emitter = new BindableBeanSupport(this);
	private String lastName;
	private String phoneNumber;
	private String fristName;
	private Byte[] portrait;
	private String lastChar;

	public String getLastChar() {
		return lastChar;
	}

	public void setLastChar(String lastChar) {
		String old = this.lastChar;
		this.lastChar = lastChar;
		this.emitter.firePropertyChange("lastChar", old, this.lastChar);
	}

	/**
	 * @param listener
	 */
	public void addPropertyChangeListener(IPropertyChangeListener listener) {
		emitter.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 */
	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		emitter.removePropertyChangeListener(listener);
	}
	
	@Override
	public boolean hasPropertyChangeListener(IPropertyChangeListener listener) {
		return emitter.hasPropertyChangeListener(listener);
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		String old = this.lastName;
		this.lastName = lastName;
		this.emitter.firePropertyChange("lastName", old, this.lastName);
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		String old = this.phoneNumber;
		this.phoneNumber = phoneNumber;
		this.emitter.firePropertyChange("phoneNumber", old, this.phoneNumber);
	}

	/**
	 * @return the fristName
	 */
	public String getFristName() {
		return fristName;
	}

	/**
	 * @param fristName the fristName to set
	 */
	public void setFristName(String fristName) {
		String old = this.fristName;
		this.fristName = fristName;
		this.emitter.firePropertyChange("fristName", old, this.fristName);
	}

	/**
	 * @return the portrait
	 */
	public Byte[] getPortrait() {
		return portrait;
	}

	/**
	 * @param portrait the portrait to set
	 */
	public void setPortrait(Byte[] portrait) {
		Byte[] old = this.portrait;
		this.portrait = portrait;
		this.emitter.firePropertyChange("portrait", old, this.portrait);
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override   
    public String toString() {
        return "ContactPersonBean ["+
                "lastName=" + this.lastName +
                " , phoneNumber=" + this.phoneNumber +
                " , fristName=" + this.fristName +
                " , portrait=" + this.portrait +
        "]";
    }	

}
