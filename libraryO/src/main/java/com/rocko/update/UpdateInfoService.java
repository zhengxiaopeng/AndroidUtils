package com.rocko.update;

import android.content.Context;

import com.rocko.config.Urls;
import com.rocko.http.CustomHttpClient;

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
     * @param context ApplicationContext
     * @param urlId   更新信息地址的资源resource id
     * @return
     * @throws Exception
     */
    public UpdateInfo getUpdateInfo(Context context) throws Exception {
        String path = Urls.baseUrl + Urls.update;// mContext.getResources().getString(R.string.base_url)
        // +
        // mContext.getResources().getString(R.string.updateInfoUrl);
        return UpdateinfoParser.getUpdateInfo(CustomHttpClient.getHttpResponseByGET(context, path).getEntity()
                .getContent());
    }
}
