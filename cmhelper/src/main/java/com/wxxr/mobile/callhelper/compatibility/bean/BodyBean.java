package com.wxxr.mobile.callhelper.compatibility.bean;

//漏接电话  实体
public class BodyBean {

    public Integer mstyle;//短信类型  收 发
    public String address;//短信号码
    public Integer count;//短信数量
    public String content;//短信内容
    public long cdate;// 短信时间
    public Integer bodyID;//
    public String amonth;//短信时间  月份
    public Integer state; //短信状态  已读、未读   0：已读  1：未读
    public String  realnum; //漏化内容中的手机号码



    public void setMstyle(Integer mstyle) {
        this.mstyle = mstyle;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCdate(long cdate) {
        this.cdate = cdate;
    }

    @Override
    public String toString() {
        return "BodyBean [mstyle=" + mstyle + ", address=" + address + ", count=" + count + ", content=" + content + ", cdate=" + cdate + ", bodyID=" + bodyID + ", amonth=" + amonth + ", state=" + state + "]";
    }

	/**
	 * @return the bodyID
	 */
	public Integer getBodyID() {
		return bodyID;
	}

	/**
	 * @param bodyID the bodyID to set
	 */
	public void setBodyID(Integer bodyID) {
		this.bodyID = bodyID;
	}

	/**
	 * @return the amonth
	 */
	public String getAmonth() {
		return amonth;
	}

	/**
	 * @param amonth the amonth to set
	 */
	public void setAmonth(String amonth) {
		this.amonth = amonth;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the mstyle
	 */
	public Integer getMstyle() {
		return mstyle;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the cdate
	 */
	public long getCdate() {
		return cdate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((amonth == null) ? 0 : amonth.hashCode());
		result = prime * result + ((bodyID == null) ? 0 : bodyID.hashCode());
		result = prime * result + (int) (cdate ^ (cdate >>> 32));
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((mstyle == null) ? 0 : mstyle.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BodyBean other = (BodyBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (amonth == null) {
			if (other.amonth != null)
				return false;
		} else if (!amonth.equals(other.amonth))
			return false;
		if (bodyID == null) {
			if (other.bodyID != null)
				return false;
		} else if (!bodyID.equals(other.bodyID))
			return false;
		if (cdate != other.cdate)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (mstyle == null) {
			if (other.mstyle != null)
				return false;
		} else if (!mstyle.equals(other.mstyle))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	
}
