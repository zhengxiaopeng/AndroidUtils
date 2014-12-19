package com.roc.update;

import com.roc.config.Urls;
import com.roc.http.CustomHttpClient;

/**
 * 用于读取服务器上的更新信息类，应在非主线程中运行
 * 
 * @author Mr.Zheng
 * @date 2014年7月9日10:46:13
 */
public class UpdateInfoService {
	/**
	 * 获取更新信息
	 * 
	 * @param urlId
	 *            更新信息地址的资源resource id
	 * @return
	 * @throws Exception
	 */
	public UpdateInfo getUpdateInfo() throws Exception {
		String path = Urls.baseUrl + Urls.update;// mContext.getResources().getString(R.string.base_url)
													// +
													// mContext.getResources().getString(R.string.updateInfoUrl);
		return UpdateinfoParser.getUpdateInfo(CustomHttpClient.getHttpResponseByGET(path, null).getEntity()
				.getContent());
	}
}
