package com.vtlallklmc.ontapandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnThem;
    ListView lvXe;

    final int INSERT_CODE = 888;

    DBHelper dbHelper;
    XeAdapter xeAdapter;
    ArrayList<Xe> lstXe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getListXe();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivityForResult(intent, INSERT_CODE);
            }
        });
    }
    private void getListXe(){
        dbHelper = new DBHelper(this);
        lstXe = dbHelper.getAllXe();
        xeAdapter = new XeAdapter(this,lstXe);
        lvXe.setAdapter(xeAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==INSERT_CODE && resultCode==RESULT_OK){
            String name = data.getStringExtra("name");
            byte[] img =  data.getStringExtra("img").getBytes();

            dbHelper = new DBHelper(this);
            dbHelper.them(new Xe(img, name));
        }
    }

    public void getView(){
        btnThem = findViewById(R.id.btnThem);
        lvXe = findViewById(R.id.listView);
    }
}