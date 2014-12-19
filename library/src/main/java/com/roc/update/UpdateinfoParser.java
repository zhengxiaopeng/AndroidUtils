package com.roc.update;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

/**
 * 更新信息解析类
 * 
 * @author Mr.Zheng
 * @date 2014年7月9日10:57:13
 */
public class UpdateinfoParser {
	/**
	 * 解析返回UpdateInfo类
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	protected static UpdateInfo getUpdateInfo(InputStream is) throws Exception {
		UpdateInfo info = new UpdateInfo();
		XmlPullParser xmlPullParser = Xml.newPullParser();
		xmlPullParser.setInput(is, "UTF-8");
		int type = xmlPullParser.getEventType();
		// 只要事件类型没到结束标识，循环解析节点
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_TAG:
				if (xmlPullParser.getName().equals("version")) // version节点
				{
					info.setVersion(xmlPullParser.nextText().trim());
				} else if (xmlPullParser.getName().equals("description"))// description节点
				{
					info.setDescription(xmlPullParser.nextText().trim());
				} else if (xmlPullParser.getName().equals("apkurl"))// apkurl节点
				{
					info.setUrl(xmlPullParser.nextText().trim());
				}
				break;

			default:
				break;
			}
			type = xmlPullParser.next();
		}
		return info;
	}
}
