package org.alesjia.hawkeyeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.baidu.mapapi.SDKInitializer;

import org.alesjia.adapter.ListEntityAdapter;
import org.alesjia.bean.AddEntityRequest;
import org.alesjia.bean.ListEntityResponse;
import org.alesjia.bean.Request;
import org.alesjia.bean.Response;
import org.alesjia.hawkeye.HawkeyeConstant;
import org.alesjia.hawkeye.HawkeyeManager;
import org.alesjia.hawkeye.HawkeyeReqs;
import org.alesjia.listener.HawkListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mStart;
    private Button mStop;
    private Button mSearch;
    private Button mAdd;
    private Context mContext;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        SDKInitializer.initialize(mContext.getApplicationContext());
        setContentView(R.layout.activity_main);
        mStart = (Button) findViewById(R.id.start);
        mStop = (Button) findViewById(R.id.stop);
        mSearch = (Button) findViewById(R.id.search);
        mAdd = (Button) findViewById(R.id.add);
        mListView = (ListView) findViewById(R.id.listView);
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mAdd.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                HawkeyeManager.getInstance(mContext).startTrace();
                break;
            case R.id.stop:
                HawkeyeManager.getInstance(mContext).stopTrace();
                break;
            case R.id.search:
                Request request = new Request();
                request.setAk(HawkeyeConstant.AK);
                request.setService_id(HawkeyeConstant.SERVICE_ID);
                System.out.println("lxl click()");
                HawkeyeReqs.listEntity(MainActivity.this, request, new HawkListener<ListEntityResponse>(mContext) {
                    @Override
                    public void onBusinessSuccess(ListEntityResponse listEntityResponse) {
                        System.out.println("lxl " + listEntityResponse);
                        mListView.setAdapter(new ListEntityAdapter(mContext, listEntityResponse.getEntities()));
                    }
                }, ListEntityResponse.class);
                break;
            case R.id.add:
                Intent intent = new Intent(mContext, AddColumnActivity.class);
                startActivity(intent);
//                AddEntityRequest addEntityRequest = new AddEntityRequest();
//                addEntityRequest.setAk(HawkeyeConstant.AK);
//                addEntityRequest.setService_id(HawkeyeConstant.SERVICE_ID);
//                addEntityRequest.setEntity_name("lxl");
//                HawkeyeReqs.addEntity(MainActivity.this, addEntityRequest, new HawkListener<Response>(mContext) {
//                    @Override
//                    public void onBusinessSuccess(Response response) {
//                        System.out.println("lxl " + response);
//                    }
//                }, Response.class);
                break;
        }
    }
}
