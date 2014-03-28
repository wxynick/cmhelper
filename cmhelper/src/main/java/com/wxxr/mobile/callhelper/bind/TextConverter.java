/**
 * 
 */
package com.wxxr.mobile.callhelper.bind;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.wxxr.mobile.core.ui.api.IValueConvertor;
import com.wxxr.mobile.core.ui.api.IWorkbenchRTContext;
import com.wxxr.mobile.core.ui.api.ValidationException;

/**
 * @author neillin
 *
 */
public class TextConverter implements IValueConvertor<String, String> {

	private String format = "yyyy/MM/dd HHmmss";
	/* (non-Javadoc)
	 * @see com.wxxr.mobile.core.ui.api.IValueConvertor#destroy()
	 */
	@Override
	public void destroy() {

	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.core.ui.api.IValueConvertor#getSourceType()
	 */
	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.core.ui.api.IValueConvertor#getTargetType()
	 */
	@Override
	public Class<String> getTargetType() {
		return String.class;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.core.ui.api.IValueConvertor#init(com.wxxr.mobile.core.ui.api.IWorkbenchRTContext, java.util.Map)
	 */
	@Override
	public void init(IWorkbenchRTContext ctx, Map<String, Object> map) {
//		if (map.containsKey("format")) {
//			this.format = (String) map.get("format");
//		}
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.core.ui.api.IValueConvertor#toSourceTypeValue(java.lang.Object)
	 */
	@Override
	public String toSourceTypeValue(String s) throws ValidationException {
		return s;
	}

	/* (non-Javadoc)
	 * @see com.wxxr.mobile.core.ui.api.IValueConvertor#toTargetTypeValue(java.lang.Object)
	 */
	@Override
	public String toTargetTypeValue(String size) {
		return "(" + size + ")";
	}

}
