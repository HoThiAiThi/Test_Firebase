package com.example.kiemtra3;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DanhSachAdapter extends RecyclerView.Adapter<DanhSachAdapter.DanhSachViewHolder> {

    Context context;
    List<DanhSach> danhSachList;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    MainActivity mainActivity = new MainActivity();

    public DanhSachAdapter(MainActivity mainActivity, List<DanhSach> danhSachList) {
        this.mainActivity = mainActivity;
        this.danhSachList = danhSachList;
    }

    @NonNull
    @Override
    public DanhSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.item_danhsach, parent, false);

        return new DanhSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachViewHolder holder, int position) {
        DanhSach danhSach = danhSachList.get(position);
        if(danhSach == null) {
            return;
        }

        holder.tenKhoahoc.setText(danhSach.getTenkhoahoc());
        holder.tenThuonggoi.setText(danhSach.getTenthuonggoi());
        Glide.with(mainActivity).load(danhSach.getHinhanh()).into(holder.hinhanh);

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fdb.collection("danhsach").document(danhSach.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(mainActivity, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        mainActivity.showData();
                    }
                });
            }
        });

        holder.chitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", danhSach.getId());
                intent.putExtra("danhsach", bundle);
                mainActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(danhSachList != null) {
            return danhSachList.size();
        }
        return 0;
    }

    public class DanhSachViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhoahoc, tenThuonggoi;
        Button xoa, chitiet;
        ImageView hinhanh;
        LinearLayout the;
        public DanhSachViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhoahoc = itemView.findViewById(R.id.tenkhoahoc);
            tenThuonggoi = itemView.findViewById(R.id.tenthuonggoi);
            xoa = itemView.findViewById(R.id.xoa);
            chitiet = itemView.findViewById(R.id.chitiet);
            the = itemView.findViewById(R.id.the);
            hinhanh = itemView.findViewById(R.id.hinhanh);
        }
    }
}
