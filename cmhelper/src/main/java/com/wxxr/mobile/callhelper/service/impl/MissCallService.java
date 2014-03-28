/**
 * 
 */
package com.wxxr.mobile.callhelper.service.impl;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import android.text.TextUtils;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.app.bean.SMSInfoBean;
import com.wxxr.mobile.callhelper.app.bean.SMSSessionGroupBean;
import com.wxxr.mobile.callhelper.app.bean.UserDetailBean;
import com.wxxr.mobile.callhelper.app.model.SMSInfo;
import com.wxxr.mobile.callhelper.app.model.SMSSessionGroup;
import com.wxxr.mobile.callhelper.app.model.SMSType;
import com.wxxr.mobile.callhelper.compatibility.bean.BodyBean;
import com.wxxr.mobile.callhelper.compatibility.constant.Constant;
import com.wxxr.mobile.callhelper.compatibility.dao.LouHuaDao;
import com.wxxr.mobile.callhelper.compatibility.dao.RemindSettingDao;
import com.wxxr.mobile.callhelper.constant.TimeFormat;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.callhelper.service.IMissCallSmsInterceptRule;
import com.wxxr.mobile.callhelper.service.IPhoneSystemService;
import com.wxxr.mobile.callhelper.service.IUserService;
import com.wxxr.mobile.callhelper.utils.PullParseUtil;
import com.wxxr.mobile.callhelper.utils.Tools;
import com.wxxr.mobile.callhelper.utils.XmlDescriptor;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;
import com.wxxr.mobile.core.util.StringUtils;

/**
 * @author fudapeng
 *
 */
public class MissCallService extends AbstractModule<ICallHeplerAppContext> implements IMissCallService{
	private static final Trace log = Trace.register(MissCallService.class);
	
	
	private List<IMissCallSmsInterceptRule> missCallRules = new ArrayList<IMissCallSmsInterceptRule>();
	
	private boolean open = true;
	
	private RemindSettingDao rdao;
	
	private IUserService userService;
	
	private IPhoneSystemService phoneService;
	
	private LouHuaDao ldao;
	
	
	public List<IMissCallSmsInterceptRule> initMissCallRule() {

		InputStream is = null;
		try {
			is = AppUtils.getFramework().getAndroidApplication().getResources().getAssets()
					.open("ReminderForProvinces.xml");
			PullParseUtil service = new PullParseUtil();
			List<XmlDescriptor> lsrds = service.doParse(is);
			for (XmlDescriptor r : lsrds) {
				MissCallSmsInterceptRule province = new MissCallSmsInterceptRule(
						r.getName(), r.getPatterns());
				missCallRules.add(province);
			}
		} catch (IOException e) {
		}

		return missCallRules;
	}
	
	
	
	
	@Override
	public void deleteMessage(Integer msgId) {
		
	}

	@Override
	public void deleteMessage(SMSInfo msg) {
		
	}
	
	@Override
	public void deleteMessageGroup(SMSSessionGroupBean msg) {
		ldao.deleteSMSAccordingtoNumberAndMonth(msg.getPhoneNumber(),null,0);
	}

	@Override
	public void deleteMessage(String number, boolean needfreshcache) {
		
	}

	@Override
	public int getAllUnreadSize() {
		return 0;
	}

	@Override
	public int getUnreadSizeOfPhoneNumber(String num) {
		return 0;
	}

	@Override
	public void setReadMsgOfPhoneNumber(String num) {
		
	}

	@Override
	public void setUnReadMsgOfPhoneNumber(String num) {
		
	}
	
	
	
	private void processSession(SMSSessionGroupBean group){
		List<SMSInfoBean> sms = group.getSms();
		if(sms != null && !sms.isEmpty()){
			SMSInfoBean last = sms.get(sms.size() - 1);
			if(last != null){
				group.setShowSessionContent(last.getContent());
				String time1 = TimeFormat.format_yue_day.format(new Date());
				String time2 = TimeFormat.format_yue_day.format(last.getCdate());
				if (!TextUtils.isEmpty(time1) && !TextUtils.isEmpty(time2)
						&& time1.equals(time2)) {
					group.setDate(TimeFormat.format_today.format(last.getCdate()));// 时间
				} else {
					group.setDate(TimeFormat.format_time.format(last.getCdate()));// 日期
				}
				group.setIsHasUnRead(last.getRead());
//				ItemPortraitRule r = ItemPortraitRule.getInstance(AppUtils.getFramework().getAndroidApplication());
//				Bitmap headIcon = r.getItemPortrait(group.getContactName(), group.getPhoneNumber());
//				group.setPortrait(group.getContactName() + ":" + group.getPhoneNumber());
				group.setSize(sms.size());
				
			}
		}
	}
	
