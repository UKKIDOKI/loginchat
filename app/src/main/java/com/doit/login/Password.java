package com.doit.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Password extends AppCompatActivity {
    EditText editText;
    Button button;
    String et;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length() == 0) {
                    Toast.makeText(Password.this, "이메일을 입력해주세요 ", Toast.LENGTH_SHORT).show();
                } else {
                    mFirebaseAuth = FirebaseAuth.getInstance();
                    mFirebaseAuth.sendPasswordResetEmail(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Password.this, "비밀번호 재설정 메일이 전송되었습니다.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Password.this, "존재하지 않는 이메일 입니다.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
    }
}