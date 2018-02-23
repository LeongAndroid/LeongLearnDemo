package com.leong.leonglearndemo.volley;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.leong.leonglearndemo.R;
import com.leong.leonglearndemo.retrofit2.ThemeHelper;
import com.leong.leonglearndemo.retrofit2.TsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 参考http://blog.csdn.net/itachi85/article/details/51043704，http://www.jianshu.com/p/a966d152b941
 *
 * StringRequest用法JsonRequest用法
 *
 * JsonObjectRequest 用来接收和发送JsonObject类型的数据
   JsonArrayRequest 用来接收和发送JsonArray类型的数据
   StringRequest 用来接收和发送响应主体为String的数据
 *
 *
 *
 * http://blog.csdn.net/guolin_blog/article/details/17482165
 * ImageLoader使用方法介绍
 *
 * http://blog.csdn.net/onlysnail/article/details/47905375
 *
 */

public class VolleyActivity extends AppCompatActivity {
    private static final String TAG = "VolleyDemo";
    private Button mGetButton = null;
    private Button mPostButton = null;
    private static final String requestTAG = "volley-test";
    private TextView mTextView;
    private ImageView imageView;
    private static final String URI = "http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2016-10-15.110808.897/preview/7.jpg";
    private NetworkImageView networkImageView = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_demo);
        mTextView = (TextView)this.findViewById(R.id.data);
        mGetButton = (Button)this.findViewById(R.id.volley_Get);
        mGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVolleyTsData();
                testString();
                imageRequest();
                imageLoaderRequest();
            }
        });
        imageView = (ImageView)this.findViewById(R.id.pic);
        networkImageView = (NetworkImageView)this.findViewById(R.id.pic2);

        mPostButton = (Button)this.findViewById(R.id.volley_Post);
        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postThemeDataSuccess1();
            }
        });
    }


    private void getVolleyTsData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "http://ts.mobile.sogou.com/query?pid=?&num=15",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /// 在UI thread上
                        int count = response.length();
                        Log.d(TAG, "count = "+count);
                        for (int i=0; i<count; i++) {
                            JSONObject myjObject = null;
                            try {
                                myjObject = response.getJSONObject(i);
                                String kwd = myjObject.getString("kwd");
                                String url = myjObject.getString("url");
                                TsResponse tsRespons = new TsResponse();
                                tsRespons.setKwd(kwd);
                                tsRespons.setUrl(url);
                                Log.d(TAG, "tsRespons = "+tsRespons.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            mTextView.setText(response.getJSONObject(0).getString("kwd"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error = "+error);
            }
        });
        ///设置tag，方便取消对应tag的request
        jsonArrayRequest.setTag(requestTAG);
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void testString() {
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                ///com.android.volley.ServerError
                ///AuthFailureError：如果在做一个HTTP的身份验证，可能会发生这个错误。
                //NetworkError：Socket关闭，服务器宕机，DNS错误都会产生这个错误。
                //NoConnectionError：和NetworkError类似，这个是客户端没有网络连接。
                //ParseError：在使用JsonObjectRequest或JsonArrayRequest时，如果接收到的JSON是畸形，会产生异常。
                //SERVERERROR：服务器的响应的一个错误，最有可能的4xx或5xx HTTP状态代码。
                //TimeoutError：Socket超时，服务器太忙或网络延迟会产生这个异常。默认情况下，Volley的超时时间为2.5秒。如果得到这个错误可以使用RetryPolicy
            }
        });
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(mStringRequest);
    }

    private void imageRequest() {
        ImageRequest request = new ImageRequest(URI,new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        request.setTag("image");
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    private void imageLoaderRequest() {
        //实例化ImageLoader
        ImageLoader imageLoader = new ImageLoader(MyVolleyHelper.getInstance(getApplicationContext()).getRequestQueue(),
                new BitmapCache());
        boolean debugNetImage = true;//Test code
        if (debugNetImage) {
            networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
            networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
            networkImageView.setImageUrl(URI, imageLoader);
        }else {
            //设置监听器
            ImageLoader.ImageListener listener =
                    ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
            //3.获取图片
            imageLoader.get(URI, listener);
        }

    }


    private void gsonRequest() {
//        GsonRequest<T> gsonRequest=new GsonRequest<T>
//                (Request.Method.POST, AppConfig.APIURL, ResultModel.class, null, new Response.Listener<ResultModel>() {
//
//                    @Override
//                    public void onResponse(ResultModel response) {
//                        VolleyLog.e("Error: %s", "成功"+ response);
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        VolleyLog.e("Error: %s", "失败"+ error);
//                    }
//                }) {
//
//            @Override
//            protected Map<String, String> getParams()
//                    throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                //Post参数
//                return params;
//            }

        }


    private void postThemeDataFail1() {
        Log.d(TAG, "postThemeData");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://xxxx",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "postThemeData onResponse");
                if (response != null) {
                    Log.d(TAG, "response = " + response.toString());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error = " + error);
            }
        }) {
            //将参数存储到map中然后返回，系统会自动调用这个方法，将参数传递出去
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put(ThemeHelper.PARAM_region, "cn");
                map.put(ThemeHelper.PARAM_mediatype, "2");
                map.put("pagenum", "1");
                map.put("pageindex", "0");
                map.put(ThemeHelper.IMEI_PARAM, "863408022864640");
                map.put(ThemeHelper.PARAM_language, "zh");
                map.put(ThemeHelper.PARAM_dpi, "xxhdpi");
                map.put(ThemeHelper.PARAM_resolution, "1080" + "*" + "1920");
                map.put(ThemeHelper.PARAM_hwmainkeys, "1");
                map.put(ThemeHelper.PARAM_apkversion, "1.0");
                map.put(ThemeHelper.PARAM_templateversion, "1");
                map.put(ThemeHelper.PRODUCTBRAND_PARAM, /*TDeviceInfo.getBrand()*/"");
                map.put(ThemeHelper.PRODUCTNAME_PARAM, "");
                map.put(ThemeHelper.PRODUCTMANUFACTURER_PARAM, "");
                map.put(ThemeHelper.CUSTOMBUILDVERSION_PARAM, "");
                map.put(ThemeHelper.INTERNALBUILDVERSION_PARAM, "");
                return map;
            }

        };

        jsonObjectRequest.setTag("post");
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void postThemeDataFail2() {
        Log.d(TAG, "postThemeData3");

        Map<String, String> map = new HashMap<>();
        map.put(ThemeHelper.PARAM_region, "cn");
        map.put(ThemeHelper.PARAM_mediatype, "2");
        map.put("pagenum", "1");
        map.put("pageindex", "0");
        map.put(ThemeHelper.IMEI_PARAM, "863408022864640");
        map.put(ThemeHelper.PARAM_language, "zh");
        map.put(ThemeHelper.PARAM_dpi, "xxhdpi");
        map.put(ThemeHelper.PARAM_resolution, "1080" + "*" + "1920");
        map.put(ThemeHelper.PARAM_hwmainkeys, "1");
        map.put(ThemeHelper.PARAM_apkversion, "1.0");
        map.put(ThemeHelper.PARAM_templateversion, "1");
        map.put(ThemeHelper.PRODUCTBRAND_PARAM, /*TDeviceInfo.getBrand()*/"");
        map.put(ThemeHelper.PRODUCTNAME_PARAM, "");
        map.put(ThemeHelper.PRODUCTMANUFACTURER_PARAM, "");
        map.put(ThemeHelper.CUSTOMBUILDVERSION_PARAM, "");
        map.put(ThemeHelper.INTERNALBUILDVERSION_PARAM, "");
        JSONObject jsonObject = new JSONObject(map);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://xxxx",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "postThemeData onResponse");
                if (response != null) {
                    Log.d(TAG, "response = " + response.toString());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error = " + error);
            }
        }) ;

        jsonObjectRequest.setTag("post");
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    private void postThemeDataSuccess1() {
        Log.d(TAG, "postThemeDataSuccess1");
        Map<String, String> map = getParamPost();

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST, "http://xxxx",
                map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "postThemeData2 onResponse");
                if (response != null) {
                    Log.d(TAG, "response = " + response.toString());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error = " + error);
            }
        });

        jsonObjectRequest.setTag("post");
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    private void postThemeDataSuccess2() {
        Log.d(TAG, "postThemeDataSuccess2");
        Map<String,String> map = getParamPost();
        String params = appendParameter(
                "http://xxxx",map);
        Log.d(TAG, "params = "+params);
        MyJsonObjectRequest jsonObjectRequest = new MyJsonObjectRequest(
                "http://xxxx", params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    Log.d(TAG, "postThemeDataSuccess2 response = " + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void postThemeDataSuccess3() {
        Log.d(TAG, "postThemeDataSuccess3");
        Map<String,String> map = getParamPost();
        final String mRequestBody  = appendParameter(
                "http://xxxx",map);
        Log.d(TAG, "params = "+mRequestBody);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://xxxx",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "postThemeData onResponse");
                if (response != null) {
                    Log.d(TAG, "response = " + response.toString());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error = " + error);
            }
        }) {
            @Override
            public byte[] getBody() {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            mRequestBody, PROTOCOL_CHARSET);
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
            }
        };
        jsonObjectRequest.setTag("post");
        MyVolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }



    private String appendParameter(String url,Map<String,String> params){
        Uri uri = Uri.parse(url);
        Uri.Builder builder = uri.buildUpon();
        for(Map.Entry<String,String> entry:params.entrySet()){
            builder.appendQueryParameter(entry.getKey(),entry.getValue());
        }
        return builder.build().getQuery();
    }


    private Map<String,String> getParamPost() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ThemeHelper.PARAM_region, "cn");
        map.put(ThemeHelper.PARAM_mediatype, "2");
        map.put("pagenum", "1");
        map.put("pageindex", "0");
        map.put(ThemeHelper.IMEI_PARAM, "863408022864640");
        map.put(ThemeHelper.PARAM_language, "zh");
        map.put(ThemeHelper.PARAM_dpi, "xxhdpi");
        map.put(ThemeHelper.PARAM_resolution, "1080" + "*" + "1920");
        map.put(ThemeHelper.PARAM_hwmainkeys, "1");
        map.put(ThemeHelper.PARAM_apkversion, "1.0");
        map.put(ThemeHelper.PARAM_templateversion, "1");
        map.put(ThemeHelper.PRODUCTBRAND_PARAM, /*TDeviceInfo.getBrand()*/"");
        map.put(ThemeHelper.PRODUCTNAME_PARAM, "");
        map.put(ThemeHelper.PRODUCTMANUFACTURER_PARAM, "");
        map.put(ThemeHelper.CUSTOMBUILDVERSION_PARAM, "");
        map.put(ThemeHelper.INTERNALBUILDVERSION_PARAM, "");
        return map;
    }

    @Override
    protected void onStop() {
        super.onPause();
        MyVolleyHelper.getInstance(getApplicationContext()).getRequestQueue().cancelAll(requestTAG);
        MyVolleyHelper.getInstance(getApplicationContext()).getRequestQueue().cancelAll("image");
        MyVolleyHelper.getInstance(getApplicationContext()).getRequestQueue().cancelAll("post");
    }
}
