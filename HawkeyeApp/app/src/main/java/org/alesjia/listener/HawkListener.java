package org.alesjia.listener;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by lxl on 2016/1/8.
 */
public abstract class HawkListener<T> implements Response.Listener<T>, Response.ErrorListener {

    private Context mContext;
    private static final int BUSINESS_SUCCESS = 0;

    public HawkListener(Context context) {
        this.mContext = context;
    }


    @Override
    final public void onErrorResponse(VolleyError volleyError) {
        System.out.println("lxl onErrorResponse() volleyError" + volleyError.getMessage());
        showDialog("提醒", "请求异常");
    }

    @Override
    final public void onResponse(T t) {
        if (t instanceof org.alesjia.bean.Response) {
            org.alesjia.bean.Response response = (org.alesjia.bean.Response) t;
            if (BUSINESS_SUCCESS == ((org.alesjia.bean.Response) t).getStatus()) {
                onBusinessSuccess(t);
                showDialog("成功", "恭喜恭喜");
            } else {
                showDialog("提醒", response.getMessage());
            }

        } else {
            showDialog("提醒", "返回类型错误");
        }
    }

    private void showDialog(String title, String warningMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(warningMsg);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public abstract void onBusinessSuccess(T t);


}
