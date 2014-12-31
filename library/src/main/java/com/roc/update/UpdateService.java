package com.roc.update;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import com.roc.androidutils.R;
import com.roc.common.DebugLog;
import com.roc.config.Configs;
import com.roc.config.Urls;

import java.io.File;

/**
 * 更新下载服务
 *
 * @author Mr.Zheng
 * @date 2014年7月13日21:39:49
 */
public class UpdateService extends Service {
    public static final String INTENT_FILTER = "com.roc.service.UPDATE_SERVICE";
    public static final int DOWNLOAD_PROGRESS = 1;
    public static final int DOWNLOAD_FINISH = 2;
    public static final int DOWNLOAD_FAILED = 3;
    /* 通知 */
    private Notification notification;
    private NotificationManager notificationManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initUpdate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initUpdate() {
        final DownloadHandler downloadHandler = new DownloadHandler();
        File dir = new File(Configs.EXTERNAL_STORAGE_DIRECTORY + Configs.UPDATE_DOWNLOAD_PATH);
        if (!dir.exists()) {
            // 创建文件夹
            dir.mkdirs();
        }
        sendNotification();
        final String apkSavePath = Configs.EXTERNAL_STORAGE_DIRECTORY + Configs.UPDATE_DOWNLOAD_PATH
                + Configs.NEW_APK_FILE_NAME;
        final String downloadUrl = Urls.baseUrl + Urls.downloadApk;// getResources().getString(R.string.base_url)
        // +
        // getResources().getString(R.string.downloadApkUrl);
        new Thread(new Runnable() {
            public void run() {
                try {
                    DownloadTask.downloadFile(getApplicationContext(), downloadUrl, apkSavePath, downloadHandler);
                } catch (Exception e) {
                    DebugLog.e(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 发送通知
     */
    private void sendNotification() {
        // 通知栏服务
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.notification_update);
        notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        notification.contentIntent = pendingIntent;
        notification.contentView = view;
        notification.contentView.setProgressBar(R.id.pb_notification, 100, 0, false);
        notification.contentView.setTextViewText(R.id.txt_notificaton_down_go, "0%");
        notificationManager.notify(0, notification);
    }

    /**
     * 安装apk的意图
     *
     * @param flags
     * @return
     */
    private PendingIntent getInstallPendingIntent(int flags) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.fromFile(new File(Configs.EXTERNAL_STORAGE_DIRECTORY + Configs.UPDATE_DOWNLOAD_PATH
                        + Configs.NEW_APK_FILE_NAME)), "application/vnd.android.package-archive");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, flags);
        return pendingIntent;
    }

    private class DownloadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 下载进度
                case DOWNLOAD_PROGRESS:
                    DebugLog.v("下载进度：" + msg.arg1 + "%");
                    notification.contentView.setProgressBar(R.id.pb_notification, 100, msg.arg1, false);
                    notification.contentView.setTextViewText(R.id.txt_notificaton_down_go, msg.arg1 + "%");
                    notificationManager.notify(0, notification);
                    break;
                // 下载完成
                case DOWNLOAD_FINISH:
                    DebugLog.v("下载更新完成");
                    notification.contentView.setProgressBar(R.id.pb_notification, 100, 100, false);
                    notification.contentView.setTextViewText(R.id.txt_notificaton_down_go, "100%");
                    notification.contentView.setTextViewText(R.id.txt_notificaton_down, "下载完成 点击安装");
                    notification.contentIntent = getInstallPendingIntent(Notification.FLAG_AUTO_CANCEL);
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(0, notification);
                /* 安装 */
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(
                            Uri.fromFile(new File(Configs.EXTERNAL_STORAGE_DIRECTORY
                                    + Configs.UPDATE_DOWNLOAD_PATH + Configs.NEW_APK_FILE_NAME)),
                            "application/vnd.android.package-archive");
                    UpdateService.this.startActivity(intent);
                    UpdateManager.clearQuote();
                    UpdateService.this.stopSelf();
                    break;
                // 下载失败
                case DOWNLOAD_FAILED:
                    notification.contentView.setTextViewText(R.id.txt_notificaton_down, "下载失败");
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(0, notification);
                    UpdateManager.clearQuote();
                    UpdateService.this.stopSelf();
                    break;
                default:
                    break;
            }
        }
    }
}
