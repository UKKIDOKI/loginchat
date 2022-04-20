package com.doit.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    Button btn_logout, btn_deluser, btn_chat;
    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        strEmail = getIntent().getStringExtra("email");
        mFirebaseAuth = FirebaseAuth.getInstance();


        btn_logout = findViewById(R.id.btn_logout);
        btn_deluser = findViewById(R.id.btn_deluser);
        btn_chat = findViewById(R.id.btn_chat);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirebaseAuth.signOut();
                Toast.makeText(MainActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("email",strEmail);
                startActivity(intent);
            }
        });
        btn_deluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
                dig.setTitle("회원탈퇴");
                dig.setMessage("회원 탈퇴를 하실려면 확인 버튼을 눌러주세요");
                dig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mFirebaseAuth.getCurrentUser().delete();
                        Toast.makeText(MainActivity.this, "회원탈퇴가 완료되었습니다.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                dig.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dig.show();

            }
        });

    }
}