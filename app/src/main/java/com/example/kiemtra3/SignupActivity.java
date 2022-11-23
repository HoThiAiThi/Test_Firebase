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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    Button btn_gotoSignin, btn_signup;
    EditText edt_signup_username, edt_signup_email, edt_signup_password;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initID();

        btn_gotoSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fdb.collection("users")
                        .whereEqualTo("userName", edt_signup_username.getText().toString().trim())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()) {
                                    QuerySnapshot documentSnapshots = task.getResult();
                                    if(documentSnapshots.isEmpty()) {
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("userName", edt_signup_username.getText().toString().trim());
                                        user.put("userEmail", edt_signup_email.getText().toString().trim());
                                        user.put("userPassword", edt_signup_password.getText().toString().trim());
                                        fdb.collection("users")
                                                .add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Toast.makeText(SignupActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(SignupActivity.this, SigninActivity.class));
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

    }

    public void initID() {
        btn_gotoSignin = findViewById(R.id.btn_gotoSignin);
        btn_signup = findViewById(R.id.btn_signup);
        edt_signup_username = findViewById(R.id.edt_signup_username);
        edt_signup_email = findViewById(R.id.edt_signup_email);
        edt_signup_password = findViewById(R.id.edt_signup_password);
    }
}