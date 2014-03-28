package com.wxxr.mobile.callhelper.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.database.Cursor;

import com.wxxr.mobile.android.app.AppUtils;
import com.wxxr.mobile.callhelper.Contants;
import com.wxxr.mobile.callhelper.ICallHeplerAppContext;
import com.wxxr.mobile.callhelper.R;
import com.wxxr.mobile.callhelper.app.bean.RegionBean;
import com.wxxr.mobile.callhelper.compatibility.bean.BodyBean;
import com.wxxr.mobile.callhelper.compatibility.bean.BodyBeanHuiZhi;
import com.wxxr.mobile.callhelper.compatibility.constant.Constant;
import com.wxxr.mobile.callhelper.compatibility.dao.HuiZhiBaoGaoDao;
import com.wxxr.mobile.callhelper.compatibility.dao.LouHuaDao;
import com.wxxr.mobile.callhelper.compatibility.event.ImporDataOKEvent;
import com.wxxr.mobile.callhelper.constant.Sms;
import com.wxxr.mobile.callhelper.service.IAffiliationAreaService;
import com.wxxr.mobile.callhelper.service.IHistoryDataImportService;
import com.wxxr.mobile.callhelper.service.IMissCallService;
import com.wxxr.mobile.callhelper.service.ISmsContentParseService;
import com.wxxr.mobile.callhelper.utils.SMSFilter;
import com.wxxr.mobile.callhelper.utils.StringUtil;
import com.wxxr.mobile.core.event.api.IEventRouter;
import com.wxxr.mobile.core.log.api.Trace;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;
import com.wxxr.mobile.core.util.StringUtils;
import com.wxxr.mobile.preference.api.IPreferenceManager;

/**
 * 
 * @author fudapeng
 *
 */
