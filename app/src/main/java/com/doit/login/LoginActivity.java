package com.doit.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
// test
public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtpwd; // 로그인 입력창
    ProgressBar progressBar; // 프로그래스바 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("login");

        mEtEmail = findViewById(R.id.et_email);      // editText 와 뷰 연결
        mEtpwd = findViewById(R.id.et_pwd);


        Button btn_login = findViewById(R.id.btn_login);       // 버튼과 뷰 연결
        btn_login.setOnClickListener(new View.OnClickListener() {    // 버튼클릭시 작동할 메서드
            @Override
            public void onClick(View view) {
                // 로그인 버튼 클릭시
                progressBar = findViewById(R.id.progressBar); //프로그래스바 가져오기
                String strEmail = mEtEmail.getText().toString(); // EditText에 입력된 데이터를 가져온뒤 문자열 형태로 변환후 문자열타입 변수를 선언후 안에 저장
                String strPwd = mEtpwd.getText().toString();
                if (strEmail.isEmpty() && strPwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (strEmail.isEmpty()){
                    Toast.makeText(LoginActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (strPwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE); //프로그래스바 표시 활성화
                //
                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE); //프로그래스바 비활성화
                        if (task.isSuccessful()) {
                            //로그인 성공
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "환영합니다", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인실패 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 버튼 눌렸을시
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class); // 새로운 인덴트 객체를 생성후 생성자로 로그인액티비티와 레지스터 액티비티르 넘겨줌
                startActivity(intent);
            }
        });
    }
}