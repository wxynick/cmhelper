package com.wxxr.mobile.callhelper.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.wxxr.mobile.core.log.api.Trace;

/**
 * @author cuizaixi
 *
 */
public class PullParseUtil {
    private static final Trace log = Trace.register(PullParseUtil.class);

    private XmlPullParser parser;

    public PullParseUtil() {
        parser = Xml.newPullParser();
    }
    public List<XmlDescriptor> doParse(InputStream is) {
        String tagName = null;
        List<XmlDescriptor> reminderTexts = null;
        XmlDescriptor reminderText = null;
        try {
            parser.setInput(is, "utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    reminderTexts = new ArrayList<XmlDescriptor>();
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    tagName = parser.getName();
                    if ("province".equals(tagName)) {
                        reminderText = new XmlDescriptor();
                    }
                    else if ("name".equals(tagName)) {
                        reminderText.setName( parser.nextText().trim());
                    }
                    else if ("remindertext".equals(tagName)) {
                        reminderText.setPattern( parser.nextText().trim());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("province".equals(parser.getName())) {
                        reminderTexts.add(reminderText);
                    }
                    break;
                }
                eventType = parser.next();
            }
            return reminderTexts;
        } catch (Exception e) {
            log.error(e.getMessage());
            
        }
        return null;
    }
}
