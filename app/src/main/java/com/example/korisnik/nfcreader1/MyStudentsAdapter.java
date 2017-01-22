package com.example.korisnik.nfcreader1;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Korisnik on 22.1.2017..
 */

public class MyStudentsAdapter extends BaseAdapter{

    private Context myContext;
    private LayoutInflater myInflater;

    public MyStudentsAdapter(Context context) {
        this.myContext = context;
        myInflater = (LayoutInflater)
                myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return DataStorage.allStudents.size();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = myInflater.inflate(R.layout.student_item,
                    viewGroup, false);

        final TextView name = (TextView) view.findViewById(R.id.textView1);
        name.setText(DataStorage.allStudents.get(i).toString());
        if (DataStorage.students.containsValue(DataStorage.allStudentsID.get(i).toString()))
            name.setBackgroundColor(0x00ff00);
        return view;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
