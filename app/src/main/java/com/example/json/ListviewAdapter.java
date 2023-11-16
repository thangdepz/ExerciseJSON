package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.squareup.picasso.Picasso;

public class ListviewAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<User> list;

    public ListviewAdapter(Context context, int layout, List<User> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView tvName, tvGender, tvAge, tvPhone;
        CircleImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.tvGender = (TextView) convertView.findViewById(R.id.gender);
            viewHolder.tvAge = (TextView) convertView.findViewById(R.id.age);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.phone);
            viewHolder.imageView = (CircleImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();
        User user = list.get(position);
        viewHolder.tvName.setText(user.getName());
        viewHolder.tvGender.setText(user.getGender());
        viewHolder.tvAge.setText(Integer.toString(user.getAge()));
        viewHolder.tvPhone.setText(user.getPhone());
        Picasso.get().load(user.getImage()).into(viewHolder.imageView);
        return convertView;
    }
}
