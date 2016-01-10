package org.alesjia.hawkeyeReq;

import org.alesjia.listener.HawkListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lxl on 2016/1/9.
 */
public class CommonGetReq<T> extends CommonReq<T> {
    public CommonGetReq(String url, Object reqObj, HawkListener<T> listener, Class<T> mClazz) {
        super(Method.GET, url, reqObj, listener, mClazz);
    }

    @Override
    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getUrl()).append("?");
        HashMap<String, String> params = super.parseParams();
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue() + "&");
        }
        String url = sb.toString();
        if (url.endsWith("&")) {
            url = url.substring(0, url.length() - 1);
        }
        System.out.println("lxl url = " + url);
        return url;
    }
}
