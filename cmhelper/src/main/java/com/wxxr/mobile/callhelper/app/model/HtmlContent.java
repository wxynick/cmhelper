/**
 * 
 */
package com.wxxr.mobile.callhelper.app.model;

import java.io.Serializable;
import java.util.Date;

import com.wxxr.mobile.core.annotation.BindableBean;



/**
 * @author wangyan
 *
 */
@BindableBean(pkg="com.wxxr.mobile.callhelper.app.bean",className="HtmlContentBean")
public class HtmlContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String abstrct;
	private String image;
	private String source;
	private String url;
	private Date createDate;
	private String origUrl;
	
	//猜你喜欢的字段
	private String defaultPage;
	private String linkUrl;
	private String pageIndex;	
	private String listimageUrl;
	private String createtime;
	private String cheap21cityname;
	
	
	public String getCheap21cityname() {
		return cheap21cityname;
	}
	public void setCheap21cityname(String cheap21cityname) {
		this.cheap21cityname = cheap21cityname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getListimageUrl() {
		return listimageUrl;
	}
	public void setListimageUrl(String listimageUrl) {
		this.listimageUrl = listimageUrl;
	}
	public String getDefaultPage() {
		return defaultPage;
	}
	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	

	


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the abstrct
	 */
	public String getAbstrct() {
		return abstrct;
	}
	/**
	 * @param abstrct the abstrct to set
	 */
	public void setAbstrct(String abstrct) {
		this.abstrct = abstrct;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the origUrl
	 */
	public String getOrigUrl() {
		return origUrl;
	}
	/**
	 * @param origUrl the origUrl to set
	 */
	public void setOrigUrl(String origUrl) {
		this.origUrl = origUrl;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abstrct == null) ? 0 : abstrct.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((origUrl == null) ? 0 : origUrl.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		HtmlContent other = (HtmlContent) obj;
		if (abstrct == null) {
			if (other.abstrct != null)
				return false;
		} else if (!abstrct.equals(other.abstrct))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (origUrl == null) {
			if (other.origUrl != null)
				return false;
		} else if (!origUrl.equals(other.origUrl))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HtmlMessage [title=" + title + ", abstrct=" + abstrct
				+ ", image=" + image + ", source=" + source + ", url=" + url
				+ ", createDate=" + createDate + ", origUrl=" + origUrl + "]";
	}

	
	
	
	
}
