package com.roc.http.volley;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.roc.androidutils.R;
import com.roc.annotation.ContentView;
import com.roc.annotation.InitView;
import com.roc.common.DebugLog;
import com.roc.ui.BaseActivity;

/**
 * @author Mr.Zheng
 * @date 2014年10月17日 下午11:51:33
 */
public class HttpVolleyTestActivity extends BaseActivity {
	@InitView(id = R.id.imgView_test)
	private ImageView imgViewTest;
	@InitView(id = R.id.imgView_loader)
	private ImageView imgViewLoader;
	@InitView(id = R.id.imgView_network)
	private NetworkImageView networkImageView;

	@Override
	@ContentView(id = R.layout.activity_http_httpvolley)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		volleyTest();
		VolleyRequest.getInstance().newStringRequest(null, null, null).setTag("");
	}

	private void volleyTest() {
		/* StringRequest */
		VolleyRequest.getInstance().newStringRequest("http://www.baidu.com", new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				DebugLog.v("stringRequest: " + response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				DebugLog.e("stringRequest: " + error);
			}
		});
		/* JsonObjectRequest */
		VolleyRequest.getInstance().newJsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						DebugLog.v("JsonObjectRequest: " + response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						DebugLog.e("JsonObjectRequest: " + error);
					}
				});
		/* ImageRequest */
		VolleyRequest.getInstance().newImageRequest(
				"http://rocko-blog.qiniudn.com/%E8%AE%BE%E7%BD%AE%E5%9F%9F%E5%90%8D%E9%82%AE%E7%AE%B1_1.jpg?imageView2/2/w/600/h/600/q/100",
				new Response.Listener<Bitmap>() {
					@Override
					public void onResponse(Bitmap response) {
						imgViewTest.setImageBitmap(response);
					}
				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						imgViewTest.setImageResource(R.drawable.ic_launcher);
					}
				});
		/* ImageLoader 会自动管理缓存 */
		VolleyRequest.getInstance().newImageViewLoaderRequest(imgViewLoader,
				"http://c.hiphotos.baidu.com/image/pic/item/738b4710b912c8fcb1fa96c5fe039245d68821a8.jpg",
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		/* NetworkImageView */
		VolleyRequest.getInstance().newNetworkImageViewsRequest(networkImageView,
				"http://c.hiphotos.baidu.com/image/pic/item/279759ee3d6d55fbf060b2276f224f4a20a4dd09.jpg",
				R.drawable.ic_launcher, R.drawable.ic_launcher);

		/* GsonRequest */
		VolleyRequest.getInstance().newGsonRequest("http://www.weather.com.cn/data/sk/101010100.html",
				Weather.class, new Response.Listener<Weather>() {
					@Override
					public void onResponse(Weather weather) {
						WeatherInfo weatherInfo = weather.getWeatherinfo();
						DebugLog.v(">>>GsonRequest: ");
						DebugLog.v("city is " + weatherInfo.getCity());
						DebugLog.v("temp is " + weatherInfo.getTemp());
						DebugLog.v("time is " + weatherInfo.getTime());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						DebugLog.e("GsonRequest: " + error);
					}
				});

		/* JacksonRequest */
		VolleyRequest.getInstance().newJacksonRequest("http://www.weather.com.cn/data/sk/101010100.html",
				Weather.class, new Response.Listener<Weather>() {
					@Override
					public void onResponse(Weather weather) {
						WeatherInfo weatherInfo = weather.getWeatherinfo();
						DebugLog.v(">>>JacksonRequest: ");
						DebugLog.v("city is " + weatherInfo.getCity());
						DebugLog.v("temp is " + weatherInfo.getTemp());
						DebugLog.v("time is " + weatherInfo.getTime());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						DebugLog.e("JacksonRequest: " + error);
					}
				});
	}

	/* Json Test */
	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class Weather {
		private WeatherInfo weatherinfo;

		public WeatherInfo getWeatherinfo() {
			return weatherinfo;
		}

		public void setWeatherinfo(WeatherInfo weatherinfo) {
			this.weatherinfo = weatherinfo;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class WeatherInfo {
		private String city;

		private String temp;

		private String time;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getTemp() {
			return temp;
		}

		public void setTemp(String temp) {
			this.temp = temp;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}
	}

}
