package com.rocko.update;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.rocko.common.DebugLog;
import com.rocko.http.CustomHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 更新下载类
 *
 * @author Mr.Zheng
 * @date 2014年7月11日15:57:34
 */
public class DownloadTask {
    /**
     * @param path           Url地址
     * @param filePath       文件存放路径
     * @param progressDialog 进度条 当前未使用
     * @return
     * @throws Exception
     */
    public static File downloadFile(Context context, String urlPath, String filePath, Handler handler) {
        DebugLog.v("Apk下载Url：" + urlPath + "\n文件存放地址：" + filePath);
        File file = new File(filePath);
        Message msg;
        try {
            HttpResponse response;
            response = CustomHttpClient.getHttpResponseByGET(context, urlPath);
            int statusCode = response.getStatusLine().getStatusCode();
            DebugLog.v("下载更新返回状态码：" + statusCode);
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                long total = entity.getContentLength();
                DebugLog.v("文件大小：" + total);
                InputStream is = entity.getContent();
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = -1;
                long process = 0;
                long cur = 0;
                long curFlag = -2;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    process += len;
                    cur = (long) (process * 100 / total);
                    if (0 == cur % 10) {
                        // 确保发送十次
                        if (curFlag != cur) {
                            curFlag = cur;
                            msg = Message.obtain();
                            msg.arg1 = (int) cur;
                            msg.what = UpdateService.DOWNLOAD_PROGRESS;
                            handler.sendMessage(msg);
                        }
                    }
                }
                fos.flush();
                fos.close();
                is.close();
                msg = Message.obtain();
                handler.sendEmptyMessage(UpdateService.DOWNLOAD_FINISH);
                return file;
            } else {
                // 失败
                msg = Message.obtain();
                handler.sendEmptyMessage(UpdateService.DOWNLOAD_FAILED);
            }
        } catch (Exception e) {
            // 失败
            msg = Message.obtain();
            handler.sendEmptyMessage(UpdateService.DOWNLOAD_FAILED);
            DebugLog.e(e.getMessage());
            e.printStackTrace();
        }
        return file;
    }
}
