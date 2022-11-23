package com.example.kiemtra3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SigninActivity extends AppCompatActivity {

    Button btn_gotoSignup, btn_login;
    EditText edt_login_name, edt_login_password;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initID();

        btn_gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fdb.collection("users")
                        .whereEqualTo("userName", edt_login_name.getText().toString().trim())
                        .whereEqualTo("userPassword", edt_login_password.getText().toString().trim())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()) {
                                    QuerySnapshot documentSnapshot = task.getResult();
                                    if(documentSnapshot.isEmpty()) {
                                        Toast.makeText(SigninActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SigninActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SigninActivity.this, MainActivity.class));
                                    }
                                }
                            }
                        });
            }
        });
    }

    public void initID() {
        btn_gotoSignup = findViewById(R.id.btn_gotoSignup);
        btn_login = findViewById(R.id.btn_login);
        edt_login_name = findViewById(R.id.edt_login_name);
        edt_login_password = findViewById(R.id.edt_login_password);
    }

}