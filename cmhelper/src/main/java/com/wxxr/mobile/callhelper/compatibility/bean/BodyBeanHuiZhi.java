package com.wxxr.mobile.callhelper.compatibility.bean;

// 短信回执实体
public class BodyBeanHuiZhi {
    public Integer mstyle;//短信类型  收 发      0:已送达 1：未送达  3：发送出去的短信
    public String address;//短信号码
    public Integer count;//短信数量
    public String content;//短信内容
    public long cdate;// 短信时间
    public Integer bodyID;//
    public String amonth;//短信时间  月份
    public String tosomebody;//致某某  （短信回执内容中手机号码）
    public Integer state; //短信状态  已读、未读   0：已读  1：未读

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof BodyBeanHuiZhi))
            return false;

        BodyBeanHuiZhi p = (BodyBeanHuiZhi) o;

        if (p.tosomebody.equals(this.tosomebody)) {
            return true;
        } else {
            return false;
        }

    }

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
}
