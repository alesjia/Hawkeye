package org.alesjia.hawkeyeapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.alesjia.bean.AddColumnRequest;
import org.alesjia.bean.Response;
import org.alesjia.hawkeye.HawkeyeReqs;
import org.alesjia.listener.HawkListener;

/**
 * Created by lxl on 2016/1/9.
 */
public class AddColumnActivity extends Activity implements View.OnClickListener{
    private Context mContext;
    private Button mBtnAddColumnName;
    private EditText mEtColumnName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_column);
        mContext = this;
        mBtnAddColumnName = (Button) findViewById(R.id.btn_add_column_name);
        mEtColumnName = (EditText) findViewById(R.id.et_column_name);
        mBtnAddColumnName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_column_name) {
            AddColumnRequest request = new AddColumnRequest();
            request.setColumn_key(mEtColumnName.getText().toString());
            request.setColumn_desc("desc");
            request.setColumn_type(3);
            HawkeyeReqs.addColumn(mContext, request, new HawkListener<Response>(mContext) {
                @Override
                public void onBusinessSuccess(Response response) {
                    System.out.println("lxl response = " + response);
                }
            }, Response.class);
        }
    }
}
