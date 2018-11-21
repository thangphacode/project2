package k.com.duan1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import k.com.duan1.AnhCuaToi.AnhCuaToi;
import k.com.duan1.R;

public class AnhcuatoiAdapter  extends BaseAdapter{
    private Context context;
    private int layout;
    private List<AnhCuaToi> anhCuaToisList;

    public AnhcuatoiAdapter(Context context, int layout, List<AnhCuaToi> anhCuaToisList) {
        this.context = context;
        this.layout = layout;
        this.anhCuaToisList = anhCuaToisList;
    }

    @Override
    public int getCount() {
        return anhCuaToisList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTen,txtMota;
        ImageView imgHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTen =(TextView) view.findViewById(R.id.textviewTenCusTom);
            holder.txtMota =(TextView) view.findViewById(R.id.textviewMoTaCusTom);
            holder.imgHinh =(ImageView) view.findViewById(R.id.imageHinhCusTom);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        AnhCuaToi anhCuaToi = anhCuaToisList.get(i);
        holder.txtTen.setText(anhCuaToi.getTen());
        holder.txtMota.setText(anhCuaToi.getMoTa());
//CHUYEN BYTE[] THANH BITMAP
        byte[] hinhAnh = anhCuaToi.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgHinh.setImageBitmap(bitmap);
        return view;
    }
}
