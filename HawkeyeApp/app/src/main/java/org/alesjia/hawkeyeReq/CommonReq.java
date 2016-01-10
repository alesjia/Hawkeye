package org.alesjia.hawkeyeReq;

import android.os.DropBoxManager;
import android.provider.Settings;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.alesjia.listener.HawkListener;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lxl on 2016/1/9.
 */
public class CommonReq<T> extends Request<T> {

    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{"utf-8"});
    private HawkListener<T> mListener;
    private Object mReqObj;
    private Class<T> mClazz;

    public CommonReq(int method, String url, Object reqObj, HawkListener<T> listener, Class<T> mClazz) {
        super(method, url, listener);
        this.mListener = listener;
        this.mReqObj = reqObj;
        this.mClazz = mClazz;
        System.out.println("lxl CommonReq()");
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        System.out.println("lxl parseNetworkResponse()");
        try {
            String content = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            T response = JSON.parseObject(content, mClazz, Feature.IgnoreNotMatch);
            return Response.success(response, getCacheEntry());
        } catch (UnsupportedEncodingException ex) {
            return Response.error(new ParseError(ex));
        }

    }

    @Override
    protected void deliverResponse(T t) {
        System.out.println("lxl deliverResponse()");
        if (null != mListener) {
            mListener.onResponse(t);
        }
    }

    @Override
    public String getBodyContentType() {
        System.out.println("lxl getBodyContentType()");
        return PROTOCOL_CONTENT_TYPE;
    }

    public HashMap<String, String> parseParams() {
        HashMap<String, String> paramsHashMap = new HashMap<>();
        String objStr = JSON.toJSONString(mReqObj, SerializerFeature.NotWriteDefaultValue);
        HashMap<String, Object> paramsFromObj = JSON.parseObject(objStr, HashMap.class, Feature.IgnoreNotMatch);
        Iterator iterator = paramsFromObj.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            paramsHashMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return paramsHashMap;
    }
}
