package k.com.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import k.com.duan1.R;

public class CustomAdapter extends BaseAdapter {Context context;
    int logos[];
    LayoutInflater inflter;

    public CustomAdapter(Context context, int[] logos) {
        super();
        this.context = context;
        this.logos = logos;
        inflter	=	LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return logos.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup arg2) {
        // TODO Auto-generated method stub
        view	=	inflter.inflate(R.layout.activity_gridview, null);
        ImageView icon	=	(ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(logos[i]);

        return view;
    }

}