	boolean isTest = false;
	private List<SMSSessionGroupBean> currentList;
	private List<SMSSessionGroupBean> getMockShowList(){
		SMSSessionGroupBean b1 = new SMSSessionGroupBean();
		b1.setContactName("fudapeng");
		b1.setPhoneNumber("13651129876");
		b1.setDate(TimeFormat.format_today.format(new Date()));
		b1.setPortrait("fudapeng:13651129876");
		b1.setShowSessionContent("广东移动提醒您.*给您来电.*次.*您可按通话键或选项键直接回拨");
		b1.setIsHasUnRead(false);
		List<SMSInfoBean> sms = new ArrayList<SMSInfoBean>();
		SMSInfoBean smsBean = new SMSInfoBean();
		smsBean.setAmonth(TimeFormat.format_yue_day.format(new Date()));
		smsBean.setCdate(new Date().getTime());
		smsBean.setContent("广东移动提醒您.给您来电.次.您可按通话键或选项键直接回拨");
		smsBean.setIsStorage(true);
		smsBean.setMstyle(0);
		smsBean.setRead(true);
		smsBean.setType(SMSType.MISSCALL);
		sms.add(smsBean);
		
		smsBean = new SMSInfoBean();
		smsBean.setAmonth(TimeFormat.format_yue_day.format(new Date()));
		smsBean.setCdate(new Date().getTime());
		smsBean.setContent("1111111");
		smsBean.setIsStorage(true);
		smsBean.setMstyle(1);
		smsBean.setRead(true);
		smsBean.setType(SMSType.MISSCALL);
		
		
		sms.add(smsBean);
		b1.setSms(sms);
//		b1.setSize(b1.getSms() != null ? b1.getSms().size() : 0);
		List<SMSSessionGroupBean> array = new ArrayList<SMSSessionGroupBean>();
		array.add(b1);
		currentList = array;
		return array;
	}
	
	
	/**
	 * 先以人分组，然后排序分组以时间
	 * 排序session中短讯
	 * 格式化sessiongroup的时间显示
	 */
	@Override
	public List<SMSSessionGroupBean> getShowList() {
		if(isTest){
			return getMockShowList();
		}
		long startime = -1, endtime = -1;
		ArrayList<ArrayList<BodyBean>> bodyss = new ArrayList<ArrayList<BodyBean>>();
		LouHuaDao ldao = LouHuaDao.getInstance(AppUtils.getFramework().getAndroidApplication());
		Date d = new Date();
		d.setTime(startime);
		d.setTime(endtime);
		ArrayList<BodyBean> alast = ldao.queryAllLouHua(startime, endtime);

		Hashtable<String, SMSSessionGroupBean> person = new Hashtable<String, SMSSessionGroupBean>();
		List<SMSSessionGroupBean> groups = new ArrayList<SMSSessionGroupBean>();
		
		for (BodyBean item : alast) {
			
			String rdate = "";
			String key = item.address;
			SMSSessionGroupBean group = null;
			if (person.get(key) == null) {
				group = new SMSSessionGroupBean();
				group.setPhoneNumber(key);
				person.put(key, group);
			}else{
				group = person.get(key);
			}
			
			List<SMSInfoBean> list = group.getSms();
			if(list == null){
				list = new ArrayList<SMSInfoBean>();
				group.setSms(list);
			}
			group.getSms().add(SMSInfoBean.convert(item));
		}
		
		Enumeration<SMSSessionGroupBean> et = person.elements();
		while(et.hasMoreElements()){
			SMSSessionGroupBean group = et.nextElement();
			Collections.sort(group.getSms(), new SortSMS());
			processSession(group);
			
			int size = group.getSms().size();
			
			if(size > 0){
				SMSInfoBean smsLast =  group.getSms().get(size -1);
				group.setDate(String.valueOf(smsLast.getCdate()));////
				group.setShowSessionContent(smsLast.getContent());
			}else{
				group.setDate("0");
			}
			group.setSize(size);
			groups.add(group);
		}
		Collections.sort(groups, new SortSession());
		formatDate(groups);
		return groups;
	}
	
	private void formatDate(List<SMSSessionGroupBean> groups){
		for(SMSSessionGroupBean item : groups){
			String time1 = TimeFormat.format_yue_day.format(new Date());
			String time2 = TimeFormat.format_yue_day.format(new Date(Long.valueOf(item.getDate())));
			if (!StringUtils.isEmpty(time1) && !StringUtils.isEmpty(time2)
					&& time1.equals(time2)) {
				item.setDate((TimeFormat.format_today.format((new Date(Long.valueOf(item.getDate()))))));;// 时间
			} else {
				item.setDate((TimeFormat.format_time.format((new Date(Long.valueOf(item.getDate()))))));// 日期
			}
			item.setPhoneNumber(Tools.getMisdnByContent(item.getShowSessionContent()));
		}
		
		
	}
	
