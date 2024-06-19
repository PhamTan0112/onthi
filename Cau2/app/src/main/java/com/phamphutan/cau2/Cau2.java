package com.phamphutan.cau2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.phamphutan.Adapter.DienThoaiAdapter;
import com.phamphutan.Model.Databases;
import com.phamphutan.Model.DienThoai;
import com.phamphutan.cau2.databinding.ActivityCau2Binding;

import java.util.ArrayList;
import java.util.List;

public class Cau2 extends AppCompatActivity {
    ActivityCau2Binding binding;
    public Databases db;
    DienThoaiAdapter adapter;
    List<DienThoai> dienThoais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCau2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prepareDb();
        loadData();
        addEvent();
    }

    private void addEvent() {
        binding.btnCau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau2.this, Cau1.class);
                startActivity(intent);
            }
        });
    }

    private void prepareDb() {
        db = new Databases(this);
        db.createSampleData();
    }

    public void loadData() {
        dienThoais = getDaFromDb();
        adapter = new DienThoaiAdapter(Cau2.this, R.layout.item_list, dienThoais);
        binding.lvDienThoai.setAdapter(adapter);
    }

    private List<DienThoai> getDaFromDb() {
        dienThoais = new ArrayList<>();
        Cursor cursor = db.QueryData("SELECT * FROM " + db.TBL_NAME);
        while (cursor.moveToNext()){
            dienThoais.add(new DienThoai(cursor.getString(0),cursor.getString(1),cursor.getDouble(2)));
        }
        cursor.close();
        return dienThoais;
    }
}
