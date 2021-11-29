package com.example.mobprog;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class contactsAdapter extends BaseAdapter {
    private LayoutInflater _inflatter;
    private ArrayList<contactsClass> _contacts;

    public contactsAdapter(Activity activity, ArrayList<contactsClass> contacts){
        _inflatter = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _contacts = contacts;
    }

    @Override
    public int getCount(){
        return _contacts.size();
    }
    @Override
    public Object getItem(int x){
        return _contacts.get(x);
    }
    @Override
    public long getItemId(int pos){
        return pos;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        convertView = _inflatter.inflate(R.layout.contact,null);
        TextView name = (TextView)convertView.findViewById(R.id.contactName);
        TextView number = (TextView)convertView.findViewById(R.id.contactNumber);
        ImageView img = (ImageView)convertView.findViewById(R.id.contactImg);
        contactsClass person = _contacts.get(pos);
        name.setText(person.getName());
        number.setText(person.getNumber());
        if(person.getImg() != null)
            img.setImageBitmap(person.getImg());
        else
            img.setImageResource(R.drawable.ic_launcher_foreground);

        convertView.setTag(person.getName());
        return convertView;
    }
}
