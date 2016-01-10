package org.alesjia.hawkeye;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnEntityListener;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.OnTrackListener;
import com.baidu.trace.Trace;
import com.baidu.trace.TraceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxl on 2016/1/9.
 */
public class HawkeyeManager {

    private static HawkeyeManager mHawkeyeManager;

    private Context mContext = null;
    /**
     * 轨迹服务
     */
    private Trace mTrace = null;

    /**
     * entity标识
     */
    private String mEntityName = null;

    /**
     * 鹰眼服务ID，开发者创建的鹰眼服务对应的服务ID
     */
    private long mServiceId = HawkeyeConstant.SERVICE_ID; // serviceId为开发者创建的鹰眼服务ID

    /**
     * 轨迹服务类型（0 : 不建立socket长连接， 1 : 建立socket长连接但不上传位置数据，2 : 建立socket长连接并上传位置数据）
     */
    private int mTraceType = 2;

    /**
     * 轨迹服务客户端
     */
    private LBSTraceClient mClient = null;

    /**
     * Entity监听器
     */
    private OnEntityListener mEntityListener = null;

    /**
     * Track监听器
     */
    private OnTrackListener mTrackListener = null;

    private OnStartTraceListener mStartTraceListener = null;

    private OnStopTraceListener mStopTraceListener = null;

    private HawkeyeManager(Context context) {
        mContext = context.getApplicationContext();
        initClient();
        setInterval(1, 5);
        initEntityName();
        initTrace();
        initEntityListener();
        initStartTraceListener();
        initStopTraceListener();
        initTrackListener();
        addEntity();
    }

    public static HawkeyeManager getInstance(Context context) {
        if (mHawkeyeManager == null) {
            mHawkeyeManager = new HawkeyeManager(context);
        }
        return mHawkeyeManager;
    }

    public void startTrace() {
        System.out.println("hawkeye startTrace ");
        mClient.startTrace(mTrace, mStartTraceListener);
    }

    public void stopTrace() {
        System.out.println("hawkeye stopTrace ");
        mClient.stopTrace(mTrace, mStopTraceListener);
    }

    private void setInterval(int gatherInterval, int packInterval) {
        mClient.setInterval(gatherInterval, packInterval);
    }
    private void initClient() {
        System.out.println("hawkeye initClient ");
        mClient = new LBSTraceClient(mContext);
    }

    private void initEntityName() {
        System.out.println("hawkeye initEntityName ");
        mEntityName = getImei(mContext);
    }

    private void initTrace() {
        System.out.println("hawkeye initTrace ");
        mTrace = new Trace(mContext, mServiceId, mEntityName, mTraceType);
    }

    private void addEntity() {
        System.out.println("hawkeye addEntity ");
        String columnKey = "";
        mClient.addEntity(mServiceId, mEntityName, columnKey, mEntityListener);
    }

    private void initEntityListener() {
        System.out.println("hawkeye initEntityListener ");
        mEntityListener = new OnEntityListener() {
            @Override
            public void onRequestFailedCallback(String s) {
                System.out.println("hawkeye onRequestFailedCallback " + s);
            }

            @Override
            public void onQueryEntityListCallback(String s) {
                System.out.println("hawkeye onQueryEntityListCallback " + s);
                super.onQueryEntityListCallback(s);
            }

            @Override
            public void onAddEntityCallback(String s) {
                System.out.println("hawkeye onAddEntityCallback " + s);
                super.onAddEntityCallback(s);
            }

            @Override
            public void onUpdateEntityCallback(String s) {
                System.out.println("hawkeye onUpdateEntityCallback " + s);
                super.onUpdateEntityCallback(s);
            }

            @Override
            public void onReceiveLocation(TraceLocation traceLocation) {
                System.out.println("hawkeye onReceiveLocation latitude = " + traceLocation.getLatitude() + ", longitude = " + traceLocation.getLongitude());
                super.onReceiveLocation(traceLocation);
            }
        };
    }

    private void initStartTraceListener() {
        System.out.println("hawkeye initStartTraceListener ");
        mStartTraceListener = new OnStartTraceListener() {
            @Override
            public void onTraceCallback(int errorNo, String msg) {
                System.out.println("hawkeye onTraceCallback errorNo = " + errorNo + ", msg = " + msg);
            }

            @Override
            public void onTracePushCallback(byte b, String s) {
                System.out.println("hawkeye onTracePushCallback errorNo = " + b + ", msg = " + s);
            }

        };
    }

    private void initStopTraceListener() {
        System.out.println("hawkeye initStopTraceListener ");
        mStopTraceListener = new OnStopTraceListener() {
            @Override
            public void onStopTraceSuccess() {
                System.out.println("hawkeye onStopTraceSuccess");
            }

            @Override
            public void onStopTraceFailed(int errorNo, String msg) {
                System.out.println("hawkeye onStopTraceFailed errorNo = " + errorNo + ", msg = " + msg);
            }
        };
    }

    private void initTrackListener() {
        System.out.println("hawkeye initTrackListener ");
        mTrackListener = new OnTrackListener() {
            @Override
            public void onRequestFailedCallback(String s) {
                System.out.println("hawkeye onRequestFailedCallback " + s);
            }
            @Override
            public void onQueryHistoryTrackCallback(String s) {
                System.out.println("hawkeye onQueryHistoryTrackCallback " + s);
                super.onQueryHistoryTrackCallback(s);
            }

            @Override
            public Map<String, String> onTrackAttrCallback() {
                Map<String, String> map = new HashMap<>();
                map.put("power", String.valueOf(getPowerLevel()));
                System.out.println("hawkeye onTrackAttrCallback power = " + getPowerLevel());
                return map;
            }
        };
        mClient.setOnTrackListener(mTrackListener);
    }

    private int getPowerLevel() {
        Intent intent = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        return level;
    }

    private String getImei(Context context) {
        System.out.println("hawkeye getImei ");
        String mImei = "NULL";
        try {
            mImei = ((TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        } catch (Exception e) {
            System.out.println("获取IMEI码失败");
            mImei = "NULL";
        }
        return mImei;
    }
}