public class HistoryDataImportService extends AbstractModule<ICallHeplerAppContext> implements
		IHistoryDataImportService {
	
	private static final Trace log = Trace.register(HistoryDataImportService.class);
	
	private boolean needimport;
	@Override
	public boolean importHistroyData() {
		 boolean sucess = false;
		 if(needimport){
	           if(log.isDebugEnabled()){
	              log.debug("invoke import old start ...... ");
	           }
	           try{
	              needimport=false;
	              SimpleDateFormat format_month = new SimpleDateFormat("MM");
	              String number = "";
	              String body = "";
	              String huizhi_number = "";
	              int read;
	              int smstate = -1;// 0 成功的回执，1失败，-1，不是回执的短信
	              long date;
	              Context mcontext =context.getApplication().getAndroidApplication().getBaseContext();
                  HuiZhiBaoGaoDao   hdao = HuiZhiBaoGaoDao.getInstance(mcontext);
                  LouHuaDao  ldao = LouHuaDao.getInstance(mcontext);
                  IAffiliationAreaService iguishudiservice = context
                         .getService(IAffiliationAreaService.class);
                  ISmsContentParseService ismscontentparseservice = context
                         .getService(ISmsContentParseService.class);

                  // ////////////////////////处理漏接电话内容复杂，所以和短信放一起了，遍历一遍数据库////////////////////////////
                  Cursor cursorloudian = mcontext.getContentResolver().query(
                         Sms.Inbox.CONTENT_URI, null, SMSFilter.FILTER_LOUHUA_SQL,
                         null, null);
                 int sms = 0;
                 int hua = 0;
                  if (cursorloudian.moveToFirst()) {
                     int numberindex = cursorloudian.getColumnIndex(Sms.ADDRESS);
                     int bodyindex = cursorloudian.getColumnIndex(Sms.BODY);
                     int dateindex = cursorloudian.getColumnIndex(Sms.DATE);
                     int readindex = cursorloudian.getColumnIndex(Sms.READ);
                     do {
                         number = cursorloudian.getString(numberindex);
                         body = cursorloudian.getString(bodyindex);
                         date = cursorloudian.getLong(dateindex);
                         read = cursorloudian.getInt(readindex);
                         huizhi_number = "";

                         RegionBean r = iguishudiservice.getRegionBySmsContent(body);
                         if (r != null) {
                            huizhi_number = r.getPhoneNumber();
                         }
                         
                         if( SMSFilter.isSmsFailHuizhi(body, number)||(SMSFilter.isSmsOKHuizhi(body, number))) {
                            if(!hdao.hasRec(date)){
                            if(SMSFilter.isSmsFailHuizhi(body, number)){                          
                            smstate = 1;
                             } else if (SMSFilter.isSmsOKHuizhi(body, number)) {
                            smstate = 0;                           
                             }
                            
                            String parseBody = ismscontentparseservice
                                   .parseSmsContent(body);
                            BodyBeanHuiZhi bb = new BodyBeanHuiZhi();
                            bb.address = number;
                            if (StringUtil.isEmpty(huizhi_number)) {
                                bb.tosomebody = number;
                            } else {
                                bb.tosomebody = huizhi_number;
                            }
                            bb.content = parseBody;
                            Date day = new Date(date);
                            day.setTime(date);
                            bb.cdate = day.getTime();
                            bb.mstyle = smstate;// 未送达
                            String mmonth = format_month.format(bb.cdate);
                            bb.amonth = mmonth;
                            bb.state = read == 0 ? 1 : 0;
                            // System.out.println("content==" + bb.content
                            // + "bb.cdate=" + bb.cdate + "bb.mstyle"
                            // + bb.mstyle + "tosomebody" + bb.tosomebody);
                            hdao.insert(bb);
                            sms++;
                            }
                         }else if(getService(IMissCallService.class).isMatch(body, number)){
                        log.debug("find   IMissCallService............"+body);
                        if(!ldao.hasRecord(date)){
                         String parseBody = ismscontentparseservice
                                .parseSmsContent(body);
                         BodyBean bb = new BodyBean();
                         bb.address = number;
                         bb.content = parseBody;
                         Date day = new Date(date); 
                         day.setTime(date);
                         bb.cdate = day.getTime();
                         bb.mstyle = 0;
                         String mmonth = format_month.format(bb.cdate);
                         bb.amonth = mmonth;
                         bb.state =  read == 0 ? 1 : 0;
                         ldao.insert(bb);
                         hua++;
                         }
                         }
                     } while (cursorloudian.moveToNext());
                  }
                  cursorloudian.close();   
                  sucess = true;
                  }catch(RuntimeException e){                	 
                     if(log.isDebugEnabled()){
                         e.printStackTrace();
                         log.debug(" import data value  exception "+e.toString());
                     }
                     sucess = false;
                  }
	              getService(IEventRouter.class).routeEvent(new ImporDataOKEvent());
	              
	       }else{
	           if(log.isDebugEnabled()){
	              log.debug("not need import old data");
	           }
	       }
		 if(log.isDebugEnabled()){
             log.debug("invoke import old end ...... ");
          }
		
		return sucess;
	}

	@Override
	public boolean isNeedImport() {
		return needimport;
	}

	@Override
	public void setNeedImport(boolean value) {
		needimport = value; 
	}

	@Override
	protected void initServiceDependency() {
		addRequiredService(IPreferenceManager.class);
		addRequiredService(IAffiliationAreaService.class);
		addRequiredService(ISmsContentParseService.class);
	}
	
	private boolean createPhoneRangeDB(){
		File file = new File(Constant.SDPATH_LOCATION);
		if(!file.exists()){
			file.mkdirs();
		}
		String packageName = AppUtils.getFramework().getAndroidApplication().getPackageName();
		String path = Constant.SDPATH_LOCATION + packageName + "/" + Constant.DB_NAME;
		
		if(!(new File(path).exists())){
			
			byte[] buffer = new byte[1024];
			int readCount = 0;

			try {
				InputStream is = AppUtils.getFramework().getAndroidApplication().getResources().openRawResource(R.raw.phone_range_zip);
				ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(is));
				
				zipInputStream.getNextEntry();   
				OutputStream os = new FileOutputStream(path);
				BufferedInputStream b = new BufferedInputStream(zipInputStream);
				
				while ((readCount = b.read(buffer)) != -1) {
					os.write(buffer, 0, readCount);
				}
				os.flush();
				os.close();
			} catch (Throwable e) {
				log.error("Failed to initialize phone range db at :"+path, e);
			}
		}
		return false;
	}

	@Override
	protected void startService() {
		context.registerService(IHistoryDataImportService.class, this);
		String value = context.getService(IPreferenceManager.class).getPreference(Contants.config,"isFromLoading");
		if(StringUtils.isBlank(value)){
			setNeedImport(true);
			createPhoneRangeDB();
			boolean sucess = importHistroyData();
			if(sucess){
				setNeedImport(false);
			}
		}else{
			setNeedImport(false);
		}
	}

	@Override
	protected void stopService() {
		
		
	}

}
