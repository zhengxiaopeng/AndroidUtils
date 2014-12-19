package com.roc.http.volley;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.roc.androidutils.R;
import com.roc.app.MainApplication;
import com.roc.http.CustomHttpClient;

/**
 * @author Mr.Zheng
 * @date 2014年10月18日 下午7:31:52
 */
public class VolleyRequest {
	private static final VolleyRequest volleyRequestQueue = new VolleyRequest();
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private VolleyRequest() {
		mRequestQueue = Volley.newRequestQueue(MainApplication.getContext(), new HttpClientStack(
				CustomHttpClient.getHttpClient()));
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
	}

	public static VolleyRequest getInstance() {
		return volleyRequestQueue;
	}

	/**
	 * 创建发送一个StringRequest请求(GET)
	 * 
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public StringRequest newStringRequest(String url, Listener<String> listener, ErrorListener errorListener) {
		StringRequest request = new StringRequest(url, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个StringRequest请求
	 * 
	 * @param method
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public StringRequest newStringRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		StringRequest request = new StringRequest(method, url, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个JsonObjectRequest请求(GET)
	 * 
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public JsonObjectRequest newJsonObjectRequest(String url, JSONObject jsonRequest,
			Listener<JSONObject> listener, ErrorListener errorListener) {
		JsonObjectRequest request = new JsonObjectRequest(url, jsonRequest, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个JsonObjectRequest请求
	 * 
	 * @param method
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public JsonObjectRequest newJsonObjectRequest(int method, String url, JSONObject jsonRequest,
			Listener<JSONObject> listener, ErrorListener errorListener) {
		JsonObjectRequest request = new JsonObjectRequest(method, url, jsonRequest, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个JsonArrayRequest请求(GET)
	 * 
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public JsonArrayRequest newJsonArrayRequest(String url, Listener<JSONArray> listener,
			ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个ImageRequest请求(GET)
	 * 
	 * @param method
	 * @param url
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public ImageRequest newImageRequest(String url, Response.Listener<Bitmap> listener, int maxWidth,
			int maxHeight, Config decodeConfig, Response.ErrorListener errorListener) {
		ImageRequest request = new ImageRequest(url, listener, maxWidth, maxHeight, decodeConfig,
				errorListener);
		add(request);
		return request;
	}

	/**
	 * 通过ImageLoader获取图片(自动管理缓存)
	 * 
	 * @param imageView
	 * @param imgViewUrl
	 * @param defaultImageResId
	 * @param errorImageResId
	 */
	public void newImageViewLoaderRequest(ImageView imageView, String imgViewUrl, int defaultImageResId,
			int errorImageResId) {
		ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.ic_launcher,
				R.drawable.ic_launcher);
		mImageLoader.get(imgViewUrl, listener);
	}

	/**
	 * networkImageView处理
	 * 
	 * @param networkImageView
	 * @param imageUrl
	 * @param defaultImageResId
	 * @param errorImageResId
	 */
	public void newNetworkImageViewsRequest(NetworkImageView networkImageView, String imageUrl,
			int defaultImageResId, int errorImageResId) {
		networkImageView.setDefaultImageResId(defaultImageResId);
		networkImageView.setErrorImageResId(errorImageResId);
		networkImageView.setImageUrl(imageUrl, mImageLoader);
	}

	/**
	 * 创建发送一个 GsonRequest(GET)
	 * 
	 * @param url
	 * @param clazz
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(String url, Class<T> clazz, Listener<T> listener,
			ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(url, clazz, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个 GsonRequest
	 * 
	 * @param url
	 * @param clazz
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
			ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(method, url, clazz, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个 GsonRequest(GET)
	 * 
	 * @param url
	 * @param typeToken
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(String url, TypeToken<T> typeToken, Listener<T> listener,
			ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(url, typeToken, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个 GsonRequest
	 * 
	 * @param url
	 * @param typeToken
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> GsonRequest<T> newGsonRequest(int method, String url, TypeToken<T> typeToken,
			Listener<T> listener, ErrorListener errorListener) {
		GsonRequest<T> request = new GsonRequest<T>(method, url, typeToken, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个 JacksonRequest(GET)
	 * 
	 * @param url
	 * @param clazz
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> JacksonRequest<T> newJacksonRequest(String url, Class<T> clazz, Listener<T> listener,
			ErrorListener errorListener) {
		JacksonRequest<T> request = new JacksonRequest<T>(url, clazz, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个 JacksonRequest
	 * 
	 * @param method
	 * @param url
	 * @param clazz
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> JacksonRequest<T> newJacksonRequest(int method, String url, Class<T> clazz,
			Listener<T> listener, ErrorListener errorListener) {
		JacksonRequest<T> request = new JacksonRequest<T>(method, url, clazz, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个 JacksonRequest(GET)
	 * 
	 * @param url
	 * @param typeReference
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> JacksonRequest<T> newJacksonRequest(String url, TypeReference<T> typeReference,
			Listener<T> listener, ErrorListener errorListener) {
		JacksonRequest<T> request = new JacksonRequest<T>(url, typeReference, listener, errorListener);
		add(request);
		return request;
	}

	/**
	 * 创建发送一个 JacksonRequest
	 * 
	 * @param method
	 * @param url
	 * @param typeReference
	 * @param listener
	 * @param errorListener
	 * @return
	 */
	public <T> JacksonRequest<T> newJacksonRequest(int method, String url, TypeReference<T> typeReference,
			Listener<T> listener, ErrorListener errorListener) {
		JacksonRequest<T> request = new JacksonRequest<T>(method, url, typeReference, listener, errorListener);
		add(request);
		return request;
	}

	private <T> Request<T> add(Request<T> request) {
		return mRequestQueue.add(request);
	}

	/**
	 * 中断、取消所有请求
	 * 
	 * @param tag
	 */
	public void cancelAll(Object tag) {
		mRequestQueue.cancelAll(tag);
	}

}
