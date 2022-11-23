package com.example.kiemtra3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView main_rcv;
    ImageButton add;
    List<DanhSach> danhSachList;
    DanhSachAdapter danhSachAdapter;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initID();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        danhSachList = new ArrayList<>();
        danhSachAdapter = new DanhSachAdapter(this, danhSachList);

        main_rcv.setLayoutManager(new LinearLayoutManager(this));
        main_rcv.setAdapter(danhSachAdapter);

        showData();

    }

    public void showData() {
        danhSachList.clear();
        fdb.collection("danhsach").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                DanhSach danhSach = documentSnapshot.toObject(DanhSach.class);
                                danhSach.setId(documentSnapshot.getId());
                                danhSachList.add(danhSach);
                            }
                            danhSachAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    public void initID() {
        main_rcv = findViewById(R.id.main_rcv);
        add = findViewById(R.id.add);
    }

}