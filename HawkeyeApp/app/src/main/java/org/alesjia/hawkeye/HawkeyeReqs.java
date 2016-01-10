package org.alesjia.hawkeye;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.alesjia.bean.AddColumnRequest;
import org.alesjia.bean.AddEntityRequest;
import org.alesjia.bean.ListEntityResponse;
import org.alesjia.bean.Response;
import org.alesjia.hawkeye.HawkeyeConstant;
import org.alesjia.hawkeyeReq.CommonGetReq;
import org.alesjia.hawkeyeReq.CommonPostReq;
import org.alesjia.listener.HawkListener;

/**
 * Created by lxl on 2016/1/9.
 */
public class HawkeyeReqs {
    private static RequestQueue mQueue;

    public static String listEntity(Context context, org.alesjia.bean.Request request, HawkListener<ListEntityResponse> listener, Class<ListEntityResponse> response) {
        CommonGetReq<ListEntityResponse> volleyReq = new CommonGetReq<>(HawkeyeConstant.LIST_ENTITY, request, listener, response);
        createReqInQueue(context, volleyReq);
        return HawkeyeConstant.LIST_ENTITY;
    }

    public static String addEntity(Context context, AddEntityRequest request, HawkListener<Response> listener, Class<Response> response) {
        CommonPostReq<Response> volleyReq = new CommonPostReq<>(HawkeyeConstant.ADD_ENTITY, request, listener, response);
        createReqInQueue(context, volleyReq);
        return HawkeyeConstant.ADD_ENTITY;
    }

    public static String addColumn(Context context, AddColumnRequest request, HawkListener<Response> listener, Class<Response> response) {
        CommonPostReq<Response> volleyReq = new CommonPostReq<>(HawkeyeConstant.ADD_COLUMN_NAME, request, listener, response);
        createReqInQueue(context, volleyReq);
        return HawkeyeConstant.ADD_ENTITY;
    }

    private static void createReqInQueue(Context context, Request<?> request) {
        RequestQueue queue = getQueue(context);
        request.setTag(context);
        queue.add(request);
    }

    private static RequestQueue getQueue(Context context) {
        if (null == mQueue) {
            mQueue = Volley.newRequestQueue(context);
        }
        return mQueue;
    }
}
