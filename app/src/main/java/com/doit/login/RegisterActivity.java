package com.doit.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtpwd, mEtname, mnickname; // 회원가입 입력창
    private Button mBtnRegister; // 회원가입 버튼
    FirebaseDatabase database;
    ProgressBar progressBar;
    boolean su = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisrter);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("login");

        mEtEmail = findViewById(R.id.et_email);
        mEtpwd = findViewById(R.id.et_pwd);
        mEtname = findViewById(R.id.et_nickname);
        mnickname = findViewById(R.id.et_nickname2);

        mBtnRegister = findViewById(R.id.btn_register);


        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = findViewById(R.id.progressBar);
                // 회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtpwd.getText().toString();
                String strname = mEtname.getText().toString();
                   String strnickname = mnickname.getText().toString();

                if (strEmail.isEmpty() && strPwd.isEmpty() && strname.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "해당 항목을 입력해주세요 ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (strEmail.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (strPwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (strname.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (strEmail.equals(null) || strPwd.equals(null)) {
                    Toast.makeText(RegisterActivity.this, "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                // FirebaseAyth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).

                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);


                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                    UserAccount account = new UserAccount();
                                    account.setIdToken(firebaseUser.getUid());
                                    account.setEmailId(firebaseUser.getEmail());
                                    account.setNname(firebaseUser.getDisplayName());
                                    account.setNickname(strname);
                                    account.setPassword(strPwd);

//                            DatabaseReference myRef = database.getReference("users").child(firebaseUser.getUid());
//
//                            Hashtable<String, String> numbers
//                                    = new Hashtable<String, String>();
//                            numbers.put("email", firebaseUser.getEmail());
//                            myRef.setValue(numbers);
//

                                    // setvalue 데이터베이스에 입력
                                    mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                                    //  user(String.valueOf(mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account)));
                                    Toast.makeText(RegisterActivity.this, "회원가입에 성공 하셨습니다 .", Toast.LENGTH_SHORT).show();


                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "회원가입에 실패 하셨습니다 .", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            }
        });

    }


}
