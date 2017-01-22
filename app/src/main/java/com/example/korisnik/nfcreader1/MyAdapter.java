package com.example.korisnik.nfcreader1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Korisnik on 22.1.2017..
 */

public class MyAdapter extends BaseAdapter{




    private Context myContext;
    private LayoutInflater myInflater;

    public MyAdapter(Context context) {
        this.myContext = context;
        myInflater = (LayoutInflater)
                myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return DataStorage.classes.size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = myInflater.inflate(R.layout.item,
                    viewGroup, false);

        final TextView name = (TextView) view.findViewById(R.id.textView);
        name.setText(DataStorage.classes.get(i).toString());
        return view;

    }
}
