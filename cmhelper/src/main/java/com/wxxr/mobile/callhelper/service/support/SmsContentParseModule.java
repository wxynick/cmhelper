package com.wxxr.mobile.callhelper.service.support;

import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.service.ISmsContentParseService;
import com.wxxr.mobile.callhelper.utils.StringUtil;
import com.wxxr.mobile.callhelper.utils.Tools;
import com.wxxr.mobile.core.async.api.IProgressMonitor;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

public class SmsContentParseModule extends AbstractModule<ICallHeplerAppContext> implements ISmsContentParseService{
	private static final Trace log = Trace.register(SmsContentParseModule.class);

	
	@Override
	protected void initServiceDependency() {
	}


	@Override
	protected void startService() {
		context.registerService(ISmsContentParseService.class, this);

	}

	@Override
	protected void stopService() {
		context.unregisterService(ISmsContentParseService.class, this);
	}
	//连续执行几十次会很有问题，可以直接使用 parseSmsContent(String )
	public String parseSmsContentAsyn(final String content) {
		return parseSmsContent(content);
	}
	//1.获取手机号 
	//2.将手机号  替换为  （联系人）+手机号+归属地
	public String parseSmsContent(String content) {
		
		String msisdn =Tools.getMisdnByContent(content);  
		if (StringUtil.isEmpty(msisdn)){
			return content;
		}
		String replace_src=null;
		//查找通讯录名称
		String name=Tools.getContactsName(context.getApplication().getAndroidApplication(),msisdn);
		if (StringUtil.isNotEmpty(name)){
			replace_src=msisdn+"("+name+")";
		}else{
			replace_src=msisdn;
		}
		//msisdn
//	    Region r=getGuiShuDiService().getRegionByMsisdn(msisdn);
//	    if (r!=null){
//	    	replace_src=replace_src+"("+r.getRegionName()+")";
//	    }
	    return content.replaceFirst(msisdn, replace_src);
		
	}
}