	class SortSMS implements Comparator<SMSInfoBean> {

		@Override
		public int compare(SMSInfoBean lhs, SMSInfoBean rhs) {
			long p1 = lhs.getCdate(),
				 p2 = rhs.getCdate();
			long compara = p2-p1;
			return (int)compara;
		}
	}
	
	
	class SortSession implements Comparator<SMSSessionGroupBean> {

		@Override
		public int compare(SMSSessionGroupBean lhs, SMSSessionGroupBean rhs) {
			long p1 = Long.parseLong(lhs.getDate()),
				 p2 = Long.parseLong(rhs.getDate());
			long compara = p1-p2;
			return (int)compara;
		}
	}

	@Override
	public List<SMSSessionGroupBean> getShowListByTime(long start, long end) {
		return null;
	}
	
	
	
	@Override
	public boolean isOpen() {
		int status = rdao.queryLouHuaSetting();
		if(status == 2){
			open = true;
		}else{
			open = false;
		}
		return open;
	}

	@Override
	public void switchSet() {
		open = !open;
		if(open)
			rdao.updateLouHuaStatus(2);
		else
			rdao.updateLouHuaStatus(1);
		
	}

	@Override
	public String getOpenCommand(String province) {
		String pro = province;
		if(province == null){
			UserDetailBean bean = userService.getUserDetail();
			if(bean != null){
				pro = bean.getProvince();
			}else{
				pro = Constant.PROVICE_GD;
			}
		}else{
			if(StringUtils.isNotBlank(pro)){
				if (Constant.PROVICE_GD.equals(pro)) {
					return "BLTXZS";
				} else if (Constant.PROVICE_SC.equals(pro)) {
					return "kttxzsa";
				}
				return "BLTXZS";
			}
		}
		return null;
	}

	@Override
	public void openBusiness(String content) {
		phoneService.sentMessage("10086", content);
	}

	@Override
	protected void initServiceDependency() {
		addRequiredService(IUserService.class);
		addRequiredService(IPhoneSystemService.class);
		addOptionalService(IUserService.class);
	}

	@Override
	protected void startService() {
		this.context.registerService(IMissCallService.class, this);
		initMissCallRule();
		rdao = RemindSettingDao.getInstance(AppUtils.getFramework().getAndroidApplication());
		userService = AppUtils.getService(IUserService.class);
		phoneService = AppUtils.getService(IPhoneSystemService.class);
		ldao = LouHuaDao.getInstance(AppUtils.getFramework().getAndroidApplication());
	}

	@Override
	protected void stopService() {
	}
	
	// 是否存在该省份的规则
	protected IMissCallSmsInterceptRule getRule(String provinceCode) {
		if(provinceCode!=null){
		for (IMissCallSmsInterceptRule r : missCallRules) {
			if (provinceCode.equals(r.getName())) {
				return r;
			}
		}
		}
		return null;
	}

	@Override
	public boolean isMatch(String smsContent, String targetnumber) {
		// 获取用户选择的省份代码
		// 判段用户是否选择了省份，如果该省份存在规则，则按照该规则拦截，如果用户没选省份或者省份没有规则则按照所有规则来拦截
		String code = context.getService(IUserService.class).getUserDetail().getProvince();
		IMissCallSmsInterceptRule r1 = getRule(code);
		if (r1 != null) {
			return r1.isMatch(smsContent, targetnumber);
		} else {
			for (IMissCallSmsInterceptRule r : missCallRules) {
				boolean x = r.isMatch(smsContent, targetnumber);
				if (x) {
					return true;
				}
			}
			return false;
		}
	}

	private static final long SHOW_ITME = 1000 * 60 * 5;
	@Override
	public boolean showTime(SMSInfoBean bean) {
		SMSSessionGroupBean gb = getSessionBean(bean);
		if(gb != null && gb.getSms()!= null && gb.getSms().contains((bean))){
			int index = gb.getSms().indexOf(bean);
			if(index >= 1){
				int preIndex = index - 1;
				SMSInfoBean preBean = gb.getSms().get(preIndex);
				if(bean.getCdate() - bean.getCdate() > SHOW_ITME){
					return true;
				}
			}
		}
		return false;
	}
	
	private SMSSessionGroupBean getSessionBean(SMSInfoBean bean){
		if(currentList != null){
			for(SMSSessionGroupBean gb : currentList){
				if(gb.getPhoneNumber().equalsIgnoreCase(bean.getPhoneNumber())){
					return gb;
				}
			}
		}
		return null;
	}




	@Override
	public void saveMessage(SMSInfoBean bean) {
		
	}

}
