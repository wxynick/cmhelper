package com.wxxr.mobile.callhelper.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wxxr.mobile.callhelper.service.IMissCallSmsInterceptRule;
import com.wxxr.mobile.callhelper.utils.StringUtil;
import com.wxxr.mobile.core.log.api.Trace;
/**
 * 漏接电话
 * @author zhengjincheng
 *
 */
public class MissCallSmsInterceptRule implements IMissCallSmsInterceptRule {
	private static final Trace log = Trace.register(MissCallSmsInterceptRule.class);

	private String name;
	private List<Pattern> ps=new ArrayList<Pattern>();
	private Pattern px = Pattern.compile("\\s*|\t|\r|\n");

	public  MissCallSmsInterceptRule(String name){
		this.name=name;
	}
	/**
	 * @param name 省份代码
	 * @param rules 正则表达式列表
	 */
	public  MissCallSmsInterceptRule(String name, String[] rules){
		this.name=name;
		if  (rules!=null && rules.length >0){
			for (String s:rules){
				ps.add(createPattern(s));
			}
		}
		
	}
	public void add(String rule){
		if  (StringUtil.isNotEmpty(rule)){			
				ps.add(createPattern(rule));			
		}	
	}
	
	@Override
	public boolean isMatch(String smsContent, String targetnumber) {
		boolean isMatch=false;
		for (Pattern p:ps ){
		    String str =init(smsContent);
			Matcher m = p.matcher(str);
			if (m.matches()){
				if (log.isDebugEnabled()){
					log.debug("---match true---   smsContent" +str +",name="+name+ ",targetnumber=" +targetnumber +",Pattern=" +p.toString());
				}
				return true;
			}else {
			    if (log.isDebugEnabled()){
                    log.debug("---match false---  smsContent" +str +",name="+name+",targetnumber=" +targetnumber +",Pattern=" +p.toString()  );
                }
			}
		}
		return isMatch;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String init(String str){
	    Matcher m = px.matcher(str); 
	    String after = m.replaceAll(""); 
	    return after;
	}
//	 public boolean isMatch(String s)
//	 {
//	// 您可按通话键或选项键直接回拨
//	 Pattern p = Pattern.compile(".*广东移动提醒您.*给您来电.*次.*您可按通话键或选项键直接回拨.*");
//	 Matcher m = p.matcher(s);
//	 return m.matches();
//	
//	 }
	 
	 private Pattern createPattern(String s){
		 Pattern p = Pattern.compile(s);
		 return p;
	 }
    @Override
    public String toString() {
        return "LouhuaSmsInterceptRule [name=" + name + ", ps=" + ps + "]";
    }
	 
}
