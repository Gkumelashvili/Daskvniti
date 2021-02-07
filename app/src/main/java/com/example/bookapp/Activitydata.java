package com.example.bookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activitydata extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    List<String> title_list, answer_list;
    ArrayAdapter<String> arrayAdapter;


    book book1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        listView = findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("daskvniti cda2");
        book1 = new book();
        title_list = new ArrayList<>();
        answer_list = new ArrayList<>();



        arrayAdapter = new ArrayAdapter<>(this,R.layout.item,R.id.item,title_list);

        ValueEventListener answer = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // am cvladis shemogebis shemdeg aplikacias vushvebdi, magram bolos iqrasheboda, gradles gasworeba unda da aplikacia gaeshveba chveulebrivad
//                DataSnapshot dataSnapshot = null;
                for (DataSnapshot d : dataSnapshot.getChildren()) {   //am erorrma gagvawama//
                    book1 = d.getValue(book.class);
                    title_list.add(book1.getTitle());
                    answer_list.add(book1.getAnswer());

                }

                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Activitydata.this, answer.class);        //Eror XELP
                        String p = answer_list.get(position);
                        intent.putExtra("answer", p);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_activitydata);
//    }
//}