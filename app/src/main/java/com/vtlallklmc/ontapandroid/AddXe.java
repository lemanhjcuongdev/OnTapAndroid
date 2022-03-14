package com.vtlallklmc.ontapandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class AddXe extends AppCompatActivity {
    ImageView imgPicker;
    EditText edtName;
    Button save, cancel;
    int SELECTED_IMG_CODE = 1;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xe);

        getView();

        //SQLite
//        dbHelper = new DBHelper(AddXe.this);

        imgPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imgPickerIntent = new Intent();
                imgPickerIntent.setType("image/*");
                imgPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(imgPickerIntent,"Chọn ảnh"),SELECTED_IMG_CODE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dbHelper.them(new Xe(imgToByteArray(imgPicker), edtName.getText().toString()));
                Intent intentResult = new Intent();
                intentResult.putExtra("name",edtName.getText().toString());
                intentResult.putExtra("img",imgToByteArray(imgPicker));
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECTED_IMG_CODE && resultCode==RESULT_OK){
            Uri uri = data.getData();
            imgPicker.setImageURI(uri);
        }
    }

    public void getView(){
        imgPicker = (ImageView) findViewById(R.id.imgPicker);
        edtName = (EditText) findViewById(R.id.edtName);
        save = (Button) findViewById(R.id.btnSave);
        cancel = (Button) findViewById(R.id.btnCancel);
    }
    //chuyển ảnh thành 1 mảng byte
    public byte[] imgToByteArray(ImageView imgView){
        BitmapDrawable img = (BitmapDrawable) imgView.getDrawable(); //khởi tạo, truyền ImageView
        Bitmap bmp = img.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream(); //khởi tạo luồng xuất mảng byte
        bmp.compress(Bitmap.CompressFormat.JPEG,100,stream); // nén ảnh định dạng JPEG chất lượng 100 với luồng xuất trên
        byte[] byteArray = stream.toByteArray(); //khai báo mảng byte, gán dữ liệu từ luồng xuất ra mảng byte;
        return byteArray;
    }
}