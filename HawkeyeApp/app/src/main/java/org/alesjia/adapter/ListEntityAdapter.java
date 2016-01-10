package org.alesjia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.alesjia.bean.ListEntityResponse;
import org.alesjia.hawkeyeapp.R;

import java.util.List;

/**
 * Created by lxl on 2016/1/9.
 */
public class ListEntityAdapter extends BaseAdapter {
    private List<ListEntityResponse.Entities> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;
    public ListEntityAdapter(Context context, List<ListEntityResponse.Entities> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (null == convertView) {
            view = mInflater.inflate(R.layout.entities_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = convertView;
        }
        ListEntityResponse.Entities entities = mDatas.get(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.tv_entity_name.setText("物体名字：" + entities.getEntity_name());
        viewHolder.tv_create_time.setText("创建时间：" + entities.getCreate_time());
        viewHolder.tv_modify_time.setText("修改时间：" + entities.getModify_time());
        viewHolder.tv_loc_time.setText("定位时间：" + entities.getRealtime_point().getLoc_time());
        viewHolder.tv_location.setText("位置信息：" + entities.getRealtime_point().getLocation());
        viewHolder.tv_direction.setText("方向信息：" + entities.getRealtime_point().getDirection());
        viewHolder.tv_speed.setText("即时速度：" + entities.getRealtime_point().getSpeed());
        viewHolder.tv_radius.setText("定位精度：" + entities.getRealtime_point().getRadius());
        viewHolder.tv_power.setText("剩余电量：" + entities.getRealtime_point().getPower());
        return view;
    }

    static class ViewHolder {

        TextView tv_entity_name;
        TextView tv_create_time;
        TextView tv_modify_time;
        TextView tv_loc_time;
        TextView tv_location;
        TextView tv_direction;
        TextView tv_speed;
        TextView tv_radius;
        TextView tv_power;

        public ViewHolder(View view) {

            tv_entity_name = (TextView) view.findViewById(R.id.tv_entity_name);
            tv_create_time = (TextView) view.findViewById(R.id.tv_create_time);
            tv_modify_time = (TextView) view.findViewById(R.id.tv_modify_time);
            tv_loc_time = (TextView) view.findViewById(R.id.tv_loc_name);
            tv_location = (TextView) view.findViewById(R.id.tv_location);
            tv_direction = (TextView) view.findViewById(R.id.tv_direction);
            tv_speed = (TextView) view.findViewById(R.id.tv_speed);
            tv_radius = (TextView) view.findViewById(R.id.tv_radius);
            tv_power = (TextView) view.findViewById(R.id.tv_power);

        }
    }
}
