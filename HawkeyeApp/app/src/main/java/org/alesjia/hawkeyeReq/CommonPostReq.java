package org.alesjia.hawkeyeReq;

import android.provider.Settings;

import com.android.volley.AuthFailureError;

import org.alesjia.listener.HawkListener;

import java.util.Map;
import java.util.Objects;

/**
 * Created by lxl on 2016/1/9.
 */
public class CommonPostReq<T> extends CommonReq<T> {
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    public CommonPostReq(String url, Object reqObj, HawkListener<T> listener, Class<T> mClazz) {
        super(Method.POST, url, reqObj, listener, mClazz);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        System.out.println("lxl getParams()" + parseParams());
        return parseParams();
    }

    @Override
    public String getBodyContentType() {
        return CONTENT_TYPE;
    }
}
