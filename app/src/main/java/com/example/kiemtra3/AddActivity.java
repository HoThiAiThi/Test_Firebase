package com.example.kiemtra3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText tenkhoahoc, tenthuonggoi, dactinh, maula, congdung, duoctinh,chuy, hinhanh;
    ImageView back;
    Button them;

    FirebaseFirestore fdb = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initID();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> danhsach = new HashMap<>();

                danhsach.put("tenkhoahoc", tenkhoahoc.getText().toString().trim());
                danhsach.put("tenthuonggoi", tenthuonggoi.getText().toString().trim());
                danhsach.put("dactinh", dactinh.getText().toString().trim());
                danhsach.put("maula", maula.getText().toString().trim());
                danhsach.put("congdung", congdung.getText().toString().trim());
                danhsach.put("duoctinh", duoctinh.getText().toString().trim());
                danhsach.put("chuy", chuy.getText().toString().trim());
                danhsach.put("hinhanh", hinhanh.getText().toString().trim());

                fdb.collection("danhsach").add(danhsach).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddActivity.this, MainActivity.class));
                    }
                });
            }
        });

    }

    public void initID() {
        tenkhoahoc = findViewById(R.id.tenkhoahoc);
        tenthuonggoi = findViewById(R.id.tenthuonggoi);
        dactinh = findViewById(R.id.dactinh);
        maula = findViewById(R.id.maula);
        congdung = findViewById(R.id.maula);
        duoctinh = findViewById(R.id.duoctinh);
        chuy = findViewById(R.id.chuy);
        hinhanh = findViewById(R.id.hinhanh);
        back = findViewById(R.id.back);
        them = findViewById(R.id.them);
    }
}