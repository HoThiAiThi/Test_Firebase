package com.example.kiemtra3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity {
    TextView tenkhoahoc, tenthuonggoi, dactinh, maula, congdung, duoctinh,chuy;
    ImageView hinhanh;
    ImageButton back;

    FirebaseFirestore fdb = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initID();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });

        Bundle bundle = getIntent().getBundleExtra("danhsach");
        if(bundle != null) {
            fdb.collection("danhsach")
                    .document(bundle.getString("id")).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        DanhSach danhSach = documentSnapshot.toObject(DanhSach.class);
                        tenkhoahoc.setText(danhSach.getTenkhoahoc());
                        tenthuonggoi.setText(danhSach.getTenthuonggoi());
                        dactinh.setText(danhSach.getDactinh());
                        maula.setText(danhSach.getMaula());
                        congdung.setText(danhSach.getCongdung());
                        duoctinh.setText(danhSach.getDuoctinh());
                        chuy.setText(danhSach.getChuy());
                        Glide.with(getApplicationContext()).load(danhSach.getHinhanh()).into(hinhanh);
                    }
                }
            });
        }


    }

    public void initID() {
        tenkhoahoc = findViewById(R.id.tenkhoahoc);
        tenthuonggoi = findViewById(R.id.tenthuonggoi);
        dactinh = findViewById(R.id.dactinh);
        maula = findViewById(R.id.maula);
        congdung = findViewById(R.id.congdung);
        duoctinh = findViewById(R.id.duoctinh);
        chuy = findViewById(R.id.chuy);
        hinhanh = findViewById(R.id.hinhanh);
        back = findViewById(R.id.back);
    }

}