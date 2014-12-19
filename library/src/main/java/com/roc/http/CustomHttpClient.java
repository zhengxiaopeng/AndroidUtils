package com.roc.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.roc.common.DebugLog;

/**
 * 
 * @author Mr.Zheng
 * @date 2014年7月13日12:05:00
 */
public class CustomHttpClient {
	private static final String CHARSET_UTF8 = HTTP.UTF_8;
	private static final String CHARSET_GB2312 = "GB2312";
	private static HttpClient customerHttpClient;
	private static HttpEntity urlEncoded;

	private CustomHttpClient() {

	}

	/**
	 * 通过GET方式获取HttpResponse
	 * 
	 * @param context
	 * @param url
	 * @param nameValuePairs
	 * @return HttpResponse
	 */
	public static HttpResponse getHttpResponseByGET(String url, NameValuePair... nameValuePairs) {
		DebugLog.v(url);
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			if (nameValuePairs != null && nameValuePairs.length > 0) {
				sb.append("?");
				for (int i = 0; i < nameValuePairs.length; i++) {
					if (i > 0) {
						sb.append("&");
					}
					sb.append(String.format("%s=%s", nameValuePairs[i].getName(),
							nameValuePairs[i].getValue()));
				}
			}
			// HttpGet连接对象
			HttpGet httpRequest = new HttpGet(sb.toString());
			// 取得HttpClient对象
			HttpClient httpclient = getHttpClient();
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);

			return httpResponse;
		} catch (UnsupportedEncodingException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过POST方法获取HttpResponse
	 * 
	 * @param context
	 * @param url
	 * @param nameValuePairsList
	 * @return HttpResponse
	 */
	public static HttpResponse getHttpResponseByPOST(String url, List<NameValuePair> nameValuePairsList) {
		DebugLog.v(url);
		try {
			UrlEncodedFormEntity urlEncoded = new UrlEncodedFormEntity(nameValuePairsList, CHARSET_UTF8);
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(urlEncoded);
			HttpClient client = getHttpClient();
			HttpResponse response = client.execute(httpPost);

			return response;
		} catch (UnsupportedEncodingException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过POST方式获取请求的返回String字符串数据
	 * 
	 * @param context
	 * 
	 * @param url
	 * @param nameValuePairsList
	 * @return
	 */
	public static String PostFromWebByHttpClient(String url, List<NameValuePair> nameValuePairsList) {
		HttpResponse response = getHttpResponseByPOST(url, nameValuePairsList);
		DebugLog.v(response.getStatusLine().getStatusCode() + "");
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new RuntimeException("请求失败");
		}
		HttpEntity resEntity = response.getEntity();
		try {
			return (resEntity == null) ? null : EntityUtils.toString(resEntity, CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			DebugLog.w(e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			DebugLog.w(e.getMessage());
			return null;
		} catch (IOException e) {
			throw new RuntimeException("连接失败");
		}
	}

	/**
	 * 通过GET方式获取请求的返回String字符串数据
	 * 
	 * @param context
	 * 
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws Exception
	 */
	public static String getFromWebByHttpClient(String url, NameValuePair... nameValuePairs) throws Exception {
		DebugLog.v(url);
		HttpResponse httpResponse = getHttpResponseByGET(url, nameValuePairs);
		// 请求成功
		if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new RuntimeException("连接失败");
		}
		return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF8);
	}

	/**
	 * 获取状态码
	 * 
	 * @param httpResponse
	 * @return 状态码
	 */
	public static int getStatusCode(HttpResponse httpResponse) {
		int statusCode = -1;
		try {
			httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
			DebugLog.e(e.getMessage());
			e.printStackTrace();
		}
		return statusCode;
	}

	/**
	 * 创建httpClient实例
	 * 
	 * @param context
	 *            用来判断手机是否是wifi连接
	 * @return
	 */
	public static synchronized HttpClient getHttpClient() {
		if (null == customerHttpClient) {
			HttpParams params = new BasicHttpParams();
			/* 设置一些基本参数 */
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, CHARSET_UTF8);
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpProtocolParams.setUserAgent(params,
					"Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
							+ "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
			/* 超时设置 */
			/* 从连接池中取连接的超时时间 不要设置太长 */
			ConnManagerParams.setTimeout(params, 1000);
			/* 连接超时 */
			int ConnectionTimeOut = 15000;
			if (!HttpUtil.isWifiDataEnable())
				ConnectionTimeOut = 10000;
			HttpConnectionParams.setConnectionTimeout(params, ConnectionTimeOut);
			/* 请求超时 */
			HttpConnectionParams.setSoTimeout(params, 4000);
			/* 设置我们的HttpClient支持HTTP和HTTPS两种模式 */
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

			// 使用线程安全的连接管理来创建HttpClient
			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
			customerHttpClient = new DefaultHttpClient(conMgr, params);
		}

		return customerHttpClient;
	}

}
