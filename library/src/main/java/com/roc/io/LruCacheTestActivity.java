package com.roc.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpResponse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.roc.androidutils.R;
import com.roc.annotation.ContentView;
import com.roc.annotation.InitView;
import com.roc.http.CustomHttpClient;
import com.roc.security.MD5Helper;
import com.roc.ui.BaseActivity;

public class LruCacheTestActivity extends BaseActivity implements OnClickListener {
	private DiskLruCache diskLruCache;
	@InitView(id = R.id.btn_io_write_cache, onClickListener = true)
	private Button btnWrite;
	@InitView(id = R.id.btn_io_read_cache, onClickListener = true)
	private Button btnReadWithSet;
	@InitView(id = R.id.btn_io_delete_cache, onClickListener = true)
	private Button btnDelete;
	@InitView(id = R.id.imgView_io_show)
	private ImageView imageView;
	private String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";

	@Override
	@ContentView(id = R.layout.activity_lrucache)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		diskLruCache = IOHelper.getDiskLruCache(this, "bitmap");
	}

	/**
	 * 读取缓存
	 */
	private void readDiskLruCache() {
		String key = MD5Helper.getMD5String(imageUrl);
		InputStream inputStream = null;
		try {
			DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
			if (snapshot != null) {
				inputStream = snapshot.getInputStream(0);
			}
		} catch (Exception e) {
		}
		Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
		imageView.setImageBitmap(bitmap);
	}

	/**
	 * 删除缓存<br>
	 * 完全不需要担心缓存的数据过多从而占用SD卡太多空间的问题，DiskLruCache会根据我们在调用open()
	 * 方法时设定的缓存最大值来自动删除多余的缓存
	 * 。只有你确定某个key对应的缓存内容已经过期，需要从网络获取最新数据的时候才应该调用remove()方法来移除缓存。
	 */
	private void deleteDiskLruCache() {
		try {
			String key = MD5Helper.getMD5String(imageUrl);
			diskLruCache.remove(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写进缓存
	 */
	private void writeImageToDiskLruCache() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 将图片的URL进行MD5编码,将此唯一的key作为Editor的key
				String key = MD5Helper.getMD5String(imageUrl);
				try {
					// key将会成为缓存文件的文件名，并且必须要和图片的URL是一一对应的
					DiskLruCache.Editor editor = diskLruCache.edit(key);
					if (editor != null) {
						// 0代表缓存文件的后缀名
						OutputStream outputStream = editor.newOutputStream(0);
						if (downloadUrlToStream(imageUrl, outputStream)) {
							editor.commit();
						} else {
							editor.abort();
						}
						/**
						 * 将内存中的操作记录同步到日志文件（也就是journal文件）当中。这个方法非常重要，
						 * 因为DiskLruCache能够正常工作的前提就是要依赖于journal文件中的内容
						 * 。前面在讲解写入缓存操作的时候我有调用过一次这个方法
						 * ，但其实并不是每次写入缓存都要调用一次flush()方法的
						 * ，频繁地调用并不会带来任何好处，只会额外增加同步journal文件的时间
						 * 。比较标准的做法就是在Activity的onPause()方法中去调用一次flush()方法就可以了。
						 */
						diskLruCache.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
		BufferedOutputStream out = null;
		BufferedInputStream in = null;
		try {
			HttpResponse httpResponse = CustomHttpClient.getHttpResponseByGET(urlString, null);
			in = new BufferedInputStream(httpResponse.getEntity().getContent(), 8 * 1024);
			out = new BufferedOutputStream(outputStream, 8 * 1024);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			out.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_io_write_cache:
			writeImageToDiskLruCache();
			break;
		case R.id.btn_io_read_cache:
			readDiskLruCache();
			break;
		case R.id.btn_io_delete_cache:
			deleteDiskLruCache();
			break;
		default:
			break;
		}
	}

}
