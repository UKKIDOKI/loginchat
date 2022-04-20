package com.doit.login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private RecyclerView recyclerView;
    MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText et_text;
    Button btn_finish, btn_send;
    String strEmail;
    FirebaseDatabase database;
    ArrayList<ChatData> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        database = FirebaseDatabase.getInstance();

        list = new ArrayList<>()
        strEmail = getIntent().getStringExtra("email");
        btn_finish = findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_send = findViewById(R.id.btn_send);
        et_text = findViewById(R.id.et_text);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        String[] myData = {"test", "text1", "test2", "test2"};
        mAdapter = new MyAdapter(myData);
        recyclerView.setAdapter(mAdapter);


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                ChatData data = snapshot.getValue(ChatData.class);
                String key = snapshot.getKey();
                String stEmail = data.getEmail();
                String stText = data.getText();
                Log.d(TAG, "stEmail + text "+ stEmail+stText);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DatabaseReference ref = database.getReference("message");
                ref.addChildEventListener(childEventListener);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stText = et_text.getText().toString();
                Toast.makeText(ChatActivity.this, "MSG:" + stText, Toast.LENGTH_SHORT).show();


                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String datetime = dateformat.format(c.getTime());
                System.out.println(datetime);
                DatabaseReference myRef = database.getReference("message").child(datetime);

                Hashtable<String, String> numbers
                        = new Hashtable<String, String>();
                numbers.put("email", strEmail);
                numbers.put("text", stText);


                myRef.setValue(numbers);
            }
        });
    }
}
