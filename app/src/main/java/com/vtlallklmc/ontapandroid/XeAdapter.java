package com.vtlallklmc.ontapandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class XeAdapter extends ArrayAdapter<Xe> {
    public XeAdapter(Context context, ArrayList<Xe> lstXe) {
        super(context,0, lstXe);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if (currentView == null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
        }

        Xe xe = getItem(position);

        TextView tvName = currentView.findViewById(R.id.textView);
        ImageView imgXe = currentView.findViewById(R.id.imageView);

        tvName.setText(xe.getName());

        //chuyển dạng mảng byte thành image
        Bitmap bitmap = BitmapFactory.decodeByteArray(xe.getPic(),0,xe.getPic().length);
        imgXe.setImageBitmap(bitmap);
        //

        return currentView;
    }

}
